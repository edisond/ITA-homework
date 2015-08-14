$(function () {
    var type = getUrlParam('type');

    $.get('GetFoodServlet', {
        type: type
    }, function (data) {
        data = $.parseJSON(data);




    })

})
