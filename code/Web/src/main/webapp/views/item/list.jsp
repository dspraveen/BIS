<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script type="text/javascript">
    function submitForm(){
		$('#item_list').submit();	
    }
</script>
<div>
    <form method="POST" action="/item/list" id="item_list">
        <c:if test="${itemList.count != '0'}">
            <h1>Item Details</h1>
                <div class="section">
                    <span class="left"><label>Select Item:</label></span
                    <span class="right">
                        <form:select path="itemList.selectedItemCode" items="${itemList.items}" itemValue="itemCode" itemLabel="itemName" onChange="submitForm();"/>
                    </span>
                </div>
                <div class="section">
                    <span class="left"><label>Item Name:</label></span
                    <span class="right"><label>${itemList.selectedItem.itemName}</label></span>
                </div>
                <div class="section">
                    <span class="left"><label>Item Description:</label></span
                    <span class="right"><label>
                        <c:choose>
                            <c:when test="${itemList.selectedItem.description != ''}">
                                ${itemList.selectedItem.description}
                            </c:when>
                            <c:otherwise>
                                Not Provided
                            </c:otherwise>
                        </c:choose>
                    </label></span>
                </div>
                <div class="section">
                    <span class="left"><label>Item Type:</label></span
                    <span class="right"><label>${itemList.selectedItem.itemLife}</label></span>
                </div>
                <div class="section">
                    <span class="left"><label>Item Returnable:</label></span
                    <span class="right"><label>${itemList.selectedItem.returnable}</label></span>
                </div>
                <div class="section">
                    <span class="left"><a href="/item/updateForm/${itemList.selectedItem.itemCode}">Update Item</a></span>
                </div>
        </c:if>
        <c:if test="${itemList.count == '0'}">
            <h1>No items to show</h1>
        </c:if>
	</form>
</div>