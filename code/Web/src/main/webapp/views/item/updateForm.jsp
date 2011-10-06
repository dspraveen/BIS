<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<form:form commandName="itemForm" method="POST" action="/item/update">
    <form:hidden path="item.itemCode"/>
    <div>
        <div class="section">
            <span class="left"><label>Item Name:</label></span
            <span class="right"><form:input path="item.itemName" readonly="true" />*</span>
        </div>
        <div class="section">
            <span class="left"><label>Item Description:</label></span
            <span class="right"><form:input path="item.description" /></span>
        </div>
        <div class="section">
            <span class="left"><label>Item Price:</label></span
            <span class="right"><form:input path="itemPrice" /></span>
        </div>
        <div class="section">
            <span class="left"><label>Item Type:</label></span
            <span class="right"><form:select path="item.itemLife" items="${itemTypes}" />*</span>
        </div>
        <div class="section">
            <span class="left"><label>Item Returnable:</label></span
            <span class="right"><form:select path="item.returnable" items="${itemReturnTypes}"/>*</span>
        </div>
        <div class="section">
            <span class="center"><input type="submit" value="Update"/></span>
        </div>
    </div>
</form:form>