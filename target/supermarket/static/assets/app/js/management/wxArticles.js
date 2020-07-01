
$(function() {
	//TODO 表格删除按钮回调,如果不需要回调，删除这段代码
	$(document).on("delete:success","a.btn-delete",function(event,data){
	});

	if(springstage && springstage.fileupload){
		springstage.fileupload({
			pick:".picker",
			fileNumLimit:1,
			queueSize:1
		});
	}

	// form页专用
	if($("#wxArticle_form").length>0){
		//表单提交
		$(document).on("submit","#wxArticle_form",function(){
			if(springstage.fileupload.getHiddenInput()){
				var $form_input_data=springstage.fileupload.getHiddenInput();
				$("#"+$form_input_data.prop("id")).remove();
				$(this).append($form_input_data);
				return true;
			}
			return false;
		});
	}
	if($("#wxArticle_form").length>0){

		KindEditor.ready(function(K){
			window.editor = K.create("#detail", {
				filterMode : true,
				items:springstage.kindEditorItems,
				uploadJson : '/management/image/editorUpload'
			});
		});


		$(document).on("submit","#wxArticle_form",function(){

			window.editor.sync();

			if(springstage.fileupload.getHiddenInput()){
				var $form_input_data=springstage.fileupload.getHiddenInput();
				$("#"+$form_input_data.prop("id")).remove();
				$(this).append($form_input_data);
				return true;
			}

			return false;
		});
	}


});

