<%--
  Created by IntelliJ IDEA.
  User: adds
  Date: 2018/7/24
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>文章列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="../../layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="../../css/public.css" media="all" />
</head>
<body class="childrenBody">
<form class="layui-form">
    <input type="hidden" id="myPathValue" value="${pageContext.request.contextPath}" />
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <a class="layui-btn layui-btn-normal addNews_btn">添加文章</a>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn">批量删除</a>
            </div>
        </form>
    </blockquote>
    <table id="newsList" lay-filter="newsList"></table>
    <!--审核状态-->
    <script type="text/html" id="newsStatus">
        {{#  if(d.isEmail == 1){ }}
        <span class="layui-blue">发送</span>
        {{#  } else if(d.isEmail == 0){ }}
        <span class="layui-red">不发送</span>
        {{#  } else { }}
        未知状态
        {{#  }}}
    </script>

    <!--审核状态-->
    <script type="text/html" id="emailStatus">
        {{#  if(d.emailStatus == 0){ }}
        <span class="layui-red">未发送</span>
        {{#  } else if(d.emailStatus == 1){ }}
        <span class="layui-blue">正在发送</span>
        {{#  } else if(d.emailStatus == 2){ }}
        <span class="layui-blue">已发送</span>
        {{#  } else { }}
        未知状态
        {{#  }}}
    </script>

    <script type="text/html" id="releaseStatus">
        {{#  if(d.releaseStatus == 1){ }}
        <span class="layui-blue">已发表</span>
        {{#  } else if(d.releaseStatus == 0){ }}
        <span class="layui-red">草稿</span>
        {{#  } else { }}
        未知状态
        {{#  }}}
    </script>

    <!--操作-->
    <script type="text/html" id="newsListBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="email">邮件</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>
</form>
<script type="text/javascript" src="../../layui/layui.js"></script>
<script type="text/javascript" src="newsList.js"></script>
</body>
</html>
