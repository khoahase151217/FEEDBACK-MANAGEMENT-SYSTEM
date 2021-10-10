const tmp = document.querySelectorAll('.actual-form-special');

Array.from(tmp).forEach((ele) => {
    ele.addEventListener('dblclick', () => {
        ele.parentElement.nextElementSibling.classList.remove('hide');
        ele.closest('.hero-actual-form').classList.remove('hide-btn');
        ele.classList.add('edit');
    })
    
    ele.addEventListener('click', () => {
        ele.parentElement.nextElementSibling.classList.add('hide');
        ele.closest('.hero-actual-form').classList.add('hide-btn');
        ele.classList.remove('edit');
    })
})

const selects = document.querySelectorAll('.actual-form-select');

Array.from(selects).forEach((select) => {
    select.addEventListener('blur', () => {
        if(select.value === "") {
            select.closest('.hero-actual-form').classList.add('hide-btn');
            select.classList.add('hide');
            select.previousElementSibling.querySelector('.actual-form-special.edit').classList.remove('edit');
        }
            
    })
})

Array.from(document.querySelectorAll('.actual-form-special')).forEach((element) => {
    element.closest('.hero-actual-form').classList.add('hide-btn');
})


