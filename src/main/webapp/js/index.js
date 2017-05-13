
goods = {
    page:2,
    hasNext:true,
    isLoading:false
}
getNextPage();
/***返回顶部***/
$(window).scroll(function() {
    var scrollValue = $(window).scrollTop();
    scrollValue > 200 ? $('div[class=scroll]').fadeIn() : $('div[class=scroll]').fadeOut();
});
$('#scroll').click(function() {
    $("html,body").animate({
        scrollTop: 0
    }, 200);
});
/**
 * 下拉加载
 */
function getNextPage(){
    $(window).scroll(function () {
        var scrollTop = $(this).scrollTop();
        var scrollHeight = $(document).height();
        var windowHeight = $(this).height();
        if ((scrollTop + windowHeight == scrollHeight) && goods.hasNext) {
            //console.log(goods.page);
            var path = $("#search_value").attr("path");
            var search = $("#search_value").val();
            $.ajax({
                post:"get",
                url:"/ajax/appendNextPage",
                data:{page:goods.page,search:search,path:path},
                cache:false,
                beforeSend:function () {
                    $("#loading").show();
                },
                success:function (result) {
                    if(result==undefined ||result.length==0){
                        $("#noMore").show();
                        goods.hasNext=false;
                    }else{
                        console.log(result[0].reason);
                        loadData(result);
                        goods.page++;
                    }
                },
                error:function () {
                    $("#noMore").show();
                },
                complete:function () {
                    $("#loading").hide();
                }

            })
        }
    })


}

function loadData(data){
    var appendHtml =  "";
    for(var i=0;i<data.length;i++){
        var cdy = data[i];
        appendHtml+="<div class='new_info_list'>";
        appendHtml+="<dl>";
        appendHtml+="<dt><a href='/"+cdy.id+"/detail' target='_blank' title='"+cdy.title+"'>"+cdy.title+"<span class='red'>"+cdy.pricePoint+"</span></a></dt>";
        appendHtml+="<dd>";
        appendHtml+="<a href='/"+cdy.id+"/detail' target='_blank' title='"+cdy.id+"/detail'><img src='/"+cdy.imageAddr+"/image' class='lazy' alt='加载中'></a>";
        appendHtml+="<div class='tags'>";
        var shartype = cdy.type=='站内推荐'?'zn':'yh';
        appendHtml+="<span>分类：<a href='/"+shartype+"'>"+cdy.type+"</a></span>";
        appendHtml+="<span class='fr'>post by "+cdy.nickname+" / "+format(cdy.shareTime, 'yyyy-MM-dd HH:mm:ss')+"<a href='/"+cdy.id+"/detail'>评论("+cdy.commentNumber+")</a></span></div>";
        appendHtml+="<div class='list_txt'>";
        appendHtml+="<p>"+cdy.reason;
        appendHtml+=" <div>&nbsp;</div></p> </div>";

        appendHtml+="<dd class='tags tagss clearfix'>";
        appendHtml+=" <span class='fl tags_list'> 标签:";
        var labels = new Array();
        labels= cdy.label.split(" ");
        console.log(labels);
        for(var j=0; j<labels.length; j++){
            appendHtml += " <a href='/search?search="+labels[j]+"' rel='tag'>"+labels[j]+"</a>";
        }

        appendHtml+="</span>";
        appendHtml+="<span class='fr buy'>";
        appendHtml+="<a href='/dh/tmall' target='_blank' class='mall'>"+cdy.platformName+"</a>";
        appendHtml+="<a target='_blank' isconvert='1' href='"+cdy.link+"' rel='nofollow'>前往购买 ></a>";
        appendHtml+=" </span> </dd> </dd> </dl> </div>";
    }
    $("#xxx").append(appendHtml);
}
/**
 <c:forEach var="cdy" items="${cdyList}">














 <c:forTokens var="label" items="${cdy.label}" delims=" ">
 <a href='/search?search=${label}' rel='tag'>${label}</a>
 </c:forTokens>



 <a target='_blank' isconvert='1' href='' rel='nofollow'>前往购买 ></a>
 </span>
 </dd>
 </dd>
 </dl>
 </div>
 </c:forEach>
 */


/**
 * 返回一条数据的html
 * @param data
 * @returns {string}
 */
function drawOne(data) {

    //keys=[reason, commentNumber, childType, pricePoint, imageAddr, nickname, shareTime, id, title, type, platformName, parentType]
    var html = "";
    for(var i=0;i<data.length;i++){

    }
    html+= "<div class='new_info_list'><dl><dt><a href='' target='_blank' title='"+data.title+"'>"+data.title+"<span class='red'>"+data.pricePoint+"</span></a></dt>" +
        "<dd><a href='' target='_blank' title='"+data.title+"'>" +
        "<img class='lazy' src='/"+data.imageAddr+"/image' alt='加载中'></a><div class='tags'>" +
        "<span>分类：<a href=''>"+data.type+"</a></span>" +
        "<span class='fr'>post by "+data.nickname+" / "+data.shareTime+"<a href=''>评论("+data.commentNumber+")</a></span></div>" +
        "<div class='list_txt'>" +
        "<p>"+data.reason+"<div>&nbsp;</div>" +
        " <div>&nbsp;</div>" +
        " </p>" +
        "</div>" +
        "<dd class='tags tagss clearfix'>" +
        "<span class='fl tags_list'> 标签: ";
    var labels = new Array();
    labels = data.label.split(" ");
    for(var i=0; i<labels.length; i++){
        html += "<a href='/search?search='"+labels[i]+"rel='tag'>"+labels[i]+"</a>";
    }
        " </span>" +
        "<span class='fr buy'>" +
        "<a href='' target='_blank'  class='mall'>"+data.platformName+"</a>" +
        "<a target='_blank' isconvert='1' href='"+data.link+"' rel='nofollow'>前往购买 ></a></span></dd></dd></dl></div>"
    return html;

}