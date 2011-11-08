<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
  <h3>Sales Menu</h3>
  <ul class="sidemenu">
    <li>
      <a href="#">Add New Transaction</a>
    </li>
    <li>
      <a href="#">Transaction Details</a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/salesPayment/createForm">Create Payment</a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/salesPayment/list">Billing Details</a>
    </li>
  </ul>
</div>