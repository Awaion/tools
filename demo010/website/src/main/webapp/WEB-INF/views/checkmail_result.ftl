<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平台->邮件激活</title>
<link rel="stylesheet" href="/js/bootstrap-3.3.2-dist/css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="/css/core.css" type="text/css" />
<script type="text/javascript" src="/js/jquery/jquery-2.1.3.js"></script>
<script type="text/javascript" src="/js/bootstrap-3.3.2-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="/js/jquery.bootstrap.min.js"></script>
<script type="text/javascript" src="/js/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="/js/plugins/jquery-validation/localization/messages_zh.js"></script>
<script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery.bootstrap.min.js"></script>

<style type="text/css">
	.el-login-form{
		width:600px; 
		margin-left:auto;
		margin-right:auto;
		margin-top: 20px;
	}
	.el-login-form .form-control{
		width: 220px;
		display: inline;
	}
</style>

</head>
<body>
	<!-- 网页头信息 -->
	<div class="el-header" >
		<div class="container" style="position: relative;">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/">首页</a></li>
				<li><a href="/login.html">登录</a></li>
				<li><a href="#">帮助</a></li>
			</ul>
		</div>
	</div>
	
	<!-- 网页导航 --> 
	<div class="navbar navbar-default el-navbar">
		<div class="container">
			<div class="navbar-header">
				<a href=""><img alt="Brand" src="/images/logo.png"></a>
				<span class="el-page-title">邮件激活结果</span>
			</div>
		</div>
	</div>
	
	<!-- 网页内容 --> 
	<div class="container"  style="min-height: 343px;">  
		<h1>
			<#if result.success>
				您的邮件已经成功激活,请登录系统查看!
			<#else>
				您的邮件激活失败,失败原因是:${msg}!
			</#if>
		</h1>
	</div>
	
		
	<!-- 网页版权 -->
	<div class="container-foot-2">
		<div class="context">
			<div class="left">
				<p>演示</p>
				<p>版权所有：&emsp;公司名</p>
				<p>地&emsp;&emsp;址：&emsp;xxx</p>
				<p>电&emsp;&emsp;话： xxx&emsp;&emsp;
					邮箱：&emsp;service@xxx.com</p>
				<p>
					<a href="http://www.miitbeian.gov.cn" style="color: #ffffff">ICP备案
						：xx</a>
				</p>
				<p>
					<a href="http://www.gzjd.gov.cn/wlaqjc/open/validateSite.do" style="color: #ffffff">穗公网安备：xxx</a>
				</p>
			</div>
			<div class="right">
				<a target="_blank" href="http://weibo.com/ITxiaomage"><img
					src="/images/sina.png"></a>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</body>
</html>