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
        url : localhostPaht + '/json/logs.json',
        cellMinWidth : 80,
        page : false,
        id : "newsListTable",
        totalRow: true,
        cols : [[
            {field: 'gongyingshangId', title: '供应商代码', align:"center" ,width:'6%', totalRowText: '合计'},
            {field: 'gongyingshangName', title: '供应商名称',width:'7%'},
            {field: 'storeId', title: '门店代码', width:'6%',align:'center',},
            {field: 'storeName', title: '门店名称', width:'9%', align:'center'},
            {field: 'dingdanriqi', title: '订单日期',width:'7%',align:'center'},
            {field: 'zongzhangriqi', title: '总账日期',width:'7%',align:'center'},
            {field: 'tiaoma', title: '国际条码',width:'10%',align:'center'},
            {field: 'tiaomamiaoshu', title: '产品名称',width:'16%',align:'center'},
            {field: 'fayunshuliang', title: '发运数量',width:'5%',align:'center',totalRow: true},
            {field: 'shuilv', title: '税率',width:'5%',align:'center'},
            {field: 'buhanshuijine', title: '不含税金额',width:'7%',align:'center',templet:"#buhanshuijine"},
            {field: 'hanshuijine', title: '含税金额',width:'7%',align:'center',templet:"#hanshuijine"},
            {field: 'gezichengdan', title: '各自承担(50%)',width:'8%',align:'center',totalRow: true,templet:"#gezichengdan"}
        ]],
        done: function(res, curr, count){
            layer.close(index);
           var gezichengdan = $(".layui-table-total .layui-table .laytable-cell-1-gezichengdan").text();

            $(".layui-table-total .layui-table .laytable-cell-1-gezichengdan").text(xiaoShuDian(gezichengdan));
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
    // var Startdate = getbeforeDate();
    // var endDate = getNowFormatDate();
    // laydate.render({
    //     elem: '#test1'
    //     ,range: true
    //     ,done: function(value, date, endDate){
    //         Startdate = value;
    //         endDate = endDate;
    //     }
    // });


    // //获取上个月
    // function getbeforeDate() {
    //     var date = new Date();
    //     var seperator1 = "-";
    //     var year = date.getFullYear();
    //     var month = date.getMonth();
    //     var strDate = date.getDate();
    //     if (month >= 1 && month <= 9) {
    //         month = "0" + month;
    //     }
    //     if (strDate >= 0 && strDate <= 9) {
    //         strDate = "0" + strDate;
    //     }
    //     var currentdate = year  + month  + strDate;
    //     return currentdate;
    // }


    // //获取当前时间
    // function getNowFormatDate() {
    //     var date = new Date();
    //     var seperator1 = "-";
    //     var year = date.getFullYear();
    //     var month = date.getMonth() + 1;
    //     var strDate = date.getDate();
    //     if (month >= 1 && month <= 9) {
    //         month = "0" + month;
    //     }
    //     if (strDate >= 0 && strDate <= 9) {
    //         strDate = "0" + strDate;
    //     }
    //     var currentdate = year  + month  + strDate;
    //     return currentdate;
    // }

    renderSeasonDate(document.getElementById('test1'),1);
    /**
     * 季度初始化
     * @param ohd 季度input dom对象非jquery对象
     * @param sgl 有值单个，无值默认范围
     */
    function renderSeasonDate(ohd,sgl){
        var ele = $(ohd);
        laydate.render({
            elem: ohd,
            type: 'month',
            format: 'yyyy年M季度',
            range: sgl?null:'~',
            min:"1900-1-1",
            max:"2099-12-31",
            btns: ['clear', 'confirm'],
            ready: function(value, date, endDate){
                var hd = $("#layui-laydate"+ele.attr("lay-key"));
                if(hd.length>0){
                    hd.click(function(){
                        ren($(this));
                    });
                }
                ren(hd);
            },
            done: function(value, date, endDate){
                if(!isNull(date)&&date.month>0&&date.month<5){
                    ele.attr("startDate",date.year+"-"+date.month);
                }else{
                    ele.attr("startDate","");
                }
                if(!isNull(endDate)&&endDate.month>0&&endDate.month<5){
                    ele.attr("endDate",endDate.year+"-"+endDate.month)
                }else{
                    ele.attr("endDate","");
                }
            }
        });
        var ren=function(thiz){
            var mls=thiz.find(".laydate-month-list");
            mls.each(function(i,e){
                $(this).find("li").each(function(inx,ele){
                    var cx = ele.innerHTML;
                    if(inx<4){
                        ele.innerHTML=cx.replace(/月/g,"季度");
                    }else{
                        ele.style.display="none";
                    }
                });
            });
        }
    }

    function isNull(s){
        if(s==null||typeof(s)=="undefined"||s=="")return true;
        return false;
    }

    $(".daochu_btn").click(function(){
        var jidu = $("#test1").attr("startDate");
        if (!jidu){
            layer.open({
                type: 1,
                title: "温馨提示",
                area: '300px',
                shade: 0.8,
                id: 'LAY_layuipro',
                btn: ['确定'],
                moveType: 1,
                content: '<div style="padding:15px 20px; text-align:justify; line-height: 22px; text-indent:2em;border-bottom:1px solid #e2e2e2;"><p class="layui-red">请选择季度</p> </div>',
                success: function(layero){
                    var btn = layero.find('.layui-layer-btn');
                    btn.css('text-align', 'center');
                }
            });
            return;
        }
        index = layer.msg('文件导出中，请稍候',{icon: 16,time:false,shade:0.8});
        $(function () {
            $.ajax({
                type : "GET",
                url :  localhostPaht + '/supplier/exportDxSunHao?jidu='+ jidu +'&gysid=' + username,
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
        var jidu = $("#test1").attr("startDate");
        if (!jidu){
            layer.open({
                type: 1,
                title: "温馨提示",
                area: '300px',
                shade: 0.8,
                id: 'LAY_layuipro',
                btn: ['确定'],
                moveType: 1,
                content: '<div style="padding:15px 20px; text-align:justify; line-height: 22px; text-indent:2em;border-bottom:1px solid #e2e2e2;"><p class="layui-red">请选择季度</p> </div>',
                success: function(layero){
                    var btn = layero.find('.layui-layer-btn');
                    btn.css('text-align', 'center');
                }
            });
        }else{
            //执行重载
            index = layer.msg('加载中，请稍候',{icon: 16,time:false,shade:0.8});
            tableIns.reload({
                url : localhostPaht + '/supplier/dxSunHao',
                method:'GET',
                where: {
                    jidu:jidu,
                    gysid: username
                }
            });
        }
    });

})


