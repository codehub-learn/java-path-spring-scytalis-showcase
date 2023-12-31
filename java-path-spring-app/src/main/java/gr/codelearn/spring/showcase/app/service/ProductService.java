package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.domain.Product;

public interface ProductService extends BaseService<Product, Long> {
	Product create(final Product product, Long categoryId);

	Product findBySerial(final String serial);
}
