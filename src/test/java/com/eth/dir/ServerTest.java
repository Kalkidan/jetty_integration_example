package com.eth.dir;

import com.eth.dir.util.Constants;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServerTest {


    private static Server server;
    private static ServletContextHandler servletContextHandler;
    private static ServletHolder servletHolder;
    private static WebAppContext webAppContext;
    @Mock private static HandlerCollection handlerCollection;


    //This will run the server only once
    @BeforeClass public static void setUp(){

        //use mock not spy
        handlerCollection = mock(HandlerCollection.class);

        //TODO:: uncomment this to see the exception when spying
        //handlerCollection = new HandlerCollection();

        //
        server = spy(new Server(8080));
        servletContextHandler = spy(new ServletContextHandler());
        //
        when(servletContextHandler.getContextPath()).thenReturn(Constants.CONTEXT_PATH_SERVER);
        servletHolder = spy(new ServletHolder("default", DefaultServlet.class));
        //

        webAppContext = spy(new WebAppContext());
        //
        setup(webAppContext);
        //Adding the web app context to the handler
        handlerCollection.addHandler(webAppContext);

    }

    @AfterClass public static void killResources(){
        //Clean up server resources here
    }

    public static void setup(WebAppContext webAppContext) {
        //
        when(webAppContext.getResourceBase()).thenReturn(Constants.RESOURCE_BASE_PATH_WEB);
        when(webAppContext.getDefaultsDescriptor()).thenReturn(Constants.DEFAULT_DESCRIPTOR_WEB);
        when(webAppContext.getContextPath()).thenReturn(Constants.CONTEXT_PATH_WEB);
    }

    @Test public void server_configuration_test(){
        assertNotNull(server);
    }
}
