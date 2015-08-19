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
	
	@Setter
	@Getter
	private User userId;
	
	@Setter
	@Getter
	private SecurityQuestions questionId;
	
	@Setter
	@Getter
	private String answer;
	
	@Setter
	@Getter
	private User users;

}
