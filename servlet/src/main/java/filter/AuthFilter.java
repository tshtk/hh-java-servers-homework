package filter;

import javax.servlet.ServletException;
import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class AuthFilter extends HttpFilter {

    @Override
    protected void doFilter(final HttpServletRequest rq, final HttpServletResponse rs, final FilterChain chain)
            throws IOException, ServletException {
        boolean authCookie = false;

        Cookie[] cookies = rq.getCookies();

        if (cookies != null) {
            final String nameAuthCookie = "hh-auth";
            final int lengthAuthCookie = 10;
            authCookie = Arrays.stream(cookies)
                    .filter(o -> o.getName().equals(nameAuthCookie))
                    .anyMatch(o -> o.getValue().length() > lengthAuthCookie);
        }
        rq.setAttribute("authCookie", authCookie);
        chain.doFilter(rq, rs);
    }
}
