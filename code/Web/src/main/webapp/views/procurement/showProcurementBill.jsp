<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="general_division">
    <div class="content_header">Bill Details</div>
    <div class="section">
        <span class="left"><label>Bill Id:</label></span>
        <span class="right"><label>${BillingProcurement.billId}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Vendor name:</label></span>
        <span class="right"><label>${BillingProcurement.vendor.vendorName}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Start Date:</label></span>
		<span class="right"><label><fmt:formatDate pattern="dd-MM-yyyy" value="${BillingProcurement.startDate}" /></span>
    </div>
    <div class="section">
        <span class="left"><label>End Date:</label></span>
		<span class="right"><label><fmt:formatDate pattern="dd-MM-yyyy" value="${BillingProcurement.endDate}" /></span>
    </div>
    <div class="section">
        <span class="left"><label>Total Bill Amount</label></span>
        <span class="right"><label>${BillingProcurement.procurementAmount}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Outstanding Amount</label></span>
        <span class="right"><label>${BillingProcurement.balanceAmount}</label></span>
    </div>
</div>