package com.infosupport.api;

import com.infosupport.Database.CursusRepository;
import com.infosupport.controller.CursusController;
import com.infosupport.domain.Cursus;
import com.infosupport.domain.ImportReader;
import com.infosupport.exceptions.ImportException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by Raymond Phua on 12-10-2016.
 */
@Path("/cursussen")
public class CursusResource implements DefaultResource<Cursus> {

    private List<Cursus> cursussen;
    private CursusController cursusController;
    @Context
    UriInfo uriInfo;

    public CursusResource() {
        cursusController = new CursusController();
    }

    @Override
    public Response getAll() {

        cursussen = cursusController.getCursussen();

        if (cursussen != null) {
            return Response.ok(cursussen).build();
        }

        return Response.noContent().build();
    }

    @POST
    @Consumes({MediaType.TEXT_PLAIN})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response importCursus(String file) {

        Response response = null;
        try {
            cursusController.importCursussen(file);
            response = Response.created(uriInfo.getAbsolutePath()).build();
        } catch (ImportException e) {
            e.printStackTrace();
            response = Response.status(406).entity("Format Error in file").build();
        }

        return response;
    }
}
