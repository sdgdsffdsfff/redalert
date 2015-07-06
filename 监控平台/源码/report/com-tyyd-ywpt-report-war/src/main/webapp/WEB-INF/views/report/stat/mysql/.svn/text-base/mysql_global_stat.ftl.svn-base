<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" media="screen" href="${webRoot}/css/stat/mysql_global.css" />
	<script type="text/javascript" src="${webRoot}/script/jquery-1.8.3.min.js"></script>
	<script src="${webRoot}/script/highcharts/js/highcharts.js"></script>
	<script src="${webRoot}/script/highcharts/js/modules/exporting.js"></script>
	<script src="${webRoot}/script/quota/monitor.js"></script>
	
	<script src="${webRoot}/script/My97DatePicker/WdatePicker.js"></script>
	
	<script>
		
		$(document).ready(function() {
			$('tbody tr').hover(function() {
			  $(this).addClass('odd');
			}, function() {
			  $(this).removeClass('odd');
			});
        });
        
	</script>

	<title>MySQL全局性能表</title>
	
</head>
<body>

	<table  id="travel" summary="Travel times to work by main mode (Autumn 2006) - Source: London Travel Report 2007 http://www.tfl.gov.uk/assets/downloads/corporate/London-Travel-Report-2007-final.pdf">
		<thead> 
			<tr>
				<th scope="col" rowspan=2 style="width:5%" >序号</th>
				<th scope="col" colspan=8 style="width:30%">服务器</th>
				<th scope="col" colspan=2 style="width:10%">主库信息</th>
				<th scope="col" colspan=3 style="width:10%">复制线程</th>
				<th scope="col" colspan=2 style="width:10%">当前二进制日志</th>
				<th scope="col" colspan=2 style="width:10%">主库二进制日志</th>
				<th scope="col" colspan=6 style="width:25%" >性能</th>
			</tr>
			<tr>
				
				<th scope="row">数据库别名</th>
				<th>IP地址</th>
				<th>端口</th>
				<th>启动时间</th>
				<th>版本号</th>
				<th>是否主库</th>
				<th>是否备库</th>
				<th>是否只读</th>
				
				<th>主库IP</th>
				<th>主库端口</th>
				
				<th>IO复制</th>
				<th>SQL复制</th>
				<th>复制延迟</th>
				
				<th>当前文件</th>
				<th>当前位置</th>
				
				<th>当前文件</th>
				<th>当前位置</th>
				
				<th>总连接数</th>
				<th>活动连接</th>
				<th>QPS</th>
				<th>TPS</th>
				<th>接收流量/秒</th>
				<th>发送流量/秒</th>
				
			</tr>
		</thead>
		<tbody>
		<#list statList as stat>
          <tr>
          	<td>${stat_index+1}</td>
          	<th scope="row">${(stat.nickName)}</th>
          	<td>${(stat.ipAddr)}</td>
          	<td>${(stat.port)}</td>
          	
          	<td>${stat.startupTimeFormat}</td>
          	<td>${(stat.version)}</td>
          	<td>${(stat.isMasterRemark)}</td>
          	<td>${(stat.isSlaveRemark)}</td>
          	<td>${(stat.readOnly)}</td>
          	<td>${(stat.masterServer)}</td>
          	<td>${(stat.masterPort)}</td>
          	
          	<td>${(stat.slaveIoRun)}</td>
          	<td>${(stat.slaveSqlRun)}</td>
          	<td>${(stat.delay)}</td>
          	<td>${(stat.currentBinlogFile)}</td>
          	<td>${(stat.currentBinlogPos)}</td>
          	<td>${(stat.masterBinlogFile)}</td>
          	<td>${(stat.masterBinlogPos)}</td>
          	
          	<td>${(stat.threadsConnects)}</td>
          	<td>${(stat.activeConnects)}</td>
          	<td>${round_escape(stat.qps,2)}</td>
          	<td>${round_escape(stat.tps,2)}</td>
          	<td>${round_escape(stat.bytesReceived,2)}</td>
          	<td>${round_escape(stat.bytesSent,2)}</td>
          	
          </tr>
         </#list>
		</tbody>
	</table>
	
</body>
</html>	