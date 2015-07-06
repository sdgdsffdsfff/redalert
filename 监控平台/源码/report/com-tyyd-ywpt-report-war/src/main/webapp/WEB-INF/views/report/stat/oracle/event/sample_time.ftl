<!-- 	头部		 -->
<#include "/report/alert/common/header.ftl">

	<script type="text/javascript" src="${webRoot}/script/jquery-1.8.3.min.js"></script>
	<script src="${webRoot}/script/highcharts/js/highcharts.js"></script>
	<script src="${webRoot}/script/highcharts/js/modules/exporting.js"></script>
	<script src="${webRoot}/script/quota/monitor.js"></script>
	
	<script src="${webRoot}/script/My97DatePicker/WdatePicker.js"></script>
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
		     
        });
        
        
        function toEventPage(sampleTime){
        	if(sampleTime != ''){
        		
        	}
        }
        
        
	</script>
<!-- 	左侧 	-->
<#include "/report/alert/common/lefter.ftl">
  <div id="content">
    <div class="feature">
      <div class="searchform">
        <h3> <a data-toggle="collapse" data-parent="#accordion" href="#">Sample Time</a></h3>
        <form name="queryForm" class="searchform" action="${webRoot}/report/stat/oracle/event/sampletime">
        	<ul >
		        <li>监控机 :</li>
				<li>
					<select id="oracle_monitors" name="oracle_monitors">
						<option seleted>选择</option>
					</select>
				</li>
				<li>选择时间：</li>
				<li><input class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" id="beginTime" name="beginTime" value="${beginTime}" readonly /></li>
				<li>-</li>
				<li><input class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" id="endTime" name="endTime" value="${endTime}" readonly/></li>
				<li>最少数量：</li>
				<li><input type="text" id="minCounts" name="minCounts" value="${minCounts}"/></li>
				<li><input type="submit" value="查询" id="query_btn"></li>
			</ul>
        </form>
      </div>
      <br/><br/>
      <div id="warnHome-oracle">
        <div>
	         <table border=1 Align="center" width="80%">
	         	<thead>
				<tr>
					<th width="20%">序号</th>
					<th width="60%">时间</th>
					<th width="20%" >合计</th>
				</tr>
				</thead>
            	<tbody>
				<#list sampleTimeList as sample>
		          <tr>
		          	<th>${sample_index+1}</th>
					<th><a href="${webRoot}/report/stat/oracle/event/eventlist/${dbId}/${sample.sampleTime}" target="_blank">${(sample.sampleTime)}</a></th>
					<th>${(sample.sampleCounts)}</th>
		          </tr>
		         </#list>
		         </tbody>
			</table>
        </div>
      </div>
      <h3>&nbsp;</h3>
      <p>&nbsp;</p>
    </div>
  </div>
  



<#include "/report/alert/common/footer.ftl">

