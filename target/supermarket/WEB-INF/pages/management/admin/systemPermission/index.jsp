<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<title>系统权限管理</title>
<css>
    <link rel="stylesheet" href="${ctx}/static/assets/lib/js/jquery-treegrid/css/jquery.treegrid.css"/>
</css>
<body>
<!-- Main Container Start -->
<div class="main-container">
	<!-- Top Bar Starts -->
	<div class="top-bar clearfix">

		<div class="page-title">
			<h4>
				<div class="fs1" aria-hidden="true" data-icon="&#xe0ac;"></div>
				系统管理 <a href="javascript:void(0)" class="samll">权限管理</a></h4>
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
							<h4>权限列表</h4>
							<ul class="links">
								<li>
									<a href="javascript:void(0)" onclick="power()" title="新建权限">
										<i class="fa fa-plus"></i> 新建权限
									</a>
								</li>
							</ul>
						</div>
						<div class="panel-body">
							<div class="row">

								<div class="col-xs-3">
									<input id="searchInput" name="search.account_like,realName_like" class="form-control" placeholder="名称" type="text" value=""/>
								</div>
								<div class="col-xs-3">
									<button type="button" class="btn btn-sm btn-success" id="search">
										查询
										<span class="ace-icon fa fa-search icon-on-right bigger-110"></span></button>
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
<!-- Main Container Start -->
</body>
<js>
    <script type="text/javascript" src="${ctx}/static/assets/lib/js/bootstrap-table-treegrid.js"></script>
    <script type="text/javascript" src="${ctx}/static/assets/lib/js/jquery-treegrid/js/jquery.treegrid.js"></script>
	<script type="text/javascript" src="${ctx}/static/assets/app/js/management/systemPermission.js"></script>
</js>