function loadSQLDesc(appRoot,checkSum,trId){
    
    var connects_url =  appRoot+"/report/stat/mysql/awr/data/sql/sqlstat/"+checkSum;
    $.getJSON(connects_url,function(data){

    	var tbody = "<ul>"
    			+"<li> 开始时间 : "+data.minDate+"</li><br/>"
    			+"<li> 结束时间 : "+data.maxDate+"</li><br/>"
    			+"<li> SQL : "+data.fingerPrint+"</li><br/>"
    			+"<li> 样例 : "+data.sample+"</li>"
    			+"</ul>";
    	
    	$("#"+trId).empty();
    	$("#"+trId).append(tbody);
    });

}




