package hrms.core.security.jwt;

public class JwtProperties {

    public static final String SECRET = "my-app-secret";
    public static final int EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

}
