inicializeCookies();

//Function for save cookies
function saveCookies(){
    if (document.URL.includes("rooms")){
        setCookie("MinPrice",document.getElementById("pricefrom").value,90);
        setCookie("MaxPrice",document.getElementById("priceto").value,90);
        setCookie("Type",document.getElementById("roomtype").value,90);
    }
        setCookie("Checkin",document.getElementById("checkin").value,90);
        setCookie("Checkout",document.getElementById("checkout").value,90);
        setCookie("Guests",document.getElementById("guests").value,90);
}

//Function for read saved cookies
function getCookiesHome() {
    document.getElementById("checkin").value = getCookie("Checkin");
    document.getElementById("checkout").value = getCookie("Checkout");
    document.getElementById("guests").value = getCookie("Guests");
}

function getCookiesRooms() {
    document.getElementById("checkin").value = getCookie("Checkin");
    document.getElementById("checkout").value = getCookie("Checkout");
    document.getElementById("guests").value = getCookie("Guests");
    document.getElementById("pricefrom").value = getCookie("MinPrice");
    document.getElementById("priceto").value = getCookie("MaxPrice");
    document.getElementById("roomtype").value= getCookie("Type");
}

//Function for get every cookies' values
function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function inicializeCookies(){
    if(!getCookie("MinPrice")){
        setCookie("MinPrice",1,90);
        setCookie("MaxPrice",1000,90);
        setCookie("Type",0,90);
    }

    if (document.URL.includes("home")){
        getCookiesHome();
    }else if (document.URL.includes("rooms")) {
        getCookiesRooms();
    }
}

function controlSelectOptions(){
    var maxSelector = document.getElementById("priceto");
    var minSelector = document.getElementById("pricefrom");
    var maxPrice =  document.getElementById("priceto").value;
    var minPrice = document.getElementById("pricefrom").value;
    for (let i = 0; i < 5; i++) {
        if (parseInt(maxSelector.options[i].value) < parseInt(minPrice)) {
            maxSelector.options[i].hidden = true;
        } else {
            maxSelector.options[i].hidden = false;
        }
        if (parseInt(minSelector.options[i].value) > parseInt(maxPrice)) {
            minSelector.options[i].hidden = true;
        } else {
            minSelector.options[i].hidden = false;
        }
    }
}