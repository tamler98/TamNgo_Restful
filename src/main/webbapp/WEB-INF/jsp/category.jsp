<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
<html>
<jsp:include page="header.jsp"/>
<body>
    <div class="container" style="margin-top: 60px;">
        <div class="col-md-4">
            <form:form action="newCategory" method="POST" modelAttribute="category">
                <fieldset class="scheduler-border">
                    <legend class="scheduler-border">
                        <c:out value="${msg}" />
                    </legend>
                    <div class="form-group">
                        <label class="control-label">Description (*)</label>
                        <form:input path="description" type="text" class="form-control" placeholder="Description" required="true" />
                    </div>
                    <div class="form-group">
                        <label class="control-label">Name (*)</label>
                        <form:input path="name" type="text" class="form-control" placeholder="Name Of Category"
                            required="true" />
                    </div>
                    <br>
                    <button class="btn- btn-primary" type="submit">Save</button>
                </fieldset>
            </form:form>
        </div>
    </div>
</body>

</html>