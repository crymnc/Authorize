package com.experiment.authorize.config;

import com.experiment.authorize.config.web.CustomAccessDeniedHandler;
import com.experiment.authorize.config.web.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final DataSource dataSource;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    public SpringSecurityConfig(RestAuthenticationEntryPoint restAuthenticationEntryPoint, DataSource dataSource) {
        super();
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.dataSource = dataSource;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder())
                .usersByUsernameQuery("select username,password,active from \"user\" u where u.username = ?")
                .authoritiesByUsernameQuery("select u.username ,r.name from \"user\" u join user_role ur on u.id = ur.user_id join \"role\" r on ur.role_id =r.id where u.username =?");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .antMatcher("/api/**").authorizeRequests()
                .antMatchers("/api/user**").hasAuthority("ADMIN")
                .antMatchers("/api/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).accessDeniedHandler(new CustomAccessDeniedHandler());


    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return (new BCryptPasswordEncoder(11));
    }

}
