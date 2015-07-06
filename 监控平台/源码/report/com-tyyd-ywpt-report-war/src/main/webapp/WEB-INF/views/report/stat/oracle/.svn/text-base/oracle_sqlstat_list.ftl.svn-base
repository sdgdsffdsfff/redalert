<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<script type="text/javascript" src="${webRoot}/script/jquery-1.8.3.min.js"></script>
	<script src="${webRoot}/script/highcharts/js/highcharts.js"></script>
	<script src="${webRoot}/script/highcharts/js/modules/exporting.js"></script>
	<script src="${webRoot}/script/quota/monitor.js"></script>
	
	<style type="text/css">
<!--
.searchform ul{list-style:none;} 
.searchform li{float:left;} 

-->

table 
{ 
border-collapse: collapse; 
border: none; 
width: 200px; 
} 
td 
{ 
border: solid #000 1px; 
} 
</style> 
	
	
	
	<script>
		 
		$(document).ready(function() {
			//加载监控主机列表
		     loadMonitorsOptionSelected("${webRoot}","oracle_monitors",2,"${(dbId)}");
		     
		     //添加查询事件
		     jQuery("#query_btn").attr("onclick","refresh()");
		     
		     jQuery("#sqlId").val("${sqlId}");
		     jQuery("#times").val("${times}");
		     
			 
			 
        });
        
       function refresh(){
	     	var dbId = $('#oracle_monitors').children('option:selected').val();
	     	if(dbId == '' || dbId == undefined){
     			return;
     		}
     		
	     	var sqlId = jQuery("#sqlId").val();
	     	if(sqlId == '' || sqlId == undefined){
     			return;
     		}
	     	
	     	var times = jQuery("#times").val();	
	     	if(times == '' || times == undefined){
     			return;
     		}
	     	
	     	
	     	
	     	document.searchform.submit();
	     	
	     	
	   }
		     
		     
		     
	</script>
	
	<title>SQL STAT报表</title>
	
</head>
<body>

	<!-- 主机 -->
	
	<div class="conditon_div">
		<form name="searchform" class="searchform" action="${webRoot}/report/stat/oracle/sqlstat/sqlstatlistview">
		<ul>
			<li>监控机 :</li>
			<li>
				<select id="oracle_monitors" name="oracle_monitors">
					<option seleted>选择</option>
				</select>
			</li>
			
			<li>SQL_ID：</li>
			<li><input type="text" id="sqlId" name="sqlId"/></li>
			
			<li>TIMES：</li>
			<li><input type="text" id="times" name="times"/></li>
			
			<li><input type="button" value="查询" id="query_btn"></li>
		</ul>
		</form>
	</div>
	

	<table>
		<tr>
			<td>序号</td>
			<td>beginTime</td>
			<td>snapId</td>
			<td>instanceId</td>
			<td>hashValue</td>
			<td>executions</td>
			<td>bufferGets</td>
			<td>diskReads</td>
			<td>cpuTime</td>
			<td>elapsedTime</td>
			<td>iowait</td>
			<td>clwait</td>
			<td>ccwait</td>
			<td>dwwait</td>
		</tr>
		
		 <#list list as alertDTO>
		<tr>
			<td>${alertDTO_index+1}</td>
			<td>${alertDTO.beginTime}</td>
			<td>${alertDTO.snapId}</td>
			<td>${alertDTO.instanceId}</td>
			<td>${alertDTO.hashValue}</td>
			<td>${alertDTO.executions}</td>
			<td>${alertDTO.bufferGets}</td>
			<td>${alertDTO.diskReads}</td>
			<td>${alertDTO.cpuTime}</td>
			<td>${alertDTO.elapsedTime}</td>
			<td>${alertDTO.iowait}</td>
			<td>${alertDTO.clwait}</td>
			<td>${alertDTO.ccwait}</td>
			<td>${alertDTO.dwwait}</td>
		</tr>
		 </#list>
		 
	</table>
	
</body>
</html>	