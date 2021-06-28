<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="utf-8">
 <title>登录</title>
 <meta name="renderer" content="webkit">
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
 <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
 <style>

  #canvas {
    float: center;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 5px;
    cursor: pointer;
  }
 </style>
</head>
  
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<body>
<fieldset class="layui-elem-field layui-field-title">
  <legend>欢迎使用 - 学生成绩管理平台</legend>
</fieldset>
<form action="${pageContext.request.contextPath}/Login" method="post" class="layui-form layui-form-pane" style="margin:0px auto;width: 500px;margin-top: 10%">
  <div class="layui-form-item">
    <label class="layui-form-label">账号框</label>
    <div class="layui-input-block">
      <input type="text" name="ID"   lay-verify="required" placeholder="请输入账号" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密码框</label>
    <div class="layui-input-inline">
      <input type="password" name="password"  lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
  </div>
<div class="layui-form-item">
    <label class="layui-form-label">身份框</label>
    <div class="layui-input-block">
      <select name="identify" lay-verify="required">
        <option value="">请选择身份</option>
        <option value="Student">学生</option>
        <option value="Teacher">教师</option>
        <option value="Dean">教务员</option>
      </select>
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">验证码框</label>
    <div class="layui-input-inline">
      <input type="text" name="captcha"  lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input">
    </div>
        <div class="layui-inline">
           <canvas id="canvas" width="100" height="45"></canvas>
    </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn layui-btn-normal" lay-submit lay-filter="login">登录</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
</body>
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
		setInterval("getNowTime()", 1000);
	</script>
<script>
  layui.use('form', function () {
    var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
    var layer = layui.layer;
    form.render();
    var show_num = [];
    draw(show_num);
    $("#canvas").on('click',function(){
      draw(show_num);
    })
    form.on('submit(login)', function(data){
    	data = data.field;
    	var num = show_num.join("");
    	var verify = data.captcha;
    	if(verify!=num){
    		layer.msg('验证码输入错误');
    		draw(show_num);
    		return false;
    	}
    	return true;
    })
    var error = "<%=request.getAttribute("error") %>";
    if(error != "null"){
        layer.msg(error);
    }
});
</script>
<script>
  function draw(show_num) {
    var canvas_width=$('#canvas').width();
    var canvas_height=$('#canvas').height();
    var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
    var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
    canvas.width = canvas_width;
    canvas.height = canvas_height;
    var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
    var aCode = sCode.split(",");
    var aLength = aCode.length;//获取到数组的长度
     
    for (var i = 0; i <= 3; i++) {
      var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
      var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
      var txt = aCode[j];//得到随机的一个内容
      show_num[i] = txt;
      var x = 10 + i * 20;//文字在canvas上的x坐标
      var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
      context.font = "bold 23px 微软雅黑";
  
      context.translate(x, y);
      context.rotate(deg);
  
      context.fillStyle = randomColor();
      context.fillText(txt, 0, 0);
  
      context.rotate(-deg);
      context.translate(-x, -y);
    }
    for (var i = 0; i <= 5; i++) { //验证码上显示线条
      context.strokeStyle = randomColor();
      context.beginPath();
      context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
      context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
      context.stroke();
    }
    for (var i = 0; i <= 30; i++) { //验证码上显示小点
      context.strokeStyle = randomColor();
      context.beginPath();
      var x = Math.random() * canvas_width;
      var y = Math.random() * canvas_height;
      context.moveTo(x, y);
      context.lineTo(x + 1, y + 1);
      context.stroke();
    }
  }
  
  function randomColor() {//得到随机的颜色值
    var r = Math.floor(Math.random() * 256);
    var g = Math.floor(Math.random() * 256);
    var b = Math.floor(Math.random() * 256);
    return "rgb(" + r + "," + g + "," + b + ")";
  }
</script>
</html>