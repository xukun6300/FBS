package sg.com.fbs.core.techinfra.web.tag.grid;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created May 18, 2016 11:21:31 AM $
 * 
 */
public class UpdateTag extends ActionTag{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getTitle() {
		return "fbs.common.ui.label.update";
	}
	
	@Override
	public String getPicName() {
		return "update.gif";
	}
	
	@Override
	public String getButtonClass() {
		return "btn btn-inverse";
	}
	
	@Override
	public String getIconClass() {
		return "icon-edit icon-white";
	}

}
