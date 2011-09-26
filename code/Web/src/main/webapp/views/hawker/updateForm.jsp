<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script type="text/javascript">
    function validateForm(){
        if(!($('.hawker_discount').val())){
            alert("Hawker discount Not Provided");
            return false;
        }else{
            var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
            if(!floatRegex.test($('.hawker_discount').val())){
                alert("Hawker discount Not Valid");
                return false;
            }
        }
        return true;
    }
</script>
<form:form commandName="hawker" method="POST" action="/hawker/update" onsubmit="return validateForm();">
    <form:hidden path="hawkerId"/>
    <div>
        <div class="section">
            <span class="left"><label>Hawker Name:</label></span>
            <span class="right"><form:input path="hawkerName" class="hawker_name" readonly="true"/>*</span>
        </div>
        <div class="section">
            <span class="left"><label>Hawker Default Discount:</label></span
            <span class="right"><form:input path="hawkerDiscount" class="hawker_discount" />*</span>
        </div>
        <div class="section">
            <span class="left"><label>Hawker Billing Cycle:</label></span
            <span class="right"><form:select path="billingCycle" items="${billingCycles}" /></span>
        </div>
        <div class="section">
            <span class="left"><label>Hawker Address:</label></span
            <span class="right"><form:input path="address" /></span>
        </div>
        <div class="section">
            <span class="left"><label>Hawker Primary Phone Number:</label></span
            <span class="right"><form:input path="phoneNumber" /></span>
        </div>
        <div class="section">
            <span class="left"><label>Hawker Alternate Phone Number:</label></span
            <span class="right"><form:input path="alternatePhone" /></span>
        </div>
        <div class="section">
            <span class="center"><input type="submit" value="Update"/></span>
        </div>
    </div>
</form:form>

