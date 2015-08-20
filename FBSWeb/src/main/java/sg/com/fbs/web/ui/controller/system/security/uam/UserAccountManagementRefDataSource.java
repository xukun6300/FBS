package sg.com.fbs.web.ui.controller.system.security.uam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.core.techinfra.web.WebDropDownListIF;
import sg.com.fbs.core.techinfra.web.WebRefDataSourceIF;
import sg.com.fbs.core.techinfra.web.WebRefDataSourceImpl;
import sg.com.fbs.model.domain.enumeration.ActiveStatusEnum;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.query.CriterionIF;
import sg.com.fbs.model.system.persistence.query.Order;
import sg.com.fbs.model.system.persistence.response.ResponseCRUD;
import sg.com.fbs.model.system.security.SecurityQuestions;
import sg.com.fbs.services.controlsource.GenderTypeControlSource;
import sg.com.fbs.services.controlsource.SalutationControlSource;
import sg.com.fbs.services.system.security.uam.mgr.UserAccountManagerBD;

/**
 * @Author Frank Xu $
 * @Created 3:29:30 pm 5 Aug, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class UserAccountManagementRefDataSource extends WebRefDataSourceImpl implements WebRefDataSourceIF{

	@Override
	public void populateReferenceData(WebDropDownListIF[] enums,ModelAndView modelView, Map extraParams)throws ApplicationCoreException {
		
		for (WebDropDownListIF dropdown : enums) {
			populateDataFromManager(new UserAccountManagementRefDataSource(), dropdown, modelView, extraParams);
		}	
	}
	
	public Map<String, String> getSalutationType(String listName, ModelAndView modelview, Map extraParams) throws ApplicationCoreException{
		Map<String, String> salutations = new LinkedHashMap<String, String>();
		salutations.put(UserAccountManagementWebEnum.DEFAULT_VALUE.toString(), UserAccountManagementWebEnum.PLEASE_SELECT.toString());
		SalutationControlSource salutationControlSource = new SalutationControlSource();
		salutations.putAll(salutationControlSource.getControlSourceValues(salutations));
		return salutations;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> getGenderType(String listName, ModelAndView modelview, Map extraParams) throws ApplicationCoreException{
		Map<String, String> genderTypes = new LinkedHashMap<String, String>();
	    GenderTypeControlSource genderTypeControlSource = new GenderTypeControlSource();
	    return genderTypeControlSource.getControlSourceValues(genderTypes);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> getSecurityQuestion(String listName, ModelAndView modelview, Map extraParams) throws ApplicationCoreException{
		UserAccountManagerBD userAccountManagerBD = new UserAccountManagerBD();
		CriteriaIF searchCriteria = new Criteria();
		CriterionIF[] criterion = {new Criterion(SecurityQuestions.ACT_IND, ActiveStatusEnum.YES.toString())};
		Order order = new Order(SecurityQuestions.ID, true);
		Order[] orders = {order};
		
		searchCriteria.setCriterion(criterion);
		searchCriteria.setOrder(orders);
		searchCriteria.setFetchAll(true);
		
		ResponseCRUD<?> response = userAccountManagerBD.searchSecurityQuestion(searchCriteria);
		Map<String, String> securityQuestionsMap = new LinkedHashMap<String, String>();
		securityQuestionsMap.put(UserAccountManagementWebEnum.DEFAULT_VALUE.toString(), UserAccountManagementWebEnum.PLEASE_SELECT.toString());
		
		if(response!=null && response.getTotalRecords()>0){
			Collection<SecurityQuestions> securityQuestions = (Collection<SecurityQuestions>) response.getQueryResult();
			for (SecurityQuestions securityQuestion : securityQuestions) {
				securityQuestionsMap.put(""+securityQuestion.getId(), securityQuestion.getQuestion());
			}
		}
		
		return securityQuestionsMap;
	}

}


























