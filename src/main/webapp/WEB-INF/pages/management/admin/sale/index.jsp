<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2020/1/7
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>销售管理</title>
<css>
    <link rel="stylesheet" href="${ctx}/static/assets/lib/layui/css/layui.css">
</css>

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
                    销售管理 <a href="javascript:void(0)" class="samll">销售管理</a>
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
                                <h4>销售列表</h4>
                                <ul class="links">
                                    <li>
                                        <a href="#" title="新建订单" onclick="edit()">
                                            <i class="fa fa-plus"></i> 新建销售
                                        </a>
                                    </li>
                                </ul>
                            </div>
                           <div class="panel-body">
                               <div class="row">
                                   <div class="col-xs-2">
                                       <input id="startTime" name="search.createAt_gte" class="form-control input-date" placeholder="开始时间" type="text" value="2020-01-01" maxlength="10"/>
                                   </div>
                                   <div class="col-xs-2">
                                       <input id="endTime" name="search.createAt_lte" class="form-control input-date" placeholder="截止时间" type="text" value="2020-12-12" maxlength="10"/>
                                   </div>
                                   <div class="col-xs-3">
                                       <button type="submit" class="btn btn-sm btn-success" id="search">
                                           查询
                                           <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
                                       </button>
                                   </div>
                               </div>
                           </br>
                               <table class="table table-bordered table-hover">
                                   <tr>
                                       <th>销售总金额（单位：元）</th>
                                       <th>退货金额（单位：元）</th>
                                       <th>合计销售总金额（单位：元）</th>
                                   </tr>
                                   <tr>
                                       <td>
							<span class="label label-success">
								<a id="pn_sale_see" data-money="250.71999999999997" data-url="" style="color:#ffffff; cursor: pointer;">
                               		786.35
                            	</a>
							</span>
                                       </td>
                                       <td>
							<span class="label label-danger">
								<a id="pz_sale_see" data-money="0.0" data-url="" style="color:#ffffff; cursor: pointer;">
									0.00
								</a>
							</span>
                                       </td>
                                       <td>
							<span class="label label-primary" style="color:#ffffff; ">
								786.35
							</span>
                                       </td>
                                   </tr>
                               </table>

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
    <script type="text/javascript" src="${ctx}/static/assets/app/js/management/sale.js"></script>
</js>