$(function () {
    init();
})
var op = {
    isParent:1,
    curPId:""
}
function init() {
    $('#typeTable').bootstrapTable({
        url: '/admin/ajax/getParentList',         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        toolbar: '#typeToolbar',                //工具按钮用哪个容器
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
        //height: 450,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false              //是否显示父子表
    });
    $('#childTypeTable').bootstrapTable({
        url: '/admin/ajax/getChildList',         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sortable: false,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        pageSize: 5,                       //每页的记录行数（*）
        pageList: [5, 8, 10, 12],        //可供选择的每页的行数（*）

        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        cardView: false,                    //是否显示详细视图
        detailView: false              //是否显示父子表
    });

    $("#typeModel").on("show.bs.modal",function (e) {op.isParent =0;});
    $("#typeModel").on("hide.bs.modal",function (e) {op.isParent =1;});

    //双击打开子类型表格
    $('#typeTable').on('dbl-click-row.bs.table', function (e, row, $element){
        $('#childTypeTable').bootstrapTable("refresh",{query: {parentId:row.id}});
        $("#typeModelLabel > span").html(row.typeName);
        op.curPId = row.id;   //每次模态框表格出现后，更新全局变量当前父节点的值
        $("#typeModel").modal('show');
    })
}

function showEditModel() {
    var rows = new Array();
    if(op.isParent==1){
        rows = $('#typeTable').bootstrapTable("getSelections");
    }else{
        rows = $('#childTypeTable').bootstrapTable("getSelections");
    }
    if(rows.length==0){alert("请选择要修改的数据");return;}

    $("#editId").html(rows[0].id);
    $("#editModel").modal('show');
}

function addType() {
    var typeId = $("#typeId").val();
    var typeName = $("#typeName").val();
    $.ajax({
        url:"/admin/ajax/addNewType",
        type:"post",
        data:{id:typeId,typeName:typeName,parentId:op.curPId,isParent:op.isParent},
        success:function (result) {
            if(result.success){
                tableRefresh();
                clearInput();
                $("#addModel").modal("hide");
            }else{
                alert(result.message);
            }
        }
    })
}

function editType() {
    var editId = $("#editId").html();
    var newTypeName = $("#newTypeName").val();
    console.log(editId);
    console.log(newTypeName);
    $.ajax({
        url:"/admin/ajax/editType",
        type:"post",
        data:{id:editId,newTypeName:newTypeName,isParent:op.isParent},
        success:function (result) {
            console.log(result);
            if(result.success){
                tableRefresh();
                clearInput();
                $("#editModel").modal("hide");
            }else{
                alert(result.message);
            }
        }
    })
}

function removeType() {
    var rows = new Array();
    console.log(op.isParent);
    if(op.isParent==1){
        rows = $('#typeTable').bootstrapTable("getSelections");
    }else{
        rows = $('#childTypeTable').bootstrapTable("getSelections");
    }
    if(rows.length==0){alert("请选择要删除的数据");return;}
    var row = rows[0];
    if(row.childNum !=null && row.childNum>0){alert("该类型含有子类型，不能删除");return;}
    $.ajax({
        url:"/admin/ajax/removeType",
        type:"post",
        data:{removeId:rows[0].id,isParent:op.isParent},
        success:function (result) {
            if(result.success){
                tableRefresh();
            }else{
                alert(result.message);
            }

        }
    })
}

function tableRefresh() {
    if(op.isParent!=1){
        //alert("开始刷新子表格 , 父ID为" + op.curPId);
        $('#childTypeTable').bootstrapTable("refresh",{query: {parentId:op.curPId}});
        $('#typeTable').bootstrapTable("refresh");
    }else{
        //alert("开始刷新父表格");
        $('#typeTable').bootstrapTable("refresh");
    }
}
function  clearInput() {
    $("#typeId").val("");
    $("#typeName").val("");
}