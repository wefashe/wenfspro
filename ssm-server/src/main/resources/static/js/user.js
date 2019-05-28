// 用户列表

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
        },
        {
            field: 'userId',
            title: 'ID',
            sort: true,
            hide: true
        }, {
            field: 'userName',
            title: '用户名',
            unresize: true,
            width: 145,
            fixed: "left"
        }, {
            field: 'nickName',
            title: '昵称',
        },
        {
            field: 'userStatus',
            title: '状态',
            unresize: true,
            width: 110,
            align: 'center',
            templet: '#statusEnabled'
        },
        {
            field: 'userSex',
            title: '性别',
            width: 60,
            unresize: true,
            templet: function (d) {
                return d.userSex == '1' ? "男" : "女";
            }
        },
        {
            field: 'headPortrait',
            title: '头像',
            width: 60,
            unresize: true,
            templet: function (d) {
                var imgStr = "<img src='";
                var headPortrait = "../../../static/img/face.jpg";
                if (d.headPortrait) {
                    headPortrait = d.headPortrait;
                }
                imgStr = imgStr + headPortrait +
                    "' width='30' height='30' style='border-radius: 50%;box-sizing: border-box;'/>"
                return imgStr;
            }
        }, {
            field: 'userPhone',
            title: '手机',
        }, {
            field: 'userEmail',
            title: '邮箱',
        }, {
            field: 'userBirthday',
            title: '生日',
        }, {
            field: 'remark',
            title: '备注',
        }, {
            title: '操作',
            toolbar: '#userBar',
            fixed: "right"
        }
        ]
    ];



    //第一个实例
    var userList = table.render({
        elem: '#userList',
        height: 450,
        url: 'getList.json',
        page: true,
        toolbar: '#userListTopBar',
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

    //是否置顶
    form.on('checkbox(status)', function (data) {
        var index = layer.msg('修改中，请稍候', { icon: 16, time: false, shade: 0.8 });
        setTimeout(function () {
            layer.close(index);
            if (data.elem.checked) {
                layer.msg("启用成功！");
            } else {
                layer.msg("取消启用成功！");
            }
        }, 500);
    })

    //头工具栏事件
    table.on('toolbar(userList)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'add':
                var data = checkStatus.data;
                add();
                // layer.alert(JSON.stringify(data));
                break;
            case 'del':
                var data = checkStatus.data, userIds = [];
                // layer.msg('选中了：' + data.length + ' 个');

                if (data.length > 0) {
                    for (var i in data) {
                        userIds.push(data[i].userId);
                    }
                    layer.confirm('确定删除选中的用户？', { icon: 3, title: '提示信息' }, function (index) {
                        // $.get("删除文章接口",{
                        //     newsId : newsId  //将需要删除的newsId作为参数传入
                        // },function(data){
                        userList.reload();
                        layer.close(index);
                        // })
                    })
                } else {
                    layer.msg("请选择需要删除的用户");
                }

                break;
        }
        ;
    });

    //监听行工具事件
    table.on('tool(userList)', function (obj) {
        var data = obj.data;
        //console.log(obj)
        if (obj.event === 'del') {
            layer.confirm('确定删除此用户？', { icon: 3, title: '提示信息' }, function (index) {
                obj.del();
                // $.get("删除文章接口",{
                //     newsId : data.newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                userList.reload();
                layer.close(index);
                // })
            });
        } else if (obj.event === 'add') {
            add();
        }
    });


    //添加文章
    function add(edit) {
        var index = layui.layer.open({
            title: "",
            type: 2,
            content: "edit.html",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    body.find(".newsName").val(edit.newsName);
                    body.find(".abstract").val(edit.abstract);
                    body.find(".thumbImg").attr("src", edit.newsImg);
                    body.find("#news_content").val(edit.content);
                    body.find(".newsStatus select").val(edit.newsStatus);
                    body.find(".openness input[name='openness'][title='" + edit.newsLook + "']").prop("checked", "checked");
                    body.find(".newsTop input[name='newsTop']").prop("checked", edit.newsTop);
                    form.render();
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(index);
        })
    }

});
