<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script type="text/javascript">
    function submitForm(){
		$('#vendor_list').submit();
    }
</script>
<div class="content_header">Vendor Details</div>
<div>
    <form method="POST" action="<%=request.getContextPath()%>/vendor/list" id="vendor_list">
        <c:if test="${vendorList.count != '0'}">
                <div class="section">
                    <span class="left"><label>Select Vendor:</label></span
                    <span class="right">
                        <form:select path="vendorList.selectedVendorId" items="${vendorList.vendors}" itemValue="vendorId" itemLabel="vendorName" onChange="submitForm();"/>
                    </span>
                </div>
                 <div class="section">
                    <span class="left"><label>Vendor Name:</label></span
                    <span class="right"><label>${vendorList.selectedVendor.vendorName}</label></span>
                </div>
                <div class="section">
                    <span class="left"><label>Vendor Default Discount:</label></span
                    <span class="right"><label>${vendorList.selectedVendor.vendorDiscount}</label></span>
                </div>
                <div class="section">
                    <span class="left"><label>Vendor Billing Cycle:</label></span
                    <span class="right"><label>${vendorList.selectedVendor.billingCycle}</label></span>
                </div>
                <div class="section">
                    <span class="left"><label>Vendor Address:</label></span>
                    <span class="right"><label>
                      <c:choose>
                        <c:when test="${vendorList.selectedVendor.address != ''}">
                            ${vendorList.selectedVendor.address}
                        </c:when>
                        <c:otherwise>
                            Not Provided
                        </c:otherwise>
                      </c:choose>
                    </label></span>
                </div>
                <div class="section">
                    <span class="left"><label>Vendor Primary Phone:</label></span>
                    <span class="right"><label>
                      <c:choose>
                        <c:when test="${vendorList.selectedVendor.phoneNumber != ''}">
                            ${vendorList.selectedVendor.phoneNumber}
                        </c:when>
                        <c:otherwise>
                            Not Provided
                        </c:otherwise>
                      </c:choose>
                    </label></span>
                </div>
                <div class="section">
                    <span class="left"><label>Vendor Alternate Phone:</label></span>
                    <span class="right"><label>
                      <c:choose>
                        <c:when test="${vendorList.selectedVendor.alternatePhone != ''}">
                            ${vendorList.selectedVendor.alternatePhone}
                        </c:when>
                        <c:otherwise>
                            Not Provided
                        </c:otherwise>
                      </c:choose>
                    </label></span>
                </div>
                <div class="section">
                    <span class="left"><a href="<%=request.getContextPath()%>/vendor/updateForm/${vendorList.selectedVendor.vendorId}">Update Vendor</a></span>
                </div>
        </c:if>
        <c:if test="${vendorList.count == '0'}">
            <h1>No Vendors to show</h1>
        </c:if>
	</form>
</div>