function sendAndRecv(appRoot,dbId,startDate,endDate){
 	//加载主机
    var send_and_recv_options = {
        chart: {
            renderTo: 'send_recv_bytes_mysql_quota', 
            type: 'areaspline' ,
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
                text: '流量'
            },
            labels: {
                format: '{value}'
            },
        },
        colors: ['green', 'blue', '#90ed7d', '#f7a35c', '#8085e9', 
                 '#f15c80', '#e4d354', '#8085e8', '#8d4653', '#91e8e1'],
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
                        this.y ;
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
					return vDate.getHours()+":"+vDate.getMinutes()+":"+vDate.getSeconds();
				}
    		}
        },
        //数据来源   [{},{},{},{}]
        series: [
        	{}
        ]
    };
    
    
    var send_and_recv_url =  appRoot+"/report/quota/mysql/data/sendandrecv/"+dbId+"/"+startDate+"/"+endDate;
    $.getJSON(send_and_recv_url,function(data){
        //报表名称
        send_and_recv_options.title.text = data.reportName;
        
        //数据初始化
        var len = data.seriesData.length;
        for(var i=0;i<len;i++){
        	send_and_recv_options.series[i] = data.seriesData[i];
        }
      
        new Highcharts.Chart(send_and_recv_options);
    });
}     			    
		    
