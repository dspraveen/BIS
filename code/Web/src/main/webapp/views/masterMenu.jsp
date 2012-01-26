<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
  <h3>Master Menu</h3>
  <ul class="sidemenu">
    <li>
      <a href="<%=request.getContextPath()%>/item/createForm">Add New Item</a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/vendor/createForm">Add New Vendor</a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/hawker/createForm">Add New Hawker</a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/vendor/list">Vendor Details</a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/item/list">Item Details</a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/hawker/list">Hawker Details</a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/group/showGroups">Groups Administration</a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/alerts/showAlertsConfig">Alerts Administration</a>
    </li>
  </ul>
</div>