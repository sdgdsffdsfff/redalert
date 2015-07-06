function goToEventSQL(appRoot,eventName,snapId,databaseId,instanceId,lastSnapId,trId,monitorId,sampleTimeParam){
    
    var connects_url =  appRoot+"/report/stat/oracle/performance/data/loadeventsql/"+snapId+"/"+databaseId+"/"+instanceId+"/"+lastSnapId+"?eventName="+eventName;
    $.getJSON(connects_url,function(data){
    	
    	if($("#load_event_sql_tbody")){
    		$("#load_event_sql_tbody").remove();
    	}
    	
    	var tbody = "<tbody id='load_event_sql_tbody'>";
    	
    	var index = 1;
    	
    	$.each(data, function(i, obj) {
    		
    		
    		var tr = "<tr>"
				+"<td>"+index+"</td>"
				+"<td>"+obj.sampleTime+"</td>"
				+"<td><a href='"+appRoot+"/report/stat/oracle/sqlstat/view?dbId="+monitorId+"&sqlid="+obj.sqlId+"&sampletime="+sampleTimeParam+"' target='_blank'>"+obj.sqlId+"</a></td>"
				+"<td>"+obj.program+"</td>"
				+"</tr>";
    		
			tbody = tbody + tr;
			
			index++;
    		
    	});
    	
    	tbody = tbody + "</tbody>";
    	
    	$("#"+trId).append(tbody);
    });

}     			    




