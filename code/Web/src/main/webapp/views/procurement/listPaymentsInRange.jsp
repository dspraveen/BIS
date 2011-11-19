<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div id="procurement_payments_accordion">
     <c:forEach var="paymentHistoryProcurements" items="${paymentHistoryProcurements}">
        <h3>
            <a href="">
                <label>Vendor : ${paymentHistoryProcurements.vendor.vendorName}</label>
                <label>Date : ${paymentHistoryProcurements.date}</label>
                <label>Amount : ${paymentHistoryProcurements.amount}</label>
            </a>
         <h3>
		<div class="section">
			<span class="left"><label>Receipt No.:</label></span>
			<span class="right"><label>${paymentHistoryProcurements.receiptNum}</label></span>
		</div>
		<div class="section">
			<span class="left"><label>Mode:</label></span>
			<span class="right"><label>${paymentHistoryProcurements.modeDescription}</label></span>
		</div>
		<div class="section">
			<span class="left"><label>Remarks:</label></span>
			<span class="right"><label>${paymentHistoryProcurements.remarks}</label></span>
		</div>
		<a href="<%=request.getContextPath()%>/procurementPayment/updateForm/${paymentHistoryProcurements.paymentId}"></br><h4> Update this Payment</h4></a>
	</c:forEach>
</div>
<script>
    $(function() {
        $( "#procurement_payments_accordion" ).accordion();
    });
</script>