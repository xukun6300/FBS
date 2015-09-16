package sg.com.fbs.model.domain.mastercode;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;
import sg.com.fbs.model.system.web.ValueLabelPair;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu $
 * @Created 11:52:58 am 16 Sep, 2015 $
 * 
 */
public class MasterCodeRequest {
	
	@Setter
	@Getter
	protected ValueLabelPair categoryType = new ValueLabelPair();
	
	@Setter
	@Getter
	protected MasterCodeType masterCodeType;
	
	@Setter
	@Getter
	protected String codeValue;
	
	@Setter
	@Getter
	protected String description;
	
	@Setter
	@Getter
	protected String remarks;
	
	@Setter
	@Getter
	protected int sequenceNo;
	
	@Setter
	@Getter
	protected DateTime effectiveDate;
	
	@Setter
	@Getter
	protected DateTime expiryDate;
}
