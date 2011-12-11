<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="item_form">
<div class="content_header">Item Details</div>
    <div class="section">
        <span class="left"><label>Item Name:</label></span
        <span class="right"><label>${item.itemName}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Item Description:</label></span>
        <span class="right"><label>
          <c:choose>
            <c:when test="${item.description != ''}">
                ${item.description}
            </c:when>
            <c:otherwise>
                Not Provided
            </c:otherwise>
          </c:choose>
        </label></span>
    </div>
    <div class="section">
        <span class="left"><label>Item Price:</label></span
        <span class="right"><label>${item.defaultPrice}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Item Type:</label></span
        <span class="right"><label>${item.itemLifeDescription}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Item Returnable:</label></span
        <span class="right"><label>${item.returnableDescription}</label></span>
    </div>
    <div class="section">
        <span class="left"><a href="<%=request.getContextPath()%>/item/updateForm/${item.itemCode}">Update Item</a></span>
    </div>
</div>
