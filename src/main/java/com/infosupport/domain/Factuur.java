package com.infosupport.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Raymond Phua on 11-10-2016.
 */
@Data
public class Factuur {

    private List<Inschrijving> inschrijvingen;

    public Factuur() {
        inschrijvingen = new ArrayList<>();
    }

    public Factuur(int id) {
        inschrijvingen = new ArrayList<>();
    }

    public void printInfoOfWeek(int weekNr) {
        inschrijvingen.stream().forEach(i -> i.lesInWeek(weekNr));
    }

    public void printInfoOfCursist(Cursist cursist) {
        System.out.println("Alle inschrijvingen voor: " + cursist.getNaam());
        inschrijvingen.stream().forEach(i -> i.cursistInInschrijving(cursist));
    }

    public void addInschrijving(Inschrijving inschrijving) {
        inschrijvingen.add(inschrijving);
    }
}
