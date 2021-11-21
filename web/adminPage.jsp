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

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminPage.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminPageDetailModal1.css" />
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_ADMIN == null}">
            <c:redirect url="login.jsp"/>
        </c:if>
        <!-- if you want to open modal, you add class for div[class = 'feedbackDetail'] value: 'open' -->
        <div class="feedbackDetail ${requestScope.flag}">
            <div class="modal feedbackDetail-modal">
                <div class="feedbackDetail-main">
                    <div class="detail-main">
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
                            <!-- giải quyết vấn đề đã có response thì sẽ không cho assign người khác nữa -->
                            <c:choose>
                                <c:when test="${feedbackDetail.flag eq true}">
                                    <div class="detail-items ${requestScope.CLASS_NAME} not_edit">
                                        <div class="detail-wrapper">
                                            <div class="detail-info">
                                                <p class="device">${feedbackDetail.deviceName}</p>
                                                <p>Room ${feedbackDetail.location}</p>
                                                <p>Quantity: ${feedbackDetail.quanity}</p>
                                                <p>Reason: ${feedbackDetail.reason}</p>
                                            </div>
                                            <div class="all-wrapper" data-id="${feedbackDetail.feedbackDetailID}">
                                                <script type="module">
                                                    import handleLoadImageForUserFromFirebase from '${pageContext.request.contextPath}/js/firebaseStorageForUser2.js';
                                                    //  getImageString
                                                    let imageString = '${feedbackDetail.imageFirebase}';
                                                    Array.from(document.querySelectorAll('.all-wrapper')).forEach(ele => {
                                                    if(ele.dataset.id === '${feedbackDetail.feedbackDetailID}' && imageString) {
                                                    handleLoadImageForUserFromFirebase(imageString, ele);
                                                    }
                                                    });
                                                </script>
                                            </div>
                                            <div class="employee-name">
                                                <div class="employee-name-heading">
                                                    <p>Employee Name:</p>
                                                    <p class="employee-name-edit">${feedbackDetail.employeeName}</p>
                                                </div>
                                                <form class="assign-form" action="AssignController">
                                                    <div class="assign-wrapper">
                                                        <select name="employee" id="" class="form-select" required>
                                                            <option value="" selected hidden>Choose employee name</option>
                                                            <c:choose>
                                                                <c:when test="${feedbackDetail.categoryDevice eq 'TD'}">
                                                                    <c:forEach var="employee" items="${sessionScope.EMPLOYEE_ELETRIC_LIST}">
                                                                        <c:if test="${feedbackDetail.userID ne employee.userID}">
                                                                            <option id ="employee_ID" value="${employee.userID}">${employee.fullName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </c:when>
                                                                <c:when test="${feedbackDetail.categoryDevice eq 'TN'}">
                                                                    <c:forEach var="employee" items="${sessionScope.EMPLOYEE_WATER_LIST}">
                                                                        <c:if test="${feedbackDetail.userID ne employee.userID}">
                                                                            <option id ="employee_ID" value="${employee.userID}">${employee.fullName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </c:when>
                                                                <c:when test="${feedbackDetail.categoryDevice eq 'EN'}">
                                                                    <c:forEach var="employee" items="${sessionScope.EMPLOYEE_ENVIROMENT_LIST}">
                                                                        <c:if test="${feedbackDetail.userID ne employee.userID}">
                                                                            <option id ="employee_ID" value="${employee.userID}">${employee.fullName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <c:forEach var="employee" items="${sessionScope.EMPLOYEE_OTHER_LIST}">
                                                                        <c:if test="${feedbackDetail.userID ne employee.userID}">
                                                                            <option id ="employee_ID" value="${employee.userID}">${employee.fullName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </c:otherwise>
                                                            </c:choose>
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
                                            <a href="ShowFormDeclineController?feedbackDetailID=${feedbackDetail.feedbackDetailID}&feedbackID=${param.feedbackID}&statusID=${param.statusID}&statusName=${param.statusName}&email=${param.email}&date=${param.date}&style_flag=${requestScope.style_flag}&style_list_category=${requestScope.style_list_category}" class="detail-links">
                                                <ion-icon name="ban"></ion-icon>
                                            </a>
                                        </div>
                                        <div class="description">
                                            <div class="des-box">
                                                <p class="des">Description:</p>
                                                <p class="content">
                                                    ${feedbackDetail.description}
                                                </p>
                                            </div>
                                            <c:if test="${feedbackDetail.check eq true}">
                                                <div class="warning-box active">
                                                    <img class="warning-icon" src="img/exclamation.png"/>
                                                    <div class="invisible-space"></div>
                                                    <div class="decline-reason-dropdown">
                                                        <p class="decline-employee">
                                                            <strong>Employee Name:</strong> ${feedbackDetail.employeeName}
                                                        </p>
                                                        <p class="decline-reason-message">
                                                            <strong>Decline Reason:</strong> ${feedbackDetail.declineReason}
                                                        </p>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="detail-items ${requestScope.CLASS_NAME}">
                                        <div class="detail-wrapper">
                                            <div class="detail-info">
                                                <p class="device">${feedbackDetail.deviceName}</p>
                                                <p>Room ${feedbackDetail.location}</p>
                                                <p>Quantity: ${feedbackDetail.quanity}</p>
                                                <p>Reason: ${feedbackDetail.reason}</p>
                                            </div>
                                            <div class="all-wrapper" data-id="${feedbackDetail.feedbackDetailID}">
                                                <script type="module">
                                                    import handleLoadImageForUserFromFirebase from '${pageContext.request.contextPath}/js/firebaseStorageForUser2.js';
                                                    //  getImageString
                                                    let imageString = '${feedbackDetail.imageFirebase}';
                                                    Array.from(document.querySelectorAll('.all-wrapper')).forEach(ele => {
                                                    if(ele.dataset.id === '${feedbackDetail.feedbackDetailID}' && imageString) {
                                                    handleLoadImageForUserFromFirebase(imageString, ele);
                                                    }
                                                    });
                                                </script>
                                            </div>
                                            <div class="employee-name">
                                                <div class="employee-name-heading">
                                                    <p>Employee Name:</p>
                                                    <p class="employee-name-edit">${feedbackDetail.employeeName}</p>
                                                </div>
                                                <form class="assign-form" action="AssignController">
                                                    <div class="assign-wrapper">
                                                        <select name="employee" id="" class="form-select" required>
                                                            <option value="" selected hidden>Choose employee name</option>
                                                            <c:choose>
                                                                <c:when test="${feedbackDetail.categoryDevice eq 'TD'}">
                                                                    <c:forEach var="employee" items="${sessionScope.EMPLOYEE_ELETRIC_LIST}">
                                                                        <c:if test="${feedbackDetail.userID ne employee.userID}">
                                                                            <option value="${employee.userID}">${employee.fullName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </c:when>
                                                                <c:when test="${feedbackDetail.categoryDevice eq 'TN'}">
                                                                    <c:forEach var="employee" items="${sessionScope.EMPLOYEE_WATER_LIST}">
                                                                        <c:if test="${feedbackDetail.userID ne employee.userID}">
                                                                            <option value="${employee.userID}">${employee.fullName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </c:when>
                                                                <c:when test="${feedbackDetail.categoryDevice eq 'EN'}">
                                                                    <c:forEach var="employee" items="${sessionScope.EMPLOYEE_ENVIROMENT_LIST}">
                                                                        <c:if test="${feedbackDetail.userID ne employee.userID}">
                                                                            <option value="${employee.userID}">${employee.fullName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <c:forEach var="employee" items="${sessionScope.EMPLOYEE_OTHER_LIST}">
                                                                        <c:if test="${feedbackDetail.userID ne employee.userID}">
                                                                            <option value="${employee.userID}">${employee.fullName}</option>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </c:otherwise>
                                                            </c:choose>
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
                                            <a href="ShowFormDeclineController?feedbackDetailID=${feedbackDetail.feedbackDetailID}&feedbackID=${param.feedbackID}&statusID=${param.statusID}&statusName=${param.statusName}&email=${param.email}&date=${param.date}&style_flag=${requestScope.style_flag}&style_list_category=${requestScope.style_list_category}" class="detail-links">
                                                <ion-icon name="ban"></ion-icon>
                                            </a>
                                        </div>
                                        <div class="description">
                                            <div class="des-box">
                                                <p class="des">Description:</p>
                                                <p class="content">
                                                    ${feedbackDetail.description}
                                                </p>
                                            </div>
                                            <c:if test="${feedbackDetail.check eq true}">
                                                <div class="warning-box active">
                                                    <img class="warning-icon" src="img/exclamation.png"/>
                                                    <div class="invisible-space"></div>
                                                    <div class="decline-reason-dropdown">
                                                        <p class="decline-employee">
                                                            <strong>Employee Name:</strong> ${feedbackDetail.employeeName}
                                                        </p>
                                                        <p class="decline-reason-message">
                                                            <strong>Decline Reason:</strong> ${feedbackDetail.declineReason}
                                                        </p>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

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

        <div class="reponse-form ${requestScope.ban_flag}">
            <div class="modal">
                <div class="reponse-form-main">
                    <h2 class="reponse-form-heading">Reason ban feedback detail</h2>
                    <form action="DeclineFeedbackDetailController" class="reponse-form-actual-form" method="post">
                        <input type="hidden" name="feedbackDetailID" value="${requestScope.FEEDBACK_DETAIL_ID}" />
                        <input type="hidden" name="feedbackID" value="${requestScope.FEEDBACK_ID}" />
                        <input type="hidden" name="statusID" value="${requestScope.statusID}" />
                        <input type="hidden" name="statusName" value="${requestScope.statusName}" />
                        <input type="hidden" name="email" value="${requestScope.EMAIL}" />
                        <input type="hidden" name="date" value="${requestScope.DATE}" />
                        <input type="hidden" name="style_flag" value="${requestScope.PIPE_OR_LIST}" />
                        <input type="hidden" name="style_list_category" value="${requestScope.style_list_category}" />
                        <div class="reponse-form-textarea-wrapper">
                            <textarea
                                name="declineReason"
                                id="description"
                                class="reponse-form-textarea"
                                placeholder="Enter message ..."
                                required
                                ></textarea>
                            <label>Description</label>
                        </div>
                        <input type="submit" value="Send" class="submit-btn" />
                    </form>
                </div>
            </div>
        </div>

        <div class="modal-decline ${requestScope.decline_flag}" id="mymodal">
            <div class="modal-decline-box modal">
                <form action="UpdateFeedbackDeclineController" method="post">
                    <input type="hidden" name="responseID" value="${requestScope.responseID}"/>
                    <input type="hidden" name="feedbackDetailID" value="${requestScope.feedbackDetailID}"/>
                    <input type="hidden" name="style_list" value="${requestScope.STYLE_COMMENT}"/>
                    <div class="decline-input">
                        <textarea name="declineReason" id="declineReason" required></textarea>
                        <label class="input-label">Decline Reason</label>
                    </div>
                    <input type="submit" value="Send" class="reply-button"/>
                </form>
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
                                    <a href="FacilityStatisticController" class="showcase-link">
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
                                            No notification can be found
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
                                            <a href="ShowUserFormController?position=adminPage&user=admin" class="dropdown-item">
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
                                        <h2 class="showcase-title">Welcome back, ${sessionScope.LOGIN_ADMIN.fullName}!</h2>
                                        <p class="showcase-desc">
                                            You have <span>${sessionScope.COUNT_OF_SUM} tasks</span> to complete
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
                                                    <div class="pipe-column" data-index="1">
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
                                                        <input type="hidden" name="pending_count" value="${sessionScope.PENDING_COUNT}"/>
                                                        <input type="hidden" name="pending_count_trash" value="${sessionScope.PENDING_TRASH_COUNT}"/>
                                                    </div>
                                                    <div class="pipe-column" data-index="2">
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
                                                    <div class="pipe-column" data-index="3">
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
                                                    <div class="pipe-column" data-index="4">
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
                                                        <div class="pipe-list" data-index="0">
                                                            <c:if test="${empty sessionScope.FEEDBACK_LIST_ALL}">
                                                                <p class="pipe-item-date">
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
                                                        <div class="pipe-list" data-index="1">
                                                            <c:if test="${empty sessionScope.FEEDBACK_LIST_PENDING}">
                                                                <p class="pipe-item-date">
                                                                    No result can be found ...
                                                                </p>
                                                            </c:if>
                                                            <c:forEach var="feedback" items="${sessionScope.FEEDBACK_LIST_PENDING}">
                                                                <a href="ShowEmployeeActiveController?feedbackID=${feedback.feedbackID}&email=${feedback.email}&date=${feedback.date}&statusID=${feedback.statusId}&statusName=${feedback.statusName}&style_flag=list&style_list_category=pending&search=${requestScope.SEARCH}">
                                                                    <div class="pipe-item pipe-list-pending">
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
                                                        <div class="pipe-list" data-index="2">
                                                            <c:if test="${empty sessionScope.FEEDBACK_LIST_FIXING}">
                                                                <p class="pipe-item-date">
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
                                                        <div class="pipe-list" data-index="3">
                                                            <c:if test="${empty sessionScope.FEEDBACK_LIST_DENY}">
                                                                <p class="pipe-item-date">
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
                                                        <div class="pipe-list" data-index="4">
                                                            <c:if test="${empty sessionScope.FEEDBACK_LIST_DONE}">
                                                                <p class="pipe-item-date">
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
                                                                        <h2 class="feedback-detail-tittle">Response ${counter.count}</h2>
                                                                        <div class="feedback-detail-header-form-wrapper">
                                                                            <form action="ShowDeclineReasonFormController" class="pipe-bottom-form">                                     
                                                                                <input type="hidden" name="feedbackDetailID" value="${reponseDetail.feedbackDetailID}"/>                                           
                                                                                <input type="hidden" name="responseID" value="${reponseDetail.responseID}"/>                                           
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
                                                                                <!--reponseDetail.image-->
                                                                                <!--                                                                                <img
                                                                                                                                                                    src="data:image/jpg/png;base64,${reponseDetail.image}"
                                                                                                                                                                    alt=""
                                                                                                                                                                    />-->
                                                                                <script type="module">
                                                                                    import handleLoadImageForUserFromFirebase from '${pageContext.request.contextPath}/js/firebaseStorageForUser2.js';
                                                                                    //  getImageString
                                                                                    let imageString = '${reponseDetail.image}';
                                                                                    let index = ${counter.index};
                                                                                    if(imageString) {
                                                                                    handleLoadImageForUserFromFirebase(imageString, Array.from(document.querySelectorAll('.feedback-detail-image-wrapper'))[index]);
                                                                                    }
                                                                                </script>
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
                                                                        <h2 class="feedback-detail-tittle">Response ${counter.count}</h2>
                                                                        <div class="feedback-detail-header-form-wrapper">
                                                                            <form action="ShowDeclineReasonFormController" class="pipe-bottom-form">
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
                                                                                <script type="module">
                                                                                    import handleLoadImageForUserFromFirebase from '${pageContext.request.contextPath}/js/firebaseStorageForUser2.js';
                                                                                    //  getImageString
                                                                                    let imageString = '${reponseDetail.image}';
                                                                                    let index = ${counter.index};
                                                                                    if(imageString) {
                                                                                    handleLoadImageForUserFromFirebase(imageString, Array.from(document.querySelectorAll('.feedback-detail-image-wrapper'))[index]);
                                                                                    }
                                                                                </script>

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
                    <c:choose>
                        <c:when test="${sessionScope.COUNT_RESPONSE eq null}" >
                            <input id="COUNT_RESPONSE" type="hidden" name="COUNT_RESPONSE" value="0"/>

                        </c:when>
                        <c:otherwise >
                            <input id="COUNT_RESPONSE" type="hidden" name="COUNT_RESPONSE" value="${sessionScope.COUNT_RESPONSE}"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </section>
        </main>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <script src="${pageContext.request.contextPath}/js/adminPage.js"></script>
        <script src="${pageContext.request.contextPath}/js/ManagerFB.js"></script>
        <script type="module" src="${pageContext.request.contextPath}/js/firebaseAdminfromUser1.js"></script>
        <script type="module" src="${pageContext.request.contextPath}/js/firebaseForAdminDone.js"></script>
        <script type="module" src="${pageContext.request.contextPath}/js/firebaseForEMP.js"></script>
        <!-- Query -->
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
//                                                                var lenght = Array.from(document.querySelectorAll('.showcase-item-dropdown-list .notification-item')).length;
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

function loadResultsPipeStyle(index, list) {
    let amount = list.querySelectorAll('.pipe .pipe-item').length;
    let search = document.querySelector('input[name="search"]').value;
    $.ajax({
        url: "/SWP391_PROJECT/LoadFeedback",
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
                var $data = $(data);
                $results.append($data);
                $data.show("slow");
                $results.removeData("loading");
            }, 1500)
        }
    });
}
;

function loadResultsListStyle(index, list) {
    let amount = list.querySelectorAll('.list-showcase-item .pipe-item').length;
    let search = document.querySelector('input[name="search"]').value;
    $.ajax({
        url: "/SWP391_PROJECT/LoadFeedback",
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
                var $data = $(data);
                $results.append($data);
                $data.show("slow");
                $results.removeData("loading");
            }, 1500)
        }
    });
}
;

//                                                    Javascript of load data when scroll of comment in adminPage.jsp
//                                                function loadResultsComments(list) {
//                                                    let amount = list.querySelectorAll('.pipe-comment-item').length;
//                                                    $.ajax({
//                                                        url: "/SWP391_PROJECT/LoadComment",
//                                                        type: "post",
//                                                        data: {
//                                                            amount: amount
//                                                        },
//                                                        beforeSend: function (xhr) {
//                                                            $(list).after($("<li class='loading'>Loading...</li>").fadeIn('slow')).data("loading", true);
//                                                        },
//                                                        success: function (data) {
//                                                            setTimeout(() => {
//                                                                var $results = $(list);
//
//                                                                $(".loading").fadeOut('fast', function () {
//                                                                    $(this).remove();
//                                                                });
//                                                                var $data = $(data);
//                                                                $results.append($data);
//                                                                $data.show("slow");
//                                                                $results.removeData("loading");
//                                                            }, 1500)
//                                                        }
//                                                    });
//                                                }
//                                                ;

$(function () {
//                                                    handleNotification();
//                                                    setInterval(handleNotification, 10000);

    var imagesPreview2 = function (input, placeToInsertImagePreview) {
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

    $("#image").on("change", function (e) {
        imagesPreview2(this);
    });


    Array.from($(".pipe .pipe-list")).forEach(item => {
        item.addEventListener('scroll', (e) => {
            var list = e.target.closest('.pipe .pipe-list');
            if (!e.target.getAttribute("data-loading")) {
                if (Math.ceil(list.offsetHeight + list.scrollTop) === list.scrollHeight) {
                    loadResultsPipeStyle(e.target.closest('.pipe .pipe-column').dataset.index, list);
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

    if ($(".pipe-comment-item.active")[0]) {
        setTimeout(() => {
            $(".pipe-comment-item.active")[0].scrollIntoView({
                behavior: "smooth",
                block: "center"
            });
        }, 700);
    }


//                                                    Javascript of load data when scroll of comment in adminPage.jsp
//                                                    document.querySelector(".comment-list-feedback .pipe-list").addEventListener('scroll', function (e) {
//                                                        var list = e.target.closest('.comment-list-feedback .pipe-list');
//                                                        if (!e.target.getAttribute("data-loading")) {
//                                                            if (Math.ceil(list.offsetHeight + list.scrollTop) === list.scrollHeight) {
//                                                                loadResultsComments(list);
//                                                            }
//                                                        }
//                                                    });
});
//                                                window.onload = function () {
//                                                    console.log("123");
//                                                   
//                                                }
        </script>
    </body>
</html>
