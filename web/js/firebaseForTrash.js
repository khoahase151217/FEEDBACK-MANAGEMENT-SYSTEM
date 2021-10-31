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
var trash = JSON.parse(localStorage.getItem("Trashobj"))||{
    id:0,
    flag:false
}
console.log(trash);
if (trash.flag===true){
    
    var id = trash.id;
$.ajax({
url: "/SWP391_PROJECT/NotificationFromTrash",
        type: "post",
        dataType: "json",
        success: function (data) {
            console.log(data);
        set(ref(database, "Employee-trash/" + id), {
          Feedback_ID: data.feedbackID,
          Date: data.date,
          Check: "true"
        });
        }
});
trash.flag=false;
localStorage.setItem("Trashobj", JSON.stringify(trash));
}


      

      


