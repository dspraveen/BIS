<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<script type="text/javascript">
    function validateForm(){
        if(!($('.group_name').val())){
            alert("Group Name Not Provided");
            return false;
        }
        return true;
    }

    $(document).ready(function(){

        $('.add_item').bind("click",function(){
            var addItemUrl = "<%=request.getContextPath()%>/group/addNewItem?itemCode="+$('.item_name').val();
            $.ajax({
                url : addItemUrl,
                processData : true,
                success : function(data) {
                    $(".group_list").html(data);
                }
            })
        });
		
		$('.add_vendor').bind("click",function(){
            var addVendorUrl = "<%=request.getContextPath()%>/group/addNewVendor?vendorId="+$('.vendor_name').val();
            $.ajax({
                url : addVendorUrl,
                processData : true,
                success : function(data) {
                    $(".group_list").html(data);
                }
            })
        });
		
		$('.add_hawker').bind("click",function(){
            var addHawkerUrl = "<%=request.getContextPath()%>/group/addNewHawker?hawkerId="+$('.hawker_name').val();
            $.ajax({
                url : addHawkerUrl,
                processData : true,
                success : function(data) {
                    $(".group_list").html(data);
                }
            })
        });
    });
</script>		

<form  method="POST" action="<%=request.getContextPath()%>/group/createNewGroup"  onsubmit="return validateForm();">
<div class="general_division">
    <div class="content_header">Create Group</div>
    <div class="section">
        <span class="left"><label>Group Name:</label></span>
        <span class="right"><form:input path="group.groupName" class="group_name"/></span>
    </div>
    <div class="section">
        <span class="left"><label>Group Description:</label></span>
        <span class="right"><form:input path="group.groupText"/></span>
    </div>
    <div class="section">
        <span class="left"><label>Unassigned Items:</label></span>
        <span class="right">
            <select class="item_name">
                <option value="-1">--Please Select</option>
                <c:forEach var="item" items="${items}">
                    <option value="${item.itemCode}">${item.itemName}</option>
                </c:forEach>
            </select>
			<input type="button" value="Add Item" class="add_item"/>
        </span>
    </div>
    <div class="section">
        <span class="left"><label>Unassigned Vendors:</label></span>
        <span class="right">
            <select class="vendor_name">
                <option value="-1">--Please Select</option>
                <c:forEach var="vendor" items="${vendors}">
                    <option value="${vendor.vendorId}">${vendor.vendorName}</option>
                </c:forEach>
            </select>
			<input type="button" value="Add Vendor" class="add_vendor"/>
        </span>
    </div>
    <div class="section">
        <span class="left"><label>Unassigned Hawkers:</label></span>
        <span class="right">
            <select class="hawker_name">
                <option value="-1">--Please Select</option>
                <c:forEach var="hawker" items="${hawkers}">
                    <option value="${hawker.hawkerId}">${hawker.hawkerName}</option>
                </c:forEach>
            </select>
			<input type="button" value="Add Hawker" class="add_hawker"/>
        </span>
    </div>
</div>
<div class="group_list">
</div>
<div class="button_div">
	 <input class="buttons" type="submit" value="Submit"/>
</div>
</form>