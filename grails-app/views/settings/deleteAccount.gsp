<%--
  Created by IntelliJ IDEA.
  User: Vic
  Date: 6/17/2015
  Time: 2:35 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Delete Account</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <style>
    body {
        background-color: #eee;
    }

    .delete-account {
        max-width: 330px;
        padding: 15px;
        margin: 0 auto;
    }
    .delete-account .delete-account-heading,
    .delete-account .checkbox {
        margin-bottom: 10px;
    }
    .delete-account .checkbox {
        font-weight: normal;
    }
    .delete-account .form-control {
        position: relative;
        height: auto;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        padding: 10px;
        font-size: 16px;
    }
    .delete-account .form-control:focus {
        z-index: 2;
    }

    .delete-account div {
        margin:0;
    }
    </style>

</head>


<body>

<div class="container">
    <g:hasErrors bean="${user}">
        <div class="alert alert-danger" role="alert">
            <ul>
                <g:eachError var="err" bean="${user}">
                    <li><g:message error="${err}" />
                </g:eachError>
            </ul>
        </div>
    </g:hasErrors>

    <g:if test="${flash.message}">
        <div class="alert alert-success">${flash.message}</div>
    </g:if>

    <g:form action="deleteUser" method="post" class="delete-account">
        <h2>Are you sure you want to delete this account?</h2>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Delete</button>
    </g:form>



</div>


</body>
</html>