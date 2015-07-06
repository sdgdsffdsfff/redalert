<!-- 	头部		 -->
<#include "/report/alert/common/header.ftl">

	<script src="${webRoot}/script/highcharts/js/highcharts.js"></script>
	<script src="${webRoot}/script/highcharts/js/highcharts-more.js"></script>
		
	<script src="${webRoot}/script/firewall/other_wap.js"></script>
	
	<script src="${webRoot}/script/firewall/voice_interface.js"></script>
	<script src="${webRoot}/script/firewall/vent_one.js"></script>
	<script src="${webRoot}/script/firewall/vent_two.js"></script>
	
	<script src="${webRoot}/script/firewall/fire_wall_pay.js"></script>
	<script src="${webRoot}/script/firewall/fire_wall_xf.js"></script>
	<script src="${webRoot}/script/firewall/fire_wall_wap.js"></script>
	<script src="${webRoot}/script/firewall/fire_wall_www.js"></script>
	
	<script src="${webRoot}/script/firewall/fire_wall_xf_other_network.js"></script>
	<script src="${webRoot}/script/firewall/fire_wall_voice.js"></script>
	
	
	
	<script>
		 
		$(document).ready(function() {
			
		     function refresh(){
				
		     	//日期
		     	var startDate = jQuery("#startDate").val();
		     	var endDate = jQuery("#endDate").val();
		     	
		     	if(startDate ==null || startDate == '' || endDate ==null || endDate == '' ){
		     		return;
		     	}
				
				//异网WAP
				otherWap("${webRoot}",startDate,endDate);
				
				//有声接口 
				voiceInterface("${webRoot}",startDate,endDate);
				
				//互联星空1
				ventOne("${webRoot}",startDate,endDate);
				
				//互联星空2
				ventTwo("${webRoot}",startDate,endDate);
			
				//防火墙计费 
				fireWallPay("${webRoot}",startDate,endDate);
				
				//防火墙下发
				fireWallXf("${webRoot}",startDate,endDate);
				
				//防火墙wap
				fireWallWap("${webRoot}",startDate,endDate);
				
				//防火墙www
				fireWallWww("${webRoot}",startDate,endDate);
				
				//防火墙异网下发
				fireWallXfOtherNetWork("${webRoot}",startDate,endDate);
				
				//防火墙有声音频
				fireWallVoice("${webRoot}",startDate,endDate);
				
		     }
		    
			
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
        <h3> <a data-toggle="collapse" data-parent="#accordion" href="#">Fire Wall</a></h3>
        <form id="hostSearch" name="hostSearch">
        	<ul >
				<li>查询日期</li>
				<li><input class="Wdate" type="text" id="startDate" name="startDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})"></li>
				<li> 到 </li>
				<li><input class="Wdate" type="text" id="endDate" name="endDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})"></li>
				<li><input id="formBtn" name="formBtn" type="button" value="查询"></li>
			</ul>
        </form>
      </div>
      <br/><br/>
      <div id="warnHome-oracle">
        <div>
          	<!-- 异网WAP -->
			<div id="other_wap" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
			<!-- 有声接口 -->
			<div id="voice_interface" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
			<!-- 互联星空1 -->
			<div id="vent_one" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
			<!-- 互联星空2 -->
			<div id="vent_two" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
			<!-- 防火墙计费 -->
			<div id="fire_wall_pay" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
			<!-- 防火墙下发 -->
			<div id="fire_wall_xf" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
			<!-- 防火墙wap -->
			<div id="fire_wall_wap" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
			<!-- 防火墙www -->
			<div id="fire_wall_www" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
			<!-- 防火墙异网下发 -->
			<div id="fire_wall_xf_other_network" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
			
			<!-- 防火墙有声音频 -->
			<div id="fire_wall_voice" style="min-width: 100px; height: 300px; margin: 0 auto"></div>
        </div>
      </div>
      <h3>&nbsp;</h3>
      <p>&nbsp;</p>
    </div>
  </div>
  
<#include "/report/alert/common/footer.ftl">
	
