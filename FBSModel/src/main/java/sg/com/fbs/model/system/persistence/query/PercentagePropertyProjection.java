package sg.com.fbs.model.system.persistence.query;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.PropertyProjection;
import org.hibernate.type.DoubleType;
import org.hibernate.type.Type;

public class PercentagePropertyProjection extends PropertyProjection{

	private static final long serialVersionUID = -566519086174038725L;

	private String denominatorProp;

	private Projection proj = null;

	private Projection denoProj = null;
	
	public String getDenominatorProp() {
		return denominatorProp;
	}

	public void setDenominatorProp(String denominatorProp) {
		this.denominatorProp = denominatorProp;
	}

	public Projection getProj() {
		return proj;
	}

	public void setProj(Projection proj) {
		this.proj = proj;
	}

	public Projection getDenoProj() {
		return denoProj;
	}

	public void setDenoProj(Projection denoProj) {
		this.denoProj = denoProj;
	}

	public PercentagePropertyProjection(String prop, String denominatorProp){
		super(prop, false);
		this.denominatorProp = denominatorProp;
	}
	
	public PercentagePropertyProjection(Projection proj, Projection denoProj){
		super(null);
		this.proj = proj;
		this.denoProj = denoProj;
	}
	
	public Type[] getTypes(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException{
		return new Type[] { new DoubleType() };
	}
	
	public String toSqlString(Criteria criteria, int loc, CriteriaQuery criteriaQuery) throws HibernateException{
		String numerator = "";
		String denominator = "";
		
		if(proj!=null && denoProj!=null){
			numerator = getSqlStringWithoutAlias(proj.toSqlString(criteria, loc, criteriaQuery));
			denominator = getSqlStringWithoutAlias(denoProj.toSqlString(criteria, loc, criteriaQuery));
		}else{
			numerator = criteriaQuery.getColumn(criteria, getPropertyName());
			denominator = criteriaQuery.getColumn(criteria, getDenominatorProp());
		}
		
		StringBuffer sb = new StringBuffer("decode( ");
		sb.append(denominator)
		  .append(", 0, 0, (")
		  .append(numerator)
		  .append("/")
		  .append(denominator)
		  .append(" * 100)");
	
		sb.append(") as y")
	   	  .append(loc)
		  .append('_')
		  .toString();
		return sb.toString();
		
	}
	
	private String getSqlStringWithoutAlias(String temp){
		if(temp!=null && temp.length()>0){
			int asIx = temp.indexOf("as");
			
			temp = temp.substring(0, asIx);
		}
		return temp;
	}
	
	public String toGroupSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		if (!isGrouped()) {
			return super.toGroupSqlString(criteria, criteriaQuery);
		} else {
			return null;
		}
	}
	
}

















