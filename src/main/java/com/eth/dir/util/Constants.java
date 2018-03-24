package com.eth.dir.util;

public class Constants {

    //overriden jetty.xml path
    public static final String JETTY_XML_PATH = "./src/main/webapp/WEB-INF/jetty.xml";

    //Web
    public static final String RESOURCE_BASE_PATH_WEB = "src/main/webapp";
    public static final String CONTEXT_PATH_WEB = "/webapp";
    public static final String DEFAULT_DESCRIPTOR_WEB = "src/main/webapp/WEB-INF/web.xml";

    //Web end

    //com.eth.dir.Server
    public static final String PATH_SPEC_SERVER = "/*";
    public static final String CONTEXT_PATH_SERVER = "/";

    //com.eth.dir.Server end

    //data source
    public static final String DATA_SOURCE_LOOK_UP_PATH = "jdbc/directory_data_source";
    //data source end


}
