package xyz.kmahyyg.eshopdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.kmahyyg.eshopdemo.security.MbtsAuthenticationProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MbtsAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().ignoringAntMatchers("/api/**")
                .and()
                .authorizeRequests().antMatchers("/", "/index", "/show/user/login", "/static/**", "/show/user/register", "/api/user/register", "/api/user/login", "/api/user/logout", "/show/user/logout", "/error", "/api/user/captcha").permitAll()
                .and()
                .authorizeRequests().antMatchers("/show/admin/**").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/show/manage/**").hasRole("SHOPOWNER")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/show/user/login").loginProcessingUrl("/api/user/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/error")
                .and()
                .logout().logoutUrl("/show/user/logout")
                .deleteCookies("JSESSIONID")
                .and()
                .rememberMe().rememberMeCookieName("rememberMe").rememberMeParameter("rememberme")
                .and()
                .sessionManagement().maximumSessions(2)
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}

