package sg.com.fbs.model.user;

import lombok.Getter;
import lombok.Setter;
import sg.com.fbs.model.business.pojo.BasePojoRequest;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 11:13:08 am 9 Sep, 2015 $
 * 
 */
public class UserRequest extends BasePojoRequest{

	private static final long serialVersionUID = 4450419410821261614L;
	
	@Setter
	@Getter
	private String name;
	
	@Setter
	@Getter
	private String salutation;
	
	@Setter
	@Getter
	private String email;
	
	@Setter
	@Getter
	private String accountStatus;
	
	@Setter
	@Getter
	private String userRole;
	
	@Setter
	@Getter
	private String lastSuccessLoginDate;
	
	@Setter
	@Getter
	private String lastFailedLoginDate;
	
	@Setter
	@Getter
	private String programme;
	
	@Setter
	@Getter
	private String dateOfBirth;
	
	@Setter
	@Getter
	private String gender;
	
	@Setter
	@Getter
	private String officeTel;
	
	@Setter
	@Getter
	private String mobileNum;
	
}
