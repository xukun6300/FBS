package sg.com.fbs.core.techinfra.web.tag.grid;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import sg.com.fbs.core.techinfra.util.ResourceBundleUtil;
import sg.com.fbs.core.techinfra.util.TagUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author Frank Xu $
 * @Created 10:18:29 am 26 Jun, 2015 $
 * Copyright (c) 2015 
 * Financial & Budgeting System All Rights Reserved.
 */
public class GridSorting extends GridComponent{

	private static final long serialVersionUID = 3310342830911193975L;

	private static final String SORTING_PROPERTY ="sortingProperty";
	
	private static final String IS_ASCENDING = "isAscending";

	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	private String displayName="";
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return ResourceBundleUtil.getMessage(displayName);
	}
	
	@Override
	protected int renderStartTag() throws JspException {
		return BodyTagSupport.SKIP_BODY;
	}

	@Override
	protected int renderEndTag() throws JspException {
		int ret = renderNormalEndTag();
		
		//release resource
		name=null;
		super.releaseResource();
		
		return ret;
	}

	@Override
	protected String getTagName() {	
		return "GridSorting";
	}

	protected int renderNormalEndTag() throws JspException{
		
		GridDataContext dataContext = getDataContext();
		
		if(dataContext!=null && dataContext.getTotalRecordFound()>0){
			long totalPage = dataContext.getTotalPage();
			long currentPage = dataContext.getCurrentPage();
			String action = getAction(); //action attribute in Grid tag
			
			if(totalPage==0){
				totalPage=-1;
			}
			
			Map<String, Object> map = dataContext.getSearchCriteria();
			Map<String, String> orderMap = dataContext.getOrderURIMap();
		
			String sortingProperty = orderMap.get(SORTING_PROPERTY);
			String isAscending = orderMap.get(IS_ASCENDING);
			
			boolean isCurrSortProp = false;
			
			if(sortingProperty!=null){
				isCurrSortProp = sortingProperty.equalsIgnoreCase(this.getName());//compare property name in Order[] and name attribute in GridSorting tag
			}
			
			String ascLinkCSS = "";
			String descLinkCSS = "";
			
			if(isCurrSortProp){
				if(isAscending.equalsIgnoreCase("true")){
					ascLinkCSS = "class='hide' ";
				}else {
					descLinkCSS = "class='hide' ";
				}
			}else {
				descLinkCSS = "class='hide' ";
			}
			
			StringBuffer buf = new StringBuffer();
			
			if(dataContext.isFetchAll){
				orderMap.put("isFetchAll", "true");
			}else {
				orderMap.put("isFetchAll", "false");
			}
			
			
			HttpServletRequest servletRequest = (HttpServletRequest) pageContext.getRequest();
			String contextPath = servletRequest.getContextPath();
			
			
			orderMap.put(SORTING_PROPERTY, this.name);
			
			
			buf.append("<div style=\"float:left;\"><div id='propName' style='float:left;'>");
			buf.append(getDisplayName()+"</div>");
			
			orderMap.put(IS_ASCENDING, "true");
			String ascUrl = TagUtils.createActionURL(pageContext.getRequest(), action, currentPage, map, orderMap);
			buf.append("<div id='pagicon' style='float:left;margin-left:5px;'><a "+ascLinkCSS+ "href=\""+ ascUrl +"\"><img style=\"\" src=\""+ 
			            contextPath +"/static/external/dataTable/images/"+(isCurrSortProp?"sort_desc.png":"sort_both.png") +"\" alt=\"Down\"> </a>&nbsp;" );
			
			orderMap.put(IS_ASCENDING, "false");
			String descUrl = TagUtils.createActionURL(pageContext.getRequest(), action, currentPage, map, orderMap);
			buf.append("<a "+descLinkCSS+"href=\""+descUrl+"\"><img style=\"\" src=\""+contextPath+"/static/external/dataTable/images/sort_asc.png\" alt=\"Up\"></a> ");
			
			buf.append("</div></div>");

			write(buf.toString());
		}

		return BodyTagSupport.EVAL_PAGE;
	}

	
}











