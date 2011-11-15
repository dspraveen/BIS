<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div>
<h2>Bill Details</h2></br>
</div>
<div>
        <div class="section">
		<h4>
                <span class="left"><label>Hawker : </label></span>
                <span class="right"><label>${BillingSales.hawker.hawkerName}</label></span>
        </h4>
		</div>
		<div class="section">
			<span class="left"><label>Start Date :</label></span>
			<span class="right"><label>${BillingSales.startDate}</label></span>
		</div>
		<div class="section">
			<span class="left"><label>End Date :</label></span>
			<span class="right"><label>${BillingSales.endDate}</label></span>
		</div>
		<div class="section">
			<span class="left"><label>Balance Amount :</label></span>
			<span class="right"><label>${BillingSales.balanceAmount}</label></span>
		</div>
</div>
