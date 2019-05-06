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
    var userEmail = JSON.parse(localStorage.getItem("user")).email;
    var role = JSON.parse(localStorage.getItem("user")).role;

    if(role == "admin" || role == "employee"){
        $("#downloadMuBan").show();
        $("#test8").show();
        $("#test9").show();
    }

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
        index = layer.msg('文件导出中，请稍候',{icon: 16,time:false,shade:0.8});
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
                    if(!data.list){
                        layer.close(index);
                        showNotice()
                        return;
                    }else{
                        $("#newsList").show();
                        $(".ajaxTr").remove();
                        $("#zhijiefukuan").removeProp("checked");
                        $("#dikohuokuan").removeProp("checked");
                        $("#heji").text("");
                    }
                    var heji = data.heji;
                    var list = data.list;
                    var payType = data.payType;
                    if (payType){
                        if (payType == "直接付款") {
                            $("#zhijiefukuan").prop("checked","true");
                        }else{
                            $("#dikohuokuan").prop("checked","true");
                        }
                    }
                    if(heji){
                        $("#heji").text(heji);
                    }
                    $("#gysId").text(username);
                    $("#gysName").text(nickname);
                    $("#gysEmail").text(userEmail);
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
                    layer.close(index);
                }
            });
    });

    $("#downloadMuBan").click(function(){
        //var index = top.layer.msg('文件下载中,请勿重复点击',{icon: 16,time:false,shade:0.8});
        var storeId = $("#storeName  option:selected").val()
        var   link = document.createElement('a');
        link.href = localhostPaht + '/json/muban.xlsx';
        link.setAttribute("download", "");
        link.click();
    });


    $("#downLoadPdf").click(function(){
        index = layer.msg('文件导出中，请稍候',{icon: 16,time:false,shade:0.8});
        $(function () {
            $.ajax({
                type : "GET",
                url :  localhostPaht + '/supplier/exportServiceList?gysid='+ username +'&danjuDate='+ Mydate+'&gysName=' + nickname + '&gysEmail='+ userEmail,
                success : function(data,status) {
                    if(data && data.state == "success"){
                        var filePath = data.msg;
                        var   link = document.createElement('a');
                        link.href = localhostPaht + filePath;
                        link.setAttribute("download", "");
                        link.click();
                        layer.close(index);
                    }else{
                        layer.close(index);
                        showNotice()
                    }
                },
            });
        })
    })

    function showNotice() {
        layer.open({
            type: 1,
            title: "提示",
            area: '300px',
            shade: 0.8,
            id: 'LAY_layuipro',
            btn: ['关闭'],
            moveType: 1,
            content: '<div style="padding:15px 20px; text-align:justify; line-height: 22px; text-indent:2em;border-bottom:1px solid #e2e2e2;">无数据,查询月份数据未生成<p class="layui-red"></p> </div>',
            success: function(layero){
                var btn = layero.find('.layui-layer-btn');
                btn.css('text-align', 'center');
                btn.on("click",function(){
                    layer.close();
                });
            },
            cancel: function(index, layero){
                layer.close();
            }
        });
    }


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


