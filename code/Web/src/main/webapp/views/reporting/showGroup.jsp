<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

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
        <td>${groupItemList.item.itemName}</td>
        <td>${groupItemList.vendor.vendorName}</td>
        <td>${groupItemList.hawker.hawkerName}</td>
        </tr>
     </c:forEach>
   </table>
   </div>
</div>