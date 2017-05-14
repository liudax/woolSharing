<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=10"/>
    <title>${cdy.title}</title>
    <link rel="/shortcut icon" href="/favicon.ico"/>
    <link rel="/bookmark" href="/favicon.ico"/>
    <link href="/css/index.css" rel="stylesheet" type="text/css">
    <link href="/css/jquery-ui.min.css" rel="stylesheet"  >
    <script src="/js/jquery-1.9.1.min.js" type="text/javascript"></script>
</head>
<body>
<%@include file="/page/common/login.html"%>
<%@include file="/page/common/header.html"%>
<%@include file="/page/common/allType.html"%>
<section class="wraper clearfix" id="container">
    <section class="leftwraper" style="width:737px;margin-left: 170px;">
        <div class="crumbs">当前位置：<a href="/index">首页</a><span> > </span>
            <a href="${cdy.typeLink}">${cdy.type}</a><span> > </span>值得买详情</div>

        <article class="newscontent">
            <div class="article_picwrap">
                <img src="/${cdy.imageAddr}/image" alt="${cdy.title}" align="center" width="180px" height="180px" /></div>
            <div class="article_info">
                <h1>${cdy.title}</h1>
                <span class="s_name">${cdy.pricePoint}</span>
                <div class="article_meta article_meta_nowrap">
                    <span class="lrTime"><fmt:formatDate value="${cdy.shareTime}" pattern="yyyy/MM/dd  HH:mm:ss"></fmt:formatDate></span>
                    <div class="recommend lFloat">推荐人：${cdy.nickname}</div>
                    <span class="lFloat">分类：
            <a href="http://www.zdzdm.com/youhui" target="_blank">${cdy.type}</a>
            <a href="/fenlei/zhuzhaijiaju" target="_blank">${cdy.childType}</a></span>
                </div>
                <span class="keywords">标签：
                    <c:forTokens var="label" items="${cdy.label}" delims=" ">
                        <a href='/search?search=${label}' target="_blank" rel='tag'>${label}</a>
                    </c:forTokens>
                </span>
                <div class="clear" style="margin-bottom: 10px "></div>
                <div class="buy">
                    <a target="_blank" isconvert="1" href="${cdy.link}">前往购买 ></a></div>
                <div class="shop">
                    <a href="/mall/ju" target="_blank" class="mall">${cdy.platformName}</a>
                    <span class="kankan"></span>
                </div>
            </div>
        </article>
        <div class="article_content">
            <span class="tuijianliyou">推荐理由：</span>
            <p>${cdy.reason}
            <div>&nbsp;</div>
            </p>
        </div>
        <br>
        <div class="slogan">“真的值得买”(值得买官网：www.zdzdm.com) 是一群网购爱好者（或说是网购上瘾者更为准确）自发建立的网购优惠推荐网站！以发现今天什么真的值得买为己任。我们不卖产品，只推荐靠谱、实惠的产品网购原创信息！打折,优惠,促销尽在“真的值得买”！这是一个值得你收藏的网站！所推荐的优惠信息均具有一定的时效性，下单前请确认优惠有效</div>

        <!--评论框-->
        <div class="comment-form" id="comment-box">
            <input type="hidden" id="commodityId" value="${cdy.id}">
           <%-- <input type="hidden" name="url" value="">--%>
            <div>
                <h5><strong>我来说两句</strong><span class="fn rt blue">已有<font color="#FF0000">${commentNumber}</font>条评论</span></h5>
                <textarea id="content" rows="8" cols="80" name="content"></textarea><br><!--&nbsp;&nbsp;&nbsp;&nbsp;(eyey75307) <a href="" target="_top">退出</a> -->
                <div class="btn" style="font-size:12px;color: red">
                    <input type="button" onclick="addNewComment()" value="发表评论">
                    <div style="display: inline" name="no_logged">&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="openLogin(); return false;">登陆</a>&nbsp;&nbsp;<a>注册</a>&nbsp;&nbsp;你还没有登陆</div>
                </div>
            </div>
        </div>

        <!-- 评论列表 -->
        <h1>用户评价</h1>
        <div class="comment" id="commentList">
           <!-- js加载评论列表 -->
        </div>
    </section>
    <!--右侧登陆信息和热门信息 -->
    <%@include file="/page/common/hot.html"%>
</section>
<%@include file="/page/common/footer.html"%>
<script src="/js/common.js" type="text/javascript"></script>
<script src="/js/de.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
