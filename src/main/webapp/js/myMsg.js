var allType = new Array();
var newCommodity= new Object();

$(function () {
    init();
})

function init() {
    loadPlatform();
    loadParent();
}
function loadPlatform() {
    $.ajax({
        url:"/ajax/getPlatformList",
        success:function (result) {
            var html ="";
            for(var i in result){
                var platform = result[i];
                html+="<option value='"+platform.id+"'>"+platform.platformName+"</option>"
            }
            $("#platformId").append(html);
        }
    })
}

function loadParent() {
    $.ajax({
        url:"/ajax/getAllType",
        success:function (result) {
            allType = result.data;
            var html ="<option value=''>父" +
                "类型</option>";
            for(var i in allType){
                var type = allType[i];
                html+="<option value='"+type.id+"'>"+type.typeName+"</option>"
            }
            $("#parentTypeId").change(function () {
                getChildren($(this).val());
            });
            $("#parentTypeId").append(html);

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

function addCommodity() {
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
            url:"/center/ajax/addCommodity",
            data:newCommodity,
            type:"post",
            success:function (data) {
                if(data.success){
                    alert("添加成功");
                    window.location.href="/center/myMsg";
                }else {
                    alert("添加失败");
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


function uploadHead(){
    var imgPath = $("#uploadFile").val();
    if (imgPath == "") {
        alert("请选择上传图片！");
        return;
    }
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
            alert("上传成功");
            $("#imageAddr").val(data);
        }
    });
    return false;
}
