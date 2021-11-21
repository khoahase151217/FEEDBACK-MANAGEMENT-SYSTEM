<%-- 
    Document   : ManagerViewFacility
    Created on : Sep 27, 2021, 4:29:22 PM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="app.facility.FacilityDTO"%>
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ManagerViewFacility.css" />
        
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
                                <a href="ShowFacilitiesController">
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
                                <li class="showcase-item active" data-index="2">
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
                                            <a href="ShowUserFormController?position=ManagerFacilityPage&user=admin" class="dropdown-item">
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

                            <!-- Facility -->
                            <div class="showcase-content-item active">
                                <div class="facility-heading">
                                    <div class="facility-heading-header">
                                        <h2 class="showcase-title">Facilities</h2>
                                        <form action="SearchFacilityController" class="showcase-form">
                                            <input
                                                type="text"
                                                name="search"
                                                value="${requestScope.SEARCH}"
                                                placeholder="Search Facilities..."
                                                />
                                            <button type="submit">
                                                <ion-icon name="search"></ion-icon>
                                            </button>
                                        </form>
                                    </div>
                                </div>

                                <div class="content-item-main facility-content-main">

                                    <div class="content-item-main-list">
                                        <div class="content-item-main-item active">
                                            <!-- List -->
                                            <div class="content-item-main-category-item facility-item-main facility-list active">
                                                <ul class="facility-list-heading">
                                                    <li class="facility-list-item ${requestScope.STYLE_LIST_ALL}" data-index="0">
                                                        All
                                                    </li>
                                                    <li class="facility-list-item" data-index="1">
                                                        Electronic
                                                    </li>
                                                    <li class="facility-list-item" data-index="2">
                                                        Water
                                                    </li>
                                                    <li class="facility-list-item" data-index="3">
                                                        Environment
                                                    </li>
                                                    <li class="facility-list-item" data-index="4">
                                                        Others
                                                    </li>
                                                </ul>
                                                <div class="facility-list-showcase">
                                                    <!-- list All -->
                                                    <div class="facility-list-showcase-item active" data-index="0">
                                                        <div class="list">
                                                            <c:if test="${empty sessionScope.FACILTIES_LIST_ALL}">
                                                                <p class="showcase-desc">
                                                                    No result can be found ...
                                                                </p>
                                                            </c:if>
                                                            <c:forEach var="facility" items="${sessionScope.FACILTIES_LIST_ALL}">
                                                                <div class="pipe-item">
                                                                    <div class="pipe-item-heading">
                                                                        <img
                                                                            src="${facility.image}"
                                                                            alt=""
                                                                            />
                                                                    </div>
                                                                    <div class="pipe-item-bottom">
                                                                        <div class="pipe-item-bottom-heading">
                                                                            <h2 class="pipe-item-title">
                                                                                ${facility.facilityName}
                                                                            </h2>
                                                                            <p class="pipe-item-desc">Quantity: ${facility.quantity}</p>
                                                                        </div>
                                                                        <div class="pipe-item-bottom-footer">
                                                                            <p class="pipe-item-desc pipe-item-date">
                                                                                Maintain Date: ${facility.maintenanceDate}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <!-- list Electronic -->
                                                    <div class="facility-list-showcase-item" data-index="1">
                                                        <div class="list">
                                                            <c:if test="${empty sessionScope.FACILTIES_LIST_ELECTRIC}">
                                                                <p class="showcase-desc">
                                                                    No result can be found ...
                                                                </p>
                                                            </c:if>
                                                            <c:forEach var="facility" items="${sessionScope.FACILTIES_LIST_ELECTRIC}">
                                                                <div class="pipe-item">
                                                                    <div class="pipe-item-heading">
                                                                        <img
                                                                            src="${facility.image}"
                                                                            alt=""
                                                                            />
                                                                    </div>
                                                                    <div class="pipe-item-bottom">
                                                                        <div class="pipe-item-bottom-heading">
                                                                            <h2 class="pipe-item-title">
                                                                                ${facility.facilityName}
                                                                            </h2>
                                                                            <p class="pipe-item-desc">Quantity: ${facility.quantity}</p>
                                                                        </div>
                                                                        <div class="pipe-item-bottom-footer">
                                                                            <p class="pipe-item-desc pipe-item-date">
                                                                                Maintain Date: ${facility.maintenanceDate}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <!-- list Water -->
                                                    <div class="facility-list-showcase-item" data-index="2">
                                                        <div class="list">
                                                            <c:if test="${empty sessionScope.FACILTIES_LIST_WATER}">
                                                                <p class="showcase-desc">
                                                                    No result can be found ...
                                                                </p>
                                                            </c:if>
                                                            <c:forEach var="facility" items="${sessionScope.FACILTIES_LIST_WATER}">
                                                                <div class="pipe-item">
                                                                    <div class="pipe-item-heading">
                                                                        <img
                                                                            src="${facility.image}"
                                                                            alt=""
                                                                            />
                                                                    </div>
                                                                    <div class="pipe-item-bottom">
                                                                        <div class="pipe-item-bottom-heading">
                                                                            <h2 class="pipe-item-title">
                                                                                ${facility.facilityName}
                                                                            </h2>
                                                                            <p class="pipe-item-desc">Quantity: ${facility.quantity}</p>
                                                                        </div>
                                                                        <div class="pipe-item-bottom-footer">
                                                                            <p class="pipe-item-desc pipe-item-date">
                                                                                Maintain Date: ${facility.maintenanceDate}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <!-- list Environment -->
                                                    <div class="facility-list-showcase-item" data-index="3">
                                                        <div class="list">
                                                            <c:if test="${empty sessionScope.FACILTIES_LIST_ENVIROMENT}">
                                                                <p class="showcase-desc">
                                                                    No result can be found ...
                                                                </p>
                                                            </c:if>
                                                            <c:forEach var="facility" items="${sessionScope.FACILTIES_LIST_ENVIROMENT}">
                                                                <div class="pipe-item">
                                                                    <div class="pipe-item-heading">
                                                                        <img
                                                                            src="${facility.image}"
                                                                            alt=""
                                                                            />
                                                                    </div>
                                                                    <div class="pipe-item-bottom">
                                                                        <div class="pipe-item-bottom-heading">
                                                                            <h2 class="pipe-item-title">
                                                                                ${facility.facilityName}
                                                                            </h2>
                                                                            <p class="pipe-item-desc">Quantity: ${facility.quantity}</p>
                                                                        </div>
                                                                        <div class="pipe-item-bottom-footer">
                                                                            <p class="pipe-item-desc pipe-item-date">
                                                                                Maintain Date: ${facility.maintenanceDate}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <!-- list Others -->
                                                    <div class="facility-list-showcase-item" data-index="4">
                                                        <div class="list">
                                                            <c:if test="${empty sessionScope.FACILTIES_LIST_OTHERS}">
                                                                <p class="showcase-desc">
                                                                    No result can be found ...
                                                                </p>
                                                            </c:if>
                                                            <c:forEach var="facility" items="${sessionScope.FACILTIES_LIST_OTHERS}">
                                                                <div class="pipe-item">
                                                                    <div class="pipe-item-heading">
                                                                        <img
                                                                            src="${facility.image}"
                                                                            alt=""
                                                                            />
                                                                    </div>
                                                                    <div class="pipe-item-bottom">
                                                                        <div class="pipe-item-bottom-heading">
                                                                            <h2 class="pipe-item-title">
                                                                                ${facility.facilityName}
                                                                            </h2>
                                                                            <p class="pipe-item-desc">Quantity: ${facility.quantity}</p>
                                                                        </div>
                                                                        <div class="pipe-item-bottom-footer">
                                                                            <p class="pipe-item-desc pipe-item-date">
                                                                                Maintain Date: ${facility.maintenanceDate}
                                                                            </p>
                                                                        </div>
                                                                    </div>
                                                                </div>
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

        <script src="${pageContext.request.contextPath}/js/adminPage.js"></script>
        <script src="${pageContext.request.contextPath}/js/ManagerFacility1.js"></script>
        <script type="module" src="${pageContext.request.contextPath}/js/firebaseAdminfromUser.js"></script>
        <!-- Query -->
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

            function loadResults(index, list) {
                let amount = list.querySelectorAll('.pipe-item').length;
                let search = document.querySelector('input[name="search"]').value;
                $.ajax({
                    url: "/SWP391_PROJECT/LoadFacilities",
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

            $(function () {
//                handleNotification();
//                setInterval(handleNotification, 10000);

                Array.from($(".list")).forEach(item => {
                    item.addEventListener('scroll', (e) => {
                        var list = e.target.closest('.list');
                        if (!e.target.getAttribute("data-loading")) {
                            if (Math.ceil(list.offsetHeight + list.scrollTop) === list.scrollHeight) {
                                loadResults(e.target.closest('.facility-list-showcase-item').dataset.index, list);
                            }
                        }
                    });
                });
            });
        </script>
    </body>
</html>

