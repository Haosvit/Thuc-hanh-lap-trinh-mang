<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="header.jsp" %>
    <%@ include file="DatabaseAccess.jsp" %>
    <%
    	String queryStr = "Select * from phongban";
    	ResultSet rs = st.executeQuery(queryStr);
    %>
</head>
<body>
<%
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	%>
<p style="text-align: center; font-size: 30px">XEM PHÒNG BAN</p>
<a href="hien_thi_them_phong_ban.jsp">
	<input style="margin-bottom: 15px;" type="button" class="btn btn-success" value="Thêm phòng ban" >
</a>
<div class="table-responsive">
	<table class="table table-hover table-bordered table-striped" align="center">
			<tr>
				<th>Mã số</th>
				<th>Tên phòng ban</th>
				<th>Thao tác</th>
			</tr>
			
		<% while (rs.next()) { 
			String majorId = rs.getString("mapb"); %>
			<tr>
				<td><%= majorId %></td>
				<td><%= rs.getString("tenpb") %></td>
				<td>
					<a href="xoa_phong_ban.jsp?majorId=<%= majorId %>" onclick="return confirmDelete()" target="main">Xóa</a>
					<a href="hien_thi_sua_phong_ban.jsp?majorId=<%= majorId %>" target="main">Sửa</a>
				</td>
			</tr>
		<% } %>
		</table>
	</div>
	<script type="text/javascript">
		function confirmDelete() {
			if (! confirm("Xóa?")){
				return false;
			}
		}
	</script>
</body>
</html>