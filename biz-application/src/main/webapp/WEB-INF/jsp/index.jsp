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
<script src="<%=basePath%>/js/mySubmit.js"></script>
<body>
    <form id="init" action="<%=path%>" method="post">
        <input name="page" value="1" style="display:none;">
    </form>

<script>
    init();
</script>

</body>
</html>
