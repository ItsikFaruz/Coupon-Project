package app.core.servicies;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exception.CouponSystemException;

@Component
@Transactional
public class AdminService extends ClientService {

	@Value("${admin.email}")
	private String email;
	@Value("${admin.password}")
	private String password;

	@Override
	public boolean login(String email, String password) {
		return email.equals(this.email) && password.equals(this.password);

	}

	/**
	 * add company
	 * 
	 * @param company
	 * @throws CouponSystemException
	 */
	public int addCompany(Company company) throws CouponSystemException {
		if (company.getId() != 0) {
			throw new CouponSystemException("addCompany faild - coupon: cant set id for coupon ");
		}

		if (companyRepo.existsByEmailOrName(company.getEmail(), company.getName()))
			throw new CouponSystemException("addCompany faild - email or name already exist");
		else
			companyRepo.save(company);
		Company companyFromDb = companyRepo.findCompanyByEmailAndPassword(company.getEmail(), company.getPassword());
		return companyFromDb.getId();
	}

	public void updateCompany(Company company) throws CouponSystemException {
		Optional<Company> opt = companyRepo.findById(company.getId());
		if (opt.isPresent()) {
			Company companyFromDb = opt.get();
			if (!companyFromDb.getName().equals(company.getName())) {
				throw new CouponSystemException("updateCompany faild - can't chang company name");
			}
			companyFromDb.setEmail(company.getEmail());
			companyFromDb.setPassword(company.getPassword());
		} else
			throw new CouponSystemException("updateCompany faild - company not exist");
	}

	public void deleteCompany(int companyId) throws CouponSystemException {
		Optional<Company> opt = companyRepo.findById(companyId);
		if (opt.isPresent()) {
			companyRepo.deleteById(companyId);
			System.out.println("Company " + companyId + " deleted");
		} else
			throw new CouponSystemException("deleteCompany faild - company " + companyId + " not exist");
	}

	public ArrayList<Company> getAllCompany() {
		ArrayList<Company> allCompany = (ArrayList<Company>) companyRepo.findAll();
		return allCompany;
	}

	public Company getOneCompany(int companyId) throws CouponSystemException {
		Optional<Company> opt = companyRepo.findById(companyId);
		if (opt.isPresent()) {
			return opt.get();
		} else
			throw new CouponSystemException("getOneCustomer faild - customer " + companyId + " not exist");
	}

	public int addCustomer(Customer customer) throws CouponSystemException {
		if (!customerRepo.existsByEmail(customer.getEmail())) {
			customerRepo.save(customer);
			Customer customerFromDb = customerRepo.findCustomerByEmailAndPassword(customer.getEmail(),
					customer.getPassword());
			return customerFromDb.getId();
		} else
			throw new CouponSystemException("addCustomer faild - this email already exist ");
	}

	public int updateCustomer(Customer customer) throws CouponSystemException {
		Optional<Customer> opt = customerRepo.findById(customer.getId());
		if (opt.isPresent()) {
			customerRepo.save(customer);
			return customer.getId();
		} else
			throw new CouponSystemException("updateCustomer faild - customer not exist");
	}

	public void deleteCustomer(int customerId) throws CouponSystemException {
		Optional<Customer> opt = customerRepo.findById(customerId);
		if (opt.isPresent()) {
			customerRepo.deleteById(customerId);
			System.out.println("Customer " + customerId + " deleted");
		} else
			throw new CouponSystemException("deleteCustomer faild - customer " + customerId + " not exist");
	}

	public ArrayList<Customer> getAllCustomer() {
		ArrayList<Customer> allCustomer = (ArrayList<Customer>) customerRepo.findAll();
		return allCustomer;
	}

	public Customer getOneCustomer(int customerId) throws CouponSystemException {
		Optional<Customer> opt = customerRepo.findById(customerId);
		if (opt.isPresent()) {
			return opt.get();
		} else
			throw new CouponSystemException("getOneCustomer faild - customer " + customerId + " not exist");
	}

	public void deleteExpiredCoupon() {
		couponRepo.deleteByEndDateBefore(LocalDate.now());
	}

}
