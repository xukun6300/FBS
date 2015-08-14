package sg.com.fbs.core.techinfra.persistence.dao;

import java.io.Serializable;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.mapping.Table;
import org.hibernate.type.Type;
import org.joda.time.DateTime;

import sg.com.fbs.core.techinfra.util.DateUtil;
import sg.com.fbs.core.techinfra.util.StringUtil;


public class SequenceGeneratorYYYYMMDD extends SequenceGenerator{

	protected Logger logger = Logger.getLogger(SequenceGeneratorYYYYMMDD.class);
	
	public String sequenceName;
	public String parameters;
	
	@Override
	public void configure(Type type, Properties params, Dialect dialect) throws MappingException{
		
		this.sequenceName = getString(SEQUENCE, params, "hibernate_sequence");
		this.parameters = params.getProperty(PARAMETERS);
		String schema = params.getProperty(SCHEMA);
		String catalog = params.getProperty(CATALOG);

		if (sequenceName.indexOf('.') < 0) {
			sequenceName = Table.qualify(catalog, schema, sequenceName);
		}

	}
	
	@Override
	public Serializable generate(SessionImplementor session, Object obj) throws HibernateException{
		
		String result = DateUtil.convertDateToDateString(new DateTime(),"yyMMddHH");
		try {
			result = result + StringUtil.leftPad(String.valueOf(getNextSequenceForSequenceName(sequenceName)),'0', 8);
		} catch (Exception e) {
			throw new HibernateException(e.getMessage(), e.getCause());
		}

		return new Long(result);
	}
	
	public String getString(String property, Properties properties, String defaultValue){
		String propValue = properties.getProperty(property);
		return property == null ? defaultValue : propValue;
	}
	
	public long getNextSequenceForSequenceName(String strSequenceName){
		return new SequenceDao().getNextKey(strSequenceName);
	}
}


















