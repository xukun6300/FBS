package sg.com.fbs.techinfra.cache.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Jul 18, 2016 2:57:00 PM $
 * 
 */
public class ContextObjectInputStream extends ObjectInputStream {

	ClassLoader mLoader;

	public ContextObjectInputStream(InputStream in, ClassLoader loader)
			throws IOException, SecurityException {
		super(in);
		mLoader = loader;
	}

	protected Class resolveClass(ObjectStreamClass v) throws IOException,
			ClassNotFoundException {
		if (mLoader == null)
			return super.resolveClass(v);
		else
			return Class.forName(v.getName(), true, mLoader);
	}
}
