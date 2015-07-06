<!-- 	头部		 -->
<#include "/report/alert/common/header.ftl">
	
	<script src="${webRoot}/script/highcharts/js/highcharts.js"></script>
	<script src="${webRoot}/script/highcharts/js/modules/exporting.js"></script>
	
	<script src="${webRoot}/script/quota/mysql/iops.js"></script>
	<script src="${webRoot}/script/quota/mysql/tps.js"></script>
	<script src="${webRoot}/script/quota/mysql/qps.js"></script>
	
	<script src="${webRoot}/script/quota/mysql/connect.js"></script>
	<script src="${webRoot}/script/quota/mysql/bytes.js"></script>
	<script src="${webRoot}/script/quota/mysql/logicread.js"></script>
	
	<script src="${webRoot}/script/quota/mysql/mysqld_cpu.js"></script>
	<script src="${webRoot}/script/quota/mysql/seconds_behind_master_quota.js"></script>
	
	<script>
		 
		$(document).ready(function() {
			//加载监控主机列表
		     loadMonitors("${webRoot}","mysql_monitors",3);
		     
		    //设置日期
			jQuery("#startDate").val("${(beginTime)}");
			jQuery("#endDate").val("${(endTime)}");
		     
		     
		     function refresh(){
		     
		     	var hostId = $('#mysql_monitors').children('option:selected').val();
		     	if(hostId==null || hostId == ''){
		     		return;
		     	}
		     	
		     	var startDate = jQuery("#startDate").val();
		     	var endDate = jQuery("#endDate").val();
		     	
		     	if(startDate ==null || startDate == '' || endDate ==null || endDate == '' ){
		     		return;
		     	}
		     	
			     //加载qps
			     qps("${webRoot}",hostId,startDate,endDate);
			     
			     //加载tps
			     tps("${webRoot}",hostId,startDate,endDate);
			     
			     //加载iops
			     iops("${webRoot}",hostId,startDate,endDate);
			     
			     //加载connect
			     loadConnect("${webRoot}",hostId,startDate,endDate);
			     
			     //加载逻辑读
			     logicRead("${webRoot}",hostId,startDate,endDate);
			     
			     //加载流量
			     sendAndRecv("${webRoot}",hostId,startDate,endDate);
		     
		     	 //从库相对主库的延时(秒)
		     	 loadSecondsBehindMaster("${webRoot}",hostId,startDate,endDate);
		     	 
		     	 //mysql 进程对应的cpu使用率
		     	 loadMysqldCpu("${webRoot}",hostId,startDate,endDate);
		     	 
		     }
		     
		     
		      $('#formBtn').click(function(){
			 	 refresh();
			  });
			 
			 setInterval(function(){
			 	refresh()
			 	},1000*60*5
			 );
        });
        
        
         function gotoTbs(){
			var hostId = $('#mysql_monitors').children('option:selected').val();
	     	if(hostId==null || hostId == '' || hostId == '-1'){
	     		alert("请先选择一个数据库");
	     		return;
	     	}
	     	
	     	window.open('${webRoot}/report/stat/tbs/view/'+ hostId);
		}
       
	</script>
	


	
	

<!-- 	左侧 	-->
<#include "/report/alert/common/lefter.ftl">
  <div id="content">
    <div class="feature">
      <div class="searchform">
        <h3> <a data-toggle="collapse" data-parent="#accordion" href="#">MySQL</a></h3>
        <form id="hostSearch" name="hostSearch">
        	<ul >
		        <li>监控机 :</li>
				<li>
					<select id="mysql_monitors">
						<option seleted>选择</option>
					</select>
					
				</li>
				<li>日期</li>
				<li><input class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" id="startDate" name="startDate"></li>
				<li>-</li>
				<li><input class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" id="endDate" name="endDate"></li>
				<li><input id="formBtn" name="formBtn" type="button" value="查询"></li>
			</ul>
        </form>
      </div>
      <br/><br/>
      <p><a href="javascript:void(0)" onClick="gotoTbs()">查看表空间</a></p>
      <div id="warnHome-oracle">
        <div>
        	<!-- connect -->
			<div id="connects_mysql_quota" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
			<!-- logic read -->
			<div id="logic_read_mysql_quota" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
          	<!-- QPS -->
			<div id="qps_mysql_quota" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
			<!-- TPS -->
			<div id="tps_mysql_quota" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
			<!-- IOPS -->
			<div id="iops_mysql_quota" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
		
			<!-- send and recv bytes -->
			<div id="send_recv_bytes_mysql_quota" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
			<!-- Seconds_Behind_Master 从库相对主库的延时(秒) -->
			<div id="seconds_behind_master_quota" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
			<!-- mysqld cpu 进程对应的cpu使用率 -->
			<div id="mysqld_cpu_quota" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
        </div>
      </div>
      <h3>&nbsp;</h3>
      <p>&nbsp;</p>
    </div>
  </div>
  
<#include "/report/alert/common/footer.ftl">
	
