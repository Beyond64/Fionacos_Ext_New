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
<form class="layui-form" >
    <input type="hidden" id="myPathValue" value="${pageContext.request.contextPath}" />
    <%--<blockquote class="layui-elem-quote quoteBox">--%>
        <%--<form class="layui-form">--%>
            <%--<div class="layui-inline">--%>
                <%--<label class="layui-form-label">分布场所</label>--%>
                <%--<div class="layui-input-inline layui-form">--%>
                    <%--<select id="storeName" name="storeName" class="layui-form" lay-verify="required" lay-search="">--%>
                    <%--</select>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</form>--%>
    <%--</blockquote>--%>
    <table id="newsList" lay-filter="newsList"></table>
</form>

<script type="text/html" id="tmuorge1">
    {{#  if(d.tmuorge1){ }}
    <span>{{d.tmuorge1 / 1000}}</span>
    {{#  } else { }}
    <span></span>
    {{#  }}}
</script>

<script type="text/html" id="tmuorg">
    {{#  if(d.tmuorg){ }}
    <span class="layui-blue">{{d.tmuorg / 1000}}</span>
    {{#  } else if(d.tmuorg == 0){ }}
    <span class="layui-blue">0</span>
    {{#  } else { }}
    <span class="layui-red"></span>
    {{#  }}}
</script>
<script type="text/javascript" src="../../layui/layui.js"></script>
<script type="text/javascript" src="orderInfo.js"></script>
</body>
</html>
