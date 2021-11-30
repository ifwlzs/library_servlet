<%@ page import="java.util.Arrays" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>书籍列表</title>
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
                <form class="layui-form layui-col-space5 layui-card-header">
                    <div class="layui-inline layui-show-block ">
                        <input type="text" name="bookName" placeholder="请输入书名" autocomplete="off" class="layui-input">
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
                            <th style="text-align: center;">选择</th>
                            <th>书籍ID</th>
                            <th>书名</th>
                            <th>作者</th>
                            <th>出版社</th>
                            <th>出版日期</th>
                            <th>单价</th>
                            <th>剩余库存</th>
                            <th>分类</th>
                            <th>ISBN号</th>
                            <th style="text-align: center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${bookList}" var="book" varStatus="status">
                            <tr>
                                <td style="text-align: center">${status.index+1}</td>
                                <td style="text-align: center">
                                    <input type="checkbox" name="id" value="${book.bookID}" lay-skin="primary">
                                </td>
                                <td>${book.bookID}</td>
                                <td>${book.bookName}</td>
                                <td>${book.bookAuthor}</td>
                                <td>${book.bookPublisher}</td>
                                <td><fmt:formatDate value="${book.publishTime}" pattern="YYYY-MM-dd HH:mm:ss"/></td>
                                <td>￥${book.bookPrice}</td>
                                <td>${book.bookSum-book.bookLend}</td>
                                <td>${book.tag}</td>
                                <td>${book.isbn}</td>
                                <td class="td-manage" style="text-align: center">
                                    <a title="借阅"
                                       class="layui-btn ${(book.bookSum-book.bookLend)>0?"":"layui-btn-disabled"}" <c:if
                                            test="${(book.bookSum-book.bookLend)>0}">${allow?"onclick=allow(this)":"onclick=notAllow(this)"}</c:if>
                                       href="javascript:;">
                                        <i class="layui-icon"></i>去借阅
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
    layui.use([' laydate', 'form'], function () {
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

    /*用户-借书*/
    function allow(obj) {
        layer.confirm("图书馆开放时间为:<center style='color: red;font-size: 18px'>周一至周日 8:00 - 22:00</center>请前往图书馆借阅！", {
            title: '通知',
            btn: ['知道了']
        })
    }

    /*用户-不准借书*/
    function notAllow(obj) {
        layer.confirm("每个用户最多可借阅<span style='color: red;font-size: 18px'>8本</span>书籍<br>请尽快前往图书馆归还！", {
            title:
                '通知', btn: ['知道了']
        })
    }

</script>
</html>
