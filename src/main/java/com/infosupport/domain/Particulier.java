package com.infosupport.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Raymond Phua on 9-10-2016.
 */
@Data
@NoArgsConstructor
public class Particulier extends Cursist {

    public Particulier(int id, String naam, String adres, String woonplaats, int parentId) {
        this.id = id;
        this.naam = naam;
        this.adres = adres;
        this.woonplaats = woonplaats;
        this.parentId = parentId;
    }

    @Override
    public void showInfo() {
        System.out.println("Particulier: " + naam);
        System.out.println("adres: " + adres + ", " + woonplaats);
        System.out.println();
    }
}
