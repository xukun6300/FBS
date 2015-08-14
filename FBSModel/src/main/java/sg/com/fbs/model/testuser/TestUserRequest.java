package sg.com.fbs.model.testuser;

import lombok.Getter;
import lombok.Setter;
import sg.com.fbs.model.business.pojo.BasePojoRequest;

public class TestUserRequest extends BasePojoRequest{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1080767589797997435L;

	@Setter
	@Getter
	private String name;
	
	@Setter
	@Getter
	private String password;
}
