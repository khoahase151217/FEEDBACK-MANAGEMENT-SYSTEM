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

      import {getDatabase,ref,set,child,update,remove,get,query,limitToLast,onChildAdded,onValue,} 
      from "https://www.gstatic.com/firebasejs/9.2.0/firebase-database.js";

      //Get Database
      const database = getDatabase();
      
      var empID =document.querySelector('option[id="employee_ID"]').value;
      var emp = JSON.parse(localStorage.getItem("Empobj"))||{
    id:0,
    flag:false
        }
        if (emp.flag===true){
    
        var id = emp.id;
$.ajax({
url: "/SWP391_PROJECT/NotificationFromEMP",
        data: {userid:  empID},
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);
        set(ref(database, "Emp_Assign/" + id), {
          Feedback_ID: data.feedbackID,
          Email: data.email,
          Date: data.date,
          Name: data.fullName,
          User_ID : empID
        });
        }
});
emp.flag=false;
localStorage.setItem("Empobj", JSON.stringify(emp));
}




      

      
