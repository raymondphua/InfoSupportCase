package com.infosupport.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 * Created by Raymond Phua on 10-10-2016.
 */
@Data
public class Inschrijving {

    private int id;
    private Map<Cursus, List<Cursist>> cursusCursisten;
    private LocalDate startDatum;

    public Inschrijving() {
        id = 0;
        cursusCursisten = new HashMap<>();
    }

    public Inschrijving(int id) {
        this.id = id;
        cursusCursisten = new HashMap<>();
    }

    public void addCursus(Cursus cursus, LocalDate startDatum) {
        if (!cursusExists(cursus)) {
            cursusCursisten.put(cursus, new ArrayList<>());
            this.startDatum = startDatum;
        }
    }

    public void addCursisten(Cursus cursus, Cursist cursist) {
        cursusCursisten.get(cursus).add(cursist);
    }

    public boolean cursusExists(Cursus cursus) {
        return cursusCursisten.containsKey(cursus);
    }

    public void printInfo() {
        cursusCursisten.entrySet().stream().forEach(cursusListEntry -> {
            //cursussen = keys
            System.out.println("Cursus: " + cursusListEntry.getKey().getTitel());
            System.out.println("Datum: " + startDatum.toString());
            System.out.println();
            //value = list of cursists
            cursusListEntry.getValue().stream().forEach(cursist -> {
                if (cursist instanceof Particulier) {
                    System.out.println("---------------");
                    Particulier particulier = (Particulier) cursist;
                    particulier.showInfo();
                } else if (cursist instanceof Bedrijf && !cursist.isOfferte()) {
                    System.out.println("---------------");
                    System.out.println("Bedrijf: ");
                    Bedrijf bedrijf = (Bedrijf)cursist;
                    bedrijf.showInfo();
                }
            });
        });
    }

    public void lesInWeek(int week) {
        if (isInWeek(week)) {
            System.out.println("Inschrijvingen voor week: " + week);
            System.out.println();
            printInfo();
        }
    }

    public boolean isInWeek(int week) {
        TemporalField woy = WeekFields.of(Locale.GERMANY).weekOfWeekBasedYear();

        return (startDatum.get(woy)) == week;
    }

    public void cursistInInschrijving(Cursist cursist) {

        cursusCursisten.entrySet().stream().forEach(cursusListEntry -> {
            cursusListEntry.getValue().stream().forEach(cursistInList -> {
                if (cursistInList.equals(cursist)) {
                    System.out.println("Cursus: " + cursusListEntry.getKey().getTitel());
                    System.out.println("Datum: " + getStartDatum());
                    System.out.println();
                }
            });
        });
    }
}
