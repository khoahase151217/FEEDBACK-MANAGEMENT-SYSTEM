
//Email config:
//mail: fptfacilityfeedback@gmail.com
//password: Hoangtuquan2
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.2.0/firebase-app.js";
// https://firebase.google.com/docs/web/setup#available-libraries
//Config
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
    var userid = document.getElementById("LOGIN_USER_ID").value
    const userRef = ref(database, "/Admin-done");
    onChildAdded(query(ref(database, "/Admin-done"), limitToLast(1)), (data) => {
        onValue(
                userRef,
                (snapshot) => {
            snapshot.forEach((childSnapshot) => {
                if (childSnapshot.val().User_ID === userid) {
                    var count = JSON.parse(localStorage.getItem("UserCount")) || 0;
                    var done = JSON.parse(localStorage.getItem("Doneobj"));
                    count++;
                    localStorage.setItem("UserCount", JSON.stringify(count));
                    var html = "<div class=\"notification-item\" onclick=\"handleReloadUserPage(event)\">\n"
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

                    document.querySelector(".pipe-list").insertAdjacentHTML("beforeend", html);
                    if (count !== 0) {
                        document.querySelector('.showcase-item-dropdown-actual-notification').classList.add('active');
                        document.querySelector('.showcase-item-dropdown-actual-notification').innerHTML = count;
                        document.querySelector('.showcase-item-dropdown-select').classList.add('active');
                        document.querySelector('.showcase-item-dropdown-sub-title').innerHTML = `You have <strong>${count}</strong> new feedback`;
//                        document.querySelector('.showcase-item-dropdown-sub-title').innerHTML = 'You have '+count+'new feedback';
                    }

                    remove(ref(database, "Admin-done/" +  childSnapshot.key), {
                        Feedback_ID: childSnapshot.val().Feedback_ID,
                        Email: childSnapshot.val().Email,
                        Date: childSnapshot.val().Date,
                        Name: childSnapshot.val().Name,
                        User_ID: childSnapshot.val().User_ID
                    });
                }
            });

        },
                {
                    onlyOnce: true,
                }
        );
    });
};

