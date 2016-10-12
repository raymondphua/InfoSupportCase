package com.infosupport.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Raymond Phua on 12-10-2016.
 */
public interface DefaultResource<T> {

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response getAll();

//    @POST
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    Response add(T t);

    //below not needed for first sprint

//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    Response getOne(@PathParam("id") int id);

//    @PUT
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    Response update(T t);

//    @DELETE
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    Response remove(@PathParam("id") int id);
//
//    @PUT
//    @Path("id")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    Response cancel(int id);
}

