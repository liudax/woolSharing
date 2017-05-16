
$(function () {
    init();
});
function init() {
    $('#platformTable').bootstrapTable({
        url: '/ajax/getPlatformList',         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        toolbar: '#platformToolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        pageSize: 5,                       //每页的记录行数（*）
        pageList: [5, 8, 10, 12],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false              //是否显示父子表
    });
    $('#platformTable').bootstrapTable("hideColumn","introduce");
    $('#platformTable').bootstrapTable("hideColumn","id");
    $('#platformTable').bootstrapTable("hideColumn","imageAddr");
}
function showModel() {
    var rows = $('#platformTable').bootstrapTable("getSelections");
    if(rows.length==0){
        alert("请选择");
        return;
    }
    var row = rows[0];

    //填充showModel
    $("#showModelLabel").html(row.platformName);
    $("#show_link").html(row.link);
    $("#show_region").html(row.region);
    $("#show_image").attr("src","/"+row.imageAddr+"/image");
    $("#show_introduce").html(row.introduce);

    $("#showModel").modal("show");
}
function showAddModel() {
    $("#addModelLabel").html("新增");
    $("#platformName").val("");
    $("#imageAddr").val("");
    $("#link").val("");
    $("#region").val("国内");
    $("#imgBox").attr("src","");
    $("#introduce").val("");
    $("#method").attr("onClick","addPlatform()")
    $("#addModel").modal("show");
}


function showEditModel() {
    var rows = $('#platformTable').bootstrapTable("getSelections");
    if(rows.length==0){
        alert("请选择");
        return;
    }
    var row = rows[0];

    //填充showModel

    $("#edit_id").val(row.id);
    $("#addModelLabel").html("修改");
    $("#platformName").val(row.platformName);
    $("#link").val(row.link);
    $("#region").val(row.region);
    $("#imageAddr").val(row.imageAddr);
    $("#imgBox").attr("src","/"+row.imageAddr+"/image");
    $("#introduce").val(row.introduce);
    $("#method").attr("onClick","editPlatform()");
    $("#addModel").modal("show");
}


function addPlatform() {
    var platform = checkParam();
    if(platform==null){
        return;
    }
    $.ajax({
        url:"/admin/ajax/addPlatform",
        type:"post",
        data:platform,
        success:function (result) {
            alert("上传成功");
            $("#platformTable").bootstrapTable("refresh");
            $("#addModel").modal("hide");
        }
    });
}
function editPlatform() {
    var platform = checkParam();
    if(platform==null){
        return;
    }
    platform.id =  $("#edit_id").val();
    $.ajax({
        url:"/admin/ajax/updatePlatform",
        type:"post",
        data:platform,
        success:function (result) {
            alert("上传成功");
            $("#platformTable").bootstrapTable("refresh");
            $("#addModel").modal("hide");
        }
    });
}

function deletePlatform() {
    var rows = $('#platformTable').bootstrapTable("getSelections");
    if(rows.length==0){
        alert("请选择");
        return;
    }
    $.ajax({
        url:"/admin/ajax/deletePlatform",
        data:{id:rows[0].id},
        type:"post",
        success:function (result) {
            alert("删除成功");
            $("#platformTable").bootstrapTable("refresh");
        }
    });

}
function checkParam() {
    var platform = new Object();
    platform.platformName =$("#platformName").val();
    platform.imageAddr =$("#imageAddr").val();
    platform.link =$("#link").val();
    platform.region =$("#region").val();
    platform.introduce =$("#introduce").val();
    console.log(platform.imageAddr);
    if( platform.platformName==null || platform.platformName==""){
        alert("商城名为空");
        return null;
    }
    if( platform.link==null || platform.link==""){
        alert("链接为空");
        return null;
    }
    if( platform.imageAddr==null || platform.imageAddr==""){
        alert("请上传图片");
        return null;
    }
    if( platform.introduce==null || platform.introduce==""){
        alert("商城介绍为空");
        return null;
    }
    return platform;
}
$('input[id=lefile]').change(function() {
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
        url:"/admin/uploadImage",
        success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
            // 此处可对 data 作相关处理
            //alert("上传成功");
            $("#imageAddr").val(data);
            $('#imgBox').attr("src","/"+data+"/image");
        }
    });
    return false;
});
