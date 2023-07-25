package gr.codelearn.spring.showcase.app.client;

import gr.codelearn.spring.showcase.app.domain.Customer;
import gr.codelearn.spring.showcase.app.domain.Order;
import gr.codelearn.spring.showcase.app.domain.Product;
import gr.codelearn.spring.showcase.app.service.CustomerService;
import gr.codelearn.spring.showcase.app.service.OrderService;
import gr.codelearn.spring.showcase.app.service.ProductService;
import gr.codelearn.spring.showcase.app.transfer.PurchasesPerCustomerCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class AsyncClientImpl implements AsyncClient {
	private final CustomerService customerService;
	private final ProductService productService;
	private final OrderService orderService;

	@Override
	@Async("asyncExecutor")
	public CompletableFuture<List<Customer>> retrieveCustomers() throws InterruptedException {
		return CompletableFuture.completedFuture(customerService.findAll());
	}

	@Override
	@Async("asyncExecutor")
	public CompletableFuture<List<Product>> retrieveProducts() throws InterruptedException {
		return CompletableFuture.completedFuture(productService.findAll());
	}

	@Override
	@Async("asyncExecutor")
	public CompletableFuture<List<Order>> retrieveOrders() throws InterruptedException {
		return CompletableFuture.completedFuture(orderService.findAll());
	}

	@Override
	@Async("asyncExecutor")
	public CompletableFuture<List<PurchasesPerCustomerCategoryDto>> findTotalNumberAndCostOfPurchasesPerCustomerCategory()
			throws InterruptedException {
		return CompletableFuture.completedFuture(orderService.findTotalNumberAndCostOfPurchasesPerCustomerCategory());
	}
}
