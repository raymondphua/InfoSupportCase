package com.infosupport.controller;

import com.infosupport.Database.FactuurRepository;
import com.infosupport.domain.Cursist;
import com.infosupport.domain.Factuur;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Raymond Phua on 12-10-2016.
 */
public class FactuurController {

    private FactuurRepository factuurRepository;

    public FactuurController() {
        this.factuurRepository = new FactuurRepository();
    }

    public List<Factuur> allFacturen() {
        return factuurRepository.getAllFacturen();
    }

    public List<Factuur> factuurFromWeek(int week) {
        return factuurRepository.getFacturenFromWeek(week);
    }

    public List<Factuur> factuurFromCursist(Cursist cursist) {
        return factuurRepository.getFacturenFromCursist(cursist);
    }
}
