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
    <%--<div class="layui-col-md3 layui-col-xs12 user_right">--%>
        <%--&lt;%&ndash;<div class="layui-upload-list">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<img class="layui-upload-img layui-circle userFaceBtn userAvatar" id="userFace">&ndash;%&gt;--%>
        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<button type="button" class="layui-btn layui-btn-primary userFaceBtn"><i class="layui-icon">&#xe67c;</i> 掐指一算，我要换一个头像了</button>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<p>由于是纯静态页面，所以只能显示一张随机的图片</p>&ndash;%&gt;--%>
    <%--</div>--%>
    <div class="layui-col-md6 layui-col-xs14">
        <div class="layui-form-item">
            <label class="layui-form-label">供应商码:</label>
            <div class="layui-input-block">
                <input type="text" placeholder="请输入供应商码"   class="layui-input username">
                <input type="text" disabled class="layui-input layui-hide userid">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">供应商名称:</label>
            <div class="layui-input-block">
                <input type="text"  placeholder="请输入供应商名称" lay-verify="required"  class="layui-input nickname">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱地址:</label>
            <div class="layui-input-block">
                <input type="text" , placeholder="请输入邮箱地址"  class="layui-input email">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">业务联系人名称:</label>
            <div class="layui-input-block">
                <input type="tel"  placeholder="请输入业务联系人名称"  class="layui-input businessName">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">业务联系人电话:</label>
            <div class="layui-input-block">
                <input type="text" placeholder="请输入业务联系人电话" class="layui-input businessPhone">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">财务联系人名称:</label>
            <div class="layui-input-block">
                <input type="tel"  placeholder="请输入财务联系人名称"  class="layui-input financeName">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">财务联系人邮箱:</label>
            <div class="layui-input-block">
                <input type="text"  placeholder="请输入财务联系人邮箱"  class="layui-input financeEmail">
            </div>
        </div>
        <%--<div class="layui-form-item userAddress">--%>
            <%--<label class="layui-form-label">公司地址:</label>--%>
            <%--<div class="layui-input-inline">--%>
                <%--<select name="province" lay-filter="province" class="province">--%>
                    <%--<option value="">请选择市</option>--%>
                <%--</select>--%>
            <%--</div>--%>
            <%--<div class="layui-input-inline">--%>
                <%--<select name="city" lay-filter="city" disabled>--%>
                    <%--<option value="">请选择市</option>--%>
                <%--</select>--%>
            <%--</div>--%>
            <%--<div class="layui-input-inline">--%>
                <%--<select name="area" lay-filter="area" disabled>--%>
                    <%--<option value="">请选择县/区</option>--%>
                <%--</select>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="layui-form-item">
            <label class="layui-form-label">财务联系人电话:</label>
            <div class="layui-input-block">
                <input type="text"  placeholder="请输入财务联系人电话"  class="layui-input financePhone">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">退货地址:</label>
            <div class="layui-input-block">
                <input type="tel"  placeholder="请输入退货地址"  class="layui-input backAddress">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">退货电话:</label>
            <div class="layui-input-block">
                <input type="text" placeholder="请输入退货电话" class="layui-input backPhone">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">银行名称:</label>
            <div class="layui-input-block">
                <input type="text"  placeholder="请输入银行名称"  class="layui-input bankName">
                <span style="color: #ab1e1e">银行账户信息如需同步至JDE,请联系管理员</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">银行账户:</label>
            <div class="layui-input-block">
                <input type="text"  placeholder="请输入银行账户"  class="layui-input bankAccount">
                <span style="color: #ab1e1e">银行账户信息如需同步至JDE,请联系管理员</span>
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
<script type="text/javascript" src="addSupplier.js"></script>
<script type="text/javascript" src="../../js/cacheUserInfo.js"></script>
</body>
</html>
