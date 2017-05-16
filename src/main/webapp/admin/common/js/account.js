var property = 1;
function checkProperty() {
    $.ajax({url:"/admin/ajax/getAdmin",success:function (editor) {
        console.log(1);
        if(editor.property==2){
            property=2;
            $("#btn_rm").css({"display":"block"});
            $("#btn_qx").css({"display":"block"});
        }
    }
    })
}
$(function () {
    checkProperty();
    init();
});
function init() {
    $('#myTable').bootstrapTable({
        url: '/admin/ajax/userList',         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        //queryParams: oTableInit.queryParams,//传递参数（*）
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
        //height: 450,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false              //是否显示父子表
    });
    $('#myTable').bootstrapTable("hideColumn","id");
    $('#myTable').bootstrapTable("hideColumn","age");
    $('#myTable').bootstrapTable("hideColumn","sex");
    if(property!=2){
        $('#myTable').bootstrapTable("hideColumn","property");
    }



}
function propertyFormat(value, row, index) {
    return value==2?"超级管理员":value==1?"管理员":"普通用户";
}

function stateFormat(value, row, index) {
    return value==1?"<span style='color: red'>禁用</span>":"启用";
}
function sexFormat(value, row, index) {
    return value==1?"女":value==0?"男":"-";
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
function addUser() {
    var loginName= $("#loginName").val();
    var nickname= $("#nickname").val();
    if(loginName!=null&& loginName.trim()!=""){
        $.ajax({
            url:"/admin/ajax/addUser",
            type:'post',
            data:{loginName:loginName,nickname:nickname},
            success:function (result) {
                if(result.success){
                    $("#newUserModel").modal('hide');
                    $('#myTable').bootstrapTable("refresh");
                }else{
                    alert(result.message);
                }
            }
        });
    }else{
        alert("账户为空");
    }
}
function changeState(newState) {
    var msg = newState!=1?"该用户已启用":"该用户已禁用";
    var rows= $('#myTable').bootstrapTable("getSelections");
    if(rows.length==0){
        alert("请选择");
        return;
    }
    var row = rows[0];
    //console.log(newState==row.state);
    if(newState==row.state){
        alert(msg);
    }else{
        $.ajax({
            url:"/admin/ajax/changeState",
            type:'post',
            data:{id:row.id,state:newState},
            success:function () {
                $('#myTable').bootstrapTable("refresh");
            }
        });
    }
}
function changeProperty(newProperty) {
    var msg = newProperty==1?"该用户已是管理员":"该用户为普通用户";
    var rows= $('#myTable').bootstrapTable("getSelections");
    if(rows.length==0){
        alert("请选择");
        return;
    }
    var row = rows[0];
    //console.log(newState==row.state);
    if(newProperty==row.property){
        alert(msg);
    }else{
        $.ajax({
            url:"/admin/ajax/changeProperty",
            type:'post',
            data:{id:row.id,property:newProperty},
            success:function (result) {
                if(result){
                    $('#myTable').bootstrapTable("refresh");
                }else{
                    alert("非法请求");
                }

            }
        });
    }
}