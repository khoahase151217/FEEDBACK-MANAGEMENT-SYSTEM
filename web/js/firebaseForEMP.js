Array.from(document.querySelectorAll(".assign-form")).forEach(item => {
    item.addEventListener("submit", (e) => {
        e.preventDefault();
        var empID = e.target.querySelector('option:checked').value;
        var emp = JSON.parse(localStorage.getItem("Empobj")) || {
            id: 0,
            emp_id: ""
        };
        emp.emp_id = empID;
        localStorage.setItem("Empobj", JSON.stringify(emp));

        e.target.submit();

    });
});















