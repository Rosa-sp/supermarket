
$(function() {
    var tmp = {scheduleId:0};

    /**
     * 选择查看档期列表
     */
    $("#scheduleTable").on("check.bs.table", function(event, row, $element){
        tmp.scheduleId = row.id;
        $("#goodTable").bootstrapTable("refresh", {query:{"search.pnzSchedule.id_eq":row.id}});
        if(row.state.key=="NOUSE"){
            $("#addGoodCon").removeClass("hide");
        }else{
            $("#addGoodCon").addClass("hide");
        }
    });


    $("#scheduleTable").on("uncheck.bs.table", function(event, row, $element){
        tmp.scheduleId = 0;
        $("#addGoodCon").addClass("hide");
        $("#goodTable").bootstrapTable("removeAll");
    });


    $("#goodsModel").on("show.bs.modal", function(event){
        if(tmp.scheduleId>0){
            $("#selectGoodsTable").bootstrapTable("refresh", {query:{scheduleId:tmp.scheduleId}});
        }else{
            window.flash("error", "请选择要添加商品的档期");
            return false;
       }
    });

    //确定按钮点击的时候
    $("#goodsModel button.sureBtn").on("click", function(){

        var rows = $("#selectGoodsTable").bootstrapTable("getData");

        var params = "scheduleId="+tmp.scheduleId;

        for(var m=0;m<rows.length;m++){
            var row = rows[m];
            if(row.offerChk){
                params += "&goodIds="+row.id;
                params += "&offerPrices="+row.offerPrice;
            }
        }

        var url = $(this).data("url");

        //ajax 保存到数据库
        $.ajax({
            url:url,
            type:"POST",
            data:params
        }).done(function(data){
            if(data.success){
                window.flash(data.level, data.message);
                //保存后刷新 表格数据,关闭弹出层
                $("#goodTable").bootstrapTable("refresh", {query:{"search.pnzSchedule.id_eq":tmp.scheduleId}});
                $("#goodsModel").modal("hide");
            }
        });

        return false;
    });

    $(document).on("change", "input.offerChk", function(e){
        var checked = $(this).is(":checked");
        var index = $(this).data("index.jsp.jsp.jsp.jsp.jsp");
        $("#selectGoodsTable").bootstrapTable("updateRow", {index:index, row:{offerChk:checked}});
    });

    //失去焦点后将row中的数据更改
    $(document).on("blur","input.offerPrice", function(e){
        e.preventDefault();
        e.stopPropagation();

        var val = $(this).val();
        var index = $(this).parents("tr").data("index.jsp.jsp.jsp.jsp.jsp");

        $("#selectGoodsTable").bootstrapTable("updateRow", {index:index, row:{offerPrice:val}});
    });

    $(document).on("submit", "#selectSearchForm", function(){

        var name = $("#selectSearchForm .searchName").attr("name");
        var val = $("#selectSearchForm .searchName").val();
        var query = {};
        query[name] = val;

        $("#selectGoodsTable").bootstrapTable("refresh", {query:query});

        return false;
    });

    $(document).on("click", ".btn.offerDelete", function(){
        var url = $(this).attr("data-url");
        var index = $(this).attr("data-index");
        $.ajax({
            url :url,
            type:"POST",
            data:{"_method":"delete"}
        }).done(function(data){
            if(data.success){
                $("#goodTable").bootstrapTable("refresh", {query:{"search.pnzSchedule.id_eq":tmp.scheduleId}});
            }
        });
        return false;
    });

 });


function colFormat(val, row, index){
    var name = $(this)[0].name;
    var rv = "";
    switch (name){
        case "offerChk":
            if(row.offerChk==undefined){
                row.offerChk=false;
            }
            rv = "<input type='checkbox' data-index='"+index+"' name='offer-chk' class='offerChk' value='"+row.id+"' "+(row.offerChk?"checked='checked'":"")+">";
            break;
        case "offerPrice":
            if(!row.offerPrice){
                row.offerPrice=row.price;
            }
            rv = "<input type='text' name='offerPrice' onclick='return false;' ondblclick='return false;' value='"+row.offerPrice+"' class='form-control offerPrice input-numberic'>";
            break;

        case "operator":
            //如果当前正在
            if($("#addGoodCon").hasClass("hide")){
                rv = "";
            }else{
                rv = "<a href='javascript:void(0);' data-url='/management/pnzOffers/"+row.id+"' data-index='"+index+"' class='btn btn-sm btn-danger offerDelete'>删除</a>";
            }
            break;
    }

    return rv;
}
