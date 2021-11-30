<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html class="x-admin-sm">

<head>
    <meta charset="UTF-8">
    <title>读者注册</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="layui-fluid" style="width: 80%;">
    <div class="layui-row">
        <form class="layui-form" style="margin: 0 auto">
            <div class="layui-form-item">
                <label for="L_id" class="layui-form-label">
                    <span class="x-red">*</span>用户ID</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_id" name="id" required="" lay-verify="id" autocomplete="off"
                           class="layui-input"></div>
                <div class="layui-form-mid layui-word-aux">
                    <span class="x-red">*</span>将会成为您唯一的登入名，必须是r开头+数字(r001)
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_name" class="layui-form-label">
                    <span class="x-red">*</span>姓名</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_name" name="name" required="" lay-verify="name"
                           autocomplete="off" class="layui-input"></div>
            </div>
            <div class="layui-form-item">
                <label for="L_pass" class="layui-form-label">
                    <span class="x-red">*</span>密码</label>
                <div class="layui-input-inline">
                    <input type="password" id="L_pass" name="pass" required="" lay-verify="pass" autocomplete="off"
                           class="layui-input"></div>
                <div class="layui-form-mid layui-word-aux">6到16个字符</div>
            </div>
            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label">
                    <span class="x-red">*</span>确认密码</label>
                <div class="layui-input-inline">
                    <input type="password" id="L_repass" name="repass" required="" lay-verify="repass"
                           autocomplete="off" class="layui-input"></div>
            </div>

            <div class="layui-form-item">
                <label for="gender" class="layui-form-label">
                    <span class="x-red">*</span>选择性别</label>
                <div class="layui-input-inline">
                    <select id="gender" name="gender" class="valid">
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_telephone" class="layui-form-label">
                    <span class="x-red">*</span>联系电话</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_telephone" name="telephone" required="" lay-verify="telephone"
                           autocomplete="off" class="layui-input"></div>
            </div>
            <div class="layui-form-item">
                <label for="L_email" class="layui-form-label">
                    <span class="x-red">*</span>邮箱</label>
                <div class="layui-input-inline">
                    <input type="text" id="L_email" name="email" required="" lay-verify="email"
                           autocomplete="off" class="layui-input"></div>
            </div>
            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label"></label>
                <button class="layui-btn" lay-filter="add" lay-submit="">注册</button>
                <a class="layui-btn" lay-filter="" href="/">前往登录</a>
            </div>
        </form>
    </div>
</div>
<script>
    layui.use(['form', 'layer'],
        function () {
            $ = layui.jquery;
            var form = layui.form,
                layer = layui.layer;

            //自定义验证规则
            form.verify({
                id: [/r(\d+)$/, 'id必须为r开头'],
                pass: [/(.+){6,12}$/, '密码必须6到12位'],
                repass: function (value) {
                    if ($('#L_pass').val() != $('#L_repass').val()) {
                        return '两次密码不一致';
                    }
                },
                telephone: [/(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/, "请输入正确的联系电话"]
            });

            //监听提交
            form.on('submit(add)',
                function (data) {
                    console.log(data);
                    //发异步，把数据提交给后端
                    $.ajax({
                        url: '/RegisterReaderServlet',
                        method: 'post',
                        data: data.field,
                        dataType: 'json',
                        success: function (res) {
                            console.log(res)
                            if (res.state == 1) {
                                //弹出提示，并在1秒后关闭当前页面
                                layer.msg("注册成功", function () {
                                    //关闭当前页面
                                    var index = parent.layer.getFrameIndex(window.name);
                                    parent.layer.close(index);
                                    //刷新父级页面
                                    window.parent.location.reload();
                                    return false;
                                });
                            } else {
                                layer.msg("注册失败！" + res.msg);
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