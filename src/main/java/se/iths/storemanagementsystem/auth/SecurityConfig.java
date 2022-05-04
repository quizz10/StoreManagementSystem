package se.iths.storemanagementsystem.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final StoreUserDetailsService storeUserDetailsService;

    public SecurityConfig(StoreUserDetailsService storeUserDetailsService) {
        this.storeUserDetailsService = storeUserDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(storeUserDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/item/**", "/shoppingcart/**", "/department/**").denyAll()
                .antMatchers(HttpMethod.GET, "/item/**", "/shoppingcart/**").hasRole("USER")
                .antMatchers(HttpMethod.PATCH, "/shoppingcart/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/item/**").hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.DELETE, "/item/**").hasRole("EMPLOYEE")
                .and()
                .authorizeRequests()
                .antMatchers("/user/signup", "/user/setup").permitAll()
                .antMatchers("/changerole", "/adminportal").hasRole("ADMIN")
                .antMatchers("/user/admin/**").hasAnyRole("ADMIN", "EMPLOYEE")
                .antMatchers("/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}