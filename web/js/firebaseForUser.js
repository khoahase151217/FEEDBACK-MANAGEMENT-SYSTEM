// Import init
      import { initializeApp } from "https://www.gstatic.com/firebasejs/9.2.0/firebase-app.js";
      // https://firebase.google.com/docs/web/setup#available-libraries
      //Config
      const firebaseConfig = {
        apiKey: "AIzaSyD9U7cIu7NbCPi350YcGyN-vK3Eo87Ay8k",
        authDomain: "facilitymanagement-77ffc.firebaseapp.com",
        databaseURL:
          "https://facilitymanagement-77ffc-default-rtdb.asia-southeast1.firebasedatabase.app",
        projectId: "facilitymanagement-77ffc",
        storageBucket: "facilitymanagement-77ffc.appspot.com",
        messagingSenderId: "487191445857",
        appId: "1:487191445857:web:f4054820d8f6cdbdf84520",
        measurementId: "G-QSQRMFMV4D",
      };

      // Initialize Firebase
      const app = initializeApp(firebaseConfig);

      import {getDatabase,ref,set,child,update,remove,get,query,limitToLast,onChildAdded,onValue,} 
      from "https://www.gstatic.com/firebasejs/9.2.0/firebase-database.js";

      //Get Database
      const database = getDatabase();
if (document.querySelector(".feedback-form-message").classList.contains("open")){
if (document.querySelector(".status-success").classList.contains("active")){
$.ajax({
url: "/SWP391_PROJECT/Test",
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);
        set(ref(database, "User-feedback/" + data.feedbackID), {
          Feedback_ID: data.feedbackID,
          Email: data.email,
          Date: data.date,
          Name: data.fullName,
          Check: "true"
        });
        }
});
}
}

      

      
