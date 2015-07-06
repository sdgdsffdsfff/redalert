<!-- 	头部		 -->
<#include "/report/alert/common/header.ftl">
	
	<link href="${webRoot}/css/ordertable/themes/blue/style.css" rel="stylesheet" type="text/css" />  

	<script src="${webRoot}/script/highcharts/js/highcharts.js"></script>
	<script src="${webRoot}/script/highcharts/js/modules/exporting.js"></script>

	<script src="${webRoot}/script/stat/disk/disk_list.js"></script>
	<script src="${webRoot}/script/stat/disk/total_disk.js"></script>
	<script src="${webRoot}/script/stat/disk/delta_disk.js"></script>
	<script src="${webRoot}/script/stat/disk/delta_disk_name.js"></script>
	<script src="${webRoot}/script/stat/disk/total_disk_name.js"></script>
	<script src="${webRoot}/script/stat/disk/disk_pie.js"></script>
	
	<script>
		 
		$(document).ready(function() {
		 	var hostId = '${hostConfigDomain.hostId}';
		 	
		 	//加载pie
		 	loadDiskPie("${webRoot}",hostId);
		 	
		 	//加载磁盘
		 	loadDiskList("${webRoot}",hostId,"disk_segment_id");
		 	
		 	//总量
		 	loadTotalDisk("${webRoot}",hostId);
		 	
		 	
		 	//增量
		 	loadDeltaDisk("${webRoot}",hostId);
		 	
		 	
        });
        
        
        function singleDisk(appRoot,hostId,diskName){
        	//总量
        	loadTotalDiskByDisk(appRoot,hostId,diskName);
        	
        	//增量
        	loadDeltaDiskByDiskName(appRoot,hostId,diskName);
        	
        }
        
	</script>
	

<!-- 	左侧 	-->
<#include "/report/alert/common/lefter.ftl">
  <div id="content">
      <div id="warnHome-oracle">
		<p>${hostConfigDomain.nickName}[${hostConfigDomain.ipAddr}]</p>
		<p>已用(${diskUsed}G),剩余(${diskRemain}G),利用率(${diskusedPercent}%)</p>
      	<p>Disk List</P>
        <table class="tablesorter" id="disk_segment_id" width="45%">
        	<thead>
        		<tr>
        			<td width="30%"></td>
        			<td width="30%"></td>
        			<td width="40%"></td>
        		</tr>
        	</thead>
		</table>
		 	
		 <!-- 总量 -->
      	<div id="disk_pie" style="min-width: 50%; height: 300px; margin: 0 auto"></div>
      
      </div>

      
     
      <!-- 总量 -->
      <div id="disk_total" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
      
       <!-- 增量 -->
      <div id="delta_disk" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
      
       <!-- 具体盘总量 -->
      <div id="disk_total_name" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
      
      <!-- 具体盘增量 -->
      <div id="delta_disk_name" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
      
    </div>
  </div>
  
<#include "/report/alert/common/footer.ftl">

