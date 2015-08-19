$(function() {
	var type = getUrlParam('type');
	var container = $('#foodContainer');
	$.get('GetFoodServlet', {
		type : type
	}, function(data) {
		data = $.parseJSON(data);
		window.foods = data;
		container.hide().empty();
		for (var i = 0; i < data.length; i++) {
			var food = '';
			food += '<div class="col-xs-4">';
			food += '<div class="mealBox">';
			food += '<a href="#" class="thumbnail">';
			food += '<img src="data:image/jpeg;base64,' + data[i].pictureBodyBase64 + '">';
			food += '</a>';
			food += '<h4>' + data[i].foodName + '</h4>';
			food += '<h5 class="text-muted"></h5>';
			food += '<div class="row">';
			food += '<div class="col-xs-7">';
			food += '<h4>$' + data[i].price + '</h4></div>';
			food += '<div class="col-xs-5 text-right">';
			food += '<button class="btn btn-primary" data-id="' + data[i].foodId + '" data-name="' + data[i].foodName + '" data-price="' + data[i].price + '">Add</button>';
			food += '</div>';
			food += '</div>';
			food += '</div>';
			food += '</div>';
			food = $(food);
			food.appendTo(container);
		}
		container.fadeIn(300);
	})
})