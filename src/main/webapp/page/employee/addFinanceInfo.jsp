<%@ page import="com.colin.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Colin
  Date: 2018/7/19
  Time: 14:46
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
    <title>添加供应商账户</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="../../layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="../../css/public.css" media="all" />
    <script>
    </script>
</head>
<body class="childrenBody">
<form class="layui-form layui-row">
    <input type="hidden" id="myPathValue" value="${pageContext.request.contextPath}" />
    <div class="layui-col-md6 layui-col-xs14">
        <input type="text"   class="layui-input layui-hide objectId" >
        <div class="layui-form-item">
            <label class="layui-form-label">姓名:</label>
            <div class="layui-input-block">
                <input type="text"  placeholder="请输入姓名" lay-verify="required"  class="layui-input name">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">职位:</label>
            <div class="layui-input-block">
                <input type="text" , placeholder="请输入职位"  class="layui-input position">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">电话:</label>
            <div class="layui-input-block">
                <input type="text" placeholder="请输入联系电话" class="layui-input phone">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱:</label>
            <div class="layui-input-block">
                <input type="text"  placeholder="请输入联系邮箱"  class="layui-input email">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">地址:</label>
            <div class="layui-input-block">
                <input type="text"  placeholder="请输入联系地址"  class="layui-input address">
            </div>
        </div>


        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="addUser">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript" src="../../layui/layui.js"></script>
<script type="text/javascript" src="addFinanceInfo.js"></script>
<script type="text/javascript" src="../../js/cacheUserInfo.js"></script>
</body>
</html>
