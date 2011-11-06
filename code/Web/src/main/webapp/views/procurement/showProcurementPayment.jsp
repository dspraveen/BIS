<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div>
    <h1>Payment Details</h1>
    <div class="section">
        <span class="left"><label>Vendor name:</label></span>
        <span class="right"><label>${PaymentHistoryProcurement.vendor.vendorName}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Date:</label></span>
        <span class="right"><label>${PaymentHistoryProcurement.date}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Amount:</label></span>
        <span class="right"><label>${PaymentHistoryProcurement.amount}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Receipt No.:</label></span>
        <span class="right"><label>${PaymentHistoryProcurement.receiptNum}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Mode:</label></span>
        <span class="right"><label>${PaymentHistoryProcurement.mode}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Remarks:</label></span>
        <span class="right"><label>${PaymentHistoryProcurement.remarks}</label></span>
    </div>
    <div class="section">
        <span class="left"><a href="<%=request.getContextPath()%>/procurementPayment/updateForm/${PaymentHistoryProcurement.paymentId}">Update Payment</a></span>
    </div>
</div>
