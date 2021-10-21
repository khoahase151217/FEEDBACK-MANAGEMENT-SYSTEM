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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ManagerStatictis.css" />
    </head>
    <body>
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
                                <a href="#">
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
                                <li class="showcase-item" data-index="4">
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
                                    <div class="showcase-profile-dropdown">
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
                                        <div class="showcase-profile-dropdown-list">
                                            <a href="ShowUserFormController?position=managerStatictisPage" class="dropdown-item">
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

                                    </div>
                                    
                                    <!-- phần của anh ở đây nhé, tuyệt đối không code css cho class = "statictis-users" nếu a muốn code cho cái div này thì 
                                        hãy đặt một cái class riêng cùng cấp với nó hoặc a tạo 1 cái div wrapper của a là con của cái div[class= "statictis-users"]
                                        rồi a hãy code css cho nó. Lưu ý: code css vào file ManagerStatictis.css nhé a (kh copy vào adminPage.css)
                                    -->
                                    <div class="statictis-users" style="background-color: red;">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <script src="${pageContext.request.contextPath}/js/ManagerStatictis.js"></script>
    </body>
</html>


