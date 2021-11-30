<%@ page import="java.util.Arrays" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>借阅信息</title>
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
                    <button class="layui-btn" onclick="xadmin.open('添加借阅信息','/admin/toAddBorrowServlet')"><i
                            class="layui-icon"></i>添加借阅信息
                    </button>
                </div>
                <form class="layui-form layui-col-space5 layui-card-header">
                    <input type="radio" name="isNull" value="F" title="全部记录" checked>
                    <input type="radio" name="isNull" value="T" title="所有未归还记录">
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
                            <th style="text-align: center;">选择</th>
                            <th colspan="2">用户ID</th>
                            <th colspan="2">书籍ID</th>
                            <th>借阅时间</th>
                            <th>归还时间</th>
                            <th style="text-align: center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${borrowList}" var="borrow" varStatus="status">
                            <tr>
                                <td style="text-align: center">${status.index+1}</td>
                                <td style="text-align: center">
                                    <input type="checkbox" name="id" value="${status.index}" lay-skin="primary">
                                </td>
                                <td>${borrow.readerID}</td>
                                <td>
                                    <a title="借阅者信息"
                                       onclick="xadmin.open('借阅者信息','/admin/toReaderListServlet?id=${borrow.readerID}')"
                                       href="javascript:;" class="layui-btn layui-btn-xs layui-btn-normal ">
                                        <i class="layui-icon">&#xe60b;</i>借阅者信息
                                    </a>
                                </td>
                                <td>${borrow.bookID}</td>
                                <td>
                                    <a title="书籍详情"
                                       onclick="xadmin.open('书籍详情','/admin/toBookListServlet?bookID=${borrow.bookID}')"
                                       href="javascript:; " class="layui-btn layui-btn-xs layui-btn-normal">
                                        <i class="layui-icon">&#xe60b;</i>书籍详情
                                    </a>
                                </td>
                                <td><fmt:formatDate value="${borrow.borrowTime}" pattern="YYYY-MM-dd HH:mm:ss"/></td>
                                <td><fmt:formatDate value="${borrow.returnTime}" pattern="YYYY-MM-dd HH:mm:ss"/></td>
                                <td class="td-manage" style="text-align: center">
                                    <a title="归还"
                                       <c:if test="${borrow.returnTime==null}">onclick="member_del(this,'${borrow.readerID}','${borrow.bookID}','${borrow.borrowTime}')"</c:if>
                                       class="layui-btn layui-btn-xs layui-btn-danger ${borrow.returnTime!=null?"layui-btn-disabled":""}"
                                       href="javascript:;">
                                        <i class="layui-icon"></i>归还书籍
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

    });

    /*用户-删除*/
    function member_del(obj, readerID, bookID, borrowTime) {
        layer.confirm('确认要归还吗？', function (index) {
            //发异步删除数据

            $.ajax({
                url: "/admin/toReturnBookServlet",
                type: "POST",
                data: {
                    "readerID": readerID,
                    "bookID": bookID,
                    "borrowTime": borrowTime,
                },
                dataType: 'json',
                success: function (res) {
                    if (res.state == 1) {
                        layer.msg('已归还!', {icon: 1, time: 1000}, function () {
                            window.location.reload();
                        });
                        return false;
                    } else {
                        layer.msg(res.msg);
                        return false;
                    }
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
