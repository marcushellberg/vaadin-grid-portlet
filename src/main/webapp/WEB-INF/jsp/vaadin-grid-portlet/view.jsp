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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://cdn.vaadin.com/vaadin-components/latest/webcomponentsjs/webcomponents-lite.min.js"></script>
<link href="https://cdn.vaadin.com/vaadin-components/latest/vaadin-components/vaadin-components.html" rel="import">

<vaadin-grid selection-mode="multi">
    <table>
        <!-- Define the columns -->
        <col name="index" header-text="#" width="48">
        <col name="user.picture.thumbnail" width="54">
        <col name="user.name.first" header-text="First Name">
        <col name="user.name.last" header-text="Last Name">
        <col name="user.email" header-text="Email" flex>
    </table>
</vaadin-grid>

<script>
    // The Web Components polyfill introduces a custom event we can
    // use to determine when the custom elements are ready to be used
    document.addEventListener("WebComponentsReady", function () {

        // Reference to the grid element
        var grid = document.querySelector("vaadin-grid");

        // Fetch some JSON data from a URL
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                if (xhr.status == 200) {
                    var json = JSON.parse(xhr.responseText);

                    // Use the returned data array directly as the data source
                    // (keeping all the data source items in the browser's memory)
                    grid.data.source = json.results;
                }
            }
        };
        xhr.open("GET", "http://api.randomuser.me/?results=100", true);
        xhr.send();

        // Add a renderer for the index column
        grid.columns[0].renderer = function(cell) {
            cell.element.innerHTML = cell.row.index;
        };

        // Add a renderer for the picture column
        grid.columns[1].renderer = function(cell) {
            cell.element.innerHTML = '<img src="' + cell.data + '" style="width: 24px;">';
        }
    });
</script>