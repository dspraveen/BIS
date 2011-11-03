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
<form method="POST" action="<%=request.getContextPath()%>/hawker/update" onsubmit="return validateForm();">
    <form:hidden path="hawker.hawkerId"/>
    <div>
        <div class="section">
            <span class="left"><label>Hawker Name:</label></span>
            <span class="right"><form:input path="hawker.hawkerName" class="hawker_name" readonly="true"/>*</span>
        </div>
        <div class="section">
            <span class="left"><label>Hawker Default Discount:</label></span
            <span class="right"><form:input path="hawker.hawkerDiscount" class="hawker_discount" />*</span>
        </div>
        <div class="section">
            <span class="left"><label>Hawker Billing Cycle:</label></span
            <span class="right"><form:select path="hawker.billingCycle" items="${billingCycles}" /></span>
        </div>
        <div class="section">
            <span class="left"><label>Hawker Address:</label></span
            <span class="right"><form:input path="hawker.address" /></span>
        </div>
        <div class="section">
            <span class="left"><label>Hawker Primary Phone Number:</label></span
            <span class="right"><form:input path="hawker.phoneNumber" /></span>
        </div>
        <div class="section">
            <span class="left"><label>Hawker Alternate Phone Number:</label></span
            <span class="right"><form:input path="hawker.alternatePhone" /></span>
        </div>
        <div class="section">
            <span class="center"><input type="submit" value="Update"/></span>
        </div>
    </div>
</form>

