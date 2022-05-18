package app.core.servicies;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.exception.CouponSystemException;

@Service
@Transactional

public class CompanyService extends ClientService {

	@Override
	public boolean login(String email, String password) {
		if (companyRepo.existsByEmailAndPassword(email, password)) {

			return true;
		} else {
			return false;
		}
	}

	public int addCoupon(Coupon coupon, int companyId) throws CouponSystemException {

		Company company = null;
		Optional<Company> opt = companyRepo.findById(companyId);
		if (opt.isPresent()) {
			company = opt.get();
		}
		if (coupon.getId() != 0) {
			throw new CouponSystemException("addCoupon faild - coupon: cant set id for coupon ");
		}

		if (couponRepo.existsByTitleAndCompanyId(coupon.getTitle(), companyId)) {
			throw new CouponSystemException("addCoupon faild - coupon: " + coupon.getTitle() + " is alredy exists  ");
		}

		company.addCoupon(coupon);
		Coupon couponFromDb = couponRepo.findByTitleAndCompanyId(coupon.getTitle(), companyId);
		return couponFromDb.getId();

	}

	public void updatCoupon(Coupon coupon, int companyId) throws CouponSystemException {
		if (couponRepo.existsByIdAndCompanyId(coupon.getId(), companyId)) {

			Optional<Coupon> opt = couponRepo.findById(coupon.getId());
			if (opt.isPresent()) {
				Coupon couponFromDb = opt.get();
				couponFromDb.setAmount(coupon.getAmount());
				couponFromDb.setCategory(coupon.getCategory());
				couponFromDb.setDescription(coupon.getDescription());
				couponFromDb.setEndDate(coupon.getEndDate());
				couponFromDb.setImage(coupon.getImage());
				couponFromDb.setStartDate(coupon.getStartDate());
				couponFromDb.setTitle(coupon.getTitle());
				couponFromDb.setPrice(coupon.getPrice());
			}

		} else
			throw new CouponSystemException(
					" updatCoupon faild - coupon " + coupon.getId() + " not exists for this company");
	}

	public void deleteCoupon(int couponId, int companyId) throws CouponSystemException {
		if (couponRepo.existsByIdAndCompanyId(couponId, companyId)) {
			couponRepo.deleteById(couponId);

		} else
			throw new CouponSystemException(
					" deleteCoupon faild - coupon " + couponId + " not exists for this company ");
	}

	public List<Coupon> getAllCompanyCoupon(int companyId) {
		return couponRepo.findByCompanyId(companyId);
	}

	public Coupon getCoupon(int couponId) {
		return couponRepo.findCouponById(couponId);
	}

	public List<Coupon> getAllCompanyCouponByCategory(Category category, int companyId) {
		return couponRepo.findByCompanyIdAndCategory(companyId, category);
	}

	public List<Coupon> getAllCompanyCouponUpToMaxPrice(double maxPrice, int companyId) {
		return couponRepo.findByCompanyIdAndPriceLessThan(companyId, maxPrice);
	}

	public Company getCompanyDetials(int companyId) throws CouponSystemException {
		Optional<Company> opt = companyRepo.findById(companyId);
		if (opt.isPresent())
			return opt.get();
		else
			throw new CouponSystemException("getCompanyDetials faild - company- " + companyId + " not exist.");
	}

}