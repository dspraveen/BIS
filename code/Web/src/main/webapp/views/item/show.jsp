<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div>
    <h1>Item Details</h1>
    <div class="section">
        <span class="left"><label>Item Name:</label></span
        <span class="right"><label>${item.itemName}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Item Description:</label></span
        <span class="right"><label>${item.description}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Item Type:</label></span
        <span class="right"><label>${item.itemLife}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Item Returnable:</label></span
        <span class="right"><label>${item.returnable}</label></span>
    </div>
    <div class="section">
        <span class="center"><a href="/item/updateForm/${item.itemCode}">Update Item</a></span>
    </div>
</div>