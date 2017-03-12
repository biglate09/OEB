<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 3/11/2017
  Time: 10:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="POST" action="/LogOutServlet">
        <button>Log Out</button> ${sessionScope.restaurantOwner.restUserName}
    </form>
</body>
</html>
