package com.pazl.authorization.danliduoli;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.pazl.authorization.entity.Book;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
@Component
public class RuleCache {
	private LoadingCache<String, Book> loadingCache;

	public RuleCache() {
		loadingCache = Caffeine.newBuilder()
				.expireAfterWrite(10, TimeUnit.SECONDS)
				.initialCapacity(2)
				.maximumSize(10)
				.recordStats()
				.build(new CacheLoader<String, Book>() {
					@Override
					public Book load(String key) throws Exception {
						return null;
					}
				});
	}

	public boolean put(String key, Book Book) {
		loadingCache.put(key, Book);

		return Boolean.TRUE;
	}

	public Book get(String key) {
		try {
			return loadingCache.get(key);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean clear(String key) {
		loadingCache.invalidate(key);

		return Boolean.TRUE;
	}
}