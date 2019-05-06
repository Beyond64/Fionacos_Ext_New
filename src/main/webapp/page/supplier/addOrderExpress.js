layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    var localhostPaht=$("#myPathValue").val();
    var OrderNumber = localStorage.getItem("OrderNumber");
    var username = JSON.parse(localStorage.getItem("user")).username;

        //监听提交
    form.on('submit(formDemo)', function(data){
        var  orderNo = $("#orderNo").val();
        var trackingNanme = data.field.trackingNanme;
        var trackingNo = data.field.trackingNo;
        $.ajax({
            type: "post",
            url : localhostPaht + "/order/updateOrderHeader",
            dataType:'json',
            data: {
                "trackingNanme":trackingNanme,
                "trackingNo":trackingNo,
                "orderNo":orderNo
            },
            success: function(data){
                var updateSuccess = data.state;
                if(updateSuccess && updateSuccess == 'success'){
                    layer.msg('修改成功', {
                        time: 500,
                    });
                    parent.location.reload();
                }else{
                    layer.msg('修改失败', {
                        time: 500, //20s后自动关闭
                    });
                }
            }
        });
            return false;
        });

})