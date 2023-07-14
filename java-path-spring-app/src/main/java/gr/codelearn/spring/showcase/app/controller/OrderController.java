package gr.codelearn.spring.showcase.app.controller;

import gr.codelearn.spring.showcase.app.domain.Order;
import gr.codelearn.spring.showcase.app.domain.chat.MessageType;
import gr.codelearn.spring.showcase.app.mapper.BaseMapper;
import gr.codelearn.spring.showcase.app.mapper.OrderMapper;
import gr.codelearn.spring.showcase.app.service.BaseService;
import gr.codelearn.spring.showcase.app.service.OrderService;
import gr.codelearn.spring.showcase.app.transfer.ApiResponse;
import gr.codelearn.spring.showcase.app.transfer.ChatMessage;
import gr.codelearn.spring.showcase.app.transfer.KeyValue;
import gr.codelearn.spring.showcase.app.transfer.PurchasesPerCustomerCategoryDto;
import gr.codelearn.spring.showcase.app.transfer.resource.OrderResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController extends BaseController<Order, OrderResource> {
	private final OrderService orderService;
	private final OrderMapper orderMapper;
	private final SimpMessagingTemplate simpMessagingTemplate;

	@Override
	public BaseService<Order, Long> getBaseService() {
		return orderService;
	}

	@Override
	public BaseMapper<Order, OrderResource> getMapper() {
		return orderMapper;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<OrderResource>> get(@PathVariable("id") final Long id) {
		return ResponseEntity.ok(
				ApiResponse.<OrderResource>builder().data(getMapper().toResource(orderService.getLazy(id))).build());
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<OrderResource>>> findAll() {
		return ResponseEntity.ok(
				ApiResponse.<List<OrderResource>>builder().data(getMapper().toResources(orderService.findAllLazy()))
						   .build());
	}

	@GetMapping(headers = "action=totalNumberAndCostOfPurchasesPerCustomerCategory")
	public ResponseEntity<ApiResponse<List<PurchasesPerCustomerCategoryDto>>> findTotalNumberAndCostOfPurchasesPerCustomerCategory() {
		final List<PurchasesPerCustomerCategoryDto> purchasesPerCustomerCategoryList = orderService.findTotalNumberAndCostOfPurchasesPerCustomerCategory();
		return ResponseEntity.ok(
				ApiResponse.<List<PurchasesPerCustomerCategoryDto>>builder().data(purchasesPerCustomerCategoryList)
						   .build());
	}

	@GetMapping(headers = "action=averageOrderCostPerCustomer")
	public ResponseEntity<ApiResponse<List<KeyValue<String, BigDecimal>>>> findAverageOrderCostPerCustomerV1() {
		final var results = orderService.findAverageOrderCostPerCustomer();
		return ResponseEntity.ok(ApiResponse.<List<KeyValue<String, BigDecimal>>>builder().data(results).build());
	}

	@Scheduled(cron = "0/30 * * * * ?")
	public void pushCustomerStatistics() {
		//@formatter:off
		StringBuilder contentBuilder = new StringBuilder();
		orderService.findAverageOrderCostPerCustomer()
					.forEach(i -> contentBuilder.append(i.key())
												.append(": €")
												.append(i.value())
												.append("<br/>"));

		simpMessagingTemplate.convertAndSend("/topic/public",
											   ChatMessage.builder()
														  .type(MessageType.CHAT)
														  .sender("server-side")
														  .content(contentBuilder.toString())
														  .build());
		//@formatter:on
	}
}
