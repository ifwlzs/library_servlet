<%@ page import="java.util.Arrays" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>管理员列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="../css/font.css">
    <link rel="stylesheet" href="../css/xadmin.css">
    <script src="../lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../js/xadmin.js"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <button class="layui-btn" onclick="xadmin.open('添加','/admin/toAddAdminServlet')"><i
                            class="layui-icon"></i>添加管理员
                    </button>
                </div>
                <form class="layui-form layui-col-space5 layui-card-header">
                    <div class="layui-inline layui-show-block">
                        <input type="text" name="name" placeholder="请输入姓名" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-inline layui-show-block">
                        <button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i>
                        </button>
                    </div>
                </form>
                <div class="layui-card-body layui-table-body layui-table-main">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th style="text-align: center">选择</th>
                            <th>用户ID</th>
                            <th>用户名</th>
                            <th>密码</th>
                            <th>性别</th>
                            <th>手机</th>
                            <th>邮箱</th>
                            <th style="text-align: center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${adminList}" var="user" varStatus="status">

                            <tr>
                                <td style="text-align: center">${status.index+1}</td>
                                <td style="text-align: center">
                                    <input type="checkbox" name="id" value="${user.id}" lay-skin="primary">
                                </td>
                                <td>${user.id}</td>
                                <td>${user.name}</td>
                                <td>${user.password}</td>
                                <td>${user.gender}</td>
                                <td>${user.telephone}</td>
                                <td>${user.email}</td>
                                <td class="td-manage" style="text-align: center">
                                    <a title="编辑"
                                       onclick="xadmin.open('编辑','/admin/toEditAdminServlet?id=${user.id}')"
                                       href="javascript:;">
                                        <i class="layui-icon">&#xe642;</i>
                                    </a>
                                    <a title="删除" onclick="member_del(this,'${user.id}')" href="javascript:;">
                                        <i class="layui-icon">&#xe640;</i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="layui-card-body ">
                    <div class="page">
                        <div>
                            <a class="prev" href="">&lt;&lt;</a>
                            <a class="num" href="">1</a>
                            <a class="next" href="">&gt;&gt;</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    layui.use(['laydate', 'form'], function () {
        var laydate = layui.laydate;
        var form = layui.form;


        // 监听全选
        form.on('checkbox(checkall)', function (data) {

            if (data.elem.checked) {
                $('tbody input').prop('checked', true);
            } else {
                $('tbody input').prop('checked', false);
            }
            form.render('checkbox');
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });


    });

    /*用户-删除*/
    function member_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            console.log(id)
            $(obj).parents("tr").remove();
            layer.msg('已删除!', {icon: 1, time: 1000});
            //发异步删除数据
            $.ajax({
                url: "/admin/DeleteAdminServlet",
                type: "POST",
                data: {"id": id},
                dataType: 'json',
                success: function (result) {
                },
            });

        });
    }


    function delAll(argument) {
        var ids = [];

        // 获取选中的id
        $('tbody input').each(function (index, el) {
            if ($(this).prop('checked')) {
                ids.push($(this).val())
            }
        });

        layer.confirm('确认要删除吗？' + ids.toString(), function (index) {
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
    }
</script>
</html>
