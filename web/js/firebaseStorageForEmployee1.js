// Store image to storage firebase
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.2.0/firebase-app.js";
import { getStorage, ref, uploadBytesResumable, getDownloadURL, } from "https://www.gstatic.com/firebasejs/9.2.0/firebase-storage.js";

const firebaseConfig = {
    apiKey: "AIzaSyDgj70nlsLRc3pYoa_I2NiIV1I8U3A8Eq8",
    authDomain: "facilityfeedbackmanagement.firebaseapp.com",
    databaseURL: "https://facilityfeedbackmanagement-default-rtdb.asia-southeast1.firebasedatabase.app",
    projectId: "facilityfeedbackmanagement",
    storageBucket: "facilityfeedbackmanagement.appspot.com",
    messagingSenderId: "904686223788",
    appId: "1:904686223788:web:67f1780443348a91517a09",
    measurementId: "G-VRY6H0EFMV"
};
const app = initializeApp(firebaseConfig);

//Get Storage
const storage = getStorage(app);
const storageRef = ref(storage, "some-child");
const metadata = {
    contentType: "image/jpeg",
};

var uploadTask;
function handleImageName(element_id, input_name) {
    let stringImageName = "";
    if (document.getElementById(element_id).files.length !== 0) {
        const fileArray = Array.from(
                document.getElementById(element_id).files
                ).forEach((file) => {
            // upload file to firebase
            const fileName = file.name.split('.');
            const storageRef = ref(storage, "Images/" + file.lastModified + '.' + fileName[1]);
            uploadTask = uploadBytesResumable(storageRef, file, metadata);
            stringImageName += file.lastModified + '.' + fileName[1] + ";";
        });
    }
    var input = document.createElement("input");
    input.name = input_name;
    input.type = 'hidden';
    if (stringImageName) {
        input.value = stringImageName.slice(0, stringImageName.length - 1);
    } else {
        input.value = stringImageName;
    }
    document.getElementById(element_id).closest('.reponse-form-image-wrapper').insertAdjacentElement("beforeend", input);
}

const response_form = document.getElementById("response_form");
const loader = document.querySelector('.loader');
if (response_form) {
    response_form.addEventListener("submit", (e) => {
        e.preventDefault();
        handleImageName('image', 'image');
        if (uploadTask) {
            uploadTask.on('state_changed',
                    (snapshot) => {
                const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
                switch (snapshot.state) {
                    case 'paused':
                        console.log('Upload is paused');
                        break;
                    case 'running':
                        loader.classList.add('open');
                        break;
                }
                if (progress === 100) {
                    setTimeout(() => {
                        response_form.submit();
                    }, 700);
                }
            }, (error) => {
                console.log(error);
            });
        }
    });
}
