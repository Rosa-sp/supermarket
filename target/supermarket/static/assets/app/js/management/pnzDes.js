
$(function() {
	$(document).on("ajax:success",".btn-delete",function(event,data, status, xhr){
		if(data.success){
			flash("success", "删除成功!");
			$(this).parents("tr").remove();//删除所在行
		}else {
			flash("error", data.message);
		}
	});  
 });