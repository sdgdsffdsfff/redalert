<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<script type="text/javascript" src="${webRoot}/script/jquery-1.8.3.min.js"></script>
	<script src="${webRoot}/script/highcharts/js/highcharts.js"></script>
	<script src="${webRoot}/script/highcharts/js/modules/exporting.js"></script>
	
	<script src="${webRoot}/script/My97DatePicker/WdatePicker.js"></script>
	
	<script src="${webRoot}/script/stat/mysql/sql/awr_sql_desc.js"></script>
	<script src="${webRoot}/script/stat/mysql/sql/mysql_awr_rpt.js"></script>
	
	
	<script src="${webRoot}/script/stat/mysql/sql/exec_count.js"></script>
	<script src="${webRoot}/script/stat/mysql/sql/file_sort_cnt.js"></script>
	<script src="${webRoot}/script/stat/mysql/sql/file_sort_on_dist_cnt.js"></script>
	<script src="${webRoot}/script/stat/mysql/sql/full_join_cnt.js"></script>
	<script src="${webRoot}/script/stat/mysql/sql/full_scan_cnt.js"></script>
	<script src="${webRoot}/script/stat/mysql/sql/lock_time_sum.js"></script>
	
	<script src="${webRoot}/script/stat/mysql/sql/query_time_sum.js"></script>
	<script src="${webRoot}/script/stat/mysql/sql/rows_affected_sum.js"></script>
	<script src="${webRoot}/script/stat/mysql/sql/rows_examined_sum.js"></script>
	<script src="${webRoot}/script/stat/mysql/sql/rows_sent_sum.js"></script>
	<script src="${webRoot}/script/stat/mysql/sql/tmp_table_cnt.js"></script>
	<script src="${webRoot}/script/stat/mysql/sql/tmp_table_on_disk_cnt.js"></script>
	
	<style type="text/css">
<!--
.searchform ul{list-style:none;} 
.searchform li{float:left;} 

-->
</style>
	
	<script>
		 
		$(document).ready(function() {

		     //添加查询事件
		     jQuery("#query_btn").attr("onclick","refresh()");
		     
		     jQuery("#startDate").val('${(startDate)}');
		     jQuery("#endDate").val('${(endDate)}');
		     jQuery("#dbId").val('${(dbId)}');
		     jQuery("#checkSum").val('${(checkSum)}');
		     jQuery("#id").val('${(id)}');
		     
		     refresh();
        });
        
        
        function refresh(){
        	
        	var dbId = jQuery("#dbId").val();
	     	var startDate = jQuery("#startDate").val();
	     	var endDate = jQuery("#endDate").val();
	     	var checkSum = jQuery("#checkSum").val();
	     	var id = jQuery("#id").val();
	     	
	     	//SQL 内容
	     	loadSQLDesc("${webRoot}",checkSum,"sql_id_segment");
	     	
	     	//报表
	     	loadRpt("${webRoot}",dbId,startDate,endDate,checkSum);
		 }
       
	</script>
	
	<title>MySQL SQL STAT报表</title>
	
</head>
<body>

	<!-- 主机 -->
	
	<div class="conditon_div">
		<form name="queryForm" class="searchform">
			<input type="hidden" name="dbId" id="dbId" />
			<input type="hidden" name="id" id="id" />
		<ul>
			<li>选择时间：</li>
			<li><input class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" id="startDate" name="startDate" readonly/></li>
			<li>-</li>
			<li><input class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" id="endDate" name="endDate" readonly/></li>
			<li>SQL_ID：</li>
			<li><input type="text" id="checkSum" name="checkSum" /></li>
			<li><input type="button" value="查询" id="query_btn"></li>
		</ul>
		</form>
	</div>
	
	<br/><br/><br/>
	<p>SQL描述</P>
        <div border=1 id="sql_id_segment">
			
		</div>
	<br/><br/><br/>
	
	<!-- 总执行次数 -->
	<div id="exec_count" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	<br/><br/>
	<!-- SQL执行消耗总时间  -->
	<div id="query_time_sum" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
	<!-- 锁等待总时间  -->
	<div id="lock_time_sum" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
	<!-- 传输数据行数 -->
	<div id="rows_sent_sum" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
	<!--  扫描数据行数 -->
	<div id="rows_examined_sum" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
	<!-- 改变数据行数  -->
	<div id="rows_affected_sum" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
	<!-- 全表扫描次数  -->
	<div id="full_scan_cnt" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
	<!-- 全表连接次数  -->
	<div id="full_join_cnt" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
	<!-- 使用临时表数量  -->
	<div id="tmp_table_cnt" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
	<!-- 使用硬盘上临时表数量  -->
	<div id="tmp_table_on_disk_cnt" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
	<!-- 使用文件排序次数  -->
	<div id="file_sort_cnt" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
	<!-- 硬盘上文件排序次数  -->
	<div id="file_sort_on_dist_cnt" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
	
</body>
</html>	

