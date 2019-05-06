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
    <%--<script src='${pageContext.request.contextPath}/js/jquery-2.1.1.min.js'></script>--%>
    <%--<script src="${pageContext.request.contextPath}/js/laydate.js"></script>--%>
</head>
<body class="childrenBody">
<form class="layui-form" >
    <input type="hidden" id="myPathValue" value="${pageContext.request.contextPath}" />
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline" style="margin-right: 8px">
                <label class="layui-form-label">查询日期</label>
                <div class="layui-input-inline">
                    <input type="text" style="margin-top: 1px;height: 30px" placeholder="请选择开始时间" id="SatrTime">—
                    <input type="text" style="margin-top: 1px;height: 30px" placeholder="请选择结束时间" id="EndTime">
                    <input type="checkbox" name="like1" lay-skin="primary" title="已开凭证" value="1" >
                    <input type="checkbox" name="like1" lay-skin="primary" title="未开凭证" value="0" checked>
                    <input type="text" style="display: none ;margin-top: 1px;height: 30px" placeholder="公司名称" id="companyId">
                    <input type="text" style="display: none ;margin-top: 1px;height: 30px" placeholder="供应商编码" id="supplierId">
                </div>
                <div class="layui-btn findOnTime" data-type="reload" id="findOnTime"  lay-filter="rechar_btn">查询</div>
            </div>
            <div class="layui-inline" style="display:none;margin-right: 8px" id="export">
                <a class="layui-btn layui-btn-normal daochu_btn">导出</a>
            </div>
        </form>
    </blockquote>
    <table id="newsList" lay-filter="newsList"></table>
</form>
<script type="text/javascript" src="../../layui/layui.js"></script>
<script type="text/javascript" src="shoopmouth.js"></script>
</body>
</html>
