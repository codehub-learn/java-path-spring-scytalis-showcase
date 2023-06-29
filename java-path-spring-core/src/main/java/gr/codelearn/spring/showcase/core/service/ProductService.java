package gr.codelearn.spring.showcase.core.service;

import gr.codelearn.spring.showcase.core.domain.Product;

import java.util.List;

public interface ProductService {
	List<Product> getProducts();

	Product getProduct(String serial);
}
