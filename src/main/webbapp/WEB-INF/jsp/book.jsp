<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
<html>
<jsp:include page="header.jsp"/>
<body>
    <div class="container" style="margin-top: 60px;">
        <div class="col-md-4">
            <form:form action="${action}" method="post" modelAttribute="book">
                <field-set class="scheduler-border">
                    <legend class="scheduler-border">
                        <c:out value="${msg}" />
                    </legend>
                    <c:if test="${type.equals('update')}">
                        <div class="form-group">
                            <label class="control-label">ID</label>
                            <form:input path="id" type="text" class="form-control" id="id" placeholder="ID"
                                disabled="true" />
                            <form:hidden path="id" />
                            <form:hidden path="bookDetails.id" />
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label class="control-label">Name (*)</label>
                        <form:input path="name" type="text" class="form-control" placeholder="Name" required="true" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">Author (*)</label>
                        <form:input path="author" type="text" class="form-control" placeholder="Author"
                            required="true" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">ISBN (*)</label>
                        <form:input path="bookDetails.isbn" type="text" class="form-control" placeholder="ISBN"
                            required="true" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">Price (*)</label>
                        <form:input path="bookDetails.price" type="number" step="any" min="1" class="form-control"
                            placeholder="Price" required="true" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">Number Of Pages (*)</label>
                        <form:input path="bookDetails.numberOfPage" type="number" min="1" class="form-control"
                            placeholder="Number Of Page" required="true" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">Public Date (*)</label>
                        <form:input path="bookDetails.publishDate" type="date" class="form-control"
                            placeholder="Public Date" required="true" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">Category (*)</label>
                        <form:select path="category.id" class="form-control">
                            <form:option value="0" label="--- Select ---" />
                            <form:options items="${categoryList}" />
                        </form:select>
                    </div>
                    <br>
                    <button class="btn- btn-primary" type="submit">Save</button>
                </field-set>
            </form:form>
        </div>
    </div>
</body>

</html>