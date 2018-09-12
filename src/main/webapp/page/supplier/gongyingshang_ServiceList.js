layui.use(['form','layer','upload','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        upload = layui.upload,
        table = layui.table;

    var localhostPaht=$("#myPathValue").val();
    var username = JSON.parse(localStorage.getItem("user")).username;
    var nickname = JSON.parse(localStorage.getItem("user")).nickname;
    var nickEmail = JSON.parse(localStorage.getItem("user")).email;

    // var index = layer.msg('加载中，请稍候',{icon: 16,time:false,shade:0.8});
    upload.render({
        elem: '#test8'
        ,url: localhostPaht + '/supplier/importServiceList'
        ,auto: false
        ,exts: 'xls|xlsx' //只允许上传压缩文件
        ,bindAction: '#test9'
        ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
            layer.load(); //上传loading
        }
        ,done: function(res){
            layer.closeAll('loading'); //关闭loading
            if(res.code == 0){
                layer.msg('文件上传成功');
            }else if(res.code == 1){
                layer.msg('本月数据已存在,请勿重复上传, 如之前数据错误,请联系管理员删除后,再上传');
            }
        }
        ,error: function(index, upload){
            layer.closeAll('loading'); //关闭loading
        }
    });

    //查询按钮
    $(".findOnTime").click(function(){
        if (Mydate >= getNowFormatDate()){
            layer.open({
                type: 1,
                title: "温馨提示",
                area: '300px',
                shade: 0.8,
                id: 'LAY_layuipro',
                btn: ['确定'],
                moveType: 1,
                content: '<div style="padding:15px 20px; text-align:justify; line-height: 22px; text-indent:2em;border-bottom:1px solid #e2e2e2;"><p class="layui-red">您选择的日期数据还未生成,请重选日期查询</p> </div>',
                success: function(layero){
                    var btn = layero.find('.layui-layer-btn');
                    btn.css('text-align', 'center');
                }
            });
        }else{
            $("#newsList").show();
            $(".ajaxTr").remove();
            // $("#zhijiefukuan").removeAttr("checked");
            // $("#dikohuokuan").removeAttr("checked");
            $("#heji").text("");

            //执行重载
            $.ajax({
                type: "POST",
                url: localhostPaht + "/supplier" + "/findServiceList",
                data:  {
                    "gysId":username,
                    "danjuDate" : Mydate
                },
                dataType: "json",
                success: function(data){
                    var heji = data.heji;
                    var list = data.list;
                    var payType = data.payType;
                    if (payType){
                        if (payType == "直接付款") {
                            $("#zhijiefukuan").attr("checked","true");
                        }else{
                            $("#dikohuokuan").attr("checked","true");
                        }
                    }
                    if(heji){
                        $("#heji").text(heji);
                    }
                    $("#gysId").text(username);
                    $("#gysName").text(nickname);
                    $("#gysEmail").text(nickEmail);
                    if(list){
                        $.each(list,function(n,serviceListVo) {
                            var $tr = $("<tr class='ajaxTr'/>");
                            var $td1 = $("<td align='center'>"+serviceListVo.danjuDate +"</td>");
                            var $td2 = $("<td align='center'>"+serviceListVo.description +"</td>");
                            var $td3 = $("<td align='center'>"+serviceListVo.amount +"</td>");
                            var $td4 = $("<td align='center'>"+serviceListVo.remarks+"</td>");
                                $tr.append($td1);
                                $tr.append($td2);
                                $tr.append($td3);
                                $tr.append($td4);
                            $("#afterTr").after($tr);
                        });
                    }
                }
            });
        }
    });


    //日期
    var Mydate = getbeforFormatDate();
    laydate.render({
        elem: '#test1'
        ,type: 'month'
        ,value: getbeforFormatDate()
        ,format: 'yyyy年M月'
        ,done: function(value, date, endDate){
            Mydate = value;
        }
    });

    //获取上个月
    function getbeforFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth();
        // if (month >= 1 && month <= 9) {
        //     month = "0" + month;
        // }
        var currentdate = year + "年"  + month + "月";
        return currentdate;
    }


    //获取当前时间
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        // if (month >= 1 && month <= 9) {
        //     month = "0" + month;
        // }
        var currentdate = year + "年"  + month + "月";
        return currentdate;
    }

})


