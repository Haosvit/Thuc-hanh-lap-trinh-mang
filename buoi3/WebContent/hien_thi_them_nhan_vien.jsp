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
<p style="text-align: center; font-size: 30px">THÊM NHÂN VIÊN</p>
	<form name="formThemNhanVien" method="POST" action="them_nhan_vien.jsp">
<div class="table-responsive">
	<table class="table table-hover table-bordered table-striped" align="center">
			<tr>
				<th>Họ tên</th>
				<th>Ngày sinh</th>
				<th>Phòng ban</th>
				<th>Địa chỉ</th>
				<th>Ghi chú</th>
			</tr>
			<tr>
				<td><input type="text" class="form-control" name="fullName"></td>
				<td><input type="text" class="form-control" name="dob" placeholder="yyyy-m-d"></td>
				<td>
					<select name="majorId" class="form-control">
						<% while (rs.next()) { %>
							<option value="<%=rs.getInt("mapb")%>"><%=rs.getString("tenpb")%></option>
							<% } %>
					</select>
				</td>
				<td><input type="text" class="form-control" name="address"></td>
				<td><input type="text" class="form-control" name="note"></td>
			</tr>

		</table>
	</div>
		<div align="center" style="margin-top: 15px;">
			<input type="submit" class="btn btn-success" value="Thêm">
		</div>
	</form>
</body>
</html>