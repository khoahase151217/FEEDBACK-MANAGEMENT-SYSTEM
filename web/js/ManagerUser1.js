document.querySelector(".user-form .modal").addEventListener("click", (e) => {
    if (!e.target.classList.contains("modal"))
        return;
    e.target.closest(".user-form").classList.remove("open");
});

document.querySelector(".user-form .modal").addEventListener("click", (e) => {
    if (!e.target.classList.contains("modal"))
        return;
    e.target.closest(".user-form").classList.remove("open");
});

const notificationItems =
        document.querySelectorAll(".notification-item");
Array.from(notificationItems).forEach((item) => {
    item.addEventListener("click", () => {
        window.location.replace("/SWP391_PROJECT/ShowFeedBackController");
    });
});
window.addEventListener('load', () => {
    console.log("456");
    
    //scrolltoview new FB
     const flag = localStorage.getItem('flag');
                                                    if (JSON.parse(flag) === true) {
                                                        var feedback = JSON.parse(localStorage.getItem('feedbackID'));
                                                        var id = feedback[1].toString();
                                                        var loop = document.getElementsByClassName("pipe-item-title");
                                                        var loopActive = document.getElementsByTagName("a");

                                                        for (let i = 0; i < Array.from(loop).length; i++) {
                                                            console.log(Array.from(loop)[i].innerHTML);
                                                            if ((Array.from(loop)[i].innerHTML).includes(id)) {
                                                                setTimeout(function () {
                                                                    (loop[i]).scrollIntoView({
                                                                        behavior: "smooth",
                                                                        block: "center"
                                                                    });
                                                                }, 700);
                                                            }
                                                        }

                                                        for (let i = 0; i < Array.from(loopActive).length; i++) {
                                                            if ((loopActive[i].href).includes(id) && (loopActive[i].href).includes("response_id")) {
                                                                loopActive[i].click();
                                                            }
                                                        }

                                                        localStorage.removeItem('flag');

                                                    }
    //set new noticout
        localStorage.setItem("NotiCount", JSON.stringify(0));
    }
function handleReloadPage(e) {
    var h3 = e.target.closest(".notification-item").querySelector(".pipe-item-title").innerHTML;
    var feedbackid = h3.split(" ");
    localStorage.setItem("feedbackID", JSON.stringify(feedbackid));
    localStorage.setItem('flag', true);  
        localStorage.setItem("NotiCount", JSON.stringify(0));

    window.location.replace("/SWP391_PROJECT/ShowFeedBackController");
}