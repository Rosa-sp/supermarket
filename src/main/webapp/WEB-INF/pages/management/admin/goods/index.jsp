<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2020/1/8
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <title>商品管理</title>
<css>
    <link rel="stylesheet" href="${ctx}/static/assets/lib/js/zTree/css/metroStyle/metroStyle.css" />
    <link rel="stylesheet" href="${ctx}/static/assets/app/css/management/goods.css"/>
    <link rel="stylesheet" href="${ctx}/static/assets/lib/layui/css/layui.css">
</css>

<body>
    <!-- Right sidebar start -->
    <!-- Right sidebar end -->
    <!-- Main Container Start -->
    <div class="main-container">
        <!-- Top Bar Starts -->
        <div class="top-bar clearfix">

            <div class="page-title">
                <h4>
                    <div class="fs1" aria-hidden="true" data-icon="&#xe0ac;"></div>
                    商品管理 <a href="javascript:void(0)" class="samll">商品管理</a>
                </h4>
            </div>

        </div>
        <!-- Top Bar Ends -->
        <!-- Container fluid Starts -->
        <div class="container-fluid">
            <!-- Spacer starts -->
            <div class="spacer-xs">
                <div class="row no-gutter">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4>商品列表</h4>
                                <ul class="links">
                                    <li>
                                        <a href="#" title="新增商品" onclick="edit()">
                                            <i class="fa fa-plus"></i> 新增商品
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <div class="panel-body">
                                <div class="row">

                                    <div class="col-md-2">
                                        <div class="panel-heading" style="font-weight: bold">
                                            商品类别
                                        </div>
                                        <div class="panel-body">

<%--                                            <div id="jstree"></div>--%>

                                         <div id="test5" class="demo-tree"></div>
                                        </div>
                                   </div>

                                    <div class="col-md-10">
                                        <div class="row">
                                            <div style="display:none;">
                                                <input id="categoryId" name="search.goodsGoscte.id_eq" type="text" value=""/>
                                            </div>
                                            <div class="col-xs-3">
                                                <input id="searchInput" name="search.goodsname_like,goodsnumber_eq,goodsbarno_like" class="form-control" placeholder="搜索名称，编号，类别" type="text" value=""/>
                                            </div>
                                            <div class="col-xs-3">
                                                <button type="submit" id="search" class="btn btn-sm btn-success">
                                                    查询
                                                    <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
                                                </button>
                                            </div>
                                        </div>

                                        <br />
                                        <table id="table"></table>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <!-- Spacer ends -->
        </div>
        <!-- Container fluid ends -->
    </div>
    <!-- Main Container Start -->

</body>
<js>
    <script type="text/javascript" src="${ctx}/static/assets/lib/js/zTree/js/jquery.ztree.core-3.5.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/assets/app/js/management/goods.js"></script>
    <script type="text/javascript" src="${ctx}/static/assets/lib/layui/layui.js" charset="utf-8"></script>
    <script>
        layui.use(['tree', 'util'], function() {
            var tree = layui.tree
                , layer = layui.layer
                , util = layui.util
                ,data = ${categorys};

            tree.render({
                elem: '#test5'
                , data: data
                , isJump: true  //link 为参数匹配
                ,onlyIconControl: true
                ,click: function(node){
                    //console.log(node) //node即为当前点击的节点数据
                    //console.log(node.data.id)
                    // var categoryId = {
                    //     id:node.data.id
                    // };

                    //刷新表格
                    reloadTable(node.data.id)
                }
            })
        })
    </script>
</js>
