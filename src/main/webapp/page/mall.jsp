<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=10"/>
    <title>超值分享汇-商城导航</title>
    <link rel="/shortcut icon" href="/favicon.ico"/>
    <link rel="/bookmark" href="/favicon.ico"/>
    <link href="/css/index.css" rel="stylesheet" type="text/css">
    <link href="/css/jquery-ui.min.css" rel="stylesheet"  >
    <script src="/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <link href="/css/sjdq.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="/page/common/login.html"%>
<%@include file="/page/common/header.html"%>
<section class="wraper clearfix" id="container">
    <div class="sjdqWrapBox">
        <div class="sjdqList">
            <div class="crumbs">当前位置：<a href="/index">首页</a><span> > </span><a href="/mall">商家导航</a> >  </div>
            <div class="sjdqBgBox sjdh">
                <div class="fontTitle zhsc">商家导航</div>
                <div class="clearfix"></div>
            </div>

            <div class="sjdqBgBox">
                <dl class="sjdqBox">
                    <c:forEach var="mall" items="${mallList}">
                        <dd>
                            <div class="sjdqOutBorder">
                                <span style="position:relative;top:3px;left:6px;color:#777">${mall.platformName}</span>
                                <a class="sjdqLogo" href="/${mall.id}/detail">
                                    <img  src="/${mall.imageAddr}/image" alt="${mall.platformName}">
                                </a>
                            </div>
                            <div class="sjdqBot">
                                <div class="sjdqBotLeft"><a href="/mall/${mall.id}" target="_blank">商家详情</a></div>
                                <div class="sjdqBotRight"><a href="${mall.link}" target="_blank"  rel="nofollow">直达商城</a></div>
                                <div class="clearfix"></div>
                            </div>
                        </dd>
                    </c:forEach>
                    <%--<dd>
                        <div class="sjdqOutBorder">
                            <a class="sjdqLogo" href="">
                                <img src="" alt="京东">
                            </a>
                        </div>
                        <div class="sjdqBot">
                            <div class="sjdqBotLeft"><a href="http://www.zdzdm.com/mall/jd">商家详情</a></div>
                            <div class="sjdqBotRight"><a href="" target="_blank"  rel="nofollow">直达商城</a></div>
                            <div class="clearfix"></div>
                        </div>
                    </dd>--%>
                </dl>
                <div class="clearfix" id="smjd"></div>
            </div>
        </div>
    </div>

</section>
<%@include file="/page/common/footer.html"%>
<script src="/js/common.js" type="text/javascript"></script>
</body>
</html>
