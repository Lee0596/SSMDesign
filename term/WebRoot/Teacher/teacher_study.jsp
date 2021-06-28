<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<div class="layui-form" style="margin: 5%; display: none" id="window" >	
	<form class="layui-form" id="addStudy" method="post" lay-filter="example">
<div class="layui-form-item">
    <label class="layui-form-label"style="width: 70px">考试方式</label>
    <div class="layui-input-block">
      <select id="studyWay">
        <option value="闭卷考试">闭卷考试</option>
        <option value="开卷考试">开卷考试</option>
        <option value="论文">论文</option>
        <option value="实习报告">实习报告</option>
      </select>
    </div>
  </div>
   <div class="layui-form-item">
    <label class="layui-form-label">成绩方式</label>
    <div class="layui-input-block" >
      <select id="studyStyle">
        <option value="百分制">百分制</option>
        <option value="等级制">等级制</option>
        <option value="合格制">合格制</option>
      </select>
    </div>
  </div>
   <div class="layui-form-item">
     <label class="layui-input-block">成绩比例(不填默认不选)</label>
   </div>
 <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">平时成绩：</label>
                    <div class="layui-inline">
                        <input type="text" class="layui-input" id="exam1">
                    </div>
                </div>
                  <div class="layui-inline">
                    <label class="layui-form-label" style="width: 150px">占比(百分值%)：</label>
                </div>
            </div>
             <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">实验成绩：</label>
                    <div class="layui-inline">
                        <input type="text" class="layui-input" id="exam2">
                    </div>
                </div>
                  <div class="layui-inline">
                    <label class="layui-form-label" style="width: 150px">占比(百分值%)：</label>
                </div>
            </div>
             <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">理论成绩：</label>
                    <div class="layui-inline">
                        <input type="text" class="layui-input" id="exam3">
                    </div>
                </div>
                  <div class="layui-inline">
                    <label class="layui-form-label" style="width: 150px">占比(百分值%)：</label>
                </div>
            </div>
             <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">口试成绩：</label>
                    <div class="layui-inline">
                        <input type="text" class="layui-input" id="exam4">
                    </div>
                </div>
                  <div class="layui-inline">
                    <label class="layui-form-label" style="width: 150px">占比(百分值%)：</label>
                </div>
            </div>
             <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">上机成绩：</label>
                    <div class="layui-inline">
                        <input type="text" class="layui-input" id="exam5">
                    </div>
                </div>
                  <div class="layui-inline">
                    <label class="layui-form-label" style="width: 150px">占比(百分值%)：</label>
                </div>
            </div>
             <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">期考成绩：</label>
                    <div class="layui-inline">
                        <input type="text" class="layui-input" id="exam6">
                    </div>
                </div>
                  <div class="layui-inline">
                    <label class="layui-form-label" style="width: 150px">占比(百分值%)：</label>
                </div>
            </div>
	</form>
</div>

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
<button class="layui-btn" id="back">返回上一页</button>
<table class="layui-hide" id="studyTable" lay-filter="studyList"></table>
<script type="text/html" id="toolBar">
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit">设置</a>
  <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="entry">登记</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="report">录入</a>
</script>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    @Design By ChuenchangLee
  </div>
</div>
<script>
layui.use(['form','layer','table'], function(){
          var table = layui.table
          ,form = layui.form
          ,$=layui.$;
          table.render({
            elem: '#studyTable'  //绑定table id
            ,url:'selectAllStudyBypage'  //数据请求路径
            ,method:'post'
            ,cellMinWidth: 100
            ,cols: [[
                   {field:'studyID', width:100, title: '开课班级号', sort: true}
                  ,{field:'couID', width:100, title: '课程号', sort: true}
                  ,{field:'studyStatus', width:100, title: '开课性质', sort: true}
                  ,{field:'studyWay', width:100, title: '考试方式', sort: true,templet: function (d) {
                      if (d.studyWay == false || d.studyWay == ""){
                          return '<a style="color:#ed2a4a">未设置</a>'
                      }else {
                          return d.studyWay
                      }
                  }}
                  ,{field:'studyStyle', width:100, title: '成绩方式', sort: true,templet: function (d) {
                      if (d.studyStyle == false || d.studyStyle == ""){
                          return '<a style="color:#ed2a4a">未设置</a>'
                      }else {
                          return d.studyStyle
                      }
                  }}
                  ,{field:'studyEntry', width:100, title: '成绩登记', sort: true,templet: function (d) {
                      if (d.studyEntry == false || d.studyEntry == ""){
                          return '<a style="color:#ed2a4a">未登记</a>'
                      }else {
                          return "已登记"
                      }
                  }}
                  ,{field:'studyReport', width:100, title: '成绩录入', sort: true,templet: function (d) {
                      if (d.studyReport == false || d.studyReport == ""){
                          return '<a style="color:#ed2a4a">未录入</a>'
                      }else {
                          return "已录入"
                      }
                  }}
                  ,{field:'deadLine', width:200, title: '截止时间', sort: true}
                  ,{fixed: 'right',title: '操作', width:250,  align:'center', toolbar: '#toolBar'}//一个工具栏  具体请查看layui官网
            ]]
        	,text: {
        	    none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
        	  }
            ,page: true   //开启分页
            ,limit:10   //默认十条数据一页
            ,limits:[10,20,30,50]  //数据分页条
            ,id: 'testReload'  
          });
          table.on('tool(studyList)', function(obj) {
      		var data = obj.data;
      		json = JSON.stringify(data);
      		switch(obj.event) {
      			case 'detail':
      				location.href = "<%=request.getContextPath() %>/Teacher/showGrade?studyID=" + data.studyID;
      				break;
      			case 'edit':
      				if(data.studyReport != false || data.studyReport != ""){
      					layer.msg("成绩已录入");
      				}else{
                    	  var editStudyLayer = layer.open({
                			  type: 1, 
                			  title:"成绩设置",
                			  area:['60%','70%'],
                			  btn: ['确定', '取消'], 
                			  content: $("#window"),
            						yes:function(index,layero){
                		  			var studyWay = $("#studyWay").val();
                 			        var studyStyle = $("#studyStyle").val();
                 			        var studyID = data.studyID;
                 			        var exam1 = $("#exam1").val();
                 			        var exam2 = $("#exam2").val();
                			        var exam3 = $("#exam3").val();
                 			        var exam4 = $("#exam4").val();
                			        var exam5 = $("#exam5").val();
                 			        var exam6 = $("#exam6").val();
                			        var arrayName = new Array();
                			        var arrayValue = new Array();
                			        if(exam1 !=''){
                			        	arrayName.push('平时成绩');
                			        	arrayValue.push(exam1);
                			        }
                			        if(exam2 !=''){
                			        	arrayName.push('实验成绩');
                			        	arrayValue.push(exam2);
                			        }
                			        if(exam3 !=''){
                			        	arrayName.push('理论成绩');
                			        	arrayValue.push(exam3);
                			        }
                			        if(exam4 !=''){
                			        	arrayName.push('口试成绩');
                			        	arrayValue.push(exam4);
                			        }
                			        if(exam5 !=''){
                			        	arrayName.push('上机成绩');
                			        	arrayValue.push(exam5);
                			        }
                			        if(exam6 !=''){
                			        	arrayName.push('期考成绩');
                			        	arrayValue.push(exam6);
                			        }
                 			      $.ajax({
                 			         url:"<%=request.getContextPath() %>/Teacher/editStudy",
                 			         data:{
                 			        	studyWay:studyWay,
                 			        	studyStyle:studyStyle,
                 			        	studyID:studyID,
                 			        	arrayName:arrayName,
                 			        	arrayValue:arrayValue,
                 			         },
                 			         type : 'POST',
                 			         dataType : 'json',
                 			         success:function(data){
                 			        	 if(data.result=="success"){
                      			        	layer.msg("设置成功"); 
                     			        	layer.close(editStudyLayer);
                 			        	 }else{
                 			        		layer.msg(data.result); 
                 			        	 }
                 			         },
             	         		     error:function(data){
                			        	layer.msg(data.result);
             	         		   }
                 			      })
                               }
                			}); 
      				}
      				break;
      			case 'entry':
      				if(data.studyReport != false || data.studyReport != "")
      				{
      					layer.msg("成绩已录入");
      				}else{
          				if(data.studyStyle == false || data.studyStyle == ""){
          					layer.msg("您还未进行成绩设置");
          				}else{
              				location.href = "<%=request.getContextPath() %>/Teacher/entryGrade?studyID=" + data.studyID;
          				}
      				}
      				break;
      			case 'report':
      				if(data.studyEntry == false || data.studyEntry == ""){
      					layer.msg("您还未登记成绩");
      				}else{
      					layui.use(['layer', 'form'], function(){
      						var layer = layui.layer,form = layui.form;
      						layer.open({
      					        type: 1
      					        ,title: "录入成绩" //显示标题栏
      					        ,closeBtn: false
      					        ,area: '300px;'
      					        ,shade: 0.8
      					        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
      					        ,btn: ['确定', '取消']
      					        ,btnAlign: 'c'
      					        ,moveType: 1 //拖拽模式，0或者1
      					        ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">确定要录入成绩？成绩录入后将不可再设置成绩和登记成绩!</div>'
      					        ,yes: function(layero){
      					        	location.href = "<%=request.getContextPath() %>/Teacher/reportGrade?studyID=" + data.studyID;
       						   }
      						});
      					})
      				}
      				break;
      		}
      	}); 
          $("#back").click(function(){
        	  location.href="${pageContext.request.contextPath}/Teacher/showAll";  
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