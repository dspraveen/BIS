<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<script type="text/javascript">
	 function removeItem(item_code){
		$.ajax({
				url : "<%=request.getContextPath()%>/group/removeItem/"+item_code,
				processData : true,
				success : function(data) {
					$(".group_list").html(data);
				}
			})
    }

    function removeVendor(vendor_code){
		$.ajax({
				url : "<%=request.getContextPath()%>/group/removeVendor/"+vendor_code,
				processData : true,
				success : function(data) {
					$(".group_list").html(data);
				}
			})
    }

    function removeHawker(hawker_code){
		$.ajax({
				url : "<%=request.getContextPath()%>/group/removeHawker/"+hawker_code,
				processData : true,
				success : function(data) {
					$(".group_list").html(data);
				}
			})
    }
</script>

<div class="general_division">
   <h3 align="left"><label>Group Items</label></h3>
   <div align="center">
   <table  width="610px" border="1">
     <thead class="table_header">
        <TD>Item</TD>
        <TD>Vendor</TD>
        <TD>Hawker</TD>
     </thead>
     <c:forEach var="groupItemList" items="${group.groupItems}" varStatus="count">
        <tr>
        <td><a href="#" onclick="removeItem(${groupItemList.item.itemCode})">${groupItemList.item.itemName}</a></td>
        <td><a href="#" onclick="removeVendor(${groupItemList.vendor.vendorId})">${groupItemList.vendor.vendorName}</a></td>
        <td><a href="#" onclick="removeHawker(${groupItemList.hawker.hawkerId})">${groupItemList.hawker.hawkerName}</a></td>
        </tr>
     </c:forEach>
	 
   </table>
   </div>
</div>