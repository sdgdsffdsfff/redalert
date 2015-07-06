<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	 <style type="text/css">
		.conddiv ul{list-style:none;} 
		.conddiv li{float:left;} 
    </style>
	
	<script type="text/javascript" src="${webRoot}/script/jquery-1.8.3.min.js"></script>
	<script src="${webRoot}/script/highcharts/js/highcharts.js"></script>
	<script src="${webRoot}/script/highcharts/js/modules/exporting.js"></script>
	
	<script type="text/javascript">
	    
	     $(document).ready(function(){
	     	
	     	//加载主机
		    var options = {
		        chart: {
		            renderTo: 'container', 
		            type: 'spline'//报表类型
		        },
		         credits : {
					enabled:false
				},
		        //报表名称
		        title:{
		            text:''
		        },
		        yAxis: {
		            min: 0,
		            title: {
		                text: ''
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
	            		return date+'<br >'+this.series.name+': '+ (this.y);					                  
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
		    
		    var url =  "${webRoot}/report/data/general/multi/quota/10/1/1/1_2_5";
		    $.getJSON(url,function(data){
		        var i = 0;
		        var len=data.length;
		        
		        //报表名称
		        options.title.text = data.reportName;
		        
	            //数据初始化
	            var len = data.seriesData.length;
	            for(var i=0;i<len;i++){
	            	options.series[i] = data.seriesData[i];
	            }
	          
		        new Highcharts.Chart(options);
		    });
		    
		    
		});
		
		
		function loadData() {  
			var monitorOPtions = $("#monitor_select");
            var configType = $("#config_type_select option:selected").val();  
            var url = "${webRoot}/report/data/general/monitor/"+configType;
            //console.log(url);
            $.ajax({  
                type: "POST",  
                dataType: "json",  
                url: url,  
                success: function (data) {
                    monitorOPtions.empty();
                    $.each(data, function (i, n) {
                        $("<option value=" + n.monitorId + ">" + n.monitorName + "["+n.ipAddr+"]" + "</option>").appendTo(monitorOPtions);  
                    });
                }  
            });  
        } 
		
		
	</script>

	<title>通用指标报表</title>
	
	
</head>
<body>
	
	<div class="conditon_div">
		<ul>
			<li>监控机类型</li>
			<li>
				<select id="config_type_select" name="config_type_select" onChange="loadData()">
					<option value="0" seleted>选择</option>
					<option value="1">主机</option>
					<option value="2">ORACLE</option>
					<option value="3">MySQL</option>
				</select>
			</li>
			<li>服务</li>
			<li>
				<select id="monitor_select">
					<option seleted>选择</option>
				</select>
			</li>
		</ul>
	</div>
	
	<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	
	
</body>
</html>