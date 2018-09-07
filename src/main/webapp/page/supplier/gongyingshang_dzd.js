layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    var localhostPaht=$("#myPathValue").val();
    var username = JSON.parse(localStorage.getItem("user")).username;

    var index = layer.msg('加载中，请稍候',{icon: 16,time:false,shade:0.8});
    //用户列表
    var tableIns = table.render({
        elem: '#newsList',
        url : localhostPaht + '/supplier/daiXiaoDuiZhang?date='+ getbeforFormatDate() +'&gysid=' + username,
        cellMinWidth : 80,
        page : false,
        id : "newsListTable",
        totalRow: true,
        cols : [[
            {field: 'gysId', title: '供应商', align:"center" ,width:'7%', totalRowText: '合计'},
            {field: 'gysName', title: '供应商名称',width:'13%'},
            {field: 'barcode', title: '商品编码', width:'12%',align:'center',},
            {field: 'barcodeDesc', title: '商品描述', width:'20%', align:'center'},
            {field: 'jieSuanShuLiang', title: '结算数量',width:'8%',align:'center',totalRow: true},
            {field: 'buHanShuiDanJia', title: '不含税单价',width:'8%',align:'center'},
            {field: 'hanShuiDanJia', title: '含税单价',width:'8%',align:'center'},
            {field: 'xiaoShouJinE', title: '销售金额',width:'8%',align:'center',totalRow: true},
            {field: 'jieSuanJinE', title: '结算金额',width:'8%',align:'center',totalRow: true},
            {field: 'posLingShouJia', title: 'POS零售价',width:'8%',align:'center'}
            // {title: '预览', width:130, templet:'#newsListBar',fixed:"right",align:"center"}
        ]],
        done: function(res, curr, count){
            layer.close(index);
           var xiaoShouJinE = $(".layui-table-total .layui-table .laytable-cell-1-xiaoShouJinE").text();
           var jieSuanJinE = $(".layui-table-total .layui-table .laytable-cell-1-jieSuanJinE").text();
            $(".layui-table-total .layui-table .laytable-cell-1-xiaoShouJinE").text(xiaoShuDian(xiaoShouJinE));
            $(".layui-table-total .layui-table .laytable-cell-1-jieSuanJinE").text(xiaoShuDian(jieSuanJinE));
        }
    });
    
    function xiaoShuDian(s) {
        if (s){
           if (s.indexOf(".") != -1){
               var split = s.split(".");
               if(split[1].length == 1){
                   return s + "0";
               }else if (split[1].length == 2) {
                   return s;
               }else if (split[1].length > 2) {
                   return split[0] + "." + split[1].substr(0,2);
               }
           } else{
               return s + ".00";
           }
        }
        return s;
    }

    //日期
    var Mydate = getbeforFormatDate();
    laydate.render({
        elem: '#test1'
        ,type: 'month'
        ,value: getbeforFormatDate()
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
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        var currentdate = year + seperator1 + month;
        return currentdate;
    }


    //获取当前时间
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        var currentdate = year + seperator1 + month;
        return currentdate;
    }

    $(".daochu_btn").click(function(){
        index = layer.msg('文件导出中，请稍候',{icon: 16,time:false,shade:0.8});
        $(function () {
            $.ajax({
                type : "GET",
                url :  localhostPaht + '/supplier/exportDaiXiaoDuiZhang?date='+ Mydate +'&gysid=' + username,
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
                        layer.open({
                            type: 1,
                            title: "提示",
                            area: '300px',
                            shade: 0.8,
                            id: 'LAY_layuipro',
                            btn: ['关闭'],
                            moveType: 1,
                            content: '<div style="padding:15px 20px; text-align:justify; line-height: 22px; text-indent:2em;border-bottom:1px solid #e2e2e2;">无数据,查询月份数据未生成或无代销商品<p class="layui-red"></p> </div>',
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
                },
            });
        })
    })


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
            //执行重载
            index = layer.msg('加载中，请稍候',{icon: 16,time:false,shade:0.8});
            tableIns.reload({
                url : localhostPaht + '/supplier/daiXiaoDuiZhang',
                method:'GET',
                where: {
                    date: Mydate,
                    gysid: username
                }
            });
        }
    });

})


