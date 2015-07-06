<!-- 	头部		 -->
<#include "/report/alert/common/header.ftl">

<script type="text/javascript">
	$(document).ready(function(){ 
　　		jQuery("#page_limit_select").val("${pageSize}");

		setInterval(function(){
			 	refreshPage()
			},1000*60
		);
	}); 
	
	function changePageSize(){
		document.search.submit();
	}
	
	
	
	
</script>

<!-- 	左侧 	-->
<#include "/report/alert/common/lefter.ftl">
  <div id="content">
    <div class="feature">
      <div>
        <h3> <a data-toggle="collapse" data-parent="#accordion" href="#">总览</a></h3>
        <form name="search" action="${webRoot}/report/alert/looking/all">
	        <select id="page_limit_select" name="page_limit_select" onChange="changePageSize()">
	        	<option value="15">15条</option>
	        	<option value="30">30条</option>
	        	<option value="60">60条</option>
	        </select>
        </form>
      </div>
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