<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script type="text/javascript">
    function submitForm(){
		$('#hawker_list').submit();
    }
</script>

<div class="all_list">
    <div class="content_header">Hawker Details</div>
    <form method="POST" action="<%=request.getContextPath()%>/hawker/list" id="hawker_list">
        <c:if test="${hawkerList.count != '0'}">
                <div class="section">
                    <span class="left"><label>Select Hawker:</label></span
                    <span class="right">
                        <form:select path="hawkerList.selectedHawkerId" items="${hawkerList.hawkers}" itemValue="hawkerId" itemLabel="hawkerName" onChange="submitForm();"/>
                    </span>
                </div>
                 <div class="section">
                    <span class="left"><label>Hawker Name:</label></span
                    <span class="right"><label>${hawkerList.selectedHawker.hawkerName}</label></span>
                </div>
                <div class="section">
                    <span class="left"><label>Hawker Default Discount:</label></span
                    <span class="right"><label>${hawkerList.selectedHawker.hawkerDiscount}</label></span>
                </div>
                <div class="section">
                    <span class="left"><label>Hawker Billing Cycle:</label></span
                    <span class="right"><label>${hawkerList.selectedHawker.billingCycleDescription}</label></span>
                </div>
                <div class="section">
                    <span class="left"><label>Hawker Address:</label></span>
                    <span class="right"><label>
                      <c:choose>
                        <c:when test="${hawkerList.selectedHawker.address != ''}">
                            ${hawkerList.selectedHawker.address}
                        </c:when>
                        <c:otherwise>
                            Not Provided
                        </c:otherwise>
                      </c:choose>
                    </label></span>
                </div>
                <div class="section">
                    <span class="left"><label>Hawker Primary Phone:</label></span>
                    <span class="right"><label>
                      <c:choose>
                        <c:when test="${hawkerList.selectedHawker.phoneNumber != ''}">
                            ${hawkerList.selectedHawker.phoneNumber}
                        </c:when>
                        <c:otherwise>
                            Not Provided
                        </c:otherwise>
                      </c:choose>
                    </label></span>
                </div>
                <div class="section">
                    <span class="left"><label>Hawker Alternate Phone:</label></span>
                    <span class="right"><label>
                      <c:choose>
                        <c:when test="${hawkerList.selectedHawker.alternatePhone != ''}">
                            ${hawkerList.selectedHawker.alternatePhone}
                        </c:when>
                        <c:otherwise>
                            Not Provided
                        </c:otherwise>
                      </c:choose>
                    </label></span>
                </div>
                <div class="section">
                    <span class="left"><a href="<%=request.getContextPath()%>/hawker/updateForm/${hawkerList.selectedHawker.hawkerId}">Update Hawker</a></span>
                </div>
        </c:if>
        <c:if test="${hawkerList.count == '0'}">
            <h1>No Hawkers to show</h1>
        </c:if>
	</form>
</div>