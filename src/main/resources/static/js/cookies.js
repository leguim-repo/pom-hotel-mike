let selectorGuest = document.getElementById("guests");
let selectPriceFrom =  document.getElementById("pricefrom");
let selectPriceTo =  document.getElementById("priceto");
let selectType =  document.getElementById("roomtype");

//on load getCookies !!!
getCookies();


//Function for save session cookies
function saveCookies(){
    var d = new Date();
    d.setTime(d.getTime() + (31 * 24 * 60* 60* 1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = "Checkin=" + document.getElementById("checkin").value + expires;
    document.cookie = "Checkout=" + document.getElementById("checkout").value + expires;
    document.cookie = "Guests=" + selectorGuest.options[selectorGuest.selectedIndex].text + expires;
    document.cookie = "MinPrice=" + selectPriceFrom.options[selectPriceFrom.selectedIndex].text + expires;
    document.cookie = "MaxPrice=" + selectPriceTo.options[selectPriceTo.selectedIndex].text + expires;
    document.cookie = "Type=" + selectType.options[selectType.selectedIndex].text + expires;
}

//Function for read saved cookies
function getCookies() {
    document.getElementById("checkin").value = getCookie("Checkin");
    document.getElementById("checkout").value = getCookie("Checkout");
    selectorGuest.options[selectorGuest.selectedIndex].text = getCookie("Guests");
    selectPriceFrom.options[selectPriceFrom.selectedIndex].text = getCookie("MinPrice");
    selectPriceTo.options[selectPriceTo.selectedIndex].text = getCookie("MaxPrice");
    selectType.options[selectType.selectedIndex].text = getCookie("Type");
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