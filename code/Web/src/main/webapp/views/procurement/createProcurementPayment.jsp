<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<script type="text/javascript">
    $(document).ready(function(){
        $('.payment_date').datepicker({dateFormat: 'dd-mm-yy' });

        $('#Lastest_bill').bind("click",function(){
            var transactionInRangeUrl = "<%=request.getContextPath()%>/procurementBilling/showLastBill?vendorId="+$('.vendor_name').val();
            $.ajax({
                url : transactionInRangeUrl,
                processData : true,
                success : function(data) {
                    $(".lastBill").html(data);
                }
            })
        });
    });

    function validateForm(){
		var errors=new Array();
		var errorCount =0;
		if($('.vendor_name').val()==-1){
			errors[errorCount++]="Please select vendor";
		}
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

<div class="general_division">
<form  method="POST" action="<%=request.getContextPath()%>/procurementPayment/createProcurementPayment" onsubmit="return validateForm();">
    <div class="content_header">Procurement Payment Entry Form</div>
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
            <span class="center"><input class="buttons" type="submit" value="Submit"/> <input class="buttons" type="reset" value="Clear"/><input class="buttons" type="button" value="Lastest Bill" id="Lastest_bill"/></span>
        </div></br></br>
        <div class="lastBill">
        </div>
    </div>
</form>
</div>