
$(function() {
	$(document).on("ajax:success",".btn-delete",function(event,data, status, xhr){
		if(data.success){
			flash("success", "下架成功");
			$(this).parents("tr").find(".btn-delete").remove();
		}
	});

	$(document).on("ajax:success",".btn-downLine,.btn-upLine",function(event,data, status, xhr){
		if(data.success) {
			window.flash(data.level, data.message);
		}

		setTimeout(function () {
			window.location.reload();
		}, 1500);

		/*//更新表格
		 var pageIndex = $(".pagination li.active a:first").text();
		 window.springstage.reloadTable({ //重新加载表格数据
		 "page.pn": pageIndex
		 }, false);*/
	});

	/**
	 * 上传头像
	 */
	if($("#pnzOfflineShop_form").length>0){
		springstage.fileupload({
			pick:".picker",
			fileNumLimit:1,
			queueSize:1
		});




	$(document).on("submit","form",function(){
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