
$(document).ready(function(){	

	//use event delegation: 
	//rows are added to DOM after DOM ready so event will not fire on newly added rows
	$("#accountTb").on('click','.arrow-up', moveRowUp);	
	$("#accountTb").on('click','.arrow-down', moveRowDown);
	//$(".arrow-up").click(moveRowUp);
	//$(".arrow-down").click(moveRowDown);
	$("#addNewRow").click(addNewRow);
});

function moveRowUp(){
	var row = $(this).closest('tr');
	row.prev().insertAfter(row);
}

function moveRowDown(){
	var row = $(this).closest('tr');
	row.next().insertBefore(row);
}

function addNewRow(){
	var newRow = "<tr>" +
			     "<td><input class=\"input\" type=\"text\" maxlength=\"100\"></td>" +
			     "<td><input class=\"input\" type=\"text\" maxlength=\"100\"></td>" +
			     "<td><select class=\"input\"><option value=\"1\">Text</option><option value=\"2\">Numeric</option><option value=\"3\">Date</option></select></td>" +
			     "<td><button type=\"button\" class=\"btn btn-default btn-sm arrow-up\"><span class=\"icon-arrow-up\"></span></button> " +
			     "<button type=\"button\" class=\"btn btn-default btn-sm arrow-down\"><span class=\"icon-arrow-down\"></span></button></td>" +
			     "</tr>";
	$("#accountTb tbody").append(newRow);
}

