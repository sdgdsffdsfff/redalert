<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<script type="text/javascript" src="${webRoot}/script/jquery-1.8.3.min.js"></script>
	<script src="${webRoot}/script/highcharts/js/highcharts.js"></script>
	<script src="${webRoot}/script/highcharts/js/modules/exporting.js"></script>
	<script src="${webRoot}/script/quota/monitor.js"></script>
	
	<script src="${webRoot}/script/My97DatePicker/WdatePicker.js"></script>
	
	<style type="text/css">
<!--
.searchform ul{list-style:none;} 
.searchform li{float:left;} 

-->
</style>
	
	<script>
		 
		$(document).ready(function() {
			
        });
        
	</script>
	
	<title>event 列表</title>
	
</head>
<body>

	<p>SampleTime : ${sampleTime} </p>
	<table border=1>
		<tr>
			<td>序号</td>
			<td>sqlId</td>
			<td>事件</td>
			<td>sessionId</td>
			<td>P1</td>
			<td>P2</td>
			<td>P3</td>
		</tr>
		
		<#list eventList as eventDTO>
          <tr>
          	<td>${eventDTO_index+1}</td>
          	<td><a href="${webRoot}/report/stat/oracle/sqlstat/view?dbId=${dbId}&sqlid=${eventDTO.sqlId}&sampletime=${sampleTime}" target="_blank">${(eventDTO.sqlId)}</a></td>
          	<td>${(eventDTO.event)}</td>
          	<td>${(eventDTO.sessionId)}</td>
          	<td>${(eventDTO.pOne)}</td>
          	<td>${(eventDTO.pTwo)}</td>
          	<td>${(eventDTO.pThree)}</td>
          </tr>
         </#list>
		
	</table>
	
</body>
</html>	