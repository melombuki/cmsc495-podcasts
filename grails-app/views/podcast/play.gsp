<%--
  Created by IntelliJ IDEA.
  User: tom
  Date: 6/15/15
  Time: 11:47 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <meta name="description" content="">
    <meta name="author" content="Tom, Ben, Josh">

    <title><g:fieldValue bean="${entry}" field="title" /></title>

    <style>
        .image-rounded {
            width: 500px;
            height: 500px;
        }

        h1 {
            text-align: center;
        }
    </style>
</head>

<body>
    <h1>
        <g:fieldValue bean="${entry}" field="title" />
    </h1>
    <div style="text-align: center;">
        <img src="<g:fieldValue bean="${subscription.podcast}" field="image" />" alt="cover" class="image-rounded">
    </div>

    <g:if test="${entry.mimeType.contains('video')}">
        <video id="videoplayer" controls style="width: 50%; margin:1% 25%;">
            <source src="${entry.file}" type="${entry.mimeType ?: ''}"/>
        </video>
    </g:if>
    <g:else>
        <audio id="audioplayer" controls style="width: 50%; margin:1% 25%;">
            <source src="${entry.file}" type="${entry.mimeType ?: ''}"/>
        </audio>
    </g:else>


    <div style="margin:0 28%;">
        ${raw(entry.description)}
    </div>
</body>
</html>