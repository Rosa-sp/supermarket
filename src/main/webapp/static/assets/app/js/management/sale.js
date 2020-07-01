$(function() {
//初始化表格
	loadTable();

	$("#search").click(function () {
		$('#table').bootstrapTable('refresh');
	});

});


function loadTable() {
	$('#table').bootstrapTable({
		url: $.ctx + "/sale/pages",
		toolbar: '#toolbar', //工具按钮用哪个容器
		showRefresh: true,
		showToggle: true,
		showColumns: true,
		striped: true, //是否显示行间隔色
		cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination: true, //是否显示分页（*）
		sortable: true, //是否启用排序
		// sortOrder : "asc", //排序方式
		queryParams: searchParam,//传递参数（*）
		sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
		pageNumber: 1, //初始化加载第一页，默认第一页
		pageSize: 10, //每页的记录行数（*）
		pageList: [5, 10, 20], //可供选择的每页的行数（*）
		search: false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		strictSearch: false,
		showColumns: false, //是否显示所有的列
		showRefresh: false, //是否显示刷新按钮
		minimumCountColumns: 2, //最少允许的列数
		clickToSelect: false, //是否启用点击选中行
		/* height : 480, *///行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId: "id", //每一行的唯一标识，一般为主键列
		showToggle: false, //是否显示详细视图和列表视图的切换按钮
		cardView: false, //是否显示详细视图
		detailView: false, //是否显示父子表
		dataField: "list", //设置数据集属性
		totalField: "total", //设置数据集总数

		columns: [{
			checkbox: true
		}, {
			field: 'id',
			title: '编号',
			formatter: function (value, row, index) {
				//获取每页显示的数量
				var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
				//获取当前是第几页
				var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
				//返回序号，注意index是从0开始的，所以要加上1
				return pageSize * (pageNumber - 1) + index + 1;
			}
		}, {
			field: 'listNum',
			title: '单号'
		},{
			field: 'goodsId',
			title: '商品编号',
		},{
			field: 'goodsName',
			title: '商品名',
		},{
			field: 'price',
			title: '商品单价(/元)',
		},{
			field: 'count',
			title: '数量',
		},{
			field: 'money',
			title: '总价(/元)',
		},{
			field: 'payTime',
			title: '付款日期',
			width: "250px",
			formatter: function (value, row, index) {
				return  dateFormatter(value);
			}
		},  {
			field: 'returnStatus',
			title: '商品状态',
			formatter: function (value, row, index) {
				if (value == false){
					return  '<span class="label label-info">已退货</span>'
				}else {
					return  '<span class="label  label-success">已售出</span>'
				}
				return "";
			}
		}, {
			field: '',
			title: '操作',
			align: 'center',
			formatter: function (value, row, index) {
				var e = '<a class="btn btn-info btn-xs" href="#"  onclick="edit(\''
					+ row.id + '\')">编辑</a> ';
				var d = '<a class="btn btn-danger btn-xs" href="#"  onclick="del(\''
					+ row.id + '\',\'' + row.number + '\')">删除</a> ';
				return  e + d ;
			}
		}
		]
	})
}


function reloadTable() {
	$('#table').bootstrapTable('refresh')
}



/**
 * 编辑
 * @param id
 */
function edit(id) {
	//默认是新增
	var url = $.ctx + '/sale/iframe/edit';
	var title = "新增";
	if (id != null && id != undefined){
		//修改
		url = $.ctx + '/sale/iframe/edit/' + id;
		title = "修改";
	}
	layer.open({
		type: 2,
		title: '',
		maxmin: true,
		shadeClose: false, //点击遮罩关闭层
		area : ['800px' , '520px'],
		content: url
	});
}


/**
 * 删除
 * @param id
 */
function del(id) {
	layer.confirm('确认要删除吗？', {
		btn: ['删除', '取消'] //按钮
		, icon: 0
	}, function () {
		var params = {
			id:id
		};
		layer.msg('好，我去删除', {icon: 1});
		$.ajax({
			url: $.ctx + "/sale/delete",
			type:'post',
			data:params,
			dataType: "json",
			success:function (data) {
				if (data.code == 1){
					layer.msg('删除成功', {icon: 6});
					reloadTable();
					layer.closeAll('iframe');
				} else{
					layer.msg(data.message, {icon: 5});
				}
			},
			error: function (x, e, s) {
				layer.msg('服务器异常，请联系管理员', {icon: 5, time: 2000});
			}
		})
	});
}


//自定义参数向后台传参
function searchParam(params) {
	var params = {

		limit: params.limit, //每页多少条
		offset: params.offset, //从第几条开始读(默认就好)
		pageNum: params.offset == 0 ? 1 : params.offset / params.limit+1, //当前页(开始页)
		pageSize: params.limit,//每页的数量
		sort: params.sort,//根据什么排序
		order: params.order,//排序方式
		start: $("#startTime").val(),//自定义参数
		end:$("#endTime").val()
		// createTime: $("#a_rent_start").val(),//自定义
	};
	//获取每页显示的数量
	var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
	//获取当前页是第几页
	var pageNumber=$('#table').bootstrapTable('getOptions').pageNumber;
	return params;
}