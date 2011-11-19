<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class="content_header">Hawker Details</div>
<div>
    <div class="section">
        <span class="left"><label>Hawker Name:</label></span
        <span class="right"><label>${hawker.hawkerName}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Hawker Default Discount:</label></span
        <span class="right"><label>${hawker.hawkerDiscount}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Hawker Billing Cycle:</label></span
        <span class="right"><label>${hawker.billingCycleDescription}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Hawker Address:</label></span>
        <span class="right"><label>
          <c:choose>
            <c:when test="${hawker.address != ''}">
                ${hawker.address}
            </c:when>
            <c:otherwise>
                Not Provided
            </c:otherwise>
          </c:choose>
        </label></span>
    </div>
    <div class="section">
        <span class="left"><label>Hawker Primary Phone:</label></span>
        <span class="right"><label>
          <c:choose>
            <c:when test="${hawker.phoneNumber != ''}">
                ${hawker.phoneNumber}
            </c:when>
            <c:otherwise>
                Not Provided
            </c:otherwise>
          </c:choose>
        </label></span>
    </div>
    <div class="section">
        <span class="left"><label>Hawker Alternate Phone:</label></span>
        <span class="right"><label>
          <c:choose>
            <c:when test="${hawker.alternatePhone != ''}">
                ${hawker.alternatePhone}
            </c:when>
            <c:otherwise>
                Not Provided
            </c:otherwise>
          </c:choose>
        </label></span>
    </div>
    <div class="section">
        <span class="left"><a href="<%=request.getContextPath()%>/hawker/updateForm/${hawker.hawkerId}">Update Hawker</a></span>
    </div>
</div>
