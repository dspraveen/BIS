<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="general_division">
	<div class="content_header">Alerts Update</div>
	<div class="section">
        <span class="left"><label>Alert Type:</label></span>
        <span class="right"><label>${alertConfigParams.alertConfig.alertType.alertName}</label></span>
    </div>
    <div class="section">
		<span class="left"><label>No. of Days:</label></span>
		<span class="right"><form:input path="alertConfigParams.numOfDays" class="config_params"/></span>
	</div>
	<div>
		<h4 align="left"><label>Group Selection</label></h4>
	</div>
</div>