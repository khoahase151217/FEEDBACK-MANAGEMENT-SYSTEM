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

// Dung Javascript
const GoodEmpList = document.querySelectorAll(".statistic-user-wrapper.statistic-goodemp-wrapper");
const BadEmpList = document.querySelectorAll(".statistic-user-wrapper.statistic-bademp-wrapper");
const BadUserList = document.querySelectorAll(".statistic-user-wrapper.statistic-student-wrapper");
const GoodEmpForward = document.querySelector(".icon-profile-forward.goodemp");
const GoodEmpBack = document.querySelector(".icon-profile-back.goodemp");
const BadEmpForward = document.querySelector(".icon-profile-forward.bademp");
const BadEmpBack = document.querySelector(".icon-profile-back.bademp");
const UserForward = document.querySelector(".icon-profile-forward.baduser");
const UserBack = document.querySelector("icon-profile-back.baduser");

var studentTab = document.getElementById("studentTab");
var employeeTab = document.getElementById("employeeTab");
var goodRole = document.getElementById("goodRole");
var badRole = document.getElementById("badRole");
var tabNavigation = document.getElementById("tabnavigation");
var studentNavigation = document.getElementById("roleStudent");
var employeeNavigation = document.getElementById("roleEmployee");
var employeeList = document.getElementById("employee-list");
var studentList = document.getElementById("student-list");
var roleGoodList = document.getElementById("roleGoodList");
var roleBadList = document.getElementById("roleBadList");
var goodempList = document.getElementById("good-emp-list");
var badempList = document.getElementById("bad-emp-list");
var goodbadOption = document.getElementById("goodbadOption");


if (document.querySelectorAll(".statistic-user-wrapper.statistic-student-wrapper").length === 1) {
    document.querySelector(".icon-profile-forward.profile-chevron-forward.baduser").style.display = "none";
    document.querySelector(".icon-profile-back.profile-chevron-back.baduser").style.display = "none";
}
if (document.querySelectorAll(".statistic-user-wrapper.statistic-bademp-wrapper").length === 1) {
    document.querySelector(".icon-profile-forward.profile-chevron-forward.bademp").style.display = "none";
    document.querySelector(".icon-profile-back.profile-chevron-back.bademp").style.display = "none";
}
if (document.querySelectorAll(".statistic-user-wrapper.statistic-goodemp-wrapper").length === 1) {
    document.querySelector(".icon-profile-forward.profile-chevron-forward.goodemp").style.display = "none";
    document.querySelector(".icon-profile-back.profile-chevron-back.goodemp").style.display = "none";
}

employeeTab.onclick = function () {
    tabNavigation.classList.remove("student");
    tabNavigation.classList.add("employee");
    employeeNavigation.classList.add("active");
    studentNavigation.classList.remove("active");
    studentList.classList.remove("active");
    employeeList.classList.add("active");
    goodempList.classList.add("active");
    badempList.classList.remove("active");
    document.querySelector(".user-behavior").classList.add("active");
    document.querySelector(".behavior-navigation.good").classList.add("active");
    document.querySelector(".behavior-navigation.bad").classList.remove("active");
    document.querySelector(".icon-profile-back.profile-chevron-back.goodemp").classList.add("active");
    document.querySelector(".icon-profile-forward.profile-chevron-forward.goodemp").classList.add("active");
    document.querySelector(".icon-profile-back.profile-chevron-back.bademp").classList.remove("active");
    document.querySelector(".icon-profile-forward.profile-chevron-forward.bademp").classList.remove("active");
    document.querySelector(".icon-profile-back.profile-chevron-back.baduser").classList.remove("active");
    document.querySelector(".icon-profile-forward.profile-chevron-forward.baduser").classList.remove("active");
};

studentTab.onclick = function () {
    tabNavigation.classList.remove("employee");
    tabNavigation.classList.add("student");
    studentNavigation.classList.add("active");
    employeeNavigation.classList.remove("active");
    employeeList.classList.remove("active");
    studentList.classList.add("active");
    document.querySelector(".user-behavior").classList.remove("active");
    document.querySelector(".icon-profile-back.profile-chevron-back.goodemp").classList.remove("active");
    document.querySelector(".icon-profile-forward.profile-chevron-forward.goodemp").classList.remove("active");
    document.querySelector(".icon-profile-back.profile-chevron-back.bademp").classList.remove("active");
    document.querySelector(".icon-profile-forward.profile-chevron-forward.bademp").classList.remove("active");
    document.querySelector(".icon-profile-back.profile-chevron-back.baduser").classList.add("active");
    document.querySelector(".icon-profile-forward.profile-chevron-forward.baduser").classList.add("active");
};

document.getElementById("goodRole").onclick = function () {
    document.querySelector(".behavior-navigation.good").classList.add("active");
    document.querySelector(".behavior-navigation.bad").classList.remove("active");
    document.querySelector(".icon-profile-back.profile-chevron-back.goodemp").classList.add("active");
    document.querySelector(".icon-profile-forward.profile-chevron-forward.goodemp").classList.add("active");
    document.querySelector(".icon-profile-back.profile-chevron-back.bademp").classList.remove("active");
    document.querySelector(".icon-profile-forward.profile-chevron-forward.bademp").classList.remove("active");
    document.querySelector(".icon-profile-back.profile-chevron-back.baduser").classList.remove("active");
    document.querySelector(".icon-profile-forward.profile-chevron-forward.baduser").classList.remove("active");
    goodempList.classList.add("active");
    badempList.classList.remove("active");
};

document.getElementById("badRole").onclick = function () {
    document.querySelector(".behavior-navigation.good").classList.remove("active");
    document.querySelector(".behavior-navigation.bad").classList.add("active");
    document.querySelector(".icon-profile-back.profile-chevron-back.goodemp").classList.remove("active");
    document.querySelector(".icon-profile-forward.profile-chevron-forward.goodemp").classList.remove("active");
    document.querySelector(".icon-profile-back.profile-chevron-back.bademp").classList.add("active");
    document.querySelector(".icon-profile-forward.profile-chevron-forward.bademp").classList.add("active");
    document.querySelector(".icon-profile-back.profile-chevron-back.baduser").classList.remove("active");
    document.querySelector(".icon-profile-forward.profile-chevron-forward.baduser").classList.remove("active");
    goodempList.classList.remove("active");
    badempList.classList.add("active");
};

// Good Emp List
document.querySelector(".icon-profile-forward.profile-chevron-forward.goodemp").addEventListener("click", () => {
    const index =
            document.querySelector(".statistic-user-wrapper.statistic-goodemp-wrapper.active").dataset.index ===
            GoodEmpList.length.toString()
            ? 0
            : document.querySelector(".statistic-user-wrapper.statistic-goodemp-wrapper.active").dataset.index;
    Array.from(GoodEmpList).forEach((ele) => ele.classList.remove("active"));
    Array.from(GoodEmpList)[index].classList.add("active");
});

document.querySelector(".icon-profile-back.profile-chevron-back.goodemp").addEventListener("click", () => {
    const index =
            Number.parseInt(
                    document.querySelector(".statistic-user-wrapper.statistic-goodemp-wrapper.active").dataset.index
                    ) === 1
            ? GoodEmpList.length + 1
            : Number.parseInt(
                    document.querySelector(".statistic-user-wrapper.statistic-goodemp-wrapper.active").dataset.index
                    );
    document.querySelector(".statistic-user-wrapper.statistic-goodemp-wrapper.active").classList.remove("active");
    Array.from(GoodEmpList)[index - 2].classList.add("active");
});

//////Bad Emp List
document.querySelector(".icon-profile-forward.profile-chevron-forward.bademp").addEventListener("click", () => {
    const index =
            document.querySelector(".statistic-user-wrapper.statistic-bademp-wrapper.active").dataset.index ===
            BadEmpList.length.toString()
            ? 0
            : document.querySelector(".statistic-user-wrapper.statistic-bademp-wrapper.active").dataset.index;
    Array.from(BadEmpList).forEach((ele) => ele.classList.remove("active"));
    Array.from(BadEmpList)[index].classList.add("active");
});

document.querySelector(".icon-profile-back.profile-chevron-back.bademp").addEventListener("click", () => {
    const index =
            Number.parseInt(
                    document.querySelector(".statistic-user-wrapper.statistic-bademp-wrapper.active").dataset.index
                    ) === 1
            ? BadEmpList.length + 1
            : Number.parseInt(
                    document.querySelector(".statistic-user-wrapper.statistic-bademp-wrapper.active").dataset.index
                    );
    document.querySelector(".statistic-user-wrapper.statistic-bademp-wrapper.active").classList.remove("active");
    Array.from(BadEmpList)[index - 2].classList.add("active");
});

//Bad User LIst
document.querySelector(".icon-profile-forward.profile-chevron-forward.baduser").addEventListener("click", () => {
    const index =
            document.querySelector(".statistic-user-wrapper.statistic-student-wrapper.active").dataset.index ===
            BadUserList.length.toString()
            ? 0
            : document.querySelector(".statistic-user-wrapper.statistic-student-wrapper.active").dataset.index;
    Array.from(BadUserList).forEach((ele) => ele.classList.remove("active"));
    Array.from(BadUserList)[index].classList.add("active");
});

document.querySelector(".icon-profile-back.profile-chevron-back.baduser").addEventListener("click", () => {
    const index =
            Number.parseInt(
                    document.querySelector(".statistic-user-wrapper.statistic-student-wrapper.active").dataset.index
                    ) === 1
            ? BadUserList.length + 1
            : Number.parseInt(
                    document.querySelector(".statistic-user-wrapper.statistic-student-wrapper.active").dataset.index
                    );
    document.querySelector(".statistic-user-wrapper.statistic-student-wrapper.active").classList.remove("active");
    Array.from(BadUserList)[index - 2].classList.add("active");
});


