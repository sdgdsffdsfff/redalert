// 表空间总量
function loadDeltaTbsByTbsName(appRoot,dbId,tbsName){
	var delta_tbs_name_options = {
        chart: {
            renderTo: 'delta_tbs_name', 
            type:'column',
            zoomType: 'xy'
        },
         credits : {
			enabled:false
		},
		colors: ['blue', '#90ed7d', '#f7a35c', '#8085e9', 
                 '#f15c80', '#e4d354', '#8085e8', '#8d4653', '#91e8e1'],
        //报表名称
        title:{
            text:''
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
                        this.y + 'M';
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
	
	
	var connects_url =  appRoot+"/report/stat/tbs/data/tbs/delta/detail/"+dbId+"?tbsname="+tbsName;
	$.getJSON(connects_url,function(data){
	    
	    //数据初始化
	    var len = data.seriesData.length;
	    for(var i=0;i<len;i++){
	    	delta_tbs_name_options.series[i] = data.seriesData[i];
	    	delta_tbs_name_options.series[i].dataLabels = {enabled: true,rotation: -90,color: '#FFFFFF',align: 'right',x: 4,y: 10,style: {fontSize: '13px',fontFamily: 'Verdana, sans-serif',textShadow: '0 0 3px black'}};
	    }
	  
	    new Highcharts.Chart(delta_tbs_name_options);
	});

}