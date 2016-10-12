package com.infosupport.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by Raymond Phua on 9-10-2016.
 */
public class CursistTest {

    @Test
    public void cursist_Is_InstanceOf_Particulier() {

        Cursist cursist = new Particulier(1, "Raymond", "Teststraat 1", "Dordrecht", 1);

        assertThat(cursist, is(instanceOf(Particulier.class)));
    }

    @Test
    public void cursist_Is_InstanceOf_Bedrijf() {

        Cursist cursist = getBedrijf();

        assertThat(cursist, is(instanceOf(Bedrijf.class)));
    }

    @Test
    public void bedrijf_Has_Offertes_Only() {

        Cursist cursist = getBedrijf();
        Bedrijf bedrijf = (Bedrijf)cursist;

        addOffertesToBedrijf(bedrijf);

        assertThat(bedrijf.getCursists(), everyItem(instanceOf(Bedrijf.class)));
    }

    @Test
    public void bedrijf_Has_Offerte_With_Particulier_Only() {

        Cursist cursist = getBedrijf();
        Bedrijf bedrijf = (Bedrijf)cursist;
        bedrijf.addCursist(new Bedrijf(2, cursist.getId()));

        Bedrijf offerte = (Bedrijf)bedrijf.getChildWithId(2);
        addParticulierenToBedrijf(offerte);

        assertThat(offerte.getCursists(), everyItem(instanceOf(Particulier.class)));
    }

    @Test
    public void getChild_WithUnknownId_ReturnNull() {
        Cursist cursist = getBedrijf();
        Bedrijf bedrijf = (Bedrijf) cursist;
        addOffertesToBedrijf(bedrijf);

        assertThat(bedrijf.getChildWithId(8), is(nullValue()));
    }

    private Cursist getBedrijf() {
        return new Bedrijf(1, "Info Support BV", "Kruisboog 42", "Veenendaal");
    }

    private void addParticulierenToBedrijf(Bedrijf bedrijf) {
        for (int i = 0; i < 5; i++) {
            bedrijf.addCursist(new Particulier(i, "particulier " + i, "adres " + i, "woonplaats " + i, bedrijf.getId()));
        }
    }

    private void addOffertesToBedrijf(Bedrijf bedrijf) {
        for (int i = 0; i < 5; i++) {
            bedrijf.addCursist(new Bedrijf(i, bedrijf.getId()));
        }
    }
}