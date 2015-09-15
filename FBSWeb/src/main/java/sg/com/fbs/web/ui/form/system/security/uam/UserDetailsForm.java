package sg.com.fbs.web.ui.form.system.security.uam;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;
import sg.com.fbs.common.form.BusinessQueryWebForm;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 3:50:04 pm 14 Sep, 2015 $
 * 
 */
public class UserDetailsForm extends BusinessQueryWebForm{

	private static final long serialVersionUID = 1L;
	
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
	
	@Setter
	@Getter
	private String selectedAccounts;
	
	@Override
	public CriteriaIF getSearchCriteria(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
