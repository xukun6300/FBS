package sg.com.fbs.validator.web.common;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

/**
 * @Author Frank Xu $
 * @Created 10:23:49 am 13 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 * 
 * Session scope map.
 */
public class SessionMap implements Map{

	private HttpSession session;
	
	public SessionMap(final HttpSession session){
		this.session = session;
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
		return session.getAttribute((String)key);
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
		session.setAttribute((String)key, value);
		return null;
	}

	@Override
	public void putAll(Map m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object remove(Object key) {
		session.removeAttribute((String)key);
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
