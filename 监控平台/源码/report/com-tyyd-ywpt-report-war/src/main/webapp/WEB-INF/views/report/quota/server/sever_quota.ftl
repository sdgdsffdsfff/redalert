<!-- 	头部		 -->
<#include "/report/alert/common/header.ftl">
	
	<script src="${webRoot}/script/highcharts/js/highcharts.js"></script>
	<script src="${webRoot}/script/highcharts/js/modules/exporting.js"></script>
	<script src="${webRoot}/script/quota/server/cpu.js"></script>
	<script src="${webRoot}/script/quota/server/load.js"></script>
	<script src="${webRoot}/script/quota/server/memory.js"></script>
	<script src="${webRoot}/script/quota/server/network.js"></script>
	
	
	<script>
		 
		$(document).ready(function() {
			//加载监控主机列表
		     loadMonitors("${webRoot}","server_monitors",1);
		     
		     //设置日期
			jQuery("#startDate").val("${(beginTime)}");
			jQuery("#endDate").val("${(endTime)}");
		     
		     function refresh(){
		     	
		     	var hostId = $('#server_monitors').children('option:selected').val();
		     	if(hostId==null || hostId == ''){
		     		return;
		     	}
		     	
		     	var startDate = jQuery("#startDate").val();
		     	var endDate = jQuery("#endDate").val();
		     	
		     	if(startDate ==null || startDate == '' || endDate ==null || endDate == '' ){
		     		return;
		     	}
		     	
			     //加载cpu
			     loadCpu("${webRoot}",hostId,startDate,endDate);
			     
			     //加载load
			     getLoad("${webRoot}",hostId,startDate,endDate);
			     
			     //加载内存
			     loadMemory("${webRoot}",hostId,startDate,endDate);
			     
			     //加载网络	
			     loadNetwork("${webRoot}",hostId,startDate,endDate);
		     
		     }
		     
		     
		      $('#formBtn').click(function(){
			 	 refresh();
			  });
		 
		 
		 	   setInterval(function(){
			 	refresh()
			 	},1000*60*5
			   );
		 
        });
       
		 
		function gotoDisk(){
			var hostId = $('#server_monitors').children('option:selected').val();
	     	if(hostId==null || hostId == '' || hostId == '-1'){
	     		alert("请先选择一个主机");
	     		return;
	     	}
	     	
	     	window.open('${webRoot}/report/stat/disk/view/'+ hostId);
		}
	
		 
	</script>
	
	

<!-- 	左侧 	-->
<#include "/report/alert/common/lefter.ftl">
  <div id="content">
    <div class="feature">
      <div class="searchform">
        <h3> <a data-toggle="collapse" data-parent="#accordion" href="#">主机</a></h3>
        <form id="hostSearch" name="hostSearch">
        	<ul >
		        <li>监控机 :</li>
				<li>
					<select id="server_monitors">
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
      <p><a href="javascript:void(0)" onClick="gotoDisk()">查看磁盘</a></p>
      <div id="warnHome-oracle">
        <div>
          	<!-- CPU -->
			<div id="cpu_quota" style="min-width: 75px; height: 200px; margin: 0 auto"></div>
			
			<!-- load -->
			<div id="load_quota" style="min-width: 75px; height: 200px; margin: 0 auto"></div>
			
			<!-- Memory -->
			<div id="memory_quota" style="min-width: 75px; height: 200px; margin: 0 auto"></div>
		
			<!-- NetWork -->
			<div id="network_quota" style="min-width: 75px; height: 200px; margin: 0 auto"></div>
        </div>
      </div>
      <h3>&nbsp;</h3>
      <p>&nbsp;</p>
    </div>
  </div>
  
	
	


	

	
	<#include "/report/alert/common/footer.ftl">