
function changeScreen(uri) {
	if (uri != "") {
		window.location = uri;
	}
}

function changeScreen(uri, target) {
	if (uri != "") {
		if (target != null && target != "") {
			window.open(uri.target);
		} else {
			window.location = uri;
		}
	}
}

function exportData(type, tableId){
	var jsonData = [];
	var jsonDataHeader = [];
	
	if($('#'+tableId).length==0){
		alert('Export was not implemented for this screen');
		return;
	}
	
	$('#'+tableId +' tr th:not(.export-ignore)').each(function(){
		var tWidth = 0;
		if($(this).attr('tW')!='' && $(this).attr('tW')!='undefined' && $(this).attr('tW')!=null){
			tWidth = $(this).attr('tW');
		}
		jsonDataHeader[jsonDataHeader.length] = $.trim($(this).text().replace(/<br>/g, "\n")).concat("_" + tWidth);		
	});
	//for table header
	jsonData[jsonData.length] = jsonDataHeader;
	
	$('#'+tableId+' tr:not(.export-ignore)').each(function(){
		var jsonDataRow = [];
		var inHTML;
		var inlinkHTML;
		//$(this) is tr?
		$(this).children('td:not(.export-ignore)').each(function(){     //The .find() and .children() methods are similar, except that the latter only travels a single level down the DOM tree.
			if($(this).find("input[type=checkbox]").length>0){
				if($(this).find("input[type=checkbox]").is(':checked')){
					inHTML = "Yes";
				}else{
					inHTML = "No";
				}
			}else if($(this).find("select").length>0){
				var selectField = $(this).find("select")[0];
				inHTML = $(selectField).children(":selected").text();
			}else if($(this).find("input, textarea").length>0){
				var inputField = $(this).find("input, textarea")[0];
				inHTML = $(inputField).val();
			}else{
				inHTML = $(this).text();
				if($(this).find('a').length>0){
					inHTML="";
					var links = $(this).find('a');  //find all <a> tag
					$(links).each(function(i, item){
						inlinkHTML = $(item).html();
						if(inlinkHTML!=null){
							inHTML = inHTML + inlinkHTML;
							if(links.length!=i+1){
								inHTML = inHTML +"<br>";
							}
						}
					});
				}
			}
			
			jsonDataRow[jsonDataRow.length] = $.trim(inHTML.replace(/<br>/g,"\n"));//reg exp,The g means Global, and causes the replace call to replace all matches, not just the first one.
		});
		if(jsonDataRow.length>0){
			jsonData[jsonData.length] = jsonDataRow;
		}
	});
	
	var rowJson = JSON.stringify(jsonData);
	console.log(rowJson);
	var url = jsBaseURL + '/gridexportdata/exportdata.action';   //jsBase is context path (/FBSWebPortal)
	var frm = $('<form>').attr({action:url,method:'POST',target:'_blank'}).appendTo($('body'));
	
	$('<input>').attr({'type':'hidden', 'value':type, 'name':'type'}).appendTo(frm);           // will pass to /gridexportdata/exportdata.action as method params with same name type and exportBody
	$('<input>').attr({'type':'hidden', 'value':rowJson, 'name':'exportBody'}).appendTo(frm);   
	
	frm.submit();
}













