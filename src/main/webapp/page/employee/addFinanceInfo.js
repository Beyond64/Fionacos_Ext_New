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


    //添加验证规则
    form.verify({
        userBirthday : function(value){
            if(!/^(\d{4})[\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|1[0-2])([\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/.test(value)){
                return "出生日期格式不正确！";
            }
        }
    })


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
            'name' : $(".name").val(),
            'position' : $(".position").val(),
            'email' : $(".email").val(),
            'phone' : $(".phone").val(),
            'address' : $(".address").val(),
            'objectId' : $(".objectId").val()
        };
        $.ajax({
            type: "POST",
            url: localhostPaht + "/supplier" + "/addFinaceInfo" ,
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