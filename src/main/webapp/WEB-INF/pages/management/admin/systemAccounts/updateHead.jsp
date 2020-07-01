<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2020/1/9
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>修改头像</title>
<css>
    <link rel="stylesheet" href="${ctx}/static/assets/lib/css/webuploader.css" />
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
                    首页 <a href="#" class="samll">用户中心-修改头像</a></h4>
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
                                <h4><i class="ace-icon glyphicon glyphicon-camera"></i>上传/更换头像</h4>
                            </div>
                            <div class="panel-body">
                                <div class="alert alert-info">
					<span> <i class="fa fa-info-circle blue"></i> 提示：请上传
						jpg/png 格式图片，大小不要超过800*800
					</span>
                                </div>
                    <form id="systemAccount" class=" form-horizontal" action="${ctx}/account/fileUpload" enctype="multipart/form-data" method="post">
                                 <input type="hidden" name="id" value="${account1.id}"/>
                                <input type="file" name="uploadFile"/>
                                <button type="submit" class="btn btn-primary btn-sm">保存</button>
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
<!-- Main Container Start -->


</body>
<js>
    <script type="text/javascript" src="${ctx}/static/assets/lib/js/md5.js"></script>
    <script type="text/javascript" src="${ctx}/static/assets/lib/js/webuploader.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/assets/app/js/management/springstage.fileupload.plugin.js"></script>
    <script type="text/javascript" src="${ctx}/static/assets/app/js/management/systemAccounts.js"></script>
</js>

