<%-- 
    Document   : EmployeeHome
    Created on : Sep 27, 2021, 7:43:01 PM
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
        <title>Employee Page</title>

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

        <!-- font awesome -->
        <script src="https://kit.fontawesome.com/97ce91ff3b.js" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/EmployeeHome1.css" />
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_EMP == null}">
            <c:redirect url="login.jsp"/>
        </c:if>
        <div class="reponse-form ${requestScope.flag}">
            <div class="modal">
                <div class="reponse-form-main">
                    <h2 class="reponse-form-heading">Feedback Detail ${requestScope.COUNT}</h2>
                    <form action="AddResponseController" class="reponse-form-actual-form" enctype="multipart/form-data" method="post" id="response_form">
                        <input type="hidden" name="feedbackDetailID" value="${requestScope.FEEDBACK_DETAIL_ID}" />
                        <div class="reponse-form-textarea-wrapper">
                            <textarea
                                name="description"
                                id="description"
                                class="reponse-form-textarea"
                                placeholder="Enter message ..."
                                ></textarea>
                            <label>Description</label>
                        </div>
                        <div class="reponse-form-image-wrapper">
                            <div class="reponse-form-image-show"></div>
                            <div class="reponse-form-drag-area">
                                <div class="icon">
                                    <i class="fas fa-cloud-upload-alt"></i>
                                </div>
                                <header>Drag & Drop to Upload File</header>
                                <span>OR</span>
                                <label>
                                    <p>Browse File</p>
                                    <input
                                        type="file"
                                        name="image_2"
                                        id="image"
                                        style="display: none"
                                        multiple
                                        />
                                </label>
                            </div>
                        </div>

                        <input type="submit" value="Send" class="submit-btn" />
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
                        <h1 class="status">Thank You</h1>
                        <p class="desc">
                            You have already response to this feedback successfully !
                        </p>
                    </div>
                    <div class="form-message-item ${requestScope.SEND_FAILURE}">
                        <div class="item-image-wrap">
                            <img src="./img/failure.png" alt="" />
                        </div>
                        <h1 class="status status-error">Whoops</h1>
                        <p class="desc">
                            Something went wrong. Let's give this another try.
                        </p>
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
                                        id="avatarImage"
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

        <div class="loader">
            <div class="modal loader-modal">
                <div class="loader-main">
                    <div class="lds-dual-ring"></div>
                    <p>Loading ...</p>
                </div>
            </div>
        </div>

        <main>
            <section class="showcase">
                <div class="container">
                    <div class="showcase-main">
                        <div class="showcase-header">
                            <div class="showcase-logo">
                                <a href="ShowFeedbackForEmpController">
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
                                    <a href="ShowFeedbackForEmpController" class="showcase-link">
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
                                        <div class="showcase-profile-avatar">
                                            <c:set var="avatar" value="${sessionScope.LOGIN_EMP.image}"></c:set>
                                            <c:choose>
                                                <c:when test="${fn:startsWith(avatar, 'http')}">
                                                    <img
                                                        src="${sessionScope.LOGIN_EMP.image}"
                                                        alt=""
                                                        />
                                                </c:when>
                                                <c:otherwise>
                                                    <img
                                                        src="data:image/jpg/png;base64,${sessionScope.LOGIN_EMP.image}"
                                                        alt=""
                                                        />
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="showcase-profile-dropdown-list">
                                            <a href="ShowUserFormController?position=EmployeePage&user=employee" class="dropdown-item">
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
                                    <form action="SearchTaskEmpController" class="showcase-form">
                                        <div class="input-hidden-wrapper">
                                            <input type="hidden" name="LIST_STYLE_TASK" value="active"/>
                                            <input type="hidden" name="style_list" value="task"/>
                                        </div>
                                        <input
                                            type="text"
                                            name="search"
                                            placeholder="Search project..."
                                            value="${sessionScope.SEARCH}"
                                            />
                                        <button type="submit">
                                            <ion-icon name="search"></ion-icon>
                                        </button>
                                        <input type="hidden" name="FEEDBACK_DETAIL_ACTIVE" value="${requestScope.FEEDBACK_ACTIVE}"/>
                                        <input type="hidden" name="HISTORY_DETAIL_ACTIVE" value="${requestScope.HISTORY_ACTIVE}"/>
                                    </form>
                                    <div class="showcase-title-wrapper">
                                        <h2 class="showcase-title">Welcome back, ${sessionScope.LOGIN_EMP.fullName}</h2>
                                        <p class="showcase-desc">
                                            You have <span>${sessionScope.COUNT} tasks</span> to complete
                                        </p>
                                    </div>
                                </div>
                                <div class="content-item-main">
                                    <div class="content-item-heading">
                                        <ul class="content-item-navigation">
                                            <li class="navigation-item ${requestScope.LIST_STYLE_TASK}" data-index="0">
                                                <a href="#">Tasks</a>
                                            </li>
                                            <li class="navigation-item ${requestScope.LIST_STYLE_HISTORY}" data-index="1">
                                                <a href="#">History</a>
                                            </li>
                                            <span class="navigation-scroll-bar"></span>
                                        </ul>
                                    </div>

                                    <div class="content-item-main-list">
                                        <!-- Tasks -->
                                        <div
                                            class="
                                            content-item-main-item
                                            employee-item-main-item
                                            ${requestScope.LIST_STYLE_TASK}
                                            "
                                            >
                                            <div class="pipe">
                                                <div class="pipe-column pipe-columm-list">
                                                    <div class="pipe-list">
                                                        <c:if test="${empty sessionScope.LIST_FEEDBACK}">
                                                            <p class="pipe-item-date">No result can be found ...</p>
                                                        </c:if>
                                                        <c:forEach var="feedback" items="${sessionScope.LIST_FEEDBACK}" varStatus="counter">
                                                            <c:choose>
                                                                <c:when test="${requestScope.FEEDBACK_ACTIVE == null && counter.count == 1}">
                                                                    <a href="ShowFeedbackDetailForEmpController?feedbackID=${feedback.feedbackID}&history=${requestScope.HISTORY_ACTIVE}&style_list=task">
                                                                        <div class="pipe-item task-item active">
                                                                            <div class="pipe-item-heading">
                                                                                <div class="pipe-item-title-wrapper">
                                                                                    <h3 class="pipe-item-title">Feedback ${feedback.feedbackID}</h3>
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
                                                                                <div class="pipe-bottom-links">
                                                                                    <form action="EmployeeDeclineController" method="post">
                                                                                        <input type="hidden" name="feedbackId" value="${feedback.feedbackID}"/>
                                                                                        <input type="hidden" name="history" value="${requestScope.HISTORY_ACTIVE}"/>
                                                                                        <button
                                                                                            type="submit"
                                                                                            onclick="return confirm('Do you want to decline this task?')"
                                                                                            class="btn-submit-links trash"
                                                                                            >
                                                                                            <ion-icon name="trash"></ion-icon>
                                                                                        </button>
                                                                                    </form>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </a>
                                                                </c:when>
                                                                <c:when test="${feedback.feedbackID eq requestScope.FEEDBACK_ACTIVE}">
                                                                    <a href="ShowFeedbackDetailForEmpController?feedbackID=${feedback.feedbackID}&history=${requestScope.HISTORY_ACTIVE}&style_list=task">
                                                                        <div class="pipe-item task-item active">
                                                                            <div class="pipe-item-heading">
                                                                                <div class="pipe-item-title-wrapper">
                                                                                    <h3 class="pipe-item-title">Feedback ${feedback.feedbackID}</h3>
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
                                                                                <div class="pipe-bottom-links">
                                                                                    <form action="EmployeeDeclineController" method="post">
                                                                                        <input type="hidden" name="feedbackId" value="${feedback.feedbackID}"/>
                                                                                        <input type="hidden" name="history" value="${requestScope.HISTORY_ACTIVE}"/>
                                                                                        <button
                                                                                            type="submit"
                                                                                            onclick="return confirm('Do you want to decline this task?')"
                                                                                            class="btn-submit-links trash "
                                                                                            >
                                                                                            <ion-icon name="trash"></ion-icon>
                                                                                        </button>
                                                                                    </form>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </a>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <a href="ShowFeedbackDetailForEmpController?feedbackID=${feedback.feedbackID}&history=${requestScope.HISTORY_ACTIVE}&style_list=task">
                                                                        <div class="pipe-item task-item">
                                                                            <div class="pipe-item-heading">
                                                                                <div class="pipe-item-title-wrapper">
                                                                                    <h3 class="pipe-item-title">Feedback ${feedback.feedbackID}</h3>
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
                                                                                <div class="pipe-bottom-links">
                                                                                    <form action="EmployeeDeclineController" method="post" onsubmit="return decline()">
                                                                                        <input type="hidden" name="feedbackId" value="${feedback.feedbackID}"/>
                                                                                        <input type="hidden" name="history" value="${requestScope.HISTORY_ACTIVE}"/>
                                                                                        <button
                                                                                            type="submit"
                                                                                            onclick="decline()"
                                                                                            class="btn-submit-links trash"
                                                                                            >
                                                                                            <ion-icon name="trash"></ion-icon>
                                                                                        </button>
                                                                                    </form>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </a>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                                <div
                                                    class="pipe-column feedback-detail task-detail-wrapper"
                                                    style="background-color: #fff;"
                                                    >
                                                    <div class="icon-chevron-back task-chevron-back">
                                                        <ion-icon name="chevron-back-outline"></ion-icon>
                                                    </div>
                                                    <div
                                                        class="icon-chevron-forward task-chevron-forward"
                                                        >
                                                        <ion-icon name="chevron-forward-outline"></ion-icon>
                                                    </div>
                                                    <c:forEach var="feedbackDetail" items="${sessionScope.DETAIL}" varStatus="counter">
                                                        <c:choose>
                                                            <c:when test="${feedbackDetail.feedbackDetailID eq requestScope.FEEDBACK_DETAIL_ACTIVE}">
                                                                <div
                                                                    class="detail-wrapper task-detail active"
                                                                    data-index="${counter.count}"
                                                                    >
                                                                    <div class="feedback-detail-header">
                                                                        <h2 class="feedback-detail-tittle">Feedback Detail ${counter.count}</h2>
                                                                        <form
                                                                            action="ShowEmployeeFormController?feedbackDetailID=${feedbackDetail.feedbackDetailID}&history=${requestScope.HISTORY_ACTIVE}&count=${counter.count}"
                                                                            method="post"
                                                                            class="form-reponse"
                                                                            >
                                                                            <button
                                                                                type="submit"
                                                                                class="btn-submit-links "
                                                                                >
                                                                                <img src="img/forward-message.png"/>
                                                                            </button>
                                                                        </form>
                                                                    </div>
                                                                    <div class="feedback-detail-showcase">
                                                                        <div class="feedback-detail-input">
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="device"
                                                                                    id="device"
                                                                                    value="${feedbackDetail.deviceName}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Device Name</label>
                                                                            </div>
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="location"
                                                                                    id="location"
                                                                                    value="Room ${feedbackDetail.location}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Location</label>
                                                                            </div>
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="reason"
                                                                                    id="reason"
                                                                                    value="${feedbackDetail.reason}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Reason</label>
                                                                            </div>
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="number"
                                                                                    name="quantity"
                                                                                    id="quantity"
                                                                                    value="${feedbackDetail.quanity}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Quantity</label>
                                                                            </div>
                                                                            <c:if test="${feedbackDetail.check eq true}">
                                                                                <div class="responsearea decline">
                                                                                    <div class="response-manager">
                                                                                        <img src="img/cancel.png"/>
                                                                                        <div class="textarea-wrapper">
                                                                                            <textarea
                                                                                                name="declineReason"
                                                                                                id="declineReason"
                                                                                                readonly
                                                                                                >${feedbackDetail.declineReason}</textarea>
                                                                                            <label class="input-label">Decline Reason</label>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                        </div>
                                                                        <div class="feedback-detail-textarea">
                                                                            <div class="textarea-wrapper">
                                                                                <textarea
                                                                                    name="description"
                                                                                    id="description"
                                                                                    readonly
                                                                                    >${feedbackDetail.description}</textarea
                                                                                >
                                                                                <label class="input-label">Description</label>
                                                                            </div>
                                                                            <div class="feedback-detail-image-wrapper task-detail-image-wrapper">
                                                                                <c:if test="${feedbackDetail.image ne ''}">
                                                                                    <!--                                                                                    <img
                                                                                                                                                                            src="data:image/jpg/png;base64,${feedbackDetail.image}"
                                                                                                                                                                            alt=""
                                                                                                                                                                            />-->
                                                                                </c:if>
                                                                                <script type="module">
                                                                                    import handleLoadImageForUserFromFirebase from '${pageContext.request.contextPath}/js/firebaseStorageForUser2.js';
                                                                                    //  getImageString
                                                                                    let imageString = '${feedbackDetail.imageFirebase}';
                                                                                    let index = ${counter.index};
                                                                                    if(imageString) {
                                                                                    handleLoadImageForUserFromFirebase(imageString, Array.from(document.querySelectorAll('.task-detail-image-wrapper'))[index]);
                                                                                    }
                                                                                </script>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:when>
                                                            <c:when test="${requestScope.FEEDBACK_DETAIL_ACTIVE == null && counter.count == 1}">
                                                                <div
                                                                    class="detail-wrapper task-detail active"
                                                                    data-index="${counter.count}"
                                                                    >
                                                                    <div class="feedback-detail-header">
                                                                        <h2 class="feedback-detail-tittle">Feedback Detail ${counter.count}</h2>
                                                                        <form
                                                                            action="ShowEmployeeFormController?feedbackDetailID=${feedbackDetail.feedbackDetailID}&history=${requestScope.HISTORY_ACTIVE}&count=${counter.count}"
                                                                            method="post"
                                                                            class="form-reponse"
                                                                            >
                                                                            <button
                                                                                type="submit"
                                                                                class="btn-submit-links"
                                                                                >
                                                                                <img src="img/forward-message.png"/>
                                                                            </button>
                                                                        </form>
                                                                    </div>
                                                                    <div class="feedback-detail-showcase">
                                                                        <div class="feedback-detail-input">
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="device"
                                                                                    id="device"
                                                                                    value="${feedbackDetail.deviceName}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Device Name</label>
                                                                            </div>
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="location"
                                                                                    id="location"
                                                                                    value="Room ${feedbackDetail.location}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Location</label>
                                                                            </div>
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="reason"
                                                                                    id="reason"
                                                                                    value="${feedbackDetail.reason}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Reason</label>
                                                                            </div>
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="number"
                                                                                    name="quantity"
                                                                                    id="quantity"
                                                                                    value="${feedbackDetail.quanity}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Quantity</label>
                                                                            </div>
                                                                            <c:if test="${feedbackDetail.check eq true}">
                                                                                <div class="responsearea decline">
                                                                                    <div class="response-manager">
                                                                                        <img src="img/cancel.png"/>
                                                                                        <div class="textarea-wrapper">
                                                                                            <textarea
                                                                                                name="declineReason"
                                                                                                id="declineReason"
                                                                                                readonly
                                                                                                >${feedbackDetail.declineReason}</textarea>
                                                                                            <label class="input-label">Decline Reason</label>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                        </div>
                                                                        <div class="feedback-detail-textarea">
                                                                            <div class="textarea-wrapper">
                                                                                <textarea
                                                                                    name="description"
                                                                                    id="description"
                                                                                    readonly
                                                                                    >${feedbackDetail.description}</textarea
                                                                                >
                                                                                <label class="input-label">Description</label>
                                                                            </div>
                                                                            <div class="feedback-detail-image-wrapper task-detail-image-wrapper">
                                                                                <c:if test="${feedbackDetail.image ne ''}">
                                                                                    <!--                                                                                    <img
                                                                                                                                                                            src="data:image/jpg/png;base64,${feedbackDetail.image}"
                                                                                                                                                                            alt=""
                                                                                                                                                                            />-->
                                                                                </c:if>
                                                                                <script type="module">
                                                                                    import handleLoadImageForUserFromFirebase from '${pageContext.request.contextPath}/js/firebaseStorageForUser2.js';
                                                                                    //  getImageString
                                                                                    let imageString = '${feedbackDetail.imageFirebase}';
                                                                                    let index = ${counter.index};
                                                                                    if(imageString) {
                                                                                    handleLoadImageForUserFromFirebase(imageString, Array.from(document.querySelectorAll('.task-detail-image-wrapper'))[index]);
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
                                                                        <h2 class="feedback-detail-tittle">Feedback Detail ${counter.count}</h2>
                                                                        <form
                                                                            action="ShowEmployeeFormController?feedbackDetailID=${feedbackDetail.feedbackDetailID}&history=${requestScope.HISTORY_ACTIVE}&count=${counter.count}"
                                                                            method="post"
                                                                            class="form-reponse"
                                                                            >
                                                                            <button
                                                                                type="submit"
                                                                                class="btn-submit-links"
                                                                                >
                                                                                <img src="img/forward-message.png"/>
                                                                            </button>
                                                                        </form>
                                                                    </div>
                                                                    <div class="feedback-detail-showcase">
                                                                        <div class="feedback-detail-input">
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="device"
                                                                                    id="device"
                                                                                    value="${feedbackDetail.deviceName}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Device Name</label>
                                                                            </div>
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="location"
                                                                                    id="location"
                                                                                    value="Room ${feedbackDetail.location}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Location</label>
                                                                            </div>
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="text"
                                                                                    name="reason"
                                                                                    id="reason"
                                                                                    value="${feedbackDetail.reason}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Reason</label>
                                                                            </div>
                                                                            <div class="input-wrapper">
                                                                                <input
                                                                                    type="number"
                                                                                    name="quantity"
                                                                                    id="quantity"
                                                                                    value="${feedbackDetail.quanity}"
                                                                                    readonly
                                                                                    />
                                                                                <label class="input-label">Quantity</label>
                                                                            </div>
                                                                            <c:if test="${feedbackDetail.check eq true}">
                                                                                <div class="responsearea decline">
                                                                                    <div class="response-manager">
                                                                                        <img src="img/cancel.png"/>
                                                                                        <div class="textarea-wrapper">
                                                                                            <textarea
                                                                                                name="declineReason"
                                                                                                id="declineReason"
                                                                                                readonly
                                                                                                >${feedbackDetail.declineReason}</textarea>
                                                                                            <label class="input-label">Decline Reason</label>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                        </div>
                                                                        <div class="feedback-detail-textarea">
                                                                            <div class="textarea-wrapper">
                                                                                <textarea
                                                                                    name="description"
                                                                                    id="description"
                                                                                    readonly
                                                                                    >${feedbackDetail.description}</textarea
                                                                                >
                                                                                <label class="input-label">Description</label>
                                                                            </div>
                                                                            <div class="feedback-detail-image-wrapper task-detail-image-wrapper">
                                                                                <c:if test="${feedbackDetail.image ne ''}">
                                                                                    <!--                                                                                    <img
                                                                                                                                                                            src="data:image/jpg/png;base64,${feedbackDetail.image}"
                                                                                                                                                                            alt=""
                                                                                                                                                                            />-->
                                                                                </c:if>
                                                                                <script type="module">
                                                                                    import handleLoadImageForUserFromFirebase from '${pageContext.request.contextPath}/js/firebaseStorageForUser2.js';
                                                                                    //  getImageString
                                                                                    let imageString = '${feedbackDetail.imageFirebase}';
                                                                                    let index = ${counter.index};
                                                                                    if(imageString) {
                                                                                    handleLoadImageForUserFromFirebase(imageString, Array.from(document.querySelectorAll('.task-detail-image-wrapper'))[index]);
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
                                        <!-- History -->
                                        <div class="content-item-main-item ${requestScope.LIST_STYLE_HISTORY}">
                                            <div class="pipe">
                                                <div class="pipe-column pipe-columm-list">
                                                    <div class="pipe-list">
                                                        <c:if test="${empty sessionScope.LIST_HISTORY}">
                                                            <p class="pipe-item-date">No result can be found ...</p>
                                                        </c:if>
                                                        <c:forEach var="feedback" items="${sessionScope.LIST_HISTORY}" varStatus="counter">
                                                            <c:choose>
                                                                <c:when test="${requestScope.HISTORY_ACTIVE == null && counter.count == 1}">
                                                                    <a href="ShowFeedbackDetailForEmpController?history=${feedback.feedbackID}&feedbackID=${requestScope.FEEDBACK_ACTIVE}&style_list=history">
                                                                        <div class="pipe-item history-item active">
                                                                            <div class="pipe-item-heading">
                                                                                <div class="pipe-item-title-wrapper">
                                                                                    <h3 class="pipe-item-title">Feedback ${feedback.feedbackID}</h3>
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
                                                                </c:when>
                                                                <c:when test="${feedback.feedbackID eq requestScope.HISTORY_ACTIVE}">
                                                                    <a href="ShowFeedbackDetailForEmpController?history=${feedback.feedbackID}&feedbackID=${requestScope.FEEDBACK_ACTIVE}&style_list=history">
                                                                        <div class="pipe-item history-item active">
                                                                            <div class="pipe-item-heading">
                                                                                <div class="pipe-item-title-wrapper">
                                                                                    <h3 class="pipe-item-title">Feedback ${feedback.feedbackID}</h3>
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
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <a href="ShowFeedbackDetailForEmpController?history=${feedback.feedbackID}&feedbackID=${requestScope.FEEDBACK_ACTIVE}&style_list=history">
                                                                        <div class="pipe-item history-item">
                                                                            <div class="pipe-item-heading">
                                                                                <div class="pipe-item-title-wrapper">
                                                                                    <h3 class="pipe-item-title">Feedback ${feedback.feedbackID}</h3>
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
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                                <div
                                                    class="pipe-column feedback-detail history-detail-wrapper"
                                                    style="background-color: #fff"
                                                    >
                                                    <div class="icon-chevron-back history-chevron-back">
                                                        <ion-icon name="chevron-back-outline"></ion-icon>
                                                    </div>
                                                    <div
                                                        class="icon-chevron-forward history-chevron-forward"
                                                        >
                                                        <ion-icon name="chevron-forward-outline"></ion-icon>
                                                    </div>
                                                    <c:forEach var="response" items="${sessionScope.HISTORY_DETAIL}" varStatus="counter">
                                                        <c:if test="${counter.count == 1}">
                                                            <div
                                                                class="detail-wrapper history-detail active"
                                                                data-index="${counter.count}"
                                                                >
                                                                <div class="feedback-detail-header">
                                                                    <h2 class="feedback-detail-tittle">Feedback Detail ${counter.count}</h2>
                                                                </div>
                                                                <div class="feedback-detail-showcase">
                                                                    <div class="feedback-detail-input">
                                                                        <div class="input-wrapper">
                                                                            <input
                                                                                type="text"
                                                                                name="device"
                                                                                id="device"
                                                                                value="${response.deviceName}"
                                                                                readonly
                                                                                />
                                                                            <label class="input-label">Device Name</label>
                                                                        </div>
                                                                        <div class="input-wrapper">
                                                                            <input
                                                                                type="text"
                                                                                name="location"
                                                                                id="location"
                                                                                value="Room ${response.location}"
                                                                                readonly
                                                                                />
                                                                            <label class="input-label">Location</label>
                                                                        </div>
                                                                        <div class="input-wrapper">
                                                                            <input
                                                                                type="number"
                                                                                name="quantity"
                                                                                id="quantity"
                                                                                value="${response.quantity}"
                                                                                readonly
                                                                                />
                                                                            <label class="input-label">Quantity</label>
                                                                        </div>

                                                                        <c:if test="${response.checkDone eq true}">
                                                                            <div class="responsearea done">
                                                                                <div class="response-manager">
                                                                                    <img src="img/checked.png"/>
                                                                                    <div class="done-label">
                                                                                        You have finished this task 
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </c:if>
                                                                        <c:if test="${response.check eq true}">
                                                                            <div class="responsearea decline">
                                                                                <div class="response-manager">
                                                                                    <img src="img/cancel.png"/>
                                                                                    <div class="textarea-wrapper">
                                                                                        <textarea
                                                                                            name="declineReason"
                                                                                            id="declineReason"
                                                                                            readonly
                                                                                            >${response.declineReason}</textarea>
                                                                                        <label class="input-label">Decline Reason</label>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </c:if>

                                                                    </div>
                                                                    <div class="feedback-detail-textarea">
                                                                        <div class="textarea-wrapper">
                                                                            <textarea
                                                                                name="description"
                                                                                id="description"
                                                                                readonly
                                                                                >${response.des}</textarea>
                                                                            <label class="input-label">Description</label>
                                                                        </div>
                                                                        <div class="feedback-detail-image-wrapper history-detail-image-wrapper">
                                                                            <c:if test="${response.image ne ''}">
                                                                                <!--                                                                                <img
                                                                                                                                                                    src="data:image/jpg/png;base64,${response.image}"
                                                                                                                                                                    alt=""
                                                                                                                                                                    />-->
                                                                            </c:if>
                                                                            <script type="module">
                                                                                import handleLoadImageForUserFromFirebase from '${pageContext.request.contextPath}/js/firebaseStorageForUser2.js';
                                                                                //  getImageString
                                                                                let imageString = '${response.image}';
                                                                                let index = ${counter.index};
                                                                                if(imageString) {
                                                                                handleLoadImageForUserFromFirebase(imageString, Array.from(document.querySelectorAll('.history-detail-image-wrapper'))[index]);
                                                                                }
                                                                            </script>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${counter.count != 1}">
                                                            <div
                                                                class="detail-wrapper history-detail"
                                                                data-index="${counter.count}"
                                                                >
                                                                <div class="feedback-detail-header">
                                                                    <h2 class="feedback-detail-tittle">Feedback Detail ${counter.count}</h2>
                                                                </div>
                                                                <div class="feedback-detail-showcase">
                                                                    <div class="feedback-detail-input">
                                                                        <div class="input-wrapper">
                                                                            <input
                                                                                type="text"
                                                                                name="device"
                                                                                id="device"
                                                                                value="${response.deviceName}"
                                                                                readonly
                                                                                />
                                                                            <label class="input-label">Device Name</label>
                                                                        </div>
                                                                        <div class="input-wrapper">
                                                                            <input
                                                                                type="text"
                                                                                name="location"
                                                                                id="location"
                                                                                value="Room ${response.location}"
                                                                                readonly
                                                                                />
                                                                            <label class="input-label">Location</label>
                                                                        </div>
                                                                        <div class="input-wrapper">
                                                                            <input
                                                                                type="number"
                                                                                name="quantity"
                                                                                id="quantity"
                                                                                value="${response.quantity}"
                                                                                readonly
                                                                                />
                                                                            <label class="input-label">Quantity</label>
                                                                        </div>
                                                                        <c:if test="${response.checkDone eq true}">
                                                                            <div class="responsearea done">
                                                                                <div class="response-manager">
                                                                                    <img src="img/checked.png"/>
                                                                                    <div class="done-label">
                                                                                        You have finished this task 
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </c:if>
                                                                        <c:if test="${response.check eq true}">
                                                                            <div class="responsearea decline">
                                                                                <div class="response-manager">
                                                                                    <img src="img/cancel.png"/>
                                                                                    <div class="textarea-wrapper">
                                                                                        <textarea
                                                                                            name="declineReason"
                                                                                            id="declineReason"
                                                                                            readonly
                                                                                            >${response.declineReason}</textarea>
                                                                                        <label class="input-label">Decline Reason</label>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </c:if>
                                                                    </div>
                                                                    <div class="feedback-detail-textarea">
                                                                        <div class="textarea-wrapper">
                                                                            <textarea
                                                                                name="description"
                                                                                id="description"
                                                                                readonly
                                                                                >${response.des}</textarea>
                                                                            <label class="input-label">Description</label>
                                                                        </div>

                                                                        <div class="feedback-detail-image-wrapper history-detail-image-wrapper">
                                                                            <c:if test="${response.image ne ''}">
                                                                                <!--                                                                                <img
                                                                                                                                                                    src="data:image/jpg/png;base64,${response.image}"
                                                                                                                                                                    alt=""
                                                                                                                                                                    />-->
                                                                            </c:if>
                                                                            <script type="module">
                                                                                import handleLoadImageForUserFromFirebase from '${pageContext.request.contextPath}/js/firebaseStorageForUser2.js';
                                                                                //  getImageString
                                                                                let imageString = '${response.image}';
                                                                                let index = ${counter.index};
                                                                                if(imageString) {
                                                                                handleLoadImageForUserFromFirebase(imageString, Array.from(document.querySelectorAll('.history-detail-image-wrapper'))[index]);
                                                                                }
                                                                            </script>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:if>
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
                        <c:when test="${sessionScope.COUNT_DETAIL_NOTIFICATION eq null}" >
                            <input id="COUNT_DETAIL_NOTIFICATION" type="hidden" name="COUNT_DETAIL_NOTIFICATION" value="0"/>

                        </c:when>
                        <c:otherwise >
                            <input id="COUNT_DETAIL_NOTIFICATION" type="hidden" name="COUNT_DETAIL_NOTIFICATION" value="${sessionScope.COUNT_DETAIL_NOTIFICATION}"/>
                        </c:otherwise>
                    </c:choose>
                    <input id="LOGIN_EMP" type="hidden" name="LOGIN_USER" value="${sessionScope.LOGIN_EMP.userID}"/>

                </div>
            </section>
        </main>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

        <script src="${pageContext.request.contextPath}/js/EmployeeHome.js"></script>
        <script type="module" src="${pageContext.request.contextPath}/js/firebaseEmpFromAdmin1.js"></script>
        <script type="module" src="${pageContext.request.contextPath}/js/firebaseForResponse.js"></script>
        <script type="module" src="${pageContext.request.contextPath}/js/firebaseForTrash.js"></script>
        <script type="module" src="${pageContext.request.contextPath}/js/firebaseStorageForEmployee1.js"></script>
        <!-- Query -->

        <script>
                                                                                                $(function () {
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
                                                                                                    $(".reponse-form-drag-area").on("drop", function (event) {
                                                                                                        event.preventDefault();
                                                                                                        $("#image").prop("files", event.originalEvent.dataTransfer.files);
                                                                                                        $("#image").trigger("change");
                                                                                                    });
                                                                                                    $("#image").on("change", function (e) {
                                                                                                        $(".reponse-form-image-show").empty();
                                                                                                        imagesPreview(this, "div.reponse-form-image-show");
                                                                                                    });
                                                                                                    $("#avatarImage").on("change", function (e) {
                                                                                                        imagesPreview2(this);
                                                                                                    });
                                                                                                });
        </script>
    </body>
</html>
