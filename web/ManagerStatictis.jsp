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
                                <li
                                    class="showcase-item active showcase-item-dropdown-select"
                                    data-index="3"
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
                                            You have 2 new feedback
                                        </h5>
                                        <!-- <h5 class="showcase-item-dropdown-sub-title sub-title-no">
                                          No notification can be found ...
                                        </h5> -->
                                        <div class="pipe-list">
                                            <div class="notification-item">
                                                <div class="pipe-item-heading">
                                                    <div class="pipe-item-title-wrapper">
                                                        <h3 class="pipe-item-title">Feedback #1</h3>
                                                        <p class="pipe-item-desc">
                                                            <strong>Name:</strong> Nguyen Duong Minh duc
                                                        </p>
                                                    </div>
                                                    <div class="pipe-item-date">Tue, August 18</div>
                                                </div>
                                                <div class="pipe-item-bottom">
                                                    <p class="pipe-bottom-item">
                                                        <strong>Send by</strong>
                                                        ducndmse151198@fpt.edu.vn
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="notification-item">
                                                <div class="pipe-item-heading">
                                                    <div class="pipe-item-title-wrapper">
                                                        <h3 class="pipe-item-title">Feedback #1</h3>
                                                        <p class="pipe-item-desc">
                                                            <strong>Name:</strong> Nguyen Duong Minh duc
                                                        </p>
                                                    </div>
                                                    <div class="pipe-item-date">Tue, August 18</div>
                                                </div>
                                                <div class="pipe-item-bottom">
                                                    <p class="pipe-bottom-item">
                                                        <strong>Send by</strong>
                                                        ducndmse151198@fpt.edu.vn
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div
                                        class="showcase-item-dropdown-actual-notification active"
                                        >
                                        2
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

                                    <!-- phần của anh ở đây nhé, tuyệt đối không code css cho class = "statictis-users" nếu a muốn code cho cái div này thì 
                                        hãy đặt một cái class riêng cùng cấp với nó hoặc a tạo 1 cái div wrapper của a là con của cái div[class= "statictis-users"]
                                        rồi a hãy code css cho nó. Lưu ý: code css vào file ManagerStatictis.css nhé a (kh copy vào adminPage.css)
                                    -->
                                    <div class="statictis-users" style="background-color: #f0e2cc;">
                                        <div class="icon-profile-back profile-chevron-back">
                                            <ion-icon name="chevron-back-outline"></ion-icon>
                                        </div>
                                        <div class="icon-profile-forward profile-chevron-forward">
                                            <ion-icon name="chevron-forward-outline"></ion-icon>
                                        </div>
                                        <div class="statistic-user-header">
                                            <label>Top 3 Users</label>
                                        </div>
                                        <div class="statistic-user-role">
                                            <ul class="user-role-navigation">
                                                <li class="role-navigation" id="roleStudent">
                                                    <a href="#" id="studentTab">Students</a>
                                                </li>
                                                <li class="role-navigation active" id="roleEmployee">
                                                    <a href="#" id="employeeTab">Employees</a>
                                                </li>
                                            </ul>
                                            <span class="role-navigation-bar employee" id="tabnavigation"></span>
                                        </div>
                                        <div class="statistic-user-title">
                                            <label>Profile</label>
                                            <div class="statistic-behavior">
                                                <ul class="user-behavior">
                                                    <li class="behavior-navigation active" id="roleStudent">
                                                        <a href="#" id="goodTab">Good Behavior</a>
                                                    </li>
                                                    <li class="behavior-navigation" id="roleEmployee">
                                                        <a href="#" id="badTab">Bad Behavior</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <c:forEach var="userprofile" items="${sessionScope.LIST_GOOD_EMP}" varStatus="counter">
                                            <div class="statistic-user-list">
                                                <c:choose>
                                                    <c:when test="${counter.count == 1}">
                                                        <c:set var="userid" scope="session" value="${userprofile.userID}"/>
                                                        <div class="statistic-user-wrapper active" data-index="${counter.count}">
                                                            <div class="statistic-user-profile">
                                                                <div class="statistic-user-avatar">
                                                                    <img
                                                                        src="data:image/jpg/png;base64,${userprofile.image}"
                                                                        alt=""
                                                                        />
                                                                </div>
                                                                <h1 class="user-name">${userprofile.fullName}</h1>
                                                                <h2>${userprofile.roleName}</h2>
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
                                                                <c:forEach var="recentfeedback" items="${sessionScope.LIST_GOOD_RECENT_RESPONE_EMP}">
                                                                    <div class="pipe-item">
                                                                        <div class="pipe-item-heading">
                                                                            <div class="pipe-item-title-wrapper">
                                                                                <h3 class="pipe-item-title">
                                                                                    Feedback ${recentfeedback.feedbackDetailID}
                                                                                </h3>
                                                                                <p class="pipe-item-desc">
                                                                                    <strong>Name:</strong> ${recentfeedback.userName}
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
                                                                </c:forEach>
                                                                <div class="unban-button">
                                                                    <input type="submit" value="Unban"/>
                                                                </div>
                                                            </div>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:set var="userid" scope="session" value="${userprofile.userID}"/>
                                                            <div class="statistic-user-wrapper" data-index="${counter.count}">
                                                                <div class="statistic-user-profile">
                                                                    <div class="statistic-user-avatar">
                                                                        <img
                                                                            src="data:image/jpg/png;base64,${userprofile.image}"
                                                                            alt=""
                                                                            />
                                                                    </div>
                                                                    <h1 class="user-name">${userprofile.fullName}</h1>
                                                                    <h2>${userprofile.roleName}</h2>
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
                                                                    <c:forEach var="recentfeedback" items="${sessionScope.LIST_GOOD_RECENT_RESPONE_EMP}">
                                                                        <c:if test="${userid eq recentfeedback.userID}">
                                                                            <div class="pipe-item">
                                                                                <div class="pipe-item-heading">
                                                                                    <div class="pipe-item-title-wrapper">
                                                                                        <h3 class="pipe-item-title">
                                                                                            Feedback ${recentfeedback.feedbackDetailID}
                                                                                        </h3>
                                                                                        <p class="pipe-item-desc">
                                                                                            <strong>Name:</strong> ${recentfeedback.userName}
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
                                                                    <div class="unban-button">
                                                                        <input type="submit" value="Unban"/>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:otherwise>
                                                    </c:choose>    
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <script src="${pageContext.request.contextPath}/js/ManagerStatictis.js"></script>
        <script
            type="text/javascript"
            src="https://www.gstatic.com/charts/loader.js"
        ></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
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
                            console.log(data);
                            google.charts.load("current", {packages: ["corechart"]});
                            google.charts.setOnLoadCallback(() => {
                                drawChart2(data);
                            });
                        }
                    });
                }
            });
            $(document).ready(function () {
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
                        },
                        ignoreBounds: true,
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


