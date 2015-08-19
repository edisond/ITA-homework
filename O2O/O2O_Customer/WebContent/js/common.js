//获取url中的参数
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
$(function() {
	$.get('GetSessionInfoServlet', function(data) {
		data = $.parseJSON(data);
		$('#userId').text(data.userName);
	});
	$.get('GetFoodTypesServlet', function(data) {
		data = $.parseJSON(data);
		var popularFood = $('#popularFood');
		var noType = true;
		for (var i = 0; i < data.length; i++) {
			var li = $('<li role="presentation"><a href="index.html?type=' + data[i].foodTypeId + '"><i class="glyphicon glyphicon-cutlery"></i>&emsp;' + data[i].foodTypeName + '</a></li>');
			var type = getUrlParam('type');
			
			if (type && type == data[i].foodTypeId) {
				popularFood.removeClass('active');
				li.addClass('active');
				noType = false;
			}
			li.hide().insertAfter(popularFood);
		}
		if (noType) {
			popularFood.addClass('active');
		}
		popularFood.nextAll().slideDown(300);
	});
})
