function loadEventClass(appRoot,waitClass,snapId,databaseId,instanceId,lastSnapId,trId,monitorId,sampleTime){
    
    var connects_url =  appRoot+"/report/stat/oracle/performance/data/loadeventbyclass/"+snapId+"/"+databaseId+"/"+instanceId+"/"+lastSnapId+"?waitClass="+waitClass;
    $.getJSON(connects_url,function(data){
    	
    	if($("#load_event_class_tbody")){
    		$("#load_event_class_tbody").remove();
    	}
    	
    	var tbody = "<tbody id='load_event_class_tbody'>";
    	var index = 1;
    	
    	$.each(data, function(i, obj) {
    		
    		
    		var tr = "<tr>"
				+"<td>"+index+"</td>"
				+"<td><a href=\"javascript:void(0)\" onclick=\"goToEventSQL('"+appRoot+"','"+obj.eventName+"','"+snapId+"','"+databaseId+"','"+instanceId+"','"+lastSnapId+"','load_event_sql_segment','"+monitorId+"','"+sampleTime+"')\">"+obj.eventName+"</a></td>"
				+"<td>"+obj.waits+"</td>"
				+"<td>"+obj.timeouts+"</td>"
				+"<td>"+obj.waitTime+"</td>"
				+"</tr>";
    		
    		
			tbody = tbody + tr;
			
			index++;
    		
    	});
    	
    	tbody = tbody + "</tbody>";
    	
    	$("#"+trId).append(tbody);
    });

}     			    




