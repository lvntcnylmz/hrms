package hrms.core.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import hrms.business.concretes.UserManager;
import hrms.core.security.AppUserDetails;
import hrms.entities.dtos.UserLoginDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserManager userManager;
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserManager userManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userManager = userManager;
        this.jwtUtil = jwtUtil;
        //setFilterProcessesUrl(JwtProperties.LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        UserLoginDto userLoginDto;
        try {
            userLoginDto = new ObjectMapper().readValue(request.getInputStream(), UserLoginDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AppUserDetails appUserDetails = (AppUserDetails) this.userManager.loadUserByUsername(userLoginDto.getEmail());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                appUserDetails.getUsername(),
                null,
                new ArrayList<>());

        return this.authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        AppUserDetails appUserDetails = (AppUserDetails) authResult.getPrincipal();

        String token = this.jwtUtil.createToken(appUserDetails);

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
    }
}