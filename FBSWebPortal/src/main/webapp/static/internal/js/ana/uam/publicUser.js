$(document).ready(function(){
	
});


function submitForm(){
	if(document.registerForm.password.value!=""&&document.registerForm.confirmPassword.value!=""){
		registerNewUser(true);
	}else{
		document.registerForm.submit();
	}
}