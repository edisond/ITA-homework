$(function() {
	var orders = $('.orders').empty();
	$.get('CartServlet?act=get', function(data) {
		data = $.parseJSON(data);
		console.log(data);
	})
	$('#foodContainer').delegate('button', 'click', function() {
		var $this = $(this);
		var json = {
			id : $this.data('id'),
			price : $this.data('price'),
			price : $this.data('price')
		}
		$.post('CartServlet?act=addFood', json, function(data) {
			console.log(data)
		})
	})
})