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
    <script src="/js/jquery-1.9.1.min.js"></script>
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
        <h3 class="title1">密码修改</h3>
        <input type="hidden" id="loginName" value="${user.loginName}">
        <div class="input_div">
            <span>原密码:</span>
            <input class="input_text" id="password" type="password">
        </div>
        <div class="input_div">
            <span>新密码:</span>
            <input class="input_text" id="newPassword" type="password">
        </div>
        <div class="input_div">
            <span>确认密码:</span>
            <input class="input_text" id="rPassword" type="password">
        </div>
        <input type="button" onclick="changePassword()" value="修改" style="margin-left: 160px;width: 60px;height: 30px" />
    </div>
</div>
<%@include file="/page/common/footer.html"%>
<script>
    function changePassword() {
        var flag =true;
        var loginName = $("#loginName").val();
        var password = $("#password").val();
        var newPassword = $("#newPassword").val();
        var rPassword = $("#rPassword").val();

        var check =/^[a-zA-Z][a-zA-Z0-9]{3,19}$/;
        if(!check.test(newPassword)){
            alert("请填写4-20位以字母开头的新密码");
            flag=false;
        }
        if(newPassword!=rPassword){
            alert("两次密码不相同");
            flag=false;
        }
        if(flag){
            $.ajax({
                url:"/center/updatePassword",
                type:"post",
                data:{loginName:loginName,password:password,newPassword:newPassword},
                success:function (result) {
                    if(result){
                        alert("修改密码成功");
                        window.location.href="/center/info";
                    }else{
                        alert("修改密码失败");
                    }
                }
            })
        }
    }

</script>
</body>
</html>
