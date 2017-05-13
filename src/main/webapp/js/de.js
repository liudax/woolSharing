$(function () {
    getComments();
});

//点赞
function support(that){
    var id = $(that).attr("f");
    var number= parseInt($(that).attr("number"))
    $(that).text("支持("+(number+1)+")");
    $.ajax({
        type:"post",
        url:"/ajax/support",
        data:{id:id}
    })
}

//回复评论显示控制
function reply_toggle(that) {
    var boxId =$(that).attr("f");
    $("#"+boxId).toggle();
}

//回复
function reply(that) {
    var answerId = $(that).attr("r_id");
    var content = $("#reply_"+answerId).val();
    addNewComment(answerId,content);
    $("#"+answerId).hide();
}

//添加评论
function addNewComment(answerId,content){
    if(content===undefined){
        content = $("#content").val();
    }

    var commodityId = $("#commodityId").val();
    var comment ={
        commodityId:commodityId,
        content:content,
        answerId:answerId
    }
    $.ajax({
        url:"/ajax/addNewComment",
        type:"post",
        data:comment,
        success:function (result) {
            if(result.success){
                var html="";
                $("#content").val("");
                html=addComment(result.data);
                alert("评论成功");
                $("#commentList").prepend(html);
            }else{
                openLogin();
            }

        }
    })

}



//获取该优惠信息的所有评论
function getComments() {
    var commodityId = $("#commodityId").val();
    $.ajax({
        url:"/ajax/allComments",
        type:"get",
        data:{commodityId:commodityId},
        success:function (result) {
            console.log(result);
            if(result!=null && result.length!=0){
                $("#commentList").empty();
                var html="";
                for(var i=0;i<result.length;i++){
                    html+=addComment(result[i]);
                }
                $("#commentList").append(html);
            }
        }
    })
}

//拼接评论html
function addComment(c){
    var html="";
    html+="<p class='fn'> <span>"+format(c.commentTime, 'yyyy-MM-dd HH:mm:ss')+"</span>by "+c.userName+"</p>";
    html+="<div class='content'>";
    html+=addAnswer(c.answer);
    html+=c.content;
    html+="<div class='rt'>";
    html+="<a href='javascript:void(0)' f='"+c.id+"' onclick='reply_toggle(this)'>回复</a>";
    html+=" <a href='javascript:void(0)'  f='"+c.id+"' number="+c.supportNum+" onclick='support(this)'>支持("+c.supportNum+")</a></div>";
    html+="<div id='"+c.id+"' style='display: none;margin: 5px;'>";
    html+="<textarea id='reply_"+c.id+"' rows='8' cols='95'></textarea><br>";
    html+="<div class='btn'><input type='button' r_id='"+c.id+"' onclick='reply(this)' value='发表评论'></div></div></div>";
    return html;

}
//属于回复性评论时
function addAnswer(answer) {
    if(answer==null){
        return "";
    }
    var html="";
    html+="<div class='content'>";
    html+="<span class='blue f12'>"+answer.userName+ "于 "+format(answer.commentTime, 'yyyy-MM-dd HH:mm:ss')+"发布</span>";
    html+=addAnswer(answer.answer);
    html+="<pre>"+answer.content+"</pre></div>"
    return html;
    
}