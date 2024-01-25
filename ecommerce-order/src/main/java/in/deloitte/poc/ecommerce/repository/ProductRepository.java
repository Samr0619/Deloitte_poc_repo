package in.deloitte.poc.ecommerce.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import in.deloitte.poc.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
