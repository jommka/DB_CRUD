package server;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.CurrencyServlet;
import servlet.ItemServlet;
import servlet.PlayerServlet;
import servlet.ProgressServlet;


public class StartServer {


    public static void server(){
        int port = 8080;
        Server server = new Server(port);

        final HttpConfiguration httpConfig = new HttpConfiguration();
        final HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(httpConfig);
        final ServerConnector serverConnector = new ServerConnector(server, httpConnectionFactory);

        serverConnector.setHost("localhost");
        serverConnector.setPort(port);
        server.setConnectors(new Connector[]{serverConnector});

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");

        context.addServlet(new ServletHolder(new PlayerServlet()), "/player");
        context.addServlet(new ServletHolder(new ItemServlet()), "/item");
        context.addServlet(new ServletHolder(new ProgressServlet()), "/progress");
        context.addServlet(new ServletHolder(new CurrencyServlet()), "/currency");


        server.setHandler(context);
        try {
            server.start();
            System.out.println("Listening port : " + port );

            server.join();
        } catch (Exception e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
    }

}
