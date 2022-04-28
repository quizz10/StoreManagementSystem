//package se.iths.storemanagementsystem.controller;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import javax.inject.Singleton;
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Singleton
//    DataSource dataSource;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user1").password("{noop}password").roles("USER")
//                .and()
//                .withUser("admin").roles("ADMIN").password("{noop}password");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/api/v1/store/**").hasRole("ADMIN")
//                .antMatchers("/api/v1/employee/**").hasRole("ADMIN")
//                .antMatchers("/api/v1/shoppingcart/**").hasRole("ADMIN")
//                .antMatchers("/api/v1/department/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/api/v1/item/**").hasRole("ADMIN")
//                .antMatchers("/api/v1/customer/**").hasRole("ADMIN").and().formLogin();
//    }
//
//    /*
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/auth/login*").anonymous()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/auth/login")
//                .defaultSuccessUrl("/home", true)
//                .failureUrl("/auth/login?error=true")
//                .and()
//                .logout().logoutSuccessUrl("/auth/login");*/
//
//}
