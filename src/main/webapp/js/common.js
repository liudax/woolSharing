
$(function () {
    loadCommodityType();
    loadHotList();
    isLogin();
    scrollMethod();

})







//登陆弹窗
function openLogin(){
    $("#loginBox").css({"visibility":"visible"});
    $("#mask").css({"height":$(document).height()});
    $("#mask").css({"width":$(document).width()});
    $("#mask").css({"visibility":"visible"});

}
function closeLogin(){
    $("#loginBox").css({"visibility":"hidden"});
    $("#mask").css({"visibility":"hidden"});
}

//



function isLogin(){
    $.ajax({
        url:"/user/isLogin ",
        type:"post",
        async:true,
        success:function (result) {
            if(result.success){
                has = true;
                $("[name=no_logged]").each(function () {
                    $(this).hide();
                });
                $("[name=user_name]").each(function () {
                    $(this).text(result.data.nickname);
                });
                $("[name=logged]").each(function () {
                    $(this).show();
                });
            }

        }
    })
}

$('#captchaImage').click(function()
{
    $('#captchaImage').attr("src", "/captcha?timestamp=" + (new Date()).valueOf());
});


function login(){
    var loginName = $("#loginName").val();
    var password = $("#password").val();
    //var code = $("#l_code").val();
    var remember = $("#remember").is(":checked");
    $.ajax({
        url:"/user/login",
        type:"post",
        data:{loginName:loginName,password:password},
        success:function (result) {
            if(result.success){
                $("[name=no_logged]").each(function () {
                    $(this).hide();
                });
                $("[name=user_name]").each(function () {
                    $(this).text(result.data.nickname);
                });
                $("[name=logged]").each(function () {
                    $(this).show();
                });
                if(remember){
                  var localStorage =  window.localStorage;
                    localStorage.setItem("loginName",loginName);
                    localStorage.setItem("password",password);
                }
                closeLogin();
            }else{
                alert(result.message);
            }
        }
    })
}

function cancel(){
    $.ajax({
        url:"/user/cancel",
        type:"post",
        success:function () {
                $("[name=no_logged]").each(function () {
                    $(this).show();
                });
                $("[name=user_name]").each(function () {
                    $(this).text("");
                });
                $("[name=logged]").each(function () {
                    $(this).hide();
                });
            }
    })
}
//上 左 右 静止事件
function scrollMethod(){
    var top2 = $("#goods_type_pop").offset().top;
    var top3 = $("#nav").offset().top;

    var mySrollTop=0;
    $(window).scroll(function(){
        mySrollTop = $(this).scrollTop();
        //导航栏
        if(mySrollTop>=top3){
            $("#nav").addClass("fiexd1");
            $("#searchBox").show();
        }else{
            $("#nav").removeClass("fiexd1");
            $("#searchBox").hide();
        }

        //商品分类栏
        if(mySrollTop>=top2){
            $("#goods_type_pop").addClass("fiexd2");
        }else{
            $("#goods_type_pop").removeClass("fiexd2");
        }


        /*//热门
        if(mySrollTop-top1 >=0){
            $("#hot").addClass("fiexd2");
        }else{
            $("#hot").removeClass("fiexd2");
        }*/
    });
}
/**
 <ul class="clearfix ul_box ">
 <li class="goodsimg"><a href="/${hot.id}/detail">
 <img class="B_blue" alt="${hot.title}" src="/${hot.imageAddr}/image"></a></li>
 <li class="name"><a title="${hot.title}" href="/${hot.id}/detail">${hot.title}</a></li>
 </ul>
 */
function loadHotList(){
    if($("#hotList").length==0)
        return;

    var storage=window.sessionStorage;
    var html=storage.getItem("hotList");
    $("#hotList").empty();
    if(html=="" ||html==undefined || html==null){
        $.ajax({
            url:"/ajax/addHotList",
            success:function (result) {
                $("#hotList").empty();
                html="";
                if(result.length!=0){
                    for(var i =0;i<result.length;i++){
                        if(i==5) break;
                        var cdy = result[i];
                        html+="<ul class='clearfix ul_box '>";
                        html+=" <li class='goodsimg'><a href='"+cdy.id+"'>";
                        html+="<img class='B_blue' alt='"+cdy.title+"' src='/"+cdy.imageAddr+"/image'></a></li>";
                        html+=" <li class='name'><a title='"+cdy.title+"' href='/"+cdy.id+"/detail'>"+cdy.title+"</a></li> </ul>";
                    }
                    storage.setItem("hotList",html);
                    $("#hotList").append(html);
                }else{
                    alert("热门排行为空");
                }
            }
        })
    }else{
        $("#hotList").append(html);
    }
}

/**
 * 获取商品分类大全
 */
function loadCommodityType(){
    if($("#navclass").length==0)
        return;
    var storage=window.sessionStorage;
    var html=storage.getItem("commodityType");
    $("#navclass").empty();
    if(html=="" ||html==undefined || html==null){
        $.ajax({
            url:"/ajax/getAllType",
            success:function (result) {
                $("#navclass").empty();
                if(result.success==true){
                    var list = result.data;
                    html="";
                    for(var i = 0; i < list.length; i++){
                        html+="<li><a href='/type/"+list[i].id+"' _hover-ignore='1'>"+list[i].typeName+"</a> <ul>";
                        var children = list[i].children;
                        for(var j = 0 ;j <children.length;j++){
                            html+=" <li><a href='/type/"+list[j].id+"'>"+children[j].typeName+"</a></li>"
                        }
                        html+="</ul></li>";
                    }
                    storage.setItem("commodityType",html);
                    $("#navclass").append(html);
                }else{
                    console.log("加载商品分类时："+result.message);
                }
            }
        })
    }else{
        $("#navclass").append(html);
    }
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




