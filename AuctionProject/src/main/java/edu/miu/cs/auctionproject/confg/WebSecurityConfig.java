package edu.miu.cs.auctionproject.confg;
import edu.miu.cs.auctionproject.service.impl.AuctionUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    public WebSecurityConfig(AuctionUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .csrf().disable()
                .headers()
                .frameOptions().sameOrigin()
                .and()
                .authorizeRequests()
                .antMatchers("/resources/static/**", "/images/**", "/css/**", "/auction/public/**").permitAll()
//                .antMatchers("/", "/auction/public/search/results").permitAll()
                .antMatchers("/auction/secured/admin/**", "/resources/secured/admin/**", "/auction/admin/**", "/templates/secured/admin/**").hasRole("ADMIN")
                .antMatchers("/auction/secured/seller/**", "/resources/secured/seller/**","/seller/**").hasRole("SELLER")
                .antMatchers("/auction/secured/customer/**", "/resources/secured/customer/**","/auction/customer/**").hasRole("CUSTOMER")
//                .antMatchers("/auction/public/search/result/**").hasRole("CUSTOMER")
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/home/log_in")
                .defaultSuccessUrl("/product/getall")
                .failureUrl("/home/log_in?error")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/home/log_out"))
                .logoutSuccessUrl("/home/log_in?logout")
                .permitAll()
                .and()
                .exceptionHandling();
    }
}
