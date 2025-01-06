package com.trungtamjavamaster.productservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trungtamjavamaster.productservice.model.ProductDTO;

@RestController
@RequestMapping("/api")
public class ProductController {

	@GetMapping("/products")
	public List<ProductDTO> getAll() {
		return Arrays.asList(new ProductDTO(1, "P1"), new ProductDTO(2, "P2"), new ProductDTO(3, "P3"));
	}

	@GetMapping("/product/{id}")
	public ProductDTO get(@PathVariable(name = "id") int id) {
		return new ProductDTO(id, "P1");
	}
}
