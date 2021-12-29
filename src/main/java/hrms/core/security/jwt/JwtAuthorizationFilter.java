package hrms.core.security.jwt;

import hrms.business.concretes.UserManager;
import hrms.core.security.AppUserDetails;
import hrms.dataAccess.abstracts.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserManager userManager;
    private final JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  UserManager userManager, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.userManager = userManager;
        this.jwtUtil = jwtUtil;
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

        Authentication authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .substring(JwtProperties.TOKEN_PREFIX.length());

        String email = this.jwtUtil.verifyToken(token);

        AppUserDetails appUserDetails = (AppUserDetails) this.userManager.loadUserByUsername(email);

        return new UsernamePasswordAuthenticationToken(
                appUserDetails,
                null,
                appUserDetails.getAuthorities());
    }

}
