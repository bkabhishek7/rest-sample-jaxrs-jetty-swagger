package rest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

import io.swagger.annotations.Api;
import io.swagger.jaxrs.config.DefaultJaxrsConfig;
public class RestApplication {
	
	public static void main(String[] args) throws Exception {

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);
        
        // Setup API resources
        ServletHolder apiServlet = context.addServlet(ServletContainer.class, "/api/*");
        apiServlet.setInitOrder(0);
        apiServlet.setInitParameter(ServerProperties.PROVIDER_PACKAGES,"rest;io.swagger.jaxrs.json;io.swagger.jaxrs.listing");
//        apiServlet.setInitParameter("swagger.api.basepath","http://localhost:8080/api");
        
        // Setup Swagger servlet
        ServletHolder swaggerServlet = context.addServlet(DefaultJaxrsConfig.class, "/swagger-core");
        swaggerServlet.setInitOrder(1);
        swaggerServlet.setInitParameter("api.version", "2.0.0");
        swaggerServlet.setInitParameter("swagger.api.basepath","http://localhost:8080/api");
        
        // Setup Swagger-UI static resources
        String resourceBasePath = RestApplication.class.getResource("/swagger-ui").toExternalForm();
        context.setWelcomeFiles(new String[] {"index.html"});
        context.setResourceBase(resourceBasePath);
        
        context.addServlet(new ServletHolder(new DefaultServlet()), "/*");
        
        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
//            jettyServer.destroy();
        }
	}
}

//        ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
//        jerseyServlet.setInitOrder(1);
//        jerseyServlet.setInitParameter(ServerProperties.PROVIDER_PACKAGES,"rest");
//        
//        ServletHolder swaggerServlet = new ServletHolder(new Bootstrap());
//        context.addServlet(swaggerServlet, "/");
//        
//          Setup API resources
//        ServletHolder apiServlet = context.addServlet(ServletContainer.class, "/api/*");
//        apiServlet.setInitOrder(1);
//        apiServlet.setInitParameter("com.sun.jersey.config.property.packages", "com.api.resources;io.swagger.jaxrs.json;io.swagger.jaxrs.listing");

//      Setup Swagger servlet
//        ServletHolder swaggerServlet = context.addServlet(DefaultJaxrsConfig.class, "/swagger-core");
//        swaggerServlet.setInitOrder(1);
//        swaggerServlet.setInitParameter("api.version", "1.0.0");

//      new Bootstrap().init();
//      Setup Swagger-UI static resources
//        String resourceBasePath = RestApplication.class.getResource("/webapp").toExternalForm();
//        context.setWelcomeFiles(new String[] {"index.html"});
//        context.setResourceBase(resourceBasePath);
//        context.addServlet(new ServletHolder(new DefaultServlet()), "/*");

//	public static void main(String[] args) throws Exception {Server server = new Server(8080);
//    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//    context.setContextPath("/");
//    server.setHandler(context);
//    
//    // Setup API resources
//    ServletHolder apiServlet = context.addServlet(ServletContainer.class, "/api/*");
//    apiServlet.setInitOrder(1);
//    apiServlet.setInitParameter("com.sun.jersey.config.property.packages", "com.api.resources;io.swagger.jaxrs.json;io.swagger.jaxrs.listing");
//    
////    // Setup Swagger servlet
////    ServletHolder swaggerServlet = context.addServlet(DefaultJaxrsConfig.class, "/swagger-core");
////    swaggerServlet.setInitOrder(2);
////    swaggerServlet.setInitParameter("api.version", "1.0.0");
////    
////    new Bootstrap().init();
//    // Setup Swagger-UI static resources
////    String resourceBasePath = RestApplication.class.getResource("/webapp").toExternalForm();
////    context.setWelcomeFiles(new String[] {"index.html"});
////    context.setResourceBase(resourceBasePath);
////    context.addServlet(new ServletHolder(new DefaultServlet()), "/*");
//    
//    server.start();
//    server.join();
//    }
