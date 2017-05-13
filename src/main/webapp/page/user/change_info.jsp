<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Magic
  Date: 2017/5/7
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会员管理中心</title>
    <link href="/css/user_header.css" type="text/css" rel="stylesheet">
    <link href="/css/center.css" type="text/css" rel="stylesheet">
</head>
<body>
<%@include file="/page/common/user_header.html"%>
<div id="content">
    <!-- 左侧导航栏 -->
    <div class="left col-1 left-memu">
        <ul>
            <li class="on"><a href="/center/info">个人信息</a></li>
            <li><a href="/center/myMsg">我的投稿</a></li>
            <li><a href="/center/newMsg">在线投稿</a></li>
            <li><a href="/center/myCollect">我的收藏</a></li>
            <li><a href="/center/myComment">我的评论</a></li>
            <li><a href="">安全退出</a></li>
        </ul>
    </div>

    <!-- 内容部分 -->
    <div class="content_right left">
        <h3 class="title1">个人信息修改</h3>
        <form action="/center/ajax/updateUser" method="post">
            <input type="hidden" name="loginName" value="${user.loginName}">
        <div class="input_div">
            <span>昵称:</span>
            <input class="input_text" name="nickname" type="text" value="${user.nickname}">
        </div>
        <div class="input_div">
            <span>电话号码:</span>
            <input class="input_text" name="phone" type="text" value="${user.phone}">
        </div>
        <div class="input_div">
            <span>性别:</span>
            <input type="radio" name="sex" value=0 <c:if test="${user.sex==0}">checked</c:if>>男
            <input type="radio" name="sex" value=1 <c:if test="${user.sex!=0}">checked</c:if>>女
        </div>
        <div class="input_div">
            <span>年龄:</span>
            <input class="input_text" name="age" type="text" value="${user.age}">
        </div>
        <div class="input_div">
            <span>邮箱:</span>
            <input class="input_text" name="email" type="text" value="${user.email}">
        </div>
        <input type="submit" onclick="return true" value="修改" style="margin-left: 160px;width: 60px;height: 30px">
        </form>
    </div>

</div>
<%@include file="/page/common/footer.html"%>
</body>
</html>
