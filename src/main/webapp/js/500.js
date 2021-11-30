// Declare the Elements
const dispNum = document.querySelector(".display .num");
const dispErr = document.querySelector(".container .stg");


window.onload = function () {

    function showNum() {
        const randomNum = Math.floor(Math.random() * 1000);
        const randomStr = randomNum.toString()
        dispNum.textContent = randomStr
    }

    var interval = setInterval(showNum, 100);

    setTimeout(() => {
        clearInterval(interval);
        dispNum.textContent = "500";
        dispErr.style.display = "block";
        dispErr.textContent = "呀!服务器发生了错误!"
    }, 500);
}