function checkLength(a, b, d) {
	if (a.length >= b && a.length <= d) {
		return true;
	}
	return false;
}

function trim(a) {
	return a.replace(/^\s+|\s+$/g, "");
}

function ltrim(a) {
	return a.replace(/^\s+/, "");
}

function rtrim(a) {
	return a.replace(/\s+$/, "");
}

function checkFormat(a){
	for (var b = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~", d, c = 0; c < a.length; c++) {
		d = a.charAt(c);
		d = b.indexOf(d);
		if (d < 0) {
			return false;
		}
	}
	return true;
}

function showDiv(a, b) {
	if (document.getElementById) {
		a = document.getElementById(a);
		a.style.display = b ? "inline" : "none";
	}
}

function toggleSpinner(a, b){
	return showDiv(a, b);
}

function displayMessage(a){
	var splitString = a.split(":");
	var errorMsg = splitString[1]!=null?splitString[1]:"";
	
	if($("#statusbar")!=null){
		$("#statusbar").show();
		if(errorMsg!=''&&errorMsg.length>0){
			$("#statusbar").html(errorMsg);
		}else{
			$("#statusbar").html(a);
		}
	}
	
	return false;
}

function validateID(a, b){
	a = trim(a);
	if(a==""){
		alert(b+" is empty.");
		return false;
	}
	return true;
}










