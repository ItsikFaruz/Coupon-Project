package app.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.Coupon;
import app.core.servicies.CompanyService;
import app.core.servicies.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/LAYOUT")
public class LayoutController {

	@Autowired
	CustomerService customerService;
	@Autowired
	CompanyService companyService;

	@GetMapping("/get_all_products")
	public List<Coupon> getAllProducts(@RequestHeader String token) {
		try {
			return this.customerService.getAllProducts();

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping("/get_one_coupon/{couponId}")
	public Coupon getCoupon(@PathVariable int couponId, @RequestHeader String token) {
		try {

			return companyService.getCoupon(couponId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
