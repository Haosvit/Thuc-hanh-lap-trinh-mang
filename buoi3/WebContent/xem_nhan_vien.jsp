<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="header.jsp" %>
    <%@ include file="DatabaseAccess.jsp" %>
    <%
    	String queryStr = "Select maso, hoten, tenpb, ngaysinh, diachi, ghichu"
    	+ " from nhanvien, phongban where nhanvien.mapb = phongban.mapb order by maso;";
    	ResultSet rs = st.executeQuery(queryStr);
    %>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<p style="text-align: center; font-size: 30px">XEM NHÂN VIÊN</p>
<a href="hien_thi_them_nhan_vien.jsp">
	<input style="margin-bottom: 15px;" type="button" class="btn btn-success" value="Thêm nhân viên" >
</a>
<div class="table-responsive">
	<table class="table table-hover table-bordered table-striped" align="center">
			<tr>
				<th>Mã số</th>
				<th>Họ tên</th>				
				<th>Ngày sinh</th>
				<th>Phòng ban</th>
				<th>Địa chỉ</th>
				<th>Ghi chú</th>
				<th>Thao tác</th>
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
				<td>
					<a href="xoa_nhan_vien.jsp?staffid=<%= staffId %>" onclick="return confirmDelete()" target="main">Xóa</a>
					<a href="hien_thi_sua_nhan_vien.jsp?staffid=<%= staffId %>" target="main">Sửa</a>
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