<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/bis-styles.css" type="text/css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/bis-panel.css" type="text/css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/styles/jquery-ui-1.8.16.custom.css" type="text/css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/ThirdParty/jquery-1.6.4.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/ThirdParty/jquery-ui-1.8.16.custom.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/ThirdParty/jquery.overlay.min.js"></script>
        <title>BIS</title>
    </head>
    <body>
        <div id="wrap">
            <tiles:insertAttribute name="header"/>
            <tiles:insertAttribute name="navigation"/>
            <div id="content-wrap">
                <div id="sidebar">
                    <tiles:insertAttribute name="context-menu"/>
                    <tiles:insertAttribute name="quick-links"/>
                </div>
                <div id="main">
                    <tiles:insertAttribute name="main-content"/>
                </div>
            </div>
            <tiles:insertAttribute name="footer"/>
        </div>
    </body>
</html>
