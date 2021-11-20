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

export default async function handleLoadImageForUserFromFirebase(imageString, element) {
    const imageList = imageString.split(';');
    // download image form fireabase
    for (let index = 0; index < imageList.length; index++) {
        await getDownloadURL(ref(storage, 'Images/' + imageList[index]))
                .then((url) => {
                    var img = document.createElement("img");
                    img.src = url;
                    element.insertAdjacentElement("beforeend", img);
                })
                .catch((error) => {
                    switch (error.code) {
                        case 'storage/object-not-found':
                            console.log("File doesn't exist");
                            break;
                        case 'storage/canceled':
                            console.log("User canceled the upload");
                            break;
                        case 'storage/unknown':
                            console.log("Unknown error occurred, inspect the server response");
                            break;
                    }
                });
    }
}
;

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
    document.getElementById(element_id).closest('.list-items-wrapper').insertAdjacentElement("beforeend", input);
}

const form = document.getElementById("root");
const loader = document.querySelector('.loader');
if (form) {
    form.addEventListener("submit", (e) => {
        e.preventDefault();
        let amount = document.querySelectorAll('.tab-select-wrapper p').length;
        switch (amount) {
            case 1:
                handleImageName('gallery-photo-add-1', 'image-1');
                break;
            case 2:
                handleImageName('gallery-photo-add-1', 'image-1');
                handleImageName('gallery-photo-add-2', 'image-2');
                break;
            case 3:
                handleImageName('gallery-photo-add-1', 'image-1');
                handleImageName('gallery-photo-add-2', 'image-2');
                handleImageName('gallery-photo-add-3', 'image-3');
                break;
            default :

                break;
        }
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
                        form.submit();
                    }, 700);
                }
            }, (error) => {
                console.log(error);
            });
        } else {
            form.submit();
        }
    });
}






