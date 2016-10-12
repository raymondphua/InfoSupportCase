package com.infosupport.domain;

import com.infosupport.Database.DummyCursistRepository;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by Raymond Phua on 10-10-2016.
 */
public class CursistRepositoryTest {

    private DummyCursistRepository repo;

    @Before
    public void init() {
        repo = new DummyCursistRepository();
    }

    @Test
    public void create_Should_Increase_Size() {
        int sizeBefore = repo.getCursists().size();

        repo.create(new Bedrijf(1, 1));

        int sizeAfter = repo.getCursists().size();

        assertThat(sizeAfter, is(sizeBefore + 1));
    }

    @Test
    public void created_Cursist_Should_Be_Found() {
        Cursist newBedrijf = new Bedrijf(1, 1);
        repo.create(newBedrijf);
        Cursist cursist = repo.getCursist(1);

        assertThat(cursist, is(newBedrijf));
    }
}
