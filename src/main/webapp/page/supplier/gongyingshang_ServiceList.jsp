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
    <style>
        table td{padding:8px;border:1px solid;}
        table td{font-size: 12px;}
    </style>
</head>
<body class="childrenBody">
<form class="layui-form" >
    <input type="hidden" id="myPathValue" value="${pageContext.request.contextPath}" />
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-upload layui-inline">
                <button type="button" class="layui-btn layui-btn-normal" id="test8">选择文件</button>
                <button type="button" class="layui-btn" id="test9">开始上传</button>
            </div>
            <div class="layui-inline" style="margin-right: 8px">
                <label class="layui-form-label">查询日期</label>
                <div class="layui-input-inline">
                    <input type="text" id="test1" style="margin-top: 2px;height: 30px">
                </div>
                <div class="layui-btn findOnTime" data-type="reload" id="findOnTime"  lay-filter="rechar_btn">查询</div>
            </div>
        </form>
    </blockquote>
    <div id="newsList" hidden>
        <table style="width: 620px; height: auto; border:1px solid;border-collapse:collapse;margin:0 auto">
            <tr>
                <td colspan="4" align="center">妍丽供应链服务费清单</td>
            </tr>
            <tr>
                <td colspan="4"></td>
            </tr>
            <tr>
                <td>客户商编码:</td>
                <td colspan="3"><span id="gysId"></span></td>
            </tr>
            <tr>
                <td>客户名称:</td>
                <td colspan="3"><span id="gysName"></span></td>
            </tr>
            <tr>
                <td>联系邮箱:</td>
                <td colspan="3"><span id="gysEmail"></span></td>
            </tr>
            <tr style="background-color: aquamarine;" id="afterTr">
                <td>单据日期</td>
                <td align="center">说明</td>
                <td align="center">金额</td>
                <td align="center">备注</td>
            </tr>

            <tr>
                <td>应收合计</td>
                <td></td>
                <td align="center"><span id="heji"></span></td>
                <td></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>供应链名称:</td>
                <td colspan="3">妍丽供应链管理(深圳)有限公司</td>
            </tr>
            <tr>
                <td>供应链账户:</td>
                <td colspan="3">40000 2330 9200 7939 29</td>
            </tr>
            <tr>
                <td>供应开户行:</td>
                <td colspan="3">工行深圳市福田支行</td>
            </tr>
            <tr>
                <td colspan="4"></td>
            </tr>
            <tr>
                <td>备注:</td>
                <td colspan="3">付款方式:   <input type="checkbox" disabled="disabled" id="zhijiefukuan" /> 直接付款    <input type="checkbox" disabled="disabled" id="dikohuokuan" /> 抵扣贷款</td>
            </tr>
            <tr>
                <td>备注:</td>
                <td colspan="3" align="right">妍丽供应链管理(深圳)有限公司</td>
            </tr>
        </table>
    </div>



</form>
<script type="text/javascript" src="../../layui/layui.js"></script>
<script type="text/javascript" src="gongyingshang_ServiceList.js"></script>
</body>
</html>
