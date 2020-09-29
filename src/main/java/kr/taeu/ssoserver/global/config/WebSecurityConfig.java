package kr.taeu.ssoserver.global.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
//                .exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
            .formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login-page")
                .permitAll()
                .and()
            .csrf()
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/user*"))
                .disable()
            .logout()
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
//                        "/v2/api-docs",
//                        "/configuration/ui",
//                        "/swagger-resources/**",
//                        "/configuration/security",
//                        "/swagger-ui.html",
//                        "/webjars/**",
                        "/h2-console/**",
                        "/js/**");
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("test").password("test").roles("USER").and()
                .withUser("lteawoo").password("1234").roles("USER");
    }
}
