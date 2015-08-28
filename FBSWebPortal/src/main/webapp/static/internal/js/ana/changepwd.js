var g_key="";

function deriveKey(a, b){
	a = new PBKDF2(a, b, 100, 16);
	//status callback 
	function d(e){
		
	}
	//result call back
	function c(e){
		g_key = hexToByteArray(e);
	}
	a.deriveKey(d, c);
}

function registerNewUser(a){
	var email = document.registerForm.email.value;
	var pwd = document.registerForm.password.value;
	var confirmPwd = document.registerForm.confirmPassword.value;
	
	if(a && !g_key){
		if(validatePIN(pwd, "")){  //validate pwd
			if(validatePIN(confirmPwd, "")){ //validate confirmPwd
				if(validateCPIN(pwd, confirmPwd)){ // validate pwd and confirmPwd matching
					if(validatePIN2(email, pwd)){ //validate pwd not same with email addr
						if(validatePINStren(pwd)){ //validate pwd strength
							//var salt = document.registerForm.salt.value;
							//salt = hexToByteArray(salt);		
							//derive PBKDF2 hash of new password
							//deriveKey(pwd, salt);
							setTimeout("registerNewUser(false)", 100);  //call it after 0.1 second 
						}
					}
				}
			}
		}
		
	}else{
	/*	var mod = document.registerForm.modulus.value;
		var exp = document.registerForm.exponent.value;
		
		var gkeyHex = binb2hex(str2binb(g_key));
		
		var rsa = new RSAKey();
		rsa.setPublic(mod, exp);
		
		var f = rsa.encrypt(gkeyHex);
		document.registerForm.password.value = f;
		document.registerForm.confirmPassword.value = f;*/
	//	alert(f);
		document.registerForm.submit();		
	}
	
/*	if(a){
		if(validatePIN(pwd, "")){  //validate pwd
			if(validatePIN(confirmPwd, "")){ //validate confirmPwd
				if(validateCPIN(pwd, confirmPwd)){ // validate pwd and confirmPwd matching
					if(validatePIN2(email, pwd)){ //validate pwd not same with email addr
						if(validatePINStren(pwd)){ //validate pwd strength

							setTimeout("registerNewUser(false)", 100);  //call it after 0.1 second 
						}
					}
				}
			}
		}
		
	}else{
		var mod = document.registerForm.modulus.value;
		var exp = document.registerForm.exponent.value;
		
		var gkeyHex = binb2hex(str2binb(g_key));
		
		var rsa = new RSAKey();
		rsa.setPublic(mod, exp);
		
		var f = rsa.encrypt(gkeyHex);
		document.registerForm.password.value = f;
		document.registerForm.confirmPassword.value = f;
	//	alert(f);
		document.registerForm.submit();		
	}*/
}

function formatAns(form, obj, answerField){
	var hashed = formatSecAns(obj.value);
	
	var mod = form.modulus.value;
	var exp = form.exponent.value;
	var rsa = new RSAKey();
	rsa.setPublic(mod, exp);
	
	document.getElementById(answerField).value = rsa.encrypt(hashed);
}

function formatSecAns(a){
	//regex is to remove all space
	return hex_sha1(a.toLowerCase().replace(/\s+/g,""));
}













