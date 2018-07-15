<%@page import="java.io.Console"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.*" %>
<%@ page import="utils.*" %>
<%@ page import="com.sina.cloudstorage.services.scs.model.ObjectMetadata" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title >查询</title>
    </head>
    <body>
    
    	<%
    		SinaStoreSDK sss = new SinaStoreSDK();
    		String date = request.getParameter("date");
    		String path = "lab324data/records/record"+date+".txt";
    	%>
    
    	<div class="container">
    	
    		<img src="<%out.print(sss.generateUrl("music-store", "img/s07.jpg", 60)); %>" alt="" class="image"/>
    	
	    	<%     
	    	   	String url = sss.generateUrl("music-store", path, 5);
	    	   	HttpUtil hu = new HttpUtil();
	    	   	String res = hu.request(url,false);
	    	   	out.println(res);
	        %>
	        <div>
	        </div>
	        
	        <% 
	    	   	out.println("<form style='margin-top:50px' action='/FLSTSWeb/download?date="+date+"' method='post'>");
	    	    out.println("<input class='btn-download' type='submit' name='date' value='下载'/>");
	    	    out.println("</form>");
	        %>
		</div>
    </body>

	<style>
	.container{
		display:flex;
		flex-direction:column;
		justify-content:center;
		align-items:flex-start;
	}
	.image{
		width:200px;
		margin-top:30px;
		margin-bottom:30px;
	}
	.btn-download{
		width:100px;
		height:100px;
		size:50rem;
	}
	</style>
</html>
