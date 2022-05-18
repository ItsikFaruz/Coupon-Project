//package app.core.test;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import app.core.entities.Company;
//import app.core.entities.Coupon;
//import app.core.entities.Coupon.Category;
//import app.core.entities.Customer;
//import app.core.exception.CouponSystemException;
//import app.core.loginManager.Clients.ClientType;
//import app.core.loginManager.LoginManager;
//import app.core.servicies.AdminService;
//import app.core.servicies.CompanyService;
//import app.core.servicies.CustomerService;
//
//
//
//@Component
//public class TestAll {
//	
//	@Autowired
//	LoginManager loginManager;
//
////==========test==========	
//	@PostConstruct
//	public void test() throws CouponSystemException {
//		System.out.println("post");
//		doAdminTest();
//		doCompanyTest();
//  	    doCustomerTest();
//
//	}
//	
//	//*******************************ADMIN TEST************************************
//	public void doAdminTest() throws CouponSystemException {
//		
//		System.out.println("\n\n\n====================== ADMIN TEST =======================");
//		AdminService af = (AdminService) loginManager.login("admin@admin.com", "admin",ClientType.ADMINISTRATOR);
////		System.out.println(adminService.getAllCompany());
//		System.out.println("\n-------------------add company----------------------------");
//		// ==================add company==============================
//		Company company1 = new Company(0, "aaa", "aaa", "aaa" , null);
//		Company company2 = new Company(0, "bbb", "bbb", "bbb" , null);
//		Company company3 = new Company(0, "ccc", "ccc", "ccc" , null);
//		af.addCompany(company1);
//		System.out.println("added " + company1);
//		af.addCompany(company2);
//		System.out.println("added " + company2);
//		af.addCompany(company3);
//		System.out.println("added " + company3);
//		System.out.println("\n-----------------update company----------------------------");
//		// ==================update company===========================
//		company1.setEmail("@@@");
//		af.updateCompany(company1);
//		System.out.println("updated company: " + company1.getId() + " to: " + company1);
//		System.out.println("\n------------------delete company---------------------------");
//		// ==================delete company===========================
//		af.deleteCompany(company1.getId());
//		System.out.println("deleted company: " + company1.getId());
//		System.out.println("\n--------------getAll Companies-----------------------------");
//		// ==================get all company==========================
//		System.out.println("All Companies: ");
//		List<Company> allcompnies = new ArrayList<>();
//		allcompnies = af.getAllCompany();
//		for (Company company : allcompnies) {
//			System.out.println(company);
//		}
//		System.out.println("\n-------------------get one company-------------------------");
//		// ==================get one company==========================
//		System.out.print("got one company : ");
//		System.out.println(af.getOneCompany(company2.getId()));
//		System.out.println("\n---------------------add customers-------------------------");
//		// ===================add customers===========================
//		Customer customer1 = new Customer(0, "aaaa", "aaaa", "aaaa", "aaaa", null);
//   		Customer customer2 = new Customer(0, "bbbb", "bbbb", "bbbb", "bbbb", null);
//   		Customer customer3 = new Customer(0, "cccc", "cccc", "cccc", "cccc", null);
//		af.addCustomer(customer1);
//		System.out.println("added " + customer1);
//		af.addCustomer(customer2);
//		System.out.println("added " + customer2);
//		af.addCustomer(customer3);
//		System.out.println("added " + customer3);
//		System.out.println("\n-------------------update customer-------------------------");
//		// ====================update customer=========================
//		customer1.setEmail("@@");
//		customer1.setFirstName("dd");
//		af.updateCustomer(customer1);
//		System.out.println("updated Customer: " + customer1.getId() + " to " + customer1);
//		System.out.println("\n--------------------delete customer------------------------");
//		// ====================delete customer=========================
//		af.deleteCustomer(customer1.getId());
//		System.out.println("deleted Customer: id = " + customer1.getId());
//		System.out.println("\n------------------get all customer-------------------------");
//		// ====================get all customer========================
//		System.out.println("List of all customers below: ");
//		List<Customer> allCustomers = new ArrayList<>();
//		allCustomers = af.getAllCustomer();
//		for (Customer customer : allCustomers) {
//			System.out.println(customer);
//		}
//		System.out.println("\n-----------------get one customer--------------------------");
//		// ====================get one customer========================
//		System.out.print("get one customer : ");
//		System.out.println(af.getOneCustomer(customer2.getId()));
//		System.out.println("\n-----------------------------------------------------------");
//	}
//
//
//	//*********************************COMPANY TEST************************************
//	public void doCompanyTest() throws CouponSystemException {
//		System.out.println("\n\n\n====================== COMPANY TEST =======================");
//		CompanyService company = (CompanyService) loginManager.login("bbb", "bbb",ClientType.COMPANY);
//		System.out.println("\n----------------------add coupon---------------------------");
//		// ==================add coupon==============================
//		Coupon coupon1 = new Coupon(0, Category.FOOD, "xxx", "xxx",LocalDate.of(2020, 05, 05) ,LocalDate.of(2022, 06, 06) ,5, 10D, "xxx", company.getCompany(2) , null);
//		Coupon coupon2 = new Coupon(0, Category.TRAVEL, "yyy", "yyy",LocalDate.of(2020, 05, 05) ,LocalDate.of(2022, 06, 06) ,5, 10D, "yyy", company.getCompany(2) , null);
//		Coupon coupon3 = new Coupon(0, Category.VACATION, "zzz", "zzz",LocalDate.of(2020, 05, 05) ,LocalDate.of(2022, 06, 06) ,5, 30D, "zzz", company.getCompany(2) , null);
//		company.addCoupon(coupon1);
//		System.out.println("added " + coupon1);
//		company.addCoupon(coupon2);
//		System.out.println("added " + coupon2);
//		company.addCoupon(coupon3);
//		System.out.println("added " + coupon3);
//		System.out.println("\n---------------------update coupon-------------------------");	
//		// ==================update coupon============================
//		coupon1.setTitle("@@@@");
//		coupon1.setImage("@@@@");
//		coupon1.setDescription("@@@@");
//		company.updatCoupon(coupon1);
//		System.out.println("updated Coupon: " + coupon1.getId() + " to " + coupon1);
//		System.out.println("\n-----------------------------------------------------------");
//		// ==================delete company===========================
//		company.deleteCoupon(coupon1.getId());
//		System.out.println("deleted Coupon: " + coupon1.getId());
//		System.out.println("------------------company coupons----------------------------");
//		// ==================company coupons==========================
//		System.out.println("All company coupons: ");
//		List<Coupon> allCoupon = new ArrayList<>();
//		allCoupon = company.getAllCompanyCoupon();
//		for (Coupon coupon : allCoupon) {
//			System.out.println(coupon);
//		}
//		System.out.println("\n------------company coupons by category--------------------");
//		// ==================company coupons by category==============
//		System.out.println("All company coupons by specific category:");
//		List<Coupon> allCouponByCategory = new ArrayList<>();
//		allCouponByCategory = company.getAllCompanyCouponByCategory(Category.TRAVEL);
//		for (Coupon coupon : allCouponByCategory) {
//			System.out.println(coupon);
//		}
//		System.out.println("\n-----------------------------------------------------------");
//		// =============company coupons by up to a max price===========
//		double maxPrice = 20D;
//		System.out.println("all company coupons up to: " + maxPrice + " Dollar");
//		List<Coupon> allCouponUpToMaxPrice = company.getAllCompanyCouponUpToMaxPrice(maxPrice);
//		for (Coupon coupon : allCouponUpToMaxPrice) {
//			System.out.println(coupon);
//		}
//		System.out.println("\n------------------Company Details--------------------------");
//		// ========================Company Details=====================
//		System.out.println("Company Details:");
//		System.out.println(company.getCompanyDetials());
//	}
//			
//	
//	
//	//*******************************CUSTOMER TEST************************************	
//	public void doCustomerTest() throws CouponSystemException {
//		System.out.println("\n\n\n====================== CUSTOMER TEST ======================");
//		CustomerService customer = (CustomerService) loginManager.login("bbbb", "bbbb",ClientType.CUSTOMER);
//		Coupon coupon2 = new Coupon();
//		coupon2.setId(2);
//		Coupon coupon3 = new Coupon();
//		coupon3.setId(3);
//		customer.purchaseCoupon(coupon2);
//		System.out.println("customer: " + customer.getCustomerId() + " purchased " + coupon2);
//		customer.purchaseCoupon(coupon3);
//		System.out.println("customer: " + customer.getCustomerId() + " purchased " + coupon3);
//		System.out.println("\n--------------all customer coupons-------------------------");
//		// ========================all customer coupons================
//		System.out.println("all customer :" + customer.getCustomerId() + " id coupon:");
//		ArrayList<Coupon> allCustomerCoupon = new ArrayList<>();
//		allCustomerCoupon =  (ArrayList<Coupon>) customer.getAllCoupon();
//		for (Coupon coupon : allCustomerCoupon) {
//			System.out.println(coupon);
//		}
//		System.out.println("\n-----------------------------------------------------------");
//		// ============all customer coupon purchased by category=======
//		Category category = Category.TRAVEL;
//		System.out.println("all customer: " + customer.getCustomerId() + " id coupon by "+ category + " category: ");
//		ArrayList<Coupon> allCustomerCouponByCategory = new ArrayList<>();
//		allCustomerCouponByCategory = (ArrayList<Coupon>) customer.getAllCouponByCategory(category);
//		for (Coupon coupon : allCustomerCouponByCategory) {
//			System.out.println(coupon);
//		}
//		System.out.println("\n------------customer coupons up to max price---------------");
//		// =============all customer coupons up to max price===========
//		double maxPrice = 15;
//		System.out.println("all customer: " + customer.getCustomerId() + " id below "+ maxPrice + " Dollar: ");
//		ArrayList<Coupon> allCustomerCouponBelowMaxPrice = new ArrayList<>();
//		allCustomerCouponBelowMaxPrice = (ArrayList<Coupon>) customer.getAllCouponUpToMaxPrice(maxPrice);
//		for (Coupon coupon : allCustomerCouponBelowMaxPrice) {
//			System.out.println(coupon);
//		}
//		System.out.println("\n-----------------------------------------------------------");
//		// ======================== customer details:==================
//		System.out.println("customer details:");
//		System.out.println(customer.getCustomerDetails());
//		System.out.println("-----------------------------------------------------------\n\n\n");
//		
//		
//	}
//
//}
