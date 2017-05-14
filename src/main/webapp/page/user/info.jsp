<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会员管理中心</title>
    <link href="/css/user_header.css">
    <link href="/css/center.css" type="text/css" rel="stylesheet">
</head>
<body>
<%@include file="/page/common/user_header.html"%>
<div id="content">
    <!-- 左侧导航栏 -->
    <div class="left col-1 left-memu">
        <ul>
            <li class="on"><a href="/center/info">个人信息</a></li>
            <li><a href="/center/newMsg">在线投稿</a></li>
            <li><a href="/center/myMsg">我的投稿</a></li>
            <li><a href="/center/myCollect">我的收藏</a></li>
            <li><a href="/center/myComment">我的评论</a></li>
            <li><a href="/login.html">安全退出</a></li>
        </ul>
    </div>
    <!-- 内容部分 -->
    <div class="content_right left">
        <h3 class="title1">个人信息
            <span style="position: relative;float:right;top:10px;font-size: 12px">
            <a href="/center/change_info" target="_blank">修改信息</a>
            <a href="/center/change_password" target="_blank">修改密码</a>
        </span>
        </h3>

        <div class="input_div">
            <span>用户名:</span>
            <span class="r">${user.loginName}</span>
        </div>
        <div class="input_div">
            <span>注册时间:</span>
            <span class="r"><fmt:formatDate value="${user.registerTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></span>
        </div>
        <div class="input_div">
            <span>昵称:</span>
            <span class="r">${user.nickname}</span>
        </div>
        <div class="input_div">
            <span>性别:</span>
            <span class="r">
                <c:if test="${user.sex==0}">男</c:if>
                <c:if test="${user.sex==1}">女</c:if>
            </span>
        </div>
        <div class="input_div">
            <span>年龄:</span>
            <span class="r">${user.age}</span>
        </div>
        <div class="input_div">
            <span>邮箱:</span>
            <span class="r">${user.email}</span>
        </div>
        <div class="input_div">
            <span>电话:</span>
            <span class="r">${user.phone}</span>
        </div>
    </div>
</div>
<%@include file="/page/common/footer.html"%>
</body>

</html>
