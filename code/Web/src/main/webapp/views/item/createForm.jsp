<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<form:form commandName="item" method="POST" action="create">
    <div>
        <h1>Hello - Spring Application</h1>
        <p><label>Code</label><form:input path="itemCode" /></p>
        <p><label>Code</label><form:input path="itemName" /></p>
        <p><label>Code</label><form:input path="description" /></p>
        <p><label>Code</label><form:input path="itemLife"/></p>
        <p><label>Code</label><form:input path="returnable" /></p>
        <p><input type="submit" value="Clear"/> <input type="clear" value="Clear"/></p>
    </div>
</form:form>