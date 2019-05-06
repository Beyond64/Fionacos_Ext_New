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
    <title>供应商自助下单</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="../../layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="./../../css/public.css" media="all" />
    <style>
        .layui-table-cell{
            height:20px;
            line-height: 20px;
        }
    </style>

</head>
<body class="childrenBody">
<div hidden>
    <a href="javascript:;" id="gotoOrderPage" data-url="page/supplier/orderManager.jsp"><i class="seraph icon-ziliao" data-icon="icon-ziliao"></i><cite>订单管理</cite></a>
</div>
<form class="layui-form" >
    <input type="hidden" id="myPathValue" value="${pageContext.request.contextPath}" />
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <label class="layui-form-label">送货至</label>
                <div class="layui-input-inline layui-form">
                    <select id="storeName" name="storeName" class="layui-form" lay-verify="required" lay-search="">
                    </select>
                </div>
            </div>
            <div class="layui-inline" style="margin-right: 8px">
                <a class="layui-btn layui-btn-warm downMuban">下载模板</a>
            </div>
            <div class="layui-inline" style="margin-right: 8px">
                <button type="button" class="layui-btn layui-btn-normal" id="test8">选择文件</button>
                <button type="button" class="layui-btn" id="test9">开始上传</button>
            </div>
            <%--<div class="layui-inline">--%>
                <%--<label class="layui-form-label">计划提货</label>--%>
                <%--<div class="layui-input-inline">--%>
                    <%--<input type="text" id="test1" style="margin-top: 2px;height: 30px">--%>
                <%--</div>--%>
            <%--</div>--%>
        </form>
    </blockquote>
    <table id="newsList" lay-filter="newsList"></table>
    <div STYLE="width:200px;margin:0 auto;">
        <button class="layui-btn" ID="oderSubmit">提交</button>
    </div>
</form>
<script type="text/javascript" src="../../layui/layui.js"></script>
<script type="text/javascript" src="addOrderNew.js"></script>
</body>
</html>
