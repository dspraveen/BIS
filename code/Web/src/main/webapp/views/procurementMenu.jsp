<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
  <h3>Procurement Menu</h3>
  <ul class="sidemenu">
    <li>
      <a href="<%=request.getContextPath()%>/procurement/createForm">New Transaction</a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/procurement/list">Transaction Details</a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>//procurementBilling/billSelectVendor">Generate Bill</a>
    </li>
    <li>
      <a href="#">Billing Details</a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/procurementPayment/createProcurementPayment">Create Payment</a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/procurementPayment/list">Payment Details</a>
    </li>
  </ul>
</div>