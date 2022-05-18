package app.core.dailyJob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import app.core.servicies.AdminService;

@Component
//@Transactional
public class CouponExpirationDailyJob {

//	@Autowired
//	protected CouponRepo couponRepo;

	@Autowired
	AdminService adminService;

	@Scheduled(cron = "1 0 0 * * * ")
	public void deleteExpiredCoupon() {
		adminService.deleteExpiredCoupon();
	}

}
