function loadTopSQL(appRoot,snapId,databaseId,instanceId,trId,monitorId,sampleTime){
    
    var connects_url =  appRoot+"/report/stat/oracle/performance/data/topsql/"+snapId+"/"+databaseId+"/"+instanceId;
    $.getJSON(connects_url,function(data){
    	
    	var tbody = "<tbody id='sql_id_segment_tbody'>";
    	var index = 1;
    	$.each(data, function(i, obj) {
    		
    		var tr = "<tr>"
				+"<td>"+index+"</td>"
				+"<td><a href='"+appRoot+"/report/stat/oracle/sqlstat/view?dbId="+monitorId+"&sqlid="+obj.sqlId+"&sampletime="+sampleTime+"' target='_blank'>"+obj.sqlId+"</a></td>"
				+"<td>"+obj.fetchesDelta+"</td>"
				+"<td>"+obj.sortsDelta+"</td>"
				+"<td>"+obj.executionsDelta+"</td>"
				+"<td>"+obj.parseCallsDelta+"</td>"
				+"<td>"+obj.diskReadsDelta+"</td>"
				+"<td>"+obj.bufferGetsDelta+"</td>"
				+"<td>"+obj.rowsProcessedDelta+"</td>"
				+"<td>"+obj.cpuTimeDelta+"</td>"
				+"<td>"+obj.elapsedTimeDelta+"</td>"
				+"<td>"+obj.iowaitDelta+"</td>"
				+"<td>"+obj.clwaitDelta+"</td>"
				+"<td>"+obj.apwaitDelta+"</td>"
				+"<td>"+obj.ccwaitDelta+"</td>"		
				+"</tr>";
				
			tbody = tbody + tr;
			
			index++;
    		
    	});
    	
    	tbody = tbody + "</tbody>";
    	
    	$("#"+trId).append(tbody);
    });

}     			    




