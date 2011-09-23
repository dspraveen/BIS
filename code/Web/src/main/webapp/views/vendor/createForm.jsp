<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script type="text/javascript">
    function validateForm(){
        if(!($('.vendor_name').val())){
            alert("Vendor Name Not Provided");
            return false;
        }
        if(!($('.vendor_discount').val())){
            alert("Vendor discount Not Provided");
            return false;
        }else{
            var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
            if(!floatRegex.test($('.vendor_discount').val())){
                alert("Vendor discount Not Valid");
                return false;
            }
        }
        return true;
    }
</script>
<form:form commandName="vendor" method="POST" action="create"  onsubmit="return validateForm();">
    <div>
        <div class="section">
            <span class="left"><label>Vendor Name:</label></span
            <span class="right"><form:input path="vendorName" class="vendor_name"/>*</span>
        </div>
        <div class="section">
            <span class="left"><label>Vendor Default Discount:</label></span
            <span class="right"><form:input path="vendorDiscount" class="vendor_discount" />*</span>
        </div>
        <div class="section">
            <span class="left"><label>Vendor Billing Cycle:</label></span
            <span class="right"><form:select path="billingCycle" items="${billingCycles}" /></span>
        </div>
        <div class="section">
            <span class="left"><label>Vendor Address:</label></span
            <span class="right"><form:input path="address" /></span>
        </div>
        <div class="section">
            <span class="left"><label>Vendor Primary Phone Number:</label></span
            <span class="right"><form:input path="phoneNumber" /></span>
        </div>
        <div class="section">
            <span class="left"><label>Vendor Alternate Phone Number:</label></span
            <span class="right"><form:input path="alternatePhone" /></span>
        </div>

        <div class="section">
            <span class="center"><input type="submit" value="Submit"/> <input type="reset" value="Clear"/></span>
        </div>
    </div>
</form:form>