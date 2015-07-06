<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<script type="text/javascript" src="${webRoot}/script/jquery-1.8.3.min.js"></script>
	<script src="${webRoot}/script/highcharts/js/highcharts.js"></script>
	<script src="${webRoot}/script/highcharts/js/modules/exporting.js"></script>
	<script src="${webRoot}/script/quota/monitor.js"></script>
	
	<script src="${webRoot}/script/My97DatePicker/WdatePicker.js"></script>
	
	<script src="${webRoot}/script/stat/oracle/sga/freememory.js"></script>
	<script src="${webRoot}/script/stat/oracle/sga/gcs.js"></script>
	<script src="${webRoot}/script/stat/oracle/sga/sqlarea.js"></script>
	
	
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
	     	
	     	
	     	 //free memory
		     loadFreeMemory("${webRoot}",hostId,beginTime,endTime);
		     
		     //gcs
		     loadGcs("${webRoot}",hostId,beginTime,endTime);
		     
		     //sql area
		     loadSQLArea("${webRoot}",hostId,beginTime,endTime);
		     
		 }
       
	</script>
	
	<title>SGA报表</title>
	
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
			<li><input type="button" value="查询" id="query_btn"></li>
		</ul>
		</form>
	</div>
	

	

	<!-- free memory  -->
	<div id="free_memory" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
	<!-- gcs-->
	<div id="gcs" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
	<!-- sql area -->
	<div id="sql_area" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
	
</body>
</html>	