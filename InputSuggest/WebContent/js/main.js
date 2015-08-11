(function () {

    var suggestionPanel = new SuggestionPanel('suggests');

    document.getElementById('inputSearch').onkeyup = function (event) {
        var inputSearch = this;
        var query = inputSearch.value;
        if (!query) {
            suggestionPanel.reset();
            return false;
        }

        if (suggestionPanel.hasCache(query)) {
            suggestionPanel.showFromCache(query);
        } else {
            var xhr = new XHR();
            xhr.open('GET', 'InputSuggestServlet?query=' + query);
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        var data = eval(xhr.responseText);
                        suggestionPanel.data = data;
                        suggestionPanel.show();
                        suggestionPanel.cache(query, data);
                    }
                }
            }
            xhr.send(null);
        }
    }

})()
