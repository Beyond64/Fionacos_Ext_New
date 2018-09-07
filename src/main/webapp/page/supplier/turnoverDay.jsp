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
    <title>周转天查询</title>
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
    <table id="newsList" lay-filter="newsList"></table>
    <!--审核状态-->
    <%--<script type="text/html" id="state">--%>
        <%--{{#  if(d.turnoverDay > d.biaoZhunGaoTurnoverDay){ }}--%>
        <%--<span class="layui-red" id={{d.cblitm}} onmouseover="showtips(1,{{d.cblitm}})">{{ d.turnoverDay }}</span>--%>
        <%--{{#  } else if(d.turnoverDay < d.biaoZhunDiTurnoverDay){ }}--%>
        <%--<span class="layui-red" id={{d.cblitm}} onmouseover="showtips(2,{{d.cblitm}})">{{ d.turnoverDay }}</span>--%>
        <%--{{#  } else { }}--%>
        <%--<span class="layui-blue">{{ d.turnoverDay }}</span>--%>
        <%--{{#  }}}--%>
    <%--</script>--%>
    <div>
        <span style="font-size: 15px">周转天颜色描述:</span><br>
        <span style="font-size: 15px">&nbsp;&nbsp;&nbsp;&nbsp;<span style="background-color: #00FF7F">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>绿色：周转天正常</span><br>
        <span style="font-size: 15px">&nbsp;&nbsp;&nbsp;&nbsp;<span style="background-color: #FF4500">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>红色：周转天偏高，业绩表现不佳，须提高销售业绩</span><br>
        <span style="font-size: 15px">&nbsp;&nbsp;&nbsp;&nbsp;<span style="background-color: #FFFF00">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>黄色：周转天偏低，即将有缺货风险:</span>
    </div>
    <script type="text/html" id="state">
        {{#  if(d.turnoverDay > d.biaoZhunGaoTurnoverDay){ }}
        <div style="background-color: #FF4500;width: 30px;height: 30px" id={{d.cblitm}} onmouseover="showtips(1,{{d.cblitm}})">&nbsp;</div>
        {{#  } else if(d.turnoverDay < d.biaoZhunDiTurnoverDay){ }}
        <div style="background-color: #FFFF00;width: 30px;height: 30px" id={{d.cblitm}} onmouseover="showtips(2,{{d.cblitm}})">&nbsp;</div>
        {{#  } else { }}
        <div style="background-color: #00FF7F;width: 30px;height: 30px" id={{d.cblitm}} onmouseover="showtips(3,{{d.cblitm}})">&nbsp;</div>
        {{#  }}}
    </script>

    <script>
        function showtips(i,id) {
            var msg;
            if(i == 1){
                msg = "周转天偏高，业绩表现不佳，须提高销售业绩";
            }else if(i == 2){
                msg = "周转天偏低，即将有缺货风险"
            }else{
                msg = "周转天正常"
            }
            layer.tips(msg, '#'+id, {
                tips: [2, '#0A0A0A'],
                time:3000
            });
        }
    </script>

</form>
<script type="text/javascript" src="../../layui/layui.js"></script>
<script type="text/javascript" src="turnoverDay.js"></script>
</body>
</html>
