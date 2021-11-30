<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>借书登记</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="../css/font.css">
    <link rel="stylesheet" href="../css/xadmin.css">
    <script type="text/javascript" src="../lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="layui-fluid" style="width: 80%;">
    <div class="layui-row">
        <form class="layui-form">
            <div class="layui-form-item">
                <label for="readerID" class="layui-form-label">
                    <span class="x-red">*</span>借阅人</label>
                <div class="layui-input-inline" style="width: 85%">
                    <select id="readerID" name="readerID" class="valid">
                        <c:forEach items="${readerList}" var="reader">
                            <option value="${reader.id}">读者ID:${reader.id}&nbsp;,&nbsp;姓名:${reader.name}&nbsp;,&nbsp;性别:${reader.gender}&nbsp;,&nbsp;联系电话:${reader.telephone}&nbsp;,&nbsp;邮箱:${reader.email}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="bookID" class="layui-form-label">
                    <span class="x-red">*</span>借阅人</label>
                <div class="layui-input-inline" style="width: 85%">
                    <select id="bookID" name="bookID" class="valid">
                        <c:forEach items="${bookList}" var="book">
                            <option value="${book.bookID}">书籍ID:${book.bookID}&nbsp;,&nbsp;书名:${book.bookName}&nbsp;,&nbsp;作者:${book.bookAuthor}&nbsp;,&nbsp;出版社:${book.bookPublisher}&nbsp;,&nbsp;ISBN:${book.isbn}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_borrowTime" class="layui-form-label">
                    <span class="x-red">*</span>借阅时间</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_borrowTime" name="borrowTime" required="" lay-verify="required"
                           autocomplete="off" style="width: 272px"
                           class="layui-input"></div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red"></span></div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <button class="layui-btn" lay-filter="add" lay-submit="">借阅</button>
            </div>
        </form>
    </div>
</div>
<script>
    layui.use(['laydate', 'form', 'layer'],
        function mydate() {
            var laydate = layui.laydate;
            //执行一个laydate实例
            laydate.render({
                elem: '#L_borrowTime', //指定元素
                type: 'datetime',
                value: new Date()
            });
        })
    ;
</script>
<script>
    layui.use(['form', 'layer'],
        function () {
            $ = layui.jquery;
            var form = layui.form,
                layer = layui.layer;
            //自定义验证规则
            form.verify({});

            //监听提交
            form.on('submit(add)',
                function (data) {
                    console.log(data);
                    //发异步，把数据提交给后端
                    $.ajax({
                        url: '/admin/AddBorrowServlet',
                        method: 'post',
                        data: data.field,
                        dataType: 'json',
                        success: function (res) {
                            console.log(res)
                            if (res.state == 1) {
                                //弹出提示，并在1秒后进行跳转
                                layer.msg("借阅成功", function () {
                                    //关闭当前页面
                                    var index = parent.layer.getFrameIndex(window.name);
                                    parent.layer.close(index);
                                    //刷新父级页面
                                    window.parent.location.reload();
                                    return false;
                                });
                            } else {
                                layer.msg("借阅失败！" + res.msg);
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