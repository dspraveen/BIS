<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/transactionDetails.js"></script>
<form method="POST" action="<%=request.getContextPath()%>/sales/addSalesTransaction"  onsubmit="return validateForm();">
    <div class="content_header">Sales Transaction Process Form</div>
    <div class="errors">
    </div>
    <div>
        <form:hidden path="salesTransaction.transactionId"/>
        <form:hidden path="salesTransaction.effectedRowId" id="effectedRowId"/>
        <form:hidden path="salesTransaction.changeType" id="changeType"/>
        <div class="section">
            <span class="left"><label>Select Hawker:</label></span
            <span class="right">
				<form:select path="salesTransaction.targetId" class="hawker_name">
					<form:option value="-1" label="--Please Select"/>
					<form:options items="${hawkers}" itemLabel="hawkerName" itemValue="hawkerId" />
				</form:select>
			</span>
        </div>
        <div class="section">
            <span class="left"><label>Transaction Date:</label></span
            <span class="right"><form:input path="salesTransaction.transactionDate" class="transaction_date"/></span>
        </div>
        <div class="section">
            <span class="left"><label>Transaction Type:</label></span
            <span class="right"><form:select path="salesTransaction.type" items="${salesTransactionType}" /></span>
        </div>
        <div>
             <div class="transaction_details"></div>
             <INPUT type="button" value="Add Row" class="add_item"/>
             <INPUT type="button" value="Delete Row" class="remove_item"/>
         </div>
        <div class="section">
            <span class="center"><input type="submit" value="Submit" class="submit"/> <input type="reset" value="Clear"/></span>
        </div>
    </div>
</form>
<script type="text/javascript">
    $(document).ready(function(){
        $('.transaction_date').datepicker({dateFormat: 'dd-mm-yy' });
        $('.date_of_publish').datepicker({dateFormat: 'dd-mm-yy' });
        $('.transaction_details').transactionDetails({contextPath:'<%=request.getContextPath()%>',type:'sales'});
    });
</script>




