<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="padding:10px">
<ol class="breadcrumb">
    <li><a href="#">用户管理</a></li>

</ol>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">用户列表</a></li>
        <li role="presentation"><a href="javascript:void(0)" aria-controls="profile" role="tab" data-toggle="modal" data-target="#addModal">新增用户</a></li>
        <li role="presentation"><a href="javascript:void(0)" aria-controls="profile" role="tab" data-toggle="modal" data-target="#addModal">修改用户</a></li>
    </ul>
    <!-- Tab panes
    <div class="tab-content">
      <div role="tabpanel" class="tab-pane active" id="home">...</div>
      <div role="tabpanel" class="tab-pane" id="profile">...</div>
      <div role="tabpanel" class="tab-pane" id="messages">...</div>
      <div role="tabpanel" class="tab-pane" id="settings">...</div>
    </div>
  -->
</div>
<table class="table table-hover">
    <tr>
        <th>ID</th>
        <th>用户名</th>
        <th>密码</th>
        <th>电子邮件</th>
        <th>操作</th>
    </tr>

    <c:forEach items="${page.values}" var="user">
        <tr id="tr${user.id}">
            <td>${user.username }</td>

                <div class="dropdown">
                    <button type="button" class="btn btn-info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        操作
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dLabel">
                        <li><a href="javascript:void(0)" onclick="showEditDialog('${user.id}')"><span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;修改</a></li>
                        <c:if test="${user.username != 'admin'}">
                            <li role="separator" class="divider"></li>
                            <li><a href="javascript:void(0)" onclick="showDelDialog('${user.id}')"><span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;删除</a></li>
                        </c:if>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">上传头像</a></li>
                    </ul>
                </div>
            </td>
        </tr>
    </c:forEach>

    <!-- <tr>
        <td>张三</td>
        <td>18</td>
        <td>男</td>
        <td>2001-12-22</td>
        <td>110110200103299887</td>
    </tr>
    <tr>
        <td>张三</td>
        <td>18</td>
        <td>男</td>
        <td>2001-12-22</td>
        <td>110110200103299887</td>
    </tr>
    <tr>
        <td>张三</td>
        <td>18</td>
        <td>男</td>
        <td>2001-12-22</td>
        <td>110110200103299887</td>
    </tr>
    <tr>
        <td>张三</td>
        <td>18</td>
        <td>男</td>
        <td>2001-12-22</td>
        <td>110110200103299887</td>
    </tr>
    <tr>
        <td>张三</td>
        <td>18</td>
        <td>男</td>
        <td>2001-12-22</td>
        <td>110110200103299887</td>
    </tr> -->
</table>
<nav aria-label="Page navigation" class="pull-right">
    <ul class="pagination">
        <li>
            <a href="/UserServlet?currentPage=${page.currentPage-1 < 1 ? 1 : page.currentPage - 1 }" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <c:forEach var="num" begin="1" end="${page.totalPage}">
            <li><a href="/UserServlet?currentPage=${num }">${num }</a></li>
        </c:forEach>

        <!--  <li><a href="/kdxfjysbbxpt/UserServlet?currentPage=2">2</a></li>
         <li><a href="/kdxfjysbbxpt/UserServlet?currentPage=3">3</a></li>
         <li><a href="/kdxfjysbbxpt/UserServlet?currentPage=4">4</a></li>
         <li><a href="/kdxfjysbbxpt/UserServlet?currentPage=5">5</a></li> -->
        <li>
            <a href="/UserServlet?currentPage=${page.currentPage+1 > page.totalPage ? page.totalPage :  page.currentPage+1}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>

<!-- Modal -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加用户</h4>
            </div>
            <div class="modal-body">
                <form action="/UserServlet" id="saveForm" method="post">
                    <div class="form-group">
                        <label for="addname">用户名</label>
                        <input type="text" id="addname" name="add_username" class="form-control" placeholder="用户名">
                    </div>
                    <div class="form-group">
                        <label for="addpassword">用户密码</label>
                        <input type="password" id="addpassword" name="add_password" class="form-control" placeholder="请输入用户密码">
                    </div>
                    <div class="form-group">
                        <label for="addpassword1">确认用户密码</label>
                        <input type="password" id="addpassword1" name="add_confirmPassword" class="form-control" placeholder="请确认输入用户密码">
                    </div>
                    <div class="form-group">
                        <label for="addage">请输入年龄</label>
                        <input type="text" id="addage" name="add_age" class="form-control" placeholder="请输入用户年龄">
                    </div>
                    <div class="form-group">
                        <label for="addBirthday">请输入出生年月</label>
                        <input type="date" id="addBirthday" name="add_birthday" class="form-control" placeholder="请输入用户出生年月">
                    </div>
                    <div class="form-group">
                        <label for="addSex">请输入性别</label>
                        <select id="addSex" name="add_sex" class="form-control">
                            <option value="0">女</option>
                            <option value="1">男</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="addCardNo">请输入用户的身份证号码</label>
                        <input type="text" id="addCardNo" name="add_cardNo" class="form-control" placeholder="请输入用户身份证">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="saveUser()">提交</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editModalLabel">修改用户</h4>
            </div>
            <div class="modal-body">
                <form action="/UserServlet" id="editForm" method="post">
                    <input type="hidden" value="put" name="_method">
                    <input type="hidden" name="id" id="editId">
                    <div class="form-group">
                        <label for="editname">用户名</label>
                        <input type="text" id="editname" disabled="disabled" class="form-control" placeholder="用户名">
                    </div>
                    <div class="form-group">
                        <label for="editpassword">用户密码</label>
                        <input type="password" id="editpassword" name="edit_password" class="form-control" placeholder="请输入用户密码">
                    </div>
                    <div class="form-group">
                        <label for="editpassword1">确认用户密码</label>
                        <input type="password" id="editpassword1" name="edit_confirmPassword" class="form-control" placeholder="请确认输入用户密码">
                    </div>
                    <div class="form-group">
                        <label for="editage">请输入年龄</label>
                        <input type="text" id="editage" name="edit_age" class="form-control" placeholder="请输入用户年龄">
                    </div>
                    <div class="form-group">
                        <label for="editBirthday">请输入出生年月</label>
                        <input type="date" id="editBirthday" name="edit_birthday" class="form-control" placeholder="请输入用户出生年月">
                    </div>
                    <div class="form-group">
                        <label for="editSex">请输入出生年月</label>
                        <select id="editSex" name="edit_sex" class="form-control">
                            <option value="0">女</option>
                            <option value="1">男</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="editCardNo">请输入用户的身份证号码</label>
                        <input type="text" id="editCardNo" name="edit_cardNo" class="form-control" placeholder="请输入用户身份证">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="editUser()">提交</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="delModalLabel" id="delModal">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="delModalLabel"><span class="glyphicon glyphicon-question-sign"></span>&nbsp;&nbsp;确认删除用户</h4>
            </div>
            <form action="/UserServlet" id="delForm" method="post">
                <input type="hidden" name="_method" value="delete">
                <input type="hidden" name="id" id="delId">
            </form>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="delUser()">确认</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script src="/js/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>

<script type="text/javascript">
    function saveUser(){
        var u = $("#addname").val();
        if (!u || u.length < 6){ // "" == false
            alert("对不起，您输入的用户名少于6位，请重新输入");
            $("#addname").focus();
            return;
        }

        var p = $("#addpassword").val();
        if (!p || p.length < 6){ // "" == false
            alert("对不起，您输入的用密码少于6位，请重新输入");
            $("#addpassword").focus();
            return;
        }

        var p1 = $("#addpassword1").val();
        if (p != p1){ // "" == false
            alert("对不起，两次输入的密码不一致，请重新输入");
            $("#addpassword").val("");
            $("#addpassword1").val("");
            $("#addpassword").focus();
            return;
        }

        //var age = $("#addage").val();
        var c = $("#addCardNo").val();
        if (!c || c.length < 18){ // "" == false
            alert("对不起，您输入的身份证格式不正确，请求重新输入");
            $("#addCardNo").focus();
            return;
        }

        // 页面请求  TODO  跳转页面
        $("#saveForm").submit(); // 手动提交
    }

    function editUser(){
        // 页面请求  TODO  跳转页面
        $("#editForm").submit(); // 手动提交
    }

    function showEditDialog(id){
        var tdArr = $("#tr"+id).find("td");
        var username = tdArr.get(0).innerHTML;
        var age = tdArr.get(1).innerHTML;
        var sex = tdArr.get(2).innerHTML;
        var birthday = tdArr.get(3).innerHTML;
        var cardNo = tdArr.get(4).innerHTML;

        $("#editname").val(trim(username));
        $("#editage").val(trim(age));
        $("#editBirthday").val(trim(birthday));

        $("#editSex").val(trim(sex) == "女" ? 0 : 1);
        $("#editCardNo").val(trim(cardNo));
        $("#editId").val(id);
        //$.ajax({});
        $('#editModal').modal('show')
    }

    function showDelDialog(id){
        $("#delId").val(id);
        $('#delModal').modal('show')
    }

    function delUser(){
        // 页面请求  TODO  跳转页面
        $("#delForm").submit(); // 手动提交
    }

    $('#editModal').on('show.bs.modal', function (e) {
        //alert("我在执行修改操作")
    });

    //去左空格;
    function ltrim(s){
        return s.replace(/(^\s*)/g, "");
    }
    //去右空格;
    function rtrim(s){
        return s.replace(/(\s*$)/g, "");
    }
    //去左右空格;
    function trim(s){
        return s.replace(/(^\s*)|(\s*$)/g, "");
    }

</script>

