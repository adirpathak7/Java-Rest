package com.it.crudexam.resources;

import com.it.crudexam.bean.CrudBean;
import com.it.crudexam.ejb.AbstractFacade;
import com.it.crudexam.ejb.Group1Facade;
import com.it.crudexam.entity.Group1;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

    @Inject
    private CrudBean crudBean;

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

    @PUT
    @Path("updateData/{id}")
    public Response updateData(@PathParam("id") int id, Group1 group1) {
        Group1 existGroup1 = abstratFaced.find(id);
        System.out.println("in the rest");
        if (existGroup1 == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Student not found").build();
        }
        System.out.println("in the rest " + existGroup1.getName());
        existGroup1.setName(group1.getName());
        existGroup1.setDate(group1.getDate());
        existGroup1.setPassword(group1.getPassword());

        abstratFaced.edit(existGroup1);
        return Response.ok(existGroup1).build();
    }
    
}
