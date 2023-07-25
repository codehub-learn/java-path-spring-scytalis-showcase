package gr.codelearn.spring.showcase.app.config;

import gr.codelearn.spring.showcase.app.base.BaseComponent;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

public class EhCacheListener extends BaseComponent implements CacheEventListener<Object, Object> {
	public void onEvent(CacheEvent<?, ?> cacheEvent) {
		logger.info("Key: {}, EventType: {}, Old value: {}, New value: {}", cacheEvent.getKey(), cacheEvent.getType(),
					cacheEvent.getOldValue(), cacheEvent.getNewValue());
	}

}
