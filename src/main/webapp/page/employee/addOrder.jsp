<%@ page import="com.colin.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Colin
  Date: 2018/7/19
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%
    User user = (User) request.getSession().getAttribute("user");
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加订单</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="./../../css/public.css" media="all" />
    <script src='${pageContext.request.contextPath}/js/jquery-2.1.1.min.js'></script>
    <script src="${pageContext.request.contextPath}/js/laydate.js"></script>
    <script>
        $(function () {
            $.ajax({
                type : "get",
                url : "${pageContext.request.contextPath}/order/storeList",
                success : function(data, status) {
                    if(status == "success"){
                        $.each(data, function(index, item) {
                            if(item.storeId == 100010001){
                                $("#storeName").append(
                                    "<option selected='selected' value="+item.storeId+">" + item.storeName+ "</option>");
                            }else{
                                $("#storeName").append(
                                    "<option value="+item.storeId+">" + item.storeName+ "</option>");
                            }
                        });
                    }
                },
            });
        })

    </script>
</head>
<body>
<input type="hidden" id="myPathValue" value="${pageContext.request.contextPath}" />
<blockquote class="layui-elem-quote quoteBox">
    <form class="layui-form">
        <div class="layui-inline">
            <label class="layui-form-label">分布场所</label>
            <div class="layui-input-inline">
                <select id="storeName" name="storeName" lay-verify="required" lay-search="">
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">计划提货</label>
            <div class="layui-input-inline">
                <input type="text" id="test1" style="margin-top: 2px;height: 30px">
            </div>
        </div>
    </form>
</blockquote>
<table class="layui-table" lay-data="{url:'${pageContext.request.contextPath}/order/barcodeList?userName=20000063', id:'test3'}" lay-filter="test3">
    <thead>
    <tr>
        <th lay-data="{type:'checkbox'}">ID</th>
        <th lay-data="{field:'param3', width:230, sort: true}">国际条码</th>
        <th lay-data="{field:'param4', width:350}">条码说明</th>
        <th lay-data="{field:'barCodeCount', edit: 'text', minWidth: 150,sort: true}">申请订购数量</th>
        <th lay-data="{field:'ApprovalBarCodeCount', minWidth: 150,sort: true}">审批后订购数量</th>
    </tr>
    </thead>
</table>


<script type="text/javascript" src="../../layui/layui.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        var lastOperator = JSON.parse(localStorage.getItem("user")).username;
        //监听单元格编辑
        table.on('edit(test3)', function(obj){
            if(obj.field == "barCodeCount"){
                $.ajax({
                    type: "GET",
                    url: "${pageContext.request.contextPath}/order/FindBarCodeCount",
                    data:  {
                        "mcu" : $("#storeName").val(),
                        "litm": obj.data.param3,
                    },
                    dataType: "json",
                    success: function(data){
                        if(data == null || data == 0){
                            layer.msg("网络异常!",{
                                time:1500
                            });
                        }else{
                           if(obj.value % data != 0){
                               layer.msg("订购最小倍数为" + data,{
                                   time:1500
                               });
                           }
                        }
                    }
                });
            }
            // layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
        });
    });

    // var start = laydate.render({
    //     elem: '#test1',
    //     min: '1900-1-1 00:00:00',
    //     value: getNowFormatDate(),
    //     trigger: 'click', //采用click弹出
    //     done: function (value, date, endDate) {
    //         startDate = value;
    //     }
    // })

    laydate.render({
        elem: '#test1'
        ,value: getNowFormatDate()
    });


    //获取当前时间
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        return currentdate;
    }
</script>

</body>
</html>
