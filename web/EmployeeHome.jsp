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

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/EmployeeHome.css" />
    </head>
    <body>
        <div class="reponse-form">
            <div class="modal">
                <div class="reponse-form-main"></div>
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
                                            <li class="navigation-item active" data-index="0">
                                                <a href="#">Tasks</a>
                                            </li>
                                            <li class="navigation-item" data-index="1">
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
                                            active
                                            "
                                            >
                                            <div class="pipe">
                                                <div class="pipe-column">
                                                    <div class="pipe-list">
                                                        <c:forEach var="feedback" items="${sessionScope.LIST_FEEDBACK}" varStatus="counter">
                                                            <c:choose>
                                                                <c:when test="${requestScope.FEEDBACK_ACTIVE == null && counter.count == 1}">
                                                                    <a href="ShowFeedbackDetailForEmpController?feedbackID=${feedback.feedbackID}">
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
                                                                                <form action="123">
                                                                                    <button type="submit" class="reponse-btn">
                                                                                        <ion-icon
                                                                                            name="send"
                                                                                            class="send-btn"
                                                                                            ></ion-icon>
                                                                                    </button>
                                                                                </form>
                                                                            </div>
                                                                        </div>
                                                                    </a>
                                                                </c:when>
                                                                <c:when test="${feedback.feedbackID eq requestScope.FEEDBACK_ACTIVE}">
                                                                    <a href="ShowFeedbackDetailForEmpController?feedbackID=${feedback.feedbackID}">
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
                                                                                <form action="123">
                                                                                    <button type="submit" class="reponse-btn">
                                                                                        <ion-icon
                                                                                            name="send"
                                                                                            class="send-btn"
                                                                                            ></ion-icon>
                                                                                    </button>
                                                                                </form>
                                                                            </div>
                                                                        </div>
                                                                    </a>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <a href="ShowFeedbackDetailForEmpController?feedbackID=${feedback.feedbackID}">
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
                                                                                <form action="123">
                                                                                    <button type="submit" class="reponse-btn">
                                                                                        <ion-icon
                                                                                            name="send"
                                                                                            class="send-btn"
                                                                                            ></ion-icon>
                                                                                    </button>
                                                                                </form>
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
                                                        <c:if test="${counter.count == 1}">
                                                            <div
                                                                class="detail-wrapper task-detail active"
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
                                                                class="detail-wrapper task-detail"
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
                                        <!-- History -->
                                        <div class="content-item-main-item">
                                            <div class="pipe">
                                                <div class="pipe-column">
                                                    <div class="pipe-list">
                                                        <a href="#">
                                                            <div class="pipe-item active">
                                                                <div class="pipe-item-heading">
                                                                    <div class="pipe-item-title-wrapper">
                                                                        <h3 class="pipe-item-title">Feedback #1</h3>
                                                                    </div>
                                                                    <div class="pipe-item-date">
                                                                        Tue, August 18
                                                                    </div>
                                                                </div>
                                                                <div class="pipe-item-image-list"></div>
                                                                <div class="pipe-item-bottom">
                                                                    <p class="pipe-bottom-item">
                                                                        <strong>Send by</strong>
                                                                        ducndmse151198@fpt.edu.vn
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </a>
                                                        <a href="#">
                                                            <div class="pipe-item">
                                                                <div class="pipe-item-heading">
                                                                    <div class="pipe-item-title-wrapper">
                                                                        <h3 class="pipe-item-title">Feedback #1</h3>
                                                                    </div>
                                                                    <div class="pipe-item-date">
                                                                        Tue, August 18
                                                                    </div>
                                                                </div>
                                                                <div class="pipe-item-image-list">
                                                                    <img
                                                                        src="https://images.unsplash.com/photo-1633114128174-2f8aa49759b0?ixid=MnwxMjA3fDF8MHxlZGl0b3JpYWwtZmVlZHwxfHx8ZW58MHx8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                                                                        alt=""
                                                                        />
                                                                    <div class="image-list-item">+ 2</div>
                                                                </div>
                                                                <div class="pipe-item-bottom">
                                                                    <p class="pipe-bottom-item">
                                                                        <strong>Send by</strong>
                                                                        ducndmse151198@fpt.edu.vn
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </a>
                                                        <a href="#">
                                                            <div class="pipe-item">
                                                                <div class="pipe-item-heading">
                                                                    <div class="pipe-item-title-wrapper">
                                                                        <h3 class="pipe-item-title">Feedback #1</h3>
                                                                    </div>
                                                                    <div class="pipe-item-date">
                                                                        Tue, August 18
                                                                    </div>
                                                                </div>
                                                                <div class="pipe-item-image-list"></div>
                                                                <div class="pipe-item-bottom">
                                                                    <p class="pipe-bottom-item">
                                                                        <strong>Send by</strong>
                                                                        ducndmse151198@fpt.edu.vn
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </a>
                                                        <a href="#">
                                                            <div class="pipe-item">
                                                                <div class="pipe-item-heading">
                                                                    <div class="pipe-item-title-wrapper">
                                                                        <h3 class="pipe-item-title">Feedback #1</h3>
                                                                    </div>
                                                                    <div class="pipe-item-date">
                                                                        Tue, August 18
                                                                    </div>
                                                                </div>
                                                                <div class="pipe-item-image-list">
                                                                    <img
                                                                        src="https://images.unsplash.com/photo-1633114128174-2f8aa49759b0?ixid=MnwxMjA3fDF8MHxlZGl0b3JpYWwtZmVlZHwxfHx8ZW58MHx8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                                                                        alt=""
                                                                        />
                                                                    <div class="image-list-item">+ 2</div>
                                                                </div>
                                                                <div class="pipe-item-bottom">
                                                                    <p class="pipe-bottom-item">
                                                                        <strong>Send by</strong>
                                                                        ducndmse151198@fpt.edu.vn
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </a>
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
                                                    <div
                                                        class="detail-wrapper history-detail active"
                                                        data-index="1"
                                                        >
                                                        <div class="feedback-detail-header">
                                                            <h2 class="feedback-detail-tittle">Feedback 1</h2>
                                                        </div>
                                                        <div class="feedback-detail-showcase">
                                                            <div class="feedback-detail-input">
                                                                <div class="input-wrapper">
                                                                    <input
                                                                        type="text"
                                                                        name="device"
                                                                        id="device"
                                                                        value="Bui Duc Uy Dung"
                                                                        readonly
                                                                        />
                                                                    <label class="input-label">Device Name</label>
                                                                </div>
                                                                <div class="input-wrapper">
                                                                    <input
                                                                        type="text"
                                                                        name="location"
                                                                        id="location"
                                                                        value="Room 302"
                                                                        readonly
                                                                        />
                                                                    <label class="input-label">Location</label>
                                                                </div>
                                                                <div class="input-wrapper">
                                                                    <input
                                                                        type="text"
                                                                        name="quantity"
                                                                        id="quantity"
                                                                        value="2"
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
                                                                        >
                                                                        At w3schools.com you will learn how to make a website. They offer free tutorials in all web development technologies.</textarea
                                                                    >
                                                                    <label class="input-label">Description</label>
                                                                </div>
                                                                <div class="feedback-detail-image-wrapper">
                                                                    <img
                                                                        src="https://images.unsplash.com/photo-1633430590464-4914c1fbab92?ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw0fHx8ZW58MHx8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                                                                        alt=""
                                                                        />
                                                                    <img
                                                                        src="https://images.unsplash.com/photo-1489914099268-1dad649f76bf?ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8MTkyMHgxMDgwfGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                                                                        alt=""
                                                                        />
                                                                    <img
                                                                        src="https://images.unsplash.com/photo-1633430590464-4914c1fbab92?ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw0fHx8ZW58MHx8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                                                                        alt=""
                                                                        />
                                                                    <img
                                                                        src="https://images.unsplash.com/photo-1489914099268-1dad649f76bf?ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8MTkyMHgxMDgwfGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                                                                        alt=""
                                                                        />
                                                                    <img
                                                                        src="https://images.unsplash.com/photo-1620121478247-ec786b9be2fa?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fDE5MjB4MTA4MHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                                                                        alt=""
                                                                        />
                                                                    <img
                                                                        src="https://images.unsplash.com/photo-1620121478247-ec786b9be2fa?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fDE5MjB4MTA4MHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                                                                        alt=""
                                                                        />
                                                                    <img
                                                                        src="https://images.unsplash.com/photo-1633430590464-4914c1fbab92?ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw0fHx8ZW58MHx8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                                                                        alt=""
                                                                        />
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div
                                                        class="detail-wrapper history-detail"
                                                        data-index="2"
                                                        >
                                                        <div class="feedback-detail-header">
                                                            <h2 class="feedback-detail-tittle">Feedback 1</h2>
                                                        </div>
                                                        <div class="feedback-detail-showcase">
                                                            <div class="feedback-detail-input">
                                                                <div class="input-wrapper">
                                                                    <input
                                                                        type="text"
                                                                        name="device"
                                                                        id="device"
                                                                        value="Bui Duc Uy Dung"
                                                                        readonly
                                                                        />
                                                                    <label class="input-label">Device Name</label>
                                                                </div>
                                                                <div class="input-wrapper">
                                                                    <input
                                                                        type="text"
                                                                        name="location"
                                                                        id="location"
                                                                        value="Room 302"
                                                                        readonly
                                                                        />
                                                                    <label class="input-label">Location</label>
                                                                </div>
                                                                <div class="input-wrapper">
                                                                    <input
                                                                        type="text"
                                                                        name="quantity"
                                                                        id="quantity"
                                                                        value="2"
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
                                                                        >
                                                                        Lorem ipsum dolor sit amet consectetur, adipisicing elit. Mollitia corrupti magnam aut quidem saepe totam eos illo cum quas molestiae odit suscipit inventore, placeat laboriosam odio dicta, sit, sunt quam?</textarea
                                                                    >
                                                                    <label class="input-label">Description</label>
                                                                </div>
                                                                <div class="feedback-detail-image-wrapper">
                                                                    <img
                                                                        src="https://images.unsplash.com/photo-1633555234047-192d10238f5f?ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw1fHx8ZW58MHx8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                                                                        alt=""
                                                                        />
                                                                    <img
                                                                        src="https://images.unsplash.com/photo-1633692301992-d27ca897ad65?ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw4fHx8ZW58MHx8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                                                                        alt=""
                                                                        />
                                                                    <img
                                                                        src="https://images.unsplash.com/photo-1633532139011-ea6f0ecfab41?ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyMHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                                                                        alt=""
                                                                        />
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
                            <div class="showcase-content-item">1234</div>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <script src="${pageContext.request.contextPath}/js/EmployeeHome.js"></script>
    </body>
</html>
