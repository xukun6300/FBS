$(document).ready(function(){

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
			 }else{
				 //non user input field
				 newRow += "<td></td>";
			 }
			 
		 });
		 newRow +="</tr>";
		
		 $("#acctTb_"+acctCode+" tbody").append(newRow);
		 $(".date-field").myDatePicker();
	});
})

