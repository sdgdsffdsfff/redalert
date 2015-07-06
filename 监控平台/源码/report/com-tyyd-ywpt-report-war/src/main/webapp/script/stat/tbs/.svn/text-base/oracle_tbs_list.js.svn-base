function loadTbsList(appRoot,dbId,trId){
    
    var connects_url =  appRoot+"/report/stat/tbs/data/tbs/info/"+dbId;
    $.getJSON(connects_url,function(data){
    	
    	var tbody = "<tbody>";
    	$.each(data, function(i, arr) {
    		var tr = "<tr>";
    		var td = "";
    		$.each(arr, function(j, obj) {
    			var tbsName = obj.tbsName;
    			var isSpace = false;
    			if(tbsName == null || tbsName == 'null' || tbsName == ''){
    				tbsName = "";
    				isSpace = true;
    			}
    			
    			if(isSpace){
    				td = td + "<td align=\"center\" width=\"30%\">"+tbsName+"</td>";
    				return true; //等于continue
    			}
    			
    			var used = obj.usedTbs;
    			var max = obj.maxTbs;
    			var percent = obj.usePercent;
    			var altContent = "已用("+used+"G),上限("+max+"G),"+"利用率("+percent+"%)";
    			
    			td = td + "<td align=\"center\" width=\"30%\"><a href='javascript:void(0)' onclick=\"singleTbs('"+appRoot+"','"+dbId+"','"+tbsName+"')\" title='"+altContent+"'>"+tbsName+"</a></td>";
    		});
    		
    		tr = tr + td + "</tr>";
			tbody = tbody + tr;
    	});
    	
    	tbody = tbody + "</tbody>";
    	
    	$("#"+trId).append(tbody);
    });

}     			    




