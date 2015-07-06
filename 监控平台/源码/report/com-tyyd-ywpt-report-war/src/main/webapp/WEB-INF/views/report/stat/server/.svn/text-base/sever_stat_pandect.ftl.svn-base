<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" media="screen" href="${webRoot}/css/stat/mysql_global.css" />
	<script type="text/javascript" src="${webRoot}/script/jquery-1.8.3.min.js"></script>
	
	<script>
		
		$(document).ready(function() {
			$('tbody tr').hover(function() {
			  $(this).addClass('odd');
			}, function() {
			  $(this).removeClass('odd');
			});
			
			
		
			setInterval(function(){
			 		location.reload();
				},1000*60
			);
		
		
        });
        
        
        
        
	</script>

	<title>主机全局性能表</title>
	
</head>
<body>

	<table  id="travel" >
		<thead> 
			<tr>
				<th scope="col" rowspan=2 style="width:5%" >序号</th>
				<th scope="col" colspan=3 style="width:30%">服务器信息</th>
				<th scope="col" colspan=3 style="width:20%">CPU</th>
				<th scope="col" colspan=1 style="width:10%" >内存</th>
				<th scope="col" colspan=2 style="width:15%">网络</th>
				<th scope="col" colspan=3 style="width:20%" >磁盘</th>
				
			</tr>
			<tr>
				
				
				<th>主机名</th>
				<th>昵称</th>
				<th>IP地址</th>
				
				
				<th>User利用率(%)</th>
				<th>SYSTEM利用率(%)</th>
				<th>Load 5分钟</th>
				
				<th>利用率(%)</th>
				
				<th>接受(M)</th>
				<th>发送(M)</th>
				
				<th>已用(G)</th>
				<th>剩余(G)</th>
				<th>利用率(%)</th>
				
				
				


			</tr>
		</thead>
		<tbody>
		<#list dataList as stat>
          <tr>
          	<td>${stat_index+1}</td>
          	
          	<td>${(stat.hostName)}</td>
          	<td>${(stat.nickName)}</td>
          	<td>${stat.ipAddr}</td>
          	
          	<td>${round_escape(stat.cpuUserPercent,2)} </td>
          	<td>${round_escape(stat.cpuSystemPercent,2)} </td>
          	<td>${round_escape(stat.load,2)}</td>
          	
          	<td>${round_escape(stat.memUserPercent,2)} </td>
          	
          	<td>${round_escape(stat.netReceiveCapacity,2)}</td>
          	<td>${round_escape(stat.netSendCapacity,2)}</td>
          	
          	<td>${round_escape(stat.diskUsed,2)}</td>
          	<td>${round_escape(stat.diskRemain,2)}</td>
          	<td>${round_escape(stat.diskUsedPercent,2)} </td>
          	
          	
          	
          	
          	
          	
          </tr>
         </#list>
		</tbody>
	</table>
	
</body>
</html>	