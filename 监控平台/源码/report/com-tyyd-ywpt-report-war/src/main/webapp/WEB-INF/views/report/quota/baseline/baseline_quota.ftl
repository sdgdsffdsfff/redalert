<!-- 	头部		 -->
<#include "/report/alert/common/header.ftl">

	<script src="${webRoot}/script/highcharts/js/highcharts.js"></script>
	<script src="${webRoot}/script/highcharts/js/highcharts-more.js"></script>
	<script src="${webRoot}/script/quota/baseline/baseline.js"></script>
	<script src="${webRoot}/script/quota/baseline/quota.js"></script>
	
	
	
	<script>
		 
		$(document).ready(function() {
			
		     function refresh(){
				
				//监控类型
				var configType = $('#monitor_config_type').children('option:selected').val();
		     	if(configType==null || configType == ''){
		     		return;
		     	}
				
				
				//监控机
				var monitorId = $('#monitors_list').children('option:selected').val();
		     	if(monitorId==null || monitorId == ''){
		     		return;
		     	}
		     	
		     	
		     	//指标
				var quotaId = $('#quota_id').children('option:selected').val();
		     	if(monitorId==null || monitorId == ''){
		     		return;
		     	}
		     	
		     	//日期
		     	var startDate = jQuery("#startDate").val();
		     	var endDate = jQuery("#endDate").val();
		     	
		     	if(startDate ==null || startDate == '' || endDate ==null || endDate == '' ){
		     		return;
		     	}
				
				
			    //加载流量
			    loadBaseLine("${webRoot}",monitorId,configType,startDate,endDate,quotaId);
		     
		     }
		    
		    
		    //类型下拉初始化
		    $('#monitor_config_type').change(function(){
			 	var monitorType = jQuery("#monitor_config_type").val();
		     	loadMonitors("${webRoot}","monitors_list",monitorType);
		     	loadQuotas("${webRoot}","quota_id",monitorType);
		     	
			});
			
			
			
			//点击查询
	     	$('#formBtn').click(function(){
		 	 	refresh();
		  	});
		    
		    jQuery("#startDate").val("${beginTime}");
		    jQuery("#endDate").val("${endTime}");
		    
        });
       
	</script>
	
	


<!-- 	左侧 	-->
<#include "/report/alert/common/lefter.ftl">
  <div id="content">
    <div class="feature">
      <div class="searchform">
        <h3> <a data-toggle="collapse" data-parent="#accordion" href="#">基线</a></h3>
        <form id="hostSearch" name="hostSearch">
        	<ul >
        		<li>监控类型 :</li>
				<li>
					<select id="monitor_config_type" >
						<option seleted>选择</option>
						<option value="1">主机</option>
						<option value="2">Oracle</option>
						<option value="3">MySQL</option>
					</select>
					
				</li>
		        <li>监控机 :</li>
				<li>
					<select id="monitors_list">
						<option seleted>选择</option>
					</select>
					
				</li>
				<li>指标 :</li>
				<li>
					<select id="quota_id">
						<option seleted>选择</option>
					</select>
				</li>
				<li>日期</li>
				<li><input class="Wdate" type="text" id="startDate" name="startDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'endDate\',{H:-6});}'})"></li>
				<li>-</li>
				<li><input class="Wdate" type="text" id="endDate" name="endDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'startDate\',{H:6});}'})"></li>
				<li><input id="formBtn" name="formBtn" type="button" value="查询"></li>
			</ul>
        </form>
      </div>
      <br/><br/>
      <div id="warnHome-oracle">
        <div>
          	<!-- baseline -->
			<div id="baseline_quota" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
        </div>
      </div>
      <h3>&nbsp;</h3>
      <p>&nbsp;</p>
    </div>
  </div>
  
<#include "/report/alert/common/footer.ftl">
	
