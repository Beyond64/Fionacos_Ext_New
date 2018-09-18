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
    <title>供应商管理</title>
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
<form class="layui-form" >
    <input type="hidden" id="myPathValue" value="${pageContext.request.contextPath}" />
    <blockquote class="layui-elem-quote quoteBox" style="display: none" >
        <form class="layui-form">
            <div class="layui-inline" style="margin-right: 8px">
                <a class="layui-btn layui-btn-normal addNews_btn" style="display: none" >添加财务信息</a>
            </div>
            <div class="layui-upload layui-inline">
                <button type="button" class="layui-btn layui-btn-warm" id="test8" style="display: none" >上传数据</button>
                <button type="button" class="layui-btn" id="test9" style="display: none">开始上传</button>
            </div>
        </form>

    </blockquote>
    <div>财务联系方式</div>
    <div><hr style="height:1px;border:none;border-top:1px solid #555555;" /> </div>
    <table id="newsList" lay-filter="newsList"></table>
    <div style="margin-top: 55px">开票资料信息</div>
    <div><hr style="height:1px;border:none;border-top:1px solid #555555;" /> </div>
    <table id="downList" lay-filter="downList"></table>

    <!--操作-->
    <script type="text/html" id="newsListBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>


    <!--操作-->
    <script type="text/html" id="downListBar">
        <a class="layui-btn layui-btn-xs" lay-event="down">下载</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>
</form>
<script type="text/javascript" src="../../layui/layui.js"></script>
<script type="text/javascript" src="financeInfo.js"></script>
</body>
</html>
