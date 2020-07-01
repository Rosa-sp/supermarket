<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2020/1/7
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>文章管理</title>
<css>
    <link rel="stylesheet" href="${ctx}/static/assets/lib/kindeditor/themes/default/default.css" />
    <link rel="stylesheet" href="${ctx}/static/assets/app/css/management/articles.css" />
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
                    文章管理 <a href="javascript:void(0)" class="samll">正品保证</a>
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
                        <div class="panel">
                            <div class="panel-heading">
                                <h4>正品保证</h4>
                            </div>
                            <div class="panel-body">
                                <form action="#" method="post" id="articleForm">
                                    <input type="hidden" id="no" name="no" value="0d266626-7fc8-4d4c-a077-ceb65aacd692" readonly>
                                    <input type="hidden" name="_method" value="PUT" readonly>
                                    <div class="form-group">
						<textarea id="context" name="context" style="width: 1000px; height:600px;">
							<p class="p" align="center" style="text-align:left;">
								品农正品保证
							</p>
						</textarea>
                                    </div>
                                    <div class="form-group">
                                        <a href="javascript:alert('暂未实现保存,请等待....')" class="btn btn-sm btn-danger">保存</a>
                                    </div>
                                </form>
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
    <script type="text/javascript" src="${ctx}/static/assets/lib/kindeditor/kindeditor-all-min.js"></script>
    <script type="text/javascript" src="${ctx}/static/assets/lib/kindeditor/lang/zh-CN.js"></script>
    <script type="text/javascript" src="${ctx}/static/assets/app/js/management/articles.js"></script>
</js>