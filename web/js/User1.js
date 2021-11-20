document.querySelector('[name="search"]').addEventListener("focus", () => {
    document
            .querySelector('[name="search"]')
            .closest(".input-warp")
            .classList.add("active");
});

document.querySelector('[name="search"]').addEventListener("blur", () => {
    if (document.querySelector('[name="search"]').value !== "")
        return;
    document
            .querySelector('[name="search"]')
            .closest(".input-warp")
            .classList.remove("active");
});
window.addEventListener('load', () => {
    localStorage.setItem("UserCount", JSON.stringify(0));
    const flag = localStorage.getItem('flag');
    if (JSON.parse(flag) === true) {
        var feedback = JSON.parse(localStorage.getItem('feedbackDoneID'));
        var id = feedback[1].toString();
        var loop = document.getElementsByClassName("pipe-item-title");
//        var loopComment = document.getElementsByClassName("pipe-item-title-comment");

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
        localStorage.removeItem('flag');
    }
});

// Pop-up form feedback
const feedbackModal = document.querySelector(".feedback-form");
document.querySelector(".header-profile-link").addEventListener("click", () => {
    feedbackModal.classList.add("open");
});
feedbackModal.addEventListener("click", (e) => {
    if (e.target.classList.contains("feedback-form-main")) {
        feedbackModal.classList.remove("open");
    }
});

// Javascrip of form-feedback Pop-up
const tabList = document.querySelectorAll(".list-items-wrapper");
const tabListScrollbar = document.querySelector(".feedback-tab-scrollbar");
const plusbtn = document.querySelector(".plus-btn");
const inputFileBtn = document.querySelector(".input-file-select");
const inputImageWrapper = document.querySelectorAll(".img-feedback");

function handleChangeTabContent(e) {
    tabListScrollbar.style.width = e.target.offsetWidth + "px";
    tabListScrollbar.style.left = e.target.offsetLeft + "px";

    Array.from(tabList).forEach((item) => {
        item.classList.remove("active");
    });
    Array.from(tabList)[e.target.dataset.index].classList.add("active");
}

var tmpArray = [];
function handleRemoveTab(e) {
    if (
            Array.from(document.querySelectorAll(".tab-select-wrapper p")).length !== 1
            ) {
        /**
         * change tab for next element of e.target
         * first i have to have a current index of e.target
         * then i have a current value (Dom element) of next element in list
         * In these progress have 2 condition :
         * --1 remove not last element in this list
         *     + get this next Element add class value 'active' to be showed to the screen
         * --2 remove last element in this list
         *     + get this prev Element add class value 'active' to be showed to the screen
         */
        let currentIndex = Array.from(
                document.querySelectorAll(".tab-select-wrapper p")
                ).indexOf(e.target);
        let nextValue = Array.from(
                document.querySelectorAll(".tab-select-wrapper p")
                )[++currentIndex];
        if (nextValue)
            Array.from(document.querySelectorAll(".tab-select-wrapper p"))[
                    currentIndex
            ];
        else {
            nextValue = Array.from(
                    document.querySelectorAll(".tab-select-wrapper p")
                    )[--currentIndex];
            var prevValue = Array.from(
                    document.querySelectorAll(".tab-select-wrapper p")
                    )[--currentIndex];
        }

        Array.from(tabList).forEach((item) => {
            item.classList.remove("active");
        });
        Array.from(tabList)[nextValue.dataset.index].classList.add("active");

        if (prevValue) {
            tabListScrollbar.style.width = prevValue.offsetWidth + "px";
            tabListScrollbar.style.left = prevValue.offsetLeft + "px";

            Array.from(tabList).forEach((item) => {
                item.classList.remove("active");
            });
            Array.from(tabList)[prevValue.dataset.index].classList.add("active");
        }


        // Remove element
        e.target.remove();
        let currentTab = Array.from(tabList)[e.target.dataset.index];
        // Reset input, select to default value
        currentTab.querySelector(".img-feedback").innerHTML = "";
//        currentTab.querySelector(".input-file-name").value = "";
        Array.from(currentTab.querySelectorAll("[name]")).forEach((element) => {
            element.value = "";
        });

        // Push to check the current index in tab list for doesn't loop the position of tab list
        Array.from(document.querySelectorAll(".tab-select-wrapper p")).forEach(
                (item) => {
            if (!tmpArray.includes(item.dataset.index))
                tmpArray.push(item.dataset.index);
        }
        );
    }

    // render tab name
    let count = 0;
    Array.from(document.querySelectorAll(".tab-select-wrapper p")).forEach(
            (item) => {
        item.innerText = `Device ${++count}`;
    }
    );

    if (Array.from(document.querySelectorAll(".tab-select-wrapper p")).length === 1) {
        tabListScrollbar.style.width = 0;
        tabListScrollbar.style.left = 0;
    }

    let indexTmp = Array.from(document.querySelectorAll(".tab-select-wrapper p")).length;
    document.querySelector('.input-check').innerHTML = `<input type="hidden" name="check" value="${indexTmp}"/>`;
}

// clear value(image) user input if they click the icon again
function handleShowImage(e) {
    Array.from(inputImageWrapper)[e.target.dataset.index - 1].innerHTML = "";
    e.target.closest('.reponse-form-drag-area').classList.remove("active");
    e.target.closest('.reponse-form-drag-area').querySelector('header').textContent = "Drag & Drop to Upload File";
}

/**
 * Purpose: Add new tab for form-feedback
 * first: listen a button 'add' event
 * check the length of this list (because only have 4 list in one of feedback). If list's lenght === 4, programm will be return
 * second condition : list's lenght < tmpArray's Lenght(--tmpArray is a list contains the data-index of tab element for dont's put value not in [0,1,2,3])
 * when user click add: they are 2 case:
 * --1 they add before dont click remove Element
 * --2 thay add after click remove Element
 */
var tmp = 1;
plusbtn.addEventListener("click", () => {
    let check = Array.from(
            document.querySelectorAll(".tab-select-wrapper p")
            ).length;
    if (check === 4)
        return;
    if (check < tmpArray.length) {
        tmpArray = [];
        Array.from(document.querySelectorAll(".tab-select-wrapper p")).forEach(
                (item) => {
            tmpArray.push(item.dataset.index);
        }
        );
    }
    if (tmpArray.length !== 0) {
        do {
            tmp = Math.floor(Math.random() * 4).toString();
        } while (tmpArray.includes(tmp));
        tmpArray.push(tmp);

        document.querySelector(
                ".tab-select-wrapper"
                ).innerHTML += `<p data-index="${tmp}" onclick="handleChangeTabContent(event)" ondblclick="handleRemoveTab(event)">Device</p>`;
    } else {
        document.querySelector(
                ".tab-select-wrapper"
                ).innerHTML += `<p data-index="${tmp++}" onclick="handleChangeTabContent(event)" ondblclick="handleRemoveTab(event)">Device</p>`;
    }

    /**
     * automatic change scrollbar to next tab element and show the same content tab
     */
    let tabSelects = Array.from(
            document.querySelectorAll(".tab-select-wrapper p")
            );
    tabListScrollbar.classList.add("active");
    tabListScrollbar.style.width = "98" + "px";
    tabListScrollbar.style.left =
            tabSelects[tabSelects.length - 1].offsetLeft + "px";
    Array.from(tabList).forEach((item) => {
        item.classList.remove("active");
    });
    Array.from(tabList)[
            tabSelects[tabSelects.length - 1].dataset.index
    ].classList.add("active");

    // Render tab name
    let count = 0;
    Array.from(document.querySelectorAll(".tab-select-wrapper p")).forEach(
            (item) => {
        item.innerText = `Device ${++count}`;
    }
    );

    let indexTmp = Array.from(document.querySelectorAll(".tab-select-wrapper p")).length;
    document.querySelector('.input-check').innerHTML = `<input type="hidden" name="check" value="${indexTmp}"/>`;
});


// Pop-up message of feedback return form back-end controller
const linkBtn = document.querySelectorAll(".link");
const formMessage = document.querySelector(".feedback-form-message");
Array.from(linkBtn).forEach((item) => {
    item.addEventListener("click", (e) => {
        e.target.closest(".feedback-form-message").classList.remove("open");
    });
});
formMessage.addEventListener("click", (e) => {
    if (e.target.classList.contains("feedback-form-message-modal")) {
        formMessage.classList.remove("open");
        feedbackModal.classList.remove("open");
    }
});

// History feedback list Javascript
const categoryList = document.querySelectorAll(".content-item-category a");
const categoryItems = document.querySelectorAll(
        ".content-item-main-category-item"
        );
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

        // add class tuong ung cho back-end
        if (e.target.closest("a").dataset.index === '0') {
            document.querySelector(".input-style-flag").innerHTML = `<input type="hidden" name="style_pipe" value="active"/>`
        } else {
            document.querySelector(".input-style-flag").innerHTML = `<input type="hidden" name="style_list" value="active"/>`
        }
        let tmp = document.querySelector(".dropdown-list a:first-child").getAttribute("href");
        let mainHref = tmp.split("&");
        if (category.dataset.index === '0') {
            document.querySelector("header .dropdown-list a:first-child").removeAttribute("href");
            document.querySelector("header .dropdown-list a:first-child").setAttribute("href", mainHref[0] + "&style_pipe=active&user=user");
        } else {
            document.querySelector("header .dropdown-list a:first-child").removeAttribute("href");
            document.querySelector("header .dropdown-list a:first-child").setAttribute("href", mainHref[0] + "&style_list=active&user=user");
        }
    });
});

const helpBtn = document.querySelector(".feedback-help");
const containerMain = document.querySelector(".container-main");
helpBtn.addEventListener("mouseover", () => {
    setTimeout(() => {
        helpBtn
                .closest(".list-items-help")
                .querySelector(".help-message--hover")
                .classList.add("show");
    }, 500);
});

helpBtn.addEventListener("mouseout", () => {
    helpBtn
            .closest(".list-items-help")
            .querySelector(".help-message--hover")
            .classList.remove("show");
});

helpBtn.addEventListener("click", () => {
    helpBtn
            .closest(".list-items-help")
            .querySelector(".help-message")
            .classList.toggle("show");
    helpBtn
            .closest(".list-items-help")
            .querySelector(".help-message--hover")
            .classList.remove("show");
    helpBtn.closest(".list-items-help").classList.toggle("background-change");
});

containerMain.addEventListener("click", (e) => {
    if (!e.target.classList.contains("feedback-help")) {
        helpBtn
                .closest(".list-items-help")
                .querySelector(".help-message--hover")
                .classList.remove("show");
        helpBtn
                .closest(".list-items-help")
                .querySelector(".help-message")
                .classList.remove("show");
        helpBtn.closest(".list-items-help").classList.remove("background-change");
    }
});

document.querySelector(".user-form .modal").addEventListener("click", (e) => {
    if (!e.target.classList.contains("modal"))
        return;
    e.target.closest(".user-form").classList.remove("open");
});


// list_style Javascript
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
function handleReloadUserPage(e) {
    var h3 = e.target.closest(".notification-item").querySelector(".pipe-item-title").innerHTML;
    var feedbackid = h3.split(" ");
    localStorage.setItem("feedbackDoneID", JSON.stringify(feedbackid));
    localStorage.setItem('flag', true);
    localStorage.setItem("UserCount", JSON.stringify(0));

    window.location.replace("/SWP391_PROJECT/ShowFacilityStudentController");
}

// Drag or upload files
const dropArea = document.querySelectorAll(".reponse-form-drag-area");
Array.from(dropArea).forEach(ele => {
    let dragText = ele.querySelector("header");
    ele.addEventListener("dragover", (event) => {
        event.preventDefault();
        ele.classList.add("active");
        dragText.textContent = "Release to Upload File";
    });

    ele.addEventListener("dragleave", () => {
        ele.classList.remove("active");
        dragText.textContent = "Drag & Drop to Upload File";
    });
});

