function SuggestionPanel(rootId) {
    this.element = document.getElementById(rootId);
    this.data = [];
    this.chache = [];
}

SuggestionPanel.prototype.reset = function () {
    this.element.innerHTML = '';
}

SuggestionPanel.prototype.show = function () {
    var _this = this;
    _this.element.innerHTML = '';
    if (_this.data.length > 0) {
        var ul = document.createElement('ul');
        for (var i = 0; i < _this.data.length; i++) {
            var li = document.createElement('li');
            li.innerHTML = _this.data[i];
            li.onclick = function () {
                inputSearch.value = this.innerHTML;
                _this.element.removeChild(this.parentNode);
            }
            ul.appendChild(li);
        }
        _this.element.appendChild(ul);
    }
}

SuggestionPanel.prototype.hasCache = function (query) {
    return this.cache[query] || this.cache[query.substr(0, 1)];
}

SuggestionPanel.prototype.cache = function (key, value) {
    this.cache[key] = value;
}

SuggestionPanel.prototype.showFromCache = function (query) {
    var _this = this;
    var queryHeader = query.substr(0, 1);

    if (_this.cache[query]) {
        _this.data = _this.cache[query];
        _this.show();
        if (query.length != 1 && _this.cache[queryHeader]) {
            _this.cache[queryHeader] = _this.cache[queryHeader].concat(_this.cache[query]);
            delete _this.cache[query];
        }
    } else if (_this.cache[queryHeader]) {
        var data = [];
        var reg = new RegExp("^" + query);
        for (var i = 0; i < _this.cache[queryHeader].length; i++) {
            if (reg.test(_this.cache[queryHeader][i])) {
                data.push(_this.cache[queryHeader][i])
            }
        }
        _this.data = data;
        _this.show();
    }
}
