
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
const app = initializeApp(firebaseConfig);

import {getDatabase, ref, set, child, update, remove, get, query, limitToLast, onChildAdded, onValue, }
from "https://www.gstatic.com/firebasejs/9.2.0/firebase-database.js";


//Get Database
const database = getDatabase();

if (document.querySelector(".feedback-form-message").classList.contains("open")) {
    if (document.querySelector(".status-success").classList.contains("active")) {
        $.ajax({
            url: "/SWP391_PROJECT/NotificationFromUser",
            type: "post",
            dataType: "json",
            success: function (data) {
                set(ref(database, "User-feedback/" + data.feedbackID), {
                    Feedback_ID: data.feedbackID,
                    Email: data.email,
                    Date: data.date,
                    Name: data.fullName,
                });
            }
        });
    }
}




