package filter;

import javax.servlet.ServletException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest rq, final ServletResponse rs, final FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRq = (HttpServletRequest) rq;

        boolean authCookie = false;

        Cookie[] cookies = httpRq.getCookies();

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
