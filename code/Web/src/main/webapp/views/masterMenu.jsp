<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
  <h3>Master Menu</h3>
  <ul class="sidemenu">
    <li>
      <a href="#">Add New Vendor</a>
    </li>
    <li>
      <a href="/item/createForm">Add New Item</a>
    </li>
    <li>
      <a href="#">Add New Hawker</a>
    </li>
    <li>
      <a href="#">Vendor Details</a>
    </li>
    <li>
      <a href="/item/list">Item Details</a>
    </li>
    <li>
      <a href="#">Hawker Details</a>
    </li>
  </ul>
</div>