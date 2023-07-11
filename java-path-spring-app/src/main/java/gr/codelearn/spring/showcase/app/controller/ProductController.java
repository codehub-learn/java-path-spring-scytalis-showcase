package gr.codelearn.spring.showcase.app.controller;

import gr.codelearn.spring.showcase.app.domain.Product;
import gr.codelearn.spring.showcase.app.mapper.BaseMapper;
import gr.codelearn.spring.showcase.app.mapper.ProductMapper;
import gr.codelearn.spring.showcase.app.service.BaseService;
import gr.codelearn.spring.showcase.app.service.ProductService;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import gr.codelearn.spring.showcase.app.transfer.resource.ProductResource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController extends BaseController<Product, ProductResource> {
	private final ProductService productService;
	private final ProductMapper productMapper;

	@Override
	public BaseService<Product, Long> getBaseService() {
		return productService;
	}

	@Override
	public BaseMapper<Product, ProductResource> getMapper() {
		return productMapper;
	}

	@PostMapping(params = "categoryId")
	public ResponseEntity<ApiResponse<ProductResource>> create(@Valid @RequestBody final ProductResource resource,
															   @RequestParam Long categoryId) {
		//@formatter:off
		return new ResponseEntity<>(
				ApiResponse.<ProductResource>builder()
						   .data(getMapper().toResource(productService.create(getMapper().toDomain(resource), categoryId)))
						   .build(),
				getNoCacheHeaders(),
				HttpStatus.CREATED);
		//@formatter:on
	}

	@GetMapping(params = "serial")
	public ResponseEntity<ApiResponse<ProductResource>> findBySerial(@RequestParam String serial) {
		final ProductResource productResource = getMapper().toResource(productService.findBySerial(serial));
		return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
	}
}
