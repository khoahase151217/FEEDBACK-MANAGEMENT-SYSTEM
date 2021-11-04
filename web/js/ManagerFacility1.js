const listFacilityCategory = document.querySelectorAll(".facility-list-item");
const facilityListItems = document.querySelectorAll(
        ".facility-list-showcase-item"
        );
Array.from(listFacilityCategory).forEach((category) => {
    category.addEventListener("click", (e) => {
        Array.from(listFacilityCategory).forEach((item) => {
            item.classList.remove("active");
        });
        e.target.classList.add("active");

        // Show page tương ứng
        var index = e.target.dataset.index;
        Array.from(facilityListItems).forEach((item) => {
            item.classList.remove("active");
        });
        Array.from(facilityListItems)[index].classList.add("active");
    });
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


function handleReloadPage(e) {
    var h3 = e.target.closest(".notification-item").querySelector(".pipe-item-title").innerHTML;
    var feedbackid = h3.split(" ");
    localStorage.setItem("feedbackID", JSON.stringify(feedbackid));
    localStorage.setItem('flag', true);  
        localStorage.setItem("NotiCount", JSON.stringify(0));

    window.location.replace("/SWP391_PROJECT/ShowFeedBackController");
}
