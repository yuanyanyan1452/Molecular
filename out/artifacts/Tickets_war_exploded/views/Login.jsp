<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2018/3/9
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<% String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>

<html>
<head>
    <title>黑猫票务-认证中心</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Login.css">
</head>
<body>
<div id="webIcon">
    <a class="indexPage" href="#"><img src="${pageContext.request.contextPath}/resources/img/web-icon.png" /></a>
</div>
<hr />

<form id="loginForm" action="">
    <div class="header">
        <span class="headerLeft">会员登录</span>
        <a href="Register.jsp"><span class="headerRight">新用户注册</span></a>
    </div>

    <div class="content">
        <input type="text" class="userId" placeholder="识别码" /><br /><br /><br />
        <input type="password" class="password" placeholder="密码" />
    </div>

    <div class="footer">
        <a href="#" id="login">登录</a>
    </div>
    <script src="${pageContext.request.contextPath}/resources/jquery/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        $(function () {

            $(".indexPage").attr("href","<%=basePath%>"+"user/index");

            $("#login").click(function () {
                if ($(".userId").val() == "" || $(".password").val() == ""){
                    alert("信息填写不完整")
                }else{
                    $.ajax({
                        url:'../user/login',
                        type:'post',
                        data:{"userId":$(".userId").val(),"password":$(".password").val()},
                        success:function (data) {
                            if (data == "molecular"){
                                // alert(data);
                                window.location.href = '<%=basePath%>' + "tickets";
                            }else if(data == "venue"){
                                window.location.href = '<%=basePath%>' + "venue/home";
                            }else if(data == "user"){
                                window.location.href = '<%=basePath%>' + "user/index";
                            }else{
                                alert(data);
                            }
                        }
                    })
                }
             })
        })
    </script>
</form>

</body>
</html>
