var query = document.querySelector.bind(document);
$$ = document.querySelectorAll.bind(document);

const inputElements = $$(".input-wrap input");
const selectElements = $$(".input-wrap select[name='reason']");
const textAreaElement = query(".input-text textarea");
const reportBtn = query("input[type='submit']");
const root = query(".root");

textAreaElement.addEventListener("focus", () => {
    console.log(query('.message-block'));
    textAreaElement.parentElement.classList.add("active");
});

textAreaElement.addEventListener("blur", () => {
    if (textAreaElement.value !== "")
        return;
    textAreaElement.parentElement.classList.remove("active");
});

let count = 0;
function handleAddEvent() {
    if (count > 2) {
        alert("Cannot add larger than 4 form input !");
        return;
    } else {
        Array.from($$(".plus-toggle")).forEach((toggle) =>
            toggle.classList.add("remove")
        );
        Array.from($$(".root input")).forEach((inputElement) =>
            inputElement.parentElement.classList.remove("active")
        );
        root.innerHTML += `<div class="actual-form">
      <div class="input-wrap">
        <input type="text" name="tmpLocation" id="location" onfocus="handelDOMEvent(event)" required/>
        <label>Room 302 ...</label>
      </div>
      <div class="input-wrap">
        <select name="tmpDevice" id="device" required>
          <option value="" selected disabled hidden>
            Damaged Device
          </option>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
        </select>
      </div>
      <div class="input-wrap-special">
        <input
          type="number"
          name="tmpQuantity"
          id="quantity"
          placeholder="1"
          required
        />
      </div>
      <div class="input-wrap">
        <select name="tmpReason" id="reason" required>
          <option value="" selected disabled hidden>Reason</option>
          <option value="broken">Broken</option>
          <option value="disappeared">Disappeared</option>
          <option value="others">Others</option>
        </select>
      </div>
      <div class="plus-toggle">
        <img
          src="https://cdn.discordapp.com/attachments/770804043794350100/888843266794086491/add-button.png"
          alt="plusImg"
          class="plus-btn"
          onclick="handleAddEvent()"
        />
        <i class="fas fa-times" onclick="handleMinusEvent(event)"></i>
      </div>
    </div>`;
        count++;
    }
}

function handleMinusEvent(e) {
    e.target.offsetParent.remove();
    count--;
}

function handelDOMEvent(e) {
    e.target.parentElement.classList.add("active");

    e.target.addEventListener("blur", () => {
        if (e.target.value !== "")
            return;
        e.target.parentElement.classList.remove("active");
    });
}

reportBtn.addEventListener("click", (e) => {
    e.preventDefault();
    const locations = $$('[name="tmpLocation"]');
    const devices = $$('[name="tmpDevice"]');
    const reasons = $$('[name="tmpReason"]');
    const quantities = $$('[name="tmpQuantity"]');
    const locationValue = Array.from(locations)
            .reduce((acc, cur) => {
                let tmp = cur.value ? cur.value : "unidentified";
                acc += "," + tmp;
                return acc;
            }, "")
            .slice(1);

    const deviceValue = Array.from(devices)
            .reduce((acc, cur) => {
                let tmp = cur.value ? cur.value : "unidentified";
                acc += "," + tmp;
                return acc;
            }, "")
            .slice(1);

    const reasonValue = Array.from(reasons)
            .reduce((acc, cur) => {
                let tmp = cur.value ? cur.value : "unidentified";
                acc += "," + tmp;
                return acc;
            }, "")
            .slice(1);

    const quantityValue = Array.from(quantities)
            .reduce((acc, cur) => {
                let tmp = cur.value ? cur.value : "unidentified";
                acc += "," + tmp;
                return acc;
            }, "")
            .slice(1);



    query(".root").innerHTML += `
    <input type="hidden" name="location" value = "${locationValue}"/>
    <input type="hidden" name="device" value = "${deviceValue}" />
    <input type="hidden" name="reason" value = "${reasonValue}"/>

    <input type="hidden" name="quantity" value = "${quantityValue}"/>

    <input type="hidden" value="Send Report" name="action" />
  `;
    query("form").submit();
});

if (query('[name="close-outline"]')) {
    query('[name="close-outline"]').addEventListener("click", () => {
        query(".message-block-wrapper").classList.add('hide');
    });
}
