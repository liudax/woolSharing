<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=10"/>
    <title>超值分享汇-首页</title>
    <link rel="/shortcut icon" href="/favicon.ico"/>
    <link rel="/bookmark" href="/favicon.ico"/>
    <link href="/css/index.css" rel="stylesheet" type="text/css">
    <link href="/css/jquery-ui.min.css" rel="stylesheet"  >
    <script src="/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <style type="text/css">
        #mainright{
            margin-top: -250px;
        }
    </style>
</head>
<body>
<%@include file="/page/common/login.html"%>
<%@include file="/page/common/header.html"%>
<!-- -->
<section class="wraper clearfix" id="container">
    <div style="height: 250px;width:900px;">
        <dl class="catagory fl">
            <dt _hover-ignore="1">商家导航</dt>
            <dd>
                <%--     --%>
                <c:forEach var="pl" items="${plList}">
                    <a href="${pl.link}" target="_new" rel="nofollow">${pl.platformName}</a>
                </c:forEach>
            </dd>
            <dt>搜索热词</dt>
            <dd _hover-ignore="1">
                <c:forEach var="hotWords" items="${hotWordsList}">
                    <a href="/search?search=${hotWords.content}" target="_new">${hotWords.content}</a>
                </c:forEach>
            </dd>
        </dl>
    </div>
    <%@include file="/page/common/allType.html"%>
    <section class="leftwraper" style="width:737px;margin-left: 170px;">
        <%--<div class="crumbs">当前位置：<a href="/index" target='_blank'>首页</a><span> ></span>
            <c:set var="uri" value="${requestScope['javax.servlet.forward.request_uri']}"></c:set>
            <c:if test="${uri =='/yh'}">
                <a href="/yh" target='_blank'>用户分享</a> >
            </c:if>
            <c:if test="${uri =='/zn'}">
                <a href="/zn" target='_blank'>站内推荐</a> >
            </c:if>
            <c:if test="${uri =='/search'}">
                <span>搜索： ${search}</span>
            </c:if>
        </div>--%>
        <div class="crumbs" style="color: #9a0d04;font:bold 26px microsoft yahei">热门推荐</div>
        <div id="xxx" class="mb_pages lb">
            <!--
            加载优惠信息列表
            -->
            <c:forEach var="cdy" items="${cdyList}">
                <div class="new_info_list">
                    <dl>
                        <dt><a href='/${cdy.id}/detail' target='_blank' title="${cdy.title}">${cdy.title}<span class='red'>${cdy.pricePoint}</span></a></dt>
                        <dd>
                            <a href='' target='_blank' title='${cdy.id}/detail'><img src='/${cdy.imageAddr}/image' class='lazy' alt='加载中'></a>
                            <div class='tags'>
                                <span>分类：<a href="">${cdy.type}</a></span>
                                <span class='fr'>post by ${cdy.nickname} / <fmt:formatDate value="${cdy.shareTime}" pattern="yyyy/MM/dd  HH:mm:ss"></fmt:formatDate><a href='/${cdy.id}/detail#comment-box'>评论(${cdy.commentNumber})</a></span></div>
                            <div class='list_txt'>
                                <p>
                                    ${cdy.reason}
                                <div>&nbsp;</div>
                                </p>
                            </div>
                        <dd class='tags tagss clearfix'>
                        <span class='fl tags_list'> 标签:
                            <c:forTokens var="label" items="${cdy.label}" delims=" ">
                                <a href='/search?search=${label}' rel='tag'>${label}</a>
                            </c:forTokens>
        </span>
                            <span class='fr buy'>
          <a href="/mall/${cdy.platformId}" target='_blank' class='mall'>${cdy.platformName}</a>
              <a target='_blank' isconvert='1' href='${cdy.link}' rel='nofollow'>直达链接 ></a>
        </span>
                        </dd>
                        </dd>
                    </dl>
                </div>
            </c:forEach>
        </div>
        <div class="content-footer">
            <img id="loading" src="/images/loading1.gif" height="80" width="80">
            <p id="noMore" style="display: none">已经没有更多内容了!</p>
        </div>
        <style>
            .hhmorebox {
                float: left;
                height: 20px;
                width: 640px;
                text-align: right;
            }

            .hhmorebox a {
                color: #FFF;
                background: #5183C0;
                height: 25px;
                float: right;
                text-align: center;
                width: 85px;
            }
        </style>
    </section>
    <!--右侧登陆信息和热门信息 -->
    <%@include file="/page/common/hot.html"%>
</section>
<div class="scroll" id="scroll">回到顶部</div>
<%@include file="/page/common/footer.html"%>
<script src="/js/common.js" type="text/javascript"></script>
<script src="/js/index.js" type="text/javascript"></script>
</body>
</html>
