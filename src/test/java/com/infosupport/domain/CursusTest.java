package com.infosupport.domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

/**
 * Created by Raymond Phua on 9-10-2016.
 */
public class CursusTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Cursus cursus;

    @Before
    public void init() {
        cursus = new Cursus("testcode", "Java8", 4);
    }

//    @Test
//    public void add_Start_Datum_To_Cursus() {
//        cursus.addStartDatumToCursus(LocalDate.of(2016, 11, 01));
//
//        LocalDate localDate = cursus.getStartDatums().get(0);
//
//        assertThat(localDate, is(LocalDate.of(2016, 11, 01)));
//    }
}