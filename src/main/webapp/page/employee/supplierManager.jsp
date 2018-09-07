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
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline" style="margin-right: 8px">
                <a class="layui-btn layui-btn-normal addNews_btn">添加账户</a>
            </div>
            <div class="layui-inline" style="margin-right: 8px">
            <a class="layui-btn layui-btn-normal shuxin_btn">刷新</a>
            </div>
            <div class="layui-inline" style="margin-right: 8px">
                <a class="layui-btn layui-btn-warm layui-btn-normal SynSupplierBankInfo_btn">更新银行账号</a>
            </div>
            <div class="layui-inline" style="margin-right: 8px">
                <a class="layui-btn layui-btn-normal export_btn" href="${pageContext.request.contextPath}/export/supplier" download="Suplier.xls">导出</a>
            </div>
        </form>
    </blockquote>
    <table id="newsList" lay-filter="newsList"></table>
    <!--审核状态-->
    <script type="text/html" id="state">
        {{#  if(d.state == 1){ }}
        <span class="layui-blue">正常</span>
        {{#  } else if(d.state == 0){ }}
        <span class="layui-red">冻结</span>
        {{#  } else { }}
        未知
        {{#  }}}
    </script>

    <!--操作-->
    <script type="text/html" id="newsListBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">冻结</a>
    </script>
</form>
<script type="text/javascript" src="../../layui/layui.js"></script>
<script type="text/javascript" src="supplierManager.js"></script>
</body>
</html>
