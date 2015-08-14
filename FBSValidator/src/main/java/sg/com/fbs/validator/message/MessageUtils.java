package sg.com.fbs.validator.message;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @Author Frank Xu $
 * @Created 11:11:52 am 9 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class MessageUtils {

	private MessageUtils(){
		
	}
	
	public static String createLabelNoPlural(String fieldName, final ResourceBundle bundle){
		if(fieldName.endsWith("es")){
			fieldName = fieldName.substring(0,fieldName.length()-2);
		}else if (fieldName.endsWith("s")) {
			fieldName = fieldName.substring(0,fieldName.length()-1);
		}
		
		return getLabel(fieldName, bundle);
	}
	
	public static String getLabel(final String fieldName, final ResourceBundle bundle){
		String label;
		try {
			label = bundle.getString(fieldName);
		} catch (MissingResourceException e) {
			label = generateLabelValue(fieldName);
		}
		
		return label;
	}
	
	public static String createLabelWithNameSpace(final String namespace, final String fieldName, final ResourceBundle bundle){
		String label;
		try {
			try {
				label = bundle.getString(namespace + "." + fieldName);
			} catch (MissingResourceException e) {
				label = bundle.getString(fieldName);
			}
		} catch (MissingResourceException e) {
			/**
			 * If can't find the label, generate it thus, "firstName" becomes "First Name".
			 */
			label = generateLabelValue(fieldName);
		}
		return label;

	}
	
	/**
	 * Generate the field. Transforms firstName into First Name. This allows
	 * reasonable defaults for labels.
	 */
	public static String generateLabelValue(final String fieldName){
		final StringBuffer buffer = new StringBuffer(fieldName.length()*2);
		
		class GenerationCommand{
			boolean capNextChar = false;
			boolean lastCharWasUpperCase = false;
			boolean lastCharWasNumber = false;
			boolean lastCharWasSpecial = false;
			boolean shouldContinue = true;
			
			char[] chars = fieldName.toCharArray();
			
			void processFieldName(){
				for (int index = 0; index < chars.length; index++) {
					char cchar = chars[index];
					
					shouldContinue = true;
					processCharWasNumber(buffer, index, cchar);
					if(!shouldContinue){
						continue;
					}
					
					processCharWasUpperCase(buffer, index, cchar);
					if(!shouldContinue){
						continue;
					}
					
					processSpecialChars(buffer, cchar);
					if(!shouldContinue){
						continue;
					}
					
					cchar = processCapitalizeCommand(cchar);
					cchar = processFirstCharacterCheck(buffer, index, cchar);
					if(!shouldContinue){
						continue;
					}
					buffer.append(cchar);
				}
			}
			
			private void processCharWasNumber(StringBuffer buffer, int index, char cchar) {
				if (lastCharWasSpecial) {
					return;
				}

				if (Character.isDigit(cchar)) {
					if (index != 0 && !lastCharWasNumber) {
						buffer.append(' ');
					}
					lastCharWasNumber = true;
					buffer.append(cchar);
					shouldContinue = false;
				} else {
					lastCharWasNumber = false;
				}
			}

			private char processFirstCharacterCheck(final StringBuffer buffer, int index, char cchar){
				if(index==0){
					cchar = Character.toUpperCase(cchar);
					buffer.append(cchar);
					shouldContinue = false;
				}
				
				return cchar;
			}
			
			private char processCapitalizeCommand(char cchar){
				if(capNextChar){
					cchar = Character.toUpperCase(cchar);
					capNextChar = false;
				}
				
				return cchar;
			}
			
			private void processSpecialChars(final StringBuffer buffer, char cchar){
				lastCharWasSpecial = false;
				if(cchar == '.'||cchar=='_'){
					buffer.append(' ');
				    capNextChar = true;
					lastCharWasSpecial = false;				    
				    shouldContinue = false;
				}
			}
			
			private void processCharWasUpperCase(final StringBuffer buffer, int index, char cchar){
				/* If the character is uppercase, append a space and keep track
				 * that the last character was uppercase for the next iteration.
				 */
				
				if(Character.isUpperCase(cchar)){
					if(index !=0 && !lastCharWasUpperCase){
						buffer.append('_');
					}
					lastCharWasUpperCase = true;
					buffer.append(cchar);
					shouldContinue = false;
					
				}else {
					lastCharWasUpperCase = false;
				}
			}
		
		}
		
		GenerationCommand gc = new GenerationCommand();
		gc.processFieldName();
		
		/* This is a hack to get address.line_1 to work. */
		return buffer.toString().replace("  ", " ");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
