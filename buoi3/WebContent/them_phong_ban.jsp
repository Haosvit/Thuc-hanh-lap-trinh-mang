<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="DatabaseAccess.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	String majorName = request.getParameter("majorName");

	String queryStr = "Insert into phongban (tenpb) values ('" + majorName + "')";
	st.executeUpdate(queryStr);
	
	request.getRequestDispatcher("xem_phong_ban.jsp").include(request, response);
%>