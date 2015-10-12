package demo;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.servlet.ServletContainer;
import org.jboss.weld.environment.servlet.Listener;

import java.net.URI;

public class Bootstrap {

    public static void main(String[] args) throws Exception {
        String port = System.getProperty("server.port", System.getenv("PORT"));
        if (port == null || port.isEmpty()) port = "8080";

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:" + port));
        WebappContext context = new WebappContext("GrizzlyContext", "");
        context.addServlet("JerseyContainer", new ServletContainer(new App())).addMapping("/*");
        context.addListener(new Listener());
        context.deploy(server);

        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));
        server.start();

        System.out.println(port);
    }
}
