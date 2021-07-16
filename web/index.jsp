<%--
  Created by IntelliJ IDEA.
  User: gao
  Date: 2021/7/2
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <style>
      a{
        text-decoration: none;
        color: black;
        font-size: 18px;
      }
      form{
        width: 180px;
        height: 38px;
        margin: 100px auto;
        text-align: center;
        line-height: 38px;
        border-radius: 5px;
      }
    </style>
  </head>
  <body>
  <form action="${pageContext.request.contextPath}/toAllTestInput" method="post" enctype="multipart/form-data">
    <div class="form-group">
      <label>主机地址：</label>
      <input type="text" name="host" class="form-control">
    </div>
    <div class="form-group">
      <label>用户名：</label>
      <input type="text" name="user" class="form-control">
    </div>
    <div class="form-group">
      <label>密码：</label>
      <input type="password" name="password" class="form-control">
    </div>
    <br>
    <button type="submit" class="btn btn-default">进入算例集界面</button>
  </form>
  </body>
</html>
