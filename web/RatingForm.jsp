<%-- 
    Document   : RatingForm
    Created on : Nov 5, 2021, 11:38:05 AM
    Author     : Hyst
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Rating Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Rating1.css" />
    </head>

    <body>
        <form action="RatingController">
            <input type="hidden" name="UserID" value="${sessionScope.UserID}"/>
            <input type="hidden" name="feedbackID" value="${sessionScope.feedbackID}"/>
            <div class="rating-box">
                <div class="rating-title">
                    Rating Our Service
                </div>
                <div class="under-title">
                    Tell us about your experience
                </div>
                <div class="rating-icon">
                    <label>
                        <input type="radio" name="stars" value="-1" class="input-check"/>
                        <span class="icon">😡</span>
                    </label>
                    <label>
                        <input type="radio" name="stars" value="0" class="input-check"/>
                        <span class="icon">😕</span>
                    </label>
                    <label>
                        <input type="radio" name="stars" value="1" class="input-check"/>
                        <span class="icon">😐</span>
                    </label>
                    <label>
                        <input type="radio" name="stars" value="2" class="input-check"/>
                        <span class="icon">😊</span>
                    </label>
                    <label>
                        <input type="radio" name="stars" value="3" class="input-check"/>
                        <span class="icon">😆</span>
                    </label>
                    <div class="comment">
                        <div class="comment-title">
                            Do you have any thoughts you'd like to share?
                        </div>
                        <div class="comment-box">
                            <textarea name="comment" required=""></textarea>
                        </div>
                    </div>
                    <div class="send-button">
                        <input type="submit" value="Send">
                    </div>
                </div>
            </div>
        </form>
    </body>
    <script src="${pageContext.request.contextPath}/js/Rating.js"></script>
</html>
