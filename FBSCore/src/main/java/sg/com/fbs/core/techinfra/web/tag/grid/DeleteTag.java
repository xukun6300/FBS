package sg.com.fbs.core.techinfra.web.tag.grid;

/**
 * @Author Frank Xu $
 * @Created 5:01:43 pm 30 Jun, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class DeleteTag extends ActionTag{

	private static final long serialVersionUID = 7123680091020738844L;
	
	@Override
	public String getTitle() {
		return "fbs.common.ui.label.delete";
	}
	
	public String getPicName() {
		return "delete.gif";
	}

	public String getButtonClass() {
		return "btn btn-danger";
	}

	public String getIconClass() {
		return "icon-remove icon-white";
	}
}
