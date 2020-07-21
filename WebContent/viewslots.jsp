<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="com.sd.hib.Slot"%>
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

    <table border="1">
    		<thead>
    			<tr>
    				<th>#</th>
    				<th>Slot ID</th>
    				<th>Doctor ID</th>
    				<th>Patient ID</th>
    				<th>Slot Date</th>
    				<th>Slot Time</th>
    				<th>Status</th>
    			</tr>
    		</thead>
    		<tbody>
    			<%
    				int i = 0;
    				List<Slot> list = (List) request.getAttribute("SlotList");
    			%>
    			<%
    				for (Slot u : list) {
    			%>
    			<tr>
    				<td><%=i++%></td>
    				<td><%=u.getSlot_id()%></td>
    				<td><%=u.getDoctor_id()%></td>
    				<td><%=u.getPatient_id()%></td>
    				<td><%=u.getSlot_date()%></td>
    				<td><%=u.getSlot_time()%></td>
    				<td><%=u.getSlot_status()%></td>
    			</tr>
    			<%
    				}
    			%>
    		</tbody>
    	</table>
    	
</center>
</body>
</html>