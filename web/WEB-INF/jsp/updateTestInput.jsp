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
        <form action="${pageContext.request.contextPath}/updateTestInput" method="post" enctype="multipart/form-data">
            <input hidden="hidden" type="text" name="id" value="${testInput.id}">
            <div class="form-group">
                <label>名称</label>
                <input type="text" name="name" class="form-control" value="${testInput.name}">
            </div>
            <div class="form-group">
                <label>输入</label>
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
            <div class="form-group">
                <label>运行选项（不填默认使用mpi并行，4核心，8线程）,取消勾选则是单核/单线程</label>
                <br>
                <input type="checkbox" name="run" value="plot"><label>仅画图</label>
                <input type="checkbox" name="run" value="mpi" checked="checked"><label>核数</label><input type="text" name="mpi" value="4" size="1">
                <input type="checkbox" name="run" value="omp" checked="checked"><label>线程数</label><input type="text" name="omp" value="8" size="1">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </div>
</div>


</body>
</html>
