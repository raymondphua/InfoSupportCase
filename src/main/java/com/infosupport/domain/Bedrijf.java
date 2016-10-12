package com.infosupport.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raymond Phua on 9-10-2016.
 */
@Data
@NoArgsConstructor
public class Bedrijf extends Cursist {

    private List<Cursist> cursists;

    public Bedrijf(int id, int parentId) {
        this.id = id;
        this.naam = "Offerte " + id;
        this.offerte = true;
        this.parentId = parentId;
        cursists = new ArrayList<>();
    }

    public Bedrijf(int id, String naam, String adres, String woonplaats) {
        this.id = id;
        this.naam = naam;
        this.adres = adres;
        this.woonplaats = woonplaats;
        this.offerte = false;
        this.parentId = 0;
        this.cursists = new ArrayList<>();
    }

    @Override
    public void showInfo() {
        System.out.println(naam);
        if (!isOfferte()) {
            System.out.println(adres + " " + woonplaats);
        } else {
            System.out.println("Aantal particulieren: " + cursists.size());
        }
        System.out.println();
        cursists.stream().forEach(c -> c.showInfo());
    }

    public void addCursist(Cursist cursist) {
        cursists.add(cursist);
    }

    public void removeCursist(Cursist cursist) {
        cursists.remove(cursist);
    }

    public Cursist getChildWithId(int id) {
        return cursists.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
}
