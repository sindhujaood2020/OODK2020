<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
String pageName = "";

Object actionPerformed = request.getAttribute("pagename");

if(actionPerformed!=null)
	pageName = actionPerformed.toString();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Center Panel</title>
</head>
<body>

<jsp:include page='<%=pageName%>'></jsp:include>
 
</body>
</html>