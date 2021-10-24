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