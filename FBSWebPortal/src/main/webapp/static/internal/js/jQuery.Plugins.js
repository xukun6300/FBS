

$.fn.myDatePicker = function(btnName){
	var _self = this; //date input field
	
	if(btnName!=null&& btnName!=''){
		$(btnName).click(function(){
			$(_self).datepicker('show');
		});
	}

	$(this).datepicker({ // from jquery ui
		dateFormat:'dd/mm/yy',
		changeMonth:true,
		changeYear:true,
		showButtonPanel:true,
		fixFocusIE:false,
		
		// to fix IE after click 'today', the date picker not close issue
		onSelect:function(text,inst){
			this.fixFocusIE = true,
			$(this).change().focus();
		},
		
		onClose:function(text,inst){
			this.fixFocusIE = true,
			$(this).focus();
		},
		
		beforeShow:function(text,inst){
			var result =true;
			if(!navigator.userAgent.match(/MSIE/) || !navigator.userAgent.match(/Trident.*rv[ :]?.*\./)){
				result = !this.fixFocusIE;
			}
			this.fixFocusIE = false;
			return result;
		}
	});
	
};