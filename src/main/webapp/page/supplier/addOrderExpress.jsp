<%--
  Created by IntelliJ IDEA.
  User: Colin
  Date: 2018/7/19
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>录入快递单号</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="../../layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="./../../css/public.css" media="all" />

</head>
<body class="childrenBody">
<input type="hidden" id="myPathValue" value="${pageContext.request.contextPath}" />
<form class="layui-form" action="">
    <div class="layui-form-item">
        <input type="text" disabled id="orderNo" class="layui-input layui-hide orderNo">
        <label class="layui-form-label">物流名称</label>
        <div class="layui-input-block">
            <input type="text" name="trackingNanme" id="trackingNanme" required  lay-verify="required" placeholder="请输入物流名称" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">物流单号</label>
        <div class="layui-input-block">
            <input type="text" name="trackingNo" id="trackingNo" required lay-verify="required" placeholder="物流单号" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script type="text/javascript" src="../../layui/layui.js"></script>
<script type="text/javascript" src="addOrderExpress.js"></script>
</body>
</html>
