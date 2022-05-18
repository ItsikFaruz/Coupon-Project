package app.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.exception.CouponSystemException;
import app.core.jwt.util.JwtUtil;
import app.core.servicies.CompanyService;

@CrossOrigin
@RestController
@RequestMapping("/COMPANY")
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/add_coupon")
	public int addCoupon(@RequestBody Coupon coupon, @RequestHeader String token) {
		try {
			int id = companyService.addCoupon(coupon, jwtUtil.extractClient(token).clientId);
			return (id);

		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

		}

	}

	@PutMapping("/update_coupon")
	public int updateCompany(@RequestBody Coupon coupon, @RequestHeader String token) {

		try {
			companyService.updatCoupon(coupon, jwtUtil.extractClient(token).clientId);
			return (coupon.getId());
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping("/delete_coupon/{couponId}")
	public int deleteCoupon(@PathVariable int couponId, @RequestHeader String token) {
		try {
			companyService.deleteCoupon(couponId, jwtUtil.extractClient(token).clientId);
			return (couponId);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping("/get_company_coupon")
	public List<Coupon> getAllCompanyCoupon(@RequestHeader String token) {
		try {
			List<Coupon> coupons = companyService.getAllCompanyCoupon(jwtUtil.extractClient(token).clientId);
			return (coupons);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/get_company_coupon_by_category/{category}")
	public List<Coupon> getAllCompanyCouponByCategory(@PathVariable Category category, @RequestHeader String token) {
		try {
			List<Coupon> coupons = companyService.getAllCompanyCouponByCategory(category,
					jwtUtil.extractClient(token).clientId);
			return (coupons);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/get_company_coupon_upToMax_price/{maxPrice}")
	public List<Coupon> getAllCompanyCouponUpToMaxPrice(@PathVariable double maxPrice, @RequestHeader String token) {
		try {
			List<Coupon> coupons = companyService.getAllCompanyCouponUpToMaxPrice(maxPrice,
					jwtUtil.extractClient(token).clientId);
			return (coupons);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/get_company_details")
	public Company getOneCompanyDetails(@RequestHeader String token) {
		try {
			return (companyService.getCompanyDetials(jwtUtil.extractClient(token).clientId));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

}
