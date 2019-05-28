layui.use(['form', 'layer', 'table', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    var cols = [
        [{
            type: 'checkbox',
            fixed: 'left'
        },
        {
            type: 'numbers',
            title: '序号',
            fixed: 'left'
        }, {
            field: 'permId',
            title: 'ID',
            sort: true,
            hide: true
        }, {
            field: 'permName',
            title: '权限名称',
        }, {
            field: 'permUrl',
            title: '地址',
        }, {
            field: 'permIcon',
            title: '图标',
            templet: function (d) {
                var html = "";
                if (d.permIcon) {
                    if (d.permIcon.indexOf("layui") != -1) {
                        html += '<i class="layui-icon ' + d.permIcon + '" ></i > ';
                    } else {
                        html += '<i class="iconfont ' + d.permIcon + '" ></i > ';
                    }
                }
                return html;
            },
        }, {
            field: 'permStatus',
            title: '状态',
            templet: function (d) {
                return d.userSex == '1' ? "启用" : "禁用";
            }
        }, {
            field: 'remark',
            title: '描述',
        }
        ]];

    var permTable = table.render({
        id: "permTable",
        elem: '#permTable',
        height: 450,
        // url: '/static/js/permList.json',
        url: '/perm/getPerm',
        page: true,
        // toolbar: '#userListTopBar',
        loading: true,
        text: {
            none: '暂无相关数据'
        },
        cols: cols,
        response: {
            statusName: 'resultCode' //规定数据状态的字段名称，默认：code
            ,
            statusCode: 0 //规定成功的状态码，默认：0
            ,
            msgName: 'resultMsg' //规定状态信息的字段名称，默认：msg
            ,
            countName: 'total' //规定数据总数的字段名称，默认：count
            ,
            dataName: 'data' //规定数据列表的字段名称，默认：data

        }
    });

    function edit(data) {
        var index = layer.open({
            title: data ? "编辑权限" : "新增权限",
            type: 2,
            content: "/perm/edit",
            success: function (layero, index) {
                // var form = layui.layer.getChildFrame('form', index);
                // var iframe = window[layero.find('iframe')[0]['name']];
                var iframe = window["layui-layer-iframe" + index];
                if (data) {
                    iframe.setValues(data);
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layer.full(index);
        })
    }

    var active = {
        add: function () {
            edit();
        },
        edit: function () {
            var checkStatus = table.checkStatus('permTable'), data = checkStatus.data;
            if (data.length == 0) {
                layer.msg("请选择需要编辑的数据")
            } else if (data.length > 1) {
                layer.msg("编辑时只能选择一条数据")
            } else {
                edit(data[0]);
            }

        },
        del: function () {
            var checkStatus = table.checkStatus('permTable'), data = checkStatus.data, permIds = [];
            if (data.length == 0) {
                layer.msg("请选择需要删除的数据")
            } else {
                for (var i in data) {
                    permIds.push(data[i].newsId);
                }
                layer.confirm('真的删除行么', function (index) {
                    $.get("/perm/delPerm", {
                        newsId: data.newsId
                    }, function (data) {
                        permTable.reload();
                        layer.close(index);
                    })

                });
            }

        }
    };


    $('#permTableBar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});