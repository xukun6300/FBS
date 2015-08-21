
function validatePIN(a, b){
	if(a==""){
		alert("Please enter" + b + "password");
		return false;
	}
	
	a=trim(a);
	a=a.toUpperCase();
	//var b = 8;
	/*if (!checkLength(a, b, 24)) {
		$('#errorMsgApplication').html("Please enter a valid " + b + " password (minimum 8 to maximum 24 characters, with at least 1 character in upper case and 1 in numeric).");
		$('#modalApplicationError').modal('show');
		return false;
	}*/
	if(!checkFormat(a)){
		$('#errorMsgApplication').html("You have entered an incorrect " + b + " password. Please try again or contact our helpdesk for assistance.");
		$('#modalApplicationError').modal('show');
		return false;
	}
	
	return true;
}

function validateCPIN(a, b){
	a = trim(a);
	b = trim(b);
	if (a.length != b.length) {
		$('#errorMsgApplication').html("Your \'New Password\' and \'Confirm Password\' do not match. Please try again.");
		$('#modalApplicationError').modal('show');
		return false;
	}
	
	for (c = 0; c < a.length; c++) {
		var d = a.charAt(c);
		var e = b.charAt(c);
		if (d != e) {
			$('#errorMsgApplication').html("Your \'New Password\' and \'Confirm Password\' do not match. Please try again.");
			$('#modalApplicationError').modal('show');
			return false;
		}
	}
	
	return true;
}

function validatePIN2(a, b){
	b = trim(b);
	b = b.toUpperCase();
	a = trim(a);
	a = a.toUpperCase();
	
	if(a.length!=b.length){
		return true;
	}
	var d = false;
	
	for (var c = 0; c < a.length; c++) {
		var e = a.charAt(c);
		var f = b.charAt(c);
		if(e!=f){
			d=true;
			break;
		}
	}
	d||alert("Your \'New Password\' cannot be the same as your user ID. Please try again or contact our helpdesk for assistance.");
	return d;
	
}

function validatePINStren(a) {
	if (passwordStrength(a) < 3) {
		var message = "Your \'New Password\' must be at least within minimum 8 to<br/>";
		message += "maximum 24 characters, with at least 1 character in upper case and 1 in numeric.";
		$('#errorMsgApplication').html(message);
		$('#modalApplicationError').modal('show');
		return false;
	}
	return true;
}


function passwordStrength(a){
	var b = 0;
	a.length > 7 && b++;
	a.match(/[a-z]/) && a.match(/[A-Z]/) && b++;
	a.match(/\d+/) && b++;
	a.match(/.[!,\",#,$,%,&,\',(,),*,+,\,,\-,.,\/,:,;,<,=,>,?,@,\[,\\,\],^,_,`,{,|,},~]/) && b++;
	a.length > 12 && b++;
	return b;
}

function passwordStrengthDesc(a){
	var b=[];
	b[0]='<small>Very Weak</small>';
	b[1]='<small>Weak</small>';
	b[2]='<small>Better</small>';
	b[3]='<small>Medium</small>';
	b[4]='<small>Strong</small>';
	b[5]='<small>Strongest</small>';
	return b[a];
}

function checkPasswordStrength(a){
	a = passwordStrength(a);
	$('#passwordDescription').html(passwordStrengthDesc(a));
	$('#passwordStrength').addClass('strength'+a);
}

