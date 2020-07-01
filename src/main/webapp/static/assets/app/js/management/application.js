
window.springstage=null; //插件命名空间所有应用的插件都归属到这个命名空间下
/**
 * 消息提醒参数设置
 */
toastr.options = {
	closeButton: true,
	progressBar: true,
	showMethod: 'slideDown',
	timeOut: 5000
};


/**
 消息提醒组件
 */
window.flash=function(level,content){
	level=level||"info";
	var title="";
	if(content){
		if(level=="success"){title='成功消息提示';}
		if(level=="info"){title='消息提示';}
		if(level=="warning"){title='警告消息提示';}
		if(level=="error"){title='错误消息提示';}
		toastr[level](content, title);
	}
}
/**
 * 设置moment插件的语言环境
 */
moment.locale('zh-cn');

/**
 * 设置全局日历选择控件
 */
jQuery.extend(jQuery.fn.datetimepicker.defaults,{
	showTodayButton: true,
	showClear:true,
	showClose:true,
	tooltips: {
		today: '今天',
		clear: '清除所选',
		close: '关闭',
		selectMonth: '选择月份',
		prevMonth: '上月',
		nextMonth: '下月',
		selectYear: '选择年',
		prevYear: '去年',
		nextYear: '明年',
		selectDecade: '选择10年',
		prevDecade: '前10年',
		nextDecade: '后10年',
		prevCentury: '上世纪',
		nextCentury: '下世纪'
	}
});

// 日期选择控件 http://eonasdan.github.io/bootstrap-datetimepicker/
jQuery(document).on('focus', '.input-date',function(e){
	jQuery(this).datetimepicker({
		viewMode:'days',
		format:'L'
	});
});

// 时间选择控件
jQuery(document).on('focus', '.input-datetime', function(e){
	jQuery(this).datetimepicker({
		sideBySide:true,
		format: 'YYYY-MM-DD HH:mm',
		showClose:true
	});
});


/**
 * 数字输入插件
 * 使用方法:
 * 1. 如果只允许输入整数，对应的HTML代码为：
 *    <input type="text" class="input-integer" />
 * 2. 如果允许输入小数,对应的HTML代码为,默认2位小数
 *    <input type="text" class="input-numberic" />
 * 如果需要指定小数位,对应的HTML代码为：
 *    <input type="text" class="input-numberic" data-decimals="4" />
 */
jQuery.fn.numberic=function(defaults){
	defaults=jQuery.extend({numberic:false,decimals:2},defaults); //默认只能输入整数，如果是小数，默认为2位小数
	jQuery(this).css("ime-mode", "disabled");
	jQuery(this).on("keydown",function(e){

		//判断是否被鼠标选中
		if(this.selectionStart==0 && this.selectionEnd==this.value.length){
			this.value="";
		}

		var validateNumberic=function(value){ //验证小数点,当输入的是 . 字符的时候进行验证
			if(value=="" || value.indexOf(".")!=-1){//小数点不能开头,小数点已经存在不能通过
				return false;
			}
			return true
		};

		var value=jQuery(this).val();

		var keycode = e.keyCode;// 48-57 96-105  8，46 删除         9 tab   37 39   . 190 110
		if(keycode==8 || keycode==46){ //如果是删除键，直接返回 [backspace-8, delete-46]
			return true;
		}
		if(keycode==189 || keycode==109){ //负号必须是第一个字符
			if(value=="" || value.charAt(0)!="-"){
				return true;
			}
		}
		var otherCharacter=[8,9,46,37,39];

		if(defaults.numberic){
			otherCharacter.push(190); //小数点
			otherCharacter.push(110);
		}
		if(jQuery.inArray(keycode,[8,9,46,37,39])!=-1){
			return true;
		}
		if((keycode>=48 && keycode<=57) || (keycode>=96 && keycode<=105) || jQuery.inArray(keycode,otherCharacter)!=-1){


			if((keycode==190 || keycode==110) && !validateNumberic(value)){//如果输入的是小数点

				return false;
			}
			if(value.indexOf(".")!=-1 && value.substr(value.indexOf(".")).length>=(defaults.decimals+1)){ //小数不能超过2位
				if(this.selectionStart>=value.indexOf(".")+1){
					return false;
				}

			}
			if(value=='0' && jQuery.inArray(keycode,otherCharacter)==-1){ //如果是0开头
				return false;
			}

			return true;
		}
		return false;
	});
	jQuery(this).on("focus",function(){
		//$(this).select();
	});
}
jQuery(document).on("focus",'.input-integer',function(e){
	jQuery(this).numberic();
});
jQuery(document).on("focus",'.input-numberic',function(e){

	if(jQuery(this).data("decimals")){
		try{
			jQuery(this).numberic({numberic:true,decimals:parseInt(jQuery(this).data("decimals"))});
			return;
		}catch(e){

		}
	}
	jQuery(this).numberic({numberic:true});
});


////////////////////////////扩展数组的包含方法, 会同时比较元素的数据类型与值 //////////////////////////////////////////////////
Array.prototype.contains = function(obj) {
	var i = this.length;
	while (i--) {
		if (this[i] === obj) {
			return true;
		}
	}
	return false;
}
////////////////////////////ajax请求session失效，异常处理//////////////////////////////////////////////////
jQuery.ajaxSetup({
	contentType:"application/x-www-form-urlencoded;charset=utf-8",
	complete:function(XMLHttpRequest,textStatus){

		var response=XMLHttpRequest.responseJSON;
		if(response && response.success==false){
			window.flash(response.level,response.message);
		}
		var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");
		if(sessionstatus=="timeout"){
			//如果超时就处理 ，指定要跳转的页面
			window.location = jQuery.ctx+'/management/signin';
		}
	}
});


//处理活动的菜单
if($("#menu").length){

	var $a=$("#menu li a[href='"+$.servletPath+"']");
	if($a && $a.length){
		var $parentMenu=$a.parent().parents("li.has-sub");
		if($parentMenu.length){
			$a.addClass("select");
			$parentMenu.addClass("highlight active");
			$a.parent().parent().show();
		}else{
			$a.parent().addClass("highlight");
		}

	}else{
		//取出所有a标签
		$a=$(".nav-list li a");
		$a.each(function(i,item){
			var href=$(item).attr("href");
			if($.servletPath.indexOf(href)>=0){
				$(item).parent().addClass("active");
				$(item).parent().parents("li").addClass("open active");
				return false;
			}
		});
	}
}
$.fn.modal.Constructor.prototype.enforceFocus = function() {};



window.springstage=window.springstage ||{};

//编辑器工具条
window.springstage.kindEditorItems = [
	'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
	'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
	'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
	'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
	'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
	'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
	'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
	'anchor', 'link', 'unlink', '|', 'about'
];

/**
 * 用于index页面的　查询按钮和分页的查询按钮
 * @param event
 */
window.springstage.query=function(event){

	event.preventDefault();
	event.stopPropagation();
	var curTarget=$(event.currentTarget);
	var ajaxConfig=null;
	if(curTarget.is("form")){
		//禁用查询按钮
		curTarget.children(".btn-search").prop("disabled",true);
		ajaxConfig={
			type:curTarget.prop("method")?curTarget.prop("method"):"GET",
			url:curTarget.prop("action"),
			data: curTarget.serialize(),
			dataType: "html"
		};
	}
	if(curTarget.is("a")){
		ajaxConfig={
			type:"GET",
			url:curTarget.prop("href"),
			dataType: "html"
		};
	}

	if(ajaxConfig){
		//提交ajax请求
		$.ajax(ajaxConfig).done(function( data, textStatus ) {

			$(".data-table").html(data);

			if(curTarget.is("form")){
				curTarget.children(".btn-search").prop("disabled",false);
			}
		}).fail(function( jqxhr, settings, exception ) {
			flash("error",exception);
			if(curTarget.is("form")){
				curTarget.children(".btn-search").prop("disabled",false);
			}
		});
	}
	return false;
};

/**
 * 重新加载表格
 * @param datas　加载表格数据时ajax提交的参数
 * @param reset　是否重置表单
 */
window.springstage.reloadTable=function(datas, reset){
	var $searchableForm=$("#searchForm");
	if(reset){
		$searchableForm.get(0).reset();
	}
	var inputHtml = "";
	if(datas){
		for(var m in datas){
			$searchableForm.children("input[name='"+m+"']").remove();
			inputHtml += "<input type='hidden' name='"+m+"' value='"+datas[m]+"'/>";
		}
	}
	$searchableForm.append($(inputHtml));

	$searchableForm.trigger("submit");

	for(var m in datas) {
		$searchableForm.children("input[name='"+m+"']").remove();
	}
};

//表单搜索提交 搜索表单的ID必须是#searchform
$(document).on("submit","#searchForm[data-remote='true']",window.springstage.query);
//分页提交
$(document).on("click",".pagination a[data-remote='true']",window.springstage.query);
//弹出层通过ajax获取数据
$(document).on('click', "[data-modal-ajax='true']", function(e){
	e.preventDefault();
	e.stopPropagation();
	if(!$(this).data("url")){
		return false;
	}
	$.get($(this).attr("data-url"))
		.done(function( data, textStatus ) {
			$("#modal-ajax .modal-content").html(data);
			$("#modal-ajax").modal();
		})
		.fail(function( jqxhr, settings, exception ) {
			//console.log( "ajax异常:"+exception );
			flash("error", exception);
		});
});
//弹出层表单ajax提交
$(document).on("submit","#modal-ajax form[data-remote='true']",function(event){
	event.preventDefault();
	event.stopPropagation();
	var $form=$(this);
	$.ajax({
		type:$form.prop("method")?$form.prop("method"):"GET",
		url:$form.prop("action"),
		data: $form.serialize()
	}).done(function( data, textStatus ,jqxhr) {
		var responseHeader=jqxhr.getResponseHeader("Content-Type");
		if(responseHeader.indexOf("text/html")!=-1){ //如果返回的内容是HTML则直接填充到窗口中
			$("#modal-ajax .modal-content").html(data);
		}
		if(data && data.success==false){
			return;
		}
		if(responseHeader.indexOf("application/json")!=-1){
			//如果是修改操作
			if($form.children("input[name='_method'][value='put']").length==1){
				flash("success",data.message);
				$("#modal-ajax").modal("hide");

				if($(".pagination").length>0){
					var pageIndex = $(".pagination li.active a:first").text();
					window.springstage.reloadTable({ //重新加载表格数据
						"page.pn": pageIndex
					}, false);
				}else{
					window.springstage.reloadTable(null, false);
				}
				//更新成功回调
				$form.trigger("update:success",data);//触发更新成功事件
			}else{
				$("#modal-ajax").modal("hide");
				flash("success",data.message);
				window.springstage.reloadTable(null, false);
				$form.trigger("create:success",data);//触发创建成功事件
			}
		}
	}).fail(function( jqxhr, settings, exception ) {
		flash("error",exception);
	})});

//表格中的删除操作
$(document).on("click",".table-responsive a.btn-delete[data-remote='true']",function(e){
	e.preventDefault();
	e.stopPropagation();
	var me=$(this);
	if(me.data("confirm") && window.confirm(me.data("confirm"))){
		$.ajax({
			type:"post",
			url:me.prop("href"),
			data:{"_method":"delete"}
		}).done(function(data, textStatus ,jqxhr){
			flash("success",data.message);
			//更新表格
			var pageIndex = $(".pagination li.active a:first").text();
			window.springstage.reloadTable({ //重新加载表格数据
				"page.pn": pageIndex
			}, false);
			me.trigger("delete:success",data); //触发删除成功事件
		});
	}
});

/**
 * ajax 操作
 *
 */
$(document).on("click","a.btn-ajax",function(e){
	e.preventDefault();
	e.stopPropagation();
	var me=$(this);
	if(me.data("confirm") && window.confirm(me.data("confirm"))){
		var method = me.data("method") || "post";
		var data = {};
		if(method.toUpperCase()!="POST" && method.toUpperCase()!="GET"){
			data["_method"] = method;
			method = "post";
		}
		$.ajax({
			type:method,
			url:me.prop("href"),
			data:data
		}).done(function(data, textStatus ,jqxhr){
			me.trigger("ajax:success",data);
		});
	}
});

$(function(){
	if($(".chosen-select").length>0) {
		$(".chosen-select").chosen({disable_search: true, allow_single_deselect: true});
	}
});

function dateFormatter(d) {
	var date = new Date(d);

	return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " "
		+ date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
}