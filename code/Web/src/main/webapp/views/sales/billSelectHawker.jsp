<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<script type="text/javascript">
    $(document).ready(function(){

        $('.hawkerId').bind("change",function(){
            var transactionInRangeUrl = "<%=request.getContextPath()%>/salesBilling/generateBill?hawkerId="+$('.hawkerId').val();
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
		if($('.hawkerId').val()==-1){
			errors[errorCount++]="Please select hawker";
		}
		if(errorCount >0){
			alert(errors.join('\n'));
			return false;
		}
    }
</script>

<div class="general_division">
<form  method="POST" action="<%=request.getContextPath()%>/salesBilling/saveSalesBill" onsubmit="return validateForm();">
<div class="content_header">Generate Sales Bill Form</div>
    <div>
        <div class="section">
            <span class="left"><label>Select Hawker:</label></span>
            <span class="right">
				<form:select path="SalesBillingDetails.hawker.hawkerId" class="hawkerId">
					<form:option value="-1" label="--Please Select"/>
					<form:options items="${hawkers}" itemLabel="hawkerName" itemValue="hawkerId" />
				</form:select>
			</span>
        </div>
        <div class="currentBill">
        </div>
    </div>
</form>
</div>