function loadFreeMemory(appRoot,dbId,beginTime,endTime){
 	//加载主机
    var connects_options = {
        chart: {
            renderTo: 'free_memory', 
            type:'spline',
            zoomType: 'xy'
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
            title: {
                text: 'Free Memory'
            }
        },
        tooltip: {
            formatter: function() {
            	var mydate = new Date(this.x);
            	var year = mydate.getFullYear();
            	var month = parseInt(mydate.getMonth())+1;
            	var day = mydate.getDate();
            	var date = mydate.getHours()+":"+mydate.getMinutes();
            	date = year+"-"+month+"-"+day+" "+date;
        		return date+'<br >'+this.series.name+': '+ (this.y) +'M' ;					                  
            }
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
            pointInterval: 3600000
        },
        navigation: {
	          menuItemStyle: {
	              fontSize: '10px'
	          }
	      },
        //x轴显示内容
        xAxis: {
            type: 'datetime',
            labels: {
        		overflow: 'justify',
        		formatter:function() {
					var vDate = new Date(this.value);
					return vDate.getHours()+":"+vDate.getMinutes()+":"+vDate.getSeconds();
				}
    		}
        },
        //数据来源   [{},{},{},{}]
        series: [
        	{}
        ]
    };
    
    
    var connects_url =  appRoot+"/report/stat/oracle/sga/data/freememory/"+dbId+"/"+beginTime+"/"+endTime;
    $.getJSON(connects_url,function(data){
        //报表名称
        connects_options.title.text = data.reportName;
        
        //数据初始化
        var len = data.seriesData.length;
        for(var i=0;i<len;i++){
        	connects_options.series[i] = data.seriesData[i];
        }
      
        new Highcharts.Chart(connects_options);
    });
    
    
    
    
}     			    




