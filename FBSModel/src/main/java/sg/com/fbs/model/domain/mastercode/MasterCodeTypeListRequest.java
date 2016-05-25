package sg.com.fbs.model.domain.mastercode;

import sg.com.fbs.model.business.pojo.BasePojoRequest;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 25, 2016 4:42:13 PM $
 * 
 */
public class MasterCodeTypeListRequest extends BasePojoRequest{

	private static final long serialVersionUID = -2573040475442973877L;
	
	private long codeTypeId;
	
	private String sequenceJson;
	
	public void setCodeTypeId(long codeTypeId) {
		this.codeTypeId = codeTypeId;
	}
	
	public long getCodeTypeId() {
		return codeTypeId;
	}
	
	public void setSequenceJson(String sequenceJson) {
		this.sequenceJson = sequenceJson;
	}
	
	public String getSequenceJson() {
		return sequenceJson;
	}
	

}
