function loadBaseLine(appRoot,monitorId,configType,startDate,endDate,quotaId){
 	
    var baseline_options = {
        chart: {
            renderTo: 'baseline_quota', 
            zoomType: 'xy'
        },
         credits : {
			enabled:false
		},
        //报表名称
        title:{
            text:''
        },
        colors: ['#00aeef', 'black', '#f7a35c', '#8085e9', 
                 '#f15c80', '#e4d354', '#8085e8', '#8d4653', '#91e8e1'],
                 
        yAxis: { 
		        	min:0, // 定义最小值  
		            minPadding: 0.2,   
		            maxPadding: 0.2,  
		        	labels: {
		                format: '{value}%'
		            },
	                title: {
	                    text: ''
	                }
        },
    	tooltip: {
    		crosshairs: true,
	        shared: true
		},
        //x轴显示内容
        xAxis: {
            type: 'datetime',
            labels: {
        		overflow: 'justify',
        		formatter:function() {
					var mydate = new Date(this.value);
//					var year = mydate.getFullYear();
//	            	var month = parseInt(mydate.getMonth())+1;
//	            	var day = mydate.getDate();
	            	var date = mydate.getHours()+":"+mydate.getMinutes();
	            	//date = year+"-"+month+"-"+day+" "+date;
					return date;
				}
    		}
        },
        //数据来源   [{},{},{},{}]
        series: [
        	{}
        ]
    };
    
    
    var baseline_url =  appRoot+"/report/quota/baseline/data/single/"+monitorId+"/"+configType+"/"+quotaId+"/"+startDate+"/"+endDate;
    
    $.getJSON(baseline_url,function(data){
        //报表名称
    	baseline_options.title.text = data.reportName;
        
        //数据初始化
    	
    	//上下轨
        baseline_options.series[0] = data.areaRange;
        baseline_options.series[0].lineWidth = 0;
        baseline_options.series[0].fillOpacity =  0.3;
        baseline_options.series[0].linkedTo = ':previous',
        baseline_options.series[0].color = '#00aeef';
        
        
        //中轨
//        baseline_options.series[1] = data.avgRange;
//        baseline_options.series[1].marker = {fillColor: 'white',lineWidth: 2};
        
        
        //当前指标值
        baseline_options.series[1] = data.quotaData;
        
//        baseline_options.title.text = "MySQL商用集中库Load 动态基线";
        
        new Highcharts.Chart(baseline_options);
    });
}     			    
		    
