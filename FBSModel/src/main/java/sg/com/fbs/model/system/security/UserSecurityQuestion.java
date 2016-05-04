package sg.com.fbs.model.system.security;

import lombok.Getter;
import lombok.Setter;
import sg.com.fbs.model.business.pojo.BaseLogPojo;

/**
 * @Author Frank Xu $
 * @Created 9:47:24 am 19 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class UserSecurityQuestion extends BaseLogPojo{
	
	private static final long serialVersionUID = -7306076144501698369L;
	
	public static final String USER_ID = "users.id";

	private User userId;

	private SecurityQuestions questionId;

	private String answer;

	private User users;

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public SecurityQuestions getQuestionId() {
		return questionId;
	}

	public void setQuestionId(SecurityQuestions questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	
}
