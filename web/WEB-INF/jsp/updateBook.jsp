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
                    <small>修改书籍</small>
                </h1>
            </div>
        </div>
        <form action="${pageContext.request.contextPath}/book/updateBook" method="post">
            <input hidden="hidden" type="text" name="bookID" value="${book.bookID}">
            <div class="form-group">
                <label>书籍名称</label>
                <input type="text" name="bookName" class="form-control" value="${book.bookName}">
            </div>
            <div class="form-group">
                <label>书籍数量</label>
                <input type="text" name="bookCounts" class="form-control" value="${book.bookCounts}">
            </div>
            <div class="form-group">
                <label>书籍描述</label>
                <input type="text" name="detail" class="form-control"value="${book.detail}">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </div>
</div>


</body>
</html>
