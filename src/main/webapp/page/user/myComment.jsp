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
            <li><a href="/center/info">个人信息</a></li>
            <li><a href="/center/newMsg">在线投稿</a></li>
            <li><a href="/center/myMsg">我的投稿</a></li>
            <li><a href="/center/myCollect">我的收藏</a></li>
            <li class="on"><a href="/center/myComment">我的评论</a></li>
            <li><a href="/login.html">安全退出</a></li>
        </ul>
    </div>
    <div class="content_right left" style="position: relative">
        <h3 class="title1">我的评论</h3>
        <table cellspacing="0" style="width: 100%">
            <thead>
            <tr>
                <th>标题</th>
                <th width="200px">发布时间</th>
                <th width="50PX">操作</th>
            </tr>
            </thead>
            <tbody id="listBody" style="text-align: center">

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

    $(function () {
        getMyMsg(true);
    })
    var page = {
        isNext:true,
        hasNext:true,
        index:0
    }

    function getMyMsg(flag) {
        page.isNext=flag;
        var pageNum = flag?page.index+1:page.index-1;
        console.log(pageNum + " " + flag);
        $.ajax({
            url:"/center/ajax/getComment",
            data:{page:pageNum},
            success:function (result) {
                if(result==null || result.length==0){
                    //alert("没有更多数据了");
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
            var com = result[i];
            html+="<tr>"+
                "<td><a href='/"+com.commodity_id+"/detail' target='-_blank'>"+com.commodityTitle+"</a></td>"+
                "<td>"+format(com.comment_time, 'yyyy-MM-dd HH:mm:ss')+"</td>"+
                "<td><a href='#' num='"+com.id+"' onclick='deleteCmm(this);return false'>删除</a> </td>"+
                "</tr>";
        }
        $("#listBody").append(html);
        if(page.isNext==false) page.hasNext=true;
        page.index = page.isNext?page.index+1:page.index-1;
        $("#pageNum").text("第"+page.index+"页")
    }

    function deleteCmm(that) {
        var id = $(that).attr("num");
        $.ajax({
            url:"/center/ajax/deleteComment",
            type:"post",
            data:{id:id},
            success:function (result) {
                if(result){
                    alert("删除成功");
                    window.location.href="/center/myComment";
                }else{
                    alert("删除失败");
                }
            }
        })
    }

    var format = function(time, format){
        var t = new Date(time);
        var tf = function(i){return (i < 10 ? '0' : '') + i};
        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
            switch(a){
                case 'yyyy':
                    return tf(t.getFullYear());
                    break;
                case 'MM':
                    return tf(t.getMonth() + 1);
                    break;
                case 'mm':
                    return tf(t.getMinutes());
                    break;
                case 'dd':
                    return tf(t.getDate());
                    break;
                case 'HH':
                    return tf(t.getHours());
                    break;
                case 'ss':
                    return tf(t.getSeconds());
                    break;
            }
        })
    }
</script>
</html>
