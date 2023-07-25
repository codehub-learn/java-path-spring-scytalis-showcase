package gr.codelearn.spring.showcase.app.controller.async;

import gr.codelearn.spring.showcase.app.base.BaseComponent;
import gr.codelearn.spring.showcase.app.client.AsyncClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class AsyncController extends BaseComponent {
	private final AsyncClient asyncClient;

	@GetMapping("/async")
	public void testAsync() throws InterruptedException, ExecutionException {
		logger.info("Testing asynchronous calls, starting....");
		var customersFuture = asyncClient.retrieveCustomers();
		var productsFuture = asyncClient.retrieveProducts();
		var ordersFuture = asyncClient.retrieveOrders();
		var reportFuture = asyncClient.findTotalNumberAndCostOfPurchasesPerCustomerCategory();

		CompletableFuture.allOf(customersFuture, productsFuture, ordersFuture, reportFuture);

		logger.debug("{} customers, {} products, {} orders retrieved.", customersFuture.get().size(),
					 productsFuture.get().size(), ordersFuture.get().size());

		logger.info("REPORT: Displaying total number of purchases and corresponding cost per customer category");
		reportFuture.get().forEach(
				i -> logger.info("{} was purchased {} times costing {}.", i.getCategory(), i.getPurchases(),
								 i.getCost()));

		logger.info("Testing asynchronous calls, finished");
	}
}
