# library_servlet

java8 + servlet + jsp + MySQL8.0.17 + c3p0 + DBUtils + Xadmin 实现图书馆管理系统

# 用前须知

sql存放在 `src/main/resources/`目录下

tomcat部署的应用程序上下文为 `/`

![应用程序上下文](https://user-images.githubusercontent.com/49548316/146531688-791893ce-ea34-4b69-b072-55c339ef71c0.png)


# 一、要求

> 系统要实现如下的基本管理功能：
>
> （1）用户分为两类：管理员，一般用户。
>
> （2）提供用户注册和用户登录验证功能；其中一个登录用户的信息有：登录用户名，登录密码。
>
> （3）管理员可以实现对注册用户的管理（查询、删除、密码修改）、用户的借书申请的确定和还书操作，并实现对图书的创建、查询、修改和删除等有关的操作
>
> （4）一般用户，只能查询图书，并进行借书操作（提出借书申请），每个用户最多借阅8本，即当目前借书已经是8本，则不能再借书了，只有还书后，才可以再借阅。
>
> 不能用框架

# 设计全局拦截器

对所有人公开：1-4

reader，admin：5-7

仅reader: 8-9

仅admin: 10-28

| 序号 | 路径                          | 说明                                   | 所在页面1             | 所在页面2              | 所在页面3           | 可访问                     |
| :--: | ----------------------------- | -------------------------------------- | --------------------- | ---------------------- | ------------------- | -------------------------- |
|  1   | /toIndexServlet               | 自动登录，未登录跳转首页               | /index.jsp            |                        |                     | 所有人                     |
|  2   | /RegisterReaderServlet        | 跳转注册                               | /register.jsp         |                        |                     | 所有人                     |
|  3   | /LoginServlet                 | 登录                                   | /login.jsp            |                        |                     | 所有人                     |
|  4   | /toErrorServlet               | 跳转错误页                             | *                     |                        |                     | 所有人                     |
|  5   | /UpdateReaderServlet          | 用户信息修改实现,管理员编辑用户信息    | /reader/edit.jsp      | /admin/editReader.jsp  |                     | admin,reader               |
|  6   | /toEditReaderServlet          | 跳转用户修改页,管理员编辑用户          | /reader/edit          | /admin/readerList.jsp  |                     | admin,reader               |
|  7   | /LogoutServlet                | 退出登录                               | /reader/index.jsp     | /admin/index.jsp       |                     | admin,reader               |
|  8   | /reader/toBookshelfServlet    | 跳转用户显示书籍,用户查找某本书的详情  | /reader/index.jsp     | /reader/borrowList.jsp |                     | reader                     |
|  9   | /reader/toReaderBorrowServlet | 跳转用户借书记录                       | /reader/index.jsp     |                        |                     | reader                     |
|  10  | /admin/toAddReaderServlet     | 管理员添加用户                         | /admin/readerList.jsp |                        |                     | admin                      |
|  11  | /admin/toBorrowListServlet    | 管理员查询借阅信息                     | /admin/readerList.jsp | /admin/index.jsp       | /admin/bookList.jsp | admin                      |
|  12  | /admin/DeleteReaderServlet    | 删除用户                               | /admin/readerList.jsp |                        |                     | admin                      |
|  13  | /admin/toEditAdminServlet     | 跳转管理员编辑页面，管理员个人信息修改 | /admin/index.jsp      | /admin/adminList.jsp   |                     | admin                      |
|  14  | /admin/toAdminListServlet     | 去展示管理员列表                       | /admin/index.jsp      |                        |                     | admin                      |
|  15  | /admin/toReaderListServlet    | 去展示用户列表，展示借阅者信息         | /admin/index.jsp      | /admin/borrowList.jsp  |                     | admin                      |
|  16  | /admin/toBookListServlet      | 去展示书籍列表                         | /admin/index.jsp      | /admin/borrowList.jsp  |                     | admin                      |
|  17  | /admin/UpdateBookServlet      | 管理员编辑书籍信息                     | /admin/editBook.jsp   |                        |                     | admin                      |
|  18  | /admin/UpdateAdminServlet     | 管理员编辑管理员信息                   | /admin/editAdmin.jsp  |                        |                     | admin                      |
|  19  | /admin/toAddBorrowServlet     | 管理员去添加借阅信息                   | /admin/borrowList.jsp |                        |                     | admin                      |
|  20  | /admin/toReturnBookServlet    | 跳转还书申请                           | /admin/borrowList.jsp |                        |                     | admin                      |
|  21  | /admin/toAddBookServlet       | 管理员去添加书籍                       | /admin/bookList.jsp   |                        |                     | admin                      |
|  22  | /admin/toEditBookServlet      | 管理员去编辑书籍                       | /admin/bookList.jsp   |                        |                     | admin                      |
|  23  | /admin/DeleteBookServlet      | 删除书籍                               | /admin/bookList.jsp   |                        |                     | admin                      |
|  24  | /admin/toAddAdminServlet      | 跳转添加管理员页面                     | /admin/adminList.jsp  |                        |                     | admin                      |
|  25  | /admin/DeleteAdminServlet     | 删除管理员信息                         | /admin/adminList.jsp  |                        |                     | admin                      |
|  26  | /admin/AddBorrowServlet       | 管理员借书                             | /admin/addBorrow.jsp  |                        |                     | admin                      |
|  27  | /admin/AddBookServlet         | 管理员加书                             | /admin/addBook.jsp    |                        |                     | admin                      |
|  28  | /admin/RegisterAdminServlet   | 管理员注册管理员                       | /admin/addAdmin.jsp   |                        |                     | 三、 总体设计三、 总体设计 |

# 二、 总体设计

## 2.1 系统功能模块划分

系统大致模块如下：

![系统功能模块](https://gitee.com/ifwlzs/img/raw/master/img/%E5%9B%BE%E4%B9%A6%E9%A6%86-%E7%B3%BB%E7%BB%9F%E6%A8%A1%E5%9D%97.png)

错误页面跳转模块：当用户访问不存在的页面，会跳到本系统默认的404页面，当执行错误操作导致服务器500错误时，也会跳到本系统配套的500页面。

权限管理：利用拦截器实现，达到用户无法越权访问页面及发送请求的目的

登录验证模块：与数据库中所存在的用户进行匹配，如果不存在，则返回错误信息。登录成功后会跳到对应的首页

管理员模块：个人的信息修改；读者的增删改查；书籍的增删改查；管理员的增删改查以及书籍借阅和书籍归还操作。

读者模块：个人的信息修改，图书馆库存查询，个人借阅记录查询。

## 2.2 E-R模型

E-R模型如图所示:

![E-R模型](https://gitee.com/ifwlzs/img/raw/master/img/%E5%9B%BE%E4%B9%A6%E9%A6%86-ER.png)

## 2.3 数据库设计

数据库设计如图所示：

![数据库设计](https://gitee.com/ifwlzs/img/raw/master/img/image-20211102110102235.png)

# 三、详细设计

## 3.1 前台设计

### 3.1.1 登录界面

登录包括注册和登录部分。当用户未输入时，只能点击前往注册，点击登录会弹窗提示“未输入必填项以及未完成滑块认证”。

![登录界面](https://user-images.githubusercontent.com/49548316/144045050-042b7adc-ed70-4d48-a53c-a2ce72f17ea8.png)

点击前往注册。可以看到注册页面。再注册界面中，加入了空值、用户ID、邮箱、电话等关键值的判断。保证用户输入的字符，符合注册要求后在向后端发送注册请求。

![注册页面](https://gitee.com/ifwlzs/img/raw/master/img/image-20211104135401687.png)

登录后，系统会根据用户ID自动判别用户类别，如果是r开头的，为读者登录，如果是m开头的则为管理员。若登录出错会返回错误信息

![登录出错](https://gitee.com/ifwlzs/img/raw/master/img/image-20211104135401687.png)

### 3.1.2 读者模块

读者模块分为三小块。右边为导航栏，包括两个功能，分别是查询馆藏书籍和查询自己的借阅记录。右边为刷新，登出和编辑个人信息功能。

![读者模块首页](https://gitee.com/ifwlzs/img/raw/master/img/image-20211104141402326.png)

### 3.1.2.1 编辑个人信息

该页面做了密码验证功能，如果密码输入错误将无法修改。

![编辑个人信息](https://gitee.com/ifwlzs/img/raw/master/img/image-20211104144147271.png)

#### 3.1.2.2 查询馆藏书籍

该页面会显示书籍的基本信息。也可以根据书名进行模糊查询。但是进行特别处理。不满足借阅条件的将无法显示“去借阅”按钮。

![馆藏目录](https://gitee.com/ifwlzs/img/raw/master/img/image-20211104144147271.png)

如果当前用户借书量达到8本了，将会提示“每个用户最多可借阅8本书籍，请尽快前往图书馆归还！”

#### 3.1.2.3 查询个人借阅记录

查询中，可以根据全部记录和未归还记录作为条件进行查询。

![查询个人借阅记录](https://gitee.com/ifwlzs/img/raw/master/img/image-20211104144147271.png)

表格中也可以根据书籍ID查询书籍详情。	

![查询书籍详情](https://gitee.com/ifwlzs/img/raw/master/img/image-20211104144147271.png)

点击“归还”按钮会提示图书馆开放时间。

## 3.2 后台设计

管理员后台功能颇多。除了个人信息更改，还包括用户管理，书籍管理，借阅管理三大模块。

### 3.2.1 用户管理

用户管理分为管理员和读者模块。管理员包括增删改和模糊查询操作。

![管理员管理](https://gitee.com/ifwlzs/img/raw/master/img/image-20211104144147271.png)

![读者管理](https://gitee.com/ifwlzs/img/raw/master/img/image-20211104144147271.png)

读者管理包括增删改模糊查询外还包括查询未归还记录和查询全部记录，下面展示查询未归还记录

![查询未归还记录](https://gitee.com/ifwlzs/img/raw/master/img/image-20211104143513322.png)

### 3.2.2 书籍管理

书籍管理大致包括书籍的增删改，以及查询某本书的借阅记录和未归还记录

![书籍管理](https://gitee.com/ifwlzs/img/raw/master/img/image-20211104143513322.png)

编辑界面中，对库存总量进行了安全安全判断，使得库存永远大于等于借出数量。并且说定了借出数量，让还书操作在借阅管理中进行。

![编辑书籍](https://gitee.com/ifwlzs/img/raw/master/img/image-20211104143513322.png)

### 3.2.3 借阅记录管理

借阅记录中可以进行借阅者，书籍详情，未归还书籍的查询；书籍归还操作以及添加借阅信息。归还操作中，满足书籍库存大于等于借出量以及借出量不为0的条件下，即可归还成功。

![借阅记录管理](https://gitee.com/ifwlzs/img/raw/master/img/image-20211104143513322.png)

添加借阅信息时。如果选中的读者以达到最大借书量，提示借书不成功。

![借书失败](https://gitee.com/ifwlzs/img/raw/master/img/image-20211104143513322.png)

## 3.3 安全设计

安全方面中，主要对不同角色的访问进行限制。

未登录用户只可访问登录和注册页面，如果访问其他角色的页面将会重定向到登录首页。

管理员不可访问reader目录下的方法和页面。访问后会自动重定向到管理员的首页。

读者不可访问admin目录下的方法和页面。访问后会自动重定向到读者的首页。

当输入不存在的页面，以及当服务器发生错误时，页面会跳转对应的错误页面。

![404页面](https://gitee.com/ifwlzs/img/raw/master/img/image-20211104152031015.png)

![服务器错误](https://gitee.com/ifwlzs/img/raw/master/img/image-20211104152219348.png)

# 结论

 本次设计中，虽然完成了基本功能，但是对比成熟的系统以及个人的借书经历而言，还是缺了点人性化的设计，例如书籍管理中应该还有二级列表。该列表中同书名的每一本书都应该进行编号。书籍管理中应该加入书籍所在书架，层数等字段。借书操作中，应该是要借助外设进行书籍ID和用户ID的录入。外加时间原因，本项目中未加入分页和批量删除操作。

servlet中存在大量的跳转JSP页面方法。由于有些涉及到查询和特判的操作。未能抽成统一的抽象方法。

未用上SpringBoot+SSM+MVC+VUE方案，使得项目文件看起来相对杂乱

