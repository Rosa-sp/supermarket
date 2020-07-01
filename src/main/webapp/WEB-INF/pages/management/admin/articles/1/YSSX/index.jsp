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


    <!-- Right sidebar start -->
    <!-- Right sidebar end -->
    <!-- Main Container Start -->
    <div class="main-container">
        <!-- Top Bar Starts -->
        <div class="top-bar clearfix">

            <div class="page-title">
                <h4>
                    <div class="fs1" aria-hidden="true" data-icon="&#xe0ac;"></div>
                    文章管理 <a href="javascript:void(0)" class="samll">运输时效</a>
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
                                <h4>运输时效</h4>
                            </div>
                            <div class="panel-body">
                                <form action="#" method="post" id="articleForm">
                                    <input type="hidden" id="no" name="no" value="0d266626-7fc8-4d4c-a077-ceb65aacd692" readonly>
                                    <input type="hidden" name="_method" value="PUT" readonly>
                                    <div class="form-group">
						<textarea id="context" name="context" style="width: 1000px; height:600px;">
							<p class="p" align="center" style="text-align:left;">
								<!--start-->
								<h3>
	<span style="font-size:16px;">一、提交退换货申请</span>
</h3>
<p>
	<span style="font-size:16px;">购物后如果发现商品存在质量问题，请您联系的客服中心：400-6286-510，会尽快安排人员进行处理。</span>
</p>
<p>
	<br />
</p>
<p>
	<br />
</p>
<h3>
	<span style="font-size:16px;">二、退款方式</span>
</h3>
<ol>
	<li>
		<span style="line-height:1.5;font-size:16px;">在线支付的用户，结算后货款将直接退回您支付时使用的银行卡（即“原路返回”）。如果您在退货申请确认后72小时之后未选择退款方式，将默认把退款金额返回至您在的虚拟账户内。</span>
	</li>
	<li>
		<span style="line-height:1.5;font-size:16px;">货到付款并且商品部分拒收时，只接受现金当场结算。</span>
	</li>
	<li>
		<span style="line-height:1.5;"></span><span style="line-height:1.5;font-size:16px;">货到付款时选择POS机刷卡的用户，如果您签收后因商品质量问题需要部分退货，需待确认收到所退商品及发票后才能线上申请退款。</span>
	</li>
	<li>
		<span style="line-height:1.5;font-size:16px;">在您申请退款时，可选择三种退款路径：</span><span style="line-height:1.5;"></span>
	</li>
	<ul>
		<li>
			<span style="line-height:1.5;"></span><span style="line-height:1.5;font-size:16px;">退回您在的虚拟账户；</span>
		</li>
		<li>
			<span style="line-height:1.5;font-size:16px;">退回至您的积分账户；</span>
		</li>
		<li>
			<span style="line-height:1.5;font-size:16px;">退回至您指定的银行卡账户内。</span>
		</li>
	</ul>
</ol>
<p>
	<br />
</p>
<p>
	<br />
</p>
<h3>
	<span style="font-size:16px;">三、退货流程</span>
</h3>
<p>
	<span style="font-size:16px;">为了保证退货订单能被及时处理，请遵循以下流程，否则可能无法办理退换货：</span>
</p>
<p>
	<br />
</p>
<p>
	<span style="font-size:16px;">申请退货以签收包裹时间为准，收到商品1日内，您可通过邮件或电话形式联系我们申请退换货，客服会在1个工作日内回复您。</span>
</p>
<p>
	<br />
</p>
<p>
	<span style="font-size:16px;">审核确认顾客需等待客服人员确认此笔退换货申请（24小时之内，包括节假日），如此申请被接受，货品可按要求退货。客服人员会进一步联系顾客进行退换货处理。</span>
</p>
<p>
	<br />
</p>
<p>
	<span style="font-size:16px;">当收到退回的物品，经过质检确认无误后，客服人员会在2个工作日内联系您，帮您办理退款或退积分事宜。</span>
</p>
<p>
	<br />
</p>
<h3>
	<span style="font-size:16px;">四、退货说明</span>
</h3>
<p>
	<span style="font-size:16px;">我们确保商品数量正确，当您收到快递时，请当场清点货品数量。</span>
</p>
<p>
	<br />
</p>
<p>
	<span style="font-size:16px;">我们确保商品外包装完整，如您收到货品时，发现商品外包装表面破损，导致商品损坏的情况，请您务必当场拒签，拒收后请联系我司客服，公司将在收到货品后，经协商，我们会进行退款或退积分给顾客。</span>
</p>
<p>
	<br />
</p>
<p>
	<span style="font-size:16px;">如顾客已签收或者他人代为签收货品，则视为商品外包装及商品数量无误，我司无法受理。</span>
</p>
<p>
	<br />
</p>
<p>
	<span style="font-size:16px;">只接受通过客服电话或者邮件形式申请的退换货。</span>
</p>
<p>
	<br />
</p>
<span style="font-size:16px;">请注意，预订商品下单后，不可退换货。</span>
                            <!--end-->
                            </p>
						</textarea>
                                    </div>
                                    <div class="form-group">
                                        <a href="javascript:alert('暂未实现保存,请等待')" class="btn btn-sm btn-danger">保存</a>
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
