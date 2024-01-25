package in.deloitte.poc.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.deloitte.poc.ecommerce.dto.OrderDto;
import in.deloitte.poc.ecommerce.model.OrderItem;
import in.deloitte.poc.ecommerce.model.Orders;
import in.deloitte.poc.ecommerce.model.Product;
import in.deloitte.poc.ecommerce.repository.OrderItemRepository;
import in.deloitte.poc.ecommerce.repository.OrdersRepository;

@Service
public class OrderService {


	@Autowired private OrdersRepository ordersRepository;
	@Autowired private OrderItemRepository orderItemRepository;
	@Autowired private ProductService productService;
	
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Orders saveOrders(OrderDto order) throws Exception {
		List<OrderItem> list = new ArrayList<OrderItem>();
		Orders ord = new Orders();
		ord.setUser_id(order.getUser_id());
		ord.setStatus(order.getOrder_status());
			
		ordersRepository.save(ord);
		
		for(OrderDto.Item items: order.getItems()) {
			OrderItem ord_item = new OrderItem();
			ord_item.setOrder(ord);
			
			Optional<Product> prod = productService.getProductById(items.getProd_id());
			ord_item.setProduct(prod.get());
			
			ord_item.setQuantity(items.getQuantity());
			
			orderItemRepository.save(ord_item);
			
			list.add(ord_item);
		}
		ord.setItems(list);
		return ord;
	}
	
	public Orders getOrderDetailsByOrderId(Long orderId)throws Exception{
		Optional<Orders> order = ordersRepository.findById(orderId);
	//	System.out.println(order.get().getItems().size());
		return order.get();
	}
	
	public List<Orders> getOrderDetailsByUserId(Long userId)throws Exception{
		Orders order = new Orders();
		order.setUser_id(userId);

		Example<Orders> example = Example.of(order);
		
		List<Orders> orders= ordersRepository.findAll(example);
	//	System.out.println(order.get().getItems().size());
		return orders;
	}
	
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void updateOrderStatus(Long user_id,String status)throws Exception{
		
		ordersRepository.updateOrderStatusById(status, user_id);
	}
}
