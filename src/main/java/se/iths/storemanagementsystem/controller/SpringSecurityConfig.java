//package se.iths.storemanagementsystem.controller;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import java.util.Arrays;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//
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
//                .antMatchers("/store/**").hasRole("ADMIN")
//                .antMatchers("/employee/**").hasRole("ADMIN")
//                .antMatchers("/shoppingcart/**").hasRole("ADMIN")
//                .antMatchers("/department/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/item/**").hasRole("ADMIN")
//                .antMatchers("/customer/**").hasAnyRole("ADMIN", "USER").and().formLogin();
//
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("*"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//}
////
////    /*
////        http
////                .csrf().disable()
////                .authorizeRequests()
////                .antMatchers("/auth/login*").anonymous()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/auth/login")
////                .defaultSuccessUrl("/home", true)
////                .failureUrl("/auth/login?error=true")
////                .and()
////                .logout().logoutSuccessUrl("/auth/login");*/
////
////}
