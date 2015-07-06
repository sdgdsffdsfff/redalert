<!-- 	头部		 -->
<#include "/report/alert/common/header.ftl">
	
	<script src="${webRoot}/script/highcharts/js/highcharts.js"></script>
	<script src="${webRoot}/script/highcharts/js/modules/exporting.js"></script>
	
	<script src="${webRoot}/script/quota/oracle/redo.js"></script>
	<script src="${webRoot}/script/quota/oracle/sqlexec.js"></script>
	<script src="${webRoot}/script/quota/oracle/trans.js"></script>
	
	<script src="${webRoot}/script/quota/oracle/connect.js"></script>
	<script src="${webRoot}/script/quota/oracle/physical.js"></script>
	<script src="${webRoot}/script/quota/oracle/logicread.js"></script>
	
	
	<script>
		 
		$(document).ready(function() {
			//加载监控主机列表
		     loadMonitors("${webRoot}","oracle_monitors",2);
		     
		    //设置日期
			jQuery("#startDate").val("${(beginTime)}");
			jQuery("#endDate").val("${(endTime)}");
		     
		     
		     function refresh(){
		     	var hostId = $('#oracle_monitors').children('option:selected').val();
		     	if(hostId==null || hostId == ''){
		     		return;
		     	}
		     	
		     	var startDate = jQuery("#startDate").val();
		     	var endDate = jQuery("#endDate").val();
		     	
		     	if(startDate ==null || startDate == '' || endDate ==null || endDate == '' ){
		     		return;
		     	}
		     	
			     //加载事务数
			     trans("${webRoot}",hostId,startDate,endDate);
			     
			     //加载SQL执行数
			     sqlExec("${webRoot}",hostId,startDate,endDate);
			     
			     //加载逻辑读
			     logicRead("${webRoot}",hostId,startDate,endDate);
			     
			     //加载物理读 
			     physical("${webRoot}",hostId,startDate,endDate);
			     
			     //加载redo
			     loadRedo("${webRoot}",hostId,startDate,endDate);
			     
			     //加载connect
			     loadConnect("${webRoot}",hostId,startDate,endDate);
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
			var hostId = $('#oracle_monitors').children('option:selected').val();
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
        <h3> <a data-toggle="collapse" data-parent="#accordion" href="#">Oracle</a></h3>
        <form id="hostSearch" name="hostSearch">
        	<ul >
		        <li>监控机 :</li>
				<li>
					<select id="oracle_monitors">
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
			<div id="connect_oracle_quota" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
			<!-- SQL执行数 -->
			<div id="sql_exec_oracle_quota" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
			<!-- 逻辑读 -->
			<div id="logic_read_oracle_quota" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
		
			<!-- 物理读 -->
			<div id="physical_read_oracle_quota" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
			<!-- redo -->
			<div id="redo_oracle_quota" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
			<!-- 事务数 -->
			<div id="trans_oracle_quota" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
        </div>
      </div>
      <h3>&nbsp;</h3>
      <p>&nbsp;</p>
    </div>
  </div>
  
<#include "/report/alert/common/footer.ftl">
	
