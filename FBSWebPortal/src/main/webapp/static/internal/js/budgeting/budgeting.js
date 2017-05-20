$(document).ready(function(){
	//var jsBaseURL = '${pageContext.request.contextPath}';
	
	$("button[id^='addNewRow_']").click(function() {
		 var acctCode = this.id.replace('addNewRow_','');
         
		 //get current account table column number (may have additional Action column)
		 var currTbColumns = $("#acctTb_"+acctCode).find("tr:first th").length;
		 //actual account table column number
		 var acctTbColumns = $("#acctTb_"+acctCode).attr("column-size");
		 
		 var acctTbIndex = $("#acctTb_"+acctCode).attr("index");
		 
		 var newRow = "<tr>";
		 $("#acctTb_"+acctCode).find("tr:first th").each(function(index, value){
			 if($(this).attr("column-type")!=undefined){				 
				 var inputWidth = $(this).attr("width")-40;
				 
				 var rowNum = $("#acctTb_"+acctCode+" tbody tr").length;
				 
				 var cellId = "accounts["+acctTbIndex+"].lineItems["+rowNum+"].column"+(index+1);
				 
				 var inputId = "input_"+acctCode+"_"+rowNum+"_"+index;
				 var labelId = "label_"+acctCode+"_"+rowNum+"_"+index;
				 var columnType = $(this).attr("column-type");
				 if(columnType==='D'){
					 newRow += "<td><label id=\""+labelId+"\"></label><input type=\"text\" id=\""+inputId+"\" cell-type=\""+columnType+"\" class=\"date-field\" style=\"width:"+ inputWidth +"px\"/></td>";				
				 }else{
					 newRow += "<td><label id=\""+labelId+"\"></label><input type=\"text\" id=\""+inputId+"\" cell-type=\""+columnType+"\" class=\"input\" style=\"width:"+ inputWidth +"px\"/></td>"; 
				 }
				 				 
			 }else if($(this).attr("column-name")==='action'){
				 //non user input field				 
				 newRow += "<td><button type=\"button\" class=\"btn-icon btn-success save-lineitem\" title=\"Save\"><i class=\"icon-check icon-white\"></i></button>" +
				 		"<button style=\"display:none;\" type=\"button\" class=\"btn-icon btn-inverse edit-lineitem\" title=\"Update\"> <i class=\"icon-edit icon-white\"></i></button>" +
				 		"&nbsp;<button style=\"display:none;\" type=\"button\" class=\"btn-icon btn-danger delete-lineitem\" title=\"Delete\"><i class=\"icon-remove icon-white\"></i></button></td>";
			 }else{
				 newRow += "<td></td>"; 
			 }
			 
		 });
		 newRow +="</tr>";
		
		 $("#acctTb_"+acctCode+" tbody").append(newRow);
		 $(".date-field").myDatePicker();
	});
	
	//use event delegation: 
	//rows are added to DOM after DOM ready so event will not fire on newly added rows
	$(".acct-table").on('click','.save-lineitem', saveLineItem);	
	$(".acct-table").on('click','.edit-lineitem', editLineItem);
	$(".acct-table").on('click','.delete-lineitem', deleteLineItem);

	//validate numeric input field
	$("body").delegate("input[cell-type='N']", "keydown", function(e) {
		$(this).keydown(numericOnly(e));
	});
	
	$("#deleteLineitemDialogCloseBtn").click(function(){
		$('#deleteLineitemDialog').modal('hide');
	});

	$("#deleteLineitemDialogDeleteBtn").click(function(){

		var lineitemId = $('#deleteLineitemDialog').attr('lineitem-id');
		var acctCode = $('#deleteLineitemDialog').attr('acctCode');
		$.ajax({
			type : 'POST',
			url : jsBaseURL + '/budgetingAjax/deleteLineItem.action',
			dataType : 'json',
			data : {"lineitemId":lineitemId},
			cache : false
		}).done(function(respData, textStatus, XMLHttpRequest) {
			try {
				if (isMissing(respData.data)) {
					alert('Unable to Delete Lineitem');
					return;
				}
				if(respData.data){								
					$("#alert_delete_success_"+acctCode).show();
					$("#alert_save_success_"+acctCode).hide();
					$('#deleteLineitemDialog').modal('hide');
					$("tr[lineitem-id='"+lineitemId+"']").hide();
				}else{
					alert('Unable to Delete Lineitem');
				}

			} catch (err) {
				alert('Unable to Delete Lineitem');
			}
		}).fail(function(jqXHR, textStatus) {
			alert('Unable to Save Lineitem Due to Communication Error Please Try Again '+textStatus);
		});
	});
});

function deleteLineItem(){
	var lineitemId = $(this).closest("tr").attr("lineitem-id");
	var acctCode = $(this).closest("table").attr("id").replace('acctTb_','');	
	$('#deleteLineitemDialog').attr('lineitem-id',lineitemId);	
	$('#deleteLineitemDialog').attr('acctCode',acctCode);
	$('#deleteLineitemDialog').modal({
        keyboard: false ,
    	backdrop: true,	    	
    }) ;
}


function editLineItem(){
	var tds = $(this).closest("tr").find("td");
	var ths = $(this).closest("table").find("th");
	var tb = $(this).closest("table");	
	var saveBtn = $(this).closest("td").find(".save-lineitem");
	var editBtn = $(this).closest("td").find(".edit-lineitem");
	var deleteBtn = $(this).closest("td").find(".delete-lineitem");

	var acctCode = tb.attr("id").replace('acctTb_','');	
	
	tds.each(function(index, value){
		var columnType = ths.eq(index).attr("column-type");
		if(columnType!==undefined){
			var inputField = tds.eq(index).find("input");
			var inputId = inputField.attr("id");
			if(inputId===undefined){
				var inputWidth = ths.eq(index).attr("width")-40;
				 
				 //var rowNum = $("#acctTb_"+acctCode+" tbody tr").length;
				 var rowNum = $(this).parent().index();
				 var inputIdNew = "input_"+acctCode+"_"+rowNum+"_"+index;
				 var labelIdNew = "label_"+acctCode+"_"+rowNum+"_"+index;
				 $(this).find("label").attr("id",labelIdNew);
				 $(this).find("input").attr("id",inputIdNew);	
				 $(this).find("input").attr("cell-type",columnType);	
				 $(this).find("input").attr("style","width:"+inputWidth+"px");
				 $(this).find("input").val($(this).find("label").text());
				 if(columnType==='D'){					 
					 $(this).find("input").attr("class","date-field");
				 }else{
					 $(this).find("input").attr("class","input"); 
				 }
				 inputId = inputField.attr("id");
			}
			
			var labelId = inputId.replace("input", "label");
			$("#"+labelId).hide();
			inputField.show();
			$("#alert_save_success_"+acctCode).hide();
			$("#alert_delete_success_"+acctCode).hide();
			saveBtn.show();
			editBtn.hide();
			deleteBtn.hide();
		}
	});
	$(".date-field").myDatePicker();
}

function saveLineItem(){
	var tds = $(this).closest("tr").find("td");
	var ths = $(this).closest("table").find("th");
	var tb = $(this).closest("table");	
	var tr = $(this).closest("tr");
	var saveBtn = $(this).closest("td").find(".save-lineitem");
	var editBtn = $(this).closest("td").find(".edit-lineitem");
	var deleteBtn = $(this).closest("td").find(".delete-lineitem");
	
	var acctCode = tb.attr("id").replace('acctTb_','');	
	var lineitemJSON = {};
	
	var accountJSON = {};
	accountJSON['id'] = $(this).closest("table").attr("account-id");
	lineitemJSON['account']=accountJSON;
	lineitemJSON['id']=tr.attr("lineitem-id");
	
	tds.each(function(index, value){
		var columnType = ths.eq(index).attr("column-type");
		if(columnType!==undefined){
			var key= 'column'+(index+1);
			lineitemJSON[key] = $.trim(tds.eq(index).find("input").val());			
		}
	});
	console.log('lineitemJSON:'+JSON.stringify(lineitemJSON));
	
	var jsonInput = {json:JSON.stringify(lineitemJSON)};
	$.ajax({
		type : 'POST',
		url : jsBaseURL + '/budgetingAjax/saveLineItem.action',
		dataType : 'json',
		data : jsonInput,
		cache : false
		}).done(function(respData, textStatus, XMLHttpRequest) {
			try {
				if (isMissing(respData.data)) {
					alert('Unable to Save Lineitem');
					return;
				}
				if(respData.data){					
					
					tr.attr("lineitem-id",respData.data);
					
					tds.each(function(index, value){
						var columnType = ths.eq(index).attr("column-type");
						if(columnType!==undefined){
							var inputField = tds.eq(index).find("input");
							var inputId = inputField.attr("id");
							var labelId = inputId.replace("input", "label");
							$("#"+labelId).text($.trim(inputField.val()));
							$("#"+labelId).show();
							inputField.hide();
							$("#alert_save_success_"+acctCode).show();
							$("#alert_delete_success_"+acctCode).hide();
							saveBtn.hide();
							editBtn.show();
							deleteBtn.show();
						}
					});
					
				}else{
					alert('Unable to Save Lineitem');
				}

			} catch (err) {
				alert('Unable to Save Lineitem');
			}
		}).fail(function(jqXHR, textStatus) {
			alert('Unable to Save Lineitem Due to Communication Error Please Try Again '+textStatus);
		});
}

function isMissing(variable) {
    if('undefined' == $.type(variable)) {
        return true;
    } else if('null' == $.type(variable)) {
        return true;
    } else {
        return false;
    }
}


function numericOnly(e) {
    // Allow: backspace, delete, tab, escape, enter and .
    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
         // Allow: Ctrl+A, Command+A
        (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
         // Allow: home, end, left, right, down, up
        (e.keyCode >= 35 && e.keyCode <= 40)) {
             // let it happen, don't do anything
             return;
    }
    // Ensure that it is a number and stop the keypress
    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
        e.preventDefault();
    }
}










