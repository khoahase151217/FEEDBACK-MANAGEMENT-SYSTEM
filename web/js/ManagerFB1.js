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
        console.log(index);
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
            taskList.length.toString()
            ? 0
            : document.querySelector(".task-detail.active").dataset.index;
    Array.from(taskList).forEach((ele) => ele.classList.remove("active"));
    Array.from(taskList)[index].classList.add("active");
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

taskChervonBack.addEventListener("mouseover", (e) => {
    console.log(123);
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