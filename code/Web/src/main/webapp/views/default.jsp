<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <link rel="stylesheet" href="/styles/bis-styles.css" type="text/css" />
        <link rel="stylesheet" href="/styles/bis-panel.css" type="text/css" />
        <title>BIS</title>
    </head>
    <body>
        <div id="wrap">
            <tiles:insertAttribute name="header"/>
            <tiles:insertAttribute name="navigation"/>
            <div id="content-wrap">
                <div id="main">
                    <tiles:insertAttribute name="main-content"/>
                </div>
                <div id="sidebar">
                    <tiles:insertAttribute name="context-menu"/>
                    <tiles:insertAttribute name="quick-links"/>
                </div>
            </div>
            <tiles:insertAttribute name="footer"/>
        </div>
    </body>
</html>
