<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div>
    <TABLE id="dataTable" width="350px" border="1">
        <thead class="table_header">
            <TD></TD>
            <TD>Item</TD>
            <TD>Date Of Publishing</TD>
            <TD>MRP</TD>
            <TD>Discount</TD>
            <TD>Price Per Item</TD>
            <TD>Qty</TD>
            <TD>Total</TD>
        </thead>
        <c:forEach var="index" begin="1" end="${fn:length(salesTransactionGrid.transactionDetails)}" step="1">
            <form:hidden path="salesTransactionGrid.transactionDetails[${index-1}].detailsId"/>
            <form:hidden path="salesTransactionGrid.transactionDetails[${index-1}].transactionId"/>
            <tr class="template_row">
                <td><form:checkbox  path='salesTransactionGrid.transactionDetails[${index-1}].checked' class='item_select'/></td>
                <td>
                    <form:select path='salesTransactionGrid.transactionDetails[${index-1}].itemCode' class='item_name'>
                        <form:option value="-1" label="--Please Select"/>
                        <form:options items="${items}" itemLabel="itemName" itemValue="itemCode"/>
                    </form:select>
                </td>
                <td>
					<select class="date_of_publish_select">
						<c:forEach var="issue" items="${salesTransactionGrid.transactionDetails[index-1].issueDates}">
							<option value="${issue.key}">${issue.value}</option>
						</c:forEach>
					</select>
					<form:input path='salesTransactionGrid.transactionDetails[${index-1}].quantity' class='date_of_publish' type='text' display="none"/>
				</td>
                <td><form:input class='mrp' type='text' readonly='true' path="salesTransactionGrid.transactionDetails[${index-1}].mrp"/></td>
                <td><form:input class='discount' type='text' path="salesTransactionGrid.transactionDetails[${index-1}].discount"/></td>
                <td><form:input class='price_per_item' type='text' path="salesTransactionGrid.transactionDetails[${index-1}].pricePerItem"/></td>
                <td><form:input path='salesTransactionGrid.transactionDetails[${index-1}].quantity' class='quantity' type='text'/></td>
                <td><form:input path='salesTransactionGrid.transactionDetails[${index-1}].amount' class='amount' type='text' readonly='true'/></td>
            </tr>
        </c:forEach>
        <tr class="transaction_details_count">
            <td colspan="7">Total</td>
            <td><form:input type="text" readonly="true" path='salesTransactionGrid.grandTotal' class="grand_total"/></td>
        </tr>
    </TABLE>
</div>



