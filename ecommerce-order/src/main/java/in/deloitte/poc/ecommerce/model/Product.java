package in.deloitte.poc.ecommerce.model;

import java.io.Serializable;

import jakarta.persistence.*;



@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROD_ID")
	private Long prod_id;
	
	@Column(name = "PROD_NAME")
    private String prod_name;

	@Column(name = "BRAND_NAME")
    private String brand_Name;

	@Column(name = "PRICE")
    private Double price;

	@Column(name = "PATH")
    private String path;

	@Column(name = "IMAGENAME")
    private String imageName;

	public Long getProd_id() {
		return prod_id;
	}

	public void setProd_id(Long prod_id) {
		this.prod_id = prod_id;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public String getBrand_Name() {
		return brand_Name;
	}

	public void setBrand_Name(String brand_Name) {
		this.brand_Name = brand_Name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}






}
