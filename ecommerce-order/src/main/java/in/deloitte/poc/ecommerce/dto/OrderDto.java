package in.deloitte.poc.ecommerce.dto;

import java.util.List;

import in.deloitte.poc.ecommerce.model.OrderStatus;

public class OrderDto {
	
	private Long user_id;
	private String order_status;
	private List<Item> items;
	
	public static class Item{
		private Long prod_id;
		private Integer quantity;
		
		public Long getProd_id() {
			return prod_id;
		}
		public void setProd_id(Long prod_id) {
			this.prod_id = prod_id;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	
	
}
