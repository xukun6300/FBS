package sg.com.fbs.user;

import sg.com.fbs.model.business.pojo.BaseLogPojo;
import lombok.Getter;
import lombok.Setter;

public class User extends BaseLogPojo{

	private static final long serialVersionUID = -1093950067125607920L;

	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String password;
}
