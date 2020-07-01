<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2020/1/7
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>供应单位管理</title>


<body>
    <!-- Right sidebar start -->
    <!-- Right sidebar end -->
    <!-- Main Container Start -->
    <div class="main-container">
        <!-- Top Bar Starts -->
        <div class="top-bar clearfix">

            <div class="page-title">
                <h4>
                    <div class="fs1" aria-hidden="true" data-icon="&#xe0ac;"></div>
                    供应单位管理 <a href="javascript:void(0)" class="samll">供应单位管理</a>
                </h4>
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
                                <h4>供应单位列表</h4>
                                <ul class="links">
                                    <li>
                                        <a href="#" title="新建订单" onclick="edit()">
                                            <i class="fa fa-plus"></i> 新建供应单位
                                        </a>
                                    </li>
                                </ul>
                            </div>
                           <div class="panel-body">
                            <div class="row">
                                <div class="col-xs-2">
                                    <input id="searchInput" name="search.order_number" class="form-control" placeholder="搜索供应单位" type="text" value="" />
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
            <!-- Spacer ends -->
        </div>
        <!-- Container fluid ends -->
    </div>
    <!-- Main Container Start -->
    </div>

</body>
<js>
    <script type="text/javascript" src="${ctx}/static/assets/app/js/management/supplier.js"></script>
</js>