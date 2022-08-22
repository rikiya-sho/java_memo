<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧 - メモアプリ</title>
<link href="<%= request.getContextPath() %>/css/base.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, user-scalable=yes">
</head>
<body>
	<div class="container">
	<h1>メモ一覧</h1>
	<p><%= request.getAttribute("message") %></p>
		<%
        ArrayList<HashMap<String,String>> rows = (ArrayList<HashMap<String,String>>)request.getAttribute("rows");
        %>

        <table>
            <tr>
                <th>ID</th>
                <th>タイトル</th>
            </tr>
            <%
            for (HashMap<String,String> columns : rows) {
            %>
                <tr>
                    <td><%= columns.get("id") %></td>
                    <td><a href='show?id=<%= columns.get("id") %>'><%= columns.get("title") %></a></td>
                </tr>
            <% } %>
        </table>
        
        <p class="btn"><a href="new">新規作成</a></p>
        </div>
</body>
</html>