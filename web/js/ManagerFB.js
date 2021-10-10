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