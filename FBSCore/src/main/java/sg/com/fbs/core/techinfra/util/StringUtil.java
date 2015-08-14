package sg.com.fbs.core.techinfra.util;

public class StringUtil {
	
	public static boolean isEmptyString(String val){
		boolean retVal= false;
		
		if(val==null||val.length()==0){
			retVal = true;
		}
		
		return retVal;
	}
	
	
	public static String leftPad(String val, char padValue, int length){
		StringBuffer result = new StringBuffer();
		int padLength = length - val.length();
		
		while(padLength>0){
			result.append(padValue);
			padLength--;
		}
		
		result.append(val);
		return result.toString();
	}
	
	public static String unCapitalize(String string){
		StringBuilder result = new StringBuilder();
		if(string!=null &&string.length()>0){
			result.append(Character.toLowerCase(string.charAt(0)));
			if(string.length()>1){
				result.append(string.substring(1));
			}
		}
		return result.toString();
	}
	
	public static String makeFirstLetterUpperCase(String str) {
		if (str != null && str.length() > 0) {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}
		return str;
	}
	
	
}
