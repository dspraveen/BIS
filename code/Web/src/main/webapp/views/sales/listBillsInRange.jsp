<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<h3>Bill Details</h3>
<div class="sales_transactions" id="sales_bills">
	<TABLE  width="800px" border="1">
		<thead class="table_header">
			<TD>Hawker</TD>
			<TD>Start Date</TD>
			<TD>End Date</TD>
			<TD>Total Bill Amount</TD>
			<TD>Balance Amount</TD>
		</thead>
		<c:forEach var="billingSales" items="${billingSales}">
			<tr>
				<td>${billingSales.hawker.hawkerName}</td>
				<td><fmt:formatDate pattern="dd-MM-yyyy" value="${billingSales.startDate}" /></td>
				<td><fmt:formatDate pattern="dd-MM-yyyy" value="${billingSales.endDate}" /></td>
				<td>${billingSales.salesAmount}</td>
				<td>${billingSales.balanceAmount}</td>
			</tr>
		</c:forEach>
	 </TABLE>
 </div>