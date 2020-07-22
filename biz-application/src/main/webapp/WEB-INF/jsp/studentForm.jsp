<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>/css/index.css">
<script src="<%=basePath%>/js/jquery-3.5.1.min.js"></script>
<script src="<%=basePath%>/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/js/mySubmit.js"></script>
<body>

<form id="initForm"></form>
<div id="wrapper" class="toggled">
    <div class="overlay"></div>
    <form id="init" method="post">
        <input name="page" value="1" style="display:none;">
    </form>

    <!-- Page Content -->
    <div id="page-content-wrapper" style="zoom: 1.2">

        <div class="container">
            <div class="row" style="margin-right: 1000px;">
                <div class="col-lg-8 col-lg-offset-2">
                    <div>
                        <button onclick="window.location.href='<%=path%>/student?method=toSaveStudent'"
                                type="button"  class="btn glyphicon glyphicon-plus">
                            添加
                        </button>
                    <table class="table table-condensed table-hover table-bordered" style="width: 1000px;
                 text-align:center;">
                        <caption>边框表格布局</caption>
                        <thead>
                        <tr>
                            <th style="text-align:center;">姓名</th>
                            <th style="text-align:center;">生日</th>
                            <th style="text-align:center;">备注</th>
                            <th style="text-align:center;">平均分</th>
                            <th style="text-align:center;">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="item" varStatus="status" >
                            <form id="${item.id}" action="<%=path%>" method="post">
                                <input id="id" name="id" style="display:none;" value="${item.id}" >
                            <tr>
                                <td name="userName">${item.name}</td>
                                <td name="birthday">${item.birthdayString}</td>
                                <td name="name">${item.description}</td>
                                <td name="email">${item.avgscore}</td>
                                <td>
                                    <button id="${item.id}" type="button" onclick="toUpdateUser(this)"  name="btn_add" class="btn btn-primary">更新</button>
                                    <button  id="${item.id}" type="button" onclick="deleteStudent(this)" class="btn btn-danger">删除</button>
                                </td>
                            </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                    </div>

                </div>
            </div>
            <div class="fixed-table-pagination"><div class="pull-left pagination-detail">
                <span class="pagination-info">展示 ${page.pageNow*10-9} 至 ${page.pageNow*10}条  共有 ${page.total} 条</span>
                <span class="page-list" style="display: none;">
                            <span class="btn-group dropup">
                                <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
                                    <span class="page-size">10</span> <span class="caret"></span></button>
                                <ul class="dropdown-menu" role="menu">
                                    <li class="active">
                                    <a href="javascript:void(0)">10</a>
                                    </li>
                                </ul></span>
                                records per page</span>
                    </div>

                    <ul class="pagination">
                        <li class="page-pre ">
                            <a href="<%=basePath%>/student?method=toUserForm&page=${page.pageNow-1}">&lt;</a>
                        </li>
                        <c:forEach var="i" begin="1" end="${page.pageNum}">
                            <li class="page-number <c:if test="${i==page.pageNow}">active disabled</c:if>">
                                <a href="<%=basePath%>/student?method=toUserForm&page=${i}">${i}</a>
                            </li>
                        </c:forEach>
                        <li class="page-next ">
                            <a href="<%=basePath%>/student?method=toUserForm&page=${page.pageNow+1}">&gt;</a>
                        </li>
                    </ul>
                </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->
</div>

<script>
</script>

</body>
</html>
