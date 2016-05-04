<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="DatabaseAccess.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	String fullName = request.getParameter("fullName");
	String dob = request.getParameter("dob");
	int majorId = Integer.parseInt(request.getParameter("majorId"));
	String address = request.getParameter("address");
	String note = request.getParameter("note");

	String queryStr = "Insert into nhanvien(hoten, ngaysinh, mapb, diachi, ghichu) " 
		+ "values ('" + fullName + "', '" + dob + "', " 
		+ majorId + ", '" + address + "', '" + note + "')";
	st.executeUpdate(queryStr);
	
	request.getRequestDispatcher("xem_nhan_vien.jsp").include(request, response);
%>