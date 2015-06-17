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
    <title>Settings</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <style>
    body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #eee;
    }

    .update-account {
        max-width: 330px;
        padding: 15px;
        margin: 0 auto;
    }
    .update-account .update-account-heading,
    .update-account .checkbox {
        margin-bottom: 10px;
    }
    .update-account .checkbox {
        font-weight: normal;
    }
    .update-account .form-control {
        position: relative;
        height: auto;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        padding: 10px;
        font-size: 16px;
    }
    .update-account .form-control:focus {
        z-index: 2;
    }

    .update-account div {
        margin:0;
    }

    .update-account div input[type="email"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    .update-account div input[type="password1"] {
        margin-bottom: -1px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    .update-account div input[type="password2"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
    </style>

</head>


<body>
    <h2><br>Need to change you email or passowrd?  You can do that here.</h2>

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

    <g:form action="updateEmail" method="post" class="update-account">
        <h2 class="update-account-heading">Update your email here</h2>

        <div class="form-group ${hasErrors(bean:user,field:'email','has-error')}">
            <label for="email" class="sr-only">Email address</label>
            <input type="email" name="email" id="email" class="form-control" placeholder="Email address" value="${user?.email}" required autofocus>
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>
    </g:form>


    <g:form action="updatePassword" method="post" class="update-account">
        <h2 class="update-account-heading">Update your password here</h2>
        <div class="form-group ${hasErrors(bean:user,field:'password','has-error')}">
            <label for="password" class="sr-only">Password</label>
            <input type="password" name="password" id="password" class="form-control" placeholder="Password" required>
        </div>

        <div class="form-group ${hasErrors(bean:user,field:'password','has-error')}">
            <label for="password2" class="sr-only">Retype Password</label>
            <input type="password" name="password2" id="password2" class="form-control" placeholder="Retype Password" required>
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>
    </g:form>



</div>


</body>
</html>