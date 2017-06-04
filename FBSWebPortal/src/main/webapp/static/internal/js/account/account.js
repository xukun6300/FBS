
$(document).ready(function(){	
	retainTable();
	//use event delegation: 
	//rows are added to DOM after DOM ready so event will not fire on newly added rows
	$("#accountTb").on('click','.arrow-up', moveRowUp);	
	$("#accountTb").on('click','.arrow-down', moveRowDown);
	$("#accountTb").on('click','.deleteRow', deleteRow);

	$("#addNewRow").click(addNewRow);
	$("#saveAccount").click(saveAccount);

	if($(".mandatory").text()==null || $(".mandatory").text()==''){
		$(".edit-mode").hide();
	}else{
		//if have any validation failed, then retain the edit mode
		$(".non-edit-mode").hide();
	}
	
	var tbodyHtml = $("#accountTb tbody").html();
	$("#editBtn").click(function(){
		$("#accountTb").addClass('table-sortable');
		$(".table-sortable tbody").sortable({
			start:function(event, ui){
				$(ui.item).attr('previous-index',ui.item.index());
			}
		});
		$(".edit-mode").show();
		$(".non-edit-mode").hide();
	});
	
	$("#cancelBtn").click(function(){
		$("#accountTb tbody").html(tbodyHtml);
		$(".edit-mode").hide();
		$(".non-edit-mode").show();
		$("#accountTb tbody").sortable("destroy");
	});
	
	
	$("#saveBtn").click(updateAccount);
	
	$(".table-sortable tbody").sortable({
		start:function(event, ui){
			$(ui.item).attr('previous-index',ui.item.index());
		}
	});
});

//retain table when submit form failed because of validation
function retainTable(){
	var tableJsonStr = $("#acctStructureJson").val();	
	try{
		var jsonObj = JSON.parse(tableJsonStr); //opposite operation for JSON.stringify
		$("#accountTb tbody").empty();
	
		$.each(jsonObj, function(i, item){
			var row = "";
			var nullable = item['nullable'];
			var checkedForYes = nullable=='Y'?"checked=\"checked\"":"";
			var checkedForNo = nullable=='N'?"checked=\"checked\"":"";
			if(item['defaultColumn']==='Y'){
				
				var optionStr = "";
				
				if(item['columnType']==='T'){
					optionStr = "Text";
				}else if(item['columnType']==='N'){
					optionStr = "Numeric";
				}else{
					optionStr = "Date";
				}
				
				row+="<tr>" +
				     "<td>"+ item['columnName'] +"</td>" +
				     "<td>"+ item['columnSize'] +"</td>" +
				     "<td>"+ optionStr +"</td>" +	
				     "<td><div class=\"controls control-radio-checkbox control-in-table\"> " +
				     "<input type=\"radio\" value=\"Y\" name=\"nullable-row"+i+"\" id=\"nullable-row"+i+"-yes\""+checkedForYes+"><label for=\"nullable-row"+i+"-yes\">Yes</label>" +
				     "<input type=\"radio\" value=\"N\" name=\"nullable-row"+i+"\" id=\"nullable-row"+i+"-no\""+checkedForNo+"><label for=\"nullable-row"+i+"-no\">No</label>"+
				     "</div></td>"+
				     "<td><button class=\"btn-icon btn-danger deleteRow\" disabled><i class=\"icon-remove icon-white\"></i></button></td>"+
				     "</tr>";
			}else{
				var optionStr = "";
				
				if(item['columnType']==='T'){
					optionStr = "<option value=\"T\" selected=\"selected\">Text</option><option value=\"N\">Numeric</option><option value=\"D\">Date</option>";
				}else if(item['columnType']==='N'){
					optionStr = "<option value=\"T\">Text</option><option value=\"N\" selected=\"selected\">Numeric</option><option value=\"D\">Date</option>";
				}else{
					optionStr = "<option value=\"T\">Text</option><option value=\"N\">Numeric</option><option value=\"D\" selected=\"selected\">Date</option>";
				}
				
				row+="<tr>" +
				     "<td><input class=\"input\" type=\"text\" maxlength=\"100\" value=\""+item['columnName']+"\"></td>" +
				     "<td><input class=\"input\" type=\"text\" maxlength=\"100\" value=\""+item['columnSize']+"\"></td>" +
				     "<td><select class=\"input-small\">"+ optionStr + "</select></td>" +	
				     "<td><div class=\"controls control-radio-checkbox control-in-table\"> " +
				     "<input type=\"radio\" value=\"Y\" name=\"nullable-row"+i+"\" id=\"nullable-row"+i+"-yes\""+checkedForYes+"><label for=\"nullable-row"+i+"-yes\">Yes</label>" +
				     "<input type=\"radio\" value=\"N\" name=\"nullable-row"+i+"\" id=\"nullable-row"+i+"-no\""+checkedForNo+"><label for=\"nullable-row"+i+"-no\">No</label>"+
				     "</div></td>"+
				     "<td><button class=\"btn-icon btn-danger deleteRow\"><i class=\"icon-remove icon-white\"></i></button></td>"+
				     "</tr>";
			}
				
            $("#accountTb tbody").append(row);
		});
		

	}catch(e){
		//do nothing to handle empty json string parse exception
	}
	
}

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
	var rowNum = $("#accountTb tr").length;
	//to handle delete some rows and then add new rows, row num will be wrong.
	$("#accountTb").find('tbody tr').each(function(i, val){
		var radioBtnId = $(this).find('td:eq(3) input:eq(0)').attr("id");
		if(radioBtnId.indexOf(rowNum)!=-1){
			rowNum++;
		}
	});
	
	var newRow = "<tr>" +
			     "<td><input class=\"input\" type=\"text\" maxlength=\"100\"></td>" +
			     "<td><input class=\"input\" type=\"text\" maxlength=\"100\"></td>" +
			     "<td><select class=\"input-small\"><option value=\"T\">Text</option><option value=\"N\">Numeric</option><option value=\"D\">Date</option></select></td>" +		
			     "<td><div class=\"controls control-radio-checkbox control-in-table\"> " +
			     "<input type=\"radio\" value=\"Y\" name=\"nullable-row"+rowNum+"\" id=\"nullable-row"+rowNum+"-yes\" checked=\"checked\"><label for=\"nullable-row"+rowNum+"-yes\">Yes</label>" +
			     "<input type=\"radio\" value=\"N\" name=\"nullable-row"+rowNum+"\" id=\"nullable-row"+rowNum+"-no\"><label for=\"nullable-row"+rowNum+"-no\">No</label>"+
			     "</div></td>"+
			     "<td><button class=\"btn-icon btn-danger deleteRow\"><i class=\"icon-remove icon-white\"></i></button></td>"+
			     "</tr>";
	$("#accountTb tbody").append(newRow);
}

function saveAccount(){
	var json = constructAcctStructureJSON();
	$("#acctStructureJson").val(JSON.stringify(json));
	console.log(JSON.stringify(json));
    $('#accountForm').submit();	
}

function updateAccount(){
	var json = constructAcctStructureJSON();
	$("#acctStructureJson").val(JSON.stringify(json));
	$('#updateAccountForm').submit();	
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
			obj['defaultColumn'] = 'Y';
			
		}else{ //user added row, editable
			obj['columnName'] = $.trim($(this).find('td:eq(0) input').val());
			obj['columnSize'] = $.trim($(this).find('td:eq(1) input').val());
			obj['columnType'] = $.trim($(this).find('td:eq(2) select').val()); //json obj key will map to AccountStructure.java attributes, coz it will be deserialized to java obj 
			obj['defaultColumn'] = 'N';
		}
		obj['nullable'] = $(this).find('td:eq(3) input:checked').val();
		obj['sequence'] = ++i;
		json.push(obj);
	});
	return json;
}




