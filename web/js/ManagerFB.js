const listFBCategory = document.querySelectorAll(".list-item");
const FbListItems = document.querySelectorAll(".list-showcase-item");

Array.from(listFBCategory).forEach((item) => {
    item.addEventListener("click", (e) => {
        Array.from(listFBCategory).forEach((item) => {
            item.classList.remove("active");
        });
        e.target.classList.add("active");

        // Show page tương ứng
        var index = e.target.dataset.index;
        Array.from(FbListItems).forEach((item) => {
            item.classList.remove("active");
        });
        Array.from(FbListItems)[index].classList.add("active");
    });
});

const pipeCommentItems = document.querySelectorAll(".pipe-comment-item");
Array.from(pipeCommentItems).forEach((item) => {
    item.addEventListener("click", (e) => {
        Array.from(pipeCommentItems).forEach((item) => {
            item.classList.remove("active");
        });
        e.target.closest(".pipe-comment-item").classList.add("active");
    });
});
const taskList = document.querySelectorAll(".task-detail");
const taskChervonBack = document.querySelector(".task-chevron-back");
const taskChervonForward = document.querySelector(".task-chevron-forward");

if (taskList.length === 1) {
    taskChervonBack.style.display = "none";
    taskChervonForward.style.display = "none";
}

taskChervonForward.addEventListener("click", () => {
    const index =
        document.querySelector(".task-detail.active").dataset.index ===
        taskList.length.toString() ?
        0 :
        document.querySelector(".task-detail.active").dataset.index;
    Array.from(taskList).forEach((ele) => ele.classList.remove("active"));
    Array.from(taskList)[index].classList.add("active");
});

taskChervonBack.addEventListener("click", () => {
    const index =
        Number.parseInt(
            document.querySelector(".task-detail.active").dataset.index
        ) === 1 ?
        taskList.length + 1 :
        Number.parseInt(
            document.querySelector(".task-detail.active").dataset.index
        );

    document.querySelector(".task-detail.active").classList.remove("active");
    Array.from(taskList)[index - 2].classList.add("active");
});

taskChervonBack.addEventListener("mouseover", (e) => {
    e.target.closest(".feedback-detail").style.borderTopLeftRadius = 0;
    e.target.closest(".feedback-detail").style.borderBottomLeftRadius = 0;
});

taskChervonBack.addEventListener("mouseout", (e) => {
    e.target.closest(".feedback-detail").style.borderRadius = `15px`;
});

taskChervonForward.addEventListener("mouseover", (e) => {
    e.target.closest(".feedback-detail").style.borderTopRightRadius = 0;
    e.target.closest(".feedback-detail").style.borderBottomRightRadius = 0;
});
taskChervonForward.addEventListener("mouseout", (e) => {
    e.target.closest(".feedback-detail").style.borderRadius = `15px`;
});

if (Array.from(pipeCommentItems).length === 0) {
    document.querySelector('.feedback-detail').style.display = 'none';
};

document.querySelector(".reponse-form").addEventListener("click", (e) => {
    if (!e.target.classList.contains("modal"))
        return;
    e.target.closest(".reponse-form").classList.remove("open");
});

document.querySelector(".modal-decline").addEventListener("click", (e) => {
    if (e.target.classList.contains("modal-decline")) {
        e.target.closest(".modal-decline").classList.remove("open");
    }
});



function handleScrollIntoView(e) {
    e.target.scrollIntoView({
        behavior: "smooth",
        block: "center"
    });
}

function handleReloadPage(e) {
    var h3 = e.target.closest(".notification-item").querySelector(".pipe-item-title").innerHTML;
    var feedbackid = h3.split(" ");
    localStorage.setItem("feedbackID", JSON.stringify(feedbackid));
    localStorage.setItem('flag', true);  
    localStorage.setItem("NotiCount", JSON.stringify(0));
    
    window.location.replace("/SWP391_PROJECT/ShowFeedBackController");
}
