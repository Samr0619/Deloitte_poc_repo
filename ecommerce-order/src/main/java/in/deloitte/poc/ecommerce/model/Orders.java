package in.deloitte.poc.ecommerce.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;





@Entity
@Table(name = "ORDERS")
public class Orders implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ID")
    private Long order_id;
	
	@Column(name = "USER_ID")
    private Long user_id;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;
	
	@Column(name = "STATUS")
    private String status;

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}



	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

   
	
   
}