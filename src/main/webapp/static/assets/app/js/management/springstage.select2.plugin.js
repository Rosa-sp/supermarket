
/**
 * 使用要点：
 * 1. 引入css与js
 * <link rel="stylesheet" href=" ${ctx}/assets/lib/css/select2.min.css" />
 * <script type="text/javascript" src="${ctx}/assets/lib/js/select2.min.js"></script> 
 * <script type="text/javascript" src="${ctx}/assets/lib/js/select2-zh-CN.js"></script>
 * <script type="text/javascript" src="${ctx}/assets/app/js/management/springstage.select2.plugin.js"></script> 
 * 
 * 2. HTML页面中 为一个seelct标签添加 select2-remote css样式
 * 
 * <form:select  id="parentId" fieldLabel="上级机构"  cssClass="select2-remote" path="parentId"
	cssStyle="width: 300px;" data-ajax--url="${ctx}/management/organizations/dropDownSearch">
		<option value="0">请选择</option>
		
		<c:if test="${not empty organization.parentId }">
			<option value="${organization.parentId}" selected="selected">${ancestorFullName}</option>
		</c:if>	
	</form:select>
 * 
 * data-ajrax--url 是 select2 查询提交的地址
 * 
 * 3. 调用springstage.select2 方法传入参数
 * formatRepo 与 formatRepoSelection 的写法参考 organizations/_form.jsp中的写法
 */


var springstage=springstage || {};

/**
 * 参数：
 * 	formatRepo(repo)
 *  function formatRepoSelection(repo)
 *  queryName: 查询参数名称
 *  
 *  下拉框类选择器固定为 select2-remote
 */
springstage.select2=function(formatRepo,formatRepoSelection,queryName){
	$(".select2-remote").select2({
		ajax: {
		      dataType: 'json',
		      delay: 250,
		      data: function (params) {
		    	var d={};
		    	d[queryName]=params.term;
		    	d.page=params.page;
		        return d;
		      },
		      processResults: function (data, params) {
		    	console.dir(data);
		    	console.dir(params);
		        params.page = params.page || 0;
		        return {
		          results: data.items,
		          pagination: {
		            more: ((params.page+1) * data.size) < data.total
		          }
		        };
		      },
		      cache: true
		  },
		  escapeMarkup: function (markup) { return markup; },
		//  minimumInputLength: 2,
		  maximumInputLength:30,
		  templateResult: formatRepo,
		  templateSelection: formatRepoSelection,
		  allowClear: true,
		  
		  placeholder:{
				id:0,
				text:'请选择'
			},
		  language:"zh-CN"
	});
}



