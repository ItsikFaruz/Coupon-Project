package app.core.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Coupon;
import app.core.entities.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	public boolean existsByEmailAndPassword(String email, String password);

	public boolean existsByEmail(String email);

	public Customer findCustomerByEmailAndPassword(String email, String password);

	List<Coupon> findCouponsById(int customerId);

}
