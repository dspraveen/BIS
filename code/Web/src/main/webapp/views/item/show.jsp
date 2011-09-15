<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div>
    <h1>Hello - Spring Application</h1>
    <p>${item.itemCode}</p>
    <p>${item.description}</p>
    <p>${item.itemName}</p>
    <p>${item.itemLife}</p>
    <p>${item.returnable}</p>
    <p><a href="item/updateForm/${item.itemCode}">update</a>
</div>