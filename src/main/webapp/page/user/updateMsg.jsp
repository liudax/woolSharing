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
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="/js/jquery-form.js"></script>
</head>
<body>
<%@include file="/page/common/user_header.html"%>
<div id="content">
    <!-- 左侧导航栏 -->
    <div class="left col-1 left-memu">
        <ul>
            <li><a href="/center/info">个人信息</a></li>
            <li><a href="/center/newMsg">在线投稿</a></li>
            <li class="on"><a href="/center/myMsg">我的投稿</a></li>
            <li><a href="/center/myCollect">我的收藏</a></li>
            <li><a href="/center/myComment">我的评论</a></li>
            <li><a href="/login.html">安全退出</a></li>
        </ul>
    </div>

    <!-- 内容部分 -->

    <div class="content_right left" style="margin-bottom: 50px">
        <h3 class="title1">优惠修改</h3>
        <input type="hidden" id="id" value="${updateId}">
        <div class="input_div">
            <span>标题:</span>
            <input class="input_text"  id="title" type="text" style="width: 300px;">
        </div>
        <div class="input_div">
            <span>价格卖点:</span>
            <input class="input_text" id="pricePoint" type="text" style="width: 300px;">
        </div>
        <div class="input_div">
            <span>标签:</span>
            <input class="input_text" id="label" type="text" style="width: 300px;">
        </div>
        <div class="input_div">
            <span>推荐理由:</span>
            <textarea id="reason" style="width: 400px;height: 100px"></textarea>
        </div>
        <div class="input_div">
            <span>图片:</span>
            <form id="imageForm" enctype="multipart/form-data" style="display: inline-block">
                <input type="file" style="display: none;" id="uploadFile" name="image">
                <img  id="imgBox" src="/${cdy.imageAddr}/image" width="100" height="100">
                <input type="button" onclick="$('#uploadFile').click()" value="选择图片">
            </form>
            <input type="hidden" id="imageAddr" value="${cdy.imageAddr}">
        </div>
        <div class="input_div">
            <span>商品链接:</span>
            <input class="input_text"  id="link" type="text" style="width: 300px;">
        </div>
        <style>
            .input_div select{
                height: 25px;
                width: 110px;
            }
        </style>
        <div class="input_div">
            <span>所属平台:</span>
            <select id="platformId">

            </select>
        </div>
        <div class="input_div">
            <span>商品类型:</span>
            <select id="parentTypeId">

            </select>

            <select  id="childTypeId">
                <option value=''>子类型</option>
            </select>
        </div>
        <input type="button" onclick="updateCommodity()" value="修改" style="margin-left: 160px;width: 60px;height: 30px">
    </div>

</div>
<%@include file="/page/common/footer.html"%>
</body>
<script type="text/javascript" src="/js/updateMsg.js"></script>
</html>
