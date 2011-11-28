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
     <TABLE  width="500px" border="1">
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
        <div class="section">
            <span class="left"><label>Purchase Transactions Total:</label></span>
            <span class="right"><label>${ProcurementBillingDetails.purchaseTransactionTotal}</label></span>
        </div>
        <div class="section">
            <span class="left"><label>Return Transactions Total:</label></span>
            <span class="right"><label>${ProcurementBillingDetails.returnTransactionTotal}</label></span>
        </div>			
  </div>
  <div class="payment_details">
     <TABLE  width="500px" border="1">
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
  <p>Bill Total:${ProcurementBillingDetails.billAmount}</p>
 <div>
    <div>
    <h3>Quick Payment</h3></br>
    </div>
    <div class="section">
         <span class="left"><label>Amount:</label></span>
         <span class="right"><form:input path="ProcurementBillingDetails.paymentAmount" class="amount"/></span>
     </div>
    <div class="section">
         <span class="left"><label>Receipt Number:</label></span>
         <span class="right"><form:input path="ProcurementBillingDetails.receiptNum" class="receiptNum"/></span>
     </div>
         <div class="section">
         <span class="left"><label>Mode:</label></span>
         <span class="right"><form:select path="ProcurementBillingDetails.mode" items="${PaymentMode}"/></span>
     </div>
     <div class="section">
         <span class="left"><label>Remarks:</label></span>
         <span class="right"><form:input path="ProcurementBillingDetails.remarks" class="receiptNum"/></span>
     </div>
 </div>
 <div class="section">
    <span class="left"><input type="submit" value="Save Bill" class="generate_bill"/></span>
 </div>
</div>
