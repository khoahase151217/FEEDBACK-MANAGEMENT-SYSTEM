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

      //UserPage.jsp
     
      //adminPage.jsp
      window.onload = function () {
        const dbRef = ref(database, "/User-feedback");
        onChildAdded(query(ref(database, "/User-feedback"), limitToLast(1)), (data) => {
          onValue(
            dbRef,
            (snapshot) => {
              snapshot.forEach((childSnapshot) => {
                  console.log(childSnapshot.id);
                const childCheck = childSnapshot.val().Check;
                if (childCheck === "true") {
                  alert(
                    "ID:" +
                      " " +
                      childSnapshot.val().Feedback_ID +
                      " " +
                      "Email:" +
                      " " +
                      childSnapshot.val().Email +
                      " " +
                      "Date:" +
                      " " +
                      childSnapshot.val().Date +
                      "Name:" +
                      " " +
                      childSnapshot.val().Name
                  );
                  update(ref(database, "User-feedback/" + childSnapshot.val().Feedback_ID), {
                    Feedback_ID: childSnapshot.val().Feedback_ID,
          Email: childSnapshot.val().Email,
          Date: childSnapshot.val().Date,
          Name: childSnapshot.val().Name,
          Check: "false"
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

