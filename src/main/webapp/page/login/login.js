layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    $(".loginBody .seraph").click(function(){
        layer.msg("这只是做个样式，",{
            time:5000
        });
    })

    //登录按钮
    form.on("submit(login)",function(data){
        $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
        var localhostPaht=$("#myPathValue").val();
        var password = $("#password").val();
        var username = $("#userName").val();
        $.ajax({
            type: "POST",
            url: localhostPaht+ "/user/login",
            data:{
                "username":username,
                "password":password
            },
            dataType: "json",
            success: function(data){
                if(data == true){
                    window.location.href =localhostPaht+ "/index.jsp";
                    // window.event.returnValue = false;
                    // if (window.event.preventDefault) window.event.preventDefault();
                    // return false;
                }else if (data == "dongjie") {
                    $("#loginBtn").text("登录").removeAttr("disabled").removeClass("layui-disabled");
                    layer.msg("账户已被冻结,请联系管理员", {
                        time: 1500
                    });
                } else {
                    $("#loginBtn").text("登录").removeAttr("disabled").removeClass("layui-disabled");
                    layer.msg("用户名或者密码错误,请检查", {
                        time: 1500
                    });
                }
            }
        });
    })

    $('#password').on('keypress',function(event){
        if(event.keyCode == 13)
        {
            $("#loginBtn").text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
            var localhostPaht=$("#myPathValue").val();
            var password = $("#password").val();
            var username = $("#userName").val();
            $.ajax({
                type: "POST",
                url: localhostPaht+ "/user/login",
                data:{
                    "username":username,
                    "password":password
                },
                dataType: "json",
                success: function(data){
                    if(data == true){
                        window.location.href =localhostPaht+ "/index.jsp";
                        // window.event.returnValue = false;
                        // if (window.event.preventDefault) window.event.preventDefault();
                        // return false;
                    }else if (data == "dongjie") {
                        $("#loginBtn").text("登录").removeAttr("disabled").removeClass("layui-disabled");
                        layer.msg("账户已被冻结,请联系管理员", {
                            time: 1500
                        });
                    } else {
                        $("#loginBtn").text("登录").removeAttr("disabled").removeClass("layui-disabled");
                        layer.msg("用户名或者密码错误,请检查", {
                            time: 1500
                        });
                    }
                }
            });
        }
    });

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})
