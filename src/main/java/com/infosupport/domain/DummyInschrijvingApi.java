package com.infosupport.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by Raymond Phua on 12-10-2016.
 *
 * Class is a dummy class to insert Cursus And Cursist easily in Inschrijving
 * Temporary solution because there is no User Interface to create a Inschrijving Object (domail.Inschrijving)
 */
@NoArgsConstructor
@Data
public class DummyInschrijvingApi {

    private Cursus cursus;
    private String startDatum;
    private LocalDate parsedToDate;
    private int cursist;

    public LocalDate getParsedToDate() {
        String[] split = startDatum.split("-");

        int day = 0;
        int month = 0;
        int year = 0;

        day = Integer.valueOf(split[0]);
        month = Integer.valueOf(split[1]);
        year = Integer.valueOf(split[2]);

        return LocalDate.of(year, month, day);
    }
}
