package filter;

import javax.servlet.ServletException;
import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class AuthFilter extends HttpFilter {

    private static final String NAME_AUTH_COOKIE = "hh-auth";
    private static final int LENGTH_AUTH_COOKIE = 10;

    @Override
    protected void doFilter(final HttpServletRequest rq, final HttpServletResponse rs, final FilterChain chain)
            throws IOException, ServletException {

        Optional<Cookie[]> cookies = Optional.ofNullable(rq.getCookies());
        boolean authCookie = cookies
                .stream()
                .flatMap(Arrays::stream)
                .filter(o -> o.getName().equals(NAME_AUTH_COOKIE))
                .anyMatch(o -> o.getValue().length() > LENGTH_AUTH_COOKIE);

        rq.setAttribute("authCookie", authCookie);
        chain.doFilter(rq, rs);
    }
}
