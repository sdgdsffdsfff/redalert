<html>
	<head>
		<title>正在运行的任务</title>
	</head>
	<body>
		<p><h2>正在运行的任务</h2></p>
		<#include "/task/task_navigation_comm.ftl">
		<p>总计:${total}</p>
		<table border=1>
			<tr>
				<td>任务编码</td>
				<td>beanId</td>
				<td>任务类型</td>
				<td>任务名称</td>
				<td>从属分组</td>
				<td>状态</td>
				<td>表达式</td>
				<td>domainId</td>
			</tr>
			
			<#list regList as alertDTO>
				<tr>
					<td>${alertDTO.jobId}</td>
					<td>${alertDTO.beanId}</td>
					<td>${alertDTO.taskType}</td>
					<td>${alertDTO.jobName}</td>
					<td>${alertDTO.jobGroup}</td>
					<td>${alertDTO.jobStatus}</td>
					<td>${alertDTO.cronExpression}</td>
					<td>${alertDTO.domainId}</td>
			</tr>
			</#list>
			
		</table>
	</body>
</html>