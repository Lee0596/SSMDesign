<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@ page import="java.util.*"%>
    <%@ page import="jmu.lzz.vo.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="utf-8">
 <title>学生成绩管理平台</title>
 <meta name="renderer" content="webkit">
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
 <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</head>

<body class="layui-layout-body">
<%
	List allCou = null ;
	allCou = (List)request.getAttribute("couList") ;
	List allElect = null ;
	allElect = (List)request.getAttribute("electList") ;
	int couCount = allCou.size();
%>
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">学生成绩管理平台-${sessionScope.uidentify}</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-right">
                      <li class="layui-nav-item">
                   <li class="layui-nav-item">
        <a href="javascript:;">
        ${sessionScope.college.getCollegeName()}
        </a>
        </li>
          <li class="layui-nav-item">
        <a href="javascript:;">
        ${sessionScope.stuClass.getClassName()}
        </a>
        </li>
      <li class="layui-nav-item">
        <a href="javascript:;">
           ${sessionScope.uname}
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="javascript:void(0);"onclick="javascript:logout();">注销</a></li>
    </ul>
  </div>
  
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
<div class="layui-inline">
<button class="layui-btn" id="back">返回上一页</button>
  </div>
  <table class="layui-table">
  <thead>
  <tr>
  <th >课程号</th>
  <th >课程名</th>
  <th >学分</th>
  <th >学时</th>
  <th >修习类别</th>
  <th >考试状态</th>
  <th >总评成绩</th>
  </tr>
</thead>
  <%
  	for(int i=0;i<couCount;i++){
  		Cou cou = (Cou)allCou.get(i);
  		String couID = cou.getCouID();
  		String couName = cou.getCouName();
  		double couCredit = cou.getCouCredit();
  		double couHours = cou.getCouHours();
  		String couStyle = cou.getCouStyle();
  		Elect elect = (Elect)allElect.get(i);
  		String electStasus = elect.getElectStasus();
  		String finalGrade = elect.getFinalGrade();
  		%>
  		<tr>
  		 <th ><%=couID %></th>
  		 <th ><%=couName %></th>
  		 <th ><%=couCredit %></th>
  		 <th ><%=couHours %></th>
  		 <th ><%=couStyle %></th>
  		 <th ><%=electStasus %></th>
  		 <th ><%=finalGrade %></th>
  		</tr>
  		
  		<%	
  	}
  %>
  </table>
  
  </div> 
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    @Design By ChuenchangLee
  </div>
</div>
<script>
layui.use(['form','layer','table'], function(){
    var table = layui.table
    ,form = layui.form,$=layui.$;
    $("#back").click(function(){
  	  location.href="${pageContext.request.contextPath}/Student/showAll";  
    })
});
</script>
<script type="text/javascript">
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
    <script type="text/javascript">
    function logout(){
		layui.use(['layer', 'form'], function(){
			var layer = layui.layer,form = layui.form;
			layer.open({
		        type: 1
		        ,title: "注意" //显示标题栏
		        ,closeBtn: false
		        ,area: '300px;'
		        ,shade: 0.8
		        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
		        ,btn: ['确定', '取消']
		        ,btnAlign: 'c'
		        ,moveType: 1 //拖拽模式，0或者1
		        ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">注意！<br><br>您将要注销登录，请确定是否要注销登录！<br><br>注销登陆后您将回到登录页面</div>'
		        ,success: function(layero){
			      var btn = layero.find('.layui-layer-btn');
			      btn.find('.layui-layer-btn0').attr({
				  href: "logout"
			     });
			   }
			});
		})
  }
  </script>
  <script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
});
</script>
</body>
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
</html>