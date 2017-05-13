var allType = new Array();

$(function () {
    loadPlatform();
    loadParent();
})

function loadPlatform() {
    $.ajax({
        url:"/ajax/getPlatformList",
        success:function (result) {
            var html ="";
            for(var i in result){
                var platform = result[i];
                html+="<option value='"+platform.id+"'>"+platform.platformName+"</option>"
            }
            $("#platform").append(html);
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
            $("#parent_type").change(function () {
                getChildren($(this).val());
            });
            $("#parent_type").append(html);

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

    $("#child_type").empty();
    var html ="";
    for(var i in children){
        var child = children[i];
        html+="<option value='"+child.id+"'>"+child.typeName+"</option>"
    }
    $("#child_type").append(html);
}

function addCommodity() {
    
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
            $("#imageName").val(data);
        }
    });
    return false;
}
