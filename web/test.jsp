<%-- 
    Document   : SendFeedback
    Created on : Sep 30, 2021, 12:52:29 PM
    Author     : Admin
--%>

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
        <title>Student Page</title>
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
            integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />

        <script
            type="module"
            src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"
        ></script>
        <script
            nomodule
            src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"
        ></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/UserSendFB.css"/>
    </head>
    <body>

        <div class="header">
            <div class="container flex">
                <div class="logo">
                    <a href="#"><h6>Traversy</h6></a>
                </div>
                <c:if test="${requestScope.NOTIFY_MESSAGE != null}">
                    <div class="message-block-wrapper">
                        <div class="message-block ${requestScope.flag}">
                            <div class="heading">
                                <ion-icon name="close-outline"></ion-icon>
                            </div>
                            <span id="message">
                            </span>
                        </div>
                    </div>
                </c:if>
                <div class="heading-bottom">
                    <a href="ShowFeedbackStudentController">Review Feedback</a>
                    <div class="header-profile-dropdown">
                        <div class="header-profile-image">
                            <img
                                src="https://images.unsplash.com/photo-1632331591393-96d5f060fd45?ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwzfHx8ZW58MHx8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                                alt="user-profile"
                                class="img-select"
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

        <div class="showcase">
            <div class="container">
                <div class="inner-box">
                    <div class="heading">
                        <h3>Facilities's Feedback</h3>
                    </div>

                    <form  method="post" action="SendFeedbackController" enctype="multipart/form-data">
                        <div class="actual-form">
                            <div class="input-wrap">
                                <input
                                    type="text"
                                    name="tmpLocation"
                                    id="location"
                                    onfocus="handelDOMEvent(event)"
                                    required
                                    />
                                <label>Room 302 ...</label>
                            </div>
                            <div class="input-wrap">
                                <select name="tmpDevice" id="device" required>
                                    <option value="" selected disabled hidden>
                                        Damaged Device
                                    </option>
                                    <c:forEach var = "facility" items="${sessionScope.FACILTIES_LIST_ALL}">
                                        <option value="${facility.facilityID}">${facility.facilityName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="input-wrap-special">
                                <input
                                    type="number"
                                    name="tmpQuantity"
                                    id="quantity"
                                    placeholder="1"
                                    required
                                    />
                            </div>
                            <div class="input-wrap">
                                <select name="tmpReason" id="reason" required>
                                    <option value="" selected disabled hidden>Reason</option>
                                    <option value="broken">Broken</option>
                                    <option value="disappeared">Disappeared</option>
                                    <option value="others">Others</option>
                                </select>
                            </div>
                            <div class="plus-toggle">
                                <img
                                    src="https://cdn.discordapp.com/attachments/770804043794350100/888843266794086491/add-button.png"
                                    alt="plusImg"
                                    class="plus-btn"
                                    onclick="handleAddEvent()"
                                    />
                                <i class="fas fa-times" onclick="handleMinusEvent(event)"></i>
                            </div>
                        </div>

                        <div class="root"></div>

                        <div class="input-text">
                            <textarea
                                type="text"
                                name="description"
                                id="description"
                                ></textarea>
                            <label>Description...</label>
                        </div>

                        <div class="input-file-wrap">
                            <input type="text" readonly />
                            <label>
                                <ion-icon name="image-outline"></ion-icon>
                                <input
                                    type="file"
                                    name="image"
                                    id="image"
                                    multiple
                                    style="display: none"
                                    />
                            </label>
                        </div>

                        <div class="report-btn">
                            <input type="submit" value="Send Report" name="action" />
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="footer">
            <div class="container">
                <div class="social">
                    <a href="#">
                        <img
                            src="https://cdn.discordapp.com/attachments/770804043794350100/888843281767759954/facebook.png"
                            alt="FB"
                            />
                    </a>
                    <a href="#">
                        <img
                            src="https://cdn.discordapp.com/attachments/770804043794350100/888843280035495987/email.png"
                            alt="GG"
                            />
                    </a>
                    <a href="#">
                        <img
                            src="https://cdn.discordapp.com/attachments/770804043794350100/888843287748837416/twitter.png"
                            alt="Twitter"
                            />
                    </a>
                    <a href="#">
                        <img
                            src="https://cdn.discordapp.com/attachments/770804043794350100/888843283122515988/instagram.png"
                            alt="instagram"
                            />
                    </a>
                </div>
                <div class="footer-info">
                    <ul>
                        <li><a href="#">About</a></li>
                        <li><a href="#">Help Center</a></li>
                        <li><a href="#">Contact Us</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <c:if test="${requestScope.NOTIFY_MESSAGE != null}">
            <script>
                function handleMessage(message) {
                    document.querySelector(".message-block span").innerHTML = message;
                    document.querySelector(".message-block").animate(
                            [
                                {
                                    transform: "scale(0)"

                                },
                                {
                                    transform: "scale(1)"
                                }
                            ],
                            {
                                duration: 300
                            }

                    );
                }
                handleMessage("${requestScope.NOTIFY_MESSAGE}");
            </script>
        </c:if>

        <script src="${pageContext.request.contextPath}/js/UserSendFB.js"></script>
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

                function handleAddEvent() {
                    if (count > 2) {
                        alert("Cannot add larger than 4 form input !");
                        return;
                    } else {
                        Array.from($$(".plus-toggle")).forEach((toggle) =>
                            toggle.classList.add("remove")
                        );
                        Array.from($$(".root input")).forEach((inputElement) =>
                            inputElement.parentElement.classList.remove("active")
                        );
                        root.innerHTML += `<div class="actual-form">
                                                <div class="input-wrap">
                                                  <input type="text" name="tmpLocation" id="location" onfocus="handelDOMEvent(event)" required/>
                                                  <label>Room 302 ...</label>
                                                </div>
                                                <div class="input-wrap">
                                                  <select name="tmpDevice" id="device" required>
                                                    <option value="" selected disabled hidden>
                                                      Damaged Device
                                                    </option>
            <c:forEach var = "facility" items="${sessionScope.LIST_FACILITIES}">
                                        <option value="${facility.facilityID}">${facility.facilityName}</option>
            </c:forEach>
                                                  </select>
                                                </div>
                                                <div class="input-wrap-special">
                                                  <input
                                                    type="number"
                                                    name="tmpQuantity"
                                                    id="quantity"
                                                    placeholder="1"
                                                    required
                                                  />
                                                </div>
                                                <div class="input-wrap">
                                                  <select name="tmpReason" id="reason" required>
                                                    <option value="" selected disabled hidden>Reason</option>
                                                    <option value="broken">Broken</option>
                                                    <option value="disappeared">Disappeared</option>
                                                    <option value="others">Others</option>
                                                  </select>
                                                </div>
                                                <div class="plus-toggle">
                                                  <img
                                                    src="https://cdn.discordapp.com/attachments/770804043794350100/888843266794086491/add-button.png"
                                                    alt="plusImg"
                                                    class="plus-btn"
                                                    onclick="handleAddEvent()"
                                                  />
                                                  <i class="fas fa-times" onclick="handleMinusEvent(event)"></i>
                                                </div>
                                              </div>`;
                        count++;
                    }
                }
        </script>
    </body>
</html>

