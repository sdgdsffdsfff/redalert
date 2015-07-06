function sortAwrTopSQL(appRoot,id,trId,columnId){
	
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
	
    var connects_url =  appRoot+"/report/stat/mysql/awr/data/topsql/"+id+"?columnId="+columnId+"&sortType="+sortType;
    $.getJSON(connects_url,function(data){
    	
    	
    	if($("#sql_id_segment_tbody")){
    		$("#sql_id_segment_tbody").remove();
    	}
    	
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
    	
    	//正序,使用完后则清除
    	if(columnId == lastSortColumn){
    		$("#sql_id_segment_columnsort").val("");
    	}else{
    		$("#sql_id_segment_columnsort").val(columnId);
    	}
    	
    });
    
    
 

} 




function initAwrTopSortTable(appRoot,id,trId){
	
	TableOrderOper.Init(trId, 0, {
        OnShow: function (i, trJqObj, _tbodyObj) {
            trJqObj.attr("class", ((i + 1) % 2 == 0 ? "hoverTr" : ""));
        }
	});
	
    
    TableOrderOper.SetOrder("sql_execCount", 3, { DataType: "int", DefaultOrder: true, OnClick: function () {
    	sortAwrTopSQL(appRoot,id,trId,"sql_execCount");
    }});
    
	TableOrderOper.SetOrder("sql_queryTimeSum", 4, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortAwrTopSQL(appRoot,id,trId,"sql_queryTimeSum");
	}});
	
	TableOrderOper.SetOrder("sql_lockTimeSum", 5, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortAwrTopSQL(appRoot,id,trId,"sql_lockTimeSum");
	}});
	
	TableOrderOper.SetOrder("sql_rowsSentSum", 6, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortAwrTopSQL(appRoot,id,trId,"sql_rowsSentSum");
	}});
	
	TableOrderOper.SetOrder("sql_rowsExaminedSum", 7, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortAwrTopSQL(appRoot,id,trId,"sql_rowsExaminedSum");
	}});
	
	TableOrderOper.SetOrder("sql_rowsAffectedSum", 8, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortAwrTopSQL(appRoot,id,trId,"sql_rowsAffectedSum");
	}});
	
	TableOrderOper.SetOrder("sql_fullScanCnt", 9, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortAwrTopSQL(appRoot,id,trId,"sql_fullScanCnt");
	}});
	
	TableOrderOper.SetOrder("sql_fullJoinCnt", 10, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortAwrTopSQL(appRoot,id,trId,"sql_fullJoinCnt");
	}});
	
	TableOrderOper.SetOrder("sql_tmpTableCnt", 11, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortAwrTopSQL(appRoot,id,trId,"sql_tmpTableCnt");
	}});
	
	TableOrderOper.SetOrder("sql_tmpTableOnDiskCnt", 12, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortAwrTopSQL(appRoot,id,trId,"sql_tmpTableOnDiskCnt");
	}});
	
	TableOrderOper.SetOrder("sql_fileSortCnt", 13, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortAwrTopSQL(appRoot,id,trId,"sql_fileSortCnt");
	}});
	
	TableOrderOper.SetOrder("sql_filesortOnDistCnt", 14, { DataType: "int", DefaultOrder: true, OnClick: function () {
		sortAwrTopSQL(appRoot,id,trId,"sql_filesortOnDistCnt");
	}});
	
	
}