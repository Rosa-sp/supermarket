

$(function() {

	var treeObj = $("#treeDom").simpleTree({
		onNodeClick:function(event, treeId, treeNode){
			$("#categoryId").val(treeNode.id);
			$("#searchform").submit();
		}
	});

	treeObj.selectNodeByKey("id", $("#categoryId").val());

 });




