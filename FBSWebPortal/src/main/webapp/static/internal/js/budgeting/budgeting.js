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
				 
				 if($(this).attr("column-type")==='D'){
					 newRow += "<td><label id=\""+labelId+"\"></label><input type=\"text\" id=\""+inputId+"\" class=\"date-field\" style=\"width:"+ inputWidth +"px\"/></td>";				
				 }else{
					 newRow += "<td><label id=\""+labelId+"\"></label><input type=\"text\" id=\""+inputId+"\" class=\"input\" style=\"width:"+ inputWidth +"px\"/></td>"; 
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
	
	$("#deleteLineitemDialogCloseBtn").click(function(){
		$('#deleteLineitemDialog').modal('hide');
	});

	$("#deleteLineitemDialogDeleteBtn").click(function(){

		var lineitemId = $('#deleteLineitemDialog').attr('lineitem-id');
		$.ajax({
			type : 'POST',
			url : jsBaseURL + '/budgetingAjax/deleteLineItem.action',
			dataType : 'json',
			data : {"lineitemId":lineitemId},
			cache : false
		}).done(function(respData, textStatus, XMLHttpRequest) {
			try {
				if (isMissing(respData.data)) {
					alert('Unable to Save Lineitem');
					return;
				}
				if(respData.data){								
					$("#alert_success_"+acctCode).show();
				}else{
					alert('Unable to Save Lineitem');
				}

			} catch (err) {
				alert('Unable to Save Lineitem');
			}
		}).fail(function(jqXHR, textStatus) {
			alert('Unable to Save Lineitem Due to Communication Error Please Try Again '+textStatus);
		});
	});
});

function deleteLineItem(){
	var lineitemId = $(this).closest("tr").attr("lineitem-id");
	$('#deleteLineitemDialog').attr('lineitem-id',lineitemId);		
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
				 
				 var rowNum = $("#acctTb_"+acctCode+" tbody tr").length;
				 
				 var inputIdNew = "input_"+acctCode+"_"+rowNum+"_"+index;
				 var labelIdNew = "label_"+acctCode+"_"+rowNum+"_"+index;
				 $(this).find("label").attr("id",labelIdNew);
				 $(this).find("input").attr("id",inputIdNew);	
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
			$("#alert_success_"+acctCode).hide();
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
							$("#alert_success_"+acctCode).show();
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













