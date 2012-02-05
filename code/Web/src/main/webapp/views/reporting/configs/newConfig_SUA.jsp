<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script type="text/javascript">

    $(document).ready(function(){
		$('.add_group').bind("click",function(){
            var addGroupUrl = "<%=request.getContextPath()%>/alerts/addGroup/"+$('.group_name').val();
            $.ajax({
                url : addGroupUrl,
                processData : true,
                success : function(data) {
                    $(".group_selection").html(data);
                }
            })
        });
    });
</script>	
<div class="general_division">
	<h3 align="left"><label>Stock Unavailability Alert Configuration</label></h3>
	<div class="section">
		<span class="left"><label>Configuration Name:</label></span>
		<span class="right"><form:input path="alertConfigParams.alertConfig.alertConfigName" class="config_params"/></span>
	</div>
	<div class="section">
		<span class="left"><label>No. of Days:</label></span>
		<span class="right"><form:input path="alertConfigParams.numOfDays_SUA" class="config_params"/></span>
	</div>
</div>
<div class="group_selection">
	<div class="general_division">
		<h3 align="left"><label>Group Selection</label></h3>
		<div class="section">
            <span class="left"><label>Unassigned Groups</label></span>
            <span class="right">
                <select class="group_name">
                    <option value="-1">--Please Select</option>
                    <c:forEach var="group" items="${groups}">
                        <option value="${group.groupId}">${group.groupName}</option>
                    </c:forEach>
                </select>
                <input type="button" value="Add Group" class="add_group"/>
            </span>
        </div>
	</div>
</div>
<div class="button_div">
	 </br><input class="buttons" type="submit" value="Submit"/>
	 <input class="buttons" type="reset" value="Clear"/>
</div>
