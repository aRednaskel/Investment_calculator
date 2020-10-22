package pl.fintech.challenge1.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.fintech.challenge1.backend.domain.investment.DepositFrequency;
import pl.fintech.challenge1.backend.domain.investment.Investment;
import pl.fintech.challenge1.backend.domain.investment.InvestmentService;
import pl.fintech.challenge1.backend.domain.investment.Type;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
