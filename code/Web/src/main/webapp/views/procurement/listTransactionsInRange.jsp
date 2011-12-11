<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="general_division">
<h3><label>Procurement Transactions For Vendor</label></h3>
<div id="procurement_transactions_accordion">
    <c:forEach var="procurementTransaction" items="${procurementTransactions}">
       <h3>
           <a href="">
               <label>Vendor : ${procurementTransaction.vendor.vendorName}</label>
               <label>Date : <fmt:formatDate pattern="dd-MM-yyyy" value="${procurementTransaction.date}"/></label>
               <label>Type : ${procurementTransaction.transactionType}</label>
           </a>
        <h3>
        <div>
           <TABLE  width="610px" border="1">
             <thead class="table_header">
                <TD>Item</TD>
                <TD>Date Of Publishing</TD>
                <TD>Price Per Item</TD>
                <TD>Qty</TD>
                <TD>Amount</TD>
             </thead>
               <c:forEach var="transactionDetail" items="${procurementTransaction.transactionDetails}">
                   <tr>
                     <td>${transactionDetail.item.itemName}</td>
                     <td><fmt:formatDate pattern="dd-MM-yyyy" value="${transactionDetail.dateOfPublishing}"/></td>
                     <td>${transactionDetail.netPrice}</td>
                     <td>${transactionDetail.quantity}</td>
                     <td>${transactionDetail.amount}</td>
                   </tr>
               </c:forEach>
           </TABLE>
           <a href="<%=request.getContextPath()%>/procurement/updateForm/${procurementTransaction.transactionId}"> Update this transaction</a>
        </div>
    </c:forEach>
</div>
</div>
<script>
   $(function() {
       $( "#procurement_transactions_accordion" ).accordion({
            collapsible:true,
			active:false
       });
   });
</script>
