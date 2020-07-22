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
<link rel="stylesheet" href="<%=basePath%>/css/bootstrap-select.min.css">
<link rel="stylesheet" href="<%=basePath%>/css/bootstrap-datetimepicker.min.css" media="screen">
<script src="<%=basePath%>/js/jquery-3.5.1.min.js"></script>
<script src="<%=basePath%>/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/js/mySubmit.js"></script>
<script src="<%=basePath%>/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<!--中文引用-->
<script type="text/javascript" src="<%=basePath%>/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<body>

<div id="wrapper" class="toggled">
    <div class="overlay"></div>

    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper" style="zoom: 1.2">

        <div class="container">
            <button type="button" onclick="window.location.href='<%=basePath%>'" class="btn glyphicon glyphicon-home">首页</button>
            <div class="row" style="margin-right: 200px;">

                <div class="col-lg-8 col-lg-offset-2">
                    <div>
                        <form id="userForm" action="<%=path%>/student?method=saveStudent" method="post">
                            <div class="modal-body" id = "model-body">
                                <div class="form-group">
                                    姓名
                                    <input type="text" name="name" class="form-control"placeholder="姓名" autocomplete="off">
                                </div>
                                <div class="form-group">
                                    备注
                                    <input type="text" name="description" class="form-control"placeholder="description" autocomplete="off">
                                </div>
                                <div class="form-group">
                                    平均分
                                    <input type="text"onkeyup="this.value=this.value.replace(/[^\d]/g,'')"name="avgscore" class="form-control"placeholder="平均分" autocomplete="off">
                                </div>
                                <div class="form-group">
                                    生日
                                    <div class="controls input-append date form_date" data-date="" data-date-format="yyyy年mm月dd日" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                        <input name="birthdayString" class="form-control" size="16" type="text" value="" readonly>
                                        <span class="add-on"><i class="icon-remove"></i></span>
                                        <span class="add-on"><i class="icon-th"></i></span>
                                    </div>
                                    <input type="hidden" name="birthday" id="dtp_input2" value="" /><br/>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <div class="form-group">
                                    <button type="submit"  class="btn btn-primary form-control">提交</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->
</div>

<script>
    $('.form_date').datetimepicker({
        language : 'zh-CN',
        format : 'yyyy-mm-dd',//日期格式
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
</script>

</body>
</html>
