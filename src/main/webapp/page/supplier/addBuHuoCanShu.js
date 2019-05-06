layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    var localhostPaht=$("#myPathValue").val();
    var username = JSON.parse(localStorage.getItem("user")).username;

    //表单初始赋值
    $.ajax({
        type: "GET",
        url: localhostPaht + "/order/findBuHuoCanShu",
        data:  {
            "username" : username
        },
        dataType: "json",
        success: function(data){
            if(data){
                $("#xiadanzhouqi").val(data.xiadanzhouqi);
                $("#songhuozhouqi").val(data.songhuozhouqi);
            }
        }
    });



    //监听提交
    form.on('submit(formCommit)', function(data){
        var xiadanzhouqi = data.field.xiadanzhouqi;
        var songhuozhouqi = data.field.songhuozhouqi;
        if (xiadanzhouqi > 30 ) {
            layer.msg("下单周期不能超过30天!",{
                time:1500
            });
            return;
        }
        $.ajax({
            type: "post",
            url : localhostPaht + "/order/saveBuHuoCanShu",
            dataType:'json',
            data: {
                "xiadanzhouqi":xiadanzhouqi,
                "songhuozhouqi":songhuozhouqi,
                "username":username
            },
            success: function(data){
                var updateSuccess = data.state;
                if(updateSuccess && updateSuccess == 'success'){
                    layer.msg('成功', {
                        time: 500,
                    });
                    parent.location.reload();
                }else{
                    layer.msg('失败', {
                        time: 500, //20s后自动关闭
                    });
                }
            }
        });
            return false;
        });

})