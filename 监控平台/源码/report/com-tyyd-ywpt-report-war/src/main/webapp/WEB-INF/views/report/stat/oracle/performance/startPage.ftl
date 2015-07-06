<!-- 	头部		 -->
	<#include "/report/alert/common/header.ftl">

	<link href="${webRoot}/css/ordertable/themes/blue/style.css" rel="stylesheet" type="text/css" />  

	<script src="${webRoot}/script/ordertable/TableOrder.js" type="text/javascript"></script>

	<script src="${webRoot}/script/stat/oracle/performance/sort_top_sql.js"></script>
	<script src="${webRoot}/script/stat/oracle/performance/top_sql.js"></script>
	<script src="${webRoot}/script/stat/oracle/performance/action_session.js"></script>
	<script src="${webRoot}/script/stat/oracle/performance/total_wait_event.js"></script>
	
	<script src="${webRoot}/script/stat/oracle/performance/event_class.js"></script>
	<script src="${webRoot}/script/stat/oracle/performance/event_sql.js"></script>
	

	<script>
		 
		$(document).ready(function() {
		     
		     var snapId = "${domain.snapId}";
		     var databaseId = "${domain.dbId}";
		     var instanceId = "${domain.instanceId}";
		     var lastSnapId = "${domain.lastSnapId}";
		     var monitorId = "${domain.monitorId}";
		     var sampleTime = "${domain.sampleTime}";
		     
		    //top sql 
		    loadTopSQL("${webRoot}",snapId,databaseId,instanceId,"sql_id_segment",monitorId,sampleTime);
		    
		    //active session
		    loadActiveSession("${webRoot}",snapId,databaseId,instanceId,"actice_session_segment",monitorId,sampleTime);
		     
		     //Total Wait Event
		    loadTotalWaitEvent("${webRoot}",snapId,databaseId,instanceId,lastSnapId,"total_wait_event_segment",monitorId,sampleTime);
		     
		    //初始化排序头
		    initOracleSortTopSQL("${webRoot}",snapId,databaseId,instanceId,"sql_id_segment",monitorId,sampleTime); 
        });
        
        
       
	</script>
	

<!-- 	左侧 	-->
<#include "/report/alert/common/lefter.ftl">
  <div id="content">
      <div id="warnHome-oracle">
      	<input type="hidden" name="sql_id_segment_columnsort" id="sql_id_segment_columnsort"/>
      	<p>TOP SQL</P>
        <table class="tablesorter" id="sql_id_segment">
        	<thead>
			<tr >
				<td>序号</td>
				<td>SQL Id</td>
				<td><a href="javascript:void(0)" id="sql_fetchesDelta" class="up" >SQL取数据的次数</a></td>
				<td><a href="javascript:void(0)" id="sql_sortsDelta" class="up" >排序次数</a></td>
				<td><a href="javascript:void(0)" id="sql_executionsDelta" class="up" >执行次数</a></td>
				<td><a href="javascript:void(0)" id="sql_parseCallsDelta" class="up" >解析次数</a></td>
				<td><a href="javascript:void(0)" id="sql_diskReadsDelta" class="up" >物理读块数</a></td>
				<td><a href="javascript:void(0)" id="sql_bufferGetsDelta" class="up" >逻辑读块数</a></td>
				<td><a href="javascript:void(0)" id="sql_rowsProcessedDelta" class="up" >返回行数</a></td>
				<td><a href="javascript:void(0)" id="sql_cpuTimeDelta" class="up" >CPU时间(s)</a></td>
				<td><a href="javascript:void(0)" id="sql_elapsedTimeDelta" class="up" >执行时间(s)</a></td>
				<td><a href="javascript:void(0)" id="sql_iowaitDelta" class="up" >IO等待时间(s)</a></td>
				<td><a href="javascript:void(0)" id="sql_clwaitDelta" class="up" >集群相关等待时间(s)</a></td>
				<td><a href="javascript:void(0)" id="sql_apwaitDelta" class="up" >应用程序相关等待时间(s)</a></td>
				<td><a href="javascript:void(0)" id="sql_ccwaitDelta" class="up" >并发相关等待时间(s)</a></td>
			</tr>
			</thead>
		</table>
		
		<p>Actice Session</P>
		<table class="tablesorter" id="actice_session_segment">
			<tr >
				<td>序号</td>
				<td>SQL Id</td>
				<td>类型</td>
				<td>事件</td>
				<td>次数</td>
			</tr>
			
		</table>
		
		<p>Wait Event</P>
		<table class="tablesorter" id="total_wait_event_segment">
			<tr >
				<td>序号</td>
				<td>等待类型</td>
				<td>等待次数</td>
				<td>超时的次数</td>
				<td>等待时间/s</td>
			</tr>
			
		</table>
		
		<p>Wait Event Class</P>
		<table class="tablesorter" id="load_event_class_segment">
			<tr >
				<td>序号</td>
				<td>事件名</td>
				<td>等待次数</td>
				<td>超时的次数</td>
				<td>等待时间(s)</td>
			</tr>
			
		</table>
		
			
		<p>Event SQL</P>
		<table class="tablesorter" id="load_event_sql_segment">
			<tr >
				<td>序号</td>
				<td>采样时间</td>
				<td>SQL ID</td>
				<td>进程名</td>
			</tr>
			
		</table>
      </div>
      <h3>&nbsp;</h3>
      <p>&nbsp;</p>
    </div>
  </div>
  
<#include "/report/alert/common/footer.ftl">

	

