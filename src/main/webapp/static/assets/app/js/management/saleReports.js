
$(function() {
//[加载中]提示组件
    var oloadingCon = $(".loadingCon");
    var query = function(ajaxConfig){
        //TODO 转圈
        oloadingCon.show();

        $.ajax(ajaxConfig).done(function( data, textStatus ) {
            //TODO 停止转圈
            //console.log(data);
            oloadingCon.hide();
            $("#data_con1").html(data);
        }).fail(function( jqxhr, settings, exception ) {
            //TODO 停止转圈
            oloadingCon.hide();
        });
    };

    //查看品农当前时间段内，销售额记录
    $(document).on("click", "#pn_sale_see", function(event){
        event.preventDefault();
        event.stopPropagation();
        var cur = $(this);
        var money = cur.data("money");
        if(money<=0){
            window.flash("warning", "此时间段内品农没有销售记录")
            return false;
        }

        var ajaxConfig={
            type: "GET",
            url:cur.data("url"),
            data: {
                startTime:$("#startTime").val(),
                endTime:$("#endTime").val(),
                deptId:1
            },
            dataType: "html"
        };
        query(ajaxConfig);
    });

    //查看品质当前时间段内，销售额记录
    $(document).on("click", "#pz_sale_see", function(event){
        event.preventDefault();
        event.stopPropagation();
        var cur = $(this);
        var money = cur.data("money");
        if(money<=0){
            window.flash("warning", "此时间段内品质没有销售记录")
            return false;
        }

        var ajaxConfig={
            type: "GET",
            url:cur.data("url"),
            data: {
                startTime:$("#startTime").val(),
                endTime:$("#endTime").val(),
                deptId:2
            },
            dataType: "html"
        };
        query(ajaxConfig);
    });

    //当前品农或品质公司，时间段内销售额记录
    $(document).on("click",".pagination a",function(event){
        event.preventDefault();
        event.stopPropagation();
        var cur = $(this);

        //http://localhost:8080/management/saleReports/list?deptId=1&page.pn=2&page.size=10
        var href_str = cur.prop("href");
        var inde = href_str.lastIndexOf("?");
        var href_str = href_str.substring(inde);
        //console.log("-"+href_str+"-");

        var ajaxConfig={
            type: "GET",
            url:"/management/saleReports/list"+href_str,
            // data: {
            //     startTime:$("#startTime").val(),
            //     endTime:$("#endTime").val(),
            //     deptId:$("#deptId").val()
            // },
            dataType: "html"
        };
        query(ajaxConfig);
    });
});