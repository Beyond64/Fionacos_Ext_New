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
    <title>供应商订单管理</title>
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
            <%--<div class="layui-inline" style="margin-right: 8px">--%>
                <%--<a class="layui-btn layui-btn-normal addOrder_btn">添加订单</a>--%>
            <%--</div>--%>
            <div class="layui-inline" style="margin-right: 8px">
                <label class="layui-form-label">订单状态</label>
                <div class="layui-input-block">
                    <select name="city" lay-verify="required" lay-filter="state" name="state">
                        <option value="" selected="selected">所有</option>
                        <option value="N">待审批</option>
                        <option value="Y">待发货</option>
                        <option value="F">已完成</option>
                        <option value="S">已取消</option>
                    </select>
                </div>
            </div>
            <%--<div class="layui-inline" style="margin-right: 8px">--%>
                <%--<a class="layui-btn layui-btn-normal addBuHuoCanShu">添加补货参数</a>--%>
            <%--</div>--%>
        </form>
    </blockquote>
    <table id="newsList" lay-filter="newsList"></table>

    <%--<div>--%>
        <%--<span style="font-size: 15px">&nbsp;&nbsp;&nbsp;&nbsp;</span><br>--%>
        <%--<span style="font-size: 12px">已作废：补货专员拒绝本次需求</span><br>--%>
        <%--<span style="font-size: 12px">已审核：补货专员已审核通过,可安排发货</span><br>--%>
        <%--<span style="font-size: 12px">已发货：货品已发往妍丽中国成品仓库/门店</span>--%>
    <%--</div>--%>
    <!--审核状态-->
    <script type="text/html" id="state">
        {{#  if(d.pdlttr == 230 && d.pdnxtr == 240){ }}
        <span>待审批</span>
        {{#  } else if(d.pdlttr == 240 && d.pdnxtr == 400){ }}
        <span class="layui-red">待发货</span>
        {{#  } else if(d.pdlttr == 400 && d.pdnxtr == 999){ }}
        <span class="layui-blue">已完成</span>
        {{#  } else if(d.pdlttr == 980 && d.pdnxtr == 999){ }}
        <span class="layui-yellow">已取消</span>
        {{#  }}}
    </script>

    <!--操作-->
    <script type="text/html" id="newsListBar">
        {{#  if(d.pdlttr == 240 && d.pdnxtr == 400){ }}
        <a class="layui-btn layui-btn-xs" lay-event="findInfoButton">查看</a>
        <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="fahuo">发货</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="daochu">导出</a>
        {{#  } else { }}
        <a class="layui-btn layui-btn-xs" lay-event="findInfoButton">查看</a>
        <a class="layui-btn layui-btn-xs layui-btn-disabled">发货</a>
        <a class="layui-btn layui-btn-xs" lay-event="daochu">导出</a>
        {{#  }}}
    </script>
</form>
<script type="text/javascript" src="../../layui/layui.js"></script>
<script type="text/javascript" src="jdeOrderManager.js"></script>
</body>
</html>
