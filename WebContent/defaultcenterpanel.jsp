<%@page import="java.util.List"%>
<%@page import="com.sd.servlet.LoginService"%>
<%@page import="java.util.Date"%>
<%@page import="com.sd.hib.User"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
     <div id="container">
             <%=new Date()%></br>
             <%
                  User user = (User) session.getAttribute("user");
             	  if(user!=null){
             %>     
             <b>Welcome <%= user.getFirst_name() + " " + user.getLast_name()%></b>     
             <br/>
             <%} %>
         </p>
     </div>
</center>
</body>
</html>