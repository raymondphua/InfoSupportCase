package com.infosupport.domain;

import lombok.Data;

/**
 * Created by Raymond Phua on 9-10-2016.
 */
@Data
public abstract class Cursist {

    protected int id;
    protected String naam;
    protected String adres;
    protected String woonplaats;
    protected boolean offerte;
    protected int parentId;

    public void showInfo() {
        throw new UnsupportedOperationException();
    }
}