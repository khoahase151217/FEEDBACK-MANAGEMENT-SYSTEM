<%-- 
    Document   : userViewFB
    Created on : Sep 30, 2021, 12:59:11 PM
    Author     : Admin
--%>

<%-- 
    Document   : userViewFB
    Created on : Sep 25, 2021, 1:53:59 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>User Page</title>
        <!-- Popins Font -->
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap"
            rel="stylesheet"
            />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/UserViewFeedback.css" />
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER == null}">
            <c:redirect url="login.jsp"/>
        </c:if>
        <header class="header">
            <div class="container">
                <div class="header-main">
                    <div class="header-logo">
                        <form action="#" autocomplete="off" method="post">
                            <a href="#">
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
                        <a href="ShowFacilityStudentController" class="header-profile-link">New Feedback</a>
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
        </header>

        <main>
            <section class="title">
                <div class="container">
                    <h1 class="title-content">Submited Feedback</h1>
                </div>
            </section>

            <section class="sort">
                <div class="container">
                    <div class="sort-inner">
                        <div class="image-wrap">
                            <img
                                src="https://cdn.discordapp.com/attachments/770804043794350100/888843834589597766/sort-by-attributes.png"
                                alt=""
                                class="dropdown-select"
                                />
                        </div>
                        <div class="dropdown-list">
                            <div class="list-item">
                                <a href="SortController?sort=DateFBU">Sort by date</a>
                            </div>
                            <div class="list-item">
                                <a href="SortController?sort=StatusFBU">Sort by status</a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section class="hero">
                <div class="container">
                    <div class="hero-inner">
                        <div class="hero-inner-heading">
                            <div>Feedback ID</div>
                            <div>Device</div>
                            <div>Location</div>
                            <div>Description</div>
                            <div>Date</div>
                            <div>Status</div>
                        </div>
                        <div class="hero-inner-list">
                            <div class="list-item">
                                <div>#1</div>
                                <div>quat,den</div>
                                <div>Room 302</div>
                                <div class="link">
                                    Lorem ipsum dolor sit amet consectetur, adipisicing elit.
                                    Tempore excepturi voluptatibus eum cumque sequi, placeat vel
                                    quod magnam nobis aut quo, dolorem natus quisquam vero
                                    asperiores, temporibus dolorum corrupti rerum.
                                </div>
                                <div>17/07/2001</div>
                                <div>
                                    <a href="#" class="btn btn-done">Done</a>
                                </div>
                            </div>
                            <div class="list-item">
                                <div>#1</div>
                                <div>quat,den</div>
                                <div>Room 302</div>
                                <div class="link">
                                    Lorem ipsum dolor sit amet consectetLorem ipsum dolor sit amet
                                    consectetur adipisicing elit.
                                </div>
                                <div>17/07/2001</div>
                                <div>
                                    <a href="#" class="btn btn-pending">pending</a>
                                </div>
                            </div>
                            <div class="list-item">
                                <div>#1</div>
                                <div>quat,den</div>
                                <div>Room 302</div>
                                <div class="link">
                                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Lorem
                                    ipsum dolor sit amet consectetur adipisicing elit.
                                </div>
                                <div>17/07/2001</div>
                                <div>
                                    <a href="#" class="btn btn-onGoing">on-going</a>
                                </div>
                            </div>
                            <div class="list-item">
                                <div>#1</div>
                                <div>quat,den</div>
                                <div>Room 302</div>
                                <div class="link">
                                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Lorem
                                    ipsum dolor sit amet consectetur adipisicing elit.
                                </div>
                                <div>17/07/2001</div>
                                <div>
                                    <a href="#" class="btn btn-decline">Decline</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>

        <footer class="footer">
            <div class="container">
                <div class="footer-main">
                    <div class="social">
                        <a href="#"
                           ><img
                                src="https://cdn.discordapp.com/attachments/770804043794350100/888843281767759954/facebook.png"
                                alt="FB"
                                /></a>
                        <a href="#"
                           ><img
                                src="https://cdn.discordapp.com/attachments/770804043794350100/888843280035495987/email.png"
                                alt="GG"
                                /></a>
                        <a href="#"
                           ><img
                                src="https://cdn.discordapp.com/attachments/770804043794350100/888843287748837416/twitter.png"
                                alt="twitter"
                                /></a>
                        <a href="#"
                           ><img
                                src="https://cdn.discordapp.com/attachments/770804043794350100/888843283122515988/instagram.png"
                                alt="instagram"
                                /></a>
                    </div>
                    <div class="info">
                        <a href="#">About</a>
                        <a href="#">Help Center</a>
                        <a href="#">Contact Us</a>
                    </div>
                </div>
            </div>
        </footer>

        <script src="${pageContext.request.contextPath}/js/UserViewFB.js"></script>
    </body>
</html>

