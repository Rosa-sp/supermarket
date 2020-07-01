
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
	if($("#pnzFocus_form").length>0){
		//表单提交
		$(document).on("submit","#pnzFocus_form",function(){
			if(springstage.fileupload.getHiddenInput()){
				var $form_input_data=springstage.fileupload.getHiddenInput();
				$("#"+$form_input_data.prop("id")).remove();
				$(this).append($form_input_data);
				return true;
			}
			return false;
		});

		//应用平台-切换选择
		$(document).on("change", "#platform", function(){
			var cur = $(this);
			var targetUrlDiv = $("#targetUrl").parents("div.form-group");

			if(cur.val()=="WAP"){
				targetUrlDiv.hide();
			}else{
				targetUrlDiv.show();
			}
		});

	}


 });

