<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div>
    <h1>Transaction Details</h1>
    <div class="section">
        <span class="left"><label>Vendor Name:</label></span
        <span class="right"><label>${salesTransaction.hawker.hawkerName}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Transaction Date:</label></span
        <span class="right"><label>${salesTransaction.date}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Transaction Type:</label></span
        <span class="right"><label>${salesTransaction.transactionType}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Transaction Total:</label></span
        <span class="right"><label>${salesTransaction.totalAmount}</label></span>
    </div>
    <div class="section">
        <TABLE id="dataTable" width="350px" border="1">
				 <thead>
					<TD>Item</TD>
					<TD>Date Of Publishing</TD>
					<TD>Qty</TD>
					<TD>Total Amount</TD>
				 </thead>
                 <c:forEach var="transactionDetail" items="${salesTransaction.transactionDetails}">
                    <tr>
                      <td>${transactionDetail.item.itemName}</td>
                      <td>${transactionDetail.dateOfPublishing}</td>
                      <td>${transactionDetail.quantity}</td>
                      <td>${transactionDetail.amount}</td>
                    </tr>
                 </c:forEach>
			</TABLE>
    </div>
</div>
