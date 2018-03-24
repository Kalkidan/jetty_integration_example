package com.eth.dir.service;

import com.eth.dir.db.DatabaseConfig;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Home {

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public DatabaseConfig squareRoot(@QueryParam("input") double input) throws Exception {

            DatabaseConfig result = new DatabaseConfig("Square Kal Root");
            result.setInput(input);
            result.setOutput(Math.sqrt(result.getInput()));
            return result;
        }
}
