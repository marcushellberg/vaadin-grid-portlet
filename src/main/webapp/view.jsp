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

<script src="https://cdn.vaadin.com/vaadin-components/latest/webcomponentsjs/webcomponents-lite.min.js"></script>
<link href="https://cdn.vaadin.com/vaadin-components/latest/vaadin-components/vaadin-components.html" rel="import">

<vaadin-grid selection-mode="multi">
    <table>
        <!-- Define the columns -->
        <col name="firstName" header-text="First Name">
        <col name="lastName" header-text="Last Name">
        <col name="company" header-text="Company">
        <col name="address" header-text="Address">
        <col name="city" header-text="City">
        <col name="zip" header-text="Zip">
    </table>
</vaadin-grid>

<script>
    document.addEventListener("WebComponentsReady", function () {

        var grid = document.querySelector("vaadin-grid");

        // Fetch some JSON data from a URL
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                if (xhr.status == 200) {
                    var json = JSON.parse(xhr.responseText);
                    grid.data.source = json;
                }
            }
        };
        xhr.open("GET", "/delegate/services/customers", true);
        xhr.send();
    });
</script>