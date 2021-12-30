<%@ page import="com.example.workshop.entity.Order" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Roma Kr
  Date: 29.12.2021
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Filter page</title>
</head>
<body>
<h2>Create filter of masters</h2>
<div>
    <form autocomplete="off" action="/workshop_war_exploded/manager/filter/master" method="post" class="form-horizontal" role="form">
        <div class="form-group">
            <div class="col-sm-9" align="center">

                <div class="form-group">
                    <div class="col-sm-9">
                        <input type="text" placeholder="Master" name="master"
                               class="form-control" minlength="1" maxlength="30"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-9">
                        <a href="/workshop_war_exploded/manager/orders/date" class="btn">All orders sorted by date</a>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
