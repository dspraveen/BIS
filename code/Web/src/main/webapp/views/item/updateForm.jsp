<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<form method="POST" action="<%=request.getContextPath()%>/item/update">
    <form:hidden path="item.itemCode"/>
    <div class="item_form">
        <div class="content_header">Item Update Form</div>
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
            <span class="right"><form:input path="item.defaultPrice" /></span>
        </div>
        <div class="section">
            <span class="left"><label>Item Type:</label></span
            <span class="right"><form:select path="item.itemLife" items="${itemTypes}" />*</span>
        </div>
        <div class="section">
            <span class="left"><label>Item Returnable:</label></span
            <span class="right"><form:select path="item.returnable" items="${itemReturnTypes}"/>*</span>
        </div>
    </div>
        <div class="button_div">
            <span class="left"><input class="buttons" type="submit" value="Update"/></span>
        </div>
</form>