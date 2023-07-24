package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.domain.Category;
import gr.codelearn.spring.showcase.app.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "categories", keyGenerator = "customKeyGenerator")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {
	private final CategoryRepository categoryRepository;

	@Override
	public JpaRepository<Category, Long> getRepository() {
		return categoryRepository;
	}

	public Category getReference(final Long id) {
		return categoryRepository.getReferenceById(id);
	}

	@Override
	@Cacheable
	public Category findByDescription(final String description) {
		return categoryRepository.findByDescription(description);
	}

	@Override
	@Cacheable
	public List<Category> findAll() {
		logger.trace("Retrieving all items.");
		return categoryRepository.findAll();
	}

	@Caching(evict = {@CacheEvict(value = "categories", allEntries = true)})
	@Scheduled(cron = "0 0/2 * * * ?")
	public void clearEntireCache() {
		logger.trace("Cache 'categories' was successfully evicted.");
	}
}
