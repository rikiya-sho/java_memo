<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧 - メモアプリ</title>
</head>
<body>
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
        
        <p><a href="new">新規作成</a></p>
</body>
</html>