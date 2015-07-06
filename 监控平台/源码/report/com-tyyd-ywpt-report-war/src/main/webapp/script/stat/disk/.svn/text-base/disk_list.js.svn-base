function loadDiskList(appRoot,hostId,trId){
    
    var connects_url =  appRoot+"/report/stat/disk/data/disk/info/"+hostId;
    $.getJSON(connects_url,function(data){
    	
    	var tbody = "<tbody>";
    	$.each(data, function(i, arr) {
    		var tr = "<tr>";
    		var td = "";
    		$.each(arr, function(j, obj) {
    			var diskName = obj.diskName;
    			var isSpace = false;
    			if(diskName == null || diskName == 'null' || diskName == ''){
    				diskName = "";
    				isSpace = true;
    			}
    			
    			if(isSpace){
    				td = td + "<td align=\"center\" width=\"30%\">"+diskName+"</td>";
    				return true; //等于continue
    			}
    			
    			var diskName = obj.diskName;
    			var used = obj.used;
    			var remain = obj.remain;
    			var percent = obj.usedPercent;
    			var altContent = "已用("+used+"G),剩余("+remain+"G),"+"利用率("+percent+"%)";
    			
    			td = td + "<td align=\"center\" width=\"30%\"><a href='javascript:void(0)' onclick=\"singleDisk('"+appRoot+"','"+hostId+"','"+diskName+"')\" title='"+altContent+"'>"+diskName+"</a></td>";
    		});
    		
    		tr = tr + td + "</tr>";
			tbody = tbody + tr;
    	});
    	
    	tbody = tbody + "</tbody>";
    	
    	$("#"+trId).append(tbody);
    });

}     			    




