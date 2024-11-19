package com.it.crudexam.resources;

import com.it.crudexam.ejb.AbstractFacade;
import com.it.crudexam.ejb.Group1Facade;
import com.it.crudexam.entity.Group1;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author
 */
@Path("rest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JakartaEE8Resource {

    @Inject
    private Group1Facade abstratFaced;

    @GET
    @Path("/hello")
    public String hello() {
        return "Hello Adi";
    }

    @POST
    @Path("/add")
    public Response addData(Group1 group1) {
        Group1 newGroup = abstratFaced.create(group1);
        return Response.status(Response.Status.CREATED).entity(newGroup).build();
    }
//    200
}
