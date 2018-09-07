<%@ page import="com.colin.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Colin
  Date: 2018/7/19
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%
    User user = (User) request.getSession().getAttribute("user");
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>个人资料</title>
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
<form class="layui-form layui-row">
    <div class="layui-col-md4 layui-col-xs12 user_right">
        <input type="hidden" id="myPathValue" value="${pageContext.request.contextPath}" />
        <%--<div class="layui-upload-list">--%>
            <%--<img class="layui-upload-img businessLicenseBtn userAvatar" alt="待上传" onclick="myShowImg(this)" src="<%=user.getBusinessLicense() == null ? "" : user.getBusinessLicense()%>" id="businessLicense">--%>
        <%--</div>--%>
        <%--&lt;%&ndash;<button type="button" class="layui-btn layui-btn-primary businessLicenseBtn"><i class="layui-icon">&#xe67c;</i> 掐指一算，我要换一个头像了</button>&ndash;%&gt;--%>
        <%--<span id="businessLicenseResult"></span>--%>
        <%--<p>营业执照</p>--%>

        <%--<div class="layui-upload-list">--%>
            <%--<img class="layui-upload-img taxCertificateBtn userAvatar" alt="待上传" onclick="myShowImg(this)" src="<%=user.getTaxCertificate() == null ? "" : user.getTaxCertificate()%>" id="taxCertificate">--%>
        <%--</div>--%>
        <%--<span id="taxCertificateResult"></span>--%>
        <%--&lt;%&ndash;<button type="button" class="layui-btn layui-btn-primary taxCertificateBtn"><i class="layui-icon">&#xe67c;</i> 掐指一算，我要换一个头像了</button>&ndash;%&gt;--%>
        <%--<p>税务登记证</p>--%>

        <%--<div class="layui-upload-list">--%>
            <%--<img class="layui-upload-img organizationCertificateBtn userAvatar" alt="待上传" onclick="myShowImg(this)" src="<%=user.getOrganizationCertificate() == null ? "" : user.getOrganizationCertificate()%>" id="organizationCertificate">--%>
        <%--</div>--%>
        <%--<span id="organizationCertificateResult"></span>--%>
        <%--&lt;%&ndash;<button type="button" class="layui-btn layui-btn-primary userFaceBtn"><i class="layui-icon">&#xe67c;</i> 掐指一算，我要换一个头像了</button>&ndash;%&gt;--%>
        <%--<p>组织机构代码证</p>--%>

        <%----%>
        <div class="layui-upload-list">
            <img class="layui-upload-img businessLicenseBtn userAvatar" alt="待上传" src="${pageContext.request.contextPath}<%=user.getBusinessLicense() == null ? "" : user.getBusinessLicense()%>" id="businessLicense">
        </div>
        <%--<button type="button" class="layui-btn layui-btn-primary businessLicenseBtn"><i class="layui-icon">&#xe67c;</i> 掐指一算，我要换一个头像了</button>--%>
        <span id="businessLicenseResult"></span>
        <p>营业执照</p>

        <div class="layui-upload-list">
            <img class="layui-upload-img taxCertificateBtn userAvatar" alt="待上传" src="${pageContext.request.contextPath}<%=user.getTaxCertificate() == null ? "" : user.getTaxCertificate()%>" id="taxCertificate">
        </div>
        <span id="taxCertificateResult"></span>
        <%--<button type="button" class="layui-btn layui-btn-primary taxCertificateBtn"><i class="layui-icon">&#xe67c;</i> 掐指一算，我要换一个头像了</button>--%>
        <p>税务登记证</p>

        <div class="layui-upload-list">
            <img class="layui-upload-img organizationCertificateBtn userAvatar" alt="待上传"  src="${pageContext.request.contextPath}<%=user.getOrganizationCertificate() == null ? "" : user.getOrganizationCertificate()%>"  id="organizationCertificate">
        </div>
        <span id="organizationCertificateResult"></span>
        <%--<button type="button" class="layui-btn layui-btn-primary userFaceBtn"><i class="layui-icon">&#xe67c;</i> 掐指一算，我要换一个头像了</button>--%>
        <p>组织机构代码证</p>
        <%----%>

    </div>
    <div class="layui-col-md6 layui-col-xs12">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名:</label>
            <div class="layui-input-block">
                <input type="text" value=<%= user.getNickname()%> disabled class="layui-input layui-disabled">
                <input type="text" value=<%= user.getUserid()%> disabled class="layui-input layui-hide userid">
                <input type="text" value=<%= user.getUsername()%> disabled class="layui-input layui-hide username">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">银行账户:</label>
            <div class="layui-input-block">
                <input type="text" value="<%=user.getBankAccount() == null ? "" : user.getBankAccount()%>" disabled class="layui-input layui-disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">财务联系人:</label>
            <div class="layui-input-block">
                <input type="text" value="<%=user.getFinanceName() == null ? "" : user.getFinanceName()%>", placeholder="请输入姓名"  class="layui-input financeName">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">财务邮箱:</label>
            <div class="layui-input-block">
                <input type="text" value="<%=user.getFinanceEmail() == null ? "" : user.getFinanceEmail()%>", placeholder="请输入邮箱" lay-verify="email" class="layui-input financeEmail">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">财务电话:</label>
            <div class="layui-input-block">
                <input type="tel" value="<%= user.getFinancePhone() == null ? "" : user.getFinancePhone()%>" placeholder="请输入电话"  class="layui-input financePhone">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">业务联系人:</label>
            <div class="layui-input-block">
                <input type="text" value="<%= user.getBusinessName() == null ? "": user.getBusinessName()%>" placeholder="请输入姓名"  class="layui-input businessName">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">联系人电话:</label>
            <div class="layui-input-block">
                <input type="tel" value="<%= user.getBusinessPhone() == null ? "" : user.getBusinessPhone()%>" placeholder="请输入手机号码"  class="layui-input businessPhone">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">退货地址:</label>
            <div class="layui-input-block">
                <input type="tel" value="<%= user.getBackAddress() == null ? "" : user.getBackAddress()%>" placeholder="请输入退货地址"  class="layui-input backAddress">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">退货电话:</label>
            <div class="layui-input-block">
                <input type="tel" value="<%= user.getBackPhone() == null ? "" : user.getBackPhone()%>" placeholder="请输入手机号码"  class="layui-input backPhone">
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
            <label class="layui-form-label">公司邮箱:</label>
            <div class="layui-input-block">
                <input type="text" value="<%= user.getEmail() == null ? "" : user.getEmail()%>" placeholder="请输入邮箱" lay-verify="email" class="layui-input userEmail">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="changeUser">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript" src="../../layui/layui.js"></script>
<script type="text/javascript" src="userInfo.js"></script>
<script type="text/javascript" src="../../js/cacheUserInfo.js"></script>
</body>
</html>
