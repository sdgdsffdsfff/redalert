function ventOne(appRoot,startDate,endDate){
 	//加载主机
    var vent_one_options = {
        chart: {
            renderTo: 'vent_one', 
            zoomType: 'xy'
        },
         credits : {
			enabled:false
		},
        //报表名称
        title:{
            text:''
        },
        colors: ['green', 'blue', '#90ed7d', '#f7a35c', '#8085e9', 
                 '#f15c80', '#e4d354', '#8085e8', '#8d4653', '#91e8e1'],
        yAxis: { 
		        	min:0, // 定义最小值  
		            minPadding: 0.2,   
		            maxPadding: 0.2,  
		        	labels: {
		                format: '{value}'
		            },
	                title: {
	                    text: '互联星空1'
	                }
            	},
        tooltip: {
        	shared:true,
            formatter: function() {
            	var mydate = new Date(this.x);
            	var year = mydate.getFullYear();
            	var month = parseInt(mydate.getMonth())+1;
            	var day = mydate.getDate();
            	var date = mydate.getHours()+":"+mydate.getMinutes();
            	date = year+"-"+month+"-"+day+" "+date;
            	
                var s = '<b>' + date + '</b>';

                $.each(this.points, function () {
                    s += '<br/>' + this.series.name + ': ' +
                        this.y + '%';
                });

                return s;
            }
		},
		legend: {
            layout: 'vertical',
            align: 'left',
            x: 120,
            verticalAlign: 'top',
            y: 100,
            floating: true,
            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
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
    
    
    var cpu_url =  appRoot+"/report/firewall/data/vent/one/"+startDate+"/"+endDate;
    
    $.getJSON(cpu_url,function(data){
        //报表名称
        vent_one_options.title.text = data.reportName;
        
        //数据初始化
        var len = data.seriesData.length;
        for(var i=0;i<len;i++){
        	vent_one_options.series[i] = data.seriesData[i];
        }
      
        new Highcharts.Chart(vent_one_options);
    });
}     			    
		    
