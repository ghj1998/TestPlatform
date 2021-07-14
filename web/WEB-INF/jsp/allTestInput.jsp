<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gao
  Date: 2021/7/2
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<%--    BootStrap美化页面--%>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>算例集合</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/toAddTestInput">新增测试用例</a>
        </div>
        <div class="col-md-4 column">
            <form action="${pageContext.request.contextPath}/selectTestInputByName" method="post">
                <input type="text" placeholder="输入算例名" name="name">
                <input type="submit" class="btn btn-primary">
            </form>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <th>用例编号</th>
                        <th>用例名称</th>
                        <th>执行结果</th>
                        <th>操作</th>
                    </tr>
                </thead>
<%--                从数据库查询出来，从controller传来的list遍历--%>
                <tbody>
                    <c:forEach var="testInput" items="${list}">
                        <tr>
                            <td>${testInput.id}</td>
                            <td>${testInput.name}</td>
                            <td>
                                <c:if test="${executeId==testInput.id}">${executeResult}</c:if>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/toUpdateTestInput?id=${testInput.id}">修改</a>
                                &nbsp; | &nbsp;
                                <a href="${pageContext.request.contextPath}/deleteTestInput?id=${testInput.id}">删除</a>
                                &nbsp; | &nbsp;
                                <a href="${pageContext.request.contextPath}/executeTestCase?id=${testInput.id}">执行</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
