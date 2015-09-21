
$(document).ready(function(){
	$('input[code-gen="true"]').each(function(){
		$(this).codeGen();
	});
});


$.fn.codeGen = function(){
	var nameId = $(this).attr('code-gen-id');
	var addonIds = $(this).attr('code-gen-add-on-ids');
	var tableName = $(this).attr('code-gen-tb');
	var allowedCharacters = $(this).attr('code-gen-param');
	
	var _self = this;
	
	$('#'+nameId).change(function(){
		var value = $(this).val();
		if(value==''){
			$(_self).val('');
			return;
		}
		
		if(addonIds){
			var addons = addonIds.split('|');
			var addonval = "";
			
			for(var i = 0; i<addons.length; i++){
				if(i==0){
					addonval = $(document).find('#'+addons[i]).val();
				}else{
					addonval += "_" + $(document).find('#'+addons[i]).val();
				}
			}
			
			if(addonval.length>0){
				value = addonval + "_" + value;
			}
		}
		
		
		if(allowedCharacters == undefined){
			$.ajax({
				url : jsBaseURL + '/common/codeAjax/getGeneratedCode.action',		
				dataType : 'text',
				type : 'POST',
			    data : {
			    	name : value,
			    	tbName : tableName
			    }
			}).done(function(data){
				$(_self).val(data);
			}).fail(function(jqXHR, textStatus){
				
			});
			
		}else{
			$.ajax({
				url : jsBaseURL + '/common/codeAjax/getGeneratedCodeWithAllowedCharacters.action',
				dataType : 'text',
				type : 'POST',
				data : {
					name : value,
			        tbName : tableName,
			        allowedCharacters : allowedCharacters
				}
			}).done(function(data){
				$(_self).val(data);
			}).fail(function(jqXHR, textStatus){
				
			});
		}
		
		
	});
	
	
};