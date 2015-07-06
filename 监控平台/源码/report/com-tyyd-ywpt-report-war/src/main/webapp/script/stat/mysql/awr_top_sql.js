function loadAwrTopSQL(appRoot,id,trId){
    
    var connects_url =  appRoot+"/report/stat/mysql/awr/data/topsql/"+id;
    $.getJSON(connects_url,function(data){
    	
    	var tbody = "<tbody id='sql_id_segment_tbody'>";
    	var index = 1;
    	$.each(data, function(i, obj) {
    		
    		var tr = "<tr>"
				+"<td>"+index+"</td>"
				+"<td style='word-break:break-all;width:60px;overflow: hidden;'>"+obj.dbMax+"</td>"
				+"<td style='word-break:break-all;width:80px;overflow: hidden;'><a href='"+appRoot+"/report/stat/mysql/awr/sql/"+id+"/"+obj.checkSum+"' target='_blank'>"+obj.checkSum+"</a></td>"
				
				+"<td>"+obj.execCount+"</td>"
				+"<td>"+obj.queryTimeSum+"</td>"
				+"<td>"+obj.lockTimeSum+"</td>"
				+"<td>"+obj.rowsSentSum+"</td>"
				+"<td>"+obj.rowsExaminedSum+"</td>"
				+"<td>"+obj.rowsAffectedSum+"</td>"
				+"<td>"+obj.fullScanCnt+"</td>"
				+"<td>"+obj.fullJoinCnt+"</td>"
				+"<td>"+obj.tmpTableCnt+"</td>"
				+"<td>"+obj.tmpTableOnDiskCnt+"</td>"
				+"<td>"+obj.fileSortCnt+"</td>"
				+"<td>"+obj.filesortOnDistCnt+"</td>"
				
				+"</tr>";
				
			tbody = tbody + tr;
			
			index++;
    		
    	});
    	
    	tbody = tbody + "</tbody>";
    	$("#"+trId).append(tbody);
    });
    
    
 

}     			    




