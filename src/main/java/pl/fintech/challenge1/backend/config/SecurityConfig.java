package pl.fintech.challenge1.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.fintech.challenge1.backend.domain.investment.InvestmentService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final InvestmentService investmentService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .and()
                .cors().disable()
                .csrf().disable();

        http.formLogin()
                .loginPage("/login")
                .permitAll()
                .and().logout()
                .permitAll();
    }
}
