package recipes.securityConfigurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import recipes.securityUser.UserDetailsServiceIml;


@EnableWebSecurity
public class WebSecurityConfigurerImpl extends WebSecurityConfigurerAdapter
{
	@Autowired
	UserDetailsService serviceImpl;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(serviceImpl)
				.passwordEncoder(getEncoder());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.mvcMatchers(HttpMethod.POST,"/api/recipe/new")
				.authenticated()
				.mvcMatchers(HttpMethod.GET, "/api/recipe/*")
				.authenticated()
				.mvcMatchers(HttpMethod.DELETE, "/api/recipe/*")
				.authenticated()
				.mvcMatchers(HttpMethod.PUT, "/api/recipe/*")
				.authenticated()
				.mvcMatchers(HttpMethod.POST,"/api/register")
				.permitAll()
				.and().httpBasic()
				.and()
				.csrf().disable().headers().frameOptions().disable();
	}


	@Bean
	public PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
}
