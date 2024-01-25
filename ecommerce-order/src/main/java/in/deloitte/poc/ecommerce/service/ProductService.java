package in.deloitte.poc.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.deloitte.poc.ecommerce.model.Product;
import in.deloitte.poc.ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired private ProductRepository productRepository;
	
	public Optional<Product> getProductById(Long prodId)throws Exception{
		return productRepository.findById(prodId);
	}
	

	
}
