<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<title>部门管理</title>
<style>
	.alt td{ background:black !important;}
</style>
<script type="text/javascript">
	$(function(){
		$(".btn_input").click(function(){
			window.location.href=$(this).data("url");		
		});
	});
</script>
</head>
<body>
	<s:debug></s:debug>
	<form id="searchForm" action="#" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							<input type="button" value="新增" class="ui_input_btn01 btn_input" data-url='<s:url namespace="/" action="department_input"/>'/> 
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>编号</th>
							<th>部门名称</th>
							<th>部门代码</th>
							<th></th>
						</tr>
						<tbody>
							<s:iterator value="#results">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" /></td>
									<td><s:property value="id"/> </td>
									<td><s:property value="name"/></td>
									<td><s:property value="sn"/></td>
									<td>
										<s:a action="department_input">编辑
											<s:param name="department.id" value="id"/>
										</s:a>
										<s:a action="department_delete">删除
											<s:param name="department.id" value="id"/>
										</s:a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<div class="ui_tb_h30">
					<div class="ui_flt" style="height: 30px; line-height: 30px;">
						共有
						<span class="ui_txt_bold04">100</span>
						条记录，当前第
						<span class="ui_txt_bold04">1/10</span>
						页
					</div>
					<div class="ui_frt">
						<input type="button" value="首页" class="ui_input_btn01"/>
						<input type="button" value="上一页" class="ui_input_btn01"/>
						<input type="button" value="下一页" class="ui_input_btn01"/>
						<input type="button" value="尾页" class="ui_input_btn01"/>
						
						<select list="{10,20,50}" name="" class="ui_select02">
							<option>10</option>
							<option>20</option>
							<option>50</option>
						</select>
						转到第<input type="text" name="" value="" class="ui_input_txt01" />页
							 <input type="button" class="ui_input_btn01" value="跳转"/>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
