jQuery(function () {

	//初始化表格
	loadTable();


	/**
	 * 重置密码
	 */
	$(".btn-resetPassword").on("click", function () {
		var url = $(this).attr("data-url");
		$.get(url, function (data) {
			flash(data.level, data.message);
		});
	});


	/**
	 * 上传头像
	 */
	if (springstage && springstage.fileupload) {
		springstage.fileupload({
			pick: ".picker",
			fileNumLimit: 1,
			queueSize: 1
		});
	}




	$("#search").click(function () {
		$('#table').bootstrapTable('refresh');
	});

});

function loadTable() {
	/**
	 * 账号列表
	 */
	$('#table').bootstrapTable({
		url: $.ctx + '/account/pages',
		toolbar : '#toolbar', //工具按钮用哪个容器
		striped : true, //是否显示行间隔色
		cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, //是否显示分页（*）
		sortable : true, //是否启用排序
		// sortOrder : "asc", //排序方式
		queryParams : searchParam,//传递参数（*）
		sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, //初始化加载第一页，默认第一页
		pageSize : 5, //每页的记录行数（*）
		pageList : [ 5, 10, 20], //可供选择的每页的行数（*）
		search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		strictSearch : false,
		showColumns : false, //是否显示所有的列
		showRefresh : true, //是否显示刷新按钮
		minimumCountColumns : 2, //最少允许的列数
		clickToSelect : false, //是否启用点击选中行
		/* height : 480, *///行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "id", //每一行的唯一标识，一般为主键列
		showToggle : true, //是否显示详细视图和列表视图的切换按钮
		cardView : false, //是否显示详细视图
		detailView : false, //是否显示父子表
		dataField: "list", //设置数据集属性
		totalField: "total", //设置数据集总数
		onDblClickRow:function(row){
			info(row.id)
		},
		columns: [{
		    checkbox: true
        },{
			field: 'id',
			title: '编号',
			formatter: function (value, row, index) {
				//获取每页显示的数量
				var pageSize=$('#table').bootstrapTable('getOptions').pageSize;
				//获取当前是第几页
				var pageNumber=$('#table').bootstrapTable('getOptions').pageNumber;
				//返回序号，注意index是从0开始的，所以要加上1
				return pageSize * (pageNumber - 1) + index + 1;
			}
		}, {
			field: 'account',
			title: '账号',
			sortable: true
		}, {
			field: 'realName',
			title: '真实姓名'
		}, {
			field: 'locked',
			title: '是否锁定',
			formatter: function (value, row, index) {
				if (value == false){
					return  '<a class="btn btn-success btn-xs" href="#"  onclick="lock(\''
						+ row.id + '\',\''+row.realName+'\',\'' + row.locked + '\')">未锁定<i class="ace-icon fa fa-lock"></i> </a> '
				}
				return  '<a class="btn btn-danger btn-xs" href="#"  onclick="lock(\''
					+ row.id + '\',\''+row.realName  + '\')">已锁定<i class="ace-icon fa fa-lock"></i> </a> '
			}
		}, {
			field: 'roles',
			title: '角色',
			formatter: function (value, row, index) {
				var html ="";
				for (var i = 0; i < value.length; i++){
					html += '<span class="label label-info">' + value[i].name + '</span> ';
				}
				return html;
			}
		},{
			field: 'createAt',
			title: '创建时间',
			align: "center",
			formatter: function (value, row, index) {
				return  dateFormatter(value);
			}
		},{
			field: 'lastLoginAt',
			title: '上次登录时间',
			align: "center",
			formatter: function (value, row, index) {
				return  value == null ? '<span class="label label-danger">未登录</span>' : dateFormatter(value);
			}
		},{
			field : '',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var c = '<a class="btn btn-default btn-xs" href="#"  onclick="info(\''
					+ row.id
					+ '\')">查看</a> ';
				var e = '<a class="btn btn-info btn-xs" href="#"  onclick="edit(\''
                + row.id
					+ '\')">编辑</a> ';
				var d = '<a class="btn btn-danger btn-xs" href="#"  onclick="del(\''
					+ row.id + '\',\'' + row.account+ '\')">删除</a> ';
				var f = '<a class="btn btn-warning btn-xs" href="#"  onclick="role(\''
					+ row.id + '\')">授权</a> ';
				return c + e + d + f;
			}
		}
		]
	})
}

function reloadTable() {
	$('#table').bootstrapTable('refresh')
}

/**
 *
 * @param id
 */
function role(id) {
	layer.open({
		type: 2,
		title: '授权管理',
		maxmin: true,
		shadeClose: false, //点击遮罩关闭层
		area: ['1000px', '620px'],
		content: $.ctx + '/account/iframe/edit/role/' + id
	});

}
/**
 * 锁定
 * @param id
 */
function lock(id,realName,locked) {
	if(locked == 'false'){
		var a = '确认锁定';
		var b = '锁定成功';
	}else {
		var a = '确认要解锁';
		var b = '解锁成功';
	}
	layer.confirm(a + realName + '吗？', {
		btn: ['确认', '取消'] //按钮
		, icon: 0
	}, function () {
		var params = {
			id:id
		};
		$.ajax({
			url: $.ctx + "/account/lock",
			type:'post',
			data:params,
			dataType: "json",
			success:function (data) {
				if (data.code == 1){
					layer.msg(b, {icon: 6});
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

/**
 * 查看
 * @param id
 */
function info(id) {
	layer.open({
		type: 2,
		title: '',
		maxmin: false,
		shadeClose: true, //点击遮罩关闭层
		area : ['500px' , '520px'],
		content: $.ctx + '/account/iframe/info/' + id
	});
}

/**
 * 编辑
 * @param id
 */
function edit(id) {
    layer.open({
        type: 2,
        title: '',
        maxmin: true,
        shadeClose: false, //点击遮罩关闭层
        area : ['800px' , '520px'],
        content: $.ctx + '/account/iframe/edit/' + id
    });
}

/**
 * 删除
 * @param id
 */
function del(id, account) {
	layer.confirm('确认要删除' + account + '吗？', {
		btn: ['删除', '取消'] //按钮
		, icon: 0
	}, function () {
		var params = {
			id:id
		};
		layer.msg('好，我去删除', {icon: 1});
		$.ajax({
			url: $.ctx + "/account/delete",
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

/**
 * 批量删除
 */
function batchDelete() {
	//判断至少选择了一项
	var checkedNum = $('#table').bootstrapTable('getSelections');
	if (checkedNum.length == 0) {
		layer.msg('至少选择一项删除', {icon: 6});
		return;
	}
	layer.confirm('确认要删除选中的用户吗？', {
		btn: ['删除', '取消'] //按钮
		, icon: 0
	}, function () {
		var accountList = new Array();
		for(var i = 0 ; i< checkedNum.length;i++){
			accountList.push(checkedNum[i].id)
		}
		$.ajax({
			url :$.ctx + "/account/batchDelete",
			type : "post",
			data : {accountList : accountList.toString()},
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
			error : function(){
				layer.msg('服务器异常，请联系管理员', {icon: 5, time: 2000});
			}
		});
	});
}

/**
 * 修改密码
 * @param id
 */
function changepwd() {
	layer.open({
		type: 2,
		title: '',
		maxmin: true,
		shadeClose: false, //点击遮罩关闭层
		area : ['800px' , '520px'],
		content: $.ctx + '/account/iframe/password'
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
		search: $("#searchInput").val(),//自定义参数
		// createTime: $("#a_rent_start").val(),//自定义
	};
	//获取每页显示的数量
	var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
	//获取当前页是第几页
	var pageNumber=$('#table').bootstrapTable('getOptions').pageNumber;
	return params;
}