package com.infosupport.api;

import com.infosupport.Database.CursistRepository;
import com.infosupport.controller.CursistController;
import com.infosupport.domain.Cursist;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by Raymond Phua on 12-10-2016.
 */
@Path("/cursisten")
public class CursistResource implements DefaultResource<Cursist> {

    private List<Cursist> cursisten;
    private CursistController cursistController;

    public CursistResource() {
        this.cursistController = new CursistController();
    }

    @Override
    public Response getAll() {

        cursisten = cursistController.getCursisten();

        if (cursisten != null) {
            return Response.ok(cursisten).build();
        }

        return Response.noContent().build();
    }
}
