// package hrms.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.http.SessionCreationPolicy;

// @EnableWebSecurity
// public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


//     // @Override
//     // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//     //     auth.inMemoryAuthentication()
//     //         .withUser("user").password("password").roles("USER")
//     //         .and()
//     //         .withUser("admin").password("password").roles("ADMIN");
//     // }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {

//         http.httpBasic()
//             .and()
//             .authorizeRequests()
//                 .antMatchers(HttpMethod.POST, "/employers/add").authenticated()
//             .and()
//             .authorizeRequests()
//                 .anyRequest().permitAll();

//         http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//     }
// }
