<%-- 
    Document   : EmployeeHome
    Created on : Sep 27, 2021, 7:43:01 PM
    Author     : Admin
--%>

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

        <!-- font awesome -->
        <script src="https://kit.fontawesome.com/97ce91ff3b.js" crossorigin="anonymous"></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/EmployeeHome1.css" />
    </head>
    <body>
        <div class="reponse-form ${requestScope.flag}">
            <div class="modal">
                <div class="reponse-form-main">
                    <h2 class="reponse-form-heading">Feedback Detail ${requestScope.COUNT}</h2>
                    <form action="AddResponseController" class="reponse-form-actual-form" enctype="multipart/form-data" method="post">
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
                                        name="image"
                                        id="image"
                                        style="display: none"
                                        />
                                </label>
                            </div>
                        </div>
                        <input type="hidden" name="feedbackDetailID" value="${requestScope.FEEDBACK_DETAIL_ID}" />
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
                    <div class="form-message-item ${requestScope.SEND_SUCCESS}">
                        <div class="item-image-wrap">
                            <img src="./img/success.png" alt="" />
                        </div>
                        <h1 class="status">Thank You</h1>
                        <p class="desc">
                            You have already cancel this feedback successfully !
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
                                    <a href="#" class="showcase-link">
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
                                </li>
                            </ul>
                            <div class="showcase-profile">
                                <div class="showcase-profile-image">
                                    <img
                                        src="https://images.unsplash.com/photo-1615915468538-0fbd857888ca?ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8bG9nbyUyMGZlZWRiYWNrfGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
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
                            <!-- If you want to Page is show for user you only need to add class 'active' for div[class="showcase-content-item"]  -->

                            <!-- Feedback -->
                            <div class="showcase-content-item active">
                                <div class="showcase-content-item-heading">
                                    <form action="#" class="showcase-form">
                                        <input
                                            type="text"
                                            name="search"
                                            placeholder="Search project..."
                                            />
                                        <button type="submit">
                                            <ion-icon name="search"></ion-icon>
                                        </button>
                                    </form>
                                    <div class="showcase-title-wrapper">
                                        <h2 class="showcase-title">Welcome back, ${sessionScope.LOGIN_USER.fullName}</h2>
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
                                                <div class="pipe-column">
                                                    <div class="pipe-list">
                                                        <c:forEach var="feedback" items="${sessionScope.LIST_FEEDBACK}" varStatus="counter">
                                                            <c:choose>
                                                                <c:when test="${requestScope.FEEDBACK_ACTIVE == null && counter.count == 1}">
                                                                    <a href="ShowFeedbackDetailForEmpController?feedbackID=${feedback.feedbackID}&history=${requestScope.HISTORY_ACTIVE}&style_list=task">
                                                                        <div class="pipe-item active">
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
                                                                                            class="btn-submit-links"
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
                                                                        <div class="pipe-item active">
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
                                                                                            class="btn-submit-links"
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
                                                                        <div class="pipe-item">
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
                                                                                            class="btn-submit-links"
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
                                                                                class="btn-submit-links"
                                                                                >
                                                                                <i class="fas fa-reply"></i>
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
                                                                                    type="number"
                                                                                    name="quantity"
                                                                                    id="quantity"
                                                                                    value="${feedbackDetail.quanity}"
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
                                                                                    >${feedbackDetail.reason}</textarea
                                                                                >
                                                                                <label class="input-label">Description</label>
                                                                            </div>
                                                                            <div class="feedback-detail-image-wrapper">
                                                                                <img
                                                                                    src="data:image/jpg/png;base64,${feedbackDetail.image}"
                                                                                    alt=""
                                                                                    />
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
                                                                                <i class="fas fa-reply"></i>
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
                                                                                    type="number"
                                                                                    name="quantity"
                                                                                    id="quantity"
                                                                                    value="${feedbackDetail.quanity}"
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
                                                                                    >${feedbackDetail.reason}</textarea
                                                                                >
                                                                                <label class="input-label">Description</label>
                                                                            </div>
                                                                            <div class="feedback-detail-image-wrapper">
                                                                                <img
                                                                                    src="data:image/jpg/png;base64,${feedbackDetail.image}"
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
                                                                                <i class="fas fa-reply"></i>
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
                                                                                    type="number"
                                                                                    name="quantity"
                                                                                    id="quantity"
                                                                                    value="${feedbackDetail.quanity}"
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
                                                                                    >${feedbackDetail.reason}</textarea
                                                                                >
                                                                                <label class="input-label">Description</label>
                                                                            </div>
                                                                            <div class="feedback-detail-image-wrapper">
                                                                                <img
                                                                                    src="data:image/jpg/png;base64,${feedbackDetail.image}"
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
                                        <!-- History -->
                                        <div class="content-item-main-item ${requestScope.LIST_STYLE_HISTORY}">
                                            <div class="pipe">
                                                <div class="pipe-column">
                                                    <div class="pipe-list">
                                                        <c:forEach var="feedback" items="${sessionScope.LIST_HISTORY}" varStatus="counter">
                                                            <c:choose>
                                                                <c:when test="${requestScope.HISTORY_ACTIVE == null && counter.count == 1}">
                                                                    <a href="ShowFeedbackDetailForEmpController?history=${feedback.feedbackID}&feedbackID=${requestScope.FEEDBACK_ACTIVE}&style_list=history">
                                                                        <div class="pipe-item active">
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
                                                                        <div class="pipe-item active">
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
                                                                        <div class="pipe-item">
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
                                                    class="pipe-column feedback-detail"
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
                                                    <c:forEach var="feedbackDetail" items="${sessionScope.HISTORY_DETAIL}" varStatus="counter">
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
                                                                                type="number"
                                                                                name="quantity"
                                                                                id="quantity"
                                                                                value="${feedbackDetail.quanity}"
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
                                                                                >${feedbackDetail.reason}</textarea
                                                                            >
                                                                            <label class="input-label">Description</label>
                                                                        </div>
                                                                        <div class="feedback-detail-image-wrapper">
                                                                            <img
                                                                                src="data:image/jpg/png;base64,${feedbackDetail.image}"
                                                                                alt=""
                                                                                />
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
                                                                                type="number"
                                                                                name="quantity"
                                                                                id="quantity"
                                                                                value="${feedbackDetail.quanity}"
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
                                                                                >${feedbackDetail.reason}</textarea
                                                                            >
                                                                            <label class="input-label">Description</label>
                                                                        </div>
                                                                        <div class="feedback-detail-image-wrapper">
                                                                            <img
                                                                                src="data:image/jpg/png;base64,${feedbackDetail.image}"
                                                                                alt=""
                                                                                />
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
                            <div class="showcase-content-item">1234</div>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <script src="${pageContext.request.contextPath}/js/EmployeeHome.js"></script>
        <!-- Query -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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

                $(".reponse-form-drag-area").on("drop", (event) => {
                    event.preventDefault();
                    $("#image").prop("files", event.originalEvent.dataTransfer.files);
                    $("#image").trigger("change");
                });

                $("#image").on("change", function (e) {
                    $(".reponse-form-image-show").empty();
                    imagesPreview(this, "div.reponse-form-image-show");
                });
            });
        </script>
    </body>
</html>
