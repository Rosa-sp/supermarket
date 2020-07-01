var treeObj = null;
$(function() {
	$(document).on("ajax:success",".btn-delete",function(event,data, status, xhr){
		flash(data.level, data.message);
		if(data.success){
			$(this).parents("tr").find(".btn-delete").remove();//删除所在行
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
	if(springstage && springstage.fileupload){
		springstage.fileupload({
			pick:".picker",
			fileNumLimit:5,
			queueSize:5
		});
	}

	if($("#treeDom").length>0){
		treeObj = $("#treeDom").simpleTree({
			onNodeClick:function(event, treeId, treeNode){
				console.log(">>> " + treeNode.id);
				if(treeNode.id!="0"){
					$("#categoryId").val(treeNode.id);
				}else{
					$("#categoryId").val("");
				}
				$("#searchform").submit();
			}
		});
		treeObj.selectNodeByKey("id", $("#categoryId").val());
	}


	if($("#pnzGoodForm").length>0){
		//富文本
		KindEditor.ready(function(K){
			window.editor = K.create("#detail", {
				filterMode : true,
				items:springstage.kindEditorItems,
				uploadJson : '/management/image/editorUpload'
			});
		});


		//切换部门 (1-品农, 2-品质)
		$(document).on("change", "#dep", function(){
			var cur = $(this);
			//商品分类
			var spfl_con = $(".spfl_con");
			var pnzCategory_pn = $(".pnzCategory_pn");
			var pnzCategory_pz = $(".pnzCategory_pz");
			//运营归类
			var yygl_con = $(".yygl_con");
			var yygl_pn = $(".yygl_pn");
			var yygl_pz = $(".yygl_pz");


			if(cur.val()=="1"){
				//品农
				//商品分类处理
				spfl_con.removeClass("hide");
				pnzCategory_pn.removeAttr("disabled").removeClass("hide");
				pnzCategory_pz.attr("disabled", "disabled").addClass("hide");

				//运营分类处理
				yygl_con.removeClass("hide");
				yygl_pn.removeClass("hide").find(".yygl_ra").removeAttr("disabled");
				yygl_pz.addClass("hide").find(".yygl_ra").attr("disabled", "disabled");
			}else if(cur.val()=="2"){
				//品质
				//商品分类处理
				spfl_con.removeClass("hide");
				pnzCategory_pz.removeAttr("disabled").removeClass("hide");
				pnzCategory_pn.attr("disabled", "disabled").addClass("hide");

				//运营分类处理
				yygl_con.removeClass("hide");
				yygl_pz.removeClass("hide").find(".yygl_ra").removeAttr("disabled");
				yygl_pn.addClass("hide").find(".yygl_ra").attr("disabled", "disabled");
			}else{
				//商品分类处理
				spfl_con.addClass("hide");
				pnzCategory_pn.attr("disabled", "disabled").addClass("hide");
				pnzCategory_pz.attr("disabled", "disabled").addClass("hide");

				//运营分类处理
				yygl_con.addClass("hide");
				yygl_pn.addClass("hide").find(".yygl_ra").attr("disabled", "disabled");
				yygl_pz.addClass("hide").find(".yygl_ra").attr("disabled", "disabled");
			}
		});


		//表单保存
		$(document).on("submit","#pnzGoodForm",function(){
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

function reloadTreeData(){
	//$("#treeDom").reloadTree();
	treeObj.reloadTree({select:{key:"id", value:2}});
	//console.log(treeNode);
}