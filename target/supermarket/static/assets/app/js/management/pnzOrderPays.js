
$(function() {
        //TODO 模态窗口表单更新成功回调,如果不需要回调，删除这段代码
		$(document).on("update:success","#modal-ajax form",function(event,data){

    	});
        //TODO 模态窗口表单创建成功回调,如果不需要回调，删除这段代码
    	$(document).on("create:success","#modal-ajax form",function(event,data){

    	});
        //TODO 表格删除按钮回调,如果不需要回调，删除这段代码
    	$(document).on("delete:success","a.btn-delete",function(event,data){

    	});
 });