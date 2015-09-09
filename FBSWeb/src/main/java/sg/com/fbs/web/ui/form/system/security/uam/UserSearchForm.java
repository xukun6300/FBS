package sg.com.fbs.web.ui.form.system.security.uam;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;
import sg.com.fbs.common.form.BusinessQueryWebForm;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.security.User;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 5:11:01 pm 8 Sep, 2015 $
 * 
 */
public class UserSearchForm extends BusinessQueryWebForm{

	private static final long serialVersionUID = 1L;

	@Setter
	@Getter
	private String name;
	
	@Setter
	@Getter
	private String email;
	
	@Setter
	@Getter
	private String accountStatus;
	
	@Setter
	@Getter
	private String userRole;
	
	
	
	
	@Override
	public CriteriaIF getSearchCriteria(HttpServletRequest request) {
		CriteriaIF criteria = new Criteria();
		criteria.appendCriterion(new Criterion(User.ACT_IND, "Y"));
		return criteria;
	}

}
