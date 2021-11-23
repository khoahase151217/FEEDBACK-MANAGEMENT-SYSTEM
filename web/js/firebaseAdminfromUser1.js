
//Email config:
//mail: fptfacilityfeedback@gmail.com
//password: Hoangtuquan2

import { initializeApp } from "https://www.gstatic.com/firebasejs/9.2.0/firebase-app.js";

const firebaseConfig = {
    apiKey: "AIzaSyDgj70nlsLRc3pYoa_I2NiIV1I8U3A8Eq8",
    authDomain: "facilityfeedbackmanagement.firebaseapp.com",
    databaseURL: "https://facilityfeedbackmanagement-default-rtdb.asia-southeast1.firebasedatabase.app",
    projectId: "facilityfeedbackmanagement",
    storageBucket: "facilityfeedbackmanagement.appspot.com",
    messagingSenderId: "904686223788",
    appId: "1:904686223788:web:67f1780443348a91517a09",
    measurementId: "G-VRY6H0EFMV"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

import {getDatabase, ref, set, child, update, remove, get, query, limitToLast, onChildAdded, onValue, }
from "https://www.gstatic.com/firebasejs/9.2.0/firebase-database.js";

//Get Database
const database = getDatabase();

//UserPage.jsp

//adminPage.jsp
window.onload = function () {

    //set new emp assign to firebase
    var emp = JSON.parse(localStorage.getItem("Empobj")) || {
            id: 0,
            emp_id: ""
        };
    if (emp.emp_id != "") {
        var tmp = emp.emp_id;
        $.ajax({
            url: "/SWP391_PROJECT/NotificationFromEMP",
            type: "POST",
            data: {userid: emp.emp_id},
            dataType: "json",
            success: function (result) {
                set(ref(database, "Emp_Assign/" + emp.id), {
                    Feedback_ID: result.feedbackID,
                    Email: result.email,
                    Date: result.date,
                    Name: result.fullName,
                    User_ID: tmp
                });
            }
        });


        emp.emp_id = "";
        localStorage.setItem("Empobj", JSON.stringify(emp));

    }

    // view notification for admin
    const userRef = ref(database, "/User-feedback");
    const responseRef = ref(database, "/Employee-response");
    const trashRef = ref(database, "/Employee-trash");

    onChildAdded(query(ref(database, "/User-feedback"), limitToLast(1)), (data) => {
        onValue(
                userRef,
                (snapshot) => {
            snapshot.forEach((childSnapshot) => {
console.log("123");

                var count = JSON.parse(localStorage.getItem("NotiCount")) || 0;
                count++;
                localStorage.setItem("NotiCount", JSON.stringify(count));
                var html = "<div class=\"notification-item\" onclick=\"handleReloadPage(event)\">\n"
                        + "                                                <div class=\"pipe-item-heading\">\n"
                        + "                                                    <div class=\"pipe-item-title-wrapper\">\n"
                        + "                                                        <h3 class=\"pipe-item-title\">Feedback " + childSnapshot.val().Feedback_ID + "</h3>\n"
                        + "                                                        <p class=\"pipe-item-desc\">\n"
                        + "                                                            <strong>Name:</strong> " + childSnapshot.val().Name + "\n"
                        + "                                                        </p>\n"
                        + "                                                    </div>\n"
                        + "                                                    <div class=\"pipe-item-date\">" + childSnapshot.val().Date + "</div>\n"
                        + "                                                </div>\n"
                        + "                                                <div class=\"pipe-item-bottom\">\n"
                        + "                                                    <p class=\"pipe-bottom-item\">\n"
                        + "                                                        <strong>Send by</strong>\n"
                        + "                                                        " + childSnapshot.val().Email + "\n"
                        + "                                                    </p>\n"
                        + "                                                </div>\n"
                        + "                                            </div>";

                document.querySelector(".pending-user-list").insertAdjacentHTML("beforeend", html);
                if (count !== 0) {
                    document.querySelector('.showcase-item-dropdown-actual-notification').classList.add('active');
                    document.querySelector('.showcase-item-dropdown-actual-notification').innerHTML = count;
                    document.querySelector('.showcase-item-dropdown-select').classList.add('active');
                    document.querySelector('.showcase-item-dropdown-sub-title').innerHTML = "You have " + count + " new feedback";
                }

                remove(ref(database, "User-feedback/" + childSnapshot.val().Feedback_ID), {
                    Feedback_ID: childSnapshot.val().Feedback_ID,
                    Email: childSnapshot.val().Email,
                    Date: childSnapshot.val().Date,
                    Name: childSnapshot.val().Name,

                });

            });
        },
                {
                    onlyOnce: true,
                }
        );
    });
    onChildAdded(query(ref(database, "/Employee-response"), limitToLast(1)), (data) => {
        onValue(
                responseRef,
                (snapshot) => {
            snapshot.forEach((childSnapshot) => {



                var count = JSON.parse(localStorage.getItem("NotiCount"));
                count++;
                localStorage.setItem("NotiCount", JSON.stringify(count));
                var html = "<div class=\"notification-item\" onclick=\"handleReloadPage(event)\">\n"
                        + "                                                <div class=\"pipe-item-heading\">\n"
                        + "                                                    <div class=\"pipe-item-title-wrapper\">\n"
                        + "                                                        <h3 class=\"pipe-item-title\">Feedback " + childSnapshot.val().Feedback_ID + "</h3>\n"
                        + "                                                        <p class=\"pipe-item-desc\">\n"
                        + "                                                            <strong>Send by:</strong> " + childSnapshot.val().Email + "\n"
                        + "                                                        </p>\n"
                        + "                                                    </div>\n"
                        + "                                                    <div class=\"pipe-item-date\">" + childSnapshot.val().Date + "</div>\n"
                        + "                                                </div>\n"
                        + "                                                <div class=\"pipe-item-bottom\">\n"
                        + "                                                    <p class=\"pipe-bottom-item\">\n"
                        + "                                                        <strong>Response by</strong>\n"
                        + "                                                        " + childSnapshot.val().Name + "\n"
                        + "                                                    </p>\n"
                        + "                                                </div>\n"
                        + "                                            </div>";
                document.querySelector(".response-list").insertAdjacentHTML("beforeend", html);
                if (count !== 0) {
                    document.querySelector('.showcase-item-dropdown-actual-notification').classList.add('active');
                    document.querySelector('.showcase-item-dropdown-actual-notification').innerHTML = count;
                    document.querySelector('.showcase-item-dropdown-select').classList.add('active');
                    document.querySelector('.showcase-item-dropdown-sub-title').innerHTML = "You have " + count + " new feedback";
                }

                remove(ref(database, "Employee-response/" + childSnapshot.key), {
                    Feedback_ID: childSnapshot.val().Feedback_ID,
                    Email: childSnapshot.val().Email,
                    Date: childSnapshot.val().Date,
                    Name: childSnapshot.val().Name,

                });

            });
        },
                {
                    onlyOnce: true,
                }
        );
    });
    onChildAdded(query(ref(database, "/Employee-trash"), limitToLast(1)), (data) => {
        onValue(
                trashRef,
                (snapshot) => {
            snapshot.forEach((childSnapshot) => {



                var count = JSON.parse(localStorage.getItem("NotiCount"));
                var trash = JSON.parse(localStorage.getItem("Trashobj"));
                count++;
                localStorage.setItem("NotiCount", JSON.stringify(count));
                var html = "<div class=\"notification-item\" onclick=\"handleReloadPage(event)\">\n"
                        + "                                                <div class=\"pipe-item-heading\">\n"
                        + "                                                    <div class=\"pipe-item-title-wrapper\">\n"
                        + "                                                        <h3 class=\"pipe-item-title\">Feedback " + childSnapshot.val().Feedback_ID + "</h3>\n"
                        + "                                                        <p class=\"pipe-item-desc\">\n"
                        + "                                                        </p>\n"
                        + "                                                    </div>\n"
                        + "                                                    <div class=\"pipe-item-date\">" + childSnapshot.val().Date + "</div>\n"
                        + "                                                </div>\n"
                        + "                                                <div class=\"pipe-item-bottom\">\n"
                        + "                                                    <p class=\"pipe-bottom-item\">\n"
                        + "                                                        <strong style=\"color:#fd0100\">Denied because of misInformation</strong>\n"
                        + "                                                    </p>\n"
                        + "                                                </div>\n"
                        + "                                            </div>";

                document.querySelector(".pending-trash-list").insertAdjacentHTML("beforeend", html);
                if (count !== 0) {
                    document.querySelector('.showcase-item-dropdown-actual-notification').classList.add('active');
                    document.querySelector('.showcase-item-dropdown-actual-notification').innerHTML = count;
                    document.querySelector('.showcase-item-dropdown-select').classList.add('active');
                    document.querySelector('.showcase-item-dropdown-sub-title').innerHTML = "You have " + count + " new feedback";
                }

                remove(ref(database, "Employee-trash/" + childSnapshot.key), {
                    Feedback_ID: childSnapshot.val().Feedback_ID,
                    Date: childSnapshot.val().Date,

                });

            });
        },
                {
                    onlyOnce: true,
                }
        );
    });

};
