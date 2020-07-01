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
			文章管理 <a href="javascript:void(0)" class="samll">费用说明</a>
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
				<h4>费用说明</h4>
			</div>
			<div class="panel-body">
				<form action="#" method="post" id="articleForm">
					<input type="hidden" id="no" name="no" value="0d266626-7fc8-4d4c-a077-ceb65aacd692" readonly>
					<input type="hidden" name="_method" value="PUT" readonly>
					<div class="form-group">
						<textarea id="context" name="context" style="width: 1000px; height:600px;">
								<!--费用说明start-->
									<p>
	<span style="font-size:16px;">为保障您的权益，请您在收到商品时与配送员当面核对，核对范围包括但不限于：商品种类、规格、数量（包括商品最小包装单位）、金额、赠品、外包装、票据等是否与订单一致，准确无误再进行签收。签收后，不再为以上问题负责。</span>
</p>
<p>
	<br />
</p>
<h3>
	的商品签收状态分为三种：
</h3>
<ol>
	<li>
		<span style="line-height:1.5;"><strong><span style="font-size:16px;">签收&nbsp;</span></strong><span style="font-size:16px;">商品核对无误后签字收货，食品类商品签收后非质量问题不退换</span></span>
	</li>
	<li>
		<span style="line-height:1.5;"><strong><span style="font-size:16px;">部分签收</span></strong><span style="font-size:16px;">&nbsp;购买商品在两种（含）以上，不完全签收，部分拒收时支持无理由退货</span></span>
	</li>
	<li>
		<span style="line-height:1.5;"><strong><span style="font-size:16px;">全部拒收</span></strong><span style="font-size:16px;">&nbsp;对购买的全部商品拒收</span></span>
	</li>
</ol>
<strong><span style="font-size:16px;">备注：</span></strong><br />
<ol>
	<li>
		<span style="line-height:1.5;font-size:16px;">商品送达时您可以拆开外包装验视，但不允许拆开内包装。</span>
	</li>
	<li>
		<span style="line-height:1.5;font-size:16px;">如果您的订单交由他人代收，代收人享有与您等同的权利，我们将视为您本人签收。</span>
	</li>
	<li>
		<span style="line-height:1.5;font-size:16px;">拒收业务完成后，已支付的款项我们将按原支付方式和路径安排退款。</span>
	</li>
</ol>
								<!--费用说明end-->




							</p>
						</textarea>
					</div>
					<div class="form-group">
						<a href="javascript:alert('暂未实现保存，请等待....')" class="btn btn-sm btn-danger">保存</a>
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