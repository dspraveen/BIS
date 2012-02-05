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
	
	function removeGroup(group_code){
		$.ajax({
			url : "<%=request.getContextPath()%>/alerts/removeGroup/"+group_code,
			processData : true,
			success : function(data) {
				$(".group_selection").html(data);
			}
		})
    }
</script>
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
   <h3 align="left"><label>Group List</label></h3>
   <div align="center">
   <table  width="610px" border="1">
     <thead class="table_header">
        <TD>Group Name</TD>
        <TD>Group Text</TD>
     </thead>
     <c:forEach var="alertAssociations" items="${alertConfigParams.alertConfig.alertAssociations}" varStatus="count">
        <tr>
        <td><a href="#" onclick="removeGroup(${alertAssociations.group.groupId})">${alertAssociations.group.groupName}</a></td>
        <td>${alertAssociations.group.groupText}</td>
        </tr>
     </c:forEach>	 
   </table>
   </div>
</div>