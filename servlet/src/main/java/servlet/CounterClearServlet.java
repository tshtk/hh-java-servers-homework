package servlet;

import counter.Counter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CounterClearServlet  extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest rq, final HttpServletResponse rs)
            throws ServletException, IOException {
        final long value;
        final String textResponse;
        final PrintWriter writer = rs.getWriter();

        if ((boolean)rq.getAttribute("authCookie")) {
            value = Counter.value.updateAndGet(o -> 0);
            textResponse = "Counter value reset.\nCounter value: " + value;
        }
        else {
            value = Counter.value.get();
            textResponse = "Unable to reset counter value.\nCounter value: " + value;
            rs.setStatus(401);
        }
        writer.print(textResponse);
        writer.flush();
    }
}
