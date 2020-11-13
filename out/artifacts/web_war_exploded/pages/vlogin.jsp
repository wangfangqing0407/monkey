<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/css/layui.css" media="all">
    <script type="text/javascript" src="/js/jquery-2.1.4.js"></script>
</head>
<body >
    <form  method="post" action="plogin" >
        登录名：<input type="text" name="username"/>
        <br/>
        密码：<input type="text" name="userPwd"/>
        <br/>
        <input type="button" onclick="vlogin();" value="AJAX登录" />
        <td>验证码：</td>
        <td>
            <input type="text" class="easyui-validatebox" id="code" name="code" placeholder="验证码" maxlength="4" value="" style="width: 60px" data-options="required:true" tipPosition="top"/>
    <%--        <img src="getCode" title="看不清，点击刷新"--%>
    <%--             onclick="reloadValidCode()"--%>
    <%--             style="vertical-align: middle;" id="imgcode"/>--%>
    </td>

        <input type="submit" value="登录">
    </form>

</body>

<script type="text/javascript">
    function vlogin(){
        $.ajax({
//            contentType:"application/json",
            dataType:"text",
            url:"/login",
            type:"POST",
            data:{"account":"123","pwd":"123"},
            success:function (res) {
                location.href="pages/layUiTest.jsp";
            }
        });
    }
</script>
</html>
