<%-- 
    Document   : SendFeedback
    Created on : Sep 30, 2021, 12:52:29 PM
    Author     : Admin
--%>

<%@page import="app.users.UserHistoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%-- 
    Document   : sendFeedback
    Created on : Sep 21, 2021, 9:34:37 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>User Page</title>

        <!-- Poppins Font -->
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap"
            rel="stylesheet"
            />

        <!-- ioniconic -->
        <script
            type="module"
            src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"
        ></script>
        <script
            nomodule
            src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"
        ></script>

        <!-- Font awesome -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css"
            integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/User.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FeedbackForm1.css" />
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER == null}">
            <c:redirect url="login.jsp"/>
        </c:if>
        <!-- Pop-up create feedback -->
        <!-- If you return a message of form you have to add class value 'open' to div[class='feedback-form'], if you dont return a message from controller you dont have to do this-->
        <div class="feedback-form ${requestScope.SEND_FEEDBACK_FLAG}">
            <div class="modal feedback-form-modal">
                <div class="feedback-form-main">
                    <div class="container-main">
                        <form id="root" action="SendFeedbackController" class="root" method="post" enctype="multipart/form-data">
                            <div class="input-check">
                                <input type="hidden" name="check" value="1"/>
                            </div>
                            <div class="input-style-flag">
                                <input type="hidden" name="style_pipe" value="${requestScope.STYLE_PIPE}"/>
                                <input type="hidden" name="style_list" value="${requestScope.STYLE_LIST}"/>
                            </div>
                            <div class="container-wrapper">
                                <div class="device">
                                    <div class="tab-select-wrapper">
                                        <p
                                            data-index="0"
                                            onclick="handleChangeTabContent(event)"
                                            ondblclick="handleRemoveTab(event)"
                                            >
                                            Device 1
                                        </p>
                                    </div>
                                    <span class="feedback-tab-scrollbar"></span>
                                    <ion-icon name="add" class="plus-btn"></ion-icon>
                                    <div class="list-items-help">
                                        <i class="far fa-question-circle feedback-help"></i>
                                        <div class="help-message--hover">Support</div>
                                        <div
                                            class="help-message"
                                            style="transform: translateX(24%)"
                                            >
                                            Double click to form to delete it !
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-list-wrapper">
                                    <div class="list-items-wrapper active">
                                        <div class="list-feedback">
                                            <!-- Device -->
                                            <fieldset class="device-name-field">
                                                <legend class="legend-device-name">Device Name</legend>
                                                <select name="tmpDevice" class="select-device">
                                                    <option value="" selected="selected">
                                                        Choose a device
                                                    </option>
                                                    <c:forEach var="facility" items="${sessionScope.FACILTIES_LIST_ALL}">
                                                        <option value="${facility.facilityID}">${facility.facilityName}</option>
                                                    </c:forEach>
                                                </select>
                                            </fieldset>
                                            <!-- End device -->
                                            <!-- Location -->
                                            <fieldset class="location-field">
                                                <legend class="legend-location">Location</legend>
                                                <input
                                                    type="text"
                                                    name="tmpLocation"
                                                    class="select-location"
                                                    />
                                            </fieldset>
                                            <!-- End Location -->
                                            <!-- Reason -->
                                            <fieldset class="reason-field">
                                                <legend class="legend-reason">Reason</legend>
                                                <select name="tmpReason" class="select-reason">
                                                    <option value="">Choose your reason</option>
                                                    <option value="broken">Broken</option>
                                                    <option value="missing">Missing</option>
                                                    <option value="others">Others</option>
                                                </select>
                                            </fieldset>
                                            <!-- End reason -->
                                            <!-- Quantity -->
                                            <fieldset class="quantity-field">
                                                <legend class="legend-quantity">Quantity</legend>
                                                <input
                                                    type="number"
                                                    name="tmpQuantity"
                                                    class="select-quantity"
                                                    />
                                            </fieldset>
                                        </div>
                                        <!-- Description -->
                                        <div class="text">
                                            <fieldset class="description-field">
                                                <legend class="legend-description">Description</legend>
                                                <textarea
                                                    name="tmpDescription"
                                                    class="select-description"
                                                    ></textarea>
                                            </fieldset>
                                            <!-- End Description -->
                                            <div class="input-file-wrap">
                                                <label>
                                                    <ion-icon
                                                        name="image-outline"
                                                        class="input-file-select"
                                                        onclick="handleShowImage(event)"
                                                        data-index="1"
                                                        ></ion-icon>
                                                    <input
                                                        type="file"
                                                        name="image-1"
                                                        id="gallery-photo-add-1"
                                                        style="display: none"
                                                        />
                                                </label>
                                                <input type="text" class="input-file-name" readonly />
                                            </div>
                                            <div class="img-feedback tab-img-1"></div>
                                        </div>
                                    </div>
                                    <div class="list-items-wrapper">
                                        <div class="list-feedback">
                                            <!-- Device -->
                                            <fieldset class="device-name-field">
                                                <legend class="legend-device-name">Device Name</legend>
                                                <select name="tmpDevice" class="select-device">
                                                    <option value="">Choose a device</option>
                                                    <c:forEach var="facility" items="${sessionScope.FACILTIES_LIST_ALL}">
                                                        <option value="${facility.facilityID}">${facility.facilityName}</option>
                                                    </c:forEach>
                                                </select>
                                            </fieldset>
                                            <!-- End device -->
                                            <!-- Location -->
                                            <fieldset class="location-field">
                                                <legend class="legend-location">Location</legend>
                                                <input
                                                    type="text"
                                                    name="tmpLocation"
                                                    class="select-location"
                                                    />
                                            </fieldset>
                                            <!-- End Location -->
                                            <!-- Reason -->
                                            <fieldset class="reason-field">
                                                <legend class="legend-reason">Reason</legend>
                                                <select name="tmpReason" class="select-reason">
                                                    <option value="">Choose your reason</option>
                                                    <option value="broken">Broken</option>
                                                    <option value="missing">Missing</option>
                                                    <option value="others">Others</option>
                                                </select>
                                            </fieldset>
                                            <!-- End reason -->
                                            <!-- Quantity -->
                                            <fieldset class="quantity-field">
                                                <legend class="legend-quantity">Quantity</legend>
                                                <input
                                                    type="number"
                                                    name="tmpQuantity"
                                                    class="select-quantity"
                                                    />
                                            </fieldset>
                                        </div>
                                        <!-- Description -->
                                        <div class="text">
                                            <fieldset class="description-field">
                                                <legend class="legend-description">Description</legend>
                                                <textarea
                                                    name="tmpDescription"
                                                    class="select-description"
                                                    ></textarea>
                                            </fieldset>
                                            <!-- End Description -->
                                            <div class="input-file-wrap">
                                                <label>
                                                    <ion-icon
                                                        name="image-outline"
                                                        class="input-file-select"
                                                        onclick="handleShowImage(event)"
                                                        data-index="2"
                                                        ></ion-icon>
                                                    <input
                                                        type="file"
                                                        name="image-2"
                                                        id="gallery-photo-add-2"
                                                        style="display: none"
                                                        />
                                                </label>
                                                <input type="text" class="input-file-name" readonly />
                                            </div>
                                            <div class="img-feedback tab-img-2"></div>
                                        </div>
                                    </div>
                                    <div class="list-items-wrapper">
                                        <div class="list-feedback">
                                            <!-- Device -->
                                            <fieldset class="device-name-field">
                                                <legend class="legend-device-name">Device Name</legend>
                                                <select name="tmpDevice" class="select-device">
                                                    <option value="">Choose a device</option>
                                                    <c:forEach var="facility" items="${sessionScope.FACILTIES_LIST_ALL}">
                                                        <option value="${facility.facilityID}">${facility.facilityName}</option>
                                                    </c:forEach>
                                                </select>
                                            </fieldset>
                                            <!-- End device -->
                                            <!-- Location -->
                                            <fieldset class="location-field">
                                                <legend class="legend-location">Location</legend>
                                                <input
                                                    type="text"
                                                    name="tmpLocation"
                                                    class="select-location"
                                                    />
                                            </fieldset>
                                            <!-- End Location -->
                                            <!-- Reason -->
                                            <fieldset class="reason-field">
                                                <legend class="legend-reason">Reason</legend>
                                                <select name="tmpReason" class="select-reason">
                                                    <option value="">Choose your reason</option>
                                                    <option value="broken">Broken</option>
                                                    <option value="missing">Missing</option>
                                                    <option value="others">Others</option>
                                                </select>
                                            </fieldset>
                                            <!-- End reason -->
                                            <!-- Quantity -->
                                            <fieldset class="quantity-field">
                                                <legend class="legend-quantity">Quantity</legend>
                                                <input
                                                    type="number"
                                                    name="tmpQuantity"
                                                    class="select-quantity"
                                                    />
                                            </fieldset>
                                        </div>
                                        <!-- Description -->
                                        <div class="text">
                                            <fieldset class="description-field">
                                                <legend class="legend-description">Description</legend>
                                                <textarea
                                                    name="tmpDescription"
                                                    class="select-description"
                                                    ></textarea>
                                            </fieldset>
                                            <!-- End Description -->
                                            <div class="input-file-wrap">
                                                <label>
                                                    <ion-icon
                                                        name="image-outline"
                                                        class="input-file-select"
                                                        onclick="handleShowImage(event)"
                                                        data-index="3"
                                                        ></ion-icon>
                                                    <input
                                                        type="file"
                                                        name="image-3"
                                                        id="gallery-photo-add-3"
                                                        style="display: none"
                                                        />
                                                </label>
                                                <input type="text" class="input-file-name" readonly />
                                            </div>
                                            <div class="img-feedback tab-img-3"></div>
                                        </div>
                                    </div>
                                    <div class="list-items-wrapper">
                                        <div class="list-feedback">
                                            <!-- Device -->
                                            <fieldset class="device-name-field">
                                                <legend class="legend-device-name">Device Name</legend>
                                                <select name="tmpDevice" class="select-device">
                                                    <option value="">Choose a device</option>
                                                    <c:forEach var="facility" items="${sessionScope.FACILTIES_LIST_ALL}">
                                                        <option value="${facility.facilityID}">${facility.facilityName}</option>
                                                    </c:forEach>
                                                </select>
                                            </fieldset>
                                            <!-- End device -->
                                            <!-- Location -->
                                            <fieldset class="location-field">
                                                <legend class="legend-location">Location</legend>
                                                <input
                                                    type="text"
                                                    name="tmpLocation"
                                                    class="select-location"
                                                    />
                                            </fieldset>
                                            <!-- End Location -->
                                            <!-- Reason -->
                                            <fieldset class="reason-field">
                                                <legend class="legend-reason">Reason</legend>
                                                <select name="tmpReason" class="select-reason">
                                                    <option value="">Choose your reason</option>
                                                    <option value="broken">Broken</option>
                                                    <option value="missing">Missing</option>
                                                    <option value="others">Others</option>
                                                </select>
                                            </fieldset>
                                            <!-- End reason -->
                                            <!-- Quantity -->
                                            <fieldset class="quantity-field">
                                                <legend class="legend-quantity">Quantity</legend>
                                                <input
                                                    type="number"
                                                    name="tmpQuantity"
                                                    class="select-quantity"
                                                    />
                                            </fieldset>
                                        </div>
                                        <!-- Description -->
                                        <div class="text">
                                            <fieldset class="description-field">
                                                <legend class="legend-description">Description</legend>
                                                <textarea
                                                    name="tmpDescription"
                                                    class="select-description"
                                                    ></textarea>
                                            </fieldset>
                                            <!-- End Description -->
                                            <div class="input-file-wrap">
                                                <label>
                                                    <ion-icon
                                                        name="image-outline"
                                                        class="input-file-select"
                                                        onclick="handleShowImage(event)"
                                                        data-index="4"
                                                        ></ion-icon>
                                                    <input
                                                        type="file"
                                                        name="image-4"
                                                        id="gallery-photo-add-4"
                                                        style="display: none"
                                                        />
                                                </label>
                                                <input type="text" class="input-file-name" readonly />
                                            </div>
                                            <div class="img-feedback tab-img-4"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="send-btn">
                                <input type="submit" name="action" value="Send" class="send"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- if you want to open modal, you add class for div[class = 'user-form'] value: 'open' -->
        <div class="user-form ${requestScope.flag}">
            <div class="modal">
                <div class="user-form-main">
                    <form action="UpdateUserController" class="user-form-actual-form" method="post" enctype="multipart/form-data">
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
                        <input type="hidden" name="search" value="${requestScope.SEARCH}"/>
                        <input type="hidden" name="style_list" value="${requestScope.STYLE_LIST}"/>
                        <input type="hidden" name="style_pipe" value="${requestScope.STYLE_PIPE}"/>
                    </form>
                </div>
            </div>
        </div>

        <!-- Pop-up message when submit -->
        <!-- When back end return a message you can add class value 'open' to div [class='feedback-form-message'] -->
        <!-- you need have 2 difference attribute for success and failure, and add to two div[class='form-message-item']  -->
        <div class="feedback-form-message ${requestScope.SEND_FEEDBACK_FLAG}">
            <div class="modal feedback-form-message-modal">
                <div class="feedback-form-message-main">
                    <div class="form-message-item status-success ${requestScope.SEND_SUCCESS}">
                        <div class="item-image-wrap">
                            <img src="./img/success.png" alt="" />
                        </div>
                        <h1 class="status ">Thank You</h1>
                        <p class="desc">
                            We're received your submission, and we'll be in touch soon!
                        </p>
                        <a href="#" class="btn btn--done link">Keep Feedback</a>
                    </div>
                    <div class="form-message-item ${requestScope.SEND_FAILURE}">
                        <div class="item-image-wrap">
                            <img src="./img/failure.png" alt="" />
                        </div>
                        <h1 class="status status-error">Whoops</h1>
                        <p class="desc">
                            Something went wrong. Let's give this another try.
                        </p>
                        <a href="#" class="btn btn--decline link">Try again</a>
                    </div>
                </div>
            </div>
        </div>


        <header class="header">
            <div class="container">
                <div class="header-main">
                    <div class="header-logo">
                        <form action="SearchUserFeedbackController" autocomplete="off">
                            <a href="ShowFacilityStudentController">
                                <img
                                    src="https://cdn.discordapp.com/attachments/770804043794350100/888843339439407104/toolkit.png"
                                    alt="Logo"
                                    class="header-logo-image"
                                    />
                            </a>

                            <div class="input-warp">
                                <img
                                    src="https://cdn.discordapp.com/attachments/770804043794350100/888843711813943326/loupe.png"
                                    alt="searchLogo"
                                    class="input-wrap-image"
                                    />
                                <div class="actual-input">
                                    <input type="text" name="search" id="search" placeholder="Search ..." value="${requestScope.
                                                                                                                   SEARCH}"/>
                                    <input type="hidden" name="amount_done" value="${requestScope.COUNT_FLAG_DONE}">
                                    <input type="hidden" name="amount_decline" value="${requestScope.COUNT_FLAG_DECLINE}">
                                    <input type="hidden" name="amount_onGoing" value="${requestScope.COUNT_FLAG_ONGOING}">
                                    <input type="hidden" name="amount_all" value="${requestScope.COUNT_FLAG_ALL}">
                                    <!--<label>Search ...</label>-->
                                </div>
                            </div>
                        </form>
                    </div>

                    <div class="header-profile">
                        <a href="#" class="header-profile-link">New Feedback</a>
                        <div class="header-profile-dropdown">
                            <div class="header-profile-image-wrapper">
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
                            <div class="dropdown-list">
                                <a href="ShowUserFormController?search=${requestScope.SEARCH}&style_pipe=${requestScope.STYLE_PIPE}&style_list=${requestScope.STYLE_LIST}&user=user&amount_done=${requestScope.COUNT_FLAG_DONE}&amount_onGoing=${requestScope.COUNT_FLAG_ONGOING}&amount_decline=${requestScope.COUNT_FLAG_DECLINE}&amount_all=${requestScope.COUNT_FLAG_ALL}" class="dropdown-item">
                                    <ion-icon name="create-outline"></ion-icon>
                                    Edit Profile
                                </a>
                                <a href="LogoutController" class="dropdown-item">
                                    <img
                                        src="https://cdn.discordapp.com/attachments/770804043794350100/888843284833779792/logout.png"
                                        alt="logout"
                                        class="header-dropdown-image"
                                        />
                                    Sign Out
                                </a>
                            </div>
                        </div>
                        <div class="showcase-item showcase-item-dropdown-select"
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
                                    You have ${sessionScope.NOTIFICATION_QUANTITY} new tasks
                                </h5>
                                <h5 class="showcase-item-dropdown-sub-title sub-title-no">
                                    No notification can be found ...
                                </h5> 

                                <div class="pipe-list">
                                </div>
                            </div>
                            <div
                                class="showcase-item-dropdown-actual-notification"
                                >
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </header>

        <main>
            <section class="hero">
                <div class="container">
                    <div class="hero-main">
                        <div class="content-item-main">
                            <div class="content-item-heading">
                                <ul class="content-item-navigation">
                                    <li class="navigation-item active" data-index="0">
                                        <a href="#">History Feedback</a>
                                    </li>
                                </ul>
                                <div class="content-item-category">
                                    <a href="#" class="${requestScope.STYLE_PIPE}" data-index="0">
                                        <ion-icon name="apps-sharp"></ion-icon>
                                    </a>
                                    <a href="#" class="${requestScope.STYLE_LIST}" data-index="1">
                                        <ion-icon name="list"></ion-icon>
                                    </a>
                                </div>
                            </div>

                            <div class="content-item-main-list">
                                <!--Pipe--> 
                                <div class="content-item-main-category-item ${requestScope.STYLE_PIPE}">
                                    <div class="pipe">
                                        <div class="pipe-column">
                                            <div class="pipe-heading">
                                                <div class="pipe-title-wrapper">
                                                    <span class="done"></span>
                                                    <h2 class="pipe-title">Done</h2>
                                                </div>
                                                <div class="pipe-date">
                                                    Date Added
                                                    <ion-icon name="chevron-down-outline"></ion-icon>
                                                </div>
                                            </div>
                                            <div class="pipe-list" data-index="1">
                                                <c:if test="${empty sessionScope.HISTORY_DONE}">
                                                    <p class="pipe-item-date">No result can be found ...</p>
                                                </c:if>
                                                <%
                                                    List<UserHistoryDTO> listDone = (List<UserHistoryDTO>) session.getAttribute("HISTORY_DONE");
                                                    if (listDone != null) {
                                                        // feedback.getImageFirebase()
                                                        for (UserHistoryDTO feedback : listDone) {
                                                %> 
                                                
                                                <div class="pipe-item">
                                                    <div class="pipe-item-heading">
                                                        <div class="pipe-item-title-wrapper">
                                                            <h3 class="pipe-item-title">Feedback <%=feedback.getFeedbackId()%></h3>
                                                        </div>
                                                        <div class="pipe-item-date"><%=feedback.getDate()%></div>
                                                    </div>


                                                    <div class="image-all-wrapper">
                                                        <%
                                                            // feedback.getImageList() có 
                                                            int count = 1;
                                                            for (String img : feedback.getImageList()) {
                                                                if (!img.equals("")) {
                                                                    if (count == 1 || count == 2) {
                                                        %>
                                                        <div class="pipe-item-image">
                                                            <img
                                                                src="data:image/jpg/png;base64,<%=img%>"
                                                                alt=""
                                                                />
                                                        </div>


                                                        <%                                                        } else if (feedback.getImageList().size() == 3) {
                                                        %>
                                                        <div class="more-wrapper">
                                                            <div class="img-more active">
                                                                <img
                                                                    src="https://icons-for-free.com/iconfiles/png/512/exposure+plus+1+48px-131985226685054051.png"
                                                                    alt=""
                                                                    />
                                                            </div>
                                                        </div>
                                                        <div class="img-more">
                                                            <img
                                                                src="https://www.shareicon.net/data/128x128/2015/09/12/100167_plus_512x512.png"
                                                                alt=""
                                                                />
                                                        </div>

                                                        <%
                                                            break;
                                                        } else {
                                                        %>
                                                        <div class="more-wrapper">
                                                            <div class="img-more">
                                                                <img
                                                                    src="https://icons-for-free.com/iconfiles/png/512/exposure+plus+1+48px-131985226685054051.png"
                                                                    alt=""
                                                                    />
                                                            </div>
                                                            <div class="img-more active">
                                                                <img
                                                                    src="https://www.shareicon.net/data/128x128/2015/09/12/100167_plus_512x512.png"
                                                                    alt=""
                                                                    />
                                                            </div>
                                                        </div>
                                                        <%
                                                                        break;
                                                                    }
                                                                    count++;
                                                                }
                                                            }
                                                        %>  
                                                    </div>



                                                    <div class="pipe-item-bottom">
                                                        <p class="pipe-bottom-item"><%=feedback.getDeviceName()%></p>
                                                        <p class="pipe-bottom-item">Room: <%=feedback.getLocation()%></p>
                                                    </div>
                                                </div>

                                                <%
                                                        }
                                                    }
                                                %> 
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
                                                    <ion-icon name="chevron-down-outline"></ion-icon>
                                                </div>
                                            </div>
                                            <div class="pipe-list" data-index="2">
                                                <c:if test="${empty sessionScope.HISTORY_ONGOING}">
                                                    <p class="pipe-item-date">No result can be found ...</p>
                                                </c:if>
                                                <%
                                                    List<UserHistoryDTO> listOngoing = (List<UserHistoryDTO>) session.getAttribute("HISTORY_ONGOING");
                                                    if (listOngoing != null) {
                                                        for (UserHistoryDTO feedback : listOngoing) {
                                                %> 
                                                <div class="pipe-item">
                                                    <div class="pipe-item-heading">
                                                        <div class="pipe-item-title-wrapper">
                                                            <h3 class="pipe-item-title">Feedback <%=feedback.getFeedbackId()%></h3>
                                                        </div>
                                                        <div class="pipe-item-date"><%=feedback.getDate()%></div>
                                                    </div>


                                                    <div class="image-all-wrapper">
                                                        <%
                                                            int count = 1;
                                                            for (String img : feedback.getImageList()) {
                                                                if (!img.equals("")) {
                                                                    if (count == 1 || count == 2) {

                                                        %>  
                                                        <div class="pipe-item-image">
                                                            <img
                                                                src="data:image/jpg/png;base64,<%=img%>"
                                                                alt=""
                                                                />
                                                        </div>



                                                        <%                                                        } else if (feedback.getImageList().size() == 3) {
                                                        %>
                                                        <div class="more-wrapper">
                                                            <div class="img-more active">
                                                                <img
                                                                    src="https://icons-for-free.com/iconfiles/png/512/exposure+plus+1+48px-131985226685054051.png"
                                                                    alt=""
                                                                    />
                                                            </div>
                                                        </div>
                                                        <div class="img-more">
                                                            <img
                                                                src="https://www.shareicon.net/data/128x128/2015/09/12/100167_plus_512x512.png"
                                                                alt=""
                                                                />
                                                        </div>

                                                        <%
                                                            break;
                                                        } else {
                                                        %>
                                                        <div class="more-wrapper">
                                                            <div class="img-more">
                                                                <img
                                                                    src="https://icons-for-free.com/iconfiles/png/512/exposure+plus+1+48px-131985226685054051.png"
                                                                    alt=""
                                                                    />
                                                            </div>
                                                            <div class="img-more active">
                                                                <img
                                                                    src="https://www.shareicon.net/data/128x128/2015/09/12/100167_plus_512x512.png"
                                                                    alt=""
                                                                    />
                                                            </div>
                                                        </div>
                                                        <%
                                                                        break;
                                                                    }
                                                                    count++;
                                                                }
                                                            }
                                                        %>  
                                                    </div>



                                                    <div class="pipe-item-bottom">
                                                        <p class="pipe-bottom-item"><%=feedback.getDeviceName()%></p>
                                                        <p class="pipe-bottom-item">Room: <%=feedback.getLocation()%></p>
                                                    </div>
                                                </div>
                                                <%
                                                        }
                                                    }
                                                %> 
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
                                                    <ion-icon name="chevron-down-outline"></ion-icon>
                                                </div>
                                            </div>
                                            <div class="pipe-list" data-index="3">
                                                <c:if test="${empty sessionScope.HISTORY_DENY}">
                                                    <p class="pipe-item-date">No result can be found ...</p>
                                                </c:if>
                                                <%
                                                    List<UserHistoryDTO> listDeny = (List<UserHistoryDTO>) session.getAttribute("HISTORY_DENY");
                                                    if (listDeny != null) {
                                                        for (UserHistoryDTO feedback : listDeny) {
                                                %> 
                                                <div class="pipe-item">
                                                    <div class="pipe-item-heading">
                                                        <div class="pipe-item-title-wrapper">
                                                            <h3 class="pipe-item-title">Feedback <%=feedback.getFeedbackId()%></h3>
                                                        </div>
                                                        <div class="pipe-item-date"><%=feedback.getDate()%></div>
                                                    </div>


                                                    <div class="image-all-wrapper">
                                                        <%
                                                            int count = 1;
                                                            for (String img : feedback.getImageList()) {
                                                                if (!img.equals("")) {
                                                                    if (count == 1 || count == 2) {

                                                        %>  
                                                        <div class="pipe-item-image">
                                                            <img
                                                                src="data:image/jpg/png;base64,<%=img%>"
                                                                alt=""
                                                                />
                                                        </div>



                                                        <%                                                        } else if (feedback.getImageList().size() == 3) {
                                                        %>
                                                        <div class="more-wrapper">
                                                            <div class="img-more active">
                                                                <img
                                                                    src="https://icons-for-free.com/iconfiles/png/512/exposure+plus+1+48px-131985226685054051.png"
                                                                    alt=""
                                                                    />
                                                            </div>
                                                        </div>
                                                        <div class="img-more">
                                                            <img
                                                                src="https://www.shareicon.net/data/128x128/2015/09/12/100167_plus_512x512.png"
                                                                alt=""
                                                                />
                                                        </div>

                                                        <%
                                                            break;
                                                        } else {
                                                        %>
                                                        <div class="more-wrapper">
                                                            <div class="img-more">
                                                                <img
                                                                    src="https://icons-for-free.com/iconfiles/png/512/exposure+plus+1+48px-131985226685054051.png"
                                                                    alt=""
                                                                    />
                                                            </div>
                                                            <div class="img-more active">
                                                                <img
                                                                    src="https://www.shareicon.net/data/128x128/2015/09/12/100167_plus_512x512.png"
                                                                    alt=""
                                                                    />
                                                            </div>
                                                        </div>
                                                        <%
                                                                        break;
                                                                    }
                                                                    count++;
                                                                }
                                                            }
                                                        %>  
                                                    </div>



                                                    <div class="pipe-item-bottom">
                                                        <p class="pipe-bottom-item"><%=feedback.getDeviceName()%></p>
                                                        <p class="pipe-bottom-item">Room: <%=feedback.getLocation()%></p>
                                                    </div>
                                                </div>
                                                <%
                                                        }
                                                    }
                                                %> 
                                            </div>
                                        </div>

                                        <c:choose>
                                            <c:when test="${requestScope.COUNT_FLAG_DONE == null}">
                                                <input type="hidden" name="amount_done" value="0">
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="amount_done" value="${requestScope.COUNT_FLAG_DONE}">
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${requestScope.COUNT_FLAG_ONGOING == null}">
                                                <input type="hidden" name="amount_onGoing" value="0">
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="amount_onGoing" value="${requestScope.COUNT_FLAG_ONGOING}">
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${requestScope.COUNT_FLAG_DECLINE == null}">
                                                <input type="hidden" name="amount_decline" value="0">
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="amount_decline" value="${requestScope.COUNT_FLAG_DECLINE}">
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${requestScope.COUNT_FLAG_ALL == null}">
                                                <input type="hidden" name="amount_all" value="0">
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="amount_all" value="${requestScope.COUNT_FLAG_ALL}">
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <!--LIST--> 
                                <div
                                    class="content-item-main-category-item list ${requestScope.STYLE_LIST}"
                                    >
                                    <ul class="list-heading">
                                        <li class="list-item active ${requestScope.STYLE_LIST_ALL}" data-index="0">All</li>
                                        <li class="list-item ${requestScope.STYLE_LIST_DONE}" data-index="1">Done</li>
                                        <li class="list-item ${requestScope.STYLE_LIST_ONGOING}" data-index="2">On-Going</li>
                                        <li class="list-item ${requestScope.STYLE_LIST_DECLINE}" data-index="3">Decline</li>
                                    </ul>
                                    <div class="list-showcase">
                                        <!-- list All -->
                                        <div class="list-showcase-item ${requestScope.STYLE_LIST_ALL}">
                                            <div class="pipe-list" data-index="0">
                                                <c:if test="${empty sessionScope.HISTORY_ALL}">
                                                    <p class="pipe-item-date">No result can be found ...</p>
                                                </c:if>
                                                <%
                                                    List<UserHistoryDTO> listAll = (List<UserHistoryDTO>) session.getAttribute("HISTORY_ALL");
                                                    if (listAll != null) {

                                                        for (UserHistoryDTO feedback : listAll) {
                                                %> 
                                                <div class="pipe-item">
                                                    <div class="pipe-item-heading">
                                                        <div class="pipe-item-title-wrapper">
                                                            <h3 class="pipe-item-title">Feedback <%=feedback.getFeedbackId()%></h3>
                                                        </div>
                                                        <div class="pipe-item-date"><%=feedback.getDate()%></div>
                                                    </div>


                                                    <div class="image-all-wrapper">
                                                        <%
                                                            int count = 1;
                                                            for (String img : feedback.getImageList()) {
                                                                if (!img.equals("")) {

                                                        %>

                                                        <div class="pipe-item-image">
                                                            <img
                                                                src="data:image/jpg/png;base64,<%=img%>"
                                                                alt=""
                                                                />
                                                        </div>

                                                        <%
                                                                }
                                                            }
                                                        %>

                                                    </div>

                                                    <div class="pipe-item-bottom">
                                                        <p class="pipe-bottom-item"><%=feedback.getDeviceName()%></p>
                                                        <p class="pipe-bottom-item">Room: <%=feedback.getLocation()%></p>
                                                    </div>
                                                </div>

                                                <%
                                                        }
                                                    }
                                                %> 
                                            </div>

                                        </div>
                                        <!-- list done -->
                                        <div class="list-showcase-item ${requestScope.STYLE_LIST_DONE}">
                                            <div class="pipe-list" data-index="1">
                                                <c:if test="${empty sessionScope.HISTORY_DONE}">
                                                    <p class="pipe-item-date">No result can be found ...</p>
                                                </c:if>
                                                <%
                                                    if (listDone != null) {

                                                        for (UserHistoryDTO feedback : listDone) {
                                                %> 
                                                <div class="pipe-item-done">
                                                    <div class="pipe-item-heading">
                                                        <div class="pipe-item-title-wrapper">
                                                            <h3 class="pipe-item-title">Feedback <%=feedback.getFeedbackId()%></h3>
                                                        </div>
                                                        <div class="pipe-item-date"><%=feedback.getDate()%></div>
                                                    </div>


                                                    <div class="image-all-wrapper">
                                                        <%
                                                            int count = 1;
                                                            for (String img : feedback.getImageList()) {
                                                                if (!img.equals("")) {

                                                        %>

                                                        <div class="pipe-item-image">
                                                            <img
                                                                src="data:image/jpg/png;base64,<%=img%>"
                                                                alt=""
                                                                />
                                                        </div>

                                                        <%
                                                                }
                                                            }
                                                        %>

                                                    </div>

                                                    <div class="pipe-item-bottom">
                                                        <p class="pipe-bottom-item"><%=feedback.getDeviceName()%></p>
                                                        <p class="pipe-bottom-item">Room: <%=feedback.getLocation()%></p>
                                                    </div>
                                                </div>

                                                <%
                                                        }
                                                    }
                                                %> 
                                            </div>
                                        </div>
                                        <!-- list on-going -->
                                        <div class="list-showcase-item ${requestScope.STYLE_LIST_ONGOING}">
                                            <div class="pipe-list" data-index="2">
                                                <c:if test="${empty sessionScope.HISTORY_ONGOING}">
                                                    <p class="pipe-item-date">No result can be found ...</p>
                                                </c:if>
                                                <%
                                                    if (listOngoing != null) {
                                                        for (UserHistoryDTO feedback : listOngoing) {
                                                %> 
                                                <div class="pipe-item">
                                                    <div class="pipe-item-heading">
                                                        <div class="pipe-item-title-wrapper">
                                                            <h3 class="pipe-item-title">Feedback <%=feedback.getFeedbackId()%></h3>
                                                        </div>
                                                        <div class="pipe-item-date"><%=feedback.getDate()%></div>
                                                    </div>


                                                    <div class="image-all-wrapper">
                                                        <%
                                                            int count = 1;
                                                            for (String img : feedback.getImageList()) {
                                                                if (!img.equals("")) {

                                                        %>  
                                                        <div class="pipe-item-image">
                                                            <img
                                                                src="data:image/jpg/png;base64,<%=img%>"
                                                                alt=""
                                                                />
                                                        </div>

                                                        <%
                                                                }
                                                            }
                                                        %>
                                                    </div>



                                                    <div class="pipe-item-bottom">
                                                        <p class="pipe-bottom-item"><%=feedback.getDeviceName()%></p>
                                                        <p class="pipe-bottom-item">Room: <%=feedback.getLocation()%></p>
                                                    </div>
                                                </div>
                                                <%
                                                        }
                                                    }
                                                %> 
                                            </div>
                                        </div>
                                        <!-- list decline -->
                                        <div class="list-showcase-item ${requestScope.STYLE_LIST_DECLINE}">
                                            <div class="pipe-list" data-index="3">
                                                <c:if test="${empty sessionScope.HISTORY_DENY}">
                                                    <p class="pipe-item-date">No result can be found ...</p>
                                                </c:if>
                                                <%
                                                    if (listDeny != null) {
                                                        for (UserHistoryDTO feedback : listDeny) {
                                                %> 
                                                <div class="pipe-item">
                                                    <div class="pipe-item-heading">
                                                        <div class="pipe-item-title-wrapper">
                                                            <h3 class="pipe-item-title">Feedback <%=feedback.getFeedbackId()%></h3>
                                                        </div>
                                                        <div class="pipe-item-date"><%=feedback.getDate()%></div>
                                                    </div>


                                                    <div class="image-all-wrapper">
                                                        <%
                                                            int count = 1;
                                                            for (String img : feedback.getImageList()) {
                                                                if (!img.equals("")) {
                                                        %>  
                                                        <div class="pipe-item-image">
                                                            <img
                                                                src="data:image/jpg/png;base64,<%=img%>"
                                                                alt=""
                                                                />
                                                        </div>

                                                        <%
                                                                }
                                                            }
                                                        %>
                                                    </div>



                                                    <div class="pipe-item-bottom">
                                                        <p class="pipe-bottom-item"><%=feedback.getDeviceName()%></p>
                                                        <p class="pipe-bottom-item">Room: <%=feedback.getLocation()%></p>
                                                    </div>
                                                </div>
                                                <%
                                                        }
                                                    }
                                                %> 
                                            </div>
                                        </div>

                                        <c:choose>
                                            <c:when test="${requestScope.COUNT_FLAG_DONE == null}">
                                                <input type="hidden" name="amount_done" value="0">
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="amount_done" value="${requestScope.COUNT_FLAG_DONE}">
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${requestScope.COUNT_FLAG_ONGOING == null}">
                                                <input type="hidden" name="amount_onGoing" value="0">
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="amount_onGoing" value="${requestScope.COUNT_FLAG_ONGOING}">
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${requestScope.COUNT_FLAG_DECLINE == null}">
                                                <input type="hidden" name="amount_decline" value="0">
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="amount_decline" value="${requestScope.COUNT_FLAG_DECLINE}">
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${requestScope.COUNT_FLAG_ALL == null}">
                                                <input type="hidden" name="amount_all" value="0">
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="amount_all" value="${requestScope.COUNT_FLAG_ALL}">
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${requestScope.FEEDBACKID_FROM_SEARCH == null}">
                                                <input type="hidden" name="FEEDBACKID_FROM_SEARCH" value="0">
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="FEEDBACKID_FROM_SEARCH" value="${requestScope.FEEDBACKID_FROM_SEARCH}">
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:choose>
                            <c:when test="${sessionScope.LIST_DONE_COUNT == null}">
                                <input type="hidden" name="LIST_DONE_COUNT" value="">
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="LIST_DONE_COUNT" value="${sessionScope.LIST_DONE_COUNT}">
                            </c:otherwise>
                        </c:choose>              
                    </div>
                </div>
            </section>
        </main>

        <footer class="footer">
            <div class="container">
                <div class="footer-top">
                    <div class="footer-column">
                        <h1 class="footer-logo">Neveslos</h1>
                        <ul class="footer-socials">
                            <a href="#" class="footer-socials-item">
                                <ion-icon name="logo-facebook"></ion-icon>
                            </a>
                            <a href="#" class="footer-socials-item">
                                <ion-icon name="logo-twitter"></ion-icon>
                            </a>
                            <a href="#" class="footer-socials-item">
                                <ion-icon name="logo-instagram"></ion-icon>
                            </a>
                            <a href="#" class="footer-socials-item">
                                <ion-icon name="logo-dribbble"></ion-icon>
                            </a>
                        </ul>
                    </div>
                    <div class="footer-column">
                        <h3 class="footer-title second-font">useful Link</h3>
                        <ul class="footer-links">
                            <li class="footer-links-item"><a href="#">About Us</a></li>
                            <li class="footer-links-item"><a href="#">Contact Us</a></li>
                            <li class="footer-links-item">
                                <a href="#">Terms of Services</a>
                            </li>
                        </ul>
                    </div>
                    <div class="footer-column">
                        <h3 class="footer-title second-font">Contact Us</h3>
                        <ul class="footer-phones text">
                            <li class="footer-links-item">
                                <a href="tel:0958237851">
                                    Phone Number :( + 84 ) 958 23 78 51</a
                                >
                            </li>
                        </ul>
                        <ul class="footer-email text">
                            <li class="footer-links-item">
                                <a href="mailto: ducndmse151198@fpt.edu.vn"
                                   >Email: support@fpt.edu.vn</a
                                >
                            </li>
                        </ul>
                        <p class="footer-address">
                            Address: Lô E2a-7, Đường D1 Khu Công nghệ cao, P. Long Thạnh Mỹ,
                            TP. Thủ Đức, TP. Hồ Chí Minh
                        </p>
                    </div>
                </div>
            </div>
            <div class="footer-bottom">
                <div class="container footer-rocket-container">
                    <div class="footer-rocket">
                        <ion-icon name="rocket-sharp"></ion-icon>
                    </div>
                </div>
                <div class="container footer-container">
                    <p class="footer-desc">
                        Copyright © 2021 Neveslos, Designed by <a href="#">MinhDuc</a> All
                        Rights Reserved.
                    </p>
                </div>
            </div>

                            <input id="LOGIN_USER_ID" type="hidden" name="COUNT_RESPONSE" value="${sessionScope.LOGIN_USER.userID}"/>
        </footer>
                                               <!-- Query -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/User1.js"></script>
        <script type="module" src="${pageContext.request.contextPath}/js/firebaseForUser.js"></script>
        <script type="module" src="${pageContext.request.contextPath}/js/firebaseUserfromAdmin.js"></script>
     
        
        <script>
                                                            function handleNotification() {
                                                                const count = document.querySelector('input[name="LIST_DONE_COUNT"]').value;
                                                                $.ajax({
                                                                    type: "POST",
                                                                    url: "/SWP391_PROJECT/NotificationUserController",
                                                                    data: {notification: count},
                                                                    success: function (result) {
                                                                        if (result !== '') {
                                                                            var lenght = result.slice(0, 1);
                                                                            $('.showcase-item-dropdown-actual-notification').addClass('active');
                                                                            $('.showcase-item-dropdown-actual-notification').html(lenght);
                                                                            $('.showcase-item-dropdown-select').addClass('active');
                                                                            $('.showcase-item-dropdown-sub-title').html("You have " + lenght + " new feedback");
                                                                        } else {
                                                                            $('.showcase-item-dropdown-sub-title').html($('.showcase-item-dropdown-sub-title.sub-title-no').text());
                                                                            $('.showcase-item-dropdown-actual-notification').removeClass('active');
                                                                            $('.showcase-item-dropdown-select').removeClass('active');
                                                                        }
                                                                        $('.showcase-item-dropdown-list .pipe-list').html(result.slice(1));

                                                                    }
                                                                });
                                                            }
                                                            //handleNotification();
                                                            //setInterval(handleNotification, 10000);
                                                            var imagesPreview = function (input, placeToInsertImagePreview) {
                                                                if (input.files) {
                                                                    var filesAmount = input.files.length;

                                                                    for (i = 0; i < filesAmount; i++) {
                                                                        var reader = new FileReader();

                                                                        reader.onload = function (event) {
                                                                            $($.parseHTML("<img>"))
                                                                                    .attr("src", event.target.result)
                                                                                    .appendTo(placeToInsertImagePreview);
                                                                        };

                                                                        reader.readAsDataURL(input.files[i]);
                                                                    }
                                                                }
                                                            };

                                                            var imagesPreview2 = function (input) {
                                                                if (input.files) {
                                                                    var filesAmount = input.files.length;

                                                                    for (i = 0; i < filesAmount; i++) {
                                                                        var reader = new FileReader();

                                                                        reader.onload = function (event) {
                                                                            $(".avatar").attr("src", event.target.result);
                                                                        };

                                                                        reader.readAsDataURL(input.files[i]);
                                                                    }
                                                                }
                                                            };

                                                            function loadResultsPipeStyle(index, list) {
                                                                let amount;
                                                                switch (index) {
                                                                    case "0":
                                                                        amount = list.closest('.pipe').querySelector('input[name="amount_all"]').value;
                                                                        break;
                                                                    case "1":
                                                                        amount = list.closest('.pipe').querySelector('input[name="amount_done"]').value;
                                                                        break;
                                                                    case "2":
                                                                        amount = list.closest('.pipe').querySelector('input[name="amount_onGoing"]').value;
                                                                        break;
                                                                    default:
                                                                        amount = list.closest('.pipe').querySelector('input[name="amount_decline"]').value;
                                                                        break;

                                                                }
                                                                ;
                                                                let search = document.querySelector('input[name="search"]').value;
                                                                $.ajax({
                                                                    url: "/SWP391_PROJECT/LoadFeedbackUserPipe",
                                                                    type: "post",
                                                                    data: {
                                                                        amount: amount,
                                                                        flag_navigation: index,
                                                                        search: search
                                                                    },
                                                                    beforeSend: function (xhr) {
                                                                        $(list).after($("<li class='loading'>Loading...</li>").fadeIn('slow')).data("loading", true);
                                                                    },
                                                                    success: function (data) {
                                                                        setTimeout(() => {
                                                                            var $results = $(list);

                                                                            $(".loading").fadeOut('fast', function () {
                                                                                $(this).remove();
                                                                            });
                                                                            switch (index) {
                                                                                case "0":
                                                                                    list.closest('.pipe').querySelector('input[name="amount_all"]').value = data.slice(1, data.indexOf("<") - 1);
                                                                                    break;
                                                                                case "1":
                                                                                    list.closest('.pipe').querySelector('input[name="amount_done"]').value = data.slice(1, data.indexOf("<") - 1);
                                                                                    break;
                                                                                case "2":
                                                                                    list.closest('.pipe').querySelector('input[name="amount_onGoing"]').value = data.slice(1, data.indexOf("<") - 1);
                                                                                    break;
                                                                                default:
                                                                                    list.closest('.pipe').querySelector('input[name="amount_decline"]').value = data.slice(1, data.indexOf("<") - 1);
                                                                                    break;
                                                                            }
                                                                            ;
                                                                            var $data = $(data.slice(data.indexOf("<")));
                                                                            $results.append($data);
                                                                            $data.show("slow");
                                                                            $results.removeData("loading");
                                                                        }, 1500)
                                                                    }
                                                                });
                                                            }
                                                            ;
                                                            function loadResultsListStyle(index, list) {
                                                                let amount;
                                                                switch (index) {
                                                                    case "0":
                                                                        amount = list.closest('.list-showcase').querySelector('input[name="amount_all"]').value;
                                                                        break;
                                                                    case "1":
                                                                        amount = list.closest('.list-showcase').querySelector('input[name="amount_done"]').value;
                                                                        break;
                                                                    case "2":
                                                                        amount = list.closest('.list-showcase').querySelector('input[name="amount_onGoing"]').value;
                                                                        break;
                                                                    default:
                                                                        amount = list.closest('.list-showcase').querySelector('input[name="amount_decline"]').value;
                                                                        break;

                                                                }
                                                                let search = document.querySelector('input[name="search"]').value;
                                                                let FEEDBACKID_FROM_SEARCH = document.querySelector('input[name="FEEDBACKID_FROM_SEARCH"]').value;
                                                                $.ajax({
                                                                    url: "/SWP391_PROJECT/LoadFeedbackUserList",
                                                                    type: "post",
                                                                    data: {
                                                                        amount: amount,
                                                                        flag_navigation: index,
                                                                        search: search,
                                                                        FEEDBACKID_FROM_SEARCH: FEEDBACKID_FROM_SEARCH
                                                                    },
                                                                    beforeSend: function (xhr) {
                                                                        $(list).after($("<li class='loading'>Loading...</li>").fadeIn('slow')).data("loading", true);
                                                                    },
                                                                    success: function (data) {
                                                                        setTimeout(() => {
                                                                            var $results = $(list);

                                                                            $(".loading").fadeOut('fast', function () {
                                                                                $(this).remove();
                                                                            });
                                                                            switch (index) {
                                                                                case "0":
                                                                                    if (search) {
                                                                                        list.closest('.list-showcase').querySelector('input[name="FEEDBACKID_FROM_SEARCH"]').value = data.slice(1, data.indexOf("<") - 1);
                                                                                    } else {
                                                                                        list.closest('.list-showcase').querySelector('input[name="amount_all"]').value = data.slice(1, data.indexOf("<") - 1);
                                                                                    }
                                                                                    break;
                                                                                case "1":
                                                                                    list.closest('.list-showcase').querySelector('input[name="amount_done"]').value = data.slice(1, data.indexOf("<") - 1);
                                                                                    break;
                                                                                case "2":
                                                                                    list.closest('.list-showcase').querySelector('input[name="amount_onGoing"]').value = data.slice(1, data.indexOf("<") - 1);
                                                                                    break;
                                                                                default:
                                                                                    list.closest('.list-showcase').querySelector('input[name="amount_decline"]').value = data.slice(1, data.indexOf("<") - 1);
                                                                                    break;
                                                                            }
                                                                            ;
                                                                            var $data = $(data.slice(data.indexOf("<")));
                                                                            $results.append($data);
                                                                            $data.show("slow");
                                                                            $results.removeData("loading");
                                                                        }, 1500)
                                                                    }
                                                                });
                                                            }
                                                            ;

                                                            $(function () {

                                                                $(document).on("change", ":file", function () {
                                                                    var input = $(this),
                                                                            numFiles = input.get(0).files ? input.get(0).files.length : 1,
                                                                            label = input.val().replace(/\\/g, "/").replace(/.*\//, "");
                                                                    input.trigger("fileselect", [numFiles, label]);
                                                                });

                                                                $(document).ready(function () {
                                                                    $(":file").on("fileselect", function (event, numFiles, label) {
                                                                        var input = $(this).parents(".input-file-wrap").find(":text"),
                                                                                log = numFiles > 1 ? numFiles + " files selected" : label;
                                                                        if (input.length) {
                                                                            input.val(log);
                                                                        }
                                                                    });
                                                                });


                                                                $("#gallery-photo-add-1").on("change", function (e) {
                                                                    imagesPreview(this, "div.tab-img-1");
                                                                });
                                                                $("#gallery-photo-add-2").on("change", function (e) {
                                                                    console.log(e.target);
                                                                    imagesPreview(this, "div.tab-img-2");
                                                                });
                                                                $("#gallery-photo-add-3").on("change", function (e) {
                                                                    console.log(e.target);
                                                                    imagesPreview(this, "div.tab-img-3");
                                                                });
                                                                $("#gallery-photo-add-4").on("change", function (e) {
                                                                    console.log(e.target);
                                                                    imagesPreview(this, "div.tab-img-4");
                                                                });

                                                                $("#image").on("change", function (e) {
                                                                    imagesPreview2(this);
                                                                });

//                                                                Scroll and load more data
                                                                Array.from($(".pipe .pipe-list")).forEach(item => {
                                                                    item.addEventListener('scroll', (e) => {
                                                                        var list = e.target.closest('.pipe .pipe-list');
                                                                        if (!e.target.getAttribute("data-loading")) {
                                                                            if (Math.ceil(list.offsetHeight + list.scrollTop) === list.scrollHeight) {
                                                                                loadResultsPipeStyle(e.target.dataset.index, list);
                                                                            }
                                                                        }
                                                                    });
                                                                });

                                                                Array.from($(".list-showcase-item .pipe-list")).forEach(item => {
                                                                    item.addEventListener('scroll', (e) => {
                                                                        var list = e.target.closest('.list-showcase-item .pipe-list');
                                                                        if (!e.target.getAttribute("data-loading")) {
                                                                            if (Math.ceil(list.offsetHeight + list.scrollTop) === list.scrollHeight) {
                                                                                loadResultsListStyle(e.target.closest('.list-showcase-item .pipe-list').dataset.index, list);
                                                                            }
                                                                        }
                                                                    });
                                                                });


                                                            });
                                                            window.onload = function () {
                                                                const flag = localStorage.getItem('flag');
                                                                if (JSON.parse(flag) === true) {
                                                                    var feedback = JSON.parse(localStorage.getItem('feedbackDoneID'));
                                                                    var id = feedback[1].toString();
                                                                    var loop = document.getElementsByClassName("pipe-item-title");
//        var loopComment = document.getElementsByClassName("pipe-item-title-comment");

                                                                    for (let i = 0; i < Array.from(loop).length; i++) {
                                                                        console.log(Array.from(loop)[i].innerHTML);
                                                                        if ((Array.from(loop)[i].innerHTML).includes(id)) {
                                                                            setTimeout(function () {
                                                                                (loop[i]).scrollIntoView({
                                                                                    behavior: "smooth",
                                                                                    block: "center"
                                                                                });
                                                                            }, 700);
                                                                        }
                                                                    }
                                                                    localStorage.removeItem('flag');

                                                                }
                                                            }
        </script>
    </body>
</html>
