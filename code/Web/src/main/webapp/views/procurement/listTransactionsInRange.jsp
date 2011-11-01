<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div id="procurement_transactions_accordion">
     <c:forEach var="procurementTransaction" items="${procurementTransactions}">
        <h3>
            <a href="">
                <label>Vendor : ${procurementTransaction.vendor.vendorName}</label>
                <label>Date : ${procurementTransaction.date}</label>
                <label>Type : ${procurementTransaction.transactionType}</label>
            </a>
         <h3>
         <div>
            <TABLE  width="350px" border="1">
				 <thead>
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
            <a href="/procurement/updateForm/${procurementTransaction.transactionId}"> Update this transaction</a>
         </div>
     </c:forEach>
</div>
<script>
    $(function() {
        $( "#procurement_transactions_accordion" ).accordion();
    });
</script>
