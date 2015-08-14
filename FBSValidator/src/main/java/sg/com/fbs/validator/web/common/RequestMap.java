package sg.com.fbs.validator.web.common;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Frank Xu $
 * @Created 10:17:01 am 13 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 * 
 * Request scope map.
 */
public class RequestMap implements Map{

	private HttpServletRequest request;
	
	public RequestMap(final HttpServletRequest request){
		this.request = request;
	}
	
	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsKey(Object key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsValue(Object value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set entrySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object get(Object key) {
		return request.getAttribute((String)key);
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set keySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object put(Object key, Object value) {
		request.setAttribute((String)key, value);
		return null;
	}

	@Override
	public void putAll(Map m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object remove(Object key) {
		request.removeAttribute((String)key);
		return null;
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection values() {
		throw new UnsupportedOperationException();
	}

}












































