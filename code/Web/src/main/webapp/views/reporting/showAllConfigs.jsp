<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<script type="text/javascript">
   $(function() {
       $( "#alertConfigs_accordion" ).accordion({
            collapsible:true,
			active:false
       });
   });
</script>

<div class="general_division">
<h3 align="left"><label>Configurations for this Alert</label></h3>
<div align="left" id="alertConfigs_accordion">
<c:forEach var="alertConfig" items="${alertConfigList}">
	<h3>
	   <a href="">
		   Configuration: ${alertConfig.alertConfigName}
		   Parameters: ${alertConfig.alertParameters}
	   </a>
	</h3>
	<div>
	<TABLE  width="310px" border="1">
		<thead class="table_header">
			<TD>Group Name</TD>
			<TD>Group Text</TD>
		</thead>
		<c:forEach var="alertAssociation" items="${alertConfig.alertAssociations}">
		<tr>
			<td>${alertAssociation.group.groupName}</td>
			<td>${alertAssociation.group.groupText}</td>
		</tr>
		</c:forEach>
	 </TABLE>
	<a href="<%=request.getContextPath()%>/alerts/updateForm/${alertConfig.alertConfigId}">Update Configuration</a>
	 </div>
</c:forEach>
</div>
</div>