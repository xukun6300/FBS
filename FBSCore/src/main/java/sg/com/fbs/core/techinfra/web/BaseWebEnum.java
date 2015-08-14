package sg.com.fbs.core.techinfra.web;

public enum BaseWebEnum {

	COMMAND_FORM{
		public String toString(){
			return "command";
		}
	},
	
	MODEL_BEAN{
		public String toString(){
			return "modelBean";
		}
	},
	
	DROP_DOWN_LIST{
		public String toString(){
			return "dropdown";
		}
	},
	
	MODELVIEW{
		public String toString(){
			return "modelView";
		}
	}
	
}
