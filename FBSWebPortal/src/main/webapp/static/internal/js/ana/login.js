var g_key = "";
var n = "";
function deriveKey(a, b) {
	a = new PBKDF2(a, b, 100, 16);
	function d(e) {
	}
	function c(e) {
		g_key = hexToByteArray(e);
	}
	a.deriveKey(d, c);
}
function fidLogin(a){
	if(a==true){
		g_key="";
	}
	
	var b = document.login.userId.value;
	var d = document.login.password.value;
	var c = "";
	
	$("#statusbar").html("");
	$("#statusbar").hide();
	

	if (b == "") {
		
		c = "Please enter User ID.";
		displayMessage(c);
		return;
	} else if (d == "") {
		
		c = "Please enter password.";
		displayMessage(c);
		return;
	} else {
		toggleSpinner("spinner", 1);
	}
	

	if (a && !g_key) {
		if(validateID(b, "User Id")){
			if(validatePIN(d)){
				
				c = sendRequest("u="+b);
				alert(c);
				if(c.indexOf("Error:")!=-1){
					displayMessage(c);
					toggleSpinner("spinner",0);
					return false;
				}else if(c.indexOf("http")!=-1){
					window.location = c;
				}else{
				
					a = c.substring(0, 32);
					a = hexToByteArray(a);
					b = c.substring(32, 64);
					n = hexToByteArray(b);
					deriveKey(d, a);
					setTimeout("fidLogin(false)", 200);
				}
		
			}else{
				toggleSpinner("spinner", 0);
			}
		}else{
			toggleSpinner("spinner", 0);
		}
	
	}else if(!a && !g_key){
		setTimeout("fidLogin(false)", 200);
	}else{
		document.login.password.value="";
		d = hex_hmac_sha1(g_key, n);
		
		var mod = document.login.mod.value;
		var exp = document.login.exp.value;
		
		var rsa = new RSAKey();
		rsa.setPublic(mod, exp);
		
		var f = rsa.encrypt(d);
		
		if(!a && g_key){
			document.submitForm.u.value = b;
			document.submitForm.f.value = f;
			document.submitForm.pv.value = "no";
			
            c = sendRequest("u=" + b + "&f=" + f);
            
            document.submitForm.pv.value = "yes";
            
            if(c.indexOf("Error:")!=-1){
            	displayMessage(c);
            	toggleSpinner("spinner",0);
            	return false;
            }
            
            document.submitForm.submit();
            toggleSpinner("spinner",0);
            
		}
		
		
	}
	
	
}


function validatePIN(a){
	if(a==""){
		displayMessage("Please enter password.");
		return false;
	}
	
	a=trim(a);
	a=a.toUpperCase();

	if (!checkLength(a, 8, 24)) {
		displayMessage("Please enter a valid user ID and/or password.");
		return false;
	}
	
	if(!checkFormat(a)){
		displayMessage("Please enter password.");
		return false;
	}
	
	return true;
}












