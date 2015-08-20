$(document).ready(function(){
	$("#btnCheckEmailId").click(emailCheck);
	$('#dob').dobDatePicker('#dob_btn');
	
});

function emailCheck(){
	$("#email\\.errors").html('');
	
	if($("#email").val().length==0){
		$("#message").html("<div class=blue>Please enter a valid Email Address</div>");
		return false;
	}else{
		$.ajax({
			type:'GET',
			url:'../useraccountmanagementajax/validateEmailId.action',
			data:{
				email:$("#email").val()
			},
			dataType:'text',
			success:function(response){
				$('#message').css('color','blue');
				$('#message').addClass('blue');
				$('#message').html(response);
				return true;
			}
		})
	}
}


function submitForm(){
	if(document.registerForm.password.value!=""&&document.registerForm.confirmPassword.value!=""){
		registerNewUser(true);
	}else{
		document.registerForm.submit();
	}
}

function selectSalutation(){
	var selected = $("#salutationTypeTId option:selected").text();
	var first = $("input[name=genderTypeTDesc] + label").first().text();
	
	if(selected.indexOf("Mrs")!=-1||selected.indexOf("Ms")!=-1||selected.indexOf("Mdm")!=-1||selected.indexOf("Miss")!=-1){
		if(first.indexOf("F")==0){
			$("input:radio[name=genderTypeTDesc]")[0].checked = true;
		}else{
			$("input:radio[name=genderTypeTDesc]")[1].checked = true;
		}
	}else if(selected.indexOf("Mr")!=-1){
		if(first.indexOf("M")==0){
			$("input:radio[name=genderTypeTDesc]")[0].checked = true;
		}else{
			$("input:radio[name=genderTypeTDesc]")[1].checked = true;
		}
	}else if(selected.indexOf("Please Select")!=-1||selected.indexOf("Others")!=-1||selected.indexOf("Dr")!=-1){
		$("input:radio[name=genderTypeTDesc]")[0].checked = false;
		$("input:radio[name=genderTypeTDesc]")[1].checked = false;
	}
}

function chooseGender(){
	var checked = $("input[name=genderTypeTDesc]:checked + label").text();  // + label means the <label> element that are next to each checked checkbox
	var selected = $("#salutationTypeTId option:selected").text();
	
	if(checked.indexOf("Male")!=-1){
		if(selected.indexOf("Dr")!=0){
			$("[name=salutationTypeTId] option").filter(function(){
				return $(this).text().indexOf("Mr")!=-1 && $(this).text().indexOf("Mrs")==-1;      // to select Mr
			}).prop("selected", true);
		}
	}else if(checked.indexOf("Female")!=-1){
		if(selected.indexOf("Dr")!=0){
			$("[name=salutationTypeTId] option").filter(function(){
				return $(this).text().indexOf("Ms")!=-1;      // to select Ms
			}).prop("selected",true);
		}
	}
}

