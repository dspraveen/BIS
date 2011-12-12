<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class="general_division">
    <div class="content_header">Transaction Details</div>
    <div class="section">
        <span class="left"><label>Hawker Name:</label></span
        <span class="right"><label>${salesTransaction.hawker.hawkerName}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Transaction Date:</label></span
        <span class="right"><label><fmt:formatDate pattern="dd-MM-yyyy" value="${salesTransaction.date}"/></label></span>
    </div>
    <div class="section">
        <span class="left"><label>Transaction Type:</label></span
        <span class="right"><label>${salesTransaction.transactionTypeDescription}</label></span>
    </div>
    <div class="section">
        <span class="left"><label>Transaction Total:</label></span
        <span class="right"><label>${salesTransaction.totalAmount}</label></span>
    </div>
    <div class="section">
        <TABLE id="displayTable" width="615px" border="1">
				 <thead class="table_header">
					<TD>Item</TD>
					<TD>Date Of Publishing</TD>
					<TD>Price Per Item</TD>
					<TD>Qty</TD>
					<TD>Amount</TD>
				 </thead>
                 <c:forEach var="transactionDetail" items="${salesTransaction.transactionDetails}">
                    <tr>
                      <td>${transactionDetail.item.itemName}</td>
                      <td><fmt:formatDate pattern="dd-MM-yyyy" value="${transactionDetail.dateOfPublishing}" /></td>
                      <td>${transactionDetail.netPrice}</td>
                      <td>${transactionDetail.quantity}</td>
                      <td>${transactionDetail.amount}</td>
                    </tr>
                 </c:forEach>
		</TABLE>
    </div>
</div>
