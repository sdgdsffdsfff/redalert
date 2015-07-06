<!-- 	头部		 -->
<#include "/report/alert/common/header.ftl">

<script type="text/javascript">
	$(document).ready(function(){ 
		//加载ORACLE列表
		loadMonitorsOptionSelected("${webRoot}","hostId",2,"${(formDto.hostId)}");
		
		//每页数量
　　		jQuery("#limitSize").val("${(formDto.limitSize)}");

		//设置日期
		jQuery("#startDate").val("${(formDto.startDate)}");
		jQuery("#endDate").val("${(formDto.endDate)}");
		
		setInterval(function(){
			 	refreshPage()
			},1000*60
		);
	}); 
	
	
</script>

<!-- 	左侧 	-->
<#include "/report/alert/common/lefter.ftl">
  <div id="content">
    <div class="feature">
      <div class="searchform">
        <h3> <a data-toggle="collapse" data-parent="#accordion" href="#">ORACLE</a></h3>
        <form id="oracleSearch" name="oracleSearch" action="${webRoot}/report/alert/looking/oracle">
        	<ul >
		        <li>
			        <select id="limitSize" name="limitSize" >
			        	<option value="15" selected>15条</option>
			        	<option value="30">30条</option>
			        	<option value="60">60条</option>
			        </select>
		        </li>
		        <li>监控机 :</li>
				<li>
					<select id="hostId" name="hostId">
						
					</select>
					
				</li>
				<li>日期</li>
				<li><input class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" id="startDate" name="startDate"></li>
				<li>-</li>
				<li><input class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" id="endDate" name="endDate"></li>
				<li><input type="submit" value="查询"></li>
			</ul>
        </form>
      </div>
      <br/><br/>
      <div id="warnHome-oracle">
        <div>
          <table>
            <thead>
              <tr>
              	<th>序号</th>
                <th>名称</th>
                <th>报警项</th>
                <th>预警级别</th>
                <th>检查时间</th>
                <th>描述</th>
              </tr>
            </thead>
            <tbody>
            <#list alertDisplayList as alertDTO>
              <tr>
              	<th>${alertDTO_index+1}</th>
                <td><a href="#" title="${alertDTO.nickName}">${alertDTO.ipAddr}:<#if "0"=="${alertDTO.port}">22<#else>${alertDTO.port}</#if></a></td>
                <td>${monitorType_escape(alertDTO.monitorType)} </td>
                <td>${noticeLevel_escape(alertDTO.noticeLevel)}</td>
				<td>${date_escape(alertDTO.gmtCreatedStr)}</td>
				<td><a href="#" title="${alertDTO.content}">${alertDTO.title}</a></td>
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