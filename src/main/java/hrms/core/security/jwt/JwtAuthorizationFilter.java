package hrms.core.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import hrms.business.concretes.UserManager;
import hrms.core.security.AppUserDetails;
import hrms.dataAccess.abstracts.UserDao;
import hrms.entities.concretes.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserDao userDao;
    private final UserManager userManager;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  UserDao userDao, UserManager userManager) {
        super(authenticationManager);
        this.userDao = userDao;
        this.userManager = userManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(JwtProperties.HEADER_STRING);

        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

//        AppUserDetails appUserDetails = (AppUserDetails) getAuthentication(request).getAuthorities();
//
//        response.addHeader(JwtProperties.HEADER_STRING,
//                JwtProperties.TOKEN_PREFIX + this.userManager.createToken(appUserDetails));

        Authentication authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "");

        String email = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
                .build()
                .verify(token)
                .getSubject();

        User user = this.userDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User could not found by email"));
        AppUserDetails appUserDetails = new AppUserDetails(user);
        return new UsernamePasswordAuthenticationToken(
                email, null, appUserDetails.getAuthorities());

    }
}
