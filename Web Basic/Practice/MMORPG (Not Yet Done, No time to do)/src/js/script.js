import { User } from './class/User.js';

let registration_submit = document.getElementById("registration_submit");
let username = document.getElementById("username");
let email = document.getElementById("email");
let password = document.getElementById("password");
let confPassword = document.getElementById("confirm-password");

let error = document.createElement("p")


registration_submit.addEventListener("click", (e) => {
    e.preventDefault();
    let user = new User(username.value, email.value, checkPassword(password.value, confPassword.value));
    
    if(checkPassword(password.value, confPassword)) {
        user.setPassword(password.value)
    } else {
        
    }
    
    username.value = "";
    email.value = "";
});


function checkPassword(pw, confPw) {
    if(pw.length < 8) {
        if(pw != confPw) {
            error.style.color = "red";
            error.value = "Confirm password does not match the current password"
            return false;
        }
        return false;
    }
    else {
        return true;
    }
}