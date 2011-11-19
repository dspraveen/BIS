<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class="content_header">Vendor Details</div>
<div>
    <div class="section">
        <span class="left"><label>Vendor Name:</label></span
        <span class="right"><label>${vendor.vendorName}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Vendor Default Discount:</label></span
        <span class="right"><label>${vendor.vendorDiscount}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Vendor Billing Cycle:</label></span
        <span class="right"><label>${vendor.billingCycleDescription}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Vendor Address:</label></span>
        <span class="right"><label>
          <c:choose>
            <c:when test="${vendor.address != ''}">
                ${vendor.address}
            </c:when>
            <c:otherwise>
                Not Provided
            </c:otherwise>
          </c:choose>
        </label></span>
    </div>
    <div class="section">
        <span class="left"><label>Vendor Primary Phone:</label></span>
        <span class="right"><label>
          <c:choose>
            <c:when test="${vendor.phoneNumber != ''}">
                ${vendor.phoneNumber}
            </c:when>
            <c:otherwise>
                Not Provided
            </c:otherwise>
          </c:choose>
        </label></span>
    </div>
    <div class="section">
        <span class="left"><label>Vendor Alternate Phone:</label></span>
        <span class="right"><label>
          <c:choose>
            <c:when test="${vendor.alternatePhone != ''}">
                ${vendor.alternatePhone}
            </c:when>
            <c:otherwise>
                Not Provided
            </c:otherwise>
          </c:choose>
        </label></span>
    </div>
    <div class="section">
        <span class="left"><a href="<%=request.getContextPath()%>/vendor/updateForm/${vendor.vendorId}">Update Vendor</a></span>
    </div>
</div>
