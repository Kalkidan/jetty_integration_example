package com.eth.dir.service;

import com.eth.dir.db.DatabaseConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("directory")
public class DirectoryServlet {

    @GET
    @Path("squareRoot")
    @Produces(MediaType.APPLICATION_JSON)
    public DatabaseConfig squareRoot(@QueryParam("input") double input) throws Exception {

        DatabaseConfig result = new DatabaseConfig("Square Root");
        result.setInput(input);
        result.setOutput(Math.sqrt(result.getInput()));
        return result;
    }

    @GET
    @Path("square")
    @Produces(MediaType.APPLICATION_JSON)
    public DatabaseConfig square(@QueryParam("input") double input){
        DatabaseConfig result = new DatabaseConfig("Square");
        result.setInput(input);
        result.setOutput(result.getInput()*result.getInput());
        return result;
    }
}
