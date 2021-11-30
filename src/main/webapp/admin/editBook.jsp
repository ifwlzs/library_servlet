<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>修改书籍</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="../css/font.css">
    <link rel="stylesheet" href="../css/xadmin.css">
    <script src="../lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/xadmin.js"></script>
    <!--
    让IE8 / 9
    支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]-->
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <!--[endif]-->
</head>

<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form">
            <div class="layui-form-item">
                <label for="L_id" class="layui-form-label">
                    <span class="x-red">*</span>书籍ID</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_id" name="bookID" required="" lay-verify="id"
                           autocomplete="off" class="layui-input" readonly value="${editBook.bookID}">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_bookName" class="layui-form-label">
                    <span class="x-red">*</span>书名</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_bookName" name="bookName" required="" lay-verify="required" autocomplete="off"
                           class="layui-input" value="${editBook.bookName}"></div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red"></span></div>
            </div>
            <div class="layui-form-item">
                <label for="L_bookAuthor" class="layui-form-label">
                    <span class="x-red">*</span>作者</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_bookAuthor" name="bookAuthor" required="" lay-verify="required" autocomplete="off"
                           class="layui-input" value="${editBook.bookAuthor}"></div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red"></span></div>
            </div>
            <div class="layui-form-item">
                <label for="L_bookPublisher" class="layui-form-label">
                    <span class="x-red">*</span>出版社</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_bookPublisher" name="bookPublisher" required="" lay-verify="required"
                           autocomplete="off"
                           class="layui-input" value="${editBook.bookPublisher}"></div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red"></span></div>
            </div>
            <div class="layui-form-item">
                <label for="L_publishTime" class="layui-form-label">
                    <span class="x-red">*</span>出版日期</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_publishTime" name="publishTime" required="" lay-verify="required"
                           autocomplete="off"
                           class="layui-input"></div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red"></span></div>
            </div>
            <div class="layui-form-item">
                <label for="L_bookPrice" class="layui-form-label">
                    <span class="x-red">*</span>单价</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_bookPrice" name="bookPrice" required="" lay-verify="bookPrice"
                           autocomplete="off"
                           class="layui-input" value="${editBook.bookPrice}"></div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red"></span></div>
            </div>
            <div class="layui-form-item">
                <label for="L_bookSum" class="layui-form-label">
                    <span class="x-red">*</span>库存总量</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_bookSum" name="bookSum" required="" lay-verify="bookSum" autocomplete="off"
                           class="layui-input" value="${editBook.bookSum}"></div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red">*库存总量不能小于借出数量</span></div>
            </div>
            <div class="layui-form-item">
                <label for="L_bookLend" class="layui-form-label">
                    <span class="x-red">*</span>借出数量</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_bookLend" name="bookLend" required="" lay-verify="bookLend"
                           autocomplete="off"
                           class="layui-input" value="${editBook.bookLend}" readonly></div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red">*不可修改</span></div>
            </div>
            <div class="layui-form-item">
                <label for="L_tag" class="layui-form-label">
                    <span class="x-red">*</span>分类</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_tag" name="tag" required="" lay-verify="required" autocomplete="off"
                           class="layui-input" value="${editBook.tag}"></div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red"></span></div>
            </div>
            <div class="layui-form-item">
                <label for="L_isbn" class="layui-form-label">
                    <span class="x-red">*</span>ISBN号</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_isbn" name="isbn" required="" lay-verify="isbn" autocomplete="off"
                           class="layui-input" value="${editBook.isbn}"></div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red"></span></div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <button class="layui-btn" lay-filter="add" lay-submit="">修改</button>
            </div>
        </form>

    </div>
</div>
<script>layui.use(['laydate', 'form', 'layer'],
    function mydate() {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#L_publishTime', //指定元素
            type: 'datetime',
            value: new Date(${editBook.publishTime.time})
        });
    })
;</script>
<script>
    layui.use(['laydate', 'form', 'layer'],
        function () {
            $ = layui.jquery;
            var form = layui.form,
                layer = layui.layer;
            //自定义验证规则
            form.verify({
                bookSum: function (value) {
                    //可排除输入字母的情况，并且保证数据正确
                    if (!($('#L_bookSum').val() >= ${editBook.bookLend})) {
                        return '库存总量不能小于借出数量';
                    }
                },
                //bookSum: [/([0-9])*$/, "请输入数字"],
                bookPrice: function (value) {
                    //可排除输入字母的情况，并且保证数据正确
                    if (!($('#L_bookPrice').val() >= 0)) {
                        return '单价不可小于0';
                    }
                }

            });


            //监听提交
            form.on('submit(add)',
                function (data) {
                    console.log(data);
                    //发异步，把数据提交给后端
                    $.ajax({
                        url: '/admin/UpdateBookServlet',
                        method: 'post',
                        data: data.field,
                        dataType: 'json',
                        success: function (res) {
                            console.log(res)
                            if (res.state == 1) {
                                //弹出提示，并在1秒后关闭当前页面
                                layer.msg("修改成功", {icon: 1, time: 1000}, function () {
                                    //关闭当前页面
                                    var index = parent.layer.getFrameIndex(window.name);
                                    parent.layer.close(index);
                                    window.parent.location.reload();
                                    return false;
                                });
                            } else {
                                layer.msg("修改失败:" + res.msg);
                                return false;
                            }
                        },
                        //请求的页面响应失败，则进行处理：
                        error: function (data) {
                            layer.msg(JSON.stringify(data.field), function () {
                                location.reload();
                            });
                            return false;
                        }
                    })
                    return false;
                });

        });

</script>


</body>

</html>