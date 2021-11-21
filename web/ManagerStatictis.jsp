<%-- 
    Document   : ManagerViewEmployee
    Created on : Sep 25, 2021, 3:05:51 PM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="app.users.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminPage.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ManagerStatictis1.css" />
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_ADMIN == null}">
            <c:redirect url="login.jsp"/>
        </c:if>
        <div class="user-form ${requestScope.edit_flag}">
            <div class="modal">
                <div class="user-form-main">
                    <form action="UpdateUserController" class="user-form-actual-form" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="position" value="${requestScope.position}"/>
                        <div class="actual-form-heading">
                            <div class="avatar-wrap">
                                <c:set var="image" value="${requestScope.USER_UPDATE.image}"/>
                                <c:choose>
                                    <c:when test="${fn:startsWith(image, 'http')}">
                                        <img
                                            src="${requestScope.USER_UPDATE.image}"
                                            alt=""
                                            class="avatar"
                                            />
                                    </c:when>
                                    <c:otherwise>
                                        <img
                                            src="data:image/jpg/png;base64,${requestScope.USER_UPDATE.image}"
                                            alt=""
                                            class="avatar"
                                            />
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="input-file-wrap">
                                <label>
                                    <ion-icon
                                        name="image-outline"
                                        class="input-file-select actual-form-heading-image"
                                        ></ion-icon>
                                    <input
                                        type="file"
                                        name="image"
                                        id="image"
                                        style="display: none"
                                        />
                                </label>
                            </div>
                        </div>
                        <div class="actual-form-content">
                            <div class="actual-form-input-wrapper">
                                <div class="input-wrap">
                                    <input
                                        type="text"
                                        name="userName"
                                        id="userName"
                                        value="${requestScope.USER_UPDATE.email}"
                                        readonly
                                        />
                                    <label>User Name</label>
                                </div>
                                <div class="input-wrap">
                                    <input
                                        type="email"
                                        name="email"
                                        id="email"
                                        value="${requestScope.USER_UPDATE.email}"
                                        readonly
                                        />
                                    <label>Email</label>
                                </div>
                                <div class="input-wrap">
                                    <input
                                        type="text"
                                        name="roleID"
                                        id="roleID"
                                        value="${requestScope.USER_UPDATE.roleID}"
                                        readonly
                                        />
                                    <label>Role</label>
                                </div>
                            </div>
                            <div class="actual-form-input-wrapper">
                                <div class="input-wrap">
                                    <input
                                        type="text"
                                        name="statusID"
                                        id="statusID"
                                        value="${requestScope.USER_UPDATE.statusID}"
                                        readonly
                                        />
                                    <label>Status</label>
                                </div>
                                <div class="input-wrap">
                                    <input
                                        type="password"
                                        name="password"
                                        id="password"
                                        value="${requestScope.USER_UPDATE.password}"
                                        />
                                    <label>Password</label>
                                </div>
                                <div class="input-wrap">
                                    <input
                                        type="text"
                                        name="fullName"
                                        id="fullName"
                                        value="${requestScope.USER_UPDATE.fullName}"
                                        placeholder="Enter full name ..."
                                        />
                                    <label>Full Name</label>
                                </div>
                            </div>
                        </div>
                        <div class="actual-form-footer">
                            <button type="submit" class="actual-form-footer-btn done">
                                <ion-icon name="checkmark-circle-outline"></ion-icon>
                            </button>
                        </div>
                        <input type="hidden" name="userID" value="${requestScope.USER_UPDATE.userID}"/>
                    </form>
                </div>
            </div>
        </div>

        <main>
            <section class="showcase">
                <div class="container">
                    <div class="showcase-main">
                        <div class="showcase-header">
                            <div class="showcase-logo">
                                <a href="FacilityStatisticController">
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
                                <li class="showcase-item active" data-index="3">
                                    <a href="#" class="showcase-link">
                                        <ion-icon
                                            name="stats-chart-outline"
                                            class="no-active-mode"
                                            ></ion-icon>
                                        <ion-icon name="stats-chart" class="active-mode"></ion-icon>
                                    </a>
                                    <span></span>
                                </li>
                                <li
                                    class="showcase-item showcase-item-dropdown-select"
                                    data-index="4"
                                    >
                                    <a href="#" class="showcase-link">
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
                                    <div class="showcase-item-dropdown-list">
                                        <h4 class="showcase-item-dropdown-title">Notification</h4>
                                        <h5 class="showcase-item-dropdown-sub-title">
                                            You have ${sessionScope.NOTIFICATION_QUANTITY} new feedback
                                        </h5>
                                        <h5 class="showcase-item-dropdown-sub-title sub-title-no">
                                            No notification can be found ...
                                        </h5> 

                                        <div class="pipe-list">
                                            <div class="pending-user-list">
                                            </div>
                                            <div class="pending-trash-list">
                                            </div>
                                            <div class="response-list">
                                            </div>
                                        </div>
                                    </div>
                                    <div
                                        class="showcase-item-dropdown-actual-notification"
                                        >
                                    </div>
                                </li>
                            </ul>
                            <div class="showcase-profile">
                                <div class="showcase-profile-image">
                                    <div class="showcase-profile-dropdown">
                                        <div class="showcase-profile-image">
                                            <c:set var="avatar" value="${sessionScope.LOGIN_ADMIN.image}"></c:set>
                                            <c:choose>
                                                <c:when test="${fn:startsWith(avatar, 'http')}">
                                                    <img
                                                        src="${sessionScope.LOGIN_ADMIN.image}"
                                                        alt=""
                                                        />
                                                </c:when>
                                                <c:otherwise>
                                                    <img
                                                        src="data:image/jpg/png;base64,${sessionScope.LOGIN_ADMIN.image}"
                                                        alt=""
                                                        />
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="showcase-profile-dropdown-list">
                                            <a href="ShowUserFormController?position=managerStatictisPage&user=admin" class="dropdown-item">
                                                <ion-icon name="create-outline"></ion-icon>
                                                Edit Profile
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <a href="LogoutController">
                                    <ion-icon name="log-out-outline"></ion-icon>
                                </a>
                            </div>
                        </div>

                        <!-- in those have 3 Pages -->
                        <div class="showcase-content-list">
                            <!-- If you want to Page is show for user you only need to add class 'active' for div[class="showcase-content-item"]  -->

                            <!-- Stats -->
                            <div class="showcase-content-item active">
                                <div class="statictis">
                                    <div class="statictis-chart">
                                        <div class="statictis-chart-header">
                                            <h2 class="statictis-chart-heading">
                                                Welcome back, ${sessionScope.LOGIN_ADMIN.fullName}
                                            </h2>
                                            <form action="FacilityStatisticController" class="statictis-chart-form">
                                                <select
                                                    name="search"
                                                    id="search"
                                                    class="statictis-chart-select"
                                                    >
                                                    <option value="${requestScope.SEARCH}" selected hidden>${requestScope.SEARCH}</option>
                                                    <option value="Year">Year</option>
                                                    <option value="Month" >Month</option>
                                                    <option value="Quarter" >Quarter</option>
                                                </select>
                                                <button type="submit">
                                                    <ion-icon name="search"></ion-icon>
                                                </button>
                                            </form>
                                        </div>
                                        <div class="statictis-chart-actual-chart">
                                            <div class="statictis-chart-actual-chart-header">
                                                <div id="columnchart_div"></div>
                                                <label>Year</label>
                                            </div>
                                            <div class="statictis-chart-actual-chart-bottom">
                                                <div class="donutchart-wrapper">
                                                    <div id="donutchart_div"></div>
                                                    <div class="label-wrapper">
                                                        <label>Total</label>
                                                        <label>${sessionScope.TOTAL}</label>
                                                    </div>
                                                    <label class="donutchart-title" style="letter-spacing: 0.25rem;"
                                                           >Month</label
                                                    >
                                                </div>
                                                <div class="statictis-chart-list">
                                                    <h3 class="statictis-chart-list-heading">
                                                        Top Facility Feedback
                                                    </h3>
                                                    <div class="statictis-chart-list-main">
                                                        <ul class="statictis-chart-list-main-heading">
                                                            <li
                                                                class="statictis-chart-list-main-heading-item"
                                                                >
                                                                Facility
                                                            </li>
                                                            <li
                                                                class="statictis-chart-list-main-heading-item"
                                                                >
                                                                Quantity
                                                            </li>
                                                            <li
                                                                class="statictis-chart-list-main-heading-item"
                                                                >
                                                                Category
                                                            </li>
                                                        </ul>
                                                        <!-- Facility Statistic -->
                                                        <div class="statictis-chart-list-main-list">
                                                            <c:forEach var="facility" items="${sessionScope.FACILITY_STATISTIC}">
                                                                <div class="statictis-chart-list-main-item">
                                                                    <div class="item-image">
                                                                        <div class="image-wrapper">
                                                                            <img
                                                                                src="${facility.image}"
                                                                                alt=""
                                                                                />
                                                                        </div>
                                                                        <p>${facility.facilityName}</p>
                                                                    </div>
                                                                    <p>${facility.count}</p>
                                                                    <p>${facility.categoryID}</p>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--Top 3 Members-->
                                    <div class="statictis-users" style="background-color: #f0e2cc;">
                                        <div class="icon-profile-back profile-chevron-back baduser active">
                                            <ion-icon name="chevron-back-outline"></ion-icon>
                                        </div>
                                        <div class="icon-profile-forward profile-chevron-forward baduser active">
                                            <ion-icon name="chevron-forward-outline"></ion-icon>
                                        </div>
                                        <div class="icon-profile-back profile-chevron-back bademp">
                                            <ion-icon name="chevron-back-outline"></ion-icon>
                                        </div>
                                        <div class="icon-profile-forward profile-chevron-forward bademp">
                                            <ion-icon name="chevron-forward-outline"></ion-icon>
                                        </div>
                                        <div class="icon-profile-back profile-chevron-back goodemp">
                                            <ion-icon name="chevron-back-outline"></ion-icon>
                                        </div>
                                        <div class="icon-profile-forward profile-chevron-forward goodemp">
                                            <ion-icon name="chevron-forward-outline"></ion-icon>
                                        </div>
                                        <div class="statistic-user-header">
                                            <label>Top 3 Members</label>
                                        </div>
                                        <div class="statistic-user-role">
                                            <ul class="user-role-navigation">
                                                <li class="role-navigation ${sessionScope.LIST_STUDENT}" id="roleStudent">
                                                    <a href="#" id="studentTab">Students</a>
                                                </li>
                                                <li class="role-navigation ${sessionScope.LIST_EMPLOYEE}" id="roleEmployee">
                                                    <a href="#" id="employeeTab">Employees</a>
                                                </li>
                                            </ul>
                                            <span class="role-navigation-bar student" id="tabnavigation"></span>
                                        </div>
                                        <div class="statistic-user-title">
                                            <label>Profile</label>
                                            <div class="statistic-behavior" id="goodbadOption">
                                                <ul class="user-behavior" id="behavior-box">
                                                    <li class="behavior-navigation good active" id="roleGoodList">
                                                        <a href="#" id="goodRole">Good Behavior</a>
                                                    </li>
                                                    <li><hr></li>
                                                    <li class="behavior-navigation bad " id="roleBadList">
                                                        <a href="#" id="badRole">Bad Behavior</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <!--Student List-->
                                        <div class="student-list ${sessionScope.SHOW_USER_LIST}" id="student-list">
                                            <div class="statistic-user-list">
                                                <c:forEach var="userprofile" items="${sessionScope.LIST_BAD_USER}" varStatus="counter">
                                                    <c:choose>
                                                        <c:when test="${counter.count == 1 && userprofile.statusID eq 'active'}">
                                                            <c:set var="userid" scope="session" value="${userprofile.userID}"/>
                                                            <div class="statistic-user-wrapper statistic-student-wrapper active" data-index="${counter.count}">
                                                                <div class="statistic-user-profile">
                                                                    <div class="statistic-user-avatar">
                                                                        <c:set var="avatar" value="${userprofile.image}"></c:set>
                                                                        <c:choose>
                                                                            <c:when test="${fn:startsWith(avatar, 'http')}">
                                                                                <img
                                                                                    src="${userprofile.image}"
                                                                                    alt=""
                                                                                    />
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <img
                                                                                    src="data:image/jpg/png;base64,${userprofile.image}"
                                                                                    alt=""
                                                                                    />
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </div>
                                                                    <h1 class="user-name">${userprofile.fullName}</h1>
                                                                    <h2>User</h2>
                                                                    <h2 style="color:#73fa5d">${userprofile.statusID}</h2>
                                                                    <hr/>
                                                                </div>
                                                                <div class="user-feedback-list">
                                                                    <div class="user-feedback-title">
                                                                        <label>Recent Feedback</label>
                                                                        <div class="viewall">
                                                                            <input type="submit" value="View All " class="viewall-button"/>
                                                                            <img src="img/doublearrow.png"/>
                                                                        </div>
                                                                    </div>
                                                                    <div class="feedback-wrapper">
                                                                        <c:forEach var="recentfeedback" items="${sessionScope.LIST_BAD_RECENT_RESPONE_USER}" varStatus="feedbackcount">
                                                                            <c:if test="${userprofile.userID == recentfeedback.userID}">
                                                                                <div class="pipe-item">
                                                                                    <div class="pipe-item-heading">
                                                                                        <div class="pipe-item-title-wrapper">
                                                                                            <h3 class="pipe-item-title">
                                                                                                Feedback ${recentfeedback.feedbackDetailID}
                                                                                            </h3>
                                                                                            <p class="pipe-item-desc">
                                                                                                <strong>Name:</strong> ${recentfeedback.deviceName}
                                                                                            </p>
                                                                                        </div>
                                                                                        <div class="pipe-item-date">
                                                                                            ${recentfeedback.date}
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="pipe-item-bottom">
                                                                                        <p class="pipe-bottom-item">
                                                                                            <strong>Status:</strong>
                                                                                            Decline
                                                                                        </p>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:when>
                                                        <c:when test="${counter.count != 1 && userprofile.statusID eq 'active'}">
                                                            <c:set var="userid" scope="session" value="${userprofile.userID}"/>
                                                            <div class="statistic-user-wrapper statistic-student-wrapper" data-index="${counter.count}">
                                                                <div class="statistic-user-profile">
                                                                    <div class="statistic-user-avatar">
                                                                        <c:set var="avatar" value="${userprofile.image}"></c:set>
                                                                        <c:choose>
                                                                            <c:when test="${fn:startsWith(avatar, 'http')}">
                                                                                <img
                                                                                    src="${userprofile.image}"
                                                                                    alt=""
                                                                                    />
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <img
                                                                                    src="data:image/jpg/png;base64,${userprofile.image}"
                                                                                    alt=""
                                                                                    />
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </div>
                                                                    <h1 class="user-name">${userprofile.fullName}</h1>
                                                                    <h2>User</h2>
                                                                    <h2 style="color:#73fa5d">${userprofile.statusID}</h2>
                                                                    <hr/>
                                                                </div>
                                                                <div class="user-feedback-list">
                                                                    <div class="user-feedback-title">
                                                                        <label>Recent Feedback</label>
                                                                        <div class="viewall">
                                                                            <input type="submit" value="View All " class="viewall-button"/>
                                                                            <img src="img/doublearrow.png"/>
                                                                        </div>
                                                                    </div>
                                                                    <div class="feedback-wrapper">
                                                                        <c:forEach var="recentfeedback" items="${sessionScope.LIST_BAD_RECENT_RESPONE_USER}" varStatus="feedbackcount">
                                                                            <c:if test="${userprofile.userID == recentfeedback.userID}">
                                                                                <div class="pipe-item">
                                                                                    <div class="pipe-item-heading">
                                                                                        <div class="pipe-item-title-wrapper">
                                                                                            <h3 class="pipe-item-title">
                                                                                                Feedback ${recentfeedback.feedbackDetailID}
                                                                                            </h3>
                                                                                            <p class="pipe-item-desc">
                                                                                                <strong>Name:</strong> ${recentfeedback.deviceName}
                                                                                            </p>
                                                                                        </div>
                                                                                        <div class="pipe-item-date">
                                                                                            ${recentfeedback.date}
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="pipe-item-bottom">
                                                                                        <p class="pipe-bottom-item">
                                                                                            <strong>Status:</strong>
                                                                                            Decline
                                                                                        </p>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:when>
                                                        <c:when test="${counter.count == 1 && userprofile.statusID eq 'inactive'}">
                                                            <c:set var="userid" scope="session" value="${userprofile.userID}"/>
                                                            <div class="statistic-user-wrapper statistic-student-wrapper active" data-index="${counter.count}">
                                                                <div class="statistic-user-profile">
                                                                    <div class="statistic-user-avatar">
                                                                        <c:set var="avatar" value="${userprofile.image}"></c:set>
                                                                        <c:choose>
                                                                            <c:when test="${fn:startsWith(avatar, 'http')}">
                                                                                <img
                                                                                    src="${userprofile.image}"
                                                                                    alt=""
                                                                                    />
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <img
                                                                                    src="data:image/jpg/png;base64,${userprofile.image}"
                                                                                    alt=""
                                                                                    />
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </div>
                                                                    <h1 class="user-name">${userprofile.fullName}</h1>
                                                                    <h2>User</h2>
                                                                    <h2 style="color: #e71e1e">${userprofile.statusID}</h2>
                                                                    <hr/>
                                                                </div>
                                                                <div class="user-feedback-list">
                                                                    <div class="user-feedback-title">
                                                                        <label>Recent Feedback</label>
                                                                        <div class="viewall">
                                                                            <input type="submit" value="View All " class="viewall-button"/>
                                                                            <img src="img/doublearrow.png"/>
                                                                        </div>
                                                                    </div>
                                                                    <div class="feedback-wrapper">
                                                                        <c:forEach var="recentfeedback" items="${sessionScope.LIST_BAD_RECENT_RESPONE_USER}" varStatus="feedbackcount">
                                                                            <c:if test="${userprofile.userID == recentfeedback.userID}">
                                                                                <div class="pipe-item">
                                                                                    <div class="pipe-item-heading">
                                                                                        <div class="pipe-item-title-wrapper">
                                                                                            <h3 class="pipe-item-title">
                                                                                                Feedback ${recentfeedback.feedbackDetailID}
                                                                                            </h3>
                                                                                            <p class="pipe-item-desc">
                                                                                                <strong>Name:</strong> ${recentfeedback.deviceName}
                                                                                            </p>
                                                                                        </div>
                                                                                        <div class="pipe-item-date">
                                                                                            ${recentfeedback.date}
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="pipe-item-bottom">
                                                                                        <p class="pipe-bottom-item">
                                                                                            <strong>Status:</strong>
                                                                                            Decline
                                                                                        </p>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </div>
                                                                </div>
                                                                <div class="unban-button">
                                                                    <a href="AUController?userID=${userprofile.userID}&StatusID=${userprofile.statusID}&flag=true">
                                                                        <input type="submit" value="Unban"/>
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </c:when>
                                                        <c:when test="${counter.count != 1 && userprofile.statusID eq 'inactive'}">
                                                            <c:set var="userid" scope="session" value="${userprofile.userID}"/>
                                                            <div class="statistic-user-wrapper statistic-student-wrapper" data-index="${counter.count}">
                                                                <div class="statistic-user-profile">
                                                                    <div class="statistic-user-avatar">
                                                                        <c:set var="avatar" value="${userprofile.image}"></c:set>
                                                                        <c:choose>
                                                                            <c:when test="${fn:startsWith(avatar, 'http')}">
                                                                                <img
                                                                                    src="${userprofile.image}"
                                                                                    alt=""
                                                                                    />
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <img
                                                                                    src="data:image/jpg/png;base64,${userprofile.image}"
                                                                                    alt=""
                                                                                    />
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </div>
                                                                    <h1 class="user-name">${userprofile.fullName}</h1>
                                                                    <h2>User</h2>
                                                                    <h2 style="color: #e71e1e">${userprofile.statusID}</h2>
                                                                    <hr/>
                                                                </div>
                                                                <div class="user-feedback-list">
                                                                    <div class="user-feedback-title">
                                                                        <label>Recent Feedback</label>
                                                                        <div class="viewall">
                                                                            <input type="submit" value="View All " class="viewall-button"/>
                                                                            <img src="img/doublearrow.png"/>
                                                                        </div>
                                                                    </div>
                                                                    <div class="feedback-wrapper">
                                                                        <c:forEach var="recentfeedback" items="${sessionScope.LIST_BAD_RECENT_RESPONE_USER}" varStatus="feedbackcount">
                                                                            <c:if test="${userprofile.userID == recentfeedback.userID}">
                                                                                <div class="pipe-item">
                                                                                    <div class="pipe-item-heading">
                                                                                        <div class="pipe-item-title-wrapper">
                                                                                            <h3 class="pipe-item-title">
                                                                                                Feedback ${recentfeedback.feedbackDetailID}
                                                                                            </h3>
                                                                                            <p class="pipe-item-desc">
                                                                                                <strong>Name:</strong> ${recentfeedback.deviceName}
                                                                                            </p>
                                                                                        </div>
                                                                                        <div class="pipe-item-date">
                                                                                            ${recentfeedback.date}
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="pipe-item-bottom">
                                                                                        <p class="pipe-bottom-item">
                                                                                            <strong>Status:</strong>
                                                                                            Decline
                                                                                        </p>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </div>
                                                                </div>
                                                                <div class="unban-button">
                                                                    <a href="AUController?userID=${userprofile.userID}&StatusID=${userprofile.statusID}&flag=true">
                                                                        <input type="submit" value="Unban"/>
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </c:when>
                                                    </c:choose>    
                                                </c:forEach>  
                                            </div>
                                        </div>
                                        <!--Employee List-->
                                        <div class="employee-list ${sessionScope.SHOW_EMPLOYEE_LIST}" id="employee-list">
                                            <!--Good Employee list-->
                                            <div class="good-employee-show active" id="good-emp-list">
                                                <div class="statistic-user-list">
                                                    <c:forEach var="userprofile" items="${sessionScope.LIST_GOOD_EMP}" varStatus="counter">
                                                        <c:choose>
                                                            <c:when test="${counter.count == 1}">
                                                                <c:set var="userid" scope="session" value="${userprofile.userID}"/>
                                                                <div class="statistic-user-wrapper statistic-goodemp-wrapper active" data-index="${counter.count}">
                                                                    <div class="statistic-user-profile">
                                                                        <div class="statistic-user-avatar">
                                                                            <c:set var="avatar" value="${userprofile.image}"></c:set>
                                                                            <c:choose>
                                                                                <c:when test="${fn:startsWith(avatar, 'http')}">
                                                                                    <img
                                                                                        src="${userprofile.image}"
                                                                                        alt=""
                                                                                        />
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <img
                                                                                        src="data:image/jpg/png;base64,${userprofile.image}"
                                                                                        alt=""
                                                                                        />
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </div>
                                                                        <h1 class="user-name">${userprofile.fullName}</h1>
                                                                        <h2>Employee</h2>
                                                                        <hr/>
                                                                    </div>
                                                                    <div class="user-feedback-list">
                                                                        <div class="user-feedback-title">
                                                                            <label>Recent Feedback</label>
                                                                            <div class="viewall">
                                                                                <input type="submit" value="View All " class="viewall-button"/>
                                                                                <img src="img/doublearrow.png"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="feedback-wrapper">
                                                                            <c:forEach var="recentfeedback" items="${sessionScope.LIST_GOOD_RECENT_RESPONE_EMP}" varStatus="feedbackcount">
                                                                                <c:if test="${userprofile.userID == recentfeedback.userID}">
                                                                                    <div class="pipe-item">
                                                                                        <div class="pipe-item-heading">
                                                                                            <div class="pipe-item-title-wrapper">
                                                                                                <h3 class="pipe-item-title">
                                                                                                    Feedback ${recentfeedback.feedbackDetailID}
                                                                                                </h3>
                                                                                                <p class="pipe-item-desc">
                                                                                                    <strong>Name:</strong> ${recentfeedback.deviceName}
                                                                                                </p>
                                                                                            </div>
                                                                                            <div class="pipe-item-date">
                                                                                                ${recentfeedback.date}
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="pipe-item-bottom">
                                                                                            <p class="pipe-bottom-item">
                                                                                                <strong>Status:</strong>
                                                                                                Done
                                                                                            </p>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:set var="userid" scope="session" value="${userprofile.userID}"/>
                                                                <div class="statistic-user-wrapper statistic-goodemp-wrapper" data-index="${counter.count}">
                                                                    <div class="statistic-user-profile">
                                                                        <div class="statistic-user-avatar">
                                                                            <c:set var="avatar" value="${userprofile.image}"></c:set>
                                                                            <c:choose>
                                                                                <c:when test="${fn:startsWith(avatar, 'http')}">
                                                                                    <img
                                                                                        src="${userprofile.image}"
                                                                                        alt=""
                                                                                        />
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <img
                                                                                        src="data:image/jpg/png;base64,${userprofile.image}"
                                                                                        alt=""
                                                                                        />
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </div>
                                                                        <h1 class="user-name">${userprofile.fullName}</h1>
                                                                        <h2>Employee</h2>
                                                                        <hr/>
                                                                    </div>
                                                                    <div class="user-feedback-list">
                                                                        <div class="user-feedback-title">
                                                                            <label>Recent Feedback</label>
                                                                            <div class="viewall">
                                                                                <input type="submit" value="View All " class="viewall-button"/>
                                                                                <img src="img/doublearrow.png"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="feedback-wrapper">
                                                                            <c:forEach var="recentfeedback" items="${sessionScope.LIST_GOOD_RECENT_RESPONE_EMP}" varStatus="feedbackcount">
                                                                                <c:if test="${userprofile.userID == recentfeedback.userID}">
                                                                                    <div class="pipe-item">
                                                                                        <div class="pipe-item-heading">
                                                                                            <div class="pipe-item-title-wrapper">
                                                                                                <h3 class="pipe-item-title">
                                                                                                    Feedback ${recentfeedback.feedbackDetailID}
                                                                                                </h3>
                                                                                                <p class="pipe-item-desc">
                                                                                                    <strong>Name:</strong> ${recentfeedback.deviceName}
                                                                                                </p>
                                                                                            </div>
                                                                                            <div class="pipe-item-date">
                                                                                                ${recentfeedback.date}
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="pipe-item-bottom">
                                                                                            <p class="pipe-bottom-item">
                                                                                                <strong>Status:</strong>
                                                                                                Done
                                                                                            </p>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>    

                                                    </c:forEach>
                                                </div>
                                            </div>
                                            <!--Bad Employee List-->
                                            <div class="bad-employee-show" id="bad-emp-list">
                                                <div class="statistic-user-list">
                                                    <c:forEach var="userprofile" items="${sessionScope.LIST_BAD_EMP}" varStatus="counter">
                                                        <c:choose>
                                                            <c:when test="${counter.count == 1 && userprofile.rating == -20}">
                                                                <c:set var="userid" scope="session" value="${userprofile.userID}"/>
                                                                <div class="statistic-user-wrapper statistic-bademp-wrapper active" data-index="${counter.count}">
                                                                    <div class="statistic-user-profile">
                                                                        <div class="statistic-user-avatar">
                                                                            <c:set var="avatar" value="${userprofile.image}"></c:set>
                                                                            <c:choose>
                                                                                <c:when test="${fn:startsWith(avatar, 'http')}">
                                                                                    <img
                                                                                        src="${userprofile.image}"
                                                                                        alt=""
                                                                                        />
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <img
                                                                                        src="data:image/jpg/png;base64,${userprofile.image}"
                                                                                        alt=""
                                                                                        />
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </div>
                                                                        <h1 class="user-name">${userprofile.fullName}</h1>
                                                                        <h2>Employee</h2>
                                                                        <hr/>
                                                                    </div>
                                                                    <div class="user-feedback-list">
                                                                        <div class="user-feedback-title">
                                                                            <label>Recent Feedback</label>
                                                                            <div class="viewall">
                                                                                <input type="submit" value="View All " class="viewall-button"/>
                                                                                <img src="img/doublearrow.png"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="feedback-wrapper">
                                                                            <c:forEach var="recentfeedback" items="${sessionScope.LIST_BAD_RECENT_RESPONE_EMP}" varStatus="feedbackcount">
                                                                                <c:if test="${userprofile.userID == recentfeedback.userID && recentfeedback.statusID eq 'done'}">
                                                                                    <div class="pipe-item">
                                                                                        <div class="pipe-item-heading">
                                                                                            <div class="pipe-item-title-wrapper">
                                                                                                <h3 class="pipe-item-title">
                                                                                                    Feedback ${recentfeedback.feedbackDetailID}
                                                                                                </h3>
                                                                                                <p class="pipe-item-desc">
                                                                                                    <strong>Name:</strong> ${recentfeedback.deviceName}
                                                                                                </p>
                                                                                            </div>
                                                                                            <div class="pipe-item-date">
                                                                                                ${recentfeedback.date}
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="pipe-item-bottom">
                                                                                            <p class="pipe-bottom-item">
                                                                                                <strong>Status:</strong>
                                                                                                ${recentfeedback.statusID}
                                                                                            </p>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:when>
                                                            <c:when test="${counter.count != 1 && userprofile.rating == -20}">
                                                                <c:set var="userid" scope="session" value="${userprofile.userID}"/>
                                                                <div class="statistic-user-wrapper statistic-bademp-wrapper" data-index="${counter.count}">
                                                                    <div class="statistic-user-profile">
                                                                        <div class="statistic-user-avatar">
                                                                            <c:set var="avatar" value="${userprofile.image}"></c:set>
                                                                            <c:choose>
                                                                                <c:when test="${fn:startsWith(avatar, 'http')}">
                                                                                    <img
                                                                                        src="${userprofile.image}"
                                                                                        alt=""
                                                                                        />
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <img
                                                                                        src="data:image/jpg/png;base64,${userprofile.image}"
                                                                                        alt=""
                                                                                        />
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </div>
                                                                        <h1 class="user-name">${userprofile.fullName}</h1>
                                                                        <h2>Employee</h2>
                                                                        <hr/>
                                                                    </div>
                                                                    <div class="user-feedback-list">
                                                                        <div class="user-feedback-title">
                                                                            <label>Recent Feedback</label>
                                                                            <div class="viewall">
                                                                                <input type="submit" value="View All " class="viewall-button"/>
                                                                                <img src="img/doublearrow.png"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="feedback-wrapper">
                                                                            <c:forEach var="recentfeedback" items="${sessionScope.LIST_BAD_RECENT_RESPONE_EMP}" varStatus="feedbackcount">
                                                                                <c:if test="${userprofile.userID == recentfeedback.userID && recentfeedback.statusID eq 'done'}">
                                                                                    <div class="pipe-item">
                                                                                        <div class="pipe-item-heading">
                                                                                            <div class="pipe-item-title-wrapper">
                                                                                                <h3 class="pipe-item-title">
                                                                                                    Feedback ${recentfeedback.feedbackDetailID}
                                                                                                </h3>
                                                                                                <p class="pipe-item-desc">
                                                                                                    <strong>Name:</strong> ${recentfeedback.deviceName}
                                                                                                </p>
                                                                                            </div>
                                                                                            <div class="pipe-item-date">
                                                                                                ${recentfeedback.date}
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="pipe-item-bottom">
                                                                                            <p class="pipe-bottom-item">
                                                                                                <strong>Status:</strong>
                                                                                                ${recentfeedback.statusID}
                                                                                            </p>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:when>
                                                            <c:when test="${counter.count == 1 && userprofile.rating > -20}">
                                                                <c:set var="userid" scope="session" value="${userprofile.userID}"/>
                                                                <div class="statistic-user-wrapper statistic-bademp-wrapper active" data-index="${counter.count}">
                                                                    <div class="statistic-user-profile">
                                                                        <div class="statistic-user-avatar">
                                                                            <c:set var="avatar" value="${userprofile.image}"></c:set>
                                                                            <c:choose>
                                                                                <c:when test="${fn:startsWith(avatar, 'http')}">
                                                                                    <img
                                                                                        src="${userprofile.image}"
                                                                                        alt=""
                                                                                        />
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <img
                                                                                        src="data:image/jpg/png;base64,${userprofile.image}"
                                                                                        alt=""
                                                                                        />
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </div>
                                                                        <h1 class="user-name">${userprofile.fullName}</h1>
                                                                        <h2>Employee</h2>
                                                                        <hr/>
                                                                    </div>
                                                                    <div class="user-feedback-list">
                                                                        <div class="user-feedback-title">
                                                                            <label>Recent Feedback</label>
                                                                            <div class="viewall">
                                                                                <input type="submit" value="View All " class="viewall-button"/>
                                                                                <img src="img/doublearrow.png"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="feedback-wrapper">
                                                                            <c:forEach var="recentfeedback" items="${sessionScope.LIST_BAD_RECENT_RESPONE_EMP}" varStatus="feedbackcount">
                                                                                <c:if test="${userprofile.userID == recentfeedback.userID && recentfeedback.statusID eq 'decline'}">
                                                                                    <div class="pipe-item">
                                                                                        <div class="pipe-item-heading">
                                                                                            <div class="pipe-item-title-wrapper">
                                                                                                <h3 class="pipe-item-title">
                                                                                                    Feedback ${recentfeedback.feedbackDetailID}
                                                                                                </h3>
                                                                                                <p class="pipe-item-desc">
                                                                                                    <strong>Name:</strong> ${recentfeedback.deviceName}
                                                                                                </p>
                                                                                            </div>
                                                                                            <div class="pipe-item-date">
                                                                                                ${recentfeedback.date}
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="pipe-item-bottom">
                                                                                            <p class="pipe-bottom-item">
                                                                                                <strong>Status:</strong>
                                                                                                ${recentfeedback.statusID}
                                                                                            </p>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:when>
                                                            <c:when test="${counter.count != 1 && userprofile.rating > -20}">
                                                                <c:set var="userid" scope="session" value="${userprofile.userID}"/>
                                                                <div class="statistic-user-wrapper statistic-bademp-wrapper" data-index="${counter.count}">
                                                                    <div class="statistic-user-profile">
                                                                        <div class="statistic-user-avatar">
                                                                            <c:set var="avatar" value="${userprofile.image}"></c:set>
                                                                            <c:choose>
                                                                                <c:when test="${fn:startsWith(avatar, 'http')}">
                                                                                    <img
                                                                                        src="${userprofile.image}"
                                                                                        alt=""
                                                                                        />
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <img
                                                                                        src="data:image/jpg/png;base64,${userprofile.image}"
                                                                                        alt=""
                                                                                        />
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </div>
                                                                        <h1 class="user-name">${userprofile.fullName}</h1>
                                                                        <h2>Employee</h2>
                                                                        <hr/>
                                                                    </div>
                                                                    <div class="user-feedback-list">
                                                                        <div class="user-feedback-title">
                                                                            <label>Recent Feedback</label>
                                                                            <div class="viewall">
                                                                                <input type="submit" value="View All " class="viewall-button"/>
                                                                                <img src="img/doublearrow.png"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="feedback-wrapper">
                                                                            <c:forEach var="recentfeedback" items="${sessionScope.LIST_BAD_RECENT_RESPONE_EMP}" varStatus="feedbackcount">
                                                                                <c:if test="${userprofile.userID == recentfeedback.userID && recentfeedback.statusID eq 'decline'}">
                                                                                    <div class="pipe-item">
                                                                                        <div class="pipe-item-heading">
                                                                                            <div class="pipe-item-title-wrapper">
                                                                                                <h3 class="pipe-item-title">
                                                                                                    Feedback ${recentfeedback.feedbackDetailID}
                                                                                                </h3>
                                                                                                <p class="pipe-item-desc">
                                                                                                    <strong>Name:</strong> ${recentfeedback.deviceName}
                                                                                                </p>
                                                                                            </div>
                                                                                            <div class="pipe-item-date">
                                                                                                ${recentfeedback.date}
                                                                                            </div>
                                                                                        </div>
                                                                                        <div class="pipe-item-bottom">
                                                                                            <p class="pipe-bottom-item">
                                                                                                <strong>Status:</strong>
                                                                                                ${recentfeedback.statusID}
                                                                                            </p>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:when>
                                                        </c:choose>    
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="pending_count" value="${sessionScope.PENDING_COUNT}"/>
                        <input type="hidden" name="pending_count_trash" value="${sessionScope.PENDING_TRASH_COUNT}"/>
                        <c:choose>
                            <c:when test="${sessionScope.COUNT_RESPONSE eq null}" >
                                <input id="COUNT_RESPONSE" type="hidden" name="COUNT_RESPONSE" value="0"/>

                            </c:when>
                            <c:otherwise >
                                <input id="COUNT_RESPONSE" type="hidden" name="COUNT_RESPONSE" value="${sessionScope.COUNT_RESPONSE}"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </section>
        </main>
                        <script src="${pageContext.request.contextPath}/js/adminPage1.js"></script>
        <script src="${pageContext.request.contextPath}/js/ManagerStatictis.js"></script>
        <script type="module" src="${pageContext.request.contextPath}/js/firebaseAdminfromUser.js"></script>
        <script
            type="text/javascript"
            src="https://www.gstatic.com/charts/loader.js"
        ></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            function handleNotification() {
                const count = document.querySelector('input[name="pending_count"]').value;
                const countTrash = document.querySelector('input[name="pending_count_trash"]').value;
                $.ajax({
                    type: "POST",
                    url: "/SWP391_PROJECT/NotificationController",
                    data: {notification: count},
                    success: function (result) {
                        var trash = JSON.parse(localStorage.getItem("trashFeedback"));
                        var response = JSON.parse(localStorage.getItem("responseFeedback"));
                        if (!trash || !response) {
                            var finalCount = 0;
                        } else {
                            finalCount = trash.finalCount + response.finalCount;
                        }
                        var pendingUserFeedback = {
                            finalCount: finalCount,
                            name: "Pending user"
                        };
                        if (result !== '') {
                            var lenght = result.slice(0, 1);
                            pendingUserFeedback.finalCount = parseInt(lenght);
                            finalCount += parseInt(lenght);
                            if (finalCount !== 0) {
                                $('.showcase-item-dropdown-actual-notification').addClass('active');
                                $('.showcase-item-dropdown-actual-notification').html(finalCount);
                                $('.showcase-item-dropdown-select').addClass('active');
                                $('.showcase-item-dropdown-sub-title').html("You have " + finalCount + " new feedback");
                            }

                        } else {
                            $('.showcase-item-dropdown-sub-title').html($('.showcase-item-dropdown-sub-title.sub-title-no').text());
                            $('.showcase-item-dropdown-actual-notification').removeClass('active');
                            $('.showcase-item-dropdown-select').removeClass('active');
                            pendingUserFeedback.finalCount = 0;
                        }
                        $('.showcase-item-dropdown-list .pipe-list .pending-user-list').html(result.slice(1));
                        localStorage.setItem("pendingUserFeedback", JSON.stringify(pendingUserFeedback));

                    }
                });

                $.ajax({
                    type: "POST",
                    url: "/SWP391_PROJECT/NotificationTrashController",
                    data: {notification: countTrash},
                    success: function (result) {
                        var pendingUser = JSON.parse(localStorage.getItem("pendingUserFeedback"));
                        var response = JSON.parse(localStorage.getItem("responseFeedback"));
                        if (!pendingUser || !response) {
                            var finalCount = 0;
                        } else {
                            finalCount = pendingUser.finalCount + response.finalCount;
                        }
                        var trashFeedback = {
                            finalCount: finalCount,
                            name: "trash feedback"
                        };
                        if (result !== '') {
                            var lenght = result.slice(0, 1);
                            trashFeedback.finalCount = parseInt(lenght);
                            finalCount += parseInt(lenght);
                            if (finalCount !== 0) {
                                $('.showcase-item-dropdown-actual-notification').addClass('active');
                                $('.showcase-item-dropdown-actual-notification').html(finalCount);
                                $('.showcase-item-dropdown-select').addClass('active');
                                $('.showcase-item-dropdown-sub-title').html("You have " + finalCount + " new feedback");
                            }
                        } else {
                            $('.showcase-item-dropdown-sub-title').html($('.showcase-item-dropdown-sub-title.sub-title-no').text());
                            $('.showcase-item-dropdown-actual-notification').removeClass('active');
                            $('.showcase-item-dropdown-select').removeClass('active');
                            trashFeedback.finalCount = 0;
                        }

                        localStorage.setItem("trashFeedback", JSON.stringify(trashFeedback));
                        $('.showcase-item-dropdown-list .pipe-list .pending-trash-list').html(result.slice(1));

                    }
                });

                const countRes = document.querySelector('#COUNT_RESPONSE').value;
                $.ajax({
                    type: "POST",
                    url: "/SWP391_PROJECT/NotificationResponseController",
                    data: {notification: countRes},
                    success: function (result) {
                        var pendingUser = JSON.parse(localStorage.getItem("pendingUserFeedback"));
                        var trash = JSON.parse(localStorage.getItem("trashFeedback"));
                        if (!pendingUser && !trash) {
                            var finalCount = 0;
                        } else {
                            finalCount = pendingUser.finalCount + trash.finalCount;
                        }
                        var responseFeedback = {
                            finalCount: finalCount,
                            name: "response feedback"
                        };
                        if (result !== '') {
                            var lenght = result.slice(0, 1);
                            responseFeedback.finalCount = parseInt(lenght);
                            finalCount += parseInt(lenght);
                            if (finalCount !== 0) {
                                $('.showcase-item-dropdown-actual-notification').addClass('active');
                                $('.showcase-item-dropdown-actual-notification').html(finalCount);
                                $('.showcase-item-dropdown-select').addClass('active');
                                $('.showcase-item-dropdown-sub-title').html("You have " + finalCount + " new feedback");
                            }
                        } else {
                            $('.showcase-item-dropdown-sub-title').html($('.showcase-item-dropdown-sub-title.sub-title-no').text());
                            $('.showcase-item-dropdown-actual-notification').removeClass('active');
                            $('.showcase-item-dropdown-select').removeClass('active');
                            responseFeedback.finalCount = 0;
                        }
                        localStorage.setItem("responseFeedback", JSON.stringify(responseFeedback));
                        $('.showcase-item-dropdown-list .response-list').html(result.slice(1));
                    }
                });
            }

            window.addEventListener("resize", (e) => {
                if (
                        document.documentElement.clientWidth <= 1600 ||
                        document.documentElement.clientWidth > 1600
                        ) {
                    $.ajax({
                        url: "/SWP391_PROJECT/FeedbackStatisticByYear",
                        dataType: "JSON",
                        success: function (data) {
                            google.charts.load("current", {packages: ["corechart"]});
                            google.charts.setOnLoadCallback(() => {
                                drawChart(data);
                            });
                        }
                    });
                    $.ajax({
                        url: "/SWP391_PROJECT/DonutStatisticController",
                        dataType: "JSON",
                        success: function (data) {
                            google.charts.load("current", {packages: ["corechart"]});
                            google.charts.setOnLoadCallback(() => {
                                drawChart2(data);
                            });
                        }
                    });
                }
            });
            $(function () {
//                handleNotification();
//                setInterval(handleNotification, 10000);

                $.ajax({
                    url: "/SWP391_PROJECT/FeedbackStatisticByYear",
                    dataType: "JSON",
                    success: function (data) {
                        google.charts.load("current", {packages: ["corechart"]});
                        google.charts.setOnLoadCallback(() => {
                            drawChart(data);
                        });
                    }
                });

                $.ajax({
                    url: "/SWP391_PROJECT/DonutStatisticController",
                    dataType: "JSON",
                    success: function (data) {
                        google.charts.load("current", {packages: ["corechart"]});
                        google.charts.setOnLoadCallback(() => {
                            drawChart2(data);
                        });
                    },
                });
            });
            function drawChart(result) {
                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Month');
                data.addColumn('number', 'Quantity');
                var dataArray = [];
                $.each(result, function (i, obj) {
                    dataArray.push([obj.month, obj.count]);
                });
                data.addRows(dataArray);

                var options = {
                    chartArea: {width: "90%", height: "70%"},
                    legend: {position: "none"},
                    tooltip: {
                        textStyle: {color: "151111", fontName: "Poppins"}
                    },
                    backgroundColor: "f0e2cc",
                    colors: ["FBA569"],
                    fontName: "Poppins",
                    hAxis: {
                        textStyle: {
                            color: "8d8d8d",
                            fontName: "Poppins",
                            bold: false,
                            fontSize: 11,
                        }
                    },
                    vAxis: {
                        baselineColor: "dadada",
                        gridlines: {color: "dadada"},
                        minorGridlines: {color: "f0e2cc"},
                        textStyle: {color: "8d8d8d"}
                    }
                };


                var chart = new google.visualization.ColumnChart(
                        document.getElementById("columnchart_div")
                        );
                chart.draw(data, options);
            }
            function drawChart2(result) {
                let data = new google.visualization.DataTable();
                data.addColumn('string', 'Status');
                data.addColumn('number', 'Quantity');
                var dataArray = [];
                $.each(result, function (i, obj) {
                    dataArray.push([obj.status, obj.count]);
                });
                data.addRows(dataArray);

                var options = {
                    backgroundColor: "#f0e2cc",
                    tooltip: {
                        showColorCode: true,
                        textStyle: {
                            fontName: "Poppins",
                            fontSize: 13,
                            italic: false
                        }
                    },
                    pieHole: 0.5,
                    pieSliceTextStyle: {
                        color: "black",
                    },
                    pieSliceText: "none",
                    legend: {
                        position: "bottom",
                        textStyle: {color: "826B7A", fontSize: 12},
                    },
                    pieSliceBorderColor: "transparent",
                    slices: {
                        0: {color: "D54341"},
                        1: {color: "55d141"},
                        2: {color: "5256ad"},
                        3: {color: "fbff37"},
                    },
//                       is3D: true,
                };

                let chart = new google.visualization.PieChart(
                        document.getElementById("donutchart_div")
                        );
                chart.draw(data, options);
            }
        </script>
    </body>
</html>


