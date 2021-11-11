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

var done = JSON.parse(localStorage.getItem("Doneobj")) || {
    id: 0,
    flag: false
}
if (done.flag === true) {

    var id = done.id;
    $.ajax({
        url: "/SWP391_PROJECT/NotificationFromAdminDone",
        type: "post",
        dataType: "json",
        success: function (data) {
            set(ref(database, "Admin-done/" + id), {
                Feedback_ID: data.feedbackID,
                Email: data.email,
                Date: data.date,
                Name: data.fullName,
                User_ID: data.userID
            });
        }
    });
    done.flag = false;
    localStorage.setItem("Doneobj", JSON.stringify(done));
}





