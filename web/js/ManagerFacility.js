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