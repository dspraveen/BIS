<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div id="sales_payments_accordion">
     <c:forEach var="paymentHistorySales" items="${paymentHistorySales}">
        <h3>
            <a href="">
                <label>Hawker : ${paymentHistorySales.hawker.hawkerName}</label>
                <label>Date : ${paymentHistorySales.date}</label>
                <label>Amount : ${paymentHistorySales.amount}</label>
            </a>
         <h3>
		<div class="section">
			<span class="left"><label>Receipt No.:</label></span>
			<span class="right"><label>${paymentHistorySales.receiptNum}</label></span>
		</div>
		<div class="section">
			<span class="left"><label>Mode:</label></span>
			<span class="right"><label>${paymentHistorySales.mode}</label></span>
		</div>
		<div class="section">
			<span class="left"><label>Remarks:</label></span>
			<span class="right"><label>${paymentHistorySales.remarks}</label></span>
		</div>
		<a href="<%=request.getContextPath()%>/salesPayment/updateForm/${paymentHistorySales.paymentId}"></br><h4> Update this Payment</h4></a>
	</c:forEach>
</div>
<script>
    $(function() {
        $( "#sales_payments_accordion" ).accordion();
    });
</script>