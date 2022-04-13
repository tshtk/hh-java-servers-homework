package servlet;

import counter.Counter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CounterServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest rq, final HttpServletResponse rs)
            throws ServletException, IOException {
        final long value = Counter.value.get();
        final String textResponse = "Counter value: " + value;
        final PrintWriter writer = rs.getWriter();
        writer.print(textResponse);
        writer.flush();
    }

    @Override
    protected void doPost(final HttpServletRequest rq, final HttpServletResponse rs)
            throws ServletException, IOException {
        final long value = Counter.value.incrementAndGet();
        final String textResponse = "Counter value increased by 1.\nCounter value: " + value;
        final PrintWriter writer = rs.getWriter();
        writer.print(textResponse);
        writer.flush();
    }

    @Override
    protected void doDelete(final HttpServletRequest rq, final HttpServletResponse rs)
            throws ServletException, IOException {
        final long delta = Long.parseLong(rq.getHeader("Subtraction-Value"));
        final long value = Counter.value.addAndGet(-1 * delta);
        final String textResponse = "Counter value decreased by " + delta + ".\nCounter value: " + value;
        final PrintWriter writer = rs.getWriter();
        writer.print(textResponse);
        writer.flush();
    }
}
