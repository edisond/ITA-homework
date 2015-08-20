$(function () {
    var type = getUrlParam('type');
    var tbody = $('table').find('tbody')
    $.get('GetFoodServlet', {
        type: type
    }, function (data) {
        data = $.parseJSON(data);
        tbody.hide();
        for (var i = 0; i < data.length; i++) {
            var tr = $('<tr>');
            $('<td>').text(data[i].foodId).appendTo(tr);
            $('<td>').text(data[i].foodName).appendTo(tr);
            $('<td>').text(data[i].price).appendTo(tr);
            $('<td>').html('<img src="data:image/jpeg;base64,' + data[i].pictureBodyBase64 + '">').appendTo(tr);
            $('<td>').text(data[i].statusId).appendTo(tr);
            $('<td>').html('<a href="UpdateFoodServlet?id=' + data[i].foodId + '">update</a>&emsp;<a href="javascript:;" data-role="delete" data-id="' + data[i].foodId + '" onclick="return confirm(\'Delete this item?\')">delete</a>').appendTo(tr);
            tr.appendTo(tbody);
            
        }
        tbody.fadeIn(300);
    })

    tbody.delegate('a[data-role=delete]', 'click', function () {
        var id = this.getAttribute('data-id');
        var $this = $(this);
        $.post('DeleteFoodServlet', {
            id: id
        }, function (data) {
            data = $.parseJSON(data);
            if (data.status == 'success') {
                $this.parents('tr').remove();
            } else {
                alert('删除失败，请重试')
            }
        })

    })
})