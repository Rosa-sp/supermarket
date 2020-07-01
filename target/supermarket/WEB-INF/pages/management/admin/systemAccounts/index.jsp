<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>系统账号管理</title>
<body>


<div class="main-container">
	<!-- Top Bar Starts -->
	<div class="top-bar clearfix">

		<div class="page-title">
			<h4>
				<div class="fs1" aria-hidden="true" data-icon="&#xe0ac;"></div>
				系统管理 <a href="javascript:void(0)" class="samll">账号管理</a></h4>
		</div>

	</div>
	<!-- Top Bar Ends -->
	<!-- Container fluid Starts -->
	<div class="container-fluid">
		<!-- Spacer starts -->
		<div class="spacer-xs">

			<div class="row no-gutter">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>用户列表</h4>
							<ul class="links">
								<li>
									<a href="#" title="新建账号" onclick="edit()">
										<i class="fa fa-plus"></i> 新建账号
									</a>
								</li>
							</ul>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-xs-3">
									<input id="searchInput" name="search.account_like,realName_like" class="form-control" placeholder="账号, 真实姓名" type="text" value=""/>
								</div>
								<div class="col-xs-3">
									<button type="button" class="btn btn-sm btn-success" id="search">
										查询
										<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
									</button>
									<button type="button" class="btn btn-sm btn-danger" onclick="batchDelete()" id="delete">
										删除
										<span class="icon-on-right bigger-110"></span>
									</button>
								</div>
							</div>
							<br />
							<table id="table"></table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Spacer ends -->
	</div>
	<!-- Container fluid ends -->
</div>

</body>
<js>
	<script type="text/javascript" src="${ctx}/static/assets/app/js/management/systemAccounts.js"></script>
</js>