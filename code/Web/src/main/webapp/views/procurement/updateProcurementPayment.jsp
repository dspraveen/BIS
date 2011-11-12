<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<script type="text/javascript">
    $(document).ready(function(){
        $('.payment_date').datepicker({dateFormat: 'dd-mm-yy' });
    });
    function validateForm(){
		var errors=new Array();
		var errorCount =0;
		if($('.payment_date').val().trim()==""){
			errors[errorCount++]="Please enter Payment date";
		}
	    if($('.amount').val().trim()=="" || $(this).val().trim()<0){
			errors[errorCount++]="Please select valid amount";
		}
		if(errorCount >0){
			alert(errors.join('\n'));
			return false;
		}
    }
</script>

<form  method="POST" action="<%=request.getContextPath()%>/procurementPayment/updateProcurementPayment" onsubmit="return validateForm();">
    <div class="content_header">Procurement Payment Entry Update Form</div>
    <form:hidden path="PaymentHistoryProcurement.paymentId"/>
    <div>
        <div class="section">
            <span class="left"><label>Select Vendor:</label></span>
            <span class="right">
				<form:select path="PaymentHistoryProcurement.vendor.vendorId" class="vendor_name">
					<form:option value="-1" label="--Please Select"/>
					<form:options items="${vendors}" itemLabel="vendorName" itemValue="vendorId" />
				</form:select>
			</span>
        </div>
        <div class="section">
            <span class="left"><label>Payment Date:</label></span>
            <span class="right"><form:input path="PaymentHistoryProcurement.date" class="payment_date"/></span>
        </div>
        <div class="section">
            <span class="left"><label>Amount:</label></span>
            <span class="right"><form:input path="PaymentHistoryProcurement.amount" class="amount"/></span>
        </div>
        <div class="section">
            <span class="left"><label>Receipt No:</label></span>
            <span class="right"><form:input path="PaymentHistoryProcurement.receiptNum" class="receiptNum"/></span>
        </div>
        <div class="section">
            <span class="left"><label>Mode:</label></span>
            <span class="right">
                <form:select path="PaymentHistoryProcurement.mode" items="${PaymentMode}"/>
            </span>
        </div>
        <div class="section">
            <span class="left"><label>Remarks:</label></span>
            <span class="right"><form:input path="PaymentHistoryProcurement.remarks" class="remarks"/></span>
        </div>
        <div class="section">
            <span class="center"><input type="submit" value="Submit"/> <input type="reset" value="Clear"/></span>
        </div>
    </div>
</form>