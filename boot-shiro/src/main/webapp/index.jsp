<%@page pageEncoding="utf-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="shrio" uri="http://shiro.apache.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>index</title>
</head>
<body>
    <h1>系统主页!</h1>
    <ul>
        <shrio:hasAnyRoles name="admin,user">
            <li><a href="">用户管理</a>
                <ul>
                    <shrio:hasPermission name="user:create:*">
                        <li><a href="">添加</a> </li>
                    </shrio:hasPermission>
                    <shrio:hasPermission name="user:delete:*">
                        <li><a href="">删除</a> </li>
                    </shrio:hasPermission>
                    <shrio:hasPermission name="user:update:*">
                        <li><a href="">修改</a> </li>
                    </shrio:hasPermission>
                    <shrio:hasPermission name="user:find:*">
                        <li><a href="">查询</a> </li>
                    </shrio:hasPermission>
                </ul>
            </li>
        </shrio:hasAnyRoles>
        <shrio:hasRole name="admin">
            <li><a href="">商品管理</a> </li>
            <li><a href="">订单管理</a> </li>
            <li><a href="">物流管理</a> </li>
        </shrio:hasRole>
    </ul>

    <ul>
        <li><a href="${pageContext.request.contextPath}/user/logout">退出</a></li>
    </ul>
</body>
</html>