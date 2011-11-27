<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="nav">
  <ul>
    <li>
      <a href="<%=request.getContextPath()%>/item/createForm">Master</a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/procurement/createForm">Procurement</a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/sales/createForm">Sales</a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/inventory/show">Inventory</a>
    </li>
    <li>
      <a href="#">Reports</a>
    </li>
  </ul>
</div>
