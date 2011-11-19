<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div>
<h2>Bill Details</h2></br>
</div>
<div>
    <div class="procurement_transactions">
        <TABLE  width="350px" border="1">
            <thead class="table_header">
                <TD>Sl.No</TD>
                <TD>Date</TD>
                <TD>TransactionType</TD>
                <TD>Total</TD>
            </thead>
            <c:forEach var="procurementTransaction" items="${ProcurementBillingDetails.procurementTransactions}" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${procurementTransaction.date}" /></td>
                    <td>${procurementTransaction.transactionTypeDescription}</td>
                    <td>${procurementTransaction.totalAmount}</td>
                </tr>
            </c:forEach>
         </TABLE>
         <p>Purchase Transactions Total:${ProcurementBillingDetails.purchaseTransactionTotal}</p>
         <p>Return Transactions Total:${ProcurementBillingDetails.returnTransactionTotal}</p>
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
            <c:forEach var="procurementPayment" items="${ProcurementBillingDetails.procurementPayments}" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${procurementPayment.date}" /></td>
                    <td>${procurementPayment.modeDescription}</td>
                    <td>${procurementPayment.receiptNum}</td>
                    <td>${procurementPayment.amount}</td>
                </tr>
            </c:forEach>
         </TABLE>
         <p>Payment Transactions Total:${ProcurementBillingDetails.paymentTotal}</p>
     </div>
     <p>Payment Transactions Total:${ProcurementBillingDetails.billAmount}</p>
</div>
