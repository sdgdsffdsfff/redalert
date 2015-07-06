<html>
	<head>
		<title>任务运行情况</title>
	</head>
	<body>
		<p><h2>任务运行情况</h2></p>
		<#include "/task/task_navigation_comm.ftl">
		<p>总计:${total}</p>
		<p>主机层</p>
		<table border=1>
			<tr>
				<td>序号</td>
				<td>任务编码</td>
				<td>beanId</td>
				<td>任务类型</td>
				<td>任务名称</td>
				<td>从属分组</td>
				<td>状态</td>
				<td>表达式</td>
				<td>domainId</td>
				<td>最近一次运行时间</td>
			</tr>
			<#assign x=0>
			<#list regList as alertDTO>
				<#if "1"=="${alertDTO.taskType}" >
					<tr <#if "No Run"=="${alertDTO.lastRunDate}">style="color:red"</#if> >
						<td><#assign x=1+x>${x}</td>
						<td>${alertDTO.jobId}</td>
						<td>${alertDTO.beanId}</td>
						<td>${alertDTO.taskType}</td>
						<td>${alertDTO.jobName}</td>
						<td>${alertDTO.jobGroup}</td>
						<td>${alertDTO.jobStatus}</td>
						<td>${alertDTO.cronExpression}</td>
						<td>${alertDTO.domainId}</td>
						<td>${alertDTO.lastRunDate}</td>
					</tr>
				</#if>
			</#list>
			
		</table>
		
		<p>ORACLE层</p>
		<table border=1>
			<tr>
				<td>序号</td>
				<td>任务编码</td>
				<td>beanId</td>
				<td>任务类型</td>
				<td>任务名称</td>
				<td>从属分组</td>
				<td>状态</td>
				<td>表达式</td>
				<td>domainId</td>
				<td>最近一次运行时间</td>
			</tr>
			<#assign x=0>
			<#list regList as alertDTO>
				<#if "2"=="${alertDTO.taskType}" >
					<tr <#if "No Run"=="${alertDTO.lastRunDate}">style="color:red"</#if> >
						<td><#assign x=1+x>${x}</td>
						<td>${alertDTO.jobId}</td>
						<td>${alertDTO.beanId}</td>
						<td>${alertDTO.taskType}</td>
						<td>${alertDTO.jobName}</td>
						<td>${alertDTO.jobGroup}</td>
						<td>${alertDTO.jobStatus}</td>
						<td>${alertDTO.cronExpression}</td>
						<td>${alertDTO.domainId}</td>
						<td>${alertDTO.lastRunDate}</td>
					</tr>
				</#if>
			</#list>
			
		</table>
		
		<p>MySQL层</p>
		<table border=1>
			<tr>
				<td>序号</td>
				<td>任务编码</td>
				<td>beanId</td>
				<td>任务类型</td>
				<td>任务名称</td>
				<td>从属分组</td>
				<td>状态</td>
				<td>表达式</td>
				<td>domainId</td>
				<td>最近一次运行时间</td>
			</tr>
			<#assign x=0>
			<#list regList as alertDTO>
				<#if "3"=="${alertDTO.taskType}" >
					<tr <#if "No Run"=="${alertDTO.lastRunDate}">style="color:red"</#if> >
						<td><#assign x=1+x>${x}</td>
						<td>${alertDTO.jobId}</td>
						<td>${alertDTO.beanId}</td>
						<td>${alertDTO.taskType}</td>
						<td>${alertDTO.jobName}</td>
						<td>${alertDTO.jobGroup}</td>
						<td>${alertDTO.jobStatus}</td>
						<td>${alertDTO.cronExpression}</td>
						<td>${alertDTO.domainId}</td>
						<td>${alertDTO.lastRunDate}</td>
					</tr>
				</#if>
			</#list>
			
		</table>
		
		
		<p>KeepAlived层</p>
		<table border=1>
			<tr>
				<td>序号</td>
				<td>任务编码</td>
				<td>beanId</td>
				<td>任务类型</td>
				<td>任务名称</td>
				<td>从属分组</td>
				<td>状态</td>
				<td>表达式</td>
				<td>domainId</td>
				<td>最近一次运行时间</td>
			</tr>
			<#assign x=0>
			<#list regList as alertDTO>
				<#if "4"=="${alertDTO.taskType}" >
					<tr <#if "No Run"=="${alertDTO.lastRunDate}">style="color:red"</#if> >
						<td><#assign x=1+x>${x}</td>
						<td>${alertDTO.jobId}</td>
						<td>${alertDTO.beanId}</td>
						<td>${alertDTO.taskType}</td>
						<td>${alertDTO.jobName}</td>
						<td>${alertDTO.jobGroup}</td>
						<td>${alertDTO.jobStatus}</td>
						<td>${alertDTO.cronExpression}</td>
						<td>${alertDTO.domainId}</td>
						<td>${alertDTO.lastRunDate}</td>
					</tr>
				</#if>
			</#list>
			
		</table>
	</body>
</html>