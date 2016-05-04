<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="header.jsp"%>
<%@ include file="DatabaseAccess.jsp"%>
<%
	String queryStr = "Select mapb, tenpb from phongban;";
	ResultSet rs = st.executeQuery(queryStr);
%>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	%>
	<p style="text-align: center; font-size: 30px">THÊM PHÒNG BAN</p>
	<form name="formThemNhanVien" method="POST" action="them_phong_ban.jsp">
		<div align="center">
			Tên phòng ban <input type="text" class="form-control" name="majorName" style="width:300px">
		</div>

		<div align="center" style="margin-top: 15px;">
			<input type="submit" class="btn btn-success" value="Thêm"> 
		</div>
	</form>
</body>
</html>