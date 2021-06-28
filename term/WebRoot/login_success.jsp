<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>欢迎使用</title>
 <meta name="renderer" content="webkit">
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
 <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>
<body>
<script type="text/javascript"
		src="https://cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.min.js"></script>
<fieldset class="layui-elem-field layui-field-title">
  <legend>欢迎登录 - 学生成绩管理平台</legend>
</fieldset>
<blockquote class="layui-elem-quote layui-quote-nm" >
  欢迎<font color="red" size="10"> <%=session.getAttribute("uname")%> </font>登录学生成绩管理平台
  <br><br>您的身份为  <font color="blue" size="10"><%=session.getAttribute("uidentify")%></font>
  <br><br><h3><a href="<%=session.getAttribute("identify") %>/showAll">点此进入管理平台</a></h3>
</blockquote>
<script type="text/javascript"
		src="https://cdn.bootcss.com/canvas-nest.js/1.0.1/canvas-nest.min.js"></script>
	<div style="position: absolute; right: 10px; bottom: 5px" id="times"></div>
	<script type="text/javascript">
		function getNowTime() {
			//当前时间
			var date = new Date();
			//格式化时间
			var localedata = date.toLocaleString();
			//获取div标签
			var divtime = document.getElementById("times");
			//写入内容	
			divtime.innerHTML = "当前时间："+localedata;
		}
		//利用定时器,动态刷新时间
		setInterval("getNowTime()", 1000)
	</script>
	<script>
	if("<%=session.getAttribute("uname")%>"=="null"){
		layui.use(['layer', 'form'], function(){
			var layer = layui.layer,form = layui.form;
			layer.open({
		        type: 1
		        ,title: "警告" //显示标题栏
		        ,closeBtn: false
		        ,area: '300px;'
		        ,shade: 0.8
		        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
		        ,btn: ['确定']
		        ,btnAlign: 'c'
		        ,moveType: 1 //拖拽模式，0或者1
		        ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">警告！<br><br>检测到您还未登录，请先返回登陆页面进行登录后再行操作！<br><br>感谢您的配合！</div>'
		        ,success: function(layero){
		          var btn = layero.find('.layui-layer-btn');
		          btn.find('.layui-layer-btn0').attr({
		            href: "<%=request.getContextPath() %>/login.jsp"
		          });
		        }
			});
		})
	}
	</script>
</body>
</html>