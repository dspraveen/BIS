<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="procurement_transactions" id="procurement_payments_accordion">
	<TABLE  width="350px" border="1">
		<thead class="table_header">
			<TD>Vendor</TD>
			<TD>Date</TD>
			<TD>Amount</TD>
			<TD>Receipt Number</TD>
			 <TD>Mode</TD>
			 <TD>Remarks</TD>
			 <TD>Action</TD> 
		</thead>
		<c:forEach var="paymentHistoryProcurements" items="${paymentHistoryProcurements}">
			<tr>
				<td>${paymentHistoryProcurements.vendor.vendorName}</td>
				<td><fmt:formatDate pattern="dd-MM-yyyy" value="${paymentHistoryProcurements.date}" /></td>
				<td>${paymentHistoryProcurements.amount}</td>
				<td>${paymentHistoryProcurements.receiptNum}</td>
				<td>${paymentHistoryProcurements.modeDescription}</td>
				<td>${paymentHistoryProcurements.remarks}</td>
				<td><a href="<%=request.getContextPath()%>/procurementPayment/updateForm/${paymentHistoryProcurements.paymentId}"></br>Update</a></td>
			</tr>
		</c:forEach>
	 </TABLE>
 </div>

		
