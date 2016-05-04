	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/mystyle.css">
		<script src="js/jquery-1.12.3.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<title>CHƯƠNG TRÌNH QUẢN LÝ PHÒNG BAN</title>
	</head>
	<body>
		<div class="container-fluid" style="background-color: #FEFFF4">
			<!-- Header -->
			<div class="row" style="background-color: #54A1FF; padding: 20px">
				<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"  align="center">
					<img class="logo" src="logo.png">
				</div>
				<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8" align="center" style="display: table-cell; vertical-align: middle;">
					<p class="header">CHƯƠNG TRÌNH QUẢN LÝ PHÒNG BAN</p>
				</div>
				<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2" align="center">
					<img class="logo" src="logo.png"/>
				</div>
			</div>

			<!-- Body -->
			<div class="row">
				<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2" align="center">
					<!-- menu -->
					<a href="trang_chu.jsp">
					<input type="button" class="btn btn-primary menu-button" value="Trang chủ">
					</a>
					<br>
					<a href="xem_nhan_vien.jsp" target="main">
						<input type="button" class="btn btn-default menu-button" value="Xem nhân viên">
					</a>
					<br>
					<a href="xem_phong_ban.jsp" target="main">
						<input type="button" class="btn btn-default menu-button" value="Xem phòng ban">
					</a>
					<br>
					<a href="nhap_tim_kiem.jsp" target="main">
						<input type="button" class="btn btn-default menu-button" value="Tìm kiếm">
					</a>
					<a href="dang_xuat.jsp">
						<input type="button" class="btn btn-danger menu-button" value="Đăng xuất">
					</a>
					<br>
				</div>

				<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8"  align="center" style="margin-top: 20px">
					<!-- noi dung chinh -->
					<iframe src="xem_nhan_vien.jsp" name="main" frameborder="0" width=100% height="500px"></iframe>
				</div>
				<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2"  align="center">
					<!-- quang cao -->
					<img class="banner" src="logo.png" style="margin-top: 15px;">
				</div>

			</div>

		</div>
	</body>
	</html>