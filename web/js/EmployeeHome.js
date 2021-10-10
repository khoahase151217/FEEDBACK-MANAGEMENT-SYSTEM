// Menu event listener
const items = document.querySelectorAll(".showcase-item");
const contents = document.querySelectorAll(".showcase-content-item");
const navigationItems = document.querySelectorAll(".navigation-item");
const navigationScrollBar = document.querySelector(".navigation-scroll-bar");
const categoryList = document.querySelectorAll(".content-item-category a");
const contentMainList = document.querySelectorAll(".content-item-main-item");
const pipeItems = document.querySelectorAll(".pipe-item");
// Javascript for Feedback Page
Array.from(items).forEach((item) => {
    item.addEventListener("click", (e) => {
        Array.from(items).forEach((item) => item.classList.remove("active"));
        e.target.closest(".showcase-item").classList.add("active");

        Array.from(contents).forEach((content) =>
            content.classList.remove("active")
        );
        let index = e.target.closest(".showcase-item").dataset.index;
        Array.from(contents)[index].classList.add("active");
    });
});

Array.from(navigationItems).forEach((item) => {
    item.addEventListener("click", (e) => {
        Array.from(navigationItems).forEach((item) =>
            item.classList.remove("active")
        );
        e.target.closest(".navigation-item").classList.add("active");

        navigationScrollBar.style.left = e.target.offsetLeft + "px";
        navigationScrollBar.style.width = e.target.offsetWidth + "px";
        // Show content tương ứng
        let index = e.target.closest(".navigation-item").dataset.index;
        Array.from(contentMainList).forEach((item) =>
            item.classList.remove("active")
        );
        Array.from(contentMainList)[index].classList.add("active");
    });
});
//Array.from(pipeItems).forEach((item) => {
//    item.addEventListener("click", (e) => {
//        Array.from(pipeItems).forEach((item) => {
//            item.classList.remove("active");
//        });
//        e.target.closest(".pipe-item").classList.add("active");
//    });
//});

// XH chervon back and forward to change feedbackDetail
const taskList = document.querySelectorAll(".task-detail");
const taskChervonBack = document.querySelector(".task-chevron-back");
const taskChervonForward = document.querySelector(".task-chevron-forward");
const historyList = document.querySelectorAll(".history-detail");
const historyChervonBack = document.querySelector(".history-chevron-back");
const historyChervonForward = document.querySelector(
        ".history-chevron-forward"
        );
const chervonBacks = document.querySelectorAll(".icon-chevron-back");
const chervonForwards = document.querySelectorAll(".icon-chevron-forward");

if (taskList.length === 1) {
    chervonBack.style.display = "none";
    taskChervonForward.style.display = "none";
}
if (historyList.length === 1) {
    historyChervonBack.style.display = "none";
    historyChervonForward.style.display = "none";
}

taskChervonForward.addEventListener("click", () => {
    const index =
            document.querySelector(".task-detail.active").dataset.index ===
            taskList.length.toString()
            ? 0
            : document.querySelector(".task-detail.active").dataset.index;
    Array.from(taskList).forEach((ele) => ele.classList.remove("active"));
    Array.from(taskList)[index].classList.add("active");
});

historyChervonForward.addEventListener("click", () => {
    const index =
            document.querySelector(".history-detail.active").dataset.index ===
            historyList.length.toString()
            ? 0
            : document.querySelector(".history-detail.active").dataset.index;
    Array.from(historyList).forEach((ele) => ele.classList.remove("active"));
    Array.from(historyList)[index].classList.add("active");
});

taskChervonBack.addEventListener("click", () => {
    const index =
            Number.parseInt(
                    document.querySelector(".task-detail.active").dataset.index
                    ) === 1
            ? taskList.length + 1
            : Number.parseInt(
                    document.querySelector(".task-detail.active").dataset.index
                    );

    document.querySelector(".task-detail.active").classList.remove("active");
    Array.from(taskList)[index - 2].classList.add("active");
});

historyChervonBack.addEventListener("click", () => {
    const index =
            Number.parseInt(
                    document.querySelector(".history-detail.active").dataset.index
                    ) === 1
            ? historyList.length + 1
            : Number.parseInt(
                    document.querySelector(".history-detail.active").dataset.index
                    );

    document.querySelector(".history-detail.active").classList.remove("active");
    Array.from(historyList)[index - 2].classList.add("active");
});

Array.from(chervonBacks).forEach((chervonBack) => {
    chervonBack.addEventListener("mouseover", (e) => {
        e.target.closest(".feedback-detail").style.borderTopLeftRadius = 0;
        e.target.closest(".feedback-detail").style.borderBottomLeftRadius = 0;
    });

    chervonBack.addEventListener("mouseout", (e) => {
        e.target.closest(".feedback-detail").style.borderRadius = `15px`;
    });
});
Array.from(chervonForwards).forEach((chervonForward) => {
    chervonForward.addEventListener("mouseover", (e) => {
        e.target.closest(".feedback-detail").style.borderTopRightRadius = 0;
        e.target.closest(".feedback-detail").style.borderBottomRightRadius = 0;
    });
    chervonForward.addEventListener("mouseout", (e) => {
        e.target.closest(".feedback-detail").style.borderRadius = `15px`;
    });
});

// Pop-up form response Messsage
document.querySelector(".reponse-form").addEventListener("click", (e) => {
    if (e.target.classList.contains(".reponse-form-main"))
        return;
    e.target.closest(".reponse-form").classList.remove("open");
});

document.querySelectorAll(".send-btn").forEach((btn) => {
    btn.addEventListener("click", () => {
        console.log(123);
        document.querySelector(".reponse-form").classList.add("open");
    });
});
