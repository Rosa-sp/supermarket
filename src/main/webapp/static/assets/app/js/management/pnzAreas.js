
$(function() {
	$(document).on("ajax:success",".btn-del",function(event,data, status, xhr){
		if(data.success){
			flash("success", "删除成功!");
			$(this).parents("tr").remove();//删除所在行
		}else {
			//flash("error", data.message);
		}
	});
 });


function checkrepetition(){

	var fullName=$("#areaName").val();
	var rv=false;
	$.ajax({
		type : "post",
		url : "management/pinYin",
		data: { fullName : fullName },
		cache : false,
		async : false,
		dateType : "json",
		success : function(json) {
			if (json.success==true) {
				$("#areaPinyinShort").val(json.content);
			}
		}
	});
}