Tools = {
	version : '1.0.0'
};

/**
 * 属性复制
 * 
 * @param {}
 *            属性接受方对象
 * @param {}
 *            属性源对象
 * @param {}
 *            默认对象，如果该参数存在，obj将会得到那些defaults有而config没有的属性
 * @return {}
 */
Tools.apply = function(o, c, defaults) {
	if (defaults) {
		Tools.apply(o, defaults);
	}
	if (o && c && typeof c == 'object') {
		for (var p in c) {
			o[p] = c[p];
		}
	}
	return o;
};

/**
 * 创建命名空间
 * 
 * @return
 */
Tools.ns = function() {
	var len1 = arguments.length, i = 0, len2, j, main, ns, sub, current;

	for (; i < len1; ++i) {
		main = arguments[i];
		ns = arguments[i].split('.');
		current = window[ns[0]];
		if (current === undefined) {
			current = window[ns[0]] = {};
		}
		sub = ns.slice(1);
		len2 = sub.length;
		for (j = 0; j < len2; ++j) {
			current = current[sub[j]] = current[sub[j]] || {};
		}
	}
	return current;
}

/**
 * 工具类
 */
var validate = Tools.apply(this, {
	rv:false,
	rv2 : {
		rv:false,
		msg:'error'
	},
	pattern:'',


	/*--------------------------------验证-----------------------------------*/
	/**
	 * 验证邮箱
	 */
	isEmail : function(value) {
		pattern = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		rv = pattern.test(value) ? true : false;
		return rv;
	},

	/**
	 * 验证手机号
	 */
	isMobile : function(value) {
		pattern = /^((13\d)|(15[0-3,5-9])|(17[0-9])|(18[0-3,5-9]))[0-9]{8}$/
		rv = pattern.test(value) ? true : false;
		return rv;
	},

	/**
	 * 验证固定电话
	 */
	isTelephone : function(value) {
		pattern = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
		rv = pattern.test(value) ? true : false;
		return rv;
	},

	/**
	 * 验证密码
	 */
	vlPassword : function(value,minLen,maxLen) {
		if(value!=""){
			if(value.length>=minLen && value.length<=maxLen){
				rv2.rv = true;
				rv2.msg = "密码不能为空";
			}else{
				rv2.msg = "密码长度"+minLen+"-"+maxLen+"位";
			}
		}else{
			rv2.msg = "密码不能为空";
		}

		return rv2;
	},

	/**
	 * 验证网址
	 */
	vlWebUrl : function(value) {
		pattern = /^(http:\/\/)?(www.)?(\w+\.)+\w{2,4}(\/)?$/;
		rv = pattern.test(value) ? true : false;
		return rv;
	},

	/**
	 * JQuery Ajax数据访问
	 * 	//1. 不用担心自己的URL中的指定的目标是否需要"?"还是"&"
	 *	//2. 如遇到需要传递参数时(不用担心中英文传参转码问题), 例如: var jsonParam = {account:"mfy张三", pwd:"123456"};
	 *	//3. async表示是否异步, false同步加载, true异步加载  
	 * @param {} url
	 * @param {} jsonParam
	 * @return {}
	 */
	jqAjax : function(url, jsonParam){
		$.ajaxSetup({async:false});
		var rv = null;
		if(url!=null && url!="" && jsonParam!=null && jsonParam!=""){
			if(url.indexOf("?")!=-1){
				url += "&";
			}else{
				url += "?";
			}
			url += "randomNoUse=" + Math.random()
			
			$.post(url, jsonParam, function(backData, textStatus, jqXHR){
				//alert(backData + "\n" + textStatus + "\n" + jqXHR);
				if(backData!=null && backData!=""){
					rv = eval("(" + backData + ")");
				}
			});
		}
		return rv;
	},


	/**
	 * 自动解析淘宝格式地址信息
	 * 
	 * 例如："张XX，13888888888，0579-88888888，浙江省 金华市 婺城区 永康街697号东侧二楼，321000"，可以提取出以下信息
	 *  0 -> 张XX
	 *	1 -> 13888888888
	 *	2 -> 0579-88888888
	 *	3 -> 浙江省
	 *	4 -> 金华市
	 *	5 -> 婺城区
	 *	6 -> 永康街697号东侧二楼
	 *	7 -> 321000
	 *
	 * 可以从淘宝等平台复制物流信息到这里，提高下单效率，格式要求（姓名，手机，电话，地址，邮编）
	 * 注：淘宝地址格式严格按照以上格式填写“省、市、区”后面要有[空格]！提交不成功请检查地址格式！
	 * 
	 * @param taoBaoStyleAddress 淘宝地址格式
	 */
	taoBaoStyleAddressAnalysis : function(taoBaoStyleAddress) {
		var tbAddAnalysisArray = new Array();
		var detailAdd = "";
		if((taoBaoStyleAddress!="") && (taoBaoStyleAddress.indexOf("，")!=-1)){
			var tbaddArray = taoBaoStyleAddress.split("，");
			for(var i=0;i<tbaddArray.length;i++){
				if(tbaddArray[i].indexOf(" ")==-1){
					//没有空格, 说明是非行政区域信息
					tbAddAnalysisArray.push(tbaddArray[i]);
				}else{
					//行政区域信息, 接下来要按 [空格] 进行二次分隔, 分别获取 "省/直辖市, 市, 区/县, 详细地址"
					detailAdd = tbaddArray[i];
					if((detailAdd!="") && (detailAdd.indexOf(" "))){
						var detailAddArray = detailAdd.split(" ");
						for(var j=0;j<detailAddArray.length;j++){
							tbAddAnalysisArray.push(detailAddArray[j]);
						}
					}
				}
			}
		}
		return tbAddAnalysisArray;
	}



});
