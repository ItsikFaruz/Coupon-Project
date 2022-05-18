package app.core.loginManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import app.core.exception.CouponSystemException;
import app.core.loginManager.Clients.ClientType;
import app.core.servicies.AdminService;
import app.core.servicies.ClientService;
import app.core.servicies.CompanyService;
import app.core.servicies.CustomerService;

@Component

public class LoginManager {

	@Autowired
	private ApplicationContext ctx;

	public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException {

		String type = clientType.toString();
		if (type.equals("CUSTOMER")) {
			CustomerService customerService = ctx.getBean("customerService", CustomerService.class);
			if (customerService.login(email, password)) {
				return customerService;
			} else {
				throw new CouponSystemException("login faild - wrong password or email ");
			}

		}

		if (type.equals("COMPANY")) {
			CompanyService companyService = ctx.getBean("companyService", CompanyService.class);
			if (companyService.login(email, password)) {
				return companyService;
			} else {
				throw new CouponSystemException("login faild - wrong password or email ");
			}

		}

		if (type.equals("ADMINISTRATOR")) {
			AdminService adminService = ctx.getBean("adminService", AdminService.class);
			if (adminService.login(email, password)) {
				return adminService;
			} else {
				throw new CouponSystemException("login faild - wrong password or email ");
			}

		}

		return null;
	}
}