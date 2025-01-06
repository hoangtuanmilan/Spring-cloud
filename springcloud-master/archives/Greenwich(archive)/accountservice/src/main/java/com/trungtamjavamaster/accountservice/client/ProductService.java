package com.trungtamjavamaster.accountservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trungtamjavamaster.accountservice.model.ProductDTO;

@FeignClient(name = "product-service")
public interface ProductService {

	@GetMapping(value = "/api/product/{id}")
	ProductDTO get(@PathVariable(name = "id") int id);

	@GetMapping(value = "/api/products")
	List<ProductDTO> getAll();
}
