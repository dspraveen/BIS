<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div>
<h2>Bill Details</h2></br>
</div>
<div>
    <div class="sales_transactions">
        <TABLE  width="350px" border="1">
            <thead class="table_header">
                <TD>Sl.No</TD>
                <TD>Date</TD>
                <TD>TransactionType</TD>
                <TD>Total</TD>
            </thead>
            <c:forEach var="salesTransaction" items="${SalesBillingDetails.salesTransactions}" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td><fmt:formatDate pattern="dd-MMM-yyyy" value="${salesTransaction.date}" /></td>
                    <td>${salesTransaction.transactionTypeDescription}</td>
                    <td>${salesTransaction.totalAmount}</td>
                </tr>
            </c:forEach>
         </TABLE>
         <p>Sales Transactions Total:${SalesBillingDetails.salesTransactionTotal}</p>
         <p>Return Transactions Total:${SalesBillingDetails.returnTransactionTotal}</p>
     </div>
     <div class="payment_details">
        <TABLE  width="350px" border="1">
            <thead class="table_header">
                <TD>Sl.No</TD>
                <TD>Date</TD>
                <TD>Mode</TD>
                <TD>Receipt Number</TD>
                <TD>Amount</TD>
            </thead>
            <c:forEach var="salesPayment" items="${SalesBillingDetails.procurementPayments}" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td><fmt:formatDate pattern="dd-MMM-yyyy" value="${salesPayment.date}" /></td>
                    <td>${salesPayment.modeDescription}</td>
                    <td>${salesPayment.receiptNum}</td>
                    <td>${salesPayment.amount}</td>
                </tr>
            </c:forEach>
         </TABLE>
         <p>Payment Transactions Total:${SalesBillingDetails.paymentTotal}</p>
     </div>
     <p>Payment Transactions Total:${SalesBillingDetails.billAmount}</p>
</div>
