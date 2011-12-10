<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<script type="text/javascript">
    $(document).ready(function(){

        $('.vendorId').bind("change",function(){
            var transactionInRangeUrl = "<%=request.getContextPath()%>/procurementBilling/generateBill?vendorId="+$('.vendorId').val();
            $.ajax({
                url : transactionInRangeUrl,
                processData : true,
                success : function(data) {
                    $(".currentBill").html(data);
                }
            })
        });
    });

    function validateForm(){
		var errors=new Array();
		var errorCount =0;
		if($('.vendorId').val()==-1){
			errors[errorCount++]="Please select vendor";
		}
		if(errorCount >0){
			alert(errors.join('\n'));
			return false;
		}
    }
</script>

<div class="general_division">
<form  method="POST" action="<%=request.getContextPath()%>/procurementBilling/saveProcurementBill" onsubmit="return validateForm();">
<div class="content_header">Generate Purchase Bill Form</div>
    <div>
        <div class="section">
            <span class="left"><label>Select Vendor:</label></span>
            <span class="right">
				<form:select path="ProcurementBillingDetails.vendor.vendorId" class="vendorId">
					<form:option value="-1" label="--Please Select"/>
					<form:options items="${vendors}" itemLabel="vendorName" itemValue="vendorId" />
				</form:select>
			</span>
        </div>
        <div class="currentBill">
        </div>
    </div>
</form>
</div>
