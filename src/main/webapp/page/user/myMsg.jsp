<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会员管理中心</title>
    <link href="/css/user_header.css" type="text/css" rel="stylesheet">
    <link href="/css/center.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
</head>
<body>
<%@include file="/page/common/user_header.html"%>
<div id="content">
    <!-- 左侧导航栏 -->
    <div class="left col-1 left-memu">
        <ul>
            <li><a href="/center/info">个人信息</a></li>
            <li  class="on"><a href="/center/myMsg">我的投稿</a></li>
            <li><a href="/center/newMsg">在线投稿</a></li>
            <li><a href="/center/myCollect">我的收藏</a></li>
            <li><a href="/center/myComment">我的评论</a></li>
            <li><a href="">安全退出</a></li>
        </ul>
    </div>
    <!-- 内容部分 -->
    <div class="content_right left" style="position: relative">
        <h3 class="title1">我的投稿</h3>
        <table cellspacing="0" style="width: 100%">
            <thead>
                <tr>
                    <th>标题</th>
                    <th width="90">状态</th>
                    <th width="180">发布时间</th>
                    <th width="40PX">操作</th>
                </tr>
            </thead>
            <tbody style="text-align: center" id="listBody">

            </tbody>
        </table>
        <div class="page">
            <span id="pageNum">第1页</span>
            <button id="next" onclick="getMyMsg(true)">下一页</button>
            <button id="pre" onclick="getMyMsg(false)">上一页</button>
        </div>
    </div>

</div>
<%@include file="/page/common/footer.html"%>
</body>
<script type="text/javascript">
    var page = {
        isNext:true,
        hasNext:true,
        index:0
    }

    $(function () {
        getMyMsg(true);
    })
    function getMyMsg(flag) {
        page.isNext=flag;
        var pageNum = flag?page.index+1:page.index-1;
        console.log(pageNum + " " + flag);
        $.ajax({
            url:"/center/ajax/getMyMsg",
            data:{page:pageNum},
            success:function (result) {
                if(result==null || result.length==0){
                    alert("没有更多数据了");
                    page.hasNext =false;
                }else{
                    loadDate(result);
                }
                var preShow = page.index<=1?"hidden":"visible";
                var nextShow = page.hasNext?"visible":"hidden";
                $("#pre").css("visibility",preShow);
                $("#next").css("visibility",nextShow);
            }
        })
    }
    function loadDate(result) {
        $("#listBody").empty();
        var html = "";
        if(result.length<10){
            page.hasNext =false;
        }
        for(var i=0;i<result.length;i++){
            if(i==10){
                break;
            }
            var cdy = result[i];
            var state = "";
            html+="<tr>"+
                "<td>"+cdy.title+"</td>"+
                "<td>"+cdy.platformName+"</td>"+
                "<td>"+cdy.shareTime+"</td>"+
                "<td><a href=''>删除</a> </td>"+
                "</tr>";
        }
        $("#listBody").append(html);
        if(page.isNext==false) page.hasNext=true;
        page.index = page.isNext?page.index+1:page.index-1;
        $("#pageNum").text("第"+page.index+"页")
    }
    
    function stateTrans(num) {
        var state = "";
        switch (num){
            case 0:state="未审核";break;
            case 1:state="已通过";break;
            case 0:state="已过期";break;
        }
    }

</script>
</html>
