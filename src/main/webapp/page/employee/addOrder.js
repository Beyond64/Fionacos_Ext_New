var form, $,areaData;
layui.config({
    base : "../../js/"
}).extend({
    "address" : "address"
})
layui.use(['form','layer','upload','laydate',"address"],function(){
    form = layui.form;
    $ = layui.jquery;
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        laydate = layui.laydate,
        address = layui.address;

    var localhostPaht=$("#myPathValue").val();

    //上传头像
    upload.render({
        elem: '.userFaceBtn',
        url: '../../json/userface.json',
        method : "get",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
        done: function(res, index, upload){
            var num = parseInt(4*Math.random());  //生成0-4的随机数，随机显示一个头像信息
            $('#userFace').attr('src',res.data[num].src);
            window.sessionStorage.setItem('userFace',res.data[num].src);
        }
    });

    //添加验证规则
    form.verify({
        userBirthday : function(value){
            if(!/^(\d{4})[\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|1[0-2])([\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/.test(value)){
                return "出生日期格式不正确！";
            }
        }
    })
    //选择出生日期
    laydate.render({
        elem: '.userBirthday',
        format: 'yyyy年MM月dd日',
        trigger: 'click',
        max : 0,
        mark : {"0-12-15":"生日"},
        done: function(value, date){
            if(date.month === 12 && date.date === 15){ //点击每年12月15日，弹出提示语
                layer.msg('今天是马哥的生日，也是layuicms2.0的发布日，快来送上祝福吧！');
            }
        }
    });

    //获取省信息
    address.provinces();

    //提交个人资料
    form.on("submit(addUser)",function(data){
        var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        var localhostPaht=$("#myPathValue").val();
        var username = JSON.parse(localStorage.getItem("user")).username;
        if(!username){
            layer.msg("您没有权限,进行此操作",{
                time:1500
            });
            return;
        }
        var data = '';
        data = {
            'username' : $(".username").val(),
            'nickname' : $(".nickname").val(),
            'email' : $(".email").val(),
            'businessName' : $(".businessName").val(),
            'businessPhone' : $(".businessPhone").val(),
            'financeName' : $(".financeName").val(),
            'financeEmail' : $(".financeEmail").val(),
            'financePhone' : $(".financePhone").val(),
            'backAddress' : $(".backAddress").val(),
            'backPhone' : $(".backPhone").val(),
            'bankName' : $(".bankName").val(),
            'bankAccount' : $(".bankAccount").val(),
            'lastOperator' : username,
        };
        $.ajax({
            type: "POST",
            url: localhostPaht + "/user" + "/addUser",
            data:data,
            dataType: "json",
            success: function(data){
                if(data == true){
                    layer.close(index);
                    layer.msg("添加成功！",{
                        time:2000
                    });
                    setTimeout(function(){
                        layui.layer.tips('点击此处返回账户列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    },1000);
                }else{
                    layer.close(index);
                    layer.msg("添加失败,请检查!",{
                        time:2000
                    });
                }
            }
        });
        // window.sessionStorage.setItem("userInfo",JSON.stringify(userInfoHtml));设置缓存
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

})