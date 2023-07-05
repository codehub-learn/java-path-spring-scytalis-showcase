package gr.codelearn.spring.showcase.app.bootstrap;

import gr.codelearn.spring.showcase.app.base.BaseComponent;
import gr.codelearn.spring.showcase.app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SampleRunner extends BaseComponent implements CommandLineRunner {
	private final OrderService orderService;

	@Override
	public void run(String... args) throws Exception {
		logger.info("Loading a sample order");
		var order = orderService.getLazy(1L);
		logger.info("{}", order);

		logger.info("Getting back our first report");
		orderService.findAverageOrderCostPerCustomer().forEach(kv -> logger.info("{}", kv));

		logger.info("REPORT: Displaying average order cost per customer");
		orderService.findAverageOrderCostPerCustomer().forEach(i -> logger.info("{}", i));

		logger.info("REPORT: Displaying total number of purchases and corresponding cost per customer category");
		orderService.findTotalNumberAndCostOfPurchasesPerCustomerCategory().forEach(
				i -> logger.info("{} was purchased {} times costing {}.", i.getCategory(), i.getPurchases(),
								 i.getCost()));
	}
}
