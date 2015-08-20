//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

$(function () {
    $.get('GetSessionInfoServlet', function (data) {
        data = $.parseJSON(data);
        $('#userId').text(data.userName);
    });

    $.get('GetFoodTypesServlet', function (data) {
        data = $.parseJSON(data);
        var foodTypeSeparator = $('#foodTypeSeparator');
        for (var i = 0; i < data.length; i++) {
            $('<li><a href="/O2O_Seller/main/food.html?type=' + data[i].foodTypeId + '">' + data[i].foodTypeName + '</a></li>').insertBefore(foodTypeSeparator);
        }
        $('select[data-role=foodType]').each(function(i,n) {
        	$n=$(n);
        	for (var i = 0; i < data.length; i++) {
                $('<option value="'+data[i].foodTypeId+'">' + data[i].foodTypeName + '</option>').appendTo($n);
            }
		})
    });

})
