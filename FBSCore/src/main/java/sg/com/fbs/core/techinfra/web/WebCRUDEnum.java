package sg.com.fbs.core.techinfra.web;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public enum WebCRUDEnum {

	NONE{
	   public String toString(){
		   return "";
	   }
	},
	
	QUERY_MODE{
	   public String toString(){
		   return "runQuery";
	   }
	},
	
	DETAILS_MODE{
		public String toString(){
			return "runDetails";
		}
	},
	
	UPDATE_MODE{
		public String toString(){
			return "update";
		}
	},
	
	INSERT_MODE{
		public String toString(){
			return "insert";
		}
	},
	
	DELETE_MODE{
		public String toString(){
			return "delete";
		}
	},
	
	DYNAMIC_MODE{
		public String toString(){
			return "dynamic";
		}
	},
	
	
}
