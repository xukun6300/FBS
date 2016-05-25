package sg.com.fbs.web.ui.form.mastercode;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import sg.com.fbs.common.form.BusinessWebForm;
import sg.com.fbs.model.domain.mastercode.MasterCodeType;
import sg.com.fbs.model.system.persistence.query.Criteria;
import sg.com.fbs.model.system.persistence.query.CriteriaIF;
import sg.com.fbs.model.system.persistence.query.Criterion;
import sg.com.fbs.model.system.persistence.query.Order;
import sg.com.fbs.validator.annotations.validation.Required;

public class MasterCodeTypeForm extends BusinessWebForm{

	private static final long serialVersionUID = 516638259451945521L;

	private String codeKey;
		
	private String remarks;

	private int sortOrder = 1;
	
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public int getSortOrder() {
		return sortOrder;
	}
	
	private DateTime effectiveDate;
	
	@Required(detailMessage="{fbs.common.errors.code.key.required}",
			  summaryMessage="{fbs.common.errors.code.key.required}")
	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}
	
	public String getCodeKey() {
		return codeKey;
	}
	
	@Required(detailMessage="{fbs.common.errors.remarks.required}",
			  summaryMessage="{fbs.common.errors.remarks.required}")
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
    @DateTimeFormat(pattern="dd/MM/yyyy")
	@Required(detailMessage="{fbs.common.errors.effectivedate.required}",
	          summaryMessage="{fbs.common.errors.effectivedate.required}")
	public void setEffectiveDate(DateTime effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	public DateTime getEffectiveDate() {
		return effectiveDate;
	}
	
	@Override
	public CriteriaIF getSearchCriteria(HttpServletRequest request) {
		
		CriteriaIF criteria = new Criteria();
		criteria.appendCriterion(new Criterion(MasterCodeType.ID, this.getId()));
		criteria.setFetchAll(true);
		
		Order order = new Order(MasterCodeType.ID, true);
		Order[] orders = {order};
		
		criteria.setOrder(orders);
		
		return criteria;
	}

}
