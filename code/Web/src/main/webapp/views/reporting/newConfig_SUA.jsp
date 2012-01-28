<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="general_division">
	<h3 align="left"><label>Stock Unavailability Alert Configuration</label></h3>
	<div class="section">
		<span class="left"><label>No. of Days:</label></span>
		<span class="right"><form:input path="alertConfig.alertParameters" class="config_params"/></span>
	</div>
	<h4 align="left"><label>Group Selection</label></h4>
</div>