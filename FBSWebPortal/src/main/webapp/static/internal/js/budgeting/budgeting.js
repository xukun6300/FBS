$(document).ready(function(){
	var jsBaseURL = '${pageContext.request.contextPath}';
	
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
				 
				 if($(this).attr("column-type")==='D'){
					 newRow += "<td><input type=\"text\" id=\""+cellId+"\" class=\"date-field\" style=\"width:"+ inputWidth +"px\"/></td>";				
				 }else{
					 newRow += "<td><input type=\"text\" id=\""+cellId+"\" class=\"input\" style=\"width:"+ inputWidth +"px\"/></td>"; 
				 }
				 				 
			 }else if($(this).attr("column-name")==='action'){
				 //non user input field
				 newRow += "<td><button type=\"button\" class=\"btn-icon btn-success save-lineitem\"><i class=\"icon-check icon-white\"></i></button></td>";
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

});

function saveLineItem(){
	var tds = $(this).closest("tr").find("td");
	var ths = $(this).closest("table").find("th");
	
	var lineitemJSON = {};
	
	var accountJSON = {};
	accountJSON['id'] = $(this).closest("table").attr("account-id");
	lineitemJSON['account']=accountJSON;
	
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
				if(respData.data == 'Y'){
					//$('#radioDiv').show();
				}else{
					//$('#radioDiv').hide();
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













