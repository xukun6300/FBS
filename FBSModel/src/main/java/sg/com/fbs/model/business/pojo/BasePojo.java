package sg.com.fbs.model.business.pojo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.joda.time.DateTime;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import sg.com.fbs.model.annotation.JsonSkipField;
import sg.com.fbs.model.domain.enumeration.ActiveStatusEnum;
import sg.com.fbs.model.system.security.User;




/**
 * @Author Frank Xu $
 * @Created 4:43:42 pm 24 Jun, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 */
@Audited
public class BasePojo implements BasePojoIF,Serializable {
	
	private static final long serialVersionUID = 4087447453462669285L;

	public static final String ID="id";
	
	public static final String CREATED_DATE ="createon";
	
	public static final String CREATED_BY ="createdby";
	
	public static final String MODIFIED_DATE ="modifyon";
	
	public static final String MODIFIED_BY ="modifiedby";
	
	public static final String ACT_IND ="activeStatus";
	
	public static final String YES ="Y";
	
	public static final String NO ="N";
	
	public static final String SEARCH_VALUE_NULL = "null";
	
	public static final String STR_EMPTY = "";
	
	public static final String ESCAPE_QUOTE = "\"";
	
	public static final String TXN_TYPE = "transactionType";
	
	public static final String DETAILS = "details";
	
	public static final String REF_ID = "refId";
	
	
	@Setter
	@Getter
	@XStreamOmitField
	protected long id;
	
	@Setter
	@Getter
	@XStreamOmitField
	protected long createdby = 0;
	
	@Setter
	@Getter
	@XStreamOmitField
	@NotAudited
	protected User createdbyUser;
	
	@Setter
	@Getter
	@XStreamOmitField
	protected DateTime createon = new DateTime();
	
	@Setter
	@Getter
	@XStreamOmitField
    protected Long modifiedby;
	
	@Setter
	@Getter
	@XStreamOmitField
	@NotAudited
	protected User modifiedbyUser;
	
	@Setter
	@Getter
	@XStreamOmitField
    protected DateTime modifyon = null;
	
	@Setter
	@Getter
	@XStreamOmitField
	protected String activeStatus = "Y";
	
	@Setter
	@Getter
	@XStreamOmitField
	@NotAudited
	protected String transactionType;
	
	@Setter
	@Getter
	@XStreamOmitField
	@NotAudited
	protected String details;
	
	@Setter
	@Getter
	@XStreamOmitField
	@NotAudited
	protected Long refId;
	
	@Setter
	@Getter
	@XStreamOmitField
	protected String triggeredFlag;
	
	
	
	public boolean isActive(){
		return ActiveStatusEnum.YES.toString().equals(activeStatus);
	}


	@Override
	public Map<String, String> getExportData() {
		return new LinkedHashMap<String, String>();
	}
	
	public Map<String, String> getAllProperties(){
		return getObjectProperties(this);
	}
	
	public static Map<String, String> getObjectProperties(final Object target){
		final Map<String, String> fieldValues = new HashMap<String, String>();
		final Class<? extends Object> entityClass = target.getClass();
		getObjectProperties(fieldValues, target, entityClass);
		
		return fieldValues;
	}
	
	private static void getObjectProperties(final Map<String, String> fieldValues, final Object target, final Class<? extends Object> entityClass){
		for (Field field : entityClass.getDeclaredFields()) {
			// bypass constants
			if(isAllUpperCase(field.getName())){
				continue;
			}
			// bypass serialVersionUID
			if("serialVersionUID".equals(field.getName())){
				continue;
			}
			
			if(field.isAnnotationPresent(JsonSkipField.class)){
				continue;
			}
			
			if(field.isAnnotationPresent(NotAudited.class)){
				continue;
			}
			
			try {
				final String fieldName = field.getName();
				final Object fieldValue = entityClass.getMethod("get"+Character.toUpperCase(fieldName.charAt(0))+fieldName.substring(1)).invoke(target);
				//ignore BasePojo sub-classes
				if(fieldValue instanceof BasePojo){
					continue;
				}
				
				if(fieldValue instanceof Collection<?>){
					continue;
				}
				
				fieldValues.put(fieldName, fieldValue==null?"":fieldValue.toString());
				
			} catch (Exception e) {
				//we really dont need to do anything... if we can't get the field value, we'll just ignore it
				continue;
			}
			
			if(entityClass!=BasePojo.class && entityClass.getSuperclass()!=null){
				getObjectProperties(fieldValues, target, entityClass.getSuperclass());
			}
		}
	}
	
	private static boolean isAllUpperCase(String string){
		if(string ==null || string.isEmpty()){
			return false;
		}
		
		for (final char character : string.toCharArray()) {
			//A_B will return true
			if(!Character.isUpperCase(character) && character!='_'){
				return false;
			}
		}
		return true;
	}
	
	
}














