var allType = new Array();
var newCommodity= new Object();
var oldCommodity = new Object();
$(function () {
    init();
})

function init() {
    loadData();
    loadPlatform();
    loadParent();
}

function loadData() {
    var id = $("#id").val();
    $.ajax({
        url:"/center/ajax/getDetail?id="+id,
        sync:false,
        success:function (data) {
            oldCommodity = data;
            $("#title").val(oldCommodity.title);
            $("#pricePoint").val(oldCommodity.pricePoint);
            $("#label").val(oldCommodity.label);
            $("#reason").val(oldCommodity.reason);
            $("#imageAddr").val(oldCommodity.imageAddr);
            $('#imgBox').attr("src","/"+oldCommodity.imageAddr+"/image");
            $("#link").val(oldCommodity.link);
        }
    });

}
function loadPlatform() {
    $.ajax({
        url:"/ajax/getPlatformList",
        sync:false,
        success:function (result) {
            var html ="";
            for(var i in result){
                var platform = result[i];
                html+="<option value='"+platform.id+"'>"+platform.platformName+"</option>"
            }
            $("#platformId").append(html);
            $("#platformId").val(oldCommodity.platformId);
        }
    })
}

function loadParent() {
    $.ajax({
        url:"/ajax/getAllType",
        sync:false,
        success:function (result) {
            allType = result.data;
            var html ="";
            for(var i in allType){
                var type = allType[i];
                html+="<option value='"+type.id+"'>"+type.typeName+"</option>"
            }

            $("#parentTypeId").append(html);

            $("#parentTypeId").val(oldCommodity.parentTypeId);
            getChildren(oldCommodity.parentTypeId);
            $("#childTypeId").val(oldCommodity.childTypeId);

            $("#parentTypeId").change(function () {
                getChildren($(this).val());
            });
        }
    })
}
function getChildren(id) {
    var children = new Array();
    for(var i = 0;i<allType.length;i++){
        if(allType[i].id == id){
            children = allType[i].children;
            break;
        }
    }

    $("#childTypeId").empty();
    var html ="";
    for(var i in children){
        var child = children[i];
        html+="<option value='"+child.id+"'>"+child.typeName+"</option>"
    }
    $("#childTypeId").append(html);

}


function updateCommodity() {
    newCommodity.id=$("#id").val();
    newCommodity.title = $("#title").val();
    newCommodity.pricePoint = $("#pricePoint").val();
    newCommodity.label = $("#label").val();
    newCommodity.reason = $("#reason").val();
    newCommodity.imageAddr = $("#imageAddr").val();
    newCommodity.link = $("#link").val();
    newCommodity.parentTypeId = $("#parentTypeId").val();
    newCommodity.platformId = $("#platformId").val();
    newCommodity.childTypeId = $("#childTypeId").val();
    if(checkArgs()){
        $.ajax({
            url:"/center/ajax/updateCommodity",
            data:newCommodity,
            type:"post",
            success:function (data) {
                console.log(data);
                if(data){
                    alert("修改成功");
                    window.location.href="/center/myMsg";
                }else {
                    alert("没有任何修改");
                }
            }
        })
    }

}
function checkArgs() {
    if( newCommodity.title==null ||  newCommodity.title ==""){
        alert("标题不能为空");
        return false;
    }
    if(newCommodity.pricePoint==null || newCommodity.pricePoint ==""){
        alert("卖点不能为空");
        return false;
    }
    if(newCommodity.label==null || newCommodity.label ==""){
        alert("标签不能为空");
        return false;
    }
    if(newCommodity.reason==null || newCommodity.reason.length<14){
        alert("原因不得少于15个字");
        return false;
    }
    if(newCommodity.imageAddr==null || newCommodity.imageAddr ==""){
        alert("请上传文件");
        return false;
    }
    if(newCommodity.link==null || newCommodity.link ==""){
        alert("请输入商品链接");
        return false;
    }
    if(newCommodity.platformId==null || newCommodity.platformId ==""){
        alert("请选择商家");
        return false;
    }
    if(newCommodity.parentTypeId==null || newCommodity.parentTypeId ==""){
        alert("请选择主分类");
        return false;
    }
    if(newCommodity.childTypeId==null || newCommodity.childTypeId ==""){
        alert("请选择次分类");
        return false;
    }
    return true;
}
$('input[id=uploadFile]').change(function() {
    var imgPath = $(this).val();
    if (imgPath == "") {
        alert("请选择上传图片！");
        return;
    }
    console.log("图片input发生变化了");
    //判断上传文件的后缀名
    var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
    if (strExtension != 'jpg' && strExtension != 'png') {
        alert("请选择.jpg或.png图片");
        return;
    }
    $("#imageForm").ajaxSubmit({
        type:"post",
        url:"/center/uploadImage",
        success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
            // 此处可对 data 作相关处理
            //alert("上传成功");
            $("#imageAddr").val(data);
            console.log(data);
            $('#imgBox').attr("src","/"+data+"/image");
        }
    });
    return false;
});
