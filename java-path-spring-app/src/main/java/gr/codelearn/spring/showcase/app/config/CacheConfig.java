package gr.codelearn.spring.showcase.app.config;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.event.EventType;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;
import java.util.List;

@Configuration
@EnableCaching
public class CacheConfig implements CachingConfigurer {
	//	@Bean
	public SimpleCacheManager getCacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		Cache categories = new ConcurrentMapCache("categories");
		Cache products = new ConcurrentMapCache("products");
		cacheManager.setCaches(List.of(categories, products));
		return cacheManager;
	}

	@Bean("customKeyGenerator")
	public KeyGenerator getCacheKeyGenerator() {
		return new CustomCacheKeyGenerator();
	}

	@Bean
	@Primary
	public CacheManager getEhCacheManager() {
		CachingProvider provider = Caching.getCachingProvider();
		CacheManager cacheManager = provider.getCacheManager();

		//@formatter:off
		// Cache configuration
		CacheConfigurationBuilder<String, ?> configurationBuilder = CacheConfigurationBuilder
				.newCacheConfigurationBuilder(String.class, Object.class,
											  ResourcePoolsBuilder.heap(1000)
																  .offheap(25, MemoryUnit.MB))
				.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(5)));

		// Cache event listener configuration
		CacheEventListenerConfigurationBuilder cacheEventListener = CacheEventListenerConfigurationBuilder
				.newEventListenerConfiguration(new EhCacheListener(),
											   EventType.CREATED, EventType.EVICTED,
											   EventType.EXPIRED, EventType.REMOVED, EventType.UPDATED)
				.unordered()
				.asynchronous();

		// Create one or more cache
		cacheManager.createCache("products",
								 Eh107Configuration.fromEhcacheCacheConfiguration(configurationBuilder.withService(cacheEventListener)));
		cacheManager.createCache("categories",
								 Eh107Configuration.fromEhcacheCacheConfiguration(configurationBuilder.withService(cacheEventListener)));
		//@formatter:on

		return cacheManager;
	}
}
