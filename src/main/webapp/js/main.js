document.addEventListener('WebComponentsReady', function () {
  var statuses = ['RECEIVED', 'PROCESSING', 'PACKAGING', 'SHIPPED', 'DELIVERED'];

  function PagedDataSource(options) {
    var baseUrl = options.baseUrl;
    var pageLength = options.pageLength || 20;
    var cache = {};
    var totalElements;

    function fetchPage(page, sortOrder) {
      return new Promise(function (resolve, reject) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
          if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
              var json = JSON.parse(xhr.responseText);
              totalElements = json.total;
              resolve({
                page: page,
                content: json.results
              });
            } else {
              reject({
                errorCode: xhr.status
              });
            }
          }
        };

        var queryParams = {
          page: page,
          size: pageLength
        };

        // Add sort if needed
        if (sortOrder && sortOrder[0]) {
          queryParams.sort = grid.columns[sortOrder[0].column].name;
          queryParams.order = sortOrder[0].direction;
        }

        xhr.open('GET', baseUrl + getQueryString(queryParams), true);
        xhr.send();
      });
    }

    /**
     * Serializes an object to a query string.
     *
     * {foo: bar, bar: baz} => '?foo=bar&bar=baz'
     */
    function getQueryString(params) {
      return '?' + Object.keys(params).map(function (key) {
          return encodeURIComponent(key) + '=' + encodeURIComponent(params[key]);
        }).join('&');
    }

    function clearCache() {
      cache = {};
      totalElements = 0;
    }

    function getFromCache(page) {
      return cache[page.toString()];
    }

    function inCache(page) {
      return page.toString() in cache;
    }

    function removeFromCache(page) {
      delete cache[page.toString()];
    }

    function addToCache(page, contents) {
      cache[page.toString()] = contents;
    }

    function getPageNum(index) {
      return Math.floor(index / pageLength);
    }

    function translateIndex(page, index) {
      return index - page * pageLength;
    }

    function cleanCache(firstPage, lastPage) {
      for (var page in cache) {
        if (cache.hasOwnProperty(page)) {
          var pageNum = parseInt(page);
          if (pageNum < firstPage || pageNum > lastPage) {
            removeFromCache(page);
          }
        }
      }
    }

    /**
     * Will get called when all needed pages are cached.
     *
     * Assembles final result and returns it to the caller.
     */
    function resolveRequest(params, callback) {
      var firstIndex = params.index;
      var lastIndex = firstIndex + params.count;
      var firstPage = getPageNum(firstIndex);
      var lastPage = getPageNum(lastIndex);

      var result = [];
      if (firstPage === lastPage) {
        result = getFromCache(firstPage).slice(translateIndex(firstPage, firstIndex), translateIndex(firstPage, lastIndex));
      } else {
        for (var page = firstPage; page <= lastPage; page++) {
          if (page === firstPage) {
            result = result.concat(getFromCache(page).slice(translateIndex(page, firstIndex)));
          } else if (page === lastPage) {
            result = result.concat(getFromCache(page).slice(0, translateIndex(page, lastIndex)));
          } else {
            result = result.concat(getFromCache(page));
          }
        }
      }
      callback(result, totalElements);
    }

    return function (params, callback) {
      var firstIndex = params.index;
      var lastIndex = firstIndex + params.count;
      var firstPage = getPageNum(firstIndex);
      var lastPage = getPageNum(lastIndex);
      var sortOrder = params.sortOrder;

      // Reset cache if we change any sort options
      if (sortChanged) {
        clearCache();
        sortChanged = false;
      }

      var neededPages = [];
      for (var page = firstPage; page <= lastPage; page++) {
        if (!inCache(page)) {
          neededPages.push(page);
        }
      }

      // Check if we need to fetch any new pages
      if (neededPages.length > 0) {
        Promise.all(neededPages.map(function (page) {
          return fetchPage(page, sortOrder);
        })).then(function (results) {
          results.forEach(function (result) {
            addToCache(result.page, result.content);
          });
          resolveRequest(params, callback);
        }).catch(function (error) {
          console.log(error);
        });
      } else {
        resolveRequest(params, callback);
      }
      cleanCache(firstPage, lastPage);
    };
  }

  var grid = document.querySelector('vaadin-grid');
  grid.frozenColumns = 2;
  grid.items = new PagedDataSource({
    baseUrl: '/delegate/services/customers',
    pageLength: 50
  });
  var sortChanged = false;
  grid.addEventListener('sort-order-changed', function () {
    sortChanged = true;
    grid.scrollToStart();
  });


  grid.columns[2].renderer = function (cell) {
    cell.element.innerHTML = '';
    var progressBar = document.createElement('progress');
    progressBar.setAttribute('value', ((statuses.indexOf(cell.data) + 1) / statuses.length).toString());
    cell.element.appendChild(progressBar);
  };


});