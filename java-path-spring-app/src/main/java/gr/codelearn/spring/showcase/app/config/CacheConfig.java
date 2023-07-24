package gr.codelearn.spring.showcase.app.config;

import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig implements CachingConfigurer {
	/* Java configuration declaring two caches
	@Bean
	public CacheManager getCacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		Cache categories = new ConcurrentMapCache("categories");
		Cache products = new ConcurrentMapCache("products");
		cacheManager.setCaches(List.of(categories, products));
		return cacheManager;
	}
	*/
	@Bean("customKeyGenerator")
	public KeyGenerator getCacheKeyGenerator() {
		return new CustomCacheKeyGenerator();
	}
}
