package in.deloitte.poc.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.deloitte.poc.ecommerce.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>{
	
	@Modifying
	@Query("update Orders ord set ord.status = ?1 where ord.order_id = ?2")
	public void updateOrderStatusById(String status,Long order_id) throws Exception;

}
