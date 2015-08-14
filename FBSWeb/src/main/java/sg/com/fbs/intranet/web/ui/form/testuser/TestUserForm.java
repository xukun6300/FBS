package sg.com.fbs.intranet.web.ui.form.testuser;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

import sg.com.fbs.core.techinfra.web.BaseQueryWebForm;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.query.CriterionIF;
import sg.com.fbs.model.system.persistence.query.Order;
import sg.com.fbs.model.system.persistence.query.OrderIF;
import sg.com.fbs.validator.annotations.validation.Length;

public class TestUserForm extends BaseQueryWebForm{

	private static final long serialVersionUID = 3380033605179494868L;
	
	@Length(min=1,max=3,summaryMessage="this is test message")
	public void setName(String name) {
		this.name = name;
	}
	@Getter
	private String name;
	
	@Setter
	@Getter
	private String password;

	@Setter
	@Getter
	private DateTime publishDateFrom;
	
	
	@Override
	public String[] getDefaultOrders() {
		String[] orders = { "name" };
		return orders;
	}

	@Override
	public CriteriaIF getSearchCriteria(HttpServletRequest request) {
	/*	CriterionIF[] criterion =getCriterion();
		
		if(criterion==null){
			CriteriaIF criteria = new Criteria();
			criteria.appendCriterion(new Criterion("name","e"));

			Order order =new Order("name", false);
			OrderIF[] orders = {order};
			criteria.setOrder(orders);
			criteria.setFetchAll(false);
			criteria.setPageSize(10);
		}
		
		
		return criteria;*/
		
		CriteriaIF searchCriteria = (CriteriaIF) new Criteria();
		CriterionIF[] criterion = getCriterion();
		
		List<CriterionIF> criterionList = new ArrayList<CriterionIF>();
		
	//	String sortingProperty = request.getParameter("sortingProperty");

		if (criterion == null) {		
			criterionList.add(new Criterion("name","e"));
		//	criterionList.add(new Criterion("password", "12345678", true));
			criterion = criterionList.toArray(new CriterionIF[criterionList.size()]);
		}
				
		
		OrderIF[] orders = getOrder();
		if (request.getParameter(IS_ASCENDING) != null && (request.getParameter(IS_ASCENDING)).trim().length()>0 && request.getParameter(SORTING_PROPERTY) != null && (request.getParameter(SORTING_PROPERTY)).trim().length()>0) {
			Order order =new Order(request.getParameter(SORTING_PROPERTY), request.getParameter(IS_ASCENDING).equalsIgnoreCase("true")?true:false);
			OrderIF[] newOrders = {order};
			orders = newOrders;
		}else {
			Order order =new Order("name",false);
			OrderIF[] newOrders = {order};
			orders = newOrders;
		}
		searchCriteria.setOrder(orders);
		searchCriteria.setPageSize(10);
		searchCriteria.setCriterion(criterion);
		searchCriteria.setFetchAll(super.isFetchAll());
		searchCriteria.setRequestedPage(super.getRequestedPage());
		
		return searchCriteria;

	}


}
