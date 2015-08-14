package sg.com.fbs.validator.web.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Frank Xu $
 * @Created 10:28:55 am 13 Jul, 2015 $
 * Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.
 * 
 * Map of cookies.
 */
public class CookieMap implements Map{

	private Map<String, String> cookies = new HashMap<String, String>();
	
	private HttpServletResponse response;
	
	public CookieMap(final Cookie[] aCookies, final HttpServletResponse aResponse) {
		this.response = aResponse;
		if(aCookies == null){
			return;
		}
		
		for (Cookie cookie : aCookies) {
			this.cookies.put(cookie.getName(), cookie.getValue());
		}
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
		return cookies.entrySet();
	}

	@Override
	public Object get(Object key) {
		return cookies.get((String)key);
	}

	@Override
	public boolean isEmpty() {
		return cookies.isEmpty();
	}

	@Override
	public Set keySet() {
		return cookies.keySet();
	}

	@Override
	public Object put(Object key, Object value) {
		String sKey = key.toString();
		String sValue = value.toString();
		
		Cookie cookie = new Cookie(sKey, sValue);
		cookie.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(cookie);
		
		return cookies.put((String)key, (String)value);
	}

	@Override
	public void putAll(Map m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object remove(Object key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return cookies.size();
	}

	@Override
	public Collection values() {
		return cookies.values();
	}

}
