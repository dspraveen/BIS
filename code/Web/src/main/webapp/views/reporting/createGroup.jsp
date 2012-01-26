<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="general_division">
    <div class="content_header">Create Group</div>
    <div class="section">
        <span class="left"><label>Group Name:</label></span
        <span class="right"><input type="text" /></span>
    </div>
    <div class="section">
        <span class="left"><label>Group Description:</label></span
        <span class="right"><input type="text" class="to_date"/></span>
    </div>
    <div class="section">
        <span class="left"><label>Unassigned Vendors:</label></span
        <span class="right">
            <select class="vendor_name">
                <option value="-1">--Please Select</option>
                <c:forEach var="vendor" items="${vendors}">
                    <option value="${vendor.vendorId}">${vendor.vendorName}</option>
                </c:forEach>
            </select>
        </span>
    </div>
    <div class="section">
        <span class="left"><label>Unassigned Hawkers:</label></span
        <span class="right">
            <select class="hawker_name">
                <option value="-1">--Please Select</option>
                <c:forEach var="hawker" items="${hawkers}">
                    <option value="${hawker.hawkerId}">${hawker.hawkerName}</option>
                </c:forEach>
            </select>
        </span>
    </div>
</div>
<div class="button_div">
    <span class="left"><input class="buttons" type="button" value="Fetch Sales Bills" id="fetch_Bills"/></span>
</div></br></br>
<div class="sales_Bills">
</div>
