<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="header.jsp"%>
<%@ include file="DatabaseAccess.jsp"%>
<%
	String majorId = request.getParameter("majorId");
	String queryStr = "select tenpb from phongban where mapb =" + majorId;
	ResultSet rs = st.executeQuery(queryStr);
	rs.next();
%>
</head>

<body>
<%
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	%>
	<p style="text-align: center; font-size: 20px">THÊM PHÒNG BAN</p>
	<form name="formThemNhanVien" method="POST" action="sua_phong_ban.jsp">
		<div align="center">
			<input type="hidden" name="majorId" value="<%= majorId%>">
			Tên phòng ban <input type="text" name="majorName" value="<%= rs.getString("tenpb")%>">
		</div>

		<div align="center" style="margin-top: 15px;">
			<input type="submit" value="Lưu">
		</div>
	</form>
</body>
</html>