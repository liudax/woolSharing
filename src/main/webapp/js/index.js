
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
        /*console.log(((scrollTop + windowHeight) /scrollHeight)>0.9 );
        console.log("是否有下一页"+goods.hasNext);*/
        if ((((scrollTop + windowHeight) /scrollHeight)>0.98) && goods.hasNext) {
            console.log(11);
            $.ajax({
                post:"get",
                url:"/ajax/appendNextPage",
                data:{page:goods.page},
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
