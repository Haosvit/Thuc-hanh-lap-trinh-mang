<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="header.jsp"%>
<%@ include file="DatabaseAccess.jsp"%>
<%
	String staffId = request.getParameter("staffid");
	String majorQuery = "select mapb, tenpb from phongban";
	String queryStr = "Select *, tenpb from nhanvien, phongban where maso = " + staffId + " and nhanvien.mapb = phongban.mapb";
	ResultSet rs = st.executeQuery(queryStr);
	rs.first();
	Date date = rs.getDate("ngaysinh");
	String dob = new SimpleDateFormat("yyyy-M-d").format(date);
	int id = Integer.parseInt(staffId);
	String fullName = rs.getString("hoten");
	String address = rs.getString("diachi");
	String note = rs.getString("ghichu");
	int staffMajorId = rs.getInt("nhanvien.mapb");
	rs.close();
	ResultSet majorRS = st.executeQuery(majorQuery);
%>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<p style="text-align: center; font-size: 20px">SỬA NHÂN VIÊN</p>
	<form name="formThemNhanVien" method="POST" action="sua_nhan_vien.jsp">
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
			<tr>
				<td><input type="text" class="form-control" name="id" readonly value="<%=id%>" style="width: 50px"></td>
				<td><input type="text" class="form-control" name="fullName" value="<%= fullName %>"></td>
				<td><input type="text" class="form-control" name="dob" value="<%= dob %>"></td>
				<td>
					<select name="majorId" class="form-control">
						<% while (majorRS.next()) { %>
						<option value="<%=majorRS.getInt("mapb")%>" <%if(majorRS.getInt("mapb") == staffMajorId){%> selected <%}%> ><%=majorRS.getString("tenpb")%></option>
						<% } %>
					</select>
				</td>
				<td><input type="text" class="form-control" name="address" value="<%= address %>"></td>
				<td><input type="text" class="form-control" name="note" value="<%=note %>"></td>
			</tr>

		</table>
	</div>
		<div align="center" style="margin-top: 15px;">
			<input type="submit" class="btn btn-success" value="Lưu">
		</div>

	</form>
</body>
</html>