<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="head">
  <ul>
    <li id="current">
      <a href="<%=request.getContextPath()%>/item/createForm"><span>Master</span></a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/procurement/createForm/purchase"><span>Procurement</span></a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/sales/createForm/sales"><span>Sales</span></a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/inventory/show"><span>Inventory</span></a>
    </li>
    <li>
      <a href="#"><span>Reports</span></a>
    </li>
  </ul>
</div>
