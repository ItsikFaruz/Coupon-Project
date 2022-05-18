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
import app.core.entities.Customer;
import app.core.exception.CouponSystemException;
import app.core.servicies.AdminService;

@CrossOrigin
@RestController
@RequestMapping("/ADMINISTRATOR")
public class AdminController {

	@Autowired
	AdminService adminService;

	@PostMapping("/add_company")
	public int addCompany(@RequestBody Company company, @RequestHeader String token) {

		try {

			int id = adminService.addCompany(company);
			return (id);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

		}

	}

	@PutMapping("/update_company")
	public int updateCompany(@RequestBody Company company, @RequestHeader String token) {

		try {
			adminService.updateCompany(company);
			return company.getId();

		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping(path = "/delete_company/{companyId}")
	public int deleteCompany(@PathVariable int companyId, @RequestHeader String token) {
		try {
			adminService.deleteCompany(companyId);
			return (companyId);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping("/get_all_companies")
	public List<Company> getAllCompany(@RequestHeader String token) {
		try {
			List<Company> companies = adminService.getAllCompany();
			return (companies);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/get_one_company/{companyId}")
	public Company getOneCompany(@PathVariable int companyId, @RequestHeader String token) {
		try {
			return (adminService.getOneCompany(companyId));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@PostMapping("/add_customer")
	public int addCustomer(@RequestBody Customer customer, @RequestHeader String token) {

		try {
			int id = adminService.addCustomer(customer);
			return (id);
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

		}

	}

	@PutMapping("/update_customer")
	public int updateCustomer(@RequestBody Customer customer, @RequestHeader String token) {
		try {
			int id = adminService.updateCustomer(customer);
			return (id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@DeleteMapping("/delete_customer/{customerId}")
	public int deleteCustomer(@PathVariable int customerId, @RequestHeader String token) {
		try {
			adminService.deleteCustomer(customerId);
			return (customerId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/get_all_customer")
	public List<Customer> getAllCustomer(@RequestHeader String token) {
		try {
			List<Customer> customers = adminService.getAllCustomer();
			return (customers);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/get_one_customer/{customerId}")
	public Customer getOneCustomer(@PathVariable int customerId, @RequestHeader String token) {
		try {
			return (adminService.getOneCustomer(customerId));
		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

}
