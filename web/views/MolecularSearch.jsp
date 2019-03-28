<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 2019/3/23
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<% String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort() + path + "/";%>

<html>
<head>
    <title>玩转分子</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/MolecularSearch.css">
</head>
<body>

<div id="entirety">
    <div id="blank1"></div>

    <div id="content">
        <div id="headTitle">
            <img src="${pageContext.request.contextPath}/resources/img/title.png" />
        </div>

        <div id="search">
            <input type="text" id="searchInput" placeholder="输入想要查询的分子式" />
            <button id="searchButton">搜索</button>
        </div>
    </div>

    <div id="blank2"></div>
</div>

<script src="${pageContext.request.contextPath}/resources/jquery/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#searchButton").click(function () {
            if ($("#searchInput").val() == ""){
                window.location.reload();
            }else{
                $.ajax({
                    url:'../molecular/search',
                    type:'post',
                    data:{"molecularName":$("#searchInput").val()},
                    success:function (data) {
                        if(data == "success")
                            <%--window.location.href = '<%basePath%>' + "molecular/";--%>
                    }
                })
            }
        })
    })
</script>

</body>
</html>
