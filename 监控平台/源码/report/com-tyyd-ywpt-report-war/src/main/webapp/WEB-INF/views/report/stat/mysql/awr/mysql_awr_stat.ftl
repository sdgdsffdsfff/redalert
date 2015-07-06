<!-- 	头部		 -->
<#include "/report/alert/common/header.ftl">
	
	<link href="${webRoot}/css/ordertable/themes/blue/style.css" rel="stylesheet" type="text/css" />  

	
	<script src="${webRoot}/script/stat/mysql/awr_top_sql.js"></script>
	<script src="${webRoot}/script/stat/mysql/awr_top_sql_tabsort.js"></script>
	
	<script src="${webRoot}/script/ordertable/TableOrder.js" type="text/javascript"></script>

	<script>
		 
		$(document).ready(function() {
		     var id = "${id}";
		     
		    //top sql 
		    loadAwrTopSQL("${webRoot}",id,"sql_id_segment");
			
			//初始化
			initAwrTopSortTable("${webRoot}",id ,"sql_id_segment");
			
			
        });
        
	</script>
	

<!-- 	左侧 	-->
<#include "/report/alert/common/lefter.ftl">
  <div id="content">
      <div id="warnHome-oracle">
      	<input type="hidden" name="sql_id_segment_columnsort" id="sql_id_segment_columnsort"/>
      	<p>TOP SQL</P>
        <table class="tablesorter" id="sql_id_segment">
        	<thead>
				<tr >
					<td style="word-break:break-all;width:20px;">序号</td>
					<td style="word-break:break-all;width:40px;overflow: hidden;">从属库</td>
					<td style="word-break:break-all;width:40px;overflow: hidden;">SQL Id</td>
					
					<td style="word-break:break-all;width:40px;"><a href="javascript:void(0)" id="sql_execCount" class="up" >总执行次数</a></td>
					<td style="word-break:break-all;width:40px;"><a href="javascript:void(0)" id="sql_queryTimeSum" class="up" >SQL执行消耗总时间</a> </td>
					<td style="word-break:break-all;width:40px;"><a href="javascript:void(0)" id="sql_lockTimeSum" class="up" >锁等待总时间</a></td>
					<td style="word-break:break-all;width:40px;"><a href="javascript:void(0)" id="sql_rowsSentSum" class="up" >传输数据行数</a></td>
					<td style="word-break:break-all;width:40px;"><a href="javascript:void(0)" id="sql_rowsExaminedSum" class="up" >扫描数据行数</a></td>
					<td style="word-break:break-all;width:40px;"><a href="javascript:void(0)" id="sql_rowsAffectedSum" class="up" >改变数据行数</a></td>
					<td style="word-break:break-all;width:40px;"><a href="javascript:void(0)" id="sql_fullScanCnt" class="up" >全表扫描次数</a></td>
					<td style="word-break:break-all;width:40px;"><a href="javascript:void(0)" id="sql_fullJoinCnt" class="up" >全表连接次数</a></td>
					<td style="word-break:break-all;width:40px;"><a href="javascript:void(0)" id="sql_tmpTableCnt" class="up" >使用临时表数量</a></td>
					<td style="word-break:break-all;width:40px;"><a href="javascript:void(0)" id="sql_tmpTableOnDiskCnt" class="up" >使用硬盘上临时表数量</a></td>
					<td style="word-break:break-all;width:40px;"><a href="javascript:void(0)" id="sql_fileSortCnt" class="up" >使用文件排序次数</a></td>
					<td style="word-break:break-all;width:40px;"><a href="javascript:void(0)" id="sql_filesortOnDistCnt" class="up" >硬盘上文件排序次数</a></td>
				</tr>
			</thead>
			
		</table>
	
      </div>
      <h3>&nbsp;</h3>
      <p>&nbsp;</p>
    </div>
  </div>
  
<#include "/report/alert/common/footer.ftl">

