<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="general_division">
<div class="content_header">Expired Stock Details Form</div>
	<div align="center">
			<TABLE  width="610px" border="1">
				 <thead class="table_header">
					<TD>Item Code</TD>
					<TD>Item Name</TD>
					<TD>Date Of Publishing</TD>
					<TD>Item Cycle</TD>
					<TD>Quantity</TD>
				 </thead>
				<c:forEach var="stock" items="${stocks}">
				   <tr>
					 <td>${stock.item.itemCode}</td>
					 <td>${stock.item.itemName}</td>
					 <td><fmt:formatDate pattern="dd-MM-yyyy" value="${stock.dateOfPublishing}"/></td>
					  <td>${stock.item.itemLifeDescription}</td>
					 <td>${stock.quantity}</td>
				   </tr>
				</c:forEach>
			</TABLE>
	</div>
</div>