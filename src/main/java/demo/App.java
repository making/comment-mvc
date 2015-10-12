package demo;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import javax.mvc.security.Csrf;

public class App extends ResourceConfig {

    public App() {
        packages(getClass().getPackage().getName());
        property(Csrf.CSRF_PROTECTION, Csrf.CsrfOptions.EXPLICIT);
        register(LoggingFilter.class);
    }
}
