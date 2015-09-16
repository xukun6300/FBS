package sg.com.fbs.model.domain.mastercode;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

import sg.com.fbs.model.business.pojo.BasePojoRequest;
import sg.com.fbs.model.system.web.ValueLabelPair;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 3:16:27 pm 16 Sep, 2015 $
 * 
 */
public class MasterCodeTypeRequest extends BasePojoRequest{

	private static final long serialVersionUID = -1730401896478916303L;

	private ValueLabelPair categoryType = new ValueLabelPair();
	
	@Setter
	@Getter
	private String codeKey;
	
	@Setter
	@Getter
	private String remarks;
	
	@Setter
	@Getter
	private DateTime effectiveDate;
	
	@Setter
	@Getter
	private int sortOrder = 1;
	
}
