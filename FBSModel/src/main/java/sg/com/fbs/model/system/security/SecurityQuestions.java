package sg.com.fbs.model.system.security;

import lombok.Getter;
import lombok.Setter;
import sg.com.fbs.model.business.pojo.BaseLogPojo;

/**
 * @Author Frank Xu $
 * @Created 9:45:56 am 19 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class SecurityQuestions extends BaseLogPojo {

	private static final long serialVersionUID = 6472680383721136047L;
	
	private String question;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	
}
