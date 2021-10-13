<%-- 
    Document   : ManagerViewFacility
    Created on : Sep 27, 2021, 4:29:22 PM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="app.facility.FacilityDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Manager Page</title>

        <!-- Popins Font -->
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700;800&display=swap"
            rel="stylesheet"
            />

        <!-- ion Icon -->
        <script
            type="module"
            src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"
        ></script>
        <script
            nomodule
            src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"
        ></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminPage1.css" />
    </head>
    <body>
        <main>
            <section class="showcase">
                <div class="container">
                    <div class="showcase-main">
                        <div class="showcase-header">
                            <div class="showcase-logo">
                                <a href="ShowFacilitiesController">
                                    <img
                                        src="https://cdn.discordapp.com/attachments/770804043794350100/888843339439407104/toolkit.png"
                                        alt=""
                                        />
                                </a>
                            </div>
                            <ul class="showcase-menu">
                                <!-- In those has 3 icon for 3 Page -->
                                <!-- If you want to icon is highlight you have to add class active for li[class = 'showcase-item'] -->
                                <li class="showcase-item" data-index="0">
                                    <a href="ShowFeedBackController" class="showcase-link">
                                        <ion-icon
                                            name="document-text-outline"
                                            class="no-active-mode"
                                            ></ion-icon>
                                        <ion-icon
                                            name="document-text"
                                            class="active-mode"
                                            ></ion-icon>
                                    </a>
                                    <span></span>
                                </li>
                                <li class="showcase-item" data-index="1">
                                    <a href="ShowUserController" class="showcase-link">
                                        <ion-icon
                                            name="people-outline"
                                            class="no-active-mode"
                                            ></ion-icon>
                                        <ion-icon name="people" class="active-mode"></ion-icon>
                                    </a>
                                    <span></span>
                                </li>
                                <li class="showcase-item active" data-index="2">
                                    <a href="ShowFacilitiesController" class="showcase-link">
                                        <ion-icon
                                            name="hammer-outline"
                                            class="no-active-mode"
                                            ></ion-icon>
                                        <ion-icon name="hammer" class="active-mode"></ion-icon>
                                    </a>
                                    <span></span>
                                </li>
                                <li class="showcase-item" data-index="3">
                                    <a href="ManagerNotification.jsp" class="showcase-link">
                                        <ion-icon
                                            name="notifications-outline"
                                            class="no-active-mode"
                                            ></ion-icon>
                                        <ion-icon
                                            name="notifications"
                                            class="active-mode"
                                            ></ion-icon>
                                    </a>
                                    <span></span>
                                </li>
                            </ul>
                            <div class="showcase-profile">
                                <div class="showcase-profile-image">
                                    <img
                                        src="${sessionScope.LOGIN_USER.image}"
                                        alt=""
                                        />
                                </div>
                                <a href="LogoutController">
                                    <ion-icon name="log-out-outline"></ion-icon>
                                </a>
                            </div>
                        </div>

                        <!-- in those have 3 Pages -->
                        <div class="showcase-content-list">

                            <!-- Facility -->
                            <div class="showcase-content-item active">
                                <div class="facility-heading">
                                    <div class="facility-heading-header">
                                        <h2 class="showcase-title">Facilities</h2>
                                        <form action="SearchFacilityController" class="showcase-form">
                                            <input
                                                type="text"
                                                name="search"
                                                value="${requestScope.SEARCH}"
                                                placeholder="Search Facilities..."
                                                />
                                            <button type="submit">
                                                <ion-icon name="search"></ion-icon>
                                            </button>
                                        </form>
                                    </div>
                                </div>

                                <div class="content-item-main facility-content-main">
                                    <div class="facility-item-heading">
                                        <div class="show-category-facility">
                                            <a href="#" class="${requestScope.STYLE_PIPE}" data-index="0">
                                                <ion-icon name="apps-sharp"></ion-icon>
                                            </a>
                                            <a href="#" class="${requestScope.STYLE_LIST}" data-index="1">
                                                <ion-icon name="list"></ion-icon>
                                            </a>
                                        </div>
                                    </div>

                                    <div class="content-item-main-list">
                                        <div class="content-item-main-item active">
                                            <!-- Pipe -->
                                            <div class="content-item-main-category-item facility-item-main ${requestScope.STYLE_PIPE}">
                                                <div class="pipe">
                                                    <!-- Avalaible -->
                                                    <div class="pipe-column">
                                                        <div class="pipe-heading">
                                                            <div class="pipe-title-wrapper">
                                                                <span class="done"></span>
                                                                <h2 class="pipe-title">Avalable</h2>
                                                            </div>
                                                            <div class="pipe-date">
                                                                Quantity
                                                                <ion-icon
                                                                    name="chevron-down-outline"
                                                                    ></ion-icon>
                                                            </div>
                                                        </div>
                                                        <div class="pipe-list">
                                                            <c:forEach var="facility" items="${sessionScope.FACILTIES_LIST_AVAILABLE}">
                                                                <div class="pipe-item">
                                                                    <div class="pipe-item-heading">
                                                                        <img
                                                                            src="${facility.image}"
                                                                            alt=""
                                                                            />
                                                                    </div>
                                                                    <div class="pipe-item-bottom">
                                                                        <div class="pipe-item-bottom-heading">
                                                                            <h2 class="pipe-item-title">
                                                                                ${facility.facilityName}
                                                                            </h2>
                                                                            <p class="pipe-item-desc">Quantity: ${facility.quantity}</p>
                                                                        </div>
                                                                        <div class="pipe-item-bottom-footer">
                                                                            <p class="pipe-item-desc pipe-item-date">
                                                                                Maintain Date: ${facility.maintenanceDate}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>

                                                    <!-- Unavalaible -->
                                                    <div class="pipe-column">
                                                        <div class="pipe-heading">
                                                            <div class="pipe-title-wrapper">
                                                                <span class="decline"></span>
                                                                <h2 class="pipe-title">Out Of Stock</h2>
                                                            </div>
                                                            <div class="pipe-date">
                                                                Maintain Date
                                                                <ion-icon
                                                                    name="chevron-down-outline"
                                                                    ></ion-icon>
                                                            </div>
                                                        </div>
                                                        <div class="pipe-list">
                                                            <c:forEach var="facility" items="${sessionScope.FACILTIES_LIST_UNAVAILABLE}">
                                                                <div class="pipe-item">
                                                                    <div class="pipe-item-heading">
                                                                        <img
                                                                            src="${facility.image}"
                                                                            alt=""
                                                                            />
                                                                    </div>
                                                                    <div class="pipe-item-bottom">
                                                                        <div class="pipe-item-bottom-heading">
                                                                            <h2 class="pipe-item-title">${facility.facilityName}</h2>
                                                                            <p class="pipe-item-desc-error">
                                                                                Out Of Stock
                                                                            </p>
                                                                        </div>
                                                                        <div class="pipe-item-bottom-footer">
                                                                            <p class="pipe-item-desc pipe-item-date">
                                                                                Maintain Date: ${facility.maintenanceDate}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <span class="pipe-line-break"></span>
                                                </div>
                                            </div>
                                            <!-- List -->
                                            <div class="content-item-main-category-item facility-item-main facility-list ${requestScope.STYLE_LIST}">
                                                <ul class="facility-list-heading">
                                                    <li class="facility-list-item ${requestScope.STYLE_LIST_ALL}" data-index="0">
                                                        All
                                                    </li>
                                                    <li class="facility-list-item" data-index="1">
                                                        Available
                                                    </li>
                                                    <li class="facility-list-item" data-index="2">
                                                        UnAvailable
                                                    </li>
                                                </ul>
                                                <div class="facility-list-showcase">
                                                    <!-- list All -->
                                                    <div class="facility-list-showcase-item active">
                                                        <div class="list">
                                                            <c:if test="${empty sessionScope.FACILTIES_LIST_ALL}">
                                                                <p class="showcase-desc">
                                                                    No result can be found ...
                                                                </p>
                                                            </c:if>
                                                            <c:forEach var="facility" items="${sessionScope.FACILTIES_LIST_ALL}">
                                                                <div class="pipe-item">
                                                                    <div class="pipe-item-heading">
                                                                        <img
                                                                            src="${facility.image}"
                                                                            alt=""
                                                                            />
                                                                    </div>
                                                                    <div class="pipe-item-bottom">
                                                                        <div class="pipe-item-bottom-heading">
                                                                            <h2 class="pipe-item-title">
                                                                                ${facility.facilityName}
                                                                            </h2>
                                                                            <c:if test="${facility.statusID eq 'UNAV'}">
                                                                                <p class="pipe-item-desc-error">
                                                                                    Out Of Stock
                                                                                </p>
                                                                            </c:if>
                                                                            <c:if test="${facility.statusID eq 'AV'}">
                                                                                <p class="pipe-item-desc">Quantity: ${facility.quantity}</p>
                                                                            </c:if>
                                                                        </div>
                                                                        <div class="pipe-item-bottom-footer">
                                                                            <p class="pipe-item-desc pipe-item-date">
                                                                                Maintain Date: ${facility.maintenanceDate}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <!-- list Available -->
                                                    <div class="facility-list-showcase-item">
                                                        <div class="list">
                                                            <c:if test="${empty sessionScope.FACILTIES_LIST_AVAILABLE}">
                                                                <p class="showcase-desc">
                                                                    No result can be found ...
                                                                </p>
                                                            </c:if>
                                                            <c:forEach var="facility" items="${sessionScope.FACILTIES_LIST_AVAILABLE}">
                                                                <div class="pipe-item">
                                                                    <div class="pipe-item-heading">
                                                                        <img
                                                                            src="${facility.image}"
                                                                            alt=""
                                                                            />
                                                                    </div>
                                                                    <div class="pipe-item-bottom">
                                                                        <div class="pipe-item-bottom-heading">
                                                                            <h2 class="pipe-item-title">
                                                                                ${facility.facilityName}
                                                                            </h2>
                                                                            <p class="pipe-item-desc">Quantity: ${facility.quantity}</p>
                                                                        </div>
                                                                        <div class="pipe-item-bottom-footer">
                                                                            <p class="pipe-item-desc pipe-item-date">
                                                                                Maintain Date: ${facility.maintenanceDate}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <!-- list UnAvailable -->
                                                    <div class="facility-list-showcase-item">
                                                        <div class="list">
                                                            <c:if test="${empty sessionScope.FACILTIES_LIST_UNAVAILABLE}">
                                                                <p class="showcase-desc">
                                                                    No result can be found ...
                                                                </p>
                                                            </c:if>
                                                            <c:forEach var="facility" items="${sessionScope.FACILTIES_LIST_UNAVAILABLE}">
                                                                <div class="pipe-item">
                                                                    <div class="pipe-item-heading">
                                                                        <img
                                                                            src="${facility.image}"
                                                                            alt=""
                                                                            />
                                                                    </div>
                                                                    <div class="pipe-item-bottom">
                                                                        <div class="pipe-item-bottom-heading">
                                                                            <h2 class="pipe-item-title">
                                                                                ${facility.facilityName}
                                                                            </h2>
                                                                            <p class="pipe-item-desc-error">
                                                                                Out Of Stock
                                                                            </p>
                                                                        </div>
                                                                        <div class="pipe-item-bottom-footer">
                                                                            <p class="pipe-item-desc pipe-item-date">
                                                                                Maintain Date: ${facility.maintenanceDate}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>

        <script src="${pageContext.request.contextPath}/js/adminPage.js"></script>
        <script src="${pageContext.request.contextPath}/js/ManagerFacility.js"></script>
    </body>
</html>

