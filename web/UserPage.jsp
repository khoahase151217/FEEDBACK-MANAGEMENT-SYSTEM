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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/FeedbackForm.css" />
    </head>
    <body>
        <!-- Pop-up create feedback -->
        <!-- If you return a message of form you have to add class value 'open' to div[class='feedback-form'], if you dont return a message from controller you dont have to do this-->
        <div class="feedback-form ${requestScope.SEND_FEEDBACK_FLAG}">
            <div class="modal feedback-form-modal">
                <div class="feedback-form-main">
                    <div class="container-main">
                        <form action="SendFeedbackController" class="root" method="post" enctype="multipart/form-data">
                            <div class="input-check">
                                <input type="hidden" name="check" value="1"/>
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
                        <a href="#" class="btn btn--decline link">Try agian</a>
                    </div>
                </div>
            </div>
        </div>

        <header class="header">
            <div class="container">
                <div class="header-main">
                    <div class="header-logo">
                        <form action="#" autocomplete="off">
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
                                    <input type="text" name="search" id="search" />
                                    <label>Search ...</label>
                                </div>
                            </div>
                        </form>
                    </div>

                    <div class="header-profile">
                        <a href="#" class="header-profile-link">New Feedback</a>
                        <div class="header-profile-dropdown">
                            <div class="header-profile-image-wrapper">
                                <img
                                    src="https://images.unsplash.com/photo-1633114128729-0a8dc13406b9?ixid=MnwxMjA3fDF8MHxlZGl0b3JpYWwtZmVlZHwxfHx8ZW58MHx8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                                    alt="user-profile"
                                    class="header-profile-image"
                                    />
                            </div>
                            <div class="dropdown-list">
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
                                    <a href="#" class="active" data-index="0">
                                        <ion-icon name="apps-sharp"></ion-icon>
                                    </a>
                                    <a href="#" data-index="1">
                                        <ion-icon name="list"></ion-icon>
                                    </a>
                                </div>
                            </div>

                            <div class="content-item-main-list">
                                <!--Pipe--> 
                                <div class="content-item-main-category-item active">
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
                                            <div class="pipe-list">
                                                <%
                                                    List<UserHistoryDTO> listDone = (List<UserHistoryDTO>) session.getAttribute("HISTORY_DONE");
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
                                            <div class="pipe-list">
                                                <%
                                                    List<UserHistoryDTO> listOngoing = (List<UserHistoryDTO>) session.getAttribute("HISTORY_ONGOING");
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
                                            <div class="pipe-list">
                                                <%
                                                    List<UserHistoryDTO> listDeny = (List<UserHistoryDTO>) session.getAttribute("HISTORY_DENY");
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
                                                %> 
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div
                                    class="content-item-main-category-item"
                                    style="background: black"
                                    ></div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>

        <footer class="footer">
            <div class="container">
                <div class="footer-top">
                    <div class="footer-column">
                        <h1 class="footer-logo">Traversy</h1>
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
                        <h3 class="footer-title second-font">usefull Link</h3>
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
                        Copyright © 2021 Traversy, Designed by <a href="#">MinhDuc</a> All
                        Rights Reserved.
                    </p>
                </div>
            </div>
        </footer>

        <script src="${pageContext.request.contextPath}/js/User1.js"></script>
        <!-- Query -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
                                                            $(function () {
                                                                // This code will attach `fileselect` event to all file inputs on the page
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
                                                                        } else {
                                                                            if (log)
                                                                                alert(log);
                                                                        }
                                                                    });
                                                                });
                                                            });

                                                            $(function () {
                                                                // This code will attach `fileselect` event to all file inputs on the page
                                                                $(document).on("change", ":file", function () {
                                                                    var input = $(this),
                                                                            numFiles = input.get(0).files ? input.get(0).files.length : 1,
                                                                            label = input.val().replace(/\\/g, "/").replace(/.*\//, "");
                                                                    input.trigger("fileselect", [numFiles, label]);
                                                                });

                                                                $(document).ready(function () {
                                                                    //below code executes on file input change and append name in text control
                                                                    $(":file").on("fileselect", function (event, numFiles, label) {
                                                                        var input = $(this).parents(".input-file-wrap").find(":text"),
                                                                                log = numFiles > 1 ? numFiles + " files selected" : label;

                                                                        if (input.length) {
                                                                            input.val(log);
                                                                        } else {
                                                                            if (log)
                                                                                alert(log);
                                                                        }
                                                                    });
                                                                });

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
                                                            });
        </script>
    </body>
</html>
