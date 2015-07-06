function loadMonitors(appRoot,monitor_select,configType) {
	var monitor_url = appRoot+"/report/data/general/monitor/"+configType;
	$.ajax({
		type : "GET",
		dataType : "json",
		url : monitor_url,
		success : function(data) {
			$("#"+monitor_select).empty();
			$("#"+monitor_select).append("<option value='-1' seleted>选择</option>");
			$.each(data, function(i, n) {
				var optionElement = "<option value='" + n.monitorId + "'>" + n.monitorName + "[" + n.ipAddr + "]" + "</option>";
				$("#"+monitor_select).append(optionElement);
			});
		}
	});
}


function loadMonitorsOptionSelected(appRoot,monitor_select,configType,selectedValue){
	var monitor_url = appRoot+"/report/data/general/monitor/"+configType;
	$.ajax({
		type : "GET",
		dataType : "json",
		url : monitor_url,
		success : function(data) {
			$("#"+monitor_select).empty();
			$("#"+monitor_select).append("<option  value='-1' seleted>选择</option>");
			$.each(data, function(i, n) {
				var optionElement = "<option value='" + n.monitorId + "'>" + n.monitorName + "[" + n.ipAddr + "]" + "</option>";
				$("#"+monitor_select).append(optionElement);
			});
			$("#"+monitor_select).val(selectedValue);
		}
	});
} 