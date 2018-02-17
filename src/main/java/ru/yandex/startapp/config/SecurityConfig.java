package ru.yandex.startapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import ru.yandex.startapp.auth.AuthenticationFilter;
import ru.yandex.startapp.service.MasterService;
import ru.yandex.startapp.service.UserService;

@Configuration
@EnableWebSecurity
@ComponentScan("ru.yandex.startapp.auth")
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private AuthenticationFilter authenticationFilter;	
	
	/*@Autowired
    private DataSource dataSource;*/
	
	private UserDetailsService userDetailsService;
	private List<UserDetails> users;
	
	@Autowired
	private UserService userDao;
	
	@Autowired
	private MasterService masterDao;
		  	 
	public SecurityConfig() {
		super(true);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
				
		http.csrf().disable()
        .exceptionHandling().and()
        .anonymous().and()
        .servletApi();

		http.authorizeRequests()
		.antMatchers("/api/auth").permitAll()
        .antMatchers(HttpMethod.GET, "/tasklist").hasAnyAuthority("ADMIN","MASTER")
        .anyRequest().permitAll();  

		/*http.formLogin().defaultSuccessUrl("http://localhost:4200", true);*/
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
	}	
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
		users = new ArrayList<UserDetails>();
		System.out.println("KuKuUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU!");
		
		List<ru.yandex.startapp.domain.User> usersList = userDao.listUser();
		
		
       for(int i=0;i<usersList.size();i++) {
    	   auth.inMemoryAuthentication()
           .withUser(usersList.get(i).getUsername())
           .password(usersList.get(i).getPassword())
           .authorities(usersList.get(i).getRole());
		}
		
    }
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
