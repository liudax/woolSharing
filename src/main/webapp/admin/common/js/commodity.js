var allType = new Array();
var newCommodity= new Object();
$(function () {
    init();
});
function init() {
    $('#commodityTable').bootstrapTable({
        url: '/admin/ajax/adminCommodityList',         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        toolbar: '#commodityToolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        queryParams: {state:-1},//传递参数（*）
        //sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        //pageNumber: 1,                       //初始化加载第一页，默认第一页
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
     $('#commodityTable').bootstrapTable("hideColumn","id");
    loadParent();
    loadPlatform();
}

function loadPlatform() {
    $.ajax({
        url:"/ajax/getPlatformList",
        success:function (result) {
            var html ="";
            for(var i in result){
                var platform = result[i];
                html+="<li><a platformId ='"+platform.id+"' href='#' onclick='selectPlatform(this);return false'>"+platform.platformName+"</a></li>";
            }
            $("#platformMenu").html(html);
           // $("#platformMenu").append(html);
        }
    })
}

function loadParent() {
    $.ajax({
        url:"/ajax/getAllType",
        success:function (result) {
            allType = result.data;
            var html ="";
            for(var i in allType){
                var type = allType[i];
                html+=" <li><a pId ='"+type.id+"' href='#' onclick='selectParent(this);return false'>"+type.typeName+"</a></li>";
            }
            $("#pIdMenu").html(html);
        }
    });
}

function loadChild(id) {
    var children = new Array();
    for(var i = 0;i<allType.length;i++){
        if(allType[i].id == id){
            children = allType[i].children;
            break;
        }
    }
    var html ="";
    var first = "";
    for(var i in children){
        var child = children[i];
        html+="<li><a cId ='"+child.id+"' href='#' onclick='selectChild(this);return false'>"+child.typeName+"</a></li>";
    }
    $("#cId").html(first+"<span class='caret'>");
    $("#cIdMenu").html(html);
}

function stateFormat(value, row, index) {
    var stateName ="";
    switch (value){
        case 1 : stateName = "<span style='color: #1E9FFF;'>通过</span>";break;
        case 2 : stateName = "<span style='color: #1E9FFF;'>推荐</span>";break;
        case 3 : stateName = "<span style='color: #1E9FFF;'>过期</span>";break;
        case 4 : stateName = "<span style='color: #1E9FFF;'>未通过</span>";break;
        default: stateName = "<span style='color: #1E9FFF;'>未审核</span>";break;
    }
    return stateName;
}
function timeFormat(value, row, index){
    var format ='yyyy-MM-dd HH:mm:ss';
    var t = new Date(value);
    var tf = function(i){return (i < 10 ? '0' : '') + i};
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
        switch(a){
            case 'yyyy':return tf(t.getFullYear());break;
            case 'MM': return tf(t.getMonth() + 1);break;
            case 'mm':return tf(t.getMinutes());break;
            case 'dd':return tf(t.getDate());break;
            case 'HH':return tf(t.getHours());break;
            case 'ss':return tf(t.getSeconds());break;
        }
    })
}

function changeState(newState) {
    var rows= $('#commodityTable').bootstrapTable("getSelections");
    if(rows.length==0){
        alert("请选择");
        return;
    }
    var idsStr = "";
    for(var i=0;i<rows.length;i++){
        if(rows[i].state==newState){break;}
        if(i==rows.length-1){
            idsStr += rows[i].id;
        }else{
            idsStr += rows[i].id+",";
        }
    }
    $.ajax({
        url:"/admin/ajax/changeCommodityState",
        type:'post',
        data:{idsStr:idsStr,newState:newState},
        success:function () {
            $('#commodityTable').bootstrapTable("refresh",{query: {state:$("#qureyState").attr("sState")}});
        }
    });
}


function selectState(that) {
    var stateName = $(that).html();
    var state = $(that).attr("sState");
    var queryState =$("#qureyState").attr("sState");
    if(state ==queryState){
        return;
    }
    $("#qureyState").html(stateName+"<span class='caret'>");
    $("#qureyState").attr("sState",state);

    $('#commodityTable').bootstrapTable("refresh",{query: {state:state}});
}

function selectParent(that){
    var pId = $(that).attr("pId");
    var pName = $(that).html();
    $("#pId").attr("pId",pId);
    $("#pId").html(pName+"<span class='caret'>");
    loadChild(pId);


}

function selectChild(that){
    var cId = $(that).attr("cId");
    var cName = $(that).html();
    $("#cId").attr("cId",cId);
    $("#cId").html(cName+"<span class='caret'>");

}

function selectPlatform(that){
    var platformId = $(that).attr("platformId");
    var platformName = $(that).html();
    $("#platformId").attr("platformId",platformId);
    $("#platformId").html(platformName+"<span class='caret'>");

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
            console.log(data);
            $('#imgBox').attr("src","/"+data+"/image");
        }
    });
    return false;
});

function deleteCommodity() {
    var rows= $('#commodityTable').bootstrapTable("getSelections");
    if(rows.length==0 || rows.length>1){
        alert("请选择一条数据");
        return;
    }
    var row = rows[0];
    $.ajax({
        url:"/admin/ajax/AdminDeleteCommodity",
        data:{id:row.id},
        type:"post",
        success:function (result) {
            if(result){
                alert("删除成功");
                $('#commodityTable').bootstrapTable("refresh",{query: {state:$("#qureyState").attr("sState")}});
            }else {
                alert("删除失败");
            }
        }
    });
}

function addCommodity() {
    newCommodity.title = $("#title").val();
    newCommodity.pricePoint = $("#pricePoint").val();
    newCommodity.label = $("#label").val();
    newCommodity.reason = $("#reason").val();
    newCommodity.imageAddr = $("#imageAddr").val();
    newCommodity.link = $("#link").val();
    newCommodity.parentTypeId = $("#pId").attr("pId");
    newCommodity.platformId = $("#platformId").attr("platformId");
    newCommodity.childTypeId = $("#cId").attr("cId")
    if(checkArgs()){
        $.ajax({
            url:"/admin/ajax/addCommodity",
            data:newCommodity,
            type:"post",
            success:function (result) {
                if(result){
                    $('#commodityTable').bootstrapTable("refresh",{query: {state:$("#qureyState").attr("sState")}});
                    alert("添加成功");
                    $("#addCdyModel").modal("hide");
                }else {
                    alert("添加失败");
                }
            }
        });
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
    if(newCommodity.platformId==null || newCommodity.platformId =="-1"){
        alert("请选择商家");
        return false;
    }
    if(newCommodity.parentTypeId==null || newCommodity.parentTypeId =="-1"){
        alert("请选择主分类");
        return false;
    }
    if(newCommodity.childTypeId==null || newCommodity.childTypeId =="-1"){
        alert("请选择次分类");
        return false;
    }
    return true;
}