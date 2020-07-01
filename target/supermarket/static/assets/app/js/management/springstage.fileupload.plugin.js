/****************************
 * 依赖于 jQuery与webuploader
 *  http://fex.baidu.com/webuploader/
 ****************************/
var springstage=springstage || {};
/**
 * 0. 结构必须是

 <div class="uploader" data-userid="1">
        <div id="picker"><i class="fa fa-check-circle-o"></i> 选择图片</div>
        <div class="queueList"></div>
 </div>

 *1.  pick:'#picker'  默认为 #picker
 *
 *2.  fileNumLimit: 文件数量限制,默认 10
 *
 *3.  server : 文件上传地址,默认 $.ctx+"/management/image/upload"
 *
 *4.  fileSingleSizeLimit :上传文件大小限制 默认3M
 *
 *5.  accept:{    可接受的文件类型,默认为图片
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }

 *6.  queueSize: 1 单个队列文件数量，默认没有限制
 */

(function($,springstage){
    springstage=springstage ||{};
    var uploader={};
    var defaults={
        swf: $.ctx+"/assets/lib/js/webuploader/Uploader.swf",
        server: $.ctx+"/management/image/upload",
        resize: false,
        dnd: ".queueList",
        paste: document.body,
        disableGlobalDnd: true,
        thumb: {
            width: 100
            , height: 100
            , quality: 70
            , allowMagnify: true
            , crop: true
            //, type: "image/jpeg"
        },
        prepareNextFile: true,
        chunked: false,
        chunkSize:5*1024*1024,
        threads: true,
        fileNumLimit: 10,
        duplicate: false,
        auto: true,
        fileSingleSizeLimit: 3 * 1024 * 1024,
        accept:{
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    }

    springstage.fileupload=function(options){
        options=options ||{pick:"#picker"};
        if(!options.pick){
            options.pick="#picker";
        }
        options = $.extend(true, {}, defaults, options);
        var backEndUrl=options.server,
            chunkSize=defaults.chunkSize;
        /**
         *
         * @param originFileName 原始文件名
         * @param newFileName    新文件名 base64编码,已包含完整路径
         * @param btn 上传的按钮
         */
        var uploadSuccess=function(originFileName,newFileName,btn){
            var $wrap=$(btn).parent();
            var data=$wrap.attr("data-files");
            if(data){
                data=data+","+originFileName+":"+newFileName;
            }else{
                data=originFileName+":"+newFileName;
            }
            $wrap.attr("data-files",data);
        };

        /**
         * 获取当前按钮所在的包裹器
         * @param file
         * @private
         */
        var _getCurrentWrap=function(file){
            return $(file.source._refer).parent();
        }

        var setStatusText=function(file,cls,text){
            $(".file-"+file.id).find(".status-message").html('<p class="'+cls+'">'+text+'</p>');
        }

        /**
         * 在文件发送之前request，此时还没有分片（如果配置了分片的话），可以用来做文件整体md5验证。
         * 秒传验证
         * @param file
         */
        var beforeSendFile=function(file){
            var me=this,
                owner=me.owner,
                task = WebUploader.Deferred();
            var start = new Date().getTime();
            //值提取文件前10M字节的MD5码

            owner.md5File(file,0,10*1024*1024).progress().then(function(val){ //完成
                    file.md5=val;//保存到文件信息中
                    var userId=_getCurrentWrap(file).get("userid");
                    file.uniqueFileName=md5(''+userId+file.name+file.type+file.lastModifiedDate+file.size); //拿到上传文件的唯一名称，用于断点续传
                    $.ajax({
                        type: "POST"
                        , url: backEndUrl+"/md5"
                        , data: {
                            totalSize: file.size
                            , md5: val
                            ,type: file.type
                            ,name: file.name
                            ,ext: file.ext
                            ,id:file.id
                            ,uniqueFileName:file.uniqueFileName
                        }
                        , cache: false
                        , timeout: 1000
                        , dataType: "json"
                    }).then(function(data){  //1009 上传限制,不允许上传  1004 服务器上不存在  1003 服务器上已存在

                        if(data && data.status==1009){
                            task.reject();
                            owner.cancelFile(file);
                            setStatusText(file,"text-danger",data.message);
                        }

                        if(data && data.status==1003){  //若存在，这返回失败给WebUploader，表明该文件不需要上传
                            task.reject();
                            owner.skipFile(file); //跳过文件上传，直接标记指定文件为已上传状态。

                            file.fileOnServer = data.fileOnServer;  //文件路径
                            uploadSuccess(data.name,data.fileOnServer,$(".file-"+file.id).parent());
                            $(".file-"+file.id).find(".progress-bar").css({'width':'100%'}).find('span').html('100%');
                            setStatusText(file,"text-success","秒传完成");

                        }else{
                            task.resolve();
                        }
                    }, function(jqXHR, textStatus, errorThrown){    //任何形式的验证失败，都触发重新上传
                        task.resolve();
                    });
                });
            return $.when(task);
        };

        /**
         * 在分片发送之前request，可以用来做分片验证，如果此分片已经上传成功了，可返回一个rejected promise来跳过此分片上传
         * @param block
         */
        var beforeSend=function(block){
            //分片验证是否已传过，用于断点续传
            var task =  WebUploader.Deferred();
            $.ajax({
                type: "POST"
                , url: backEndUrl+"/chunk"
                , data: {
                    name: block.file.name //文件名
                    ,uniqueFileName: block.file.uniqueFileName //唯一文件名
                    , chunk: block.chunk //分片索引
                    , chunks : block.chunks
                    , size: block.end - block.start
                }
                , cache: false
                , timeout: 1000
                , dataType: "json"
            }).then(function(data, textStatus, jqXHR){

                if(data && data.status==1003){
                    task.reject();//若存在，返回失败给WebUploader，表明该分块不需要上传
                }else{
                    task.resolve();
                }
            }, function(jqXHR, textStatus, errorThrown){    //任何形式的验证失败，都触发重新上传
                task.resolve();
            });

            return $.when(task);
        };

        /**
         * 文件上传并合并
         * @param file
         */
        var afterSendFile=function(file){
            var chunksTotal = 0;
            if((chunksTotal = Math.ceil(file.size/chunkSize)) > 1){
                //合并请求
                var task =  WebUploader.Deferred();
                $.ajax({
                    type: "POST"
                    , url: backEndUrl+"/merge"
                    , data: {
                        uniqueFileName:file.uniqueFileName
                        , chunks: chunksTotal
                        , ext: file.ext
                        , md5: file.md5
                        , name: file.name
                        , totalSize: file.size
                        , type: file.type
                    }
                    , cache: false
                    , dataType: "json"
                }).then(function(data, textStatus, jqXHR){

                    if(data &&(data.status==1003 ||data.status==1005)){ //上传成功
                        task.resolve();
                        uploadSuccess(data.name,data.fileOnServer,$(".file-"+file.id).parent());
                        setStatusText(file,'text-success','上传成功');

                    }else{
                        task.reject();
                        setStatusText(file,'text-danger',data.message);//上传失败
                    }
                }, function(jqXHR, textStatus, errorThrown){
                    task.reject();
                });
                return $.when(task);
            }else{
                $(".file-"+file.id).find(".progress-bar").css({'width':'100%'}).find('span').html('100%');
                setStatusText(file,'text-success','上传成功');
            }
        };

        /**
         * 加入队列之前验证
         */
        var beforeFileQueued=function(file){
            if(options.queueSize && file.source._refer.parent().find(".media").length>=options.queueSize){
                window.flash("error","最多上传"+options.queueSize+"个文件,请删除后在上传！");
                return false;
            }
            //验证文件大小
            if(options.fileSingleSizeLimit && options.fileSingleSizeLimit<file.size){
                window.flash("error","文件不能超过"+WebUploader.Base.formatSize(options.fileSingleSizeLimit));
                return false;
            }
            //验证扩展名
            if(options.accept && options.accept.extensions && options.accept.extensions.indexOf(file.ext.toLowerCase())==-1){
                window.flash("error","文件类型错误,允许上传的文件为:"+options.accept.extensions);
                return false;
            }
        }


        /**
         * 加入队列后
         * @param file
         */
        var fileQueued=function(file){
            var $divMedia=$('<div class="media file-'+file.id+'"></div>'),
            $left=$('<div class="media-left"></div>'),
            $left_img=$("<img class='file-img' />"),
            $center=$('<div class="media-body"></div>'),

            $file_name=$("<h4 class='media-heading file-name'>"+file.name+"</h4>"),
            $file_size=$('<h4 class="media-heading file-size">'+WebUploader.Base.formatSize(file.size)+'</h4>'),
            $progress=$('<div class="progress"></div>'),
            $progress_bar=$('<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="min-width: 2em;"><span>20%</span></div>'),
            $message=$('<div class="media-heading status-message"></div>');

            $right=$('<div class="media-right"></div>'),
            $right_btn=$('<a class="btn btn-danger">取消</a>');
            $right_btn.on("click",function(){
                if(!file.fileOnServer){
                    $.post(backEndUrl+"/delete",{
                        uniqueFileName:file.uniqueFileName
                        , id:file.id
                        , lastModifiedDate:file.lastModifiedDate
                        , ext: file.ext
                        , md5: file.md5
                        , name: file.name
                        , type: file.type
                    },function(data){

                    });
                }else{
                    //删除数据
                    var deleteData=file.name+":"+file.fileOnServer;
                    var container=$divMedia.parent().parent();
                    var dataFiles=container.attr("data-files");
                    if(dataFiles && dataFiles.indexOf(deleteData)!=-1){
                        dataFiles=dataFiles.replace(deleteData,"").split(",").filter(function(n){return n;}).join(",");
                        container.attr("data-files",dataFiles)
                    }
                }
                uploader.removeFile(file,true);
                $divMedia.remove();

            });

            $left_img.appendTo($left);
            $file_name.appendTo($center);
            $file_size.appendTo($center);
            $message.appendTo($center);
            $progress.appendTo($center);
            $progress.append($progress_bar);
            $right.append($right_btn);
            $left.appendTo($divMedia);
            $center.appendTo($divMedia);
            $right.appendTo($divMedia);
            _getCurrentWrap(file).find(".queueList").append($divMedia);
            uploader.makeThumb(file, function(error, src){
                if(error){
                    $left_img.replaceWith("<span>不能预览</span>");
                }
                $left_img.attr("src", src);
            });




        };

        /**
         * 绑定一些参数 到 服务器端的 FileInfo对象中
         * @param file
         */
        var uploadBeforeSend=function( block, data ){
            var file = block.file;
            // 修改data可以控制发送哪些携带数据。
            data.md5= file.md5;
            data.size=block.end - block.start;//分片大小
            data.totalSize=file.size;
            data.uniqueFileName=file.uniqueFileName;
            data.ext=file.ext;
        };

        /**
         * 上传进度条控制
         * @param file
         * @param percentage
         */
        var uploadProgress=function(file, percentage){

            $(".file-"+file.id).find(".progress-bar").css({'width':(percentage * 100 )+'%'}).find('span').html((percentage * 100) +'%');

            if(percentage>=1){
                setStatusText(file,"text-success",'上传完成');
            }

        };

        var uploadError=function(file,reason){
            setStatusText(file,"text-danger",reason);
        };
        //注册钩子函数
        WebUploader.Uploader.register({
            "before-send-file": "beforeSendFile",
            "before-send": "beforeSend"
            , "after-send-file": "afterSendFile"
        },{
            beforeSendFile:beforeSendFile,
            beforeSend:beforeSend,
            afterSendFile:afterSendFile
        });

        uploader = WebUploader.create(options);
        uploader.on("beforeFileQueued",beforeFileQueued);
        uploader.on("fileQueued",fileQueued);
        uploader.on('uploadBeforeSend',uploadBeforeSend);
        uploader.on("uploadProgress",uploadProgress);
        uploader.on("uploadError",uploadError);
        uploader.on("uploadSuccess",function(file,response){
            if(response && response.status==1000 && !response.chunk){ //如果不是分片,并且已经上传
                file.fileOnServer=response.fileOnServer;
                uploadSuccess(file.name,file.fileOnServer,$(".file-"+file.id).parent());
            }
        });
        return uploader;
    };

    /**
     * 获取上传数据
     * @returns 正在上传返回 空字符串, 正常返回 JSON对象
     */
    springstage.fileupload.getJsonData=function(){
        if(uploader.getStats && uploader.getStats().progressNum>0){
            window.flash("error","文件未上传完成");
            return "";
        }
        var result={};
        $(uploader.options.pick).each(function(){
            var container=$(this).parent();
            var data=container.attr("data-files");
            result[container.attr("data-fieldName")]=data.split(",").filter(function(n){return n;}).join(",");
        });
        return result;

    }
    /**
     * 获取上传数据
     * @returns 正在上传返回 空字符串, 正常返回隐藏域的JQuery对象
     */
    springstage.fileupload.getHiddenInput=function(){
        if(uploader.getStats && uploader.getStats().progressNum>0){
            window.flash("error","文件未上传完成");
            return "";
        }
        var result=$("<div id='form_file_data'></div>");
        var domId = uploader.options.pick;
        if( typeof(uploader.options.pick) === "object" ){
            domId = uploader.options.pick.id;
        }
        $(domId).each(function(){
            var container=$(this).parent();
            var $hidden=$("<input type='hidden'/>");
            $hidden.attr("name",container.attr("data-fieldName"));
            var data=container.attr("data-files");
            if(data){
                $hidden.attr("value",data.split(",").filter(function(n){return n;}).join(","));
                $hidden.appendTo(result);
            }
        });
        return result;
    }


})(jQuery,springstage);
