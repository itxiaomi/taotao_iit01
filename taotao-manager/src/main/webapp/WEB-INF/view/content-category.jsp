<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	 <ul id="contentCategory" class="easyui-tree">
    </ul>
</div>

<%--对树的条目右键显示的菜单--%>
<div id="contentCategoryMenu" class="easyui-menu" style="width:120px;" data-options="onClick:menuHandler">
    <div data-options="iconCls:'icon-add',name:'add'">添加</div>
    <div data-options="iconCls:'icon-edit',name:'rename'">重命名</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove',name:'delete'">删除</div>
</div>
<script type="text/javascript">

    /*入口函数： 页面加载完毕就执行*/
$(function(){
    //在这个id标签里面构建树， 树的内容来自于/rest/content/category
	$("#contentCategory").tree({
		url : '/rest/content/category',
		animate: true,
		method : "GET",

        //在右键选中的位置，显示菜单。
		onContextMenu: function(e,node){
            e.preventDefault();
            $(this).tree('select',node.target);
            $('#contentCategoryMenu').menu('show',{
                left: e.pageX,
                top: e.pageY
            });
        },

        //编辑完成之后
        onAfterEdit : function(node){
        	var _tree = $(this);

        	//如果是新增的，那么走这个if
        	if(node.id == 0){
        		// 新增节点
        		$.post("/rest/content/category/add",{parentId:node.parentId,name:node.text},function(data){
        			_tree.tree("update",{
        				target : node.target,
        				id : data.id
        			});
        		});
        	}else{
        	    //如果是更新（重命名）
        		$.ajax({
        			   type: "POST",
        			   url: "/rest/content/category/update",
        			   data: {id:node.id,name:node.text}, //把这个节点的id &  文本传送给服务器
        			   success: function(msg){
        				   $.messager.alert('提示','重命名成功!');
        			   },
        			   error: function(){
        				   $.messager.alert('提示','重命名失败!');
        			   }
        			});
        	}
        }
	});
});
function menuHandler(item){
	var tree = $("#contentCategory");
	var node = tree.tree("getSelected");
	if(item.name === "add"){
		tree.tree('append', {
            parent: (node?node.target:null),
            data: [{
                text: '新建分类',
                id : 0,
                parentId : node.id
            }]
        }); 
		var _node = tree.tree('find',0);
		tree.tree("select",_node.target).tree('beginEdit',_node.target);
	}else if(item.name === "rename"){
		tree.tree('beginEdit',node.target);
	}else if(item.name === "delete"){
		$.messager.confirm('确认','确定删除名为 '+node.text+' 的分类吗？',function(r){
			if(r){
				$.ajax({
     			   type: "POST",
     			   url: "/rest/content/category/delete",
     			   data : {parentId:node.parentId,id:node.id},
     			   success: function(msg){
     				   //$.messager.alert('提示','新增商品成功!');
     				  tree.tree("remove",node.target);
     			   },
     			   error: function(){
     				   $.messager.alert('提示','删除失败!');
     			   }
     			});
			}
		});
	}
}
</script>