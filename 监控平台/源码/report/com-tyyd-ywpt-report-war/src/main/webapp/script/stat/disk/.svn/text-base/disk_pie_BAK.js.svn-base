// 磁盘总量
function loadDiskPie(appRoot,hostId){
	var disk_pie_options = {
        chart: {
            renderTo: 'disk_pie', 
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
         credits : {
			enabled:false
		},
        //报表名称
        title:{
            text:''
        },
        yAxis: { 
        	min:0, // 定义最小值  
            minPadding: 0.2,   
            maxPadding: 0.2,  
            labels: {
                format: '{value}G'
            },
        	title: {
                text: '磁盘总量'
            }
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            area: {
            	allowPointSelect: true,
    		    lineWidth: 0,			
                states: {
                   hover: {			      	   	  
                      lineWidth: 1
                   }
                },
            	marker: {
	                enabled: false,
	 			   radius: 0,
	                states: {
	                   hover: {
	                      enabled: true,
	                      symbol: 'circle',
	                      radius: 2,
	                      lineWidth: 0
	                   }
	                }   
	             }
            },
            pointInterval: 3600000,
            series: {
                cursor: 'pointer',
                events: {
                    click: function(e) {
                        //alert(e.point.id);
                        //location.href = e.point.url;
                        //上面是当前页跳转，如果是要跳出新页面，那就用
                        //window.open(appRoot+'/report/stat/mysql/awr/start/'+e.point.id);
                        //这里的url要后面的data里给出
                    	singleDisk(appRoot,hostId,e.point.name);
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: '磁盘分布'
        }]
    };
	
	
	var connects_url =  appRoot+"/report/stat/disk/data/disk/pie/"+hostId;
	$.getJSON(connects_url,function(data){
	    //报表名称
	   // disk_total_options.title.text = data.reportName;
	    
	    //数据初始化
	    disk_pie_options.series[0].data = data;
	  
	    new Highcharts.Chart(disk_pie_options);
	});

}