<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="sales_transactions" id="sales_payments_accordion">
	<TABLE  width="350px" border="1">
		<thead class="table_header">
			<TD>Hawker</TD>
			<TD>Date</TD>
			<TD>Amount</TD>
			<TD>Receipt Number</TD>
			 <TD>Mode</TD>
			 <TD>Remarks</TD>
			 <TD>Action</TD>
		</thead>
		<c:forEach var="paymentHistorySales" items="${paymentHistorySales}">
			<tr>
				<td>${paymentHistorySales.hawker.hawkerName}</td>
				<td><fmt:formatDate pattern="dd-MM-yyyy" value="${paymentHistorySales.date}" /></td>
				<td>${paymentHistorySales.amount}</td>
				<td>${paymentHistorySales.receiptNum}</td>
				<td>${paymentHistorySales.modeDescription}</td>
				<td>${paymentHistorySales.remarks}</td>
				<td><a href="<%=request.getContextPath()%>/salesPayment/updateForm/${paymentHistorySales.paymentId}"></br>Update</a></td>
			</tr>
		</c:forEach>
	 </TABLE>
 </div>


