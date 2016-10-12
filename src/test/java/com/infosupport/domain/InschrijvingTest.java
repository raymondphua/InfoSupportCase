package com.infosupport.domain;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by Raymond Phua on 10-10-2016.
 */
public class InschrijvingTest {

    private Inschrijving inschrijving;
    private Cursus cursus;

    @Before
    public void init() {
        inschrijving = new Inschrijving();
        cursus = new Cursus("test", "test", 2);
    }

    @Test
    public void add_New_Cursus_Should_Increase_Size() {

        int sizeBefore = inschrijving.getCursusCursisten().size();

        addCursusToInschrijving(inschrijving);

        int sizeAfter = inschrijving.getCursusCursisten().size();
        assertThat(sizeAfter, is(sizeBefore + 1));
    }

    @Test
    public void add_Existing_Cursus_Should_Not_Increase_Size() {

        addCursusToInschrijving(inschrijving);

        int sizeBefore = inschrijving.getCursusCursisten().size();

        addCursusToInschrijving(inschrijving);

        int sizeAfter = inschrijving.getCursusCursisten().size();

        assertThat(sizeAfter, is(sizeBefore));
    }

    @Test
    public void add_Cursist_To_Cursus_Should_Increase_Size() {

        addCursusToInschrijving(inschrijving);

        int sizeBefore = inschrijving.getCursusCursisten().get(cursus).size();
        inschrijving.addCursisten(cursus, getCursist());

        int sizeAfter = inschrijving.getCursusCursisten().get(cursus).size();

        assertThat(sizeAfter, is(sizeBefore + 1));
    }

    @Test
    public void inschrijving_Should_Have_Bedrijf() {

        addCursusToInschrijving(inschrijving);

        inschrijving.addCursisten(cursus, getCursist());

        assertThat(inschrijving.getCursusCursisten().get(cursus).get(0), is(instanceOf(Bedrijf.class)));
    }

    @Test
    public void inschrijving_Should_Have_Bedrijf_With_Offerte() {

        addCursusToInschrijving(inschrijving);

        Bedrijf bedrijf = (Bedrijf)getCursist();
        Bedrijf offerte = new Bedrijf(2, 1);
        bedrijf.addCursist(offerte);

        inschrijving.addCursisten(cursus, bedrijf);

        Bedrijf bedrijfInCursus = (Bedrijf) inschrijving.getCursusCursisten().get(cursus).get(0);
        Bedrijf offerteInBedrijfInCursus = (Bedrijf)bedrijfInCursus.getCursists().get(0);

        assertThat(offerteInBedrijfInCursus, is(offerte));
    }

    @Test
    public void inschrijving_Should_Have_Bedrijf_With_Offerte_With_Particulier() {

        addCursusToInschrijving(inschrijving);

        Particulier p = new Particulier(3, "Raymond", "test", "test", 2);

        Bedrijf offerte = new Bedrijf(2, 1);
        offerte.addCursist(p);

        Bedrijf bedrijf = (Bedrijf)getCursist();
        bedrijf.addCursist(offerte);

        inschrijving.addCursisten(cursus, bedrijf);

        Bedrijf bedrijfInCursus = (Bedrijf) inschrijving.getCursusCursisten().get(cursus).get(0);
        Bedrijf offerteInBedrijfInCursus = (Bedrijf)bedrijfInCursus.getCursists().get(0);
        Particulier particulierInOfferteInBedrijf = (Particulier) offerteInBedrijfInCursus.getCursists().get(0);

        assertThat(particulierInOfferteInBedrijf, is(p));
    }

    @Test
    public void inschrijving_Has_Date_In_Given_Week() {

        addCursusToInschrijving(inschrijving);

        assertTrue(inschrijving.isInWeek(41));
    }

    @Test
    public void inschrijving_Has_No_Date_In_Given_Week() {

        addCursusToInschrijving(inschrijving);

        assertFalse(inschrijving.isInWeek(22));
    }

    private Cursist getCursist() {
        return new Bedrijf(1, "InfoSupport", "test", "test");
    }

    private Cursus getCursus() {
        return cursus;
    }

    private void addCursusToInschrijving(Inschrijving inschrijving) {
        inschrijving.addCursus(getCursus(), LocalDate.of(2016, 10, 10));
    }
}