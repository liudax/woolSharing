<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=10"/>
    <title>超值分享汇-${pl.platformName}</title>
    <link rel="/shortcut icon" href="/favicon.ico"/>
    <link rel="/bookmark" href="/favicon.ico"/>
    <link href="/css/index.css" rel="stylesheet" type="text/css">
    <link href="/css/jquery-ui.min.css" rel="stylesheet"  >
    <script src="/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <style type="text/css">
        .discovery_list {width:100%;margin-left:-8px;overflow:hidden;margin-right:0;float:none}
        .discovery_list li.list {width:220px;height:340px;border:1px solid #dcdcdc;float:left;margin:0 8px 16px;position:relative;padding-bottom:0;margin-top:0}
        .discovery_list li:hover {-webkit-box-shadow:#ccc 0 2px 5px;-moz-box-shadow:#ccc 0 2px 5px;box-shadow:#ccc 0 2px 5px;border-color:#f04848}
        .discovery_list a.picBox {display:block;width:200px;height:200px;margin:15px auto 12px;text-align:center;overflow:hidden}
        .discovery_list .listItem,.show_list .listItem {padding:0 15px;font-size:12px}
        .discovery_list .itemName {height:60px;line-height:20px;overflow:hidden;margin-bottom:8px;font-size:14px;padding-left:0;font-weight:bold}
        .discovery_list .itemName .red {display:block;height:20px;line-height:20px;overflow:hidden}
        .discovery_list .itemName .black {max-height:40px;display:block;line-height:20px;overflow:hidden}
        .discovery_list .rankAvatar { width:128px}
        .discovery_list .zan_fav_com {height: 24px;width: 117px;float: left;padding-top: 1px;}
    </style>
</head>

<body>
<%@include file="/page/common/login.html"%>
<%@include file="/page/common/header.html"%>
<section class="wraper clearfix" id="container">
    <section class="sjdhWrap">
        <div class="crumbs">当前位置：<a href="/index">首页</a><span> > </span><a href="/mall">商家导航</a> >   ${pl.platformName}</div>
        <div class="sjdhInfoBox">
            <div class="sjdhPic">
                <img src="/${pl.imageAddr}/image" alt="${pl.platformName}">
            </div>
            <div class="sjdhInfo">
                <h1 class="fontTitle">${pl.platformName}</h1>
                <div class="mallAddress">
                    <h3 class="mallName">商城地址：</h3>
                    <a href="" class="blue" target="_blank">${pl.link}</a>
                    <div class="clear"></div>
                    <h3 class="mallName">地域信息：</h3>
                    <span>国内</span>
                </div>
                <div class="mallInfoBox mallInfoBox2">
                    <h3 class="mallName">商城介绍：</h3>
                    <div class="mallInfo">
                        <p class="p_excerpt"> ${pl.introduce}</p>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <a name="tabs"></a>
            <div class="clear"></div>
        </div>

        <div class="screenPannel">
            <!-- tab_nav-->
            <ul class="tab_nav">
                <li class="tab_li2 current"><span>商城精选</span></li>
            </ul>
            <!-- tab_nav end-->
        </div>

        <ul class="leftWrap discovery_list" id="youhui_box">
            <c:forEach items="${cdyList}" var="cdy">
                <li class="list">
                    <a href="/${cdy.id}/detail" target="_blank"  class="picBox">
                        <img src="/${cdy.imageAddr}/image" alt="cdy.title" width="180px" height="180px" style="margin-top:12px">
                    </a>
                    <div class="mall_time"><span class="mall_bg"></span><span class="mall_time_word"><fmt:formatDate value="${cdy.shareTime}" pattern="MM-dd  HH:mm"></fmt:formatDate></span></div>
                    <div class="mall"><span class="mall_bg"></span><span class="mall_word">${cdy.platformName}</span></div>
                    <div class="listItem">
                        <h2 class="itemName"><a href="/${cdy.id}/detail"  target="_blank"><span class="black">${cdy.title}</span><span class="red">${cdy.pricePoint}</span></a></h2>
                        <div class="item_buy_mall">
                            <div class="zan_fav_com">
                                <a href="/${cdy.id}/detail#comment-box" target="_blank" onclick="" class="comment" title="评论"><i class="icon-comment"><!--[if lt IE 8]>评论<![endif]--></i>${cdy.commentNumber}</a>
                            </div>
                            <a target="_blank" href="${cdy.link}" class="directLink">直达链接 ></a>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <div class="pager"> <a href="http://www.zdzdm.com/mall/jd?page=0" class="a1">上一页</a> <a href="http://www.zdzdm.com/mall/jd?page=2" class="next_link">下一页</a></div>

    </section>

</section>
<%@include file="/page/common/footer.html"%>
<script src="/js/common.js" type="text/javascript"></script>
</body>
</html>
