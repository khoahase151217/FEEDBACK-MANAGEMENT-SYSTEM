<%-- 
    Document   : login
    Created on : Sep 19, 2021, 1:33:13 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>FPT facilities feedback management</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
    </head>
    <body>
        <%String check = request.getAttribute("flag") != null ? "sign-up-mode" : "sign-in-mode";%>
        <main class="<%=check%>">  
            <div class="box">
                <div class="inner-box">
                    <div class="form-wrap">
                        <form action="MainController" method="POST" class="sign-in-form">
                            <div class="logo">
                                <img
                                    src="https://cdn.discordapp.com/attachments/770804043794350100/888843339439407104/toolkit.png"
                                    alt="Logo"
                                    />
                                <h4>Traversy</h4>
                            </div>

                            <div class="text-wrap">
                                <h2>Welcome back</h2>
                                <div class="message-wrap ${requestScope.INVALID}">
                                    <h6>
                                        To stay connected with FPT please login with your
                                        credentials by entering email address and password
                                    </h6>
                                    <div class="message">
                                        ${requestScope.ERROR_MESSAGE}
                                    </div>
                                </div>
                            </div>

                            <div class="actual-form">
                                <div class="input-wrap ${requestScope.INVALID}">
                                    <input type="text" name="userName" />
                                    <label>User Name</label>
                                </div>
                                <div class="input-wrap ${requestScope.INVALID}">
                                    <input type="password" name="password" />
                                    <label>Password</label>
                                </div>
                                <div class="actual-form-footer">
                                    <div class="checkbox">
                                        <input type="checkbox" name="rememberMe" />
                                        <label>Remember Me</label>
                                    </div>
                                    <a href="#">Forgot password</a>
                                </div>

                                <div class="sign-btn">
                                    <input type="submit" name="action" value="Login" />
                                    <div class="or-container">
                                        <div class="line-separator"></div>
                                        <div class="or-label">or</div>
                                        <div class="line-separator"></div>
                                    </div>
                                    <a
                                        class="btn-google"
                                        href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/SWP391_PROJECT/LoginGoogleController&response_type=code&client_id=988197174823-vpp8lg3rrd0onf7n1fisnjnifenipk7f.apps.googleusercontent.com&approval_prompt=force"                                           
                                        />
                                    <img
                                        src="./img/google-logo.png"
                                        alt="Google Icon" />Signup Using Google
                                    </a>
                                </div>
                            </div>

                            <div class="form-footer">

                                <h6>
                                    By sighning up, you agree to FPT's
                                    <a href="#">Terms and Conditions</a> &
                                    <a href="#">Privacy Policy</a>
                                </h6>
                            </div>
                        </form>

                        <form action="MainController" class="sign-up-form" method = "POST">
                            <div class="logo">
                                <img src="https://printgo.vn/uploads/file-logo/1/x512x512.ed307e1f3d9bb4741ffa3f1641a78911.ai.1.png.pagespeed.ic.z15Dl9fqQf.webp" alt="FPT" />
                                <h4>Traversy</h4>
                            </div>

                            <div class="text-wrap">
                                <h2>Signing Up</h2>
                                <div class="message-wrap ${requestScope.INVALID}">
                                    <h6>
                                        To stay connected with FPT please sign up by entering name
                                        and password
                                    </h6>
                                    <div class="message">
                                        <p>${requestScope.FULLNAME_MESSAGE}</p>
                                        <p>${requestScope.PASSWORD_MESSAGE}</p>
                                        <p>${requestScope.CONFIRM_PASSWORD_MESSAGE}</p>
                                    </div>
                                </div>
                            </div>

                            <div class="actual-form">
                                <div class="input-wrap readonly">
                                    <input
                                        type="text"
                                        name="userName"
                                        value="${requestScope.email}"
                                        readonly
                                        required
                                        />
                                    <label>User Name</label>
                                </div>
                                <div class="input-wrap ${requestScope.FULLNAME_FLAG}">
                                    <input type="text" name="fullName" required/>
                                    <label>Full Name</label>
                                </div>
                                <div class="input-wrap ${requestScope.PASSWORD_FLAG}">
                                    <input type="password" name="password" required/>
                                    <label>Password</label>
                                </div>
                                <div class="input-wrap ${requestScope.CONFIRM_PASSWORD_FLAG}">
                                    <input type="password" name="confirmPassword" required/>
                                    <label>Confirm Password</label>
                                </div>

                                <div class="sign-btn">
                                    <input type="submit" name="action" value="Sign Up" />
                                </div>
                            </div>

                            <div class="form-footer">
                                <h6>
                                    By sighning up, you agree to FPT's
                                    <a href="#">Terms and Conditions</a> &
                                    <a href="#">Privacy Policy</a>
                                </h6>
                            </div>
                        </form>
                    </div>

                    <div class="carousel">
                        <div class="image-wrap">
                            <img
                                src="./img/image1.png"
                                alt="image-1"
                                class="image img-1 show"
                                />
                            <img src="./img/image2.png" alt="image-2" class="image img-2" />
                            <img src="./img/image3.png" alt="image-3" class="image img-3" />
                        </div>

                        <div class="text-slider">
                            <div class="text-wrap">
                                <div class="text-group">
                                    <h2>Create your own feedback</h2>
                                    <h2>Customize as you like</h2>
                                    <h2>Handle for good environment</h2>
                                </div>
                            </div>
                            <div class="bullets">
                                <span class="active" data-index="1"></span>
                                <span data-index="2"></span>
                                <span data-index="3"></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <script src="${pageContext.request.contextPath}/js/login.js"></script>
    </body>
</html>
