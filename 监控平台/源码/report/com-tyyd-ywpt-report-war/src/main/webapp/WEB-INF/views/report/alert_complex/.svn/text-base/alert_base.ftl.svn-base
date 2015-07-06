<!-- 	头部		 -->
<#include "/report/alert/common/header.ftl">

<!-- 	左侧 	-->
<#include "/report/alert_complex/common/lefter.ftl">
  <div id="content">
    <div class="feature">
      <div>
        <h3> <a data-toggle="collapse" data-parent="#accordion" href="#">总览</a></h3>
        <form name="pageComplexSearch" action="${webRoot}/report/alert/complex/looking/all">
	        <input type="hidden" name="currentPage" id="currentPage" value=""/>
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
                <th>首次检查时间</th>
                <th>最近一次检查时间</th>
                <th>描述</th>
              </tr>
            </thead>
            <tbody>
            <#list pageObject.records as alertDTO>
              <tr>
              	<th>${alertDTO.rn}</th>
                <td><a href="#" title="${alertDTO.nickName}">${alertDTO.ipAddr}:<#if "0"=="${alertDTO.port}">22<#else>${alertDTO.port}</#if></a></td>
                <td>${monitorType_escape(alertDTO.monitorType)} </td>
                <td>${noticeLevel_escape(alertDTO.noticeLevel)}</td>
				<td>${date_escape(alertDTO.gmtCreatedStr)}</td>
				<td>${date_escape(alertDTO.gmtModifedStr)}</td>
				<td><a href="#" title="${alertDTO.content}">${alertDTO.summary}</a></td>
              </tr>
             </#list>
            </tbody>
          </table>
        </div>
        <div id="page_label">
        	共${(pageObject.totalRows)}条记录&nbsp;当前第${(pageObject.currentPage)}/${(pageObject.totalPage)}页&nbsp;
        	<a href="#" onClick='changePage("1","0")'>首页</a>&nbsp;
        	<a href="#" onClick="changePage(${(pageObject.currentPage)},'-1')">上一页</a>&nbsp;
        	<a href="#" onClick="changePage(${(pageObject.currentPage)},'1')">下一页</a>&nbsp;
        	<a href="#" onClick="changePage('${(pageObject.totalPage)}','0')">末页</a>&nbsp;
        	每页${(pageObject.pageSize)}条记录
        </div>
      </div>
      <h3>&nbsp;</h3>
      <p>&nbsp;</p>
    </div>
  </div>
  
<script language="javascript" type="text/javascript">
	function changePage(page,changeType){
			var currentPage = 1;
			if(page == ''){
				page = 1;
			}
			
			if(changeType == '-1'){
				//上一页
				currentPage = page - 1;
			}else if(changeType == '0'){
				//直达
				currentPage = page;
			}else if(changeType == '1'){
				//下一页
				currentPage = page + 1;
			}
			
			jQuery("#currentPage").val(currentPage);
			document.pageComplexSearch.submit();
	}
	
</script>


<#include "/report/alert/common/footer.ftl">