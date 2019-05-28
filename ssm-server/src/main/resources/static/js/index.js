
layui.use(["element", "jquery"], function () {
  var element = layui.element,
    $ = layui.jquery;

  createNav();
  /*
	 * @todo tab触发事件：增加、删除、切换
	 */
  var tab = {
    add: function (title, url, id) {
      if (url) {
        var exist = $("li[lay-id=" + id + "]").length;
        if (!exist) {
          element.tabAdd('tabs', {
            title: title,
            content: '<iframe tab-id="' + id + '" frameborder="0" src="' + url + '" scrolling="yes" class="tabiframe"></iframe>',
            id: id
          });
        }
        element.tabChange('tabs', id);
        // CustomRightClick(id); //绑定右键菜单
        // FrameWH(); //计算框架高度

        // var h = $(window).height();
        // $("iframe").css("height", h + "px");
      }
    },

    delete: function (id) {
      element.tabDelete("tabs", id); //删除
      removeStorageMenu(id);

    },

    change: function (id) {
      //切换到指定Tab项
      element.tabChange('tabs', id);
    },

    deleteAll: function (ids) { //删除所有
      $.each(ids, function (i, item) {
        element.tabDelete("tabs", item);
      })
      sessionStorage.removeItem('menu');
    }

  };

  element.on('nav(menus)', function (menu) {
    var title = menu.html();
    var url = menu.attr("menu-url");
    var id = menu.attr("menu-id");
    tab.add(title, url, id);
  });

  element.on('tab(tabs)', function (tab) {
    console.log(tab);


  });


  function transData(a, idStr, pidStr, chindrenStr) {
    var r = [], hash = {}, id = idStr, pid = pidStr, children = chindrenStr, i = 0, j = 0, len = a.length;
    for (; i < len; i++) {
      hash[a[i][id]] = a[i];
    }
    for (; j < len; j++) {
      var aVal = a[j], hashVP = hash[aVal[pid]];
      if (hashVP) {
        !hashVP[children] && (hashVP[children] = []);
        hashVP[children].push(aVal);
      } else {
        r.push(aVal);
      }
    }
    return r;
  }


  function createNav() {
    $.ajax({
      // url: "../static/js/menu.json",
      url: "/perm/getPerm",
      method: "get",
      data: {
        userId: '1'
      },
      dataType: "json",
      success: function (result) {
        if (result.resultCode == 0) {
          var menus = transData(result.data, "permId", "parId", "children");
          var html = '<ul class="layui-nav layui-nav-tree" lay-filter="menus" id="menus">';
          $.each(menus, function (i, menu) {
            html += '<li class="layui-nav-item"><a href="javascript:;" menu-id="' + menu.permId + '"';
            if (menu.permUrl) {
              html += ' menu-url="' + menu.permUrl;
            }
            html += '">';
            if (menu.permIcon) {
              if (menu.permIcon.indexOf("layui") != -1) {
                html += '<i class="layui-icon ' + menu.permIcon + '" ></i > ';
              } else {
                html += '<i class="iconfont ' + menu.permIcon + '" ></i > ';
              }
            }
            html += menu.permName + '</a>';
            if (menu.children) {
              html += '<dl class="layui-nav-child">';
              $.each(menu.children, function (i, menuChild) {
                html += getChildMenu(menuChild, 1);
              });
              html += '</dl>'
            }
            html += '</li>\n';
          });
          html += '</ul>'
          $(".layui-side-scroll").html(html);
          element.init();
        }
      }
    });
  }




  // 递归生成子菜单
  function getChildMenu(menu, num) {
    var marginLeft = 10;
    var html = '';
    if (menu.children) {
      html += '<dd><ul>';
      html += '<li class="layui-nav-item" >';
      html += '<a style="margin-Left:' + num * marginLeft + 'px;"  href="javascript:;" menu-id="' + menu.permId + '"';
      if (menu.permUrl) {
        html += ' menu-url="' + menu.permUrl;
      }
      html += '">';
      if (menu.permIcon) {
        if (menu.permIcon.indexOf("layui") != -1) {
          html += '<i class="layui-icon ' + menu.permIcon + '" ></i > ';
        } else {
          html += '<i class="iconfont ' + menu.permIcon + '" ></i > ';
        }
      }
      html += menu.permName + '</a>'
      html += '<dl class="layui-nav-child">';
      $.each(menu.children, function (i, menuChild) {
        html += getChildMenu(menuChild, num + 1);
      });
      html += "</dl></li></ul></dd>";
    } else {
      html += '<dd>';
      html += '<a style="margin-Left:' + num * marginLeft + 'px"; href="javascript:" menu-id="' + menu.permId + '"';
      if (menu.permUrl) {
        html += ' menu-url="' + menu.permUrl;
      }
      html += '">';
      if (menu.permIcon) {
        if (menu.permIcon.indexOf("layui") != -1) {
          html += '<i class="layui-icon ' + menu.permIcon + '" ></i > ';
        } else {
          html += '<i class="iconfont ' + menu.permIcon + '" ></i > ';
        }
      }
      html += menu.permName + '</a></dd>';
    }
    return html;
  }

  // $(".left-open i").click(function(event) {
  //   if ($(".layui-side").css("width") == "0px") {
  //     $(".layui-side").animate(
  //       {
  //         width: "200px"
  //       },
  //       100
  //     );
  //   } else {
  //     $(".layui-side").animate(
  //       {
  //         width: "0px"
  //       },
  //       100
  //     );
  //   }
  // });
});
