package sg.com.fbs.services.security.external.crypto.provider;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Author Frank Xu $
 * @Created 10:36:53 am 19 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
@XmlRootElement(name="tokens")
public class SecurityAnswerComparisonTokenList {

	private List<SecurityAnswerComparisonToken> tokens;
	
	public SecurityAnswerComparisonTokenList(){
		
	}
	
	public SecurityAnswerComparisonTokenList(List<SecurityAnswerComparisonToken> tokens){
		this.tokens = tokens;
	}
	
	public void setTokens(List<SecurityAnswerComparisonToken> tokens) {
		this.tokens = tokens;
	}
	
	public List<SecurityAnswerComparisonToken> getTokens() {
		return tokens;
	}
}
