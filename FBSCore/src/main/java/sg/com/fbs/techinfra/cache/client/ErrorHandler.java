package sg.com.fbs.techinfra.cache.client;


/**Copyright (c) 2015 Financial & Budgeting System All Rights Reserved.

 * @Author Frank Xu Kun $
 * @Created Jul 18, 2016 2:57:48 PM $
 * 
 */
public interface ErrorHandler {

	/**
	 * Called for errors thrown during initialization.
	 */
	public void handleErrorOnInit(final MemcachedClient client,
			final Throwable error);

	/**
	 * Called for errors thrown during {@link MemcachedClient#get(String)} and
	 * related methods.
	 */
	public void handleErrorOnGet(final MemcachedClient client,
			final Throwable error, final String cacheKey);

	/**
	 * Called for errors thrown during {@link MemcachedClient#getMulti(String)}
	 * and related methods.
	 */
	public void handleErrorOnGet(final MemcachedClient client,
			final Throwable error, final String[] cacheKeys);

	/**
	 * Called for errors thrown during
	 * {@link MemcachedClient#set(String,Object)} and related methods.
	 */
	public void handleErrorOnSet(final MemcachedClient client,
			final Throwable error, final String cacheKey);

	/**
	 * Called for errors thrown during {@link MemcachedClient#delete(String)}
	 * and related methods.
	 */
	public void handleErrorOnDelete(final MemcachedClient client,
			final Throwable error, final String cacheKey);

	/**
	 * Called for errors thrown during {@link MemcachedClient#flushAll()} and
	 * related methods.
	 */
	public void handleErrorOnFlush(final MemcachedClient client,
			final Throwable error);

	/**
	 * Called for errors thrown during {@link MemcachedClient#stats()} and
	 * related methods.
	 */
	public void handleErrorOnStats(final MemcachedClient client,
			final Throwable error);

}
