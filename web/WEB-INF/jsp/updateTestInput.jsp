<%--
  Created by IntelliJ IDEA.
  User: gao
  Date: 2021/7/2
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改测试用例</small>
                </h1>
            </div>
        </div>
        <form action="${pageContext.request.contextPath}/updateTestInput" method="post">
            <input hidden="hidden" type="text" name="id" value="${testInput.id}">
            <div class="form-group">
                <label>测试名称</label>
                <input type="text" name="name" class="form-control" value="${testInput.name}">
            </div>
            <div class="form-group">
                <label>测试输入</label>
                <input type="file" name="input" class="form-control">
            </div>
            <div class="form-group">
                <label>测试输出</label>
                <input type="file" name="output" class="form-control">
            </div>
            <div class="form-group">
                <label>允许误差</label>
                <input type="text" name="permissibleError" class="form-control" value="${testInput.permissibleError}">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </div>
</div>


</body>
</html>
