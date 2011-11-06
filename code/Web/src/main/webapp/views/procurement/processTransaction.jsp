<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/procurementTransactionDetails.js"></script>
<form method="POST" action="<%=request.getContextPath()%>/procurement/addProcurementTransaction"  onsubmit="return validateForm();">
    <div>
        <form:hidden path="procurementTransaction.transactionId"/>
        <form:hidden path="procurementTransaction.effectedRowId" id="effectedRowId"/>
        <form:hidden path="procurementTransaction.changeType" id="changeType"/>
        <div class="section">
            <span class="left"><label>Select Vendor:</label></span
            <span class="right">
				<form:select path="procurementTransaction.targetId" class="vendor_name">
					<form:option value="-1" label="--Please Select"/>
					<form:options items="${vendors}" itemLabel="vendorName" itemValue="vendorId" />
				</form:select>
			</span>
        </div>
        <div class="section">
            <span class="left"><label>Transaction Date:</label></span
            <span class="right"><form:input path="procurementTransaction.transactionDate" class="transaction_date"/></span>
        </div>
        <div class="section">
            <span class="left"><label>Transaction Type:</label></span
            <span class="right"><form:select path="procurementTransaction.type" items="${procurementTransactionType}" /></span>
        </div>
        <div>
             <div class="transaction_details"></div>
             <INPUT type="button" value="Add Row" class="add_item"/>
             <INPUT type="button" value="Delete Row" class="remove_item"/>
         </div>
        <div class="section">
            <span class="center"><input type="submit" value="Submit"/> <input type="reset" value="Clear"/></span>
        </div>
    </div>
</form>
<script type="text/javascript">
    $(document).ready(function(){
        $('.transaction_date').datepicker({dateFormat: 'dd-mm-yy' });
        $('.date_of_publish').datepicker({dateFormat: 'dd-mm-yy' });
        $('.transaction_details').procurementTransactionDetails({contextPath:'<%=request.getContextPath()%>'});
    });
</script>




