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
List allStudent = null ;
allStudent = (List)request.getAttribute("studentList") ;
List allExam = null ;
allExam = (List)request.getAttribute("examList") ;
int examCount = allExam.size();
%>
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">学生成绩管理平台-${sessionScope.uidentify}</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-right">
    
                  <li class="layui-nav-item">
        <a href="javascript:;">
           <%=session.getAttribute("uidentify") %>
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
<div class="layui-inline">
<button class="layui-btn" id="back">返回上一页</button>
</div>
<div class="layui-inline">
<label class="layui-form-label" style="width: auto;">课程名：${sessionScope.cou.getCouName()}</label>
<label class="layui-form-label" style="width: auto;">学分：${sessionScope.cou.getCouCredit()}</label>
<label class="layui-form-label" style="width: auto;">学时：${sessionScope.cou.getCouHours()}</label>
<label class="layui-form-label" style="width: auto;">修习类别：${sessionScope.cou.getCouStyle()}</label>
</div>
</div>
<form action="${pageContext.request.contextPath}/Teacher/entryGradeUpload" method="post" class="layui-form layui-form-pane">
<div class="layui-form-item">
<div class="layui-input-block">
<div class="layui-inline">
 <label class="layui-form-label" style="width: 90px;">学号</label>
</div>
<div class="layui-inline">
 <label class="layui-form-label" style="width: 90px;">班级号</label>
</div>
<div class="layui-inline">
 <label class="layui-form-label" style="width: 90px;">姓名</label>
</div>
<%
Iterator examIter = allExam.iterator() ;
while(examIter.hasNext()){
	Exam exam = (Exam)examIter.next();
	String examName = exam.getExamName();
	String examRadio = String.valueOf(exam.getExamRadio()*100);
	%>
	<div class="layui-inline">
 <label class="layui-form-label" style="width: 140px;"><%=examName %><%=examRadio %>%</label>
  <input type="hidden" class="layui-input" name="examName" value="<%=examName.toString()%>">
   </div>
	<%
}
%>
<div class="layui-inline">
 <label class="layui-form-label" style="width: 90px;">考试状态</label>
</div>
<div class="layui-inline">
 <label class="layui-form-label" style="width: 90px;">总评成绩</label>
</div>
</div>
</div>
<%
 Iterator studentIter = allStudent.iterator() ;
  while(studentIter.hasNext()){
	Student student = (Student)studentIter.next() ;
	String stuID = student.getStuID();
	String classID = student.getClassID();
	String stuName = student.getStuName();
	%>
<div class="layui-form-item">
<div class="layui-input-block">
<div class="layui-inline">
 <label class="layui-form-label" style="width: 90px;"><%=stuID %></label>
 <input type="hidden" class="layui-input" name="stuID" value="<%=stuID.toString()%>">
</div>
<div class="layui-inline">
 <label class="layui-form-label" style="width: 90px;"><%=classID %></label>
</div>
<div class="layui-inline">
 <label class="layui-form-label" style="width: 90px;"><%=stuName %></label>
</div>
<%
  	for(int i=0;i<examCount;i++){
  		String examName ="exam"+i;
  		%>
  		<div class="layui-inline">
       <input type="text" lay-verify="required" class="layui-input" style="width: 140px;" name="<%=examName %>">
        </div>
  		<%
  	}
%>
		<div class="layui-inline"  style="width: 90px;">
        <select name="electStasus" lay-verify="required">
        <option value="正常">正常</option>
        <option value="缺考">缺考</option>
        <option value="作弊">作弊</option>
      </select>
        </div>
  		<div class="layui-inline">
       <input type="text" lay-verify="required" class="layui-input" style="width: 90px;" name="finalGrade">
        </div>
</div>
</div>
<%
}
%>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn layui-btn-normal" lay-submit lay-filter="entry">登记</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
  </div>
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    @Design By ChuenchangLee
  </div>
</div>
<script>
layui.use(['form','layer','table'], function () {
    var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
    var layer = layui.layer;
    var $=layui.$;
    form.render();
    form.on('submit(entry)', function(data){
    	return true;
    });
    $("#back").click(function(){
      location.href="${pageContext.request.contextPath}/Teacher/showStudy?termDate=${sessionScope.termDate}";  
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