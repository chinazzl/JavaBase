<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>This is Demo</h2>
<c:forEach items="${ints}" var="inter">
    <c:out value="${inter}"/></br>
</c:forEach>