package com.infosupport.api;

import com.infosupport.Database.FactuurRepository;
import com.infosupport.controller.FactuurController;
import com.infosupport.domain.Bedrijf;
import com.infosupport.domain.Cursist;
import com.infosupport.domain.Factuur;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by Raymond Phua on 12-10-2016.
 */
@Path("/facturen")
public class FactuurResource implements DefaultResource<Factuur> {

    private List<Factuur> facturen;
    private FactuurController factuurController;
    @Context
    UriInfo uriInfo;

    public FactuurResource() {
        this.factuurController = new FactuurController();
    }

    @Override
    public Response getAll() {

        facturen = factuurController.allFacturen();

        if (facturen != null) {
            return Response.ok(facturen).build();
        }

        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getFromWeek(@PathParam("id") int week) {

        facturen = factuurController.factuurFromWeek(week);

        if (facturen.size() > 0) {
            return Response.ok(facturen).build();
        }

        return Response.noContent().build();
    }

    //TODO: Had to give cursist instead of a integer from cursist.
    //TODO: Cursist is abstract so can't give an cursist from API
    // second param is null, above i use the same method with int id,
    // should be cursist below, but because of this impediment I will need random 2nd param
    @GET
    @Path("/{id}/cursist")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getFromCursist(@PathParam("id") int cursistId, int zero) {

        //this is not how it's supposed to be
        Cursist cursist = new Bedrijf(cursistId, 0);

        facturen = factuurController.factuurFromCursist(cursist);

        if (facturen.size() > 0) {
            return Response.ok(facturen).build();
        }

        return Response.noContent().build();
    }
}
