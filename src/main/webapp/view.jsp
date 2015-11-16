<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<script src="https://cdn.vaadin.com/vaadin-elements/0.3.0-beta12/webcomponentsjs/webcomponents-lite.min.js"></script>
<link href="https://cdn.vaadin.com/vaadin-elements/0.3.0-beta12/vaadin-elements/vaadin-elements.html" rel="import">

<vaadin-grid selection-mode="multi">
    <table>
        <col name="firstName" header-text="First Name" sortable>
        <col name="lastName" header-text="Last Name" sortable>
        <col name="orderProgress" header-text="Order progress">
        <col name="company" header-text="Company" sortable>
        <col name="address" header-text="Address" sortable>
        <col name="city" header-text="City" sortable>
        <col name="zip" header-text="Zip" sortable width="100">
    </table>
</vaadin-grid>
