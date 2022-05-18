package app.core.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

	List<Coupon> deleteAllByCompanyId(int companyId);

	public boolean existsByTitleAndCompanyId(String title, int companyId);

	Coupon findByTitleAndCompanyId(String title, int companyId);

	public boolean existsByIdAndCompanyId(int couponId, int companyId);

	List<Coupon> findByCompanyId(int companyId);

	List<Coupon> findByCompanyIdAndCategory(int companyId, Category category);

	List<Coupon> findByCompanyIdAndPriceLessThan(int companyId, Double maxPrice);

	List<Coupon> findByCustomersId(int customerId);

	List<Coupon> findByCustomersIdAndCategory(int customerId, Category category);

	List<Coupon> findByCustomersIdAndPriceLessThan(int customerId, Double maxPrice);

	public void deleteByEndDateBefore(LocalDate time);

	Coupon findCouponById(int couponId);
}
