package sg.com.fbs.model.system.persistence.query;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.PropertyProjection;
import org.hibernate.type.DoubleType;
import org.hibernate.type.Type;

public class WeightedAverageProjection extends PropertyProjection {

	private static final long serialVersionUID = -7551748127480713327L;
	
	@Getter
	@Setter
	private String denominatorProp;
	
	public WeightedAverageProjection(String prop, String denominatorProp){
		super(prop,false);
		this.denominatorProp = denominatorProp;
	}
	
	public Type[] getTypes(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		return new Type[]{ new DoubleType() };
	}

}
