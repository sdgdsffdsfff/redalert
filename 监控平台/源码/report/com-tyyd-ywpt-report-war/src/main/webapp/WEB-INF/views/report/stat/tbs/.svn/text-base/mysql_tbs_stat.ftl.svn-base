<!-- 	头部		 -->
<#include "/report/alert/common/header.ftl">
	
	<link href="${webRoot}/css/ordertable/themes/blue/style.css" rel="stylesheet" type="text/css" />  

	<script src="${webRoot}/script/highcharts/js/highcharts.js"></script>
	<script src="${webRoot}/script/highcharts/js/modules/exporting.js"></script>

	<script src="${webRoot}/script/stat/tbs/mysql_tbs_list.js"></script>
	<script src="${webRoot}/script/stat/tbs/delta_tbs.js"></script>
	<script src="${webRoot}/script/stat/tbs/delta_tbs_name.js"></script>
	
	
	<script>
		 
		$(document).ready(function() {
		 	var dbId = '${dbConfigDomain.dbId}';
		 	
		 	//加载表空间
		 	loadTbsList("${webRoot}",dbId,"tbs_segment_id");
		 	
		 	
		 	//增量
		 	loadDeltaTbs("${webRoot}",dbId);
		 	
		 	
        });
        
        
        function singleTbs(appRoot,dbId,tbsName){
        	
        	//增量
        	loadDeltaTbsByTbsName(appRoot,dbId,tbsName);
        	
        }
        
	</script>
	

<!-- 	左侧 	-->
<#include "/report/alert/common/lefter.ftl">
  <div id="content">
      <div id="warnHome-oracle">
		<p>${dbConfigDomain.nickName}[${dbConfigDomain.ipAddr}]</p>
		<p>已用(${tbsUsed}G)</p>
      	<p>Table Space List</P>
        <table class="tablesorter" id="tbs_segment_id">
        	<thead>
        		<tr>
        			<td width="30%"></td>
        			<td width="30%"></td>
        			<td width="40%"></td>
        		</tr>
        	</thead>
		</table>
	
      </div>
      <h3>&nbsp;</h3>
      <p>&nbsp;</p>
      
       <!-- 增量 -->
      <div id="delta_tbs" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
      
      <!-- 具体盘增量 -->
      <div id="delta_tbs_name" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
      
    </div>
  </div>
  
<#include "/report/alert/common/footer.ftl">

