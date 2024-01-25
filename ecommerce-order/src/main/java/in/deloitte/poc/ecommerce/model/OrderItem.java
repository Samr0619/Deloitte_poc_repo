package in.deloitte.poc.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ITEM_ID")
	private Long order_item_id;
	
	 @ManyToOne
	 @JoinColumn(name = "ORDER_ID")
	 @JsonBackReference
	 private Orders order;

	 @ManyToOne
	 @JoinColumn(name = "PRODUCT_ID")
	 private Product product;

	 @Column(name = "QUANTITY")
	 private int quantity;

	

	public Long getOrder_item_id() {
		return order_item_id;
	}

	public void setOrder_item_id(Long order_item_id) {
		this.order_item_id = order_item_id;
	}



	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	 
	 
}
