package com.infosupport.api;

import com.infosupport.controller.InschrijvingController;
import com.infosupport.domain.Cursist;
import com.infosupport.domain.Cursus;
import com.infosupport.domain.DummyInschrijvingApi;
import com.infosupport.domain.Inschrijving;
import com.infosupport.exceptions.ImportException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raymond Phua on 12-10-2016.
 */
@Path("/inschrijvingen")
public class InschrijvingResource implements DefaultResource<Inschrijving> {

    private InschrijvingController inschrijvingController;
    private List<Inschrijving> inschrijvingen;
    @Context
    UriInfo uriInfo;

    public InschrijvingResource() {
        inschrijvingController = new InschrijvingController();
        inschrijvingen = new ArrayList<>();
    }

    @Override
    public Response getAll() {
        inschrijvingen = inschrijvingController.getInschrijvingen();

        if (inschrijvingen != null) {
            return Response.ok(inschrijvingen).build();
        }

        return Response.noContent().build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response registerCursist(DummyInschrijvingApi inschrijving) {

        Response response = null;
        try {
            inschrijvingController.registerCursist(inschrijving);
            response = Response.created(uriInfo.getAbsolutePath()).build();
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.status(406).entity("Format Error in file").build();
        }

        return response;
    }

}
