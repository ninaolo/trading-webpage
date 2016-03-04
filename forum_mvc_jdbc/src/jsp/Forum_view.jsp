<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html><head><title>Forum (visa)</title></head><body>
<%@ page import="java.util.ArrayList" %>

<jsp:useBean class="bean.Forum" id="forum" scope="application"/>
<jsp:useBean class="bean.User" id="user" scope="session"/>


<h4><%= user.getNickname() %>(<%= user.getEmail() %>)</h4>

<% 
   ArrayList posts= forum.getPosts();
   pageContext.setAttribute("allPosts", posts);

%>

<c:forEach items="${allPosts}" var="current">
  <br><c:out value="${current.nickname}" />: 
  <c:out value="${current.text}" />
</c:forEach>


<form action="/forum_mvc_jdbc/ForumController">
Text<input type="text" name="text"><br>
<input type="submit"></form>
</body></html>
