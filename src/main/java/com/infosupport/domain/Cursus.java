package com.infosupport.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Raymond Phua on 9-10-2016.
 */
@Data
@NoArgsConstructor
public class Cursus {

    private String code;
    private String titel;
    private int duurtijd;
//    private List<LocalDate> startDatums;

    //TODO: hashmap<weekNr, List<cursist>> of hashmap<Week, List<Cursist>> week = weeknr + datum

    public Cursus(String code, String titel, int duurtijd) {
        this.code = code;
        this.titel = titel;
        this.duurtijd = duurtijd;
//        startDatums = new ArrayList<>();
    }

//    public void addStartDatumToCursus(LocalDate startDatum) {
//        //check if startdatum exists
//        LocalDate found = startDatums.stream().filter(date -> date.equals(startDatum)).findFirst().orElse(null);
//        if (found == null) {
//            startDatums.add(startDatum);
//        }
//    }
}
