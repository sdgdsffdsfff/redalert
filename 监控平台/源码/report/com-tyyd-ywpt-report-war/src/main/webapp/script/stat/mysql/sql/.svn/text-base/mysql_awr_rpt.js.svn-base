function loadRpt(appRoot,dbId,startDate,endDate,checkSum){
    
    
    var connects_url =  appRoot+"/report/stat/mysql/awr/data/sql/rpt/"+checkSum+"/"+startDate+"/"+endDate+"/"+dbId;
    $.getJSON(connects_url,function(data){
        
        //总执行次数
        parseMySQLAwrRptData(exec_count_options ,data.execCount);
        
        //SQL执行消耗总时间
        parseMySQLAwrRptData(query_time_sum_options ,data.queryTimeSum);

        //锁等待总时间
        parseMySQLAwrRptData(lock_time_sum_options ,data.lockTimeSum);
        
        //传输数据行数
        parseMySQLAwrRptData(rows_sent_sum_options ,data.rowsSentSum);
        
        //扫描数据行数
        parseMySQLAwrRptData(rows_examined_sum_options ,data.rowsExaminedSum);
        
        //改变数据行数
        parseMySQLAwrRptData(rows_affected_sum_options ,data.rowsAffectedSum);
        
    	//全表扫描次数
        parseMySQLAwrRptData(full_scan_cnt_options ,data.fullScanCnt);
        
        //全表连接次数
        parseMySQLAwrRptData(full_join_cnt_options ,data.fullJoinCnt);
        
    	//使用临时表数量
        parseMySQLAwrRptData(tmp_table_cnt_options ,data.tmpTableCnt);
        
        //使用硬盘上临时表数量
        parseMySQLAwrRptData(tmp_table_on_disk_cnt_options ,data.tmpTableOnDiskCnt);
        
        //使用文件排序次数
        parseMySQLAwrRptData(file_sort_cnt_options ,data.fileSortCnt);
        
        //硬盘上文件排序次数
        parseMySQLAwrRptData(file_sort_on_dist_cnt_options ,data.filesortOnDistCnt);
        
    });
    
    
    
    
}


/**
 * 通用解析
 * @param options
 * @param data
 */
function parseMySQLAwrRptData(options, data){
	//报表名称
	options.title.text = data.reportName;
    
    //数据初始化
    var len = data.seriesData.length;
    for(var i=0;i<len;i++){
    	options.series[i] = data.seriesData[i];
    }
  
    new Highcharts.Chart(options);
}




