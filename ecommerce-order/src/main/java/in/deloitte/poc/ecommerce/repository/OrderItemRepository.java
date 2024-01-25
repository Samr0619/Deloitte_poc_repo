package in.deloitte.poc.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.deloitte.poc.ecommerce.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
