function loadQuotas(appRoot,monitor_select,configType) {
	
	var monitor_url = appRoot+"/report/common/quota/list/"+configType;
	$.ajax({
		type : "GET",
		dataType : "json",
		url : monitor_url,
		success : function(data) {
			$("#"+monitor_select).empty();
			$("#"+monitor_select).append("<option value='-1' seleted>选择</option>");
			$.each(data, function(i, n) {
				
				var optionElement = "<option value='" + i + "'>" + data[i] + "</option>";
				$("#"+monitor_select).append(optionElement);
			});
		}
	});
}



