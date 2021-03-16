<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=path%>/resource/bs/js/jquery-1.11.1.js"></script>	<!-- 引入jquery -->
<link rel="stylesheet" href="<%=path%>/resource/bs/css/bootstrap.css"> <!-- 引入bootstrap.css -->
<script src="<%=path%>/resource/bs/js/bootstrap.min.js"></script><!-- 引入bootstrap.min.js -->
<script src="<%=path%>/resource/lib/echarts/echarts.js"></script><!-- 引入echarts.js -->
<title>用户列表</title>
</head>
<body>
	<section class="container">
		<div id="main" style="width: 600px;height:400px;"></div>
	</section>
</body>

<script type="text/javascript">

// 数据组装
var gdata = []
var gdatatext = []
<c:forEach items="${ gradesList }" var="list">
	gdatatext.push('成绩' + ${list})
	gdata.push(${list})
</c:forEach>
	console.log(gdatatext)
	console.log(gdata)
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

// 指定图表的配置项和数据
var option = {
    title: {
        text: '成绩数据可视化'
    },
    tooltip: {},
    legend: {
        data: ['成绩']
    },
    xAxis: {
        data: gdatatext
    },
    yAxis: {},
    series: [{
        name: '成绩',
        type: 'bar',
        data: gdata
    }]
};

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);
</script>
</html>