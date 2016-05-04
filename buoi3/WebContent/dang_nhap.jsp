<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<title>QUẢN LÝ PHÒNG BAN</title>
</head>
<body>
<div class="container">
    <div class="row" style="width: 30%; margin: 0 auto; marign-top: 50px">
		<div class="span12">
			<form class="form-horizontal" action="kiem_tra_dang_nhap.jsp" method="POST">
			  <fieldset>
			    <div id="legend">
			      <legend class="">Đăng nhập</legend>
			    </div>
			    <div class="control-group">
			      <!-- Username -->
			      <label class="control-label"  for="username">Tên đăng nhập</label>
			      <div class="controls">
			        <input type="text" id="username" name="username" class="form-control" placeholder="" class="input-xlarge">
			      </div>
			    </div>
			    <div class="control-group">
			      <!-- Password-->
			      <label class="control-label" for="password">Mật khẩu</label>
			      <div class="controls">
			        <input type="password" id="password" name="password" class="form-control" placeholder="" class="input-xlarge">
			      </div>
			    </div>
			    <div class="control-group" style="margin-top: 20px">
			      <!-- Button -->
			      <div class="controls">
			        <button class="btn btn-success">Đăng nhập</button>
			      </div>
			    </div>
			  </fieldset>
			</form>
		</div>
	</div>
</div>

</body>
</html>