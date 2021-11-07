<%-- 
    Document   : EmployeeReponse
    Created on : Sep 27, 2021, 7:48:52 PM
    Author     : Admin
--%>

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
            href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap"
            rel="stylesheet"
            />

        <script
            type="module"
            src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"
        ></script>
        <script
            nomodule
            src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"
        ></script>

        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_EMP == null}">
            <c:redirect url="login.jsp"/>
        </c:if>
        <header class="header">
            <div class="container">
                <div class="header-main">
                    <div class="header-logo">
                        <form action="#" autocomplete="off">
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
                        <div class="header-profile-dropdown">
                            <div class="header-profile-image">
                                <img
                                    src="https://images.unsplash.com/photo-1632331591393-96d5f060fd45?ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwzfHx8ZW58MHx8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"
                                    alt="user-profile"
                                    />
                            </div>
                            <div class="dropdown-list">
                                <a href="#" class="dropdown-item">
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
                    <div class="hero-inner">
                        <div class="hero-heading">
                            <div class="hero-heading-link">
                                <a href="#">
                                    <ion-icon name="arrow-back"></ion-icon>
                                </a>
                            </div>
                            <h2 class="hero-heading-title">Reply</h2>
                        </div>
                        <form action="#" class="hero-form">
                            <div class="rows">
                                <div class="row-item">
                                    <h3 class="name">Message:</h3>
                                    <textarea
                                        name="message"
                                        id="message"
                                        placeholder="Enter ..."
                                        ></textarea>
                                </div>
                                <div class="row-item">
                                    <div class="input-file-wrap">
                                        <input type="text" disabled />
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
                                </div>
                            </div>
                            <div class="rows">
                                <div class="row-item">
                                    <input type="submit" value="Send" name="action" class="btn" />
                                </div>
                            </div>
                        </form>
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

        <script src="js/index.js"></script>
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
        </script>
    </body>
</html>

