// 传输数据行数

var rows_sent_sum_options = {
        chart: {
            renderTo: 'rows_sent_sum', 
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
                text: '传输数据行数'
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