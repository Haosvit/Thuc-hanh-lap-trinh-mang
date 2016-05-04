<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 	pageEncoding="UTF-8" %>

<%@ include file="DatabaseAccess.jsp" %>

<%
String username = request.getParameter("username");
String password = request.getParameter("password");

String queryStr = "Select * from admin where username = '" + username + "' and password = '" + password + "';";

ResultSet rs = st.executeQuery(queryStr);
boolean isLoginOK = rs.next();

if (isLoginOK) {
	request.getRequestDispatcher("trang_chu.jsp").include(request, response);
	session.setAttribute("username", username);
}
else {
	out.println("Login fail! <a href=\"login.jsp\">Back</a>");
}
%>