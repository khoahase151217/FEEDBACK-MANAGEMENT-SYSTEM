// Menu event listener
const items = document.querySelectorAll(".showcase-item");
const contents = document.querySelectorAll(".showcase-content-item");
const navigationItems = document.querySelectorAll(".navigation-item");
const navigationScrollBar = document.querySelector(".navigation-scroll-bar");
const categoryList = document.querySelectorAll(".content-item-category a");
const contentMainList = document.querySelectorAll(".content-item-main-item");
const taskItems = document.querySelectorAll(".task-item");
const historyItems = document.querySelectorAll(".history-item");
const categoryFlag = JSON.parse(localStorage.getItem("CATEGORY_EMPLOYEE_PAGE")) || "task";

window.addEventListener('load', () => {
    localStorage.setItem("EmpCount", JSON.stringify(0));

    navigationScrollBar.style.left = document.querySelector('.navigation-item.active').offsetLeft + "px";
    navigationScrollBar.style.width = document.querySelector('.navigation-item.active').offsetWidth + "px";
    if (Array.from(taskItems).length === 0) {
        document.querySelector(".task-detail-wrapper").style.display = 'none';
    }
    if (Array.from(historyItems).length === 0) {
        document.querySelector(".history-detail-wrapper").style.display = 'none';
    }

    setTimeout(() => {
        document.querySelector('.pipe-item.history-item.active').scrollIntoView({behavior: "smooth", block: "center", inline: "nearest"});
        document.querySelector('.pipe-item.task-item.active').scrollIntoView({behavior: "smooth", block: "center", inline: "nearest"});
    }, 700);

    // save value of category ["task", "history"]
    if (categoryFlag === 'task') {
        document.querySelector('.input-hidden-wrapper').innerHTML = `
                <input type="hidden" name="LIST_STYLE_TASK" value="active"/>
                <input type="hidden" name="style_list" value="task"/>
                `;
    } else if (categoryFlag === 'history') {
        document.querySelector('.input-hidden-wrapper').innerHTML = `
                <input type="hidden" name="LIST_STYLE_HISTORY" value="active"/>
                <input type="hidden" name="style_list" value="history"/>
                `;
    } else {
        localStorage.setItem("CATEGORY_EMPLOYEE_PAGE", JSON.stringify("task"));
    }

});
// Javascript for Feedback Page
//Array.from(items).forEach((item) => {
//    item.addEventListener("click", (e) => {
//        Array.from(items).forEach((item) => item.classList.remove("active"));
//        e.target.closest(".showcase-item").classList.add("active");
//
//        Array.from(contents).forEach((content) =>
//            content.classList.remove("active")
//        );
//        let index = e.target.closest(".showcase-item").dataset.index;
//        Array.from(contents)[index].classList.add("active");
//    });
//});
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
        if (e.target.innerText === "History") {
            localStorage.setItem("CATEGORY_EMPLOYEE_PAGE", JSON.stringify("history"));
            document.querySelector('.input-hidden-wrapper').innerHTML = `
                <input type="hidden" name="LIST_STYLE_HISTORY" value="active"/>
                <input type="hidden" name="style_list" value="history"/>
                `;
        } else {
            localStorage.setItem("CATEGORY_EMPLOYEE_PAGE", JSON.stringify("task"));
            document.querySelector('.input-hidden-wrapper').innerHTML = `
                <input type="hidden" name="LIST_STYLE_TASK" value="active"/>
                <input type="hidden" name="style_list" value="task"/>
                `;
        }
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
    Array.from(chervonBacks)[0].style.display = "none";
    Array.from(chervonForwards)[0].style.display = "none";
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
    if (!e.target.classList.contains("modal"))
        return;
    e.target.closest(".reponse-form").classList.remove("open");
});

const formMessage = document.querySelector(".feedback-form-message");
formMessage.addEventListener("click", (e) => {
    if (!e.target.classList.contains("feedback-form-message-modal"))
        return;
    formMessage.classList.remove("open");
    document.querySelector(".reponse-form").classList.remove("open");

});

// Drag or upload files
const dropArea = document.querySelector(".reponse-form-drag-area");
const dragText = dropArea.querySelector("header");
dropArea.addEventListener("dragover", (event) => {
    event.preventDefault();
    dropArea.classList.add("active");
    dragText.textContent = "Release to Upload File";
});

dropArea.addEventListener("dragleave", () => {
    dropArea.classList.remove("active");
    dragText.textContent = "Drag & Drop to Upload File";
});

document.querySelector(".user-form .modal").addEventListener("click", (e) => {
    if (!e.target.classList.contains("modal"))
        return;
    e.target.closest(".user-form").classList.remove("open");
});
function handleReloadPage(e) {
    localStorage.setItem("EmpCount", JSON.stringify(0));
    window.location.replace("/SWP391_PROJECT/ShowFeedbackForEmpController");
}

Array.from(document.querySelectorAll(".btn-submit-links.trash")).forEach(item => {
    item.addEventListener("click", () => {
        var trash = JSON.parse(localStorage.getItem("Trashobj")) || {
            id: 0,
            flag: false
        };
        if (trash.flag === false) {
            trash.id++;
            trash.flag = true;
            localStorage.setItem("Trashobj", JSON.stringify(trash));
        }

    })
})

function decline() {
    var proceed = confirm("Do you want to decline this task?");
    if (proceed) {
        return true;
    } else {
        return false;
    }
}
