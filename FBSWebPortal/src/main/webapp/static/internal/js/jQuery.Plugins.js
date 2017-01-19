

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


$.fn.dobDatePicker = function(btnName) {
    var _self = this;
    $(this).datepicker({
           dateFormat: 'dd/mm/yy',
           changeMonth: true,
           changeYear: true,
           yearRange: "-100:+0",
           showButtonPanel: true,
           maxDate: '0'   //base on todays date. force user chose date not later than today
    });
    if (btnName!=null && btnName!='') {
       $(btnName).click(function() {
          $(_self).datepicker('show');
       });
    }
};

$.fn.budgetConfigDatePicker = function(btnName, defaultBudgetDate, defaultMinDate) {
	
    var _self = this; //date input field
    //need to destroy before change datepicker setting on the fly...
    _self.datepicker('destroy');
    
	if(btnName!=null&& btnName!=''){
		$(btnName).click(function(){
			$(_self).datepicker('show');
		});
	}

	$(this).datepicker({ // from jquery ui
		dateFormat:'dd/mm/yy',
		changeMonth:true,
		changeYear:false,
		defaultDate:defaultBudgetDate,
		minDate: defaultMinDate,
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
}

