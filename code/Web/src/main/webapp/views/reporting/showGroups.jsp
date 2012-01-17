<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<form method="POST" action="<%=request.getContextPath()%>/hawker/create"  onsubmit="return validateForm();">
    <div class="general_division">
        <div class="content_header">Groups Administration</div>
    </div>
	<div id="group_list">
        <c:forEach var="groupItem" items="${groupList}" varStatus="counter">
            <ul class="group_menu">
                    <a href="#">${groupItem.groupName}</a>
            </ul>
        </c:forEach>
	</div>
	<div id="entity_list">
		<Table>
			<thead class="table_header">
				<td>Items</td>
				<td>Vendors</td>
				<td>Hawkers</td>
			</thead>
			<tr>
				<td>India Today English Item 1</td>
				<td>Bennet Colemann 1</td>
				<td>Hawker 1</td>
			</tr>
		</Table>
	</div>
</form>