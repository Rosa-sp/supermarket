
$(function() {
	$(document).on("ajax:success",".btn-delete",function(event,data, status, xhr){
		if(data.success){
			flash("success", "删除成功!");
			$(this).parents("tr").remove();//删除所在行
		}else {
			flash("error", data.message);
		}
	});

	/**
	 * 上传头像
	 */
	if(springstage && springstage.fileupload){
		springstage.fileupload({
			pick:".picker"
		});

	}

	$(document).on("submit","form",function(){
		if(springstage.fileupload.getHiddenInput()){
			var $form_input_data=springstage.fileupload.getHiddenInput();
			$("#"+$form_input_data.prop("id")).remove();
			$(this).append($form_input_data);
			return true;
		}
		return false;
	});

 });