<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<script type="text/javascript">
   $(function() {
       $( "#groups_accordion" ).accordion({
            collapsible:true,
			active:false
       });
   });
</script>
<div class="general_division">
	<div class="content_header">Groups Administration</div>
	<div id="groups_accordion">
		<c:forEach var="groupItem" items="${groupList}" varStatus="counter">
		    <h3>
			   <a href="">
				   Group Name: ${groupItem.groupName}
				   Desc: ${groupItem.groupText}
			   </a>
			</h3>
			<div>
			   <TABLE  width="510px" border="1">
				 <thead class="table_header">
					<TD>Item</TD>
					<TD>Vendor</TD>
					<TD>Hawker</TD>
				 </thead>
				 <c:forEach var="groupItemList" items="${groupItem.groupItems}" varStatus="count">
					<tr>
					<td>${groupItemList.item.itemName}</td>
					<td>${groupItemList.vendor.vendorName}</td>
					<td>${groupItemList.hawker.hawkerName}</td>
					</tr>
				 </c:forEach>
			   </TABLE>
			   <a href="<%=request.getContextPath()%>/group/updateGroup/${groupItem.groupId}">Update this Group</a>
			</div>
		</c:forEach>
	</div>
</div>
<div class="button_div">
	<span class="center"><a href="<%=request.getContextPath()%>/group/createGroup" class="buttons">Create New Group</a>
</div>
