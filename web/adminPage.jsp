<%-- 
    Document   : adminPage
    Created on : Oct 6, 2021, 4:40:25 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminPageDetailModal.css" />
    </head>
    <body>
        <!-- if you want to open modal, you add class for div[class = 'feedbackDetail'] value: 'open' -->
        <div class="feedbackDetail ${requestScope.flag}">
            <div class="modal feedbackDetail-modal">
                <div class="feedbackDetail-main">
                    <div class="detail-main">
                        <div class="detail-header">
                            <div class="all-wrapper">
                                <c:forEach var="feedbackDetail" items="${sessionScope.LIST_DETAIL}">
                                    <c:if test="${feedbackDetail.image ne ''}">
                                        <img
                                            src="data:image/jpg/png;base64,${feedbackDetail.image}"
                                            alt=""
                                            />
                                    </c:if>
                                </c:forEach>

                            </div>
                        </div>
                        <div class="feedback"> 
                            <div class="feedback-header">
                                <p>Feedback ID</p>
                                <p>Email</p>
                                <p>Date</p>
                                <p>Status</p>
                            </div>
                            <div class="feedback-info">
                                <p>${param.feedbackID}${requestScope.feedbackID}</p>
                                <p>${param.email}${requestScope.email}</p>
                                <p>${param.date}${requestScope.date}</p>
                                <button class="status-btn-${requestScope.statusID}">${requestScope.statusName}</button>
                            </div>
                        </div>
                        <c:forEach var="feedbackDetail" items="${sessionScope.LIST_DETAIL}">
                            <div class="detail-items ${requestScope.CLASS_NAME}">
                                <div class="detail-wrapper">
                                    <div class="detail-info">
                                        <p class="device">${feedbackDetail.deviceName}</p>
                                        <p>Room ${feedbackDetail.location}</p>
                                        <p>Quantity: ${feedbackDetail.quanity}</p>
                                        <p>Reason: ${feedbackDetail.reason}</p>
                                    </div>
                                    <div class="employee-name">
                                        <div class="employee-name-heading">
                                            <p>Employee Name:</p>
                                            <p class="employee-name-edit">${feedbackDetail.employeeName}</p>
                                        </div>
                                        <form action="AssignController">
                                            <div class="assign-wrapper">
                                                <select name="employee" id="" class="form-select" required>
                                                    <option value="" selected hidden>Choose employee name</option>
                                                    <c:forEach var="employee" items="${sessionScope.EMPLOYEE_LIST}">
                                                        <option value="${employee.userID}">${employee.fullName}</option>
                                                    </c:forEach>
                                                </select>
                                                <div class="assign">
                                                    <button type="submit" class="icon-block">
                                                        <ion-icon
                                                            name="pencil-outline"
                                                            class="form-select"
                                                            ></ion-icon>
                                                    </button>
                                                    </a>
                                                </div>
                                                <input type="hidden" name="feedbackDetailID" value="${feedbackDetail.feedbackDetailID}"/>
                                                <input type="hidden" name="feedbackID" value="${param.feedbackID}"/>
                                                <input type="hidden" name="email" value="${param.email}"/>
                                                <input type="hidden" name="date" value="${param.date}"/>
                                                <input type="hidden" name="statusName" value="${param.statusName}"/>
                                                <input type="hidden" name="statusID" value="${param.statusID}"/>
                                                <input type="hidden" name="stylePipe" value="${requestScope.STYLE_PIPE}"/>
                                                <input type="hidden" name="styleList" value="${requestScope.STYLE_LIST}"/>
                                                <input type="hidden" name="style_list_category_all" value="${requestScope.STYLE_LIST_ALL}"/>
                                                <input type="hidden" name="style_list_category_pending" value="${requestScope.STYLE_LIST_PENDING}"/>
                                                <input type="hidden" name="style_list_category_onGoing" value="${requestScope.STYLE_LIST_ONGOING}"/>
                                            </div>
                                        </form>
                                    </div>
                                    <a href="DeclineFeedbackDetailController?feedbackDetailID=${feedbackDetail.feedbackDetailID}&feedbackID=${param.feedbackID}${requestScope.feedbackID}&statusID=${requestScope.statusID}&statusName=${requestScope.statusName}&email=${param.email}${requestScope.email}&date=${param.date}${requestScope.date}&flag=true" class="detail-links" onclick="return confirm('Do you really want to inactivate ?')">
                                        <ion-icon name="ban"></ion-icon>
                                    </a>
                                </div>
                                <div class="description">
                                    <p class="des">Description:</p>
                                    <p class="content">
                                        ${feedbackDetail.description}
                                    </p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <main>
            <section class="showcase">
                <div class="container">
                    <div class="showcase-main">
                        <div class="showcase-header">
                            <div class="showcase-logo">
                                <a href="ShowFeedBackController">
                                    <img
                                        src="https://cdn.discordapp.com/attachments/770804043794350100/888843339439407104/toolkit.png"
                                        alt=""
                                        />
                                </a>
                            </div>
                            <ul class="showcase-menu">
                                <!-- In those has 3 icon for 3 Page -->
                                <!-- If you want to icon is highlight you have to add class active for li[class = 'showcase-item'] -->
                                <li class="showcase-item active" data-index="0">
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
                                <li class="showcase-item" data-index="2">
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
                                    <c:set var="avatar" value="${sessionScope.LOGIN_USER.image}"></c:set>
                                    <c:choose>
                                        <c:when test="${fn:startsWith(avatar, 'http')}">
                                            <img
                                                src="${sessionScope.LOGIN_USER.image}"
                                                alt=""
                                                />
                                        </c:when>
                                        <c:otherwise>
                                            <img
                                                src="data:image/jpg/png;base64,${sessionScope.LOGIN_USER.image}"
                                                alt=""
                                                />
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <a href="LogoutController">
                                    <ion-icon name="log-out-outline"></ion-icon>
                                </a>
                            </div>
                        </div>

                        <!-- in those have 3 Pages -->
                        <div class="showcase-content-list">
                            <!-- If you want to Page is show for user you only need to add class 'active' for div[class="showcase-content-item"]  -->

                            <!-- Feedback -->
                            <div class="showcase-content-item active">
                                <div class="showcase-content-item-heading">
                                    <form action="SearchFeedbackController" class="showcase-form">
                                        <input
                                            type="text"
                                            name="search"
                                            value="${requestScope.SEARCH}"
                                            placeholder="Search project..."
                                            />
                                        <button type="submit">
                                            <ion-icon name="search"></ion-icon>
                                        </button>
                                    </form>
                                    <div class="showcase-title-wrapper">
                                        <h2 class="showcase-title">Welcome back, ${sessionScope.LOGIN_USER.fullName}!</h2>
                                        <p class="showcase-desc">
                                            You have <span>${requestScope.COUNT} tasks</span> to complete
                                        </p>
                                    </div>
                                </div>
                                <div class="content-item-main">
                                    <div class="content-item-heading">
                                        <ul class="content-item-navigation">
                                            <li class="navigation-item ${requestScope.STYLE_TASK}" data-index="0">
                                                <a href="#">Tasks</a>
                                            </li>
                                            <li class="navigation-item ${requestScope.STYLE_COMMENT}" data-index="1">
                                                <a href="#">Comments</a>
                                            </li>
                                            <span class="navigation-scroll-bar"></span>
                                        </ul>
                                        <div class="content-item-category show">
                                            <a href="#" class="${requestScope.STYLE_PIPE}" data-index="0">
                                                <ion-icon name="apps-sharp"></ion-icon>
                                            </a>
                                            <a href="#" class="${requestScope.STYLE_LIST}" data-index="1">
                                                <ion-icon name="list"></ion-icon>
                                            </a>
                                        </div>
                                    </div>

                                    <div class="content-item-main-list">
                                        <!-- Tasks -->
                                        <div
                                            class="
                                            content-item-main-item
                                            facility-item-main-item
                                            ${requestScope.STYLE_TASK}
                                            "
                                            data-index="1"
                                            >
                                            <!-- Pipe -->
                                            <div
                                                class="
                                                content-item-main-category-item
                                                feedback-item-main-category-item
                                                ${requestScope.STYLE_PIPE}
                                                "
                                                >
                                                <div class="pipe">
                                                    <div class="pipe-column">
                                                        <div class="pipe-heading">
                                                            <div class="pipe-title-wrapper">
                                                                <span class="pending"></span>
                                                                <h2 class="pipe-title">Pending</h2>
                                                            </div>
                                                            <div class="pipe-date">
                                                                Date Added
                                                                <ion-icon
                                                                    name="chevron-down-outline"
                                                                    ></ion-icon>
                                                            </div>
                                                        </div>
                                                        <div class="pipe-list">
                                                            <c:forEach var="feedback" items="${sessionScope.FEEDBACK_LIST_PENDING}">
                                                                <a href="ShowEmployeeActiveController?feedbackID=${feedback.feedbackID}&email=${feedback.email}&date=${feedback.date}&statusID=${feedback.statusId}&statusName=${feedback.statusName}&style_flag=pipe&search=${requestScope.SEARCH}">
                                                                    <div class="pipe-item">
                                                                        <div class="pipe-item-heading">
                                                                            <div class="pipe-item-title-wrapper">
                                                                                <h3 class="pipe-item-title">
                                                                                    Feedback ${feedback.feedbackID}
                                                                                </h3>
                                                                                <p class="pipe-item-desc">
                                                                                    <strong>Name:</strong> ${feedback.fullName}
                                                                                </p>
                                                                            </div>
                                                                            <div class="pipe-item-date">
                                                                                ${feedback.date}
                                                                            </div>
                                                                        </div>
                                                                        <div class="pipe-item-bottom">
                                                                            <p class="pipe-bottom-item">
                                                                                <strong>Send by</strong>
                                                                                ${feedback.email}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </a>
                                                            </c:forEach>
                                                            <c:if test="${empty sessionScope.FEEDBACK_LIST_PENDING}">
                                                                <p class="pipe-item-date">No result can be found ...</p>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                    <div class="pipe-column">
                                                        <div class="pipe-heading">
                                                            <div class="pipe-title-wrapper">
                                                                <span class="onGoing"></span>
                                                                <h2 class="pipe-title">On-going</h2>
                                                            </div>
                                                            <div class="pipe-date">
                                                                Date Added
                                                                <ion-icon
                                                                    name="chevron-down-outline"
                                                                    ></ion-icon>
                                                            </div>
                                                        </div>
                                                        <div class="pipe-list">
                                                            <c:forEach var="feedback" items="${sessionScope.FEEDBACK_LIST_FIXING}">
                                                                <a href="ShowEmployeeActiveController?feedbackID=${feedback.feedbackID}&email=${feedback.email}&date=${feedback.date}&statusID=${feedback.statusId}&statusName=${feedback.statusName}&style_flag=pipe&search=${requestScope.SEARCH}">
                                                                    <div class="pipe-item">
                                                                        <div class="pipe-item-heading">
                                                                            <div class="pipe-item-title-wrapper">
                                                                                <h3 class="pipe-item-title">
                                                                                    Feedback ${feedback.feedbackID}
                                                                                </h3>
                                                                                <p class="pipe-item-desc">
                                                                                    <strong>Name:</strong> ${feedback.fullName}
                                                                                </p>
                                                                            </div>
                                                                            <div class="pipe-item-date">
                                                                                ${feedback.date}
                                                                            </div>
                                                                        </div>
                                                                        <div class="pipe-item-bottom">
                                                                            <p class="pipe-bottom-item">
                                                                                <strong>Send by</strong>
                                                                                ${feedback.email}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </a>
                                                            </c:forEach>
                                                            <c:if test="${empty sessionScope.FEEDBACK_LIST_FIXING}">
                                                                <p class="pipe-item-date">No result can be found ...</p>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                    <div class="pipe-column">
                                                        <div class="pipe-heading">
                                                            <div class="pipe-title-wrapper">
                                                                <span class="decline"></span>
                                                                <h2 class="pipe-title">Decline</h2>
                                                            </div>
                                                            <div class="pipe-date">
                                                                Date Added
                                                                <ion-icon
                                                                    name="chevron-down-outline"
                                                                    ></ion-icon>
                                                            </div>
                                                        </div>
                                                        <div class="pipe-list">
                                                            <c:forEach var="feedback" items="${sessionScope.FEEDBACK_LIST_DENY}">
                                                                <a href="ShowEmployeeActiveController?feedbackID=${feedback.feedbackID}&email=${feedback.email}&date=${feedback.date}&statusID=${feedback.statusId}&statusName=${feedback.statusName}&style_flag=pipe&search=${requestScope.SEARCH}">
                                                                    <div class="pipe-item">
                                                                        <div class="pipe-item-heading">
                                                                            <div class="pipe-item-title-wrapper">
                                                                                <h3 class="pipe-item-title">
                                                                                    Feedback ${feedback.feedbackID}
                                                                                </h3>
                                                                                <p class="pipe-item-desc">
                                                                                    <strong>Name:</strong> ${feedback.fullName}
                                                                                </p>
                                                                            </div>
                                                                            <div class="pipe-item-date">
                                                                                ${feedback.date}
                                                                            </div>
                                                                        </div>
                                                                        <div class="pipe-item-bottom">
                                                                            <p class="pipe-bottom-item">
                                                                                <strong>Send by</strong>
                                                                                ${feedback.email}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </a>
                                                            </c:forEach>
                                                            <c:if test="${empty sessionScope.FEEDBACK_LIST_DENY}">
                                                                <p class="pipe-item-date">No result can be found ...</p>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                    <div class="pipe-column">
                                                        <div class="pipe-heading">
                                                            <div class="pipe-title-wrapper">
                                                                <span class="done"></span>
                                                                <h2 class="pipe-title">Done</h2>
                                                            </div>
                                                            <div class="pipe-date">
                                                                Date Added
                                                                <ion-icon
                                                                    name="chevron-down-outline"
                                                                    ></ion-icon>
                                                            </div>
                                                        </div>
                                                        <div class="pipe-list">
                                                            <c:forEach var="feedback" items="${sessionScope.FEEDBACK_LIST_DONE}">
                                                                <a href="ShowEmployeeActiveController?feedbackID=${feedback.feedbackID}&email=${feedback.email}&date=${feedback.date}&statusID=${feedback.statusId}&statusName=${feedback.statusName}&style_flag=pipe&search=${requestScope.SEARCH}">
                                                                    <div class="pipe-item">
                                                                        <div class="pipe-item-heading">
                                                                            <div class="pipe-item-title-wrapper">
                                                                                <h3 class="pipe-item-title">
                                                                                    Feedback ${feedback.feedbackID}
                                                                                </h3>
                                                                                <p class="pipe-item-desc">
                                                                                    <strong>Name:</strong> ${feedback.fullName}
                                                                                </p>
                                                                            </div>
                                                                            <div class="pipe-item-date">
                                                                                ${feedback.date}
                                                                            </div>
                                                                        </div>
                                                                        <div class="pipe-item-bottom">
                                                                            <p class="pipe-bottom-item">
                                                                                <strong>Send by</strong>
                                                                                ${feedback.email}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </a>
                                                            </c:forEach>
                                                            <c:if test="${empty sessionScope.FEEDBACK_LIST_DONE}">
                                                                <p class="pipe-item-date">No result can be found ...</p>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- List -->
                                            <div
                                                class="
                                                content-item-main-category-item
                                                feedback-item-main-category-item
                                                list
                                                ${requestScope.STYLE_LIST}
                                                "
                                                >
                                                <ul class="list-heading">
                                                    <li class="list-item ${requestScope.STYLE_LIST_ALL}" data-index="0">All</li>
                                                    <li class="list-item ${requestScope.STYLE_LIST_PENDING}" data-index="1">Pending</li>
                                                    <li class="list-item ${requestScope.STYLE_LIST_ONGOING}" data-index="2">On-Going</li>
                                                    <li class="list-item ${requestScope.STYLE_LIST_DECLINE}" data-index="3">Decline</li>
                                                    <li class="list-item ${requestScope.STYLE_LIST_DONE}" data-index="4">Done</li>
                                                </ul>
                                                <div class="list-showcase">
                                                    <!-- list All -->
                                                    <div class="list-showcase-item ${requestScope.STYLE_LIST_ALL}">
                                                        <div class="pipe-list">
                                                            <c:if test="${empty sessionScope.FEEDBACK_LIST_ALL}">
                                                                <p class="showcase-desc">
                                                                    No result can be found ...
                                                                </p>
                                                            </c:if>
                                                            <c:forEach var="feedback" items="${sessionScope.FEEDBACK_LIST_ALL}">
                                                                <a href="ShowEmployeeActiveController?feedbackID=${feedback.feedbackID}&email=${feedback.email}&date=${feedback.date}&statusID=${feedback.statusId}&statusName=${feedback.statusName}&style_flag=list&style_list_category=all&search=${requestScope.SEARCH}">
                                                                    <div class="pipe-item">
                                                                        <div class="pipe-item-heading">
                                                                            <div class="pipe-item-title-wrapper">
                                                                                <h3 class="pipe-item-title">
                                                                                    Feedback ${feedback.feedbackID}
                                                                                </h3>
                                                                                <p class="pipe-item-desc">
                                                                                    <strong>Name:</strong> ${feedback.fullName}
                                                                                </p>
                                                                            </div>
                                                                            <div class="pipe-item-date">
                                                                                ${feedback.date}
                                                                            </div>
                                                                        </div>
                                                                        <div class="pipe-item-bottom">
                                                                            <p class="pipe-bottom-item">
                                                                                <strong>Send by</strong>
                                                                                ${feedback.email}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </a>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <!-- list pending -->
                                                    <div class="list-showcase-item ${requestScope.STYLE_LIST_PENDING}">
                                                        <div class="pipe-list">
                                                            <c:if test="${empty sessionScope.FEEDBACK_LIST_PENDING}">
                                                                <p class="showcase-desc">
                                                                    No result can be found ...
                                                                </p>
                                                            </c:if>
                                                            <c:forEach var="feedback" items="${sessionScope.FEEDBACK_LIST_PENDING}">
                                                                <a href="ShowEmployeeActiveController?feedbackID=${feedback.feedbackID}&email=${feedback.email}&date=${feedback.date}&statusID=${feedback.statusId}&statusName=${feedback.statusName}&style_flag=list&style_list_category=pending&search=${requestScope.SEARCH}">
                                                                    <div class="pipe-item">
                                                                        <div class="pipe-item-heading">
                                                                            <div class="pipe-item-title-wrapper">
                                                                                <h3 class="pipe-item-title">
                                                                                    Feedback ${feedback.feedbackID}
                                                                                </h3>
                                                                                <p class="pipe-item-desc">
                                                                                    <strong>Name:</strong> ${feedback.fullName}
                                                                                </p>
                                                                            </div>
                                                                            <div class="pipe-item-date">
                                                                                ${feedback.date}
                                                                            </div>
                                                                        </div>
                                                                        <div class="pipe-item-bottom">
                                                                            <p class="pipe-bottom-item">
                                                                                <strong>Send by</strong>
                                                                                ${feedback.email}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </a>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <!-- list on-going -->
                                                    <div class="list-showcase-item ${requestScope.STYLE_LIST_ONGOING}">
                                                        <div class="pipe-list">
                                                            <c:if test="${empty sessionScope.FEEDBACK_LIST_FIXING}">
                                                                <p class="showcase-desc">
                                                                    No result can be found ...
                                                                </p>
                                                            </c:if>
                                                            <c:forEach var="feedback" items="${sessionScope.FEEDBACK_LIST_FIXING}">
                                                                <a href="ShowEmployeeActiveController?feedbackID=${feedback.feedbackID}&email=${feedback.email}&date=${feedback.date}&statusID=${feedback.statusId}&statusName=${feedback.statusName}&style_flag=list&style_list_category=onGoing&search=${requestScope.SEARCH}">
                                                                    <div class="pipe-item">
                                                                        <div class="pipe-item-heading">
                                                                            <div class="pipe-item-title-wrapper">
                                                                                <h3 class="pipe-item-title">
                                                                                    Feedback ${feedback.feedbackID}
                                                                                </h3>
                                                                                <p class="pipe-item-desc">
                                                                                    <strong>Name:</strong> ${feedback.fullName}
                                                                                </p>
                                                                            </div>
                                                                            <div class="pipe-item-date">
                                                                                ${feedback.date}
                                                                            </div>
                                                                        </div>
                                                                        <div class="pipe-item-bottom">
                                                                            <p class="pipe-bottom-item">
                                                                                <strong>Send by</strong>
                                                                                ${feedback.email}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </a>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <!-- list decline -->
                                                    <div class="list-showcase-item ${requestScope.STYLE_LIST_DECLINE}">
                                                        <div class="pipe-list">
                                                            <c:if test="${empty sessionScope.FEEDBACK_LIST_DENY}">
                                                                <p class="showcase-desc">
                                                                    No result can be found ...
                                                                </p>
                                                            </c:if>
                                                            <c:forEach var="feedback" items="${sessionScope.FEEDBACK_LIST_DENY}">
                                                                <a href="ShowEmployeeActiveController?feedbackID=${feedback.feedbackID}&email=${feedback.email}&date=${feedback.date}&statusID=${feedback.statusId}&statusName=${feedback.statusName}&style_flag=list&style_list_category=decline&search=${requestScope.SEARCH}">
                                                                    <div class="pipe-item">
                                                                        <div class="pipe-item-heading">
                                                                            <div class="pipe-item-title-wrapper">
                                                                                <h3 class="pipe-item-title">
                                                                                    Feedback ${feedback.feedbackID}
                                                                                </h3>
                                                                                <p class="pipe-item-desc">
                                                                                    <strong>Name:</strong> ${feedback.fullName}
                                                                                </p>
                                                                            </div>
                                                                            <div class="pipe-item-date">
                                                                                ${feedback.date}
                                                                            </div>
                                                                        </div>
                                                                        <div class="pipe-item-bottom">
                                                                            <p class="pipe-bottom-item">
                                                                                <strong>Send by</strong>
                                                                                ${feedback.email}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </a>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <!-- list done -->
                                                    <div class="list-showcase-item ${requestScope.STYLE_LIST_DONE}">
                                                        <div class="pipe-list">
                                                            <c:if test="${empty sessionScope.FEEDBACK_LIST_DONE}">
                                                                <p class="showcase-desc">
                                                                    No result can be found ...
                                                                </p>
                                                            </c:if>
                                                            <c:forEach var="feedback" items="${sessionScope.FEEDBACK_LIST_DONE}">
                                                                <a href="ShowEmployeeActiveController?feedbackID=${feedback.feedbackID}&email=${feedback.email}&date=${feedback.date}&statusID=${feedback.statusId}&statusName=${feedback.statusName}&style_flag=list&style_list_category=done&search=${requestScope.SEARCH}">
                                                                    <div class="pipe-item">
                                                                        <div class="pipe-item-heading">
                                                                            <div class="pipe-item-title-wrapper">
                                                                                <h3 class="pipe-item-title">
                                                                                    Feedback ${feedback.feedbackID}
                                                                                </h3>
                                                                                <p class="pipe-item-desc">
                                                                                    <strong>Name:</strong> ${feedback.fullName}
                                                                                </p>
                                                                            </div>
                                                                            <div class="pipe-item-date">
                                                                                ${feedback.date}
                                                                            </div>
                                                                        </div>
                                                                        <div class="pipe-item-bottom">
                                                                            <p class="pipe-bottom-item">
                                                                                <strong>Send by</strong>
                                                                                ${feedback.email}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </a>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Comments -->
                                        <div
                                            class="content-item-main-item facility-item-main-item ${requestScope.STYLE_COMMENT}" data-index="2"
                                            >
                                            <div class="comments-wrapper">
                                                <div class="comment-list-feedback">
                                                    <div class="pipe-column">
                                                        <div class="pipe-list">
                                                            <c:forEach var="response" items="${sessionScope.FEEDBACK_RESPONE_LIST}" varStatus="counter">
                                                                <c:choose>
                                                                    <c:when test="${requestScope.RESPONE_ACTIVE == null && counter.count == 1}">
                                                                        <a href="ShowFeedbackResponeDetailForManagerController?feedback_response_id=${response.feedbackID}&style_list=comment">
                                                                            <div class="pipe-comment-item active">
                                                                                <div class="pipe-item-heading">
                                                                                    <div class="pipe-item-title-wrapper">
                                                                                        <h3 class="pipe-item-title">
                                                                                            Feedback ${response.feedbackID}
                                                                                        </h3>
                                                                                    </div>
                                                                                    <div class="pipe-item-date">
                                                                                        ${response.date}
                                                                                    </div>
                                                                                </div>
                                                                                <div class="pipe-item-bottom">
                                                                                    <p class="pipe-bottom-item">
                                                                                        <strong>Send by</strong>
                                                                                        ${response.email}
                                                                                    </p>
                                                                                    <c:if test="${requestScope.COMPLETED_ITEM eq true}">
                                                                                        <div class="pipe-item-bottom-form-wrapper">
                                                                                            <form action="UpdateFeedbackDoneController" class="pipe-bottom-form">
                                                                                                <input type="hidden" name="feedbackID" value="${response.feedbackID}"/>
                                                                                                <button type="submit" class="btn--done">
                                                                                                    <ion-icon
                                                                                                        name="checkbox-outline"
                                                                                                        ></ion-icon>
                                                                                                </button>
                                                                                            </form>
                                                                                            <form action="UpdateFeedbackDeclineController" class="pipe-bottom-form">
                                                                                                <input type="hidden" name="feedbackID" value="${response.feedbackID}"/>
                                                                                                <button
                                                                                                    type="submit"
                                                                                                    class="btn--decline"
                                                                                                    >
                                                                                                    <ion-icon
                                                                                                        name="close-circle-outline"
                                                                                                        ></ion-icon>
                                                                                                </button>
                                                                                            </form>
                                                                                        </div>
                                                                                    </c:if>
                                                                                </div>
                                                                            </div>
                                                                        </a>
                                                                    </c:when>
                                                                    <c:when test="${response.feedbackID eq requestScope.RESPONE_ACTIVE}">
                                                                        <a href="ShowFeedbackResponeDetailForManagerController?feedback_response_id=${response.feedbackID}&style_list=comment">
                                                                            <div class="pipe-comment-item active">
                                                                                <div class="pipe-item-heading">
                                                                                    <div class="pipe-item-title-wrapper">
                                                                                        <h3 class="pipe-item-title">
                                                                                            Feedback ${response.feedbackID}
                                                                                        </h3>
                                                                                    </div>
                                                                                    <div class="pipe-item-date">
                                                                                        ${response.date}
                                                                                    </div>
                                                                                </div>
                                                                                <div class="pipe-item-bottom">
                                                                                    <p class="pipe-bottom-item">
                                                                                        <strong>Send by</strong>
                                                                                        ${response.email}
                                                                                    </p>
                                                                                    <c:if test="${requestScope.COMPLETED_ITEM eq true}">
                                                                                        <div class="pipe-item-bottom-form-wrapper">
                                                                                            <form action="UpdateFeedbackDoneController" class="pipe-bottom-form">
                                                                                                <input type="hidden" name="feedbackID" value="${response.feedbackID}"/>
                                                                                                <button type="submit" class="btn--done">
                                                                                                    <ion-icon
                                                                                                        name="checkbox-outline"
                                                                                                        ></ion-icon>
                                                                                                </button>
                                                                                            </form>
                                                                                            <form action="UpdateFeedbackDeclineController" class="pipe-bottom-form">
                                                                                                <input type="hidden" name="feedbackID" value="${response.feedbackID}"/>
                                                                                                <button
                                                                                                    type="submit"
                                                                                                    class="btn--decline"
                                                                                                    >
                                                                                                    <ion-icon
                                                                                                        name="close-circle-outline"
                                                                                                        ></ion-icon>
                                                                                                </button>
                                                                                            </form>
                                                                                        </div>
                                                                                    </c:if>
                                                                                </div>
                                                                            </div>
                                                                        </a>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <a href="ShowFeedbackResponeDetailForManagerController?feedback_response_id=${response.feedbackID}&style_list=comment">
                                                                            <div class="pipe-comment-item">
                                                                                <div class="pipe-item-heading">
                                                                                    <div class="pipe-item-title-wrapper">
                                                                                        <h3 class="pipe-item-title">
                                                                                            Feedback ${response.feedbackID}
                                                                                        </h3>
                                                                                    </div>
                                                                                    <div class="pipe-item-date">
                                                                                        ${response.date}
                                                                                    </div>
                                                                                </div>
                                                                                <div class="pipe-item-bottom">
                                                                                    <p class="pipe-bottom-item">
                                                                                        <strong>Send by</strong>
                                                                                        ${response.email}
                                                                                    </p>
                                                                                </div>
                                                                            </div>
                                                                        </a>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                            <c:if test="${empty sessionScope.FEEDBACK_RESPONE_LIST}">
                                                                <p class="pipe-item-date">No result can be found ...</p>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div
                                                    class="pipe-column feedback-detail"
                                                    style="background-color: #fff"
                                                    >
                                                    <div class="icon-chevron-back task-chevron-back">
                                                        <ion-icon name="chevron-back-outline"></ion-icon>
                                                    </div>
                                                    <div
                                                        class="icon-chevron-forward task-chevron-forward"
                                                        >
                                                        <ion-icon name="chevron-forward-outline"></ion-icon>
                                                    </div>
                                                    <c:forEach var="reponseDetail" items="${sessionScope.RESPONE_DETAIL_LIST}" varStatus="counter">
                                                        <c:choose>
                                                            <c:when test="${counter.count == 1}">
                                                                <div
                                                                    class="detail-wrapper task-detail active"
                                                                    data-index="${counter.count}"
                                                                    >
                                                                    <div class="feedback-detail-header">
                                                                        <h2 class="feedback-detail-tittle">Reponse ${counter.count}</h2>
                                                                    </div>
                                                                    <div class="feedback-detail-showcase">
                                                                        <div class="feedback-detail-input">
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="device"
                                                                                    id="device"
                                                                                    value="${reponseDetail.deviceName}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Device Name</label>
                                                                            </div>
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="location"
                                                                                    id="location"
                                                                                    value="Room ${reponseDetail.location}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Location</label>
                                                                            </div>
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="employee"
                                                                                    id="employee"
                                                                                    value="${reponseDetail.userName}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Employee</label>
                                                                            </div>
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="date"
                                                                                    id="date"
                                                                                    value="${reponseDetail.date}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Date</label>
                                                                            </div>
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="quantity"
                                                                                    id="quantity"
                                                                                    value="${reponseDetail.quantity}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Quantity</label>
                                                                            </div>
                                                                        </div>
                                                                        <div class="feedback-detail-textarea">
                                                                            <div class="textarea-wrapper">
                                                                                <textarea
                                                                                    name="description"
                                                                                    id="description"
                                                                                    readonly
                                                                                    >${reponseDetail.des}</textarea
                                                                                >
                                                                                <label class="input-label">Description</label>
                                                                            </div>
                                                                            <div class="feedback-detail-image-wrapper">
                                                                                <img
                                                                                    src="data:image/jpg/png;base64,${reponseDetail.image}"
                                                                                    alt=""
                                                                                    />
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <div
                                                                    class="detail-wrapper task-detail"
                                                                    data-index="${counter.count}"
                                                                    >
                                                                    <div class="feedback-detail-header">
                                                                        <h2 class="feedback-detail-tittle">Reponse ${counter.count}</h2>
                                                                    </div>
                                                                    <div class="feedback-detail-showcase">
                                                                        <div class="feedback-detail-input">
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="device"
                                                                                    id="device"
                                                                                    value="${reponseDetail.deviceName}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Device Name</label>
                                                                            </div>
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="location"
                                                                                    id="location"
                                                                                    value="Room ${reponseDetail.location}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Location</label>
                                                                            </div>
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="employee"
                                                                                    id="quantity"
                                                                                    value="${reponseDetail.userName}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Employee</label>
                                                                            </div>
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="date"
                                                                                    id="date"
                                                                                    value="${reponseDetail.date}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Date</label>
                                                                            </div>
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="quantity"
                                                                                    id="quantity"
                                                                                    value="${reponseDetail.quantity}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Quantity</label>
                                                                            </div>
                                                                        </div>
                                                                        <div class="feedback-detail-textarea">
                                                                            <div class="textarea-wrapper">
                                                                                <textarea
                                                                                    name="description"
                                                                                    id="description"
                                                                                    readonly
                                                                                    >${reponseDetail.des}</textarea
                                                                                >
                                                                                <label class="input-label">Description</label>
                                                                            </div>
                                                                            <div class="feedback-detail-image-wrapper">
                                                                                <img
                                                                                    src="data:image/jpg/png;base64,${reponseDetail.image}"
                                                                                    alt=""
                                                                                    />
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
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
            </section>
        </main>
        <script src="${pageContext.request.contextPath}/js/adminPage1.js"></script>
        <script src="${pageContext.request.contextPath}/js/ManagerFB.js"></script>
    </body>
</html>