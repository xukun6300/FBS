package sg.com.fbs.core.techinfra.web.tag.grid;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import lombok.Setter;
import lombok.Getter;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import com.google.gson.Gson;

import sg.com.fbs.core.techinfra.util.DateUtil;
import sg.com.fbs.core.techinfra.util.ResourceBundleUtil;
import sg.com.fbs.core.techinfra.util.TagUtils;
import sg.com.fbs.model.system.activity.ActivityLog;
import sg.com.fbs.model.system.datalog.DataLog;
import sg.com.fbs.model.system.transactionlog.TransactionLog;
import sun.tools.jar.resources.jar;


/**
 * @Author Frank Xu $
 * @Created 4:56:51 pm 29 Jun, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
public class GridPaging extends GridComponent{
	
	private static final long serialVersionUID = -2462554141815425685L;
	
	private Logger logger = Logger.getLogger(GridPaging.class);
	
	private static final int DEFAULT_PAGE_SIZE = 10;
	
	private static final String DOWNLOAD_IMAGE = "../images/csv.gif";
	
	private static final String TOTAL_COUNT_SUFFIX = "_TOTAL_CNT";
	
	private static final String TOTAL_PAGE_SUFFIX = "_TOTAL_PAGE";
	
	private static final String ONCLICK_CHANGEPATH = "' onClick=\"javascript:changePage(this,'";
	
	private static final String ONCLICK_CHANGESCREEN = "' onClick=\"javascript:changeScreen('";
	
	private static final String INPUT_ID = "<input id='";
	
	private static final String CLOSE_BRACKET ="' />";
	
	private static final String CLOSE_BRACKET_WITH ="')\">";
	
	private int pageSize;
	
	private String tableId; //html table id that shows grid

	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	
	private boolean viewAll = true;

	public boolean isViewAll() {
		return viewAll;
	}
	public void setViewAll(boolean viewAll) {
		this.viewAll = viewAll;
	}

	private boolean download = true;
	
	public void setDownload(boolean download) {
		this.download = download;
	}
	public boolean isDownload() {
		return download;
	}
	

	private boolean exportExcel = true;

	private boolean exportPDF = true;

	private boolean doPaging;

	public boolean isExportExcel() {
		return exportExcel;
	}
	public void setExportExcel(boolean exportExcel) {
		this.exportExcel = exportExcel;
	}
	public boolean isExportPDF() {
		return exportPDF;
	}
	public void setExportPDF(boolean exportPDF) {
		this.exportPDF = exportPDF;
	}
	public boolean isDoPaging() {
		return doPaging;
	}
	public void setDoPaging(boolean doPaging) {
		this.doPaging = doPaging;
	}
	public GridPaging(){
		this.pageSize = -1;
	}
	
	@Override
	protected int renderStartTag() throws JspException {
		return BodyTagSupport.SKIP_BODY;
	}

	@Override
	protected int renderEndTag() throws JspException {
		int ret = renderDemoEndTag();
		
		//release resource
		tableId = null;
		super.releaseResource();
		
		return ret;
	}

	@Override
	protected String getTagName() {
		return "GridPaging";
	}

	public int renderDemoEndTag(){
		
		GridDataContext dataContext = getDataContext();
		
		if (dataContext != null && dataContext.getResult() != null && dataContext.getTotalRecordFound() > 0) {

			long totalPage = dataContext.getTotalPage();
			long currentPage = dataContext.getCurrentPage();
			//?
			if(totalPage==0){
				totalPage = -1;
			}
			
			HashMap map = (HashMap) dataContext.getSearchCriteria();
			HashMap orderMap = (HashMap) dataContext.getOrderURIMap();
			orderMap.put("isFetchAll", "false");
			
			boolean isLog = false;
			Collection resultFromCrud = dataContext.getResult().getQueryResult();
			
			if(resultFromCrud!=null && resultFromCrud.size()>0){
				Object obj = resultFromCrud.iterator().next();
				isLog = (obj instanceof ActivityLog)|| (obj instanceof DataLog) || (obj instanceof TransactionLog);
			}
			
			StringBuffer buf = new StringBuffer();
			
			Collection objCol = null;
			int colSize = 0;
			
			Gson gson = new Gson();
			
			String[] actionArray = getAction().split("/");
			
			if(isAjax()){
				//why actionArray[1]
				buf.append("<div class='form-inline'><select id='"+actionArray[1]+"_selectPages' style='height:10px;border=1px;' class='input-medium'  onchange=\"javascript:changePage(this,'"+getAction()+"','"+tableId+"','"+getMapKey()+CLOSE_BRACKET_WITH );
				
				pageSize = dataContext.getResult().getCriteria().getPageSize();
				
				if (dataContext.getResult().getMoreQueryResults() != null && dataContext.getResult().getMoreQueryResults().size() > 0) {
					//???
					Map<String, Object> moreQueryResults = dataContext.getResult().getMoreQueryResults();
					objCol = (Collection) moreQueryResults.get(getMapKey());
					if(objCol!=null){
						if(moreQueryResults.get(getMapKey()+TOTAL_COUNT_SUFFIX)!=null){
							colSize = ((Integer)moreQueryResults.get(getMapKey()+TOTAL_COUNT_SUFFIX)).intValue();
						}
						if(moreQueryResults.get(getMapKey()+TOTAL_PAGE_SUFFIX)!=null){
							totalPage = ((Integer)moreQueryResults.get(getMapKey()+TOTAL_PAGE_SUFFIX)).intValue();
						}
					}
				}
				
				//??
				if (objCol == null) {
					colSize = dataContext.getResult().getTotalRecords();
				}
				
				for (int i = 0; i < totalPage; i++) {
					buf.append("<option value='"+i+"' >");
					buf.append(ResourceBundleUtil.getMessage("fbs.common.ui.label.pagination.page")+" "+ i + " of " +totalPage);
					buf.append("</option>\n");
				}
				//first button
				buf.append("</select> &nbsp; <input type='button' id='"+actionArray[1]+"_first' class='bt-sm' style='margin-left:8px;' value='"+
				           ResourceBundleUtil.getMessage("fbs.common.ui.label.pagination.first")+ONCLICK_CHANGEPATH+getAction()+"','"+tableId+"','"+getMapKey()+CLOSE_BRACKET_WITH);
				//previous button
				buf.append("<input type='button' id='"+actionArray[1]+"_previous' class='bt-sm hide' value='"+
				           ResourceBundleUtil.getMessage("fbs.common.ui.label.pagination.previous") + ONCLICK_CHANGEPATH+getAction()+"','"+tableId+"','"+getMapKey()+CLOSE_BRACKET_WITH);
				//next button
				if(currentPage<totalPage){
					buf.append("<input type='button' id='"+actionArray[1]+"_next' class='bt-sm' style='margin-left:8px;' value='"+
				               ResourceBundleUtil.getMessage("fbs.common.ui.label.pagination.next") + ONCLICK_CHANGEPATH + getAction()+"','" +tableId+"','"+getMapKey()+CLOSE_BRACKET_WITH);
				}
				//last button
				buf.append("<input type='button' id='"+actionArray[1]+"_last' class='bt-sm' style='margin-left:8px;' value='"+
				           ResourceBundleUtil.getMessage("fbs.common.ui.label.pagination.last") + ONCLICK_CHANGEPATH+getAction()+"','"+tableId+"','"+getMapKey()+CLOSE_BRACKET_WITH);
				//show all button
				if(!isLog && viewAll){
					buf.append("<input type='button' id='" + actionArray[1]+"_all' class='bt-sm' style='margin-left:8px;' value='"+
				               ResourceBundleUtil.getMessage("fbs.common.ui.label.pagination.show.all")+ONCLICK_CHANGEPATH + getAction()+"','"+tableId+"','"+getMapKey()+CLOSE_BRACKET_WITH);
				}
				
				buf.append(INPUT_ID + actionArray[1] +"_totalRecords type='hidden' value='" + colSize + CLOSE_BRACKET);
				buf.append(INPUT_ID + actionArray[1] +"_totalPage type='hidden' value='" + totalPage + CLOSE_BRACKET);
				buf.append(INPUT_ID + actionArray[1] +"_currentPage type='hidden' value='" + currentPage + CLOSE_BRACKET);
				buf.append(INPUT_ID + actionArray[1] +"_pageSize type='hidden' value='" + pageSize + CLOSE_BRACKET);
				buf.append(INPUT_ID + actionArray[1] +  "_requestedPage' type='hidden' value='1' />");
				buf.append(INPUT_ID + actionArray[1] +  "_isFetchAll' type='hidden' value='false' />");
				
				buf.append(INPUT_ID + actionArray[1] + "_criteriaMap' type='hidden' value='" +gson.toJson(processMap(map))+ CLOSE_BRACKET);
				buf.append(INPUT_ID + actionArray[1] + "_orderMap' type='hidden' value='" +gson.toJson(processMap(orderMap)) + CLOSE_BRACKET);
				
			}else {
				
				if (isDoPaging()) {  // bottom paging
					long startPage = 1;
					long endPage = 5;

					if (currentPage > 3) { // Maximum display is 5. Current page always at the center, except first two and last two records
						startPage = currentPage - 2;
						endPage = currentPage + 2;
					}

					if (currentPage > totalPage - 2) { //
						startPage = totalPage - 4;
						endPage = totalPage;
					}

					if (startPage < 1) {
						startPage = 1;
					}

					if (endPage > totalPage) {
						endPage = totalPage;
					}
					
					buf.append("<div class='pagination pagination-sm'>");
					buf.append("<ul>");
					// for prev button
					buf.append("<li class='"+(currentPage<=1?"disabled":EMPTY_STRING) +"'><a href='"+
					            (currentPage>1? TagUtils.createActionURL(pageContext.getRequest(), getAction(), currentPage-1, map, orderMap) :"#")+"'>&laquo; Prev</a></li>" );
					//for each page number
					for (long i = startPage; i <= endPage; i++) {
						buf.append("<li class='"+(currentPage==i?"active":EMPTY_STRING)+"'><a href='"+
					                TagUtils.createActionURL(pageContext.getRequest(), getAction(), i, map, orderMap)+"'>"+ i +"</a></li>");
					}
					
					//for next button
					buf.append("<li class='"+ (currentPage>=totalPage?"disabled":EMPTY_STRING)+ "'><a href='"+ 
					            (currentPage < totalPage ? TagUtils.createActionURL(pageContext.getRequest(), getAction(), currentPage+1, map, orderMap) : "#") +"'>Next &raquo;</a></li>");
					buf.append("</ul>");
					buf.append("</div>");
					
					//??
					orderMap.put("isFetchAll", "true");
				} else {  // top dropdown list paging

					buf.append("<div class='form-inline'><select name='selectPages' class='input-medium' style='height:25px; margin-top:3px; margin-left:20px' onchange='javascript:document.location.href=(this.options[this.selectedIndex].value)'>");
				    
					for (int i = 1; i <= totalPage; i++) {
						String uri = TagUtils.createActionURL(pageContext.getRequest(), getAction(), i, map, orderMap);
						buf.append("<option value='"+uri+"' "+(currentPage==i?"selected":"")+">");
						buf.append(ResourceBundleUtil.getMessage("fbs.common.ui.label.pagination.page") +" "+ i +" of " +totalPage);
						buf.append("</option>\n");
					}
					//first page
					String firstPageUrl = TagUtils.createActionURL(pageContext.getRequest(), getAction(), 1, map, orderMap);				
					buf.append("</select> &nbsp; <input type='button' class='bt-sm' style='margin-left:8px;' value='" +
					           ResourceBundleUtil.getMessage("fbs.common.ui.label.pagination.first") + ONCLICK_CHANGESCREEN + firstPageUrl + CLOSE_BRACKET_WITH);

					if (currentPage > 1) {  //prev page
						String prevPageUrl = TagUtils.createActionURL(pageContext.getRequest(), getAction(), currentPage-1, map, orderMap);
						buf.append("<input type='button' class='bt-sm' style='margin-left:8px;' value='" +
						           ResourceBundleUtil.getMessage("fbs.common.ui.label.pagination.previous") + ONCLICK_CHANGESCREEN + prevPageUrl + CLOSE_BRACKET_WITH);
					}
				
					if (currentPage <totalPage) {  //next page
						String nextPageUrl = TagUtils.createActionURL(pageContext.getRequest(), getAction(), currentPage+1, map, orderMap);
						buf.append("<input type='button' class='bt-sm' style='margin-left:8px;' value='" +
						           ResourceBundleUtil.getMessage("fbs.common.ui.label.pagination.next") + ONCLICK_CHANGESCREEN + nextPageUrl + CLOSE_BRACKET_WITH);
					}
				    //last page
					String lastPageUrl = TagUtils.createActionURL(pageContext.getRequest(), getAction(), totalPage, map, orderMap);				
					buf.append("<input type='button' class='bt-sm' style='margin-left:8px;' value='" +
					           ResourceBundleUtil.getMessage("fbs.common.ui.label.pagination.last") + ONCLICK_CHANGESCREEN + lastPageUrl + CLOSE_BRACKET_WITH);
					
					orderMap.put("isFetchAll", "true");		//for show all button			
					String viewAllUrl = TagUtils.createActionURL(pageContext.getRequest(), getAction(), 1, map, orderMap);	
					
					if(!isLog && viewAll){
						buf.append("<input type='button' class='bt-sm' style='margin-left:8px;' value='" +
						           ResourceBundleUtil.getMessage("fbs.common.ui.label.pagination.show.all") + ONCLICK_CHANGESCREEN + viewAllUrl + CLOSE_BRACKET_WITH);
					}
					
				}

			}
			
			
			if(!isDoPaging()){
				if(exportExcel){
					buf.append("<input type='button' id='btnExportExcel' class='bt-sm' style='margin-left:8px;' value='"+ResourceBundleUtil.getMessage("fbs.common.ui.label.exportExcel") +
							"' onClick=\"javascript:exportData('excel','"+tableId+"');\">");
				}			
				if(exportPDF){
					buf.append("<input type='button' id='btnExportPDF' class='bt-sm' style='margin-left:8px;' value='"+ResourceBundleUtil.getMessage("fbs.common.ui.label.exportPDF") +
							"' onClick=\"javascript:exportData('pdf','"+tableId+"');\">");
				}			
			}
			
			//to display total records
			String messageCode ="fbs.common.ui.label.pagination.record";
			if(isAjax()){
				messageCode += colSize > 1 ? "s" : "";
				
				buf.append("<div class=\"pull-right\"><span class=\"nobr\" style=\"margin-right:20px;\" ><b><i id='"+actionArray[1]+"_totalRecordsDisplay'>"+ colSize +"</i></b>&nbsp;" 
				            + ResourceBundleUtil.getMessage(messageCode)+"</span></div><br/>");			
			}else {
				messageCode += dataContext.getTotalRecordFound() > 1 ? "s" : "";
				if(isDoPaging()){
					
					buf.append("<div class=\"pull-left\" style=\"margin-left:25px; padding-top:20px;\"><span class=\"nowrap\"><b><i>"+dataContext.getTotalRecordFound()+"</i></b>&nbsp;"+
					           ResourceBundleUtil.getMessage(messageCode) +"</span></div><br/>");
				}else {
					buf.append("<div class=\"pull-right\"><span class=\"nobr\" style=\"margin-right:20px;\"><b><i>" +dataContext.getTotalRecordFound()+"</i></b>&nbsp;" + 
				               ResourceBundleUtil.getMessage(messageCode)+"</span></div><br/><br/>");
				}
			}
			
			write(buf.toString());	
		}
				
		return BodyTagSupport.EVAL_PAGE;
	}
	
	
	
	public Map<String, String> processMap(Map map){
		Map<String, String> result = new HashMap<String, String>();
		
		Iterator iterator = result.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value;
			
			try {
				Object obj = result.get(key);

				if (obj instanceof DateTime) {
					value = DateUtil.convertDateToDateString((DateTime) obj);
					
				} else {
					
					if (obj instanceof Long) {
						value = String.valueOf(((Long) obj).longValue());
						
					} else {
						if (obj instanceof Object[]) {
							continue;
						}
						if (obj == null) {
							continue;
						}
						value = obj.toString();
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				continue;
			}
			result.put(key, value == null ? "" : value);
		}
		
		return result;
	}
	
	
}














