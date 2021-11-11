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

function handleImageName(element_id, input_name) {
    let stringImageName = "";
    if (document.getElementById(element_id).files.length !== 0) {
        const fileArray = Array.from(
                document.getElementById(element_id).files
                ).forEach((file) => {
            // upload file to firebase
            const fileName = file.name.split('.');
            const storageRef = ref(storage, "Images/" + file.lastModified + '.' + fileName[1]);
            let uploadTask = uploadBytesResumable(storageRef, file, metadata);
            stringImageName += file.lastModified + '.' + fileName[1] + ";";
        });
        var input = document.createElement("input");
        input.name = input_name;
        input.type = 'hidden';
        input.value = stringImageName.slice(0, stringImageName.length - 1);
        document.getElementById(element_id).closest('.list-items-wrapper').insertAdjacentElement("beforeend", input);
        console.log(stringImageName.slice(0, stringImageName.length - 1));
    }
}

const form = document.getElementById("root");
form.addEventListener("submit", (e) => {
    e.preventDefault();
    handleImageName('gallery-photo-add-1', 'image-1');
    handleImageName('gallery-photo-add-2', 'image-2');
    handleImageName('gallery-photo-add-3', 'image-3');
    handleImageName('gallery-photo-add-4', 'image-4');
});

export default function handleLoadImageForUserFromFirebase(imageString, element) {
    const imageList = imageString.split(';');
    // download image form fireabase
    for (let index = 0; index < imageList.length; index++) {
        getDownloadURL(ref(storage, 'Images/' + imageList[index]))
                .then((url) => {
                    var img = document.createElement("img");
                    img.src = url;
                    element.insertAdjacentElement("beforeend", img);
                })
                .catch((error) => {
                    console.log(error);
                });
    }
}
;





