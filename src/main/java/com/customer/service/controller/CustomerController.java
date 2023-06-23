package com.customer.service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.customer.service.dao.CustomerRepository;
import com.customer.service.dao.PurchaseGoodsRepository;
import com.customer.service.model.Customer;
import com.customer.service.model.Product;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	PurchaseGoodsRepository purchaseGoodsRepository;

	@Value("${productservice}")
	public String productServiceUrl;

	 @Autowired
	 private RestTemplate restTemplate;

	/*
	 * @Bean public RestTemplate restTemplate() { return new RestTemplate(); }
	 */

	@PostMapping("/saveCustomer")
	public Customer saveCustomer(@RequestBody Customer customer) {

		System.out.println("customer obj:" + customer.toString());
		return customerRepository.save(customer);
	}

	@GetMapping("/getProductDetails")
	public Product getProductDetails(@RequestParam Integer productId) {
		// RestTemplate resttemplate=new RestTemplate();
		Product product = null;
		String url = productServiceUrl + productId;
		System.out.println("url:" + url);
		ResponseEntity<Product> responseEntity = restTemplate.getForEntity(url, Product.class);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {

			// Process the response from the other microservice
			product = responseEntity.getBody();
			System.out.println("Product name" + product.productname + "Product ID:" + product.productID + "Price:"
					+ product.price);

		}

		return product;
	}


	@GetMapping("/getAllCustomers")
	public List<Customer> getAllCustomers() {

		return customerRepository.findAll();
	}

	@GetMapping("/getCustomerByID/{customerID}")
	public Optional<Customer> findCustomerByID(@PathVariable Integer customerID) {
		return customerRepository.findById(customerID);
	}

	@GetMapping("/getCustomerByIDByParam")
	public Optional<Customer> findCustomerByIDByparam(@RequestParam Integer customerID) {
		return customerRepository.findById(customerID);
	}

}
