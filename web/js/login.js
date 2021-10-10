const inputs = document.querySelectorAll(".input-wrap input");
const testBtn = document.querySelector(".actual-form-footer a");
const main = document.querySelector("main");
const bullets = document.querySelectorAll(".bullets span");
const images = document.querySelectorAll(".image");

let index = 1;
setInterval(() => {
    if (index === 4) {
        index = 1;
    }
    const currentImage = document.querySelector(`.img-${index}`);
    images.forEach((img) => img.classList.remove("show"));
    currentImage.classList.add("show");

    document.querySelector(".text-group").style.transform = `translateY(-${
            (index - 1) * 2.2
            }rem)`;

    bullets.forEach((bullet) => bullet.classList.remove("active"));
    Array.from(bullets)[index - 1].classList.add("active");
    index++;
}, 2000);

inputs.forEach((inp) => {
    inp.addEventListener("blur", () => {
        if (inp.value != "")
            return;
        inp.parentElement.classList.toggle("active");
    });
    const invalid = document.querySelector(".message-wrap.invalid");
    inp.addEventListener("focus", () => {
        if (!inp.parentElement.classList.contains("readonly")) {
            if (invalid) {
                invalid.classList.remove("invalid");
            }
            inputs.forEach((inp) => inp.parentElement.classList.remove("invalid"));
            inp.parentElement.classList.add("active");
        }
    });

    inp.addEventListener("input", () => {
        invalid.classList.remove("invalid");
    });
});

function moveSlider() {
    let index = this.dataset.index;

    const currentImage = document.querySelector(`.img-${index}`);
    images.forEach((img) => img.classList.remove("show"));
    currentImage.classList.add("show");

    document.querySelector(".text-group").style.transform = `translateY(-${
            (index - 1) * 2.2
            }rem)`;

    bullets.forEach((bullet) => bullet.classList.remove("active"));
    this.classList.add("active");
}

bullets.forEach((bullet) => bullet.addEventListener("click", moveSlider));
