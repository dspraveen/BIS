<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
  <h3>Inventory Menu</h3>
  <ul class="sidemenu">
    <li>
      <a href="<%=request.getContextPath()%>/inventory/show">Stock Details</a>
    </li>
    <li>
      <a href="<%=request.getContextPath()%>/inventory/showExpiredStock">Expired Stock Details</a>
    </li>
  </ul>
</div>