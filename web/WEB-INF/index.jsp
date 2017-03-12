<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 3/11/2017
  Time: 7:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>เข้าสู่ระบบ | OEB</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="../css/bootstrap.min.css">
  <link rel="stylesheet" href="../css/bootstrap-theme.min.css">
  <link rel="stylesheet" href="../css/jquery.dataTables.min.css">
  <link href="../css/bootstrap.min.css" rel="stylesheet">
  <link href="../css/login.css" rel="stylesheet">
</head>
<body>
    <div style="overflow:hidden;width:100vw;height:100vh;position:absolute;">
        <img src="../images/loginbg.jpg" style="position:absolute;z-index:-1;">
        <div style="background-color:black;width:100vw;height:100vh;position:absolute;opacity:0.6;"></div>
    </div>

    <div class="col-lg-4 col-lg-offset-4">
      <center><img class="logo-img" src="../images/OEB.png"></center>

      <form class="form-signin" action="LogInServlet" method="POST">
        <p class="form-signin-heading" style="font-size:2vw;text-align:center;">ลงชื่อเข้าใช้</p>
        <input type="text" id="username" name="username" class="form-control" placeholder="ชื่อผู้ใช้" value="${username}" minlength="6" maxlength="20" required onfocus="this.value = this.value;" autofocus>
        <input type="password" id="password" name="password" class="form-control" placeholder="รหัสผ่าน" minlength="6" maxlength="20" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me" name="remember-me" ${check == true?'checked':''}> จำรหัสผ่าน
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" name="button-login" type="submit">เข้าสู่ระบบ</button>
      </form>
        <p style="text-align:center;color:red;">${msg}</p>
    </div>
</body>
</html>