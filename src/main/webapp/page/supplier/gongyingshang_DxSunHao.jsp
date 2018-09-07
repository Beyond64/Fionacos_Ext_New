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
                <%--<label class="layui-form-label">日期范围</label>--%>
                <%--<div class="layui-input-inline">--%>
                    <%--<input type="text" id="test1" style="margin-top: 2px;height: 30px">--%>
                <%--</div>--%>
                <div class="layui-input-inline">
                    <input type="text" style="margin-top: 2px;height: 30px" placeholder="请选择季度" id="test1">
                </div>
            <div class="layui-btn findOnTime" data-type="reload" id="findOnTime"  lay-filter="rechar_btn">查询</div>
            </div>
            <div class="layui-inline" style="margin-right: 8px">
                <a class="layui-btn layui-btn-normal daochu_btn">导出</a>
            </div>
        </form>
    </blockquote>
    <table id="newsList" lay-filter="newsList"></table>
    <!--处理小数-->
    <script type="text/html" id="buhanshuijine">
        {{#  if(d.buhanshuijine){ }}
               {{ Math.round(d.buhanshuijine * 100) / 100}}
        {{#  } else { }}
        0.00
        {{#  }}}
    </script>
    <script type="text/html" id="hanshuijine">
        {{#  if(d.hanshuijine){ }}
        {{ Math.round(d.hanshuijine * 100) / 100}}
        {{#  } else { }}
        0.00
        {{#  }}}
    </script>
    <script type="text/html" id="gezichengdan">
        {{#  if(d.gezichengdan){ }}
        {{ Math.round(d.gezichengdan * 100) / 100}}
        {{#  } else { }}
        0.00
        {{#  }}}
    </script>


</form>
<script type="text/javascript" src="../../layui/layui.js"></script>
<script type="text/javascript" src="gongyingshang_DxSunHao.js"></script>
</body>
</html>
