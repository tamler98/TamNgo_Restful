<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
      rel="stylesheet">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<jsp:include page="header.jsp" />
<body>
<div class="row">
    <div class="col-xs-12 col-sm-8 col-md-6">
        <form:form action="search" method="get">
            <input name="searchInput" type="text" class="form-control" placeholder="Search by name or author..."/>
                <button type="submit" >Search</button>
        </form:form>
    </div>
</div>
<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Name</th>
      <th scope="col">Author</th>
      <th scope="col">ISBN</th>
      <th scope="col">Price</th>
      <th scope="col">Publish Date</th>
      <th scope="col">Category</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="book" items="${bookList}" varStatus="index">
    <tr>
    <th scope="row">${book.id}</th>
      <td>${book.name}</td>
      <td>${book.author}</td>
      <td>${book.bookDetails.isbn}</td>
      <td>${book.bookDetails.price}</td>
      <td>${book.bookDetails.publishDate}</td>
      <td>${book.category.name}</td>
    </tr>
    </c:forEach>
  </tbody>
</table>
</body>
</html>