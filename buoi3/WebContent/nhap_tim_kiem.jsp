<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>
	<div style="width: 50%; margin: 0 auto;">

		<form method="POST" action="tim_kiem.jsp">
			Chọn loại tìm kiếm <Select name="searchType" class="form-control">
				<Option value="staff">Nhân viên</Option>
				<Option value="major">Phòng ban</Option>
			</Select> <br> Tìm kiếm theo <Select name="searchBy" class="form-control">
				<Option value="name">Tên</Option>
				<Option value="id">Mã</Option>
			</Select> <br> 
			Nội dung tìm kiếm <input type="text" name="searchContent" class="form-control">
			<br> <input type="submit" value="Tìm" class="btn btn-success">
		</form>
	</div>
</body>
</html>