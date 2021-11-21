

// Javascript for Feedback Page
//Array.from(items).forEach((item) => {
//    item.addEventListener("click", (e) => {
//        Array.from(items).forEach((item) => item.classList.remove("active"));
//        e.target.closest(".showcase-item").classList.add("active");
//
//        Array.from(contents).forEach((content) =>
//            content.classList.remove("active")// Menu event listener
const items = document.querySelectorAll(".showcase-item");
const contents = document.querySelectorAll(".showcase-content-item");
const navigationItems = document.querySelectorAll(".navigation-item");
const navigationScrollBar = document.querySelector(".navigation-scroll-bar");
const categoryList = document.querySelectorAll(".content-item-category a");
const contentMainList = document.querySelectorAll(".facility-item-main-item");
const categoryItems = document.querySelectorAll(
        ".feedback-item-main-category-item"
        );
const feedbackDetailModal = document.querySelector(".feedbackDetail");
const pipeItems = document.querySelectorAll(".pipe-item");
const userCategory = document.querySelectorAll(".category-item");
const userContentItems = document.querySelectorAll(".user-content-main-item");
const userCategoryList = document.querySelectorAll(".show-category a");
const facilityCategoryList = document.querySelectorAll(
        ".show-category-facility a"
        );
const userCategoryContentItems = document.querySelectorAll(".user-main-item");
const facilityCategoryContentItems = document.querySelectorAll(
        ".facility-item-main"
        );

window.addEventListener('load', () => {
console.log("456")
    //scrolltoview new FB
    const flag = localStorage.getItem('flag');
    if (JSON.parse(flag) === true) {
        var feedback = JSON.parse(localStorage.getItem('feedbackID'));
        var id = feedback[1].toString();
        var loop = document.getElementsByClassName("pipe-item-title");
        var loopActive = document.getElementsByTagName("a");

        for (let i = 0; i < Array.from(loop).length; i++) {
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

    navigationScrollBar.style.left = document.querySelector('.navigation-item.active').offsetLeft + "px";
    navigationScrollBar.style.width = document.querySelector('.navigation-item.active').offsetWidth + "px";
    if (document.querySelector('.facility-item-main-item.active').dataset.index === '2') {
        document.querySelector(".content-item-category").classList.remove('show');
    }

});
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

        // Vẫn hiện thị content của column Pipe
        // Array.from(categoryItems).forEach((item) =>
        //   item.classList.remove("active")
        // );
        // Array.from(categoryItems)[0].classList.add("active");
        // Array.from(categoryList).forEach((category) => {
        //   category.classList.remove("active");
        // });
        // Array.from(categoryList)[0].classList.add("active");

        // Show content tương ứng
        let index = e.target.closest(".navigation-item").dataset.index;
        Array.from(contentMainList).forEach((item) =>
            item.classList.remove("active")
        );
        Array.from(contentMainList)[index].classList.add("active");
        if (index === "0")
            e.target
                    .closest(".showcase-content-item")
                    .querySelector(".content-item-category")
                    .classList.add("show");
        else
            e.target
                    .closest(".showcase-content-item")
                    .querySelector(".content-item-category")
                    .classList.remove("show");
    });
});

// category: pipe, list
// Feedback Page
Array.from(categoryList).forEach((category) => {
    category.addEventListener("click", (e) => {
        Array.from(categoryList).forEach((category) => {
            category.classList.remove("active");
        });
        e.target.closest("a").classList.add("active");

        // Show content tương ứng
        let index = e.target.closest("a").dataset.index;
        Array.from(categoryItems).forEach((item) =>
            item.classList.remove("active")
        );
        Array.from(categoryItems)[index].classList.add("active");
    });
});

// User Page
Array.from(userCategoryList).forEach((category) => {
    category.addEventListener("click", (e) => {
        Array.from(userCategoryList).forEach((category) => {
            category.classList.remove("active");
        });
        e.target.closest("a").classList.add("active");

        // Show content tương ứng
        let index = e.target.closest("a").dataset.index;
        Array.from(userCategoryContentItems).forEach((item) =>
            item.classList.remove("active")
        );
        Array.from(userCategoryContentItems)[index].classList.add("active");
    });
});

// Facility Page
Array.from(facilityCategoryList).forEach((category) => {
    category.addEventListener("click", (e) => {
        Array.from(facilityCategoryList).forEach((category) => {
            category.classList.remove("active");
        });
        e.target.closest("a").classList.add("active");

        // Show content tương ứng
        let index = e.target.closest("a").dataset.index;
        Array.from(facilityCategoryContentItems).forEach((item) =>
            item.classList.remove("active")
        );
        Array.from(facilityCategoryContentItems)[index].classList.add("active");
    });
});

// Javascript for User page
Array.from(userCategory).forEach((item) => {
    item.addEventListener("click", (e) => {
        Array.from(userCategory).forEach((item) => {
            item.classList.remove("active");
        });
        e.target.classList.add("active");

        // Show page tương ứng
        var index = e.target.dataset.index;
        Array.from(userContentItems).forEach((item) => {
            item.classList.remove("active");
        });
        Array.from(userContentItems)[index].classList.add("active");
    });
});

// Open Feedback Detail Modal
// 
//Array.from(pipeItems).forEach((item) => {
//    item.addEventListener("click", (e) => {
//        feedbackDetailModal.classList.add("open");
//    });
//});

feedbackDetailModal.addEventListener("click", (e) => {
    if (e.target.classList.contains("feedbackDetail-modal"))
        feedbackDetailModal.classList.remove("open");
});

// Javascript of Detail Modal
const employeeName = document.querySelectorAll(
        ".detail-items.onGoing .employee-name-edit"
        );
const detailItems = document.querySelectorAll(".detail-items.onGoing");
Array.from(employeeName).forEach((item) => {
    item.addEventListener("dblclick", (e) => {
        if (e.target.closest(".detail-items").classList.contains('not_edit'))
            return;
        e.target.closest(".detail-items").classList.add("edit");
    });
});

Array.from(detailItems).forEach((item) => {
    item.addEventListener("click", (e) => {
        if (
                e.target.classList.contains("employee-name-edit") ||
                e.target.classList.contains("form-select")
                )
            return;
        if (
                e.target.closest(".detail-items").querySelector(".form-select").value ===
                ""
                )
            e.target.closest(".detail-items").classList.remove("edit");

    });
});

document.querySelector(".user-form .modal").addEventListener("click", (e) => {
    if (!e.target.classList.contains("modal"))
        return;
    e.target.closest(".user-form").classList.remove("open");
});


Array.from(document.querySelectorAll(".btn--done")).forEach(item => {
    item.addEventListener("click", () => {
        var done = JSON.parse(localStorage.getItem("Doneobj")) || {
            id: 0,
            flag: false
        };
        if (done.flag === false) {
            done.id++;
            done.flag = true;
            localStorage.setItem("Doneobj", JSON.stringify(done));
        }

    })
})

Array.from(document.querySelectorAll(".icon-block")).forEach(item => {
    item.addEventListener("click", () => {
        var emp = JSON.parse(localStorage.getItem("Empobj")) || {
            id: 0,
            emp_id: ""
        };
        if (emp.emp_id === "") {
            emp.id++;
            localStorage.setItem("Empobj", JSON.stringify(emp));
        }

    })
})



