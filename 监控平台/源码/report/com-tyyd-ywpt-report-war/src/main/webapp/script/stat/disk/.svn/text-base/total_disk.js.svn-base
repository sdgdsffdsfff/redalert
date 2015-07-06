// 磁盘总量
function loadTotalDisk(appRoot,hostId){
	var disk_total_options = {
        chart: {
            renderTo: 'disk_total', 
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
            labels: {
                format: '{value}G'
            },
        	title: {
                text: '磁盘总量'
            }
        },
        tooltip: {
        	shared:true,
            formatter: function() {
            	var mydate = new Date(this.x);
            	var year = mydate.getFullYear();
            	var month = parseInt(mydate.getMonth())+1;
            	var day = mydate.getDate();
            	var date = year+"-"+month+"-"+day;
            	
                var s = '<b>' + date + '</b>';

                $.each(this.points, function () {
                    s += '<br/>' + this.series.name + ': ' +
                        this.y + 'G';
                });

                return s;
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
					return (vDate.getMonth()+1)+"-"+vDate.getDate();
				}
    		}
        },
        //数据来源   [{},{},{},{}]
        series: [
        	{}
        ]
    };
	
	
	var connects_url =  appRoot+"/report/stat/disk/data/disk/total/"+hostId;
	$.getJSON(connects_url,function(data){
	    //报表名称
	   // disk_total_options.title.text = data.reportName;
	    
	    //数据初始化
	    var len = data.seriesData.length;
	    for(var i=0;i<len;i++){
	    	disk_total_options.series[i] = data.seriesData[i];
	    }
	  
	    new Highcharts.Chart(disk_total_options);
	});

}