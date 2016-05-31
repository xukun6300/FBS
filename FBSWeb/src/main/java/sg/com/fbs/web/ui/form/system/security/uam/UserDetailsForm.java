package sg.com.fbs.web.ui.form.system.security.uam;

import java.util.List;

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
	
	private String name;

	private String salutation;

	private String email;

	private String accountStatus;

	private String userRole;

	private String lastSuccessLoginDate;

	private String lastFailedLoginDate;
	
	private String programme;

	private String dateOfBirth;

	private String gender;

	private String officeTel;

	private String mobileNum;

	private String selectedAccounts;
	
	private List<String> selectedAccountList;

	public void setSelectedAccountList(List<String> selectedAccountList) {
		this.selectedAccountList = selectedAccountList;
	}

	public List<String> getSelectedAccountList() {
		return selectedAccountList;
	}
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSalutation() {
		return salutation;
	}



	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getAccountStatus() {
		return accountStatus;
	}



	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}



	public String getUserRole() {
		return userRole;
	}



	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}



	public String getLastSuccessLoginDate() {
		return lastSuccessLoginDate;
	}



	public void setLastSuccessLoginDate(String lastSuccessLoginDate) {
		this.lastSuccessLoginDate = lastSuccessLoginDate;
	}



	public String getLastFailedLoginDate() {
		return lastFailedLoginDate;
	}



	public void setLastFailedLoginDate(String lastFailedLoginDate) {
		this.lastFailedLoginDate = lastFailedLoginDate;
	}



	public String getProgramme() {
		return programme;
	}



	public void setProgramme(String programme) {
		this.programme = programme;
	}



	public String getDateOfBirth() {
		return dateOfBirth;
	}



	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getOfficeTel() {
		return officeTel;
	}



	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}



	public String getMobileNum() {
		return mobileNum;
	}



	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}



	public String getSelectedAccounts() {
		return selectedAccounts;
	}



	public void setSelectedAccounts(String selectedAccounts) {
		this.selectedAccounts = selectedAccounts;
	}



	@Override
	public CriteriaIF getSearchCriteria(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
