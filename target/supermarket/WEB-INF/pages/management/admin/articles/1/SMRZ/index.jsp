<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2020/1/7
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <title>文章管理</title>
<css>
    <link rel="stylesheet" href="${ctx}/static/assets/lib/kindeditor/themes/default/default.css" />
    <link rel="stylesheet" href="${ctx}/static/assets/app/css/management/articles.css" />
</css>
<body>



    <!-- Header ends -->
    <!-- Right sidebar start -->
    <!-- Right sidebar end -->
    <!-- Main Container Start -->
    <div class="main-container">
        <!-- Top Bar Starts -->
        <div class="top-bar clearfix">

            <div class="page-title">
                <h4>
                    <div class="fs1" aria-hidden="true" data-icon="&#xe0ac;"></div>
                    文章管理 <a href="javascript:void(0)" class="samll">实名认证</a>
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
                                <h4>实名认证</h4>
                            </div>
                            <div class="panel-body">
                                <form action="#" method="post" id="articleForm">
                                    <input type="hidden" id="no" name="no" value="0d266626-7fc8-4d4c-a077-ceb65aacd692" readonly>
                                    <input type="hidden" name="_method" value="PUT" readonly>
                                    <div class="form-group">
						<textarea id="context" name="context" style="width: 1000px; height:600px;">

							<!--start-->
								<h3>
	<span style="font-size:16px;">如何实名认证：</span>
</h3>
<ol>
	<li>
		<span style="line-height:1.5;font-size:16px;">点击首页实名认证按钮</span>
	</li>
	<li>
		<span style="line-height:1.5;font-size:16px;">在弹出框内填写相关信息，点击提交即可。</span>
	</li>
	<li>
		<span style="line-height:1.5;font-size:16px;">的会员等级是根据您的经验值多少，获得相应会员身份。随着您经验值的增加，会员等级会自动提升；</span>
	</li>
</ol>
<p>
	<br />
</p>
<h3>
	<span style="font-size:16px;">会员福利：</span>
</h3>
<ol>
	<li>
		<span style="line-height:1.5;font-size:16px;">会员价：会员享受全场商品会员价购买，不同等级会员，享受不同等级折扣，非会员无折扣优惠。</span>
	</li>
	<li>
		<span style="line-height:1.5;font-size:16px;">会员积分：会员购买商品，按照1元=1积分获得积分，非会员无积分获得。</span>
	</li>
	<li>
		<span style="line-height:1.5;font-size:16px;">优惠券：会员可使用发放的不同代金券，非会员不可使用部分代金券。</span>
	</li>
	<li>
		<span style="line-height:1.5;font-size:16px;">不定期会员活动：会员部分限量新品，合作商品优先品尝。</span>
	</li>
	<li>
		<span style="line-height:1.5;font-size:16px;">更多福利:陆续开放中,敬请期待…</span>
	</li>
</ol>
                            <!--end-->
						</textarea>
                                    </div>
                                    <div class="form-group">
                                        <a href="javascript:alert('暂未实现保存，请等待...')" class="btn btn-sm btn-danger">保存</a>
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
    <!-- Main Container Start -->

</body>
<js>
    <script type="text/javascript" src="${ctx}/static/assets/lib/kindeditor/kindeditor-all-min.js"></script>
    <script type="text/javascript" src="${ctx}/static/assets/lib/kindeditor/lang/zh-CN.js"></script>
    <script type="text/javascript" src="${ctx}/static/assets/app/js/management/articles.js"></script>
</js>