<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.colin.tool.ParseJsonParam"%>
<%@page import="com.colin.entity.Params"%>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.colin.entity.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User) request.getSession().getAttribute("user");
String un  = user.getUsername();
if(un==null){  
   String url  = basePath+"/user/logout";
   response.sendRedirect(url);  
   return;
}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>供应商条码查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script src='${pageContext.request.contextPath}/js/jquery-2.1.1.min.js'></script>
	<style type="text/css">
	        body{
	            background: white;
	            margin: 0 auto;
	            overflow: hidden;
	        }
	        .head{
		       width: 100%;
		       height: 35px;
		       background: #2A343E;
		       color: white;
		       font-size:1.15em;
		       line-height: 30px;
		       font-weight: bold;
		       padding: 2px;
		       border: 1px solid gray;
		       box-sizing: border-box;
		     }
		     #main{
		        width: 100%;
		        height: 90%;
		        overflow: auto;
		     }
				       
		     table {
					text-align: center;
					width: 100%;
					border-width: 0px 1px 1px 0px;
					border: 1px solid #add9c0;
					box-sizing: border-box;
					font-size: 10px; 
				}
			td {
					border-width: 0px 1px 1px 0px;
					border: 1px solid #add9c0;
					box-sizing: border-box; 
				}
		    tr:hover{
		        color: #2365ff;
		        cursor: pointer;
		    }
		    #hide_div{
		       width: 100%;height: 98%;z-index: 99;position:absolute;filter: alpha(opacity=10);opacity: 0.5; background: white;
               cursor: pointer; 
		    }
		    #loading_div{
		       margin-left: 38%;
		       margin-top: 15%;
		    }
	</style>
	<script type="text/javascript"> 
	</script>

  </head>
  
  <body>
       <div id="hide_div"> 
                 <div id="loading_div">
                 <img style="width: 40px;height: 40px" src="images/loading.gif"></img>
                 </div>
       </div>
       <%--<div class="head">供应商商品查询</div> --%>
       <div id="main">
              <div id="data_tab">
                     <% 
                    ParseJsonParam pjp = new ParseJsonParam();
					String url = "http://183.11.241.154:8081/Ext_interface/ext/gongyingshang/getext_json_1.jsp?gongyingshang="+un;
					//System.out.println(url+">>>");
			        String json = pjp.loadJSON(url);
			        List<Params> list=JSON.parseArray(json,Params.class);
			        String tablestr ="";
			        int countst =0;
			             tablestr ="<table>"+
						                         "<tr>"+
							                     "<td>供应商编码</td>"+
							                     "<td>供应商名称</td>"+
							                     "<td>国际条码</td>"+
							                     "<td>条码说明</td>"+
							                     "<td>代购销</td>"+
							                    "</tr>";
			        
			        
			        
			        for (Params params : list) { 
			                                                       tablestr+="<tr>";
													               tablestr+="<td>"+params.getParam1()+"</td>";
													               tablestr+="<td>"+params.getParam2()+"</td>";
													               tablestr+="<td>"+params.getParam3()+"</td>";
													               tablestr+="<td>"+params.getParam4()+"</td>";
													               tablestr+="<td>"+params.getParam5()+"</td>"; 
													               tablestr+="</tr>"; 
													               countst++;  
			        }
			         tablestr+="<tr><td colspan=8>当前数量:<font color=green>"+countst+"</font></td></tr>";
													          tablestr +="</table>";

                     out.write(tablestr);
                     out.write("<script type=\"text/javascript\"> $(\"#hide_div\").hide();</script>");	
                
                 %>
              </div>
                
			       
        
         </div>
  </body>
</html>
