package sg.com.fbs.core.businfra.facade;


import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import sg.com.fbs.core.techinfra.exception.ApplicationCoreException;
import sg.com.fbs.model.business.pojo.BasePojo;
import sg.com.fbs.model.domain.enumeration.ActiveStatusEnum;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;

public class CommonFacade implements CommonFacadeIF,ServiceDefinitionIF {

	public Object run() throws ApplicationCoreException{
		FacadeExecutor facadeExecutor = (FacadeExecutor) ServiceLocator.getService(FACADE_EXECUTOR);
		
		return facadeExecutor.execute(this, null);
	}
	
	public Object run(Object parameter) throws ApplicationCoreException{
		FacadeExecutor facadeExecutor = (FacadeExecutor) ServiceLocator.getService(FACADE_EXECUTOR);
		
		return facadeExecutor.execute(this, parameter);
	}
	
	public Object run(long parameter) throws ApplicationCoreException{
		FacadeExecutor facadeExecutor = (FacadeExecutor) ServiceLocator.getService(FACADE_EXECUTOR);
		
		return facadeExecutor.execute(this, new Long(parameter));
	}
	
	public Object run(Object obj, long parameter) throws ApplicationCoreException{
		FacadeExecutor facadeExecutor = (FacadeExecutor) ServiceLocator.getService(FACADE_EXECUTOR);
		
		return facadeExecutor.execute(this, obj, new Long(parameter));
	}
	
	public Object run(long param1, long param2) throws ApplicationCoreException{
		FacadeExecutor facadeExecutor = (FacadeExecutor) ServiceLocator.getService(FACADE_EXECUTOR);
		
		return facadeExecutor.execute(this, new Long(param1), new Long(param2));
	}
	
	public Object run(Object obj1, Object obj2) throws ApplicationCoreException{
		FacadeExecutor facadeExecutor = (FacadeExecutor) ServiceLocator.getService(FACADE_EXECUTOR);
		
		return facadeExecutor.execute(this, obj1, obj2);
	}
	
	public Object run(Object obj1, Object obj2, Object obj3) throws ApplicationCoreException{
		FacadeExecutor facadeExecutor = (FacadeExecutor) ServiceLocator.getService(FACADE_EXECUTOR);
		
		return facadeExecutor.execute(this, obj1, obj2, obj3);
	}
	
	public Object run(Object obj1, Object obj2, Object obj3, Object obj4) throws ApplicationCoreException{
		FacadeExecutor facadeExecutor = (FacadeExecutor) ServiceLocator.getService(FACADE_EXECUTOR);
		
		return facadeExecutor.execute(this, obj1, obj2, obj3, obj4);
	}
	
	public Object run(Object obj1, Object obj2, Object obj3, Object obj4, Object obj5) throws ApplicationCoreException{
		FacadeExecutor facadeExecutor = (FacadeExecutor) ServiceLocator.getService(FACADE_EXECUTOR);
		
		return facadeExecutor.execute(this, obj1, obj2, obj3, obj4, obj5);
	}
	
	protected void addActiveStatusCriterion(CriteriaIF criteria){
		List<Criterion> newCriterions = new ArrayList<Criterion>();
		boolean activeCriterionExist = false;
		
		Criterion[] currCriterions = criteria.getCriterion();
		
		for (Criterion criterion : currCriterions) {
			if(criterion.getPropertyName().equalsIgnoreCase(BasePojo.ACT_IND)){
				activeCriterionExist = true;
				break;
			}
		}
		
		if(!activeCriterionExist){
			List<Criterion> currCriterionList = Arrays.asList(currCriterions); 
			newCriterions.addAll(currCriterionList);
			newCriterions.add(new Criterion(BasePojo.ACT_IND, ActiveStatusEnum.YES.toString()));
			currCriterions = (Criterion[])newCriterions.toArray(new Criterion[newCriterions.size()]);
		}
		
		criteria.setCriterion(currCriterions);
	}
	
	protected void addInactiveStatusCriterion(CriteriaIF criteria){
		List<Criterion> newCriterions = new ArrayList<Criterion>();		
		boolean inactiveCriterionExist = false;
		
		Criterion[] currCriterions = criteria.getCriterion();
		
		for (Criterion criterion : currCriterions) {
			if(criterion.getPropertyName().equalsIgnoreCase(BasePojo.ACT_IND)){
				inactiveCriterionExist = true;
				break;
			}
		}
		
		if(!inactiveCriterionExist){
			List<Criterion> currCriterionList = Arrays.asList(currCriterions);
			newCriterions.addAll(currCriterionList);
			newCriterions.add(new Criterion(BasePojo.ACT_IND, ActiveStatusEnum.NO.toString()));
			currCriterions = (Criterion[])newCriterions.toArray(new Criterion[newCriterions.size()]);			
		}
		
		criteria.setCriterion(currCriterions);
	}
	
	
	
	
}






















