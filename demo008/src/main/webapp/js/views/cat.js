$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var catDatagrid,catDialog,catForm;
	catDatagrid = $("#cat_datagrid");
	catDialog = $("#cat_dialog");
	catForm = $("#cat_form");
	/*
	 * 初始化数据表格 
	 */
	catDatagrid.datagrid({
		url:"/cat/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#cat_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	catDialog.dialog({
		width:300,
		height:300,
		closed:true,
		buttons:'#cat_dialog_bt'
	});
	/*
	 * 对页面按钮进行统一监听
	 */
	$("a[data-cmd]").on("click",function(){
		var cmd = $(this).data("cmd");
		if(cmd){
			cmdObj[cmd]();
		}
	});
	/*
	 * 所有的操作封装到cmdObj对象中,方便管理
	 */
	var cmdObj = {
			 add:function(){
				catForm.form("clear");
				catDialog.dialog("setTitle","新增");
				catDialog.dialog("open");
			},
			edit:function(){
				var rowData = catDatagrid.datagrid("getSelected");
				if(rowData){
					catForm.form("clear");
					catDialog.dialog("setTitle","编辑");
					catDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					catForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = catDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/cat/delete?catId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										catDatagrid.datagrid("reload");
									});
								}else{
									$.messager.alert("温馨提示",data.msg,"error");
								}
							},"json")
						}
					});
				}else{
					$.messager.alert("温馨提示","请选择需要删除的数据!","warining");
				}
			},
			reload:function(){
				catDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/cat/update"
				}else{
					url = "/cat/save";
				}
				catForm.form("submit",{
					url:url,
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								catDialog.dialog("close");
								catDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				catDialog.dialog("close");
			}
	}
});
