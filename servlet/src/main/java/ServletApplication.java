import filter.AuthFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterMapping;
import org.eclipse.jetty.servlet.ServletHandler;
import servlet.CounterClearServlet;
import servlet.CounterServlet;

public class ServletApplication {



  public static void main(String[] args) throws Exception {
    final int port = 8081;
    final Server server = createServer(port);
    server.start();
    server.join();
  }

  private static Server createServer(int port) {
    final Server server = new Server(port);
    final ServletHandler servletHandler = new ServletHandler();
    servletHandler.addServletWithMapping(CounterServlet.class, "/counter");
    servletHandler.addServletWithMapping(CounterClearServlet.class, "/counter/clear");
    servletHandler.addFilterWithMapping(AuthFilter.class, "/counter/clear", FilterMapping.DEFAULT);
    server.setHandler(servletHandler);
    return server;
  }
}
