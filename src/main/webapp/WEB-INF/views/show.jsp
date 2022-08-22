<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>詳細表示 - メモアプリ</title>
<link href="<%= request.getContextPath() %>/css/base.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, user-scalable=yes">
</head>
<body>
	<div class="container">
	<h1>メモ詳細</h1>
	<p><%= request.getAttribute("message") %></p>
	<h2>タイトル</h2>
	<p><%= request.getAttribute("title") %></p>
	<h2>本文</h2>
	<p><%= request.getAttribute("content") %></p>
	<br>
	<p><a href="list">一覧に戻る</a>　|　<a href='edit?id=<%= request.getAttribute("id") %>'>編集する</a>　|　<a href="destroy?id=<%= request.getAttribute("id") %>">削除する</a></p>
	</div>
</body>
</html>