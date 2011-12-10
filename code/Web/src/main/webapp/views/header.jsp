<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="header">
  <h1 id="logo-text">
    <a href="<%=request.getContextPath()%>/home" title="">BIS</a>
  </h1>
  <div id="header-links">
    <p>
        <a href="#">Settings</a>|
        <a href="#">Profile</a>
     </p>
  </div>
</div>