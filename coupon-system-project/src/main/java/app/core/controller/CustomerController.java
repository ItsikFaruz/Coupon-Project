package app.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.entities.Customer;
import app.core.exception.CouponSystemException;
import app.core.jwt.util.JwtUtil;
import app.core.servicies.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/CUSTOMER")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/purchase_coupon/{couponId}")
	public int purchaseCoupon(@PathVariable int couponId, @RequestHeader String token) {
		try {
			customerService.purchaseCouponFromDb(couponId, jwtUtil.extractClient(token).clientId);
			return (couponId);

		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

		}

	}

	@GetMapping("/get_all_customer_coupon")
	public List<Coupon> getAllCustomerCoupon(@RequestHeader String token) {
		try {
			System.out.println(customerService.getAllCoupon(jwtUtil.extractClient(token).clientId));
			return customerService.getAllCoupon(jwtUtil.extractClient(token).clientId);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@GetMapping("/get_all_customer_coupon_by_category/{category}")
	public List<Coupon> getAllCustomerCouponByCategory(@PathVariable Category category, @RequestHeader String token) {
		try {
			return customerService.getAllCouponByCategory(category, jwtUtil.extractClient(token).clientId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/get_all_customer_coupon_up_to_max_price/{maxPrice}")
	public List<Coupon> getAllCustomerCouponUpToMaxPrice(@PathVariable double maxPrice, @RequestHeader String token) {
		try {
			return customerService.getAllCouponUpToMaxPrice(maxPrice, jwtUtil.extractClient(token).clientId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/get_customer_details")
	public Customer getOneCompanyDetails(@RequestHeader String token) {
		try {
			return (customerService.getCustomerDetails(jwtUtil.extractClient(token).clientId));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

}
