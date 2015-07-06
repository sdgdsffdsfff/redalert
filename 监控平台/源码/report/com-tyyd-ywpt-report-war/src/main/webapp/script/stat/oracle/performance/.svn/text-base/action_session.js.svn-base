function loadActiveSession(appRoot,snapId,databaseId,instanceId,trId,monitorId,sampleTime){
    
    var connects_url =  appRoot+"/report/stat/oracle/performance/data/activesession/"+snapId+"/"+databaseId+"/"+instanceId;
    $.getJSON(connects_url,function(data){
    	
    	var tbody = "";
    	var index = 1;
    	$.each(data, function(i, obj) {
    		
    		var event = obj.event;
    		if(event == 'null' || event == null) 
    			event = '';
    		
    		var tr = "<tr>"
				+"<td>"+index+"</td>"
				+"<td><a href='"+appRoot+"/report/stat/oracle/sqlstat/view?dbId="+monitorId+"&sqlid="+obj.sqlId+"&sampletime="+sampleTime+"' target='_blank'>"+obj.sqlId+"</a></td>"
				+"<td>"+obj.sessionState+"</td>"
				+"<td>"+ event+"</td>"
				+"<td>"+obj.counts+"</td>";
				
			tbody = tbody + tr;
			
			index++;
    		
    	});
    	
    	$("#"+trId).append(tbody);
    });

}     			    




