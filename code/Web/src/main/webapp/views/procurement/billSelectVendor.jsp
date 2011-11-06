<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<script type="text/javascript">

    function validateForm(){
		var errors=new Array();
		var errorCount =0;
		if($('.vendor_name').val()==-1){
			errors[errorCount++]="Please select vendor";
		}
		if(errorCount >0){
			alert(errors.join('\n'));
			return false;
		}
    }
</script>

<form  method="POST" action="<%=request.getContextPath()%>/procurementPayment/createProcurementPayment" onsubmit="return validateForm();">
    <div>
        <div class="section">
            <span class="left"><label>Select Vendor:</label></span>
            <span class="right">
				<form:select path="BillingProcurement.vendor.vendorId" class="vendor_name">
					<form:option value="-1" label="--Please Select"/>
					<form:options items="${vendors}" itemLabel="vendorName" itemValue="vendorId" />
				</form:select>
			</span>
        </div>
    </div>
</form>
