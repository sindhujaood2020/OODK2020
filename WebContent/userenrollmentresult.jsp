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
<title>User Enrollment response</title>
</head>
<body>
<center>
     <h1><%=request.getAttribute("message")%></h1>
     <a href="loginpage.jsp">Click here</a> to login with your credentials.
</center>
</body>
</html>