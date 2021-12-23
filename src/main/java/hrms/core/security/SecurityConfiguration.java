package hrms.core.security;

import hrms.business.concretes.UserManager;
import hrms.core.security.jwt.JwtAuthorizationFilter;
import hrms.dataAccess.abstracts.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserManager userManager;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfiguration(UserManager userManager,
                                 UserDao userDao,
                                 PasswordEncoder passwordEncoder) {
        this.userManager = userManager;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.userManager);
        provider.setPasswordEncoder(this.passwordEncoder.bCryptPasswordEncoder());

        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //.addFilter(new JwtAuthenticationFilter(authenticationManager(), userManager))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userDao, userManager))
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                .antMatchers("/api/jobSeekers/getAll").hasAnyRole("ADMIN")
                .antMatchers("/api/employers/getAll").hasAnyRole("EMPLOYER")
                .anyRequest().authenticated();
    }
}
