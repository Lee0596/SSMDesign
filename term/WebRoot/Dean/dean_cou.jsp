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
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">学生成绩管理平台-${sessionScope.uidentify}</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-right">
              <li class="layui-nav-item">
        <a href="javascript:;">
        ${sessionScope.college.getCollegeName()}
        </a>
        </li>
          <li class="layui-nav-item">
        <a href="javascript:;">
        ${sessionScope.room.getRoomName()}
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
      <li class="layui-nav-item"><a href="javascript:void(0);"onclick="javascript:logout();">退出</a></li>
    </ul>
  </div>
  
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">信息录入</a>
          <dl class="layui-nav-child">
            <dd id="uploadClass"><a href="javascript:;">班级录入</a></dd>
            <dd id="uploadStu"><a href="javascript:;">学生录入</a></dd>
            <dd id="uploadCou"><a href="javascript:;">课程录入</a></dd>
          </dl>
        </li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->

<table class="layui-hide" id="couTable" lay-filter="couList"></table>
<script type="text/html" id="toolBar">
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
</script>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    @Design By ChuenchangLee
  </div>
</div>
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
layui.use('upload', function(){
	  var $ = layui.jquery
	  ,upload = layui.upload;
	  //指定允许上传的文件类型
	  upload.render({
	    elem: '#uploadStu' 
	    ,url: 'uploadStu' //改成您自己的上传接口
	    ,accept: 'file' //普通文件
	    ,exts:'xls|xlsx'
	    ,done: function(json){
			layer.msg(json.msg);
	    }
	  });
	});
</script>
<script type="text/javascript">
layui.use('upload', function(){
	  var $ = layui.jquery
	  ,upload = layui.upload;
	  //指定允许上传的文件类型
	  upload.render({
	    elem: '#uploadClass' 
	    ,url: 'uploadClass' //改成您自己的上传接口
	    ,accept: 'file' //普通文件
	    ,exts:'xls|xlsx'
	    ,done: function(json){
			layer.msg(json.msg);
	    }
	  });
	});
</script>
<script type="text/javascript">
layui.use('upload', function(){
	  var $ = layui.jquery
	  ,upload = layui.upload;
	  //指定允许上传的文件类型
	  upload.render({
	    elem: '#uploadCou' 
	    ,url: 'uploadCou' //改成您自己的上传接口
	    ,accept: 'file' //普通文件
	    ,exts:'xls|xlsx'
	    ,done: function(json){
			layer.msg(json.msg);
	    }
	  });
	});
</script>
<script>
layui.use(['form','layer','table'], function(){
          var table = layui.table
          ,form = layui.form,$=layui.$;
          table.render({
            elem: '#couTable'  //绑定table id
            ,url:'selectCouBypage'  //数据请求路径
            ,method:'post'
            ,cellMinWidth: 100
            ,cols: [[
                  {field:'couID', width:150, title: '课程号', sort: true}
                  ,{field:'roomID', width:150, title: '教研室编号', sort: true}
                  ,{field:'couName', width:150, title: '课程名', sort: true}
                  ,{field:'couCredit', width:100, title: '学分', sort: true}
                  ,{field:'couHours', width:150, title: '学时', sort: true} //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
                  ,{field:'couStyle', width:100, title: '修习类别', sort: true}
                  ,{fixed: 'right',title: '操作', width:180,      align:'center', toolbar: '#toolBar'}//一个工具栏  具体请查看layui官网
            ]]
        	,text: {
        	    none: '暂无相关数据' //默认：无数据。注：该属性为 layui 2.2.5 开始新增
        	  }
            ,page: true   //开启分页
            ,limit:10   //默认十条数据一页
            ,limits:[10,20,30,50]  //数据分页条
            ,id: 'testReload'  
          });
          table.on('tool(couList)', function(obj) {
        		var data = obj.data;
        		json = JSON.stringify(data);
        		switch(obj.event) {
        			case 'detail':
        				location.href = "<%=request.getContextPath() %>/Dean/showStudy?couID=" + data.couID;
        				;
        				break;
        		}
        	}); 
        	});
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