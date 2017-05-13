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
            <li><a href="/center/info">个人信息</a></li>
            <li><a href="/center/myMsg">我的投稿</a></li>
            <li><a href="/center/newMsg">在线投稿</a></li>
            <li class="on"><a href="/center/myCollect">我的收藏</a></li>
            <li><a href="/center/myComment">我的评论</a></li>
            <li><a href="">安全退出</a></li>
        </ul>
    </div>
    <style>
        /*.content_right table{ width: 100%;table-layout: fixed;}
        .content_right thead th{padding-left: 10px; background-color: #F5F5F5;height: 30px;font-weight: normal;font-size: 14px;text-align: left;}
        .content_right tbody td{padding-left: 10px;text-align: left;height: 25px;line-height: 25px;border-bottom: 1px solid #F5F5F5;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;color: #333333;font-size: 12px;font-family: "Microsoft YaHei", "SimSun";}
        .page{padding-top: 5px;width: 100%;border-top:1px solid #ccc;position: absolute;top:360px;}
        .page span{}
        .page button{width: 80px;height: 22px;margin-left: 10px;float: right }*/
    </style>
    <!-- 内容部分 -->
    <div class="content_right left" style="position: relative">
        <h3 class="title1">我的收藏</h3>
        <table cellspacing="0" style="width: 100%">
            <thead>
            <tr>
                <th>标题</th>
                <th width="200">发布时间</th>
                <th width="50PX">操作</th>
            </tr>
            </thead>
            <tbody style="text-align: center">
            <tr>
                <td><a href="#">补券+降价！Peacock孔雀AMW-55双层真空不锈钢保温杯550ml</a></td>

                <td>2016-08-01 19:12；00</td>
                <td><a href="#">删除</a> </td>
            </tr>
            <tr>
                <td>aaaaasdasd德紧迫啊uds</td>
                <td>2016-08-01 19:12；00</td>
                <td><a href="#">删除</a> </td>
            </tr>
            <tr>
                <td>aaaaasdasd德紧迫啊uds</td>

                <td>2016-08-01 19:12；00</td>
                <td><a href="#">删除</a> </td>
            </tr>
            <tr>
                <td>aaaaasdasd德紧迫啊uds</td>

                <td>2016-08-01 19:12；00</td>
                <td><a href="#">删除</a> </td>
            </tr>
            <tr>
                <td>aaaaasdasd德紧迫啊uds</td>

                <td>2016-08-01 19:12；00</td>
                <td><a href="#">删除</a> </td>
            </tr>
            <tr>
                <td>aaaaasdasd德紧迫啊uds</td>

                <td>2016-08-01 19:12；00</td>
                <td><a href="#">删除</a> </td>
            </tr>
            <tr>
                <td>aaaaasdasd德紧迫啊uds</td>

                <td>2016-08-01 19:12；00</td>
                <td><a href="#">删除</a> </td>
            </tr>
            <tr>
                <td>aaaaasdasd德紧迫啊uds</td>

                <td>2016-08-01 19:12；00</td>
                <td><a href="#">删除</a> </td>
            </tr>
            <tr>
                <td>aaaaasdasd德紧迫啊uds</td>

                <td>2016-08-01 19:12；00</td>
                <td><a href="#">删除</a> </td>
            </tr>
            <tr>
                <td>aaaaasdasd德紧迫啊uds</td>

                <td>2016-08-01 19:12；00</td>
                <td><a href="#">删除</a> </td>
            </tr>
            </tbody>
        </table>
        <div class="page">
            <span>第3页</span>
            <button>上一页</button>
            <button>下一页</button>
        </div>
    </div>

</div>
<%@include file="/page/common/footer.html"%>
</body>
</html>
