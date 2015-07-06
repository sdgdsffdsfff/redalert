// 磁盘总量
function loadDiskPie(appRoot,hostId){
	
	var colors = Highcharts.getOptions().colors;
    var categories;
    var name = '磁盘分布';
    var data; 
    
	var connects_url =  appRoot+"/report/stat/disk/data/disk/pie/"+hostId;
	
	//设置同步
	$.ajaxSettings.async = false
	
	$.getJSON(connects_url,function(serverData){
		//数据初始化
		categories = serverData.categories;
		data = serverData.dataList;
	});

    var browserData = [];
    var versionsData = [];
    for (var i = 0; i < data.length; i++) {

        // add browser data
        browserData.push({
            name: categories[i],
            y: data[i].y,
            color: colors[data[i].colorIndex]
        });

        // add version data
        for (var j = 0; j < data[i].drilldown.data.length; j++) {
            var brightness = 0.2 - (j / data[i].drilldown.data.length) / 5 ;
            versionsData.push({
                name: data[i].drilldown.categories[j],
                y: data[i].drilldown.data[j],
                color: Highcharts.Color(colors[data[i].colorIndex]).brighten(brightness).get()
            });
        }
    }

    // Create the chart
    $('#disk_pie').highcharts({
        chart: {
            type: 'pie'
        },
        title: {
            text: '磁盘分布'
        },
        plotOptions: {
            pie: {
                shadow: false,
                center: ['50%', '50%']
            },
            series: {
                cursor: 'pointer',
                events: {
                    click: function(e) {
                        //alert(e.point.id);
                        //location.href = e.point.url;
                        //上面是当前页跳转，如果是要跳出新页面，那就用
                        //window.open(appRoot+'/report/stat/mysql/awr/start/'+e.point.id);
                        //这里的url要后面的data里给出
                    	if(e.point.name.indexOf("剩余") == -1){
                    		var diskParam = e.point.name.replace("[","").replace("]","").replace("剩余","").replace("已用","");
                        	singleDisk(appRoot,hostId,diskParam);
                    	}
                    }
                }
            }
        },
        tooltip: {
    	    valueSuffix: '%'
        },
        series: [{
            name: 'Browsers',
            data: browserData,
            size: '60%',
            dataLabels: {
                formatter: function() {
                    return this.y > 5 ? this.point.name : null;
                },
                color: 'white',
                distance: -30
            }
        }, {
            name: 'Versions',
            data: versionsData,
            size: '80%',
            innerSize: '60%',
            dataLabels: {
                formatter: function() {
                    // display only if larger than 1
                    return this.y > 1 ? '<b>'+ this.point.name +':</b> '+ this.y +'%'  : null;
                }
            }
        }]
    });
	
	
	

}