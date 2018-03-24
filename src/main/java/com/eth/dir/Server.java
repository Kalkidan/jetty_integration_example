import com.eth.dir.util.Constants;
import com.eth.dir.util.ServletUtil;

import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.xml.XmlConfiguration;
import org.postgresql.ds.PGConnectionPoolDataSource;
import org.postgresql.ds.common.BaseDataSource;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

public class Server {


    public static void main(String[] args) throws Exception {

        /**
         * At its basic form Jetty needs, connectors, handlers, server, and threadpool.
         * We can implement Servlets in context of servlet handlers. Most of the time
         * this approach will save us the overhead by {@link javax.servlet.Servlet}
         */

         //Fetch the server configuration
        Resource fileCfg = Resource.newResource(Constants.JETTY_XML_PATH);

         //XML configuration file.
        XmlConfiguration configuration = new XmlConfiguration(fileCfg.getInputStream());

         //We can cast this SERVER
        //TODO:: this is just demo functionality for JETTY configuration
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
        ServletHolder jerseyServlet =
                context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, Constants.PATH_SPEC_SERVER);
        jerseyServlet.setInitOrder(0);

         //Add initial parameters of the Servlet
        jerseyServlet.setInitParameters(ServletUtil.getInitialParameters());

         //ServletHandler
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServlet(jerseyServlet );

         //set handler
        handlerCollection.addHandler(context);
        jettyServer.setHandler(handlerCollection);

         //This is just for trial
        InitialContext ic = new InitialContext();
        PGConnectionPoolDataSource dataSource = (PGConnectionPoolDataSource) ic.lookup(Constants.DATA_SOURCE_LOOK_UP_PATH);

        String sql = "CREATE TABLE KA " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " AGE            INT     NOT NULL, " +
                " ADDRESS        CHAR(50), " +
                " SALARY         REAL)";

        Connection baseDataSource = dataSource.getConnection();

        //baseDataSource.createStatement().execute(sql);



         //Start and join the server
        try {
            jettyServer.start();
            jettyServer.join();

        } catch (Exception e){

             // end the server if anything
            jettyServer.destroy();
        }
    }
}
