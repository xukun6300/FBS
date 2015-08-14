package sg.com.fbs.core.techinfra.web.tag.grid;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import lombok.Getter;
import lombok.Setter;

import org.apache.log4j.Logger;

import sg.com.fbs.core.techinfra.web.tag.StructuredBaseTag;


/**
 * @author Frank Xu
 *
 */
public abstract class GridComponent extends StructuredBaseTag{

	private static final long serialVersionUID = 8503768415083889359L;
	
	private Logger logger = Logger.getLogger(GridComponent.class);

	private GridDataContext dataContext;
	
	private Grid grid;

	private String action; 
	
	private boolean isAjax;

	private String mapKey; // Used to identify data for paginations
	
	
	public GridComponent(){
		dataContext = null;
		grid = null;
	}
	
	public void release(){
		super.release();
		dataContext = null; 
		grid = null;
	}
	
	protected void prepare() {
		grid = (Grid) TagSupport.findAncestorWithClass(this, Grid.class);
		
		if (grid != null) {
			dataContext = grid.getDataContext();
			if (action != null && !action.trim().equalsIgnoreCase("")) {
				grid.setAction(action);
			}
			if (isAjax != false) {
				grid.setAjax(isAjax);
			}
			if (mapKey != null && !mapKey.trim().equalsIgnoreCase("")) {
				grid.setMapKey(mapKey);
			}

		}
	}
	
	protected Class getValidContainerType(){
		return Grid.class;
	}
	
	protected final GridDataContext getDataContext(){
		return dataContext;
	}
	
	protected final Grid getGrid(){
		return grid;
	}
	
	protected final void releaseResource() throws JspException {
		dataContext = null;
		grid = null;
		action = null;
		mapKey = null;
	}

	
	public String getAction() {
		return grid.getAction();  //get from Grid
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isAjax() {
		return grid.isAjax(); //get from Grid
	}

	public void setAjax(boolean isAjax) {
		this.isAjax = isAjax;
	}

	public String getMapKey() {
		return grid.getMapKey(); //get from Grid
	} 

	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}
}




















