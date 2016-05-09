
$(document).ready(function(){	

	//use event delegation: 
	//rows are added to DOM after DOM ready so event will not fire on newly added rows
	$("#accountTb").on('click','.arrow-up', moveRowUp);	
	$("#accountTb").on('click','.arrow-down', moveRowDown);
	$("#accountTb").on('click','.deleteRow', deleteRow);
	
	//$(".arrow-up").click(moveRowUp);
	//$(".arrow-down").click(moveRowDown);
	$("#addNewRow").click(addNewRow);
	$("#saveAccount").click(saveAccount);
});

function moveRowUp(){
	var row = $(this).closest('tr');
	row.prev().insertAfter(row);
}

function moveRowDown(){
	var row = $(this).closest('tr');
	row.next().insertBefore(row);
}

function deleteRow(){
	var row = $(this).closest('tr');
	row.remove();
}

function addNewRow(){
	var newRow = "<tr>" +
			     "<td><input class=\"input\" type=\"text\" maxlength=\"100\"></td>" +
			     "<td><input class=\"input\" type=\"text\" maxlength=\"100\"></td>" +
			     "<td><select class=\"input-small\"><option value=\"T\">Text</option><option value=\"N\">Numeric</option><option value=\"D\">Date</option></select></td>" +
			     "<td><button type=\"button\" class=\"btn btn-default btn-sm arrow-up\"><span class=\"icon-arrow-up\"></span></button> " +
			     "<button type=\"button\" class=\"btn btn-default btn-sm arrow-down\"><span class=\"icon-arrow-down\"></span></button></td>" +
			     "<td><button class=\"btn btn-danger deleteRow\"><i class=\"icon-remove icon-white\"></i></button></td>"+
			     "</tr>";
	$("#accountTb tbody").append(newRow);
}

function saveAccount(){
	var json = constructAcctStructureJSON();
	//alert(JSON.stringify(jsonStr));
	console.log(JSON.stringify(json));
	$('<input>').attr({'type':'hidden','value':JSON.stringify(json),'name':'acctStructureJson'}).appendTo('#accountForm');
	$('#accountForm').submit();	
}


function constructAcctStructureJSON(){
	var json = [];
	$("#accountTb").find('tbody tr').each(function(i, val){
		var obj = {};
		
		if($(this).find('td:eq(0) input').length==0){ //non-editable row(default)
			var colText = $.trim($(this).find('td:eq(2)').text());
			var colType = '';		
			if(colText==='Text'){
				colType = 'T';
			}else if (colText==='Numeric'){
				colType = 'N';
			}else{
				colType = 'D';
			}
			obj['columnName'] = $.trim($(this).find('td:eq(0)').text());
			obj['columnSize'] = $.trim($(this).find('td:eq(1)').text());			
			obj['columnType'] = colType ;
			
		}else{ //user added row, editable
			obj['columnName'] = $.trim($(this).find('td:eq(0) input').val());
			obj['columnSize'] = $.trim($(this).find('td:eq(1) input').val());
			obj['columnType'] = $.trim($(this).find('td:eq(2) select').val()); //json obj key will map to AccountStructure.java attributes, coz it will be deserialized to java obj 
		}
		
		obj['sequence'] = ++i;
		json.push(obj);
	});
	return json;
}







