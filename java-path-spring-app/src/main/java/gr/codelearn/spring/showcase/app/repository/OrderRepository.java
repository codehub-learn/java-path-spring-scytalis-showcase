package gr.codelearn.spring.showcase.app.repository;

import gr.codelearn.spring.showcase.app.domain.Order;
import gr.codelearn.spring.showcase.app.transfer.KeyValue;
import gr.codelearn.spring.showcase.app.transfer.PurchasesPerCustomerCategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("""
				select distinct o from Order o
				join fetch o.customer
				join fetch o.orderItems oi
				join fetch oi.product
				where o.id = :id
			""")
	Optional<Order> getLazy(Long id);

	@Query("""
				select distinct o from Order o
				join fetch o.customer
				join fetch o.orderItems oi
				join fetch oi.product
			""")
	Optional<List<Order>> findAllLazy();

	@Query("""
			select new gr.codelearn.spring.showcase.app.transfer.KeyValue(concat(c.lastname, ' ', c.firstname), avg(o.cost))
			from Order o join o.customer c
			group by concat(c.lastname, ' ', c.firstname)
			order by concat(c.lastname, ' ', c.firstname)
			""")
	List<KeyValue<String, BigDecimal>> findAverageOrderCostPerCustomer();

	@Query(nativeQuery = true)
	List<PurchasesPerCustomerCategoryDto> findTotalNumberAndCostOfPurchasesPerCustomerCategory();
}
