package sg.com.fbs.model.system.persistence.query;

/**
 * @author Frank Xu
 *
 */
public interface ProjectionIF {
  
	public static final String AVERAGE="AVERAGE";
	
	public static final String WEIGHTED_AVERAGE = "WEIGHTEDAVERAGE";
	
	public static final String SUM = "SUM";
	
	public static final String MAX = "MAX";
	
	public static final String MIN = "MIN";
	
	public static final String ROWCOUNT = "ROWCOUNT";

	public static final String GROUP_BY = "GROUPBY";

	public static final String GROUP_BY_DAY = "GROUPBYDAY";

	public static final String GROUP_BY_WEEK = "GROUPBYWEEK";

	public static final String GROUP_BY_QUARTER = "GROUPBYQUARTER";

	public static final String GROUP_BY_MONTH = "GROUPBYMONTH";

	public static final String GROUP_BY_YEAR = "GROUPBYYEAR";
   
	public static final String SELECT_PROPERTY = "SELECTPROPERTY";
	
	public static final String RANK = "RANK";
	
	public static final String PERCENTAGE = "PERCENTAGE";
	
	public static final String LOG = "LOG";
	
	public static final String COUNT = "COUNT";
	
	public static final String DISTINCT = "DISTINCT";
	
	public boolean isWeekProjection();
	
	public boolean isMonthProjection();
	
	public boolean isYearProjection();
	
	public boolean isShowRowCount();
	
	public boolean isMax();
	
	public boolean isMin();
	
	public boolean isAverage();
	
	public boolean isCount();
	
	public boolean isWeightedAverage();
	
	public boolean isGroup();
	
	public boolean isSum();
	
	public boolean isRowCount();
	
	public boolean isSelectProperty();
	
	public boolean isType(String type);
	
	public String getProperty();
	
	public String getWithProperty();
	
	public String getType();
	
	public void setProperty(String property);
	
	public void setWithProperty(String property);
	
}



















