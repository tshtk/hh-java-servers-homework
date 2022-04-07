import application.App;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;


public class JerseyApplication {

  public static void main(String[] args) throws Exception {

    final int port = 8081;
    final Server server = createServer(port);
    server.start();
    server.join();
  }

  private static Server createServer(int port) {

    Server server = new Server(port);

    ServletContextHandler handler = new ServletContextHandler();

    server.setHandler(handler);

    ServletHolder servletHolder = handler.addServlet(ServletContainer.class, "/*");
    servletHolder.setInitParameter("javax.ws.rs.Application", App.class.getName());
    servletHolder.setInitOrder(1);
    return server;
  }
}
