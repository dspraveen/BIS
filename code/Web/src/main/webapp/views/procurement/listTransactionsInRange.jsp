<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div id="procurement_transactions_accordion">
    <TABLE  width="350px" border="1">
        <thead class="table_header">
            <TD>Sl.No</TD>
		    <TD>Vendor</TD>
			<TD>Date</TD>
			<TD>TransactionType</TD>
			<TD>Total</TD>
			<TD>Action</TD>
		</thead>
        <c:forEach var="procurementTransaction" items="${procurementTransactions}" varStatus="counter">
            <tr>
                <td>${counter.count}</td>
                <td>${procurementTransaction.vendor.vendorName}</td>
                <td>${procurementTransaction.date}</td>
                <td>${procurementTransaction.transactionType}</td>
                <td>${procurementTransaction.totalAmount}</td>
                <td><a href="#" rel="#transaction_details${counter.count}">Show Details</a></td>
            </tr>
        </c:forEach>
     </TABLE>
    <c:forEach var="procurementTransaction" items="${procurementTransactions}" varStatus="counter">
        <div class="simple_overlay" id="transaction_details${counter.count}">
              <h2>Transaction Details for line : ${counter.count}</h2>
             <TABLE  width="350px" border="1">
                         <thead class="table_header">
                            <TD>Item</TD>
                            <TD>Date Of Publishing</TD>
                            <TD>Price Per Item</TD>
                            <TD>Qty</TD>
                         </thead>
                        <c:forEach var="transactionDetail" items="${procurementTransaction.transactionDetails}">
                            <tr>
                              <td>${transactionDetail.item.itemName}</td>
                              <td>${transactionDetail.dateOfPublishing}</td>
                              <td>${transactionDetail.amount}</td>
                              <td>${transactionDetail.quantity}</td>
                            </tr>
                        </c:forEach>
              </TABLE>
         </div>
    </c:forEach>
</div>
<script>
    $(function() {
        $("a[rel]").overlay({
			top:'25%',
			left:'1100px'
		});
    });
</script>
