<%@page import="java.nio.ByteBuffer"%>
<%@page import="java.nio.charset.Charset"%>
<%@page import="java.nio.charset.CharsetEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.ResultSet"%>
<%@ include file="DatabaseAccess.jsp"%>
<%@ include file="header.jsp" %>
<% 
String searchType = request.getParameter("searchType");
String searchBy = request.getParameter("searchBy");
String searchContent = request.getParameter("searchContent");
searchContent = new String( searchContent.getBytes("ISO-8859-1"), "UTF-8");
String queryStr = "";
ResultSet majorRS = null, rs = null;
switch (searchType) {
case "staff":
	switch (searchBy) {
	case "name":
		queryStr = "select *, tenpb from nhanvien, phongban where hoten like '%" + searchContent + "%' and nhanvien.mapb = phongban.mapb";
		break;
	case "id":
		queryStr = "select *, tenpb from nhanvien, phongban where maso=" + searchContent + " and nhanvien.mapb = phongban.mapb";
		break;
	}

	rs = st.executeQuery(queryStr); 
	break;
case "major":
	switch (searchBy) {
	case "name":
		queryStr = "select * from phongban where tenpb like '%" + searchContent + "%'";
		break;
	case "id":
		queryStr = "select * from phongban where mapb=" + searchContent;
		break;
	}
	majorRS = st.executeQuery(queryStr);
	break;
}

%>
<%@ include file="header.jsp"%>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>

<% if ("staff".equals(searchType)) {%>
<div class="table-responsive">
	<table class="table table-hover table-bordered table-striped" align="center">
	<tr>
		<th>Mã số</th>
		<th>Họ tên</th>
		<th>Ngày sinh</th>
		<th>Phòng ban</th>
		<th>Địa chỉ</th>
		<th>Ghi chú</th>
	</tr>

	<% while (rs.next()) { 
			String staffId = rs.getString("maso"); 
			String dob = new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("ngaysinh"));%>
	<tr>
		<td><%= staffId %></td>
		<td><%= rs.getString("hoten") %></td>
		<td><%= dob %></td>
		<td><%= rs.getString("tenpb") %></td>
		<td><%= rs.getString("diachi") %></td>
		<td><%= rs.getString("ghichu") %></td>
	</tr>
	<% } %>
</table>
</div>
<%} 
	else {%>
<div class="table-responsive">
	<table class="table table-hover table-bordered table-striped" align="center">
	<tr>
		<th>Mã số</th>
		<th>Tên phòng ban</th>
	</tr>

	<% while (majorRS.next()) {  %>
	<tr>
		<td><%= majorRS.getInt("mapb") %></td>
		<td><%= majorRS.getString("tenpb") %></td>
	</tr>
	<% } %>
</table>
</div>
<%} %>
</body>
</html>