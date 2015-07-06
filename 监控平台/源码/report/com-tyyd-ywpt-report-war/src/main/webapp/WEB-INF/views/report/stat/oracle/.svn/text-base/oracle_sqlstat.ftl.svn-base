<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<script type="text/javascript" src="${webRoot}/script/jquery-1.8.3.min.js"></script>
	<script src="${webRoot}/script/highcharts/js/highcharts.js"></script>
	<script src="${webRoot}/script/highcharts/js/modules/exporting.js"></script>
	<script src="${webRoot}/script/quota/monitor.js"></script>
	
	<script src="${webRoot}/script/My97DatePicker/WdatePicker.js"></script>
	
	<script src="${webRoot}/script/stat/oracle/sqlstat/bufferget.js"></script>
	<script src="${webRoot}/script/stat/oracle/sqlstat/diskread.js"></script>
	<script src="${webRoot}/script/stat/oracle/sqlstat/elapsed.js"></script>
	
	<script src="${webRoot}/script/stat/oracle/sqlstat/executions.js"></script>
	<script src="${webRoot}/script/stat/oracle/sqlstat/memory.js"></script>
	<script src="${webRoot}/script/stat/oracle/sqlstat/parse.js"></script>
	
	<style type="text/css">
<!--
.searchform ul{list-style:none;} 
.searchform li{float:left;} 

-->
</style>
	
	<script>
		 
		$(document).ready(function() {
			//加载监控主机列表
		     loadMonitorsOptionSelected("${webRoot}","oracle_monitors",2,"${(dbId)}");
		     
		     //添加查询事件
		     jQuery("#query_btn").attr("onclick","refresh()");
		     
		     
		     jQuery("#beginTime").val('${(beginTime)}');
		     jQuery("#endTime").val('${(endTime)}');
		     jQuery("#sqlId").val('${(sqlId)}');
		     
		     refresh("${(dbId)}");
        });
        
        
        function refresh(dbId){
	     	var hostId = $('#oracle_monitors').children('option:selected').val();
	     	
	     	if(dbId != '' && dbId != undefined){
	     		hostId = dbId;
	     	}
	     	
	     	if(hostId==null || hostId == ''){
	     		return;
	     	}
	     	
	     	var beginTime = jQuery("#beginTime").val();
	     	var endTime = jQuery("#endTime").val();
	     	var sqlId = jQuery("#sqlId").val();
	     	
	     	
	     	 //执行次数
		     loadExecutions("${webRoot}",hostId,beginTime,endTime,sqlId);
		     
		     //物理读
		     loadDiskRead("${webRoot}",hostId,beginTime,endTime,sqlId);
		     
		     //逻辑读
		     loadBufferGet("${webRoot}",hostId,beginTime,endTime,sqlId);
		     
		     //内存
		     loadMemory("${webRoot}",hostId,beginTime,endTime,sqlId);
		    
		     //解析
		     loadParse("${webRoot}",hostId,beginTime,endTime,sqlId);
		     
		     //执行时间
		     loadElapsed("${webRoot}",hostId,beginTime,endTime,sqlId);
		 }
       
	</script>
	
	<title>SQL STAT报表</title>
	
</head>
<body>

	<!-- 主机 -->
	
	<div class="conditon_div">
		<form name="queryForm" class="searchform">
		<ul>
			<li>监控机 :</li>
			<li>
				<select id="oracle_monitors">
					<option seleted>选择</option>
				</select>
			</li>
			<li>选择时间：</li>
			<li><input class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" id="beginTime" name="beginTime" readonly /></li>
			<li>-</li>
			<li><input class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" id="endTime" name="endTime" readonly/></li>
			<li>SQL_ID：</li>
			<li><input type="text" id="sqlId" name="sqlId"/></li>
			<li><input type="button" value="查询" id="query_btn"></li>
		</ul>
		</form>
	</div>
	
	<br/><br/><br/>
	<span>
		${(sqlText)}
	</span>
	<br/><br/><br/>
	
	<!-- 执行次数 -->
	<div id="sqlstat_executions" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	<br/><br/>
	<!-- 物理读 -->
	<div id="sqlstat_disk_read" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
	<!-- 逻辑读 -->
	<div id="sqlstat_buffer_get" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
	<!-- 内存 -->
	<div id="sqlstat_memory" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
	<!-- 解析 -->
	<div id="sqlstat_parse" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
	<!-- 执行时间 -->
	<div id="sqlstat_elapsed" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
	
</body>
</html>	