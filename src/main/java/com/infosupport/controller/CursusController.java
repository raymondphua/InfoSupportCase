package com.infosupport.controller;

import com.infosupport.Database.CursusRepository;
import com.infosupport.domain.Cursus;
import com.infosupport.domain.ImportReader;
import com.infosupport.exceptions.ImportException;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Raymond Phua on 12-10-2016.
 */
public class CursusController {

    private CursusRepository cursusRepository;
    private ImportReader importReader;

    public CursusController() {
        cursusRepository = new CursusRepository();
        importReader = new ImportReader();
    }

    public List<Cursus> getCursussen() {
        return cursusRepository.getAllCursussen();
    }

    public void importCursussen(String file) throws ImportException {
        Map<Cursus, List<LocalDate>> cursusListMap;

        cursusListMap = importReader.importCursus(file);
        cursusListMap.entrySet().stream().forEach(cursusDate -> {
            Cursus cursus = cursusDate.getKey();
            List<LocalDate> startDates = cursusDate.getValue();
            cursusRepository.createCursus(cursus, startDates);
        });
    }
}
