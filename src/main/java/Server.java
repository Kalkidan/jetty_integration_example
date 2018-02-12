import com.eth.dir.util.Constants;
import com.eth.dir.util.ServletUtil;

import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;
import javax.naming.InitialContext;

public class Server {


    public static void main(String[] args) throws Exception {

         //Fetch the server configuration
        Resource fileCfg = Resource.newResource(Constants.JETTY_XML_PATH);

         //XML configuration file.
        XmlConfiguration configuration = new XmlConfiguration(fileCfg.getInputStream());

         //We can cast this SERVER
        org.eclipse.jetty.server.Server jettyServer = (org.eclipse.jetty.server.Server) configuration.configure();

         //Define a handler collection
        HandlerCollection handlerCollection = new HandlerCollection();

         //Add web app context
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setResourceBase(Constants.RESOURCE_BASE_PATH_WEB);
        webAppContext.setContextPath(Constants.CONTEXT_PATH_WEB);
        webAppContext.setDefaultsDescriptor(Constants.DEFAULT_DESCRIPTOR_WEB);

         //Handler collection
        handlerCollection.addHandler(webAppContext);

         //com.beacon.helper.server.Server context
         //Define Server Context
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath(Constants.CONTEXT_PATH_SERVER);

         //Servlet holder instance
        ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, Constants.PATH_SPEC_SERVER);
        jerseyServlet.setInitOrder(0);

         //Add initial parameters of the Servlet
        jerseyServlet.setInitParameters(ServletUtil.getInitialParameters());

         //set handler
        handlerCollection.addHandler(context);
        jettyServer.setHandler(handlerCollection);

        //This is just for trial
        InitialContext ic = new InitialContext();
        ic.lookup(Constants.DATA_SOURCE_LOOK_UP_PATH);

         //Start the server
        try {
            jettyServer.start();


            jettyServer.join();

        } catch (Exception e){

        }
    }
}
