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
      Array.from(document.querySelectorAll(".assign-form")).forEach(item=>{
          item.addEventListener("submit", (e)=>{
          e.preventDefault();
          console.log("123");
          var empID = e.target.querySelector('option:checked').value;
          var emp = JSON.parse(localStorage.getItem("Empobj"))||{
    id:0,
    flag:false
        }
//        if (emp.flag===true){
              e.target.submit()

        var id = emp.id;
        
$.ajax({
           

url: "/SWP391_PROJECT/NotificationFromEMP",
type: "POST",
                   data: {userid:  empID},
        dataType: "json",
        success: function (result) {
        set(ref(database, "Emp_Assign/" + id), {
          Feedback_ID: result.feedbackID,
          Email: result.email,
          Date: result.date,
          Name: result.fullName,
          User_ID : empID
        });
        }
});
emp.flag=false;
localStorage.setItem("Empobj", JSON.stringify(emp));
//}
      })
      })

     



      

      
