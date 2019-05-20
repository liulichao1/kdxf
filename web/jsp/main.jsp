<%--
  Created by IntelliJ IDEA.
  User: chao
  Date: 2019/4/22
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <style>
        html,body{
            width:100%;
            height:100%;
        }
        body {
            background-color:rgb(54,65,80);
            color:#FFF;
        }
        .navbar-black{
            background-color: rgb(43,54,67);
        }

        .navbar-black .navbar-brand {
            color:white;
        }

        .navbar-nav .menu {
            color:white;
        }

        .nav-sidebar {
            /*background-color:rgb(54,65,80);*/
        }

        .nav-pills>li.active>a, .nav-pills>li.active>a:focus, .nav-pills>li.active>a:hover {
            background-color:rgb(28,175,154);
        }

        .nav-sidebar .fontColor{
            color:whitesmoke;
        }

        .nav-sidebar li {
            border-bottom: 1px solid rgb(43,54,67);
        }

        .nav-sidebar .sub-ul{
            width:80%;
            margin-left:10%;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-black" id="header">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" style="padding-top:5px;padding-bottom: 5px;" href="#">
                <img alt="Brand" src="/img/ifly-logo2.png">
            </a>
            <a class="navbar-brand" href="#">
                科大讯飞设备管理系统
            </a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${user == 'admin' }">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle menu" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                <img alt="" src="img/avatar2.jpg" width="20">
                                管理员 <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="#">基本信息</a></li>
                                <li><a href="#">我的博客</a></li>
                                <li><a href="#">修改密码</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">上传头像</a></li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="#" class="menu">欢迎您,${user}</a></li>
                    </c:otherwise>
                </c:choose>
                <li><a href="/QuitServlet" class="menu"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div>
</nav>
<div class="container-fluid" id="container">
    <div class="row" style="height:100%;">
        <div class="col-md-2 nav-sidebar" style="height:100%;">
            <ul class="nav nav-pills nav-stacked">
                <c:forEach items="${menuList }" var="menu" varStatus="status">
                    <li role="presentation"  <c:if test='${status.index == 0 }'> class="active" </c:if> >
                        <a href="#" class="fontColor" data-toggle="collapse" data-target="#menu${menu.id}" href="#menu${menu.id}" aria-expanded="false" aria-controls="menu${menu.id}"><span class="${menu.ico }"></span>&nbsp;&nbsp;${menu.name }</a>
                        <ul class="nav nav-pills nav-stacked collapse sub-ul" id="menu${menu.id}">
                            <c:forEach items="${menu.children}" var="subMenu">
                                <li role="presentation">
                                    <a href="/UserServlet" target="mainFrame" class="fontColor"><span class="${subMenu.ico }"></span>&nbsp;&nbsp;${subMenu.name }</a>
                                </li>
                            </c:forEach>
                            <!-- <li role="presentation"><a href="#" class="fontColor"><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;新增设备</a></li>
                            <li role="presentation"><a href="#" class="fontColor"><span class="glyphicon glyphicon-grain"></span>&nbsp;&nbsp;修改设备</a></li> -->
                        </ul>
                    </li>
                </c:forEach>

                <!-- <li role="presentation">
                    <a href="#" class="fontColor" data-toggle="collapse" data-target="#sys_func" href="#sys_func" aria-expanded="false" aria-controls="sys_func"><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;系统功能</a>
                    <ul class="nav nav-pills nav-stacked collapse sub-ul" id="sys_func">
                      <li role="presentation">
                            <a href="#" class="fontColor"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;我的工作台</a>
                        </li>
                        <li role="presentation"><a href="#" class="fontColor"><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;系统功能</a></li>
                        <li role="presentation"><a href="#" class="fontColor"><span class="glyphicon glyphicon-grain"></span>&nbsp;&nbsp;设备管理</a></li>
                  </ul>
                </li> -->
            </ul>
        </div>
        <div class="col-md-10" style="height:100%;">
            <iframe name="mainFrame" width="100%" height="100%" src="/UserServlet" border="0"></iframe>
        </div>
    </div>
</div>
</body>
</html>
<script src="/js/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script>
    $(".nav-stacked>li").click(function(event){
        $(".nav-stacked li").removeClass("active");
        $(this).addClass("active");
    });

    $(function(){
        var h = $(document).height();
        console.log(h);
        $("#container").height(h - $("#header").height() - 30);
    });

</script>