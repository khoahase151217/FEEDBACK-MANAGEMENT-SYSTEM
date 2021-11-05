// Import init
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
var trash = JSON.parse(localStorage.getItem("Trashobj")) || {
    id: 0,
    flag: false
};
if (trash.flag === true) {

    var id = trash.id;
    $.ajax({
        url: "/SWP391_PROJECT/NotificationFromTrash",
        type: "post",
        dataType: "json",
        success: function (data) {
            set(ref(database, "Employee-trash/" + id), {
                Feedback_ID: data.feedbackID,
                Date: data.date,
            });
        }
    });
    trash.flag = false;
    localStorage.setItem("Trashobj", JSON.stringify(trash));
}







