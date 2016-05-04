<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@ include file="header.jsp"%>
<%@ include file="DatabaseAccess.jsp"%>

<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");

String majorId = request.getParameter("majorId");
String majorName = request.getParameter("majorName");
String updateStr = "update phongban set tenpb ='" + majorName + "' where mapb ='" + majorId + "'";
st.executeUpdate(updateStr);
response.sendRedirect("xem_phong_ban.jsp");
%>