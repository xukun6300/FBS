package sg.com.fbs.services.security.external.crypto.provider;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Author Frank Xu $
 * @Created 10:21:52 am 19 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
@XmlRootElement(name="token")
public class SecurityAnswerComparisonToken {

	private char[] answer;
	private char[] authAnswer;
	
	public SecurityAnswerComparisonToken(){
		
	}
	
	public SecurityAnswerComparisonToken(char[] answer, char[] authAnswer) {
		if (answer != null) {
			this.answer = Arrays.copyOf(answer, answer.length);
		}
		if (authAnswer != null) {
			this.authAnswer = Arrays.copyOf(authAnswer, authAnswer.length);
		}
	}

	public void setAnswer(char[] answer) {
		if (answer != null) {
			this.answer = Arrays.copyOf(answer, answer.length);
		}
	}

	public char[] getAnswer() {
		return answer;
	}

	public void setAuthAnswer(char[] authAnswer) {
		if (authAnswer != null) {
			this.authAnswer = Arrays.copyOf(authAnswer, authAnswer.length);
		}
	}

	public char[] getAuthAnswer() {
		return authAnswer;
	}
	
}











