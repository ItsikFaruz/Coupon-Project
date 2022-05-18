package app.core.servicies;

import org.springframework.beans.factory.annotation.Autowired;

import app.core.repos.CompanyRepo;
import app.core.repos.CouponRepo;
import app.core.repos.CustomerRepo;

public abstract class ClientService {

	@Autowired
	protected CompanyRepo companyRepo;
	@Autowired
	protected CustomerRepo customerRepo;
	@Autowired
	protected CouponRepo couponRepo;

	public abstract boolean login(String email, String password);

}
