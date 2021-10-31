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
                          
        const userRef = ref(database, "/User-feedback");
        const responseRef = ref(database, "/Employee-response");
        const trashRef = ref(database, "/Employee-trash");
        onChildAdded(query(ref(database, "/User-feedback"), limitToLast(1)), (data) => {
          onValue(
            userRef,
            (snapshot) => {
              snapshot.forEach((childSnapshot) => {
                const childCheck = childSnapshot.val().Check;
                if (childCheck === "true") {
                                var count = JSON.parse(localStorage.getItem("NotiCount"))||0;
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
                    
                    document.querySelector(".pending-user-list").insertAdjacentHTML("beforeend",html);
                    if (count !== 0) {
                                                                     document.querySelector('.showcase-item-dropdown-actual-notification').classList.add('active');
                                                                    document.querySelector('.showcase-item-dropdown-actual-notification').innerHTML=count;
                                                                     document.querySelector('.showcase-item-dropdown-select').classList.add('active');
                                                                     document.querySelector('.showcase-item-dropdown-sub-title').innerHTML="You have " + count + " new feedback";
                                                                }

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
        onChildAdded(query(ref(database, "/Employee-response"), limitToLast(1)), (data) => {
          onValue(
            responseRef,
            (snapshot) => {
              snapshot.forEach((childSnapshot) => {

                const childCheck = childSnapshot.val().Check;
                if (childCheck === "true") {
                                var count = JSON.parse(localStorage.getItem("NotiCount"));
                                count++;
                                localStorage.setItem("NotiCount", JSON.stringify(count));
                    var html = "<div class=\"notification-item\" onclick=\"handleReloadPage(event)\">\n"
                        + "                                                <div class=\"pipe-item-heading\">\n"
                        + "                                                    <div class=\"pipe-item-title-wrapper\">\n"
                        + "                                                        <h3 class=\"pipe-item-title\">Feedback " +  childSnapshot.val().Feedback_ID + "</h3>\n"
                        + "                                                        <p class=\"pipe-item-desc\">\n"
                        + "                                                            <strong>Send by:</strong> " + childSnapshot.val().Email+ "\n"
                        + "                                                        </p>\n"
                        + "                                                    </div>\n"
                        + "                                                    <div class=\"pipe-item-date\">" + childSnapshot.val().Date + "</div>\n"
                        + "                                                </div>\n"
                        + "                                                <div class=\"pipe-item-bottom\">\n"
                        + "                                                    <p class=\"pipe-bottom-item\">\n"
                        + "                                                        <strong>Response by</strong>\n"
                        + "                                                        " + childSnapshot.val().Name+ "\n"
                        + "                                                    </p>\n"
                        + "                                                </div>\n"
                        + "                                            </div>";
                    document.querySelector(".response-list").insertAdjacentHTML("beforeend",html);
                    if (count !== 0) {
                                                                     document.querySelector('.showcase-item-dropdown-actual-notification').classList.add('active');
                                                                    document.querySelector('.showcase-item-dropdown-actual-notification').innerHTML=count;
                                                                     document.querySelector('.showcase-item-dropdown-select').classList.add('active');
                                                                     document.querySelector('.showcase-item-dropdown-sub-title').innerHTML="You have " + count + " new feedback";
                                                                }

                  update(ref(database, "Employee-response/" + childSnapshot.key), {
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
        onChildAdded(query(ref(database, "/Employee-trash"), limitToLast(1)), (data) => {
          onValue(
            trashRef,
            (snapshot) => {
              snapshot.forEach((childSnapshot) => {
                const childCheck = childSnapshot.val().Check;
                console.log(childCheck);
                if (childCheck === "true") {
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
                    
                    document.querySelector(".pending-trash-list").insertAdjacentHTML("beforeend",html);
                    if (count !== 0) {
                                                                     document.querySelector('.showcase-item-dropdown-actual-notification').classList.add('active');
                                                                    document.querySelector('.showcase-item-dropdown-actual-notification').innerHTML=count;
                                                                     document.querySelector('.showcase-item-dropdown-select').classList.add('active');
                                                                     document.querySelector('.showcase-item-dropdown-sub-title').innerHTML="You have " + count + " new feedback";
                                                                }

                  update(ref(database, "Employee-trash/" + trash.id), {
                    Feedback_ID: childSnapshot.val().Feedback_ID,
          Date: childSnapshot.val().Date,
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

