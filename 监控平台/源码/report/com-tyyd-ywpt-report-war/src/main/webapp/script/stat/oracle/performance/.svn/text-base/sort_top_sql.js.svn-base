function sortTopSQL(appRoot,snapId,databaseId,instanceId,trId,monitorId,sampleTime,columnId){
    
	//没有排序列，不处理
	if(columnId == ''){
		return false;
	}
	
	//获取上次的排序列
	var lastSortColumn = $("#sql_id_segment_columnsort").val();
	
	//如果两次一致，则顺序，从小到大
	if(columnId == lastSortColumn){
		sortType = '0';
	}else{
		sortType = '1';
	}
	
	
    var connects_url =  appRoot+"/report/stat/oracle/performance/data/topsql/"+snapId+"/"+databaseId+"/"+instanceId+"?columnId="+columnId+"&sortType="+sortType;;
    $.getJSON(connects_url,function(data){
    	
    	if($("#sql_id_segment_tbody")){
    		$("#sql_id_segment_tbody").remove();
    	}
    	
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
    	
    	//正序,使用完后则清除
    	if(columnId == lastSortColumn){
    		$("#sql_id_segment_columnsort").val("");
    	}else{
    		$("#sql_id_segment_columnsort").val(columnId);
    	}
    	
    	
    });

}     			    

function initOracleSortTopSQL(appRoot,snapId,databaseId,instanceId,trId,monitorId,sampleTime){
	
	TableOrderOper.Init(trId, 0, {
        OnShow: function (i, trJqObj, _tbodyObj) {
            trJqObj.attr("class", ((i + 1) % 2 == 0 ? "hoverTr" : ""));
        }
	});
	
    
    TableOrderOper.SetOrder("sql_fetchesDelta", 2, { DataType: "int", DefaultOrder: true, OnClick: function () {
    	sortTopSQL(appRoot,snapId,databaseId,instanceId,"sql_id_segment",monitorId,sampleTime,"sql_fetchesDelta");
    }});
    
	TableOrderOper.SetOrder("sql_sortsDelta", 3, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortTopSQL(appRoot,snapId,databaseId,instanceId,"sql_id_segment",monitorId,sampleTime,"sql_sortsDelta");
	}});
	
	TableOrderOper.SetOrder("sql_executionsDelta", 4, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortTopSQL(appRoot,snapId,databaseId,instanceId,"sql_id_segment",monitorId,sampleTime,"sql_executionsDelta");
	}});
	
	TableOrderOper.SetOrder("sql_parseCallsDelta", 5, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortTopSQL(appRoot,snapId,databaseId,instanceId,"sql_id_segment",monitorId,sampleTime,"sql_parseCallsDelta");
	}});
	
	TableOrderOper.SetOrder("sql_diskReadsDelta", 6, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortTopSQL(appRoot,snapId,databaseId,instanceId,"sql_id_segment",monitorId,sampleTime,"sql_diskReadsDelta");
	}});
	
	TableOrderOper.SetOrder("sql_bufferGetsDelta", 7, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortTopSQL(appRoot,snapId,databaseId,instanceId,"sql_id_segment",monitorId,sampleTime,"sql_bufferGetsDelta");
	}});
	
	TableOrderOper.SetOrder("sql_rowsProcessedDelta", 8, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortTopSQL(appRoot,snapId,databaseId,instanceId,"sql_id_segment",monitorId,sampleTime,"sql_rowsProcessedDelta");
	}});
	
	TableOrderOper.SetOrder("sql_cpuTimeDelta", 9, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortTopSQL(appRoot,snapId,databaseId,instanceId,"sql_id_segment",monitorId,sampleTime,"sql_cpuTimeDelta");
	}});
	
	TableOrderOper.SetOrder("sql_elapsedTimeDelta", 10, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortTopSQL(appRoot,snapId,databaseId,instanceId,"sql_id_segment",monitorId,sampleTime,"sql_elapsedTimeDelta");
	}});
	
	TableOrderOper.SetOrder("sql_iowaitDelta", 11, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortTopSQL(appRoot,snapId,databaseId,instanceId,"sql_id_segment",monitorId,sampleTime,"sql_iowaitDelta");
	}});
	
	TableOrderOper.SetOrder("sql_clwaitDelta", 12, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortTopSQL(appRoot,snapId,databaseId,instanceId,"sql_id_segment",monitorId,sampleTime,"sql_clwaitDelta");
	}});
	
	TableOrderOper.SetOrder("sql_apwaitDelta", 13, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortTopSQL(appRoot,snapId,databaseId,instanceId,"sql_id_segment",monitorId,sampleTime,"sql_apwaitDelta");
	}});
	
	TableOrderOper.SetOrder("sql_ccwaitDelta", 14, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortTopSQL(appRoot,snapId,databaseId,instanceId,"sql_id_segment",monitorId,sampleTime,"sql_ccwaitDelta");
	}});
}


