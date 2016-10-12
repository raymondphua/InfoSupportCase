package com.infosupport.controller;

import com.infosupport.Database.InschrijvingRepository;
import com.infosupport.domain.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Raymond Phua on 12-10-2016.
 */
public class InschrijvingController {

    private InschrijvingRepository inschrijvingsRepository;

    public InschrijvingController() {
        inschrijvingsRepository = new InschrijvingRepository();
    }

    public List<Inschrijving> getInschrijvingen() {
        return inschrijvingsRepository.getAllInschrijvingen();
    }

    public void registerCursist(DummyInschrijvingApi inschrijving) {

        Cursus cursus = inschrijving.getCursus();
        LocalDate startDatum = inschrijving.getParsedToDate();
        Cursist cursist = new Bedrijf(inschrijving.getCursist(), 0);

        inschrijvingsRepository.registerCursist(cursus, startDatum, cursist);
    }
}
