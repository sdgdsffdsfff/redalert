function loadTotalWaitEvent(appRoot,snapId,databaseId,instanceId,lastSnapId,trId,monitorId,sampleTime){
    
    var connects_url =  appRoot+"/report/stat/oracle/performance/data/totalwaitevent/"+snapId+"/"+databaseId+"/"+instanceId+"/"+lastSnapId;
    $.getJSON(connects_url,function(data){
    	
    	var tbody = "";
    	var index = 1;
    	$.each(data, function(i, obj) {
    		
    		
    		var tr = "<tr>"
				+"<td>"+index+"</td>"
				+"<td><a href=\"javascript:void(0)\" onclick=\"loadEventClass('"+appRoot+"','"+obj.waitClass+"','"+snapId+"','"+databaseId+"','"+instanceId+"','"+lastSnapId+"','load_event_class_segment','"+monitorId+"','"+sampleTime+"')\">"+obj.waitClass+"</a></td>"
				+"<td>"+obj.waits+"</td>"
				+"<td>"+obj.timeouts+"</td>"
				+"<td>"+obj.waitTime+"</td>"
				+"</tr>";
    		
			tbody = tbody + tr;
			
			index++;
    		
    	});
    	
    	$("#"+trId).append(tbody);
    });

}     			    




