package in.deloitte.poc.ecommerce.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.deloitte.poc.ecommerce.dto.OrderDto;
import in.deloitte.poc.ecommerce.model.Orders;
import in.deloitte.poc.ecommerce.model.Product;
import in.deloitte.poc.ecommerce.service.OrderService;
import in.deloitte.poc.ecommerce.service.ProductService;

@RestController
@RequestMapping("/orders")
public class OrdersRestController {
	
	@Autowired private ProductService productService;
	@Autowired private OrderService orderService;

	@GetMapping("/getprod")
	public ResponseEntity<?> getProducts(){
		Optional<Product> prod = null;
		try {
			prod = productService.getProductById(1L);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ResponseEntity.ok(prod.isPresent()?prod.get():null);
	}

    @PostMapping("/createOrder")
    public ResponseEntity<?> getNewOrders(@RequestBody OrderDto orders) {
        Orders neworders = null;
        try{
        	neworders = orderService.saveOrders(orders);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(neworders);
    }

    @GetMapping("/getOrderByOrderId")
    public ResponseEntity<?> getOrderByOrderId(@RequestParam Long ord_id) {
    	Orders order = null;
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {
    		order = orderService.getOrderDetailsByOrderId(ord_id);
    		
    		map.put("Order", order);
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    	}
    	return ResponseEntity.ok(map);
    }
    
    @GetMapping("/getOrderByUserId")
    public ResponseEntity<?> getOrderByUserId(@RequestParam Long user_id) {
    	List<Orders> order = null;
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	try {
    		order = orderService.getOrderDetailsByUserId(user_id);
    		map.put("Order", order);
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    	}
    	return ResponseEntity.ok(map);
    }
    
    @PutMapping("/updateOrderStatus")
    public ResponseEntity<?> getUpdateOrderStatus(@RequestParam Long order_id,
    											  @RequestParam String status){
    	try {
    		orderService.updateOrderStatus(order_id, status);
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
		return ResponseEntity.ok("OK");
    	
    }
    
}

