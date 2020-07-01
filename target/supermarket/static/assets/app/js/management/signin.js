$(function () {
    $("#loginForm input[name='account']").focus();
    $(".jcaptcha-btn").click(function () {
        var img = $(".jcaptcha-img"), imageSrc = img.attr("src");
        if (imageSrc.indexOf("?") > 0) {
            imageSrc = imageSrc.substr(0, imageSrc.indexOf("?"));
        }
        imageSrc = imageSrc + "?" + new Date().valueOf();
        img.attr("src", imageSrc);
    });

});


//验证码
function vlCode() {
    var rv = false;
    var ocode = $("#captcha");

    if (ocode && $.trim(ocode.val()) != "") {
        $.ajax({
            type: "get",
            url: $.ctx+"/jcaptcha-validate",
            dataType: "json",
            async: false,
            data: {
                fieldValue: $.trim(ocode.val())
            },
            success: function (data) {
                if (data.success) {
                    rv = true;
                    $("#loginForm").data("validate", "true");
                    $("#validate-status").attr("class", "fa fa-check-circle").parent().addClass("text-success");
                    $("#error-info").hide();
                } else {
                    $("#validate-status").attr("class", "fa fa-times-circle").parent().addClass("text-danger");
                    $("#error-info").text(data.message);
                    $("#error-info").show();
                }
            }
        });
    } else {
        $("#validate-status").attr("class", "fa fa-times-circle").parent().addClass("text-danger");
        $("#error-info").text("请填写验证码");
        $("#error-info").show();
    }
    return rv;
}


//表单提交
/*
function formSubmit() {
    var rv = false;

    if (vlCode()) {
        rv = true;
    }
    return rv;
}
*/
