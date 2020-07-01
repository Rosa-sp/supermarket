/****************************
 * 吴青
 *  用于下拉选择树上的某个节点
 *  2016-7-14
 * 依赖于 ztree
 *  http://www.ztree.me/v3/api.php
 ****************************/
var springstage=springstage || {};


(function($,springstage){
    $.fn.simpleTree = function(defaults){
        var $this = $(this);
        var initConfig = function(){
            if(!$this.hasClass("ztree")){
                $this.addClass("ztree");
            }
            defaults = $.extend({
                url:"",
                expandAll:false,
                rootName:"",
                onNodeClick:null,
                settings:null
            }, defaults);

            var url = $this.data("url");
            if(url){
                defaults.url = url;
            }

            var rootName = $this.data("rootName")
            if(rootName){
                defaults.rootName = rootName;
            }

            if(defaults.url==""){
                throw "请在dom上添加data-url 获取数据";
            }

            //树节点配置
            var settings = {
                data: {
                    simpleData: {
                        enable: true,
                        idKey:'id',
                        pIdKey:'parentId'
                    }
                },
                callback:{
                    onClick:function (event, treeId, treeNode) {
                        if(defaults.onNodeClick){
                            defaults.onNodeClick(event, treeId, treeNode)
                        }
                    }
                }
            };

            if(defaults.settings){
                defaults.settings = $.extend(settings, defaults.settings);
            }else{
                defaults.settings = settings;
            }

            return defaults;
        };

        //根据key, value 选中某个节点
        $this.selectNodeByKey=function(key, value){
            if(!$this.tree){
                return;
            }
            var node =$this.tree.getNodeByParam(key, value);
            if(node){
                $this.tree.selectNode(node, false);
            }
        };
        //重新加载树
        $this.reloadTree= function(params){
            var defaults = initConfig();

            var zNode = [];
            $.ajax({
                type:"get",
                url:$.ctx+defaults.url,
                async: false
            }).done(function(data, textStatus ,jqxhr){
                zNode = data.content;
            });

            if(defaults.rootName!=""){
                zNode.push({
                    id:0, parentId:-1, name:defaults.rootName, open:true
                });
            }

            $this.tree= $.fn.zTree.init($this, defaults.settings, zNode);

            if(params && params.select){
                $this.selectNodeByKey(params.select.key, params.select.value);
            }

            if(defaults.expandAll){
                $this.tree.expandAll(true);
            }

        };
        $this.reloadTree();
        return  $this;
    };

})(jQuery,springstage);



