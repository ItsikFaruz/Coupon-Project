package app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import app.core.exception.CouponSystemException;
import app.core.filters.AdminFilter;
import app.core.filters.CompanyFilter;
import app.core.filters.CustomerFilter;
import app.core.jwt.util.JwtUtil;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableScheduling
@SpringBootApplication
public class CouponSystemSpringItsikApplication {

	public static void main(String[] args) throws CouponSystemException {
		SpringApplication.run(CouponSystemSpringItsikApplication.class, args);
	}

	// register and map the filter in a filter container
	@Bean
	public FilterRegistrationBean<AdminFilter> adminmFilter(JwtUtil jwtUtil) {
		// container for registering filters
		FilterRegistrationBean<AdminFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		// register our filter - set the filter
		AdminFilter adminFilter = new AdminFilter(jwtUtil);
		filterRegistrationBean.setFilter(adminFilter);
		// map the filter to a url pattern
		filterRegistrationBean.addUrlPatterns("/ADMINISTRATOR/*");
		// return the FilterRegistrationBean
		return filterRegistrationBean;
	}

	// register and map the filter in a filter container
	@Bean
	public FilterRegistrationBean<CompanyFilter> companyFilter(JwtUtil jwtUtil) {
		// container for registering filters
		FilterRegistrationBean<CompanyFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		// register our filter - set the filter
		CompanyFilter companyFilter = new CompanyFilter(jwtUtil);
		filterRegistrationBean.setFilter(companyFilter);
		// map the filter to a url pattern
		filterRegistrationBean.addUrlPatterns("/COMPANY/*");
		// return the FilterRegistrationBean
		return filterRegistrationBean;
	}

	// register and map the filter in a filter container
	@Bean
	public FilterRegistrationBean<CustomerFilter> customerFilter(JwtUtil jwtUtil) {
		// container for registering filters
		FilterRegistrationBean<CustomerFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		// register our filter - set the filter
		CustomerFilter customerFilter = new CustomerFilter(jwtUtil);
		filterRegistrationBean.setFilter(customerFilter);
		// map the filter to a url pattern
		filterRegistrationBean.addUrlPatterns("/CUSTOMER/*");
		// return the FilterRegistrationBean
		return filterRegistrationBean;
	}

}
