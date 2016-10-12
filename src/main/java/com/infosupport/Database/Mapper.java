package com.infosupport.Database;

import com.infosupport.domain.*;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Raymond Phua on 12-10-2016.
 */
@NoArgsConstructor
public class Mapper {

    public Cursus mapToCursus(ResultSet rset) throws SQLException {
        String code = rset.getString("code");
        String titel = rset.getString("titel");
        int duurtijd = rset.getInt("duurtijd");

        Cursus cursus = new Cursus(code, titel, duurtijd);

        return cursus;
    }

    public Cursist mapToCursist(ResultSet resultSet) throws SQLException{

        int id = resultSet.getInt("id");
        String naam = resultSet.getString("naam");
        String adres = resultSet.getString("adres");
        String woonplaats = resultSet.getString("woonplaats");
        int parentId = resultSet.getInt("parent");
        Cursist cursist;

        if (resultSet.getBoolean("OFFERTE")){
            cursist = new Bedrijf(id, parentId);
        } else if (resultSet.getBoolean("ISBEDRIJF")) {
            cursist = new Bedrijf(id, naam, adres, woonplaats);
        } else {
            cursist = new Particulier(id, naam, adres, woonplaats, parentId);
        }

        return cursist;
    }

    public Factuur mapToFactuur(ResultSet rset, List<Factuur> facturen) throws SQLException {

        int inschrId = rset.getInt("id");
        LocalDate date = rset.getDate("start_datum").toLocalDate();
        Factuur factuur = new Factuur();

        //Factuur existingFactuur = facturen.stream().filter(f -> f.getId() == id).findAny().orElse(null);

        Cursus cursus = mapToCursus(rset);
        Cursist cursist = mapToCursist(rset);

//        if (existingFactuur == null) {
            Inschrijving inschrijving = new Inschrijving(inschrId);

            inschrijving.addCursus(cursus, date);
            inschrijving.addCursisten(cursus, cursist);

            factuur.addInschrijving(inschrijving);
            return factuur;
//        } else {
//            Inschrijving inschrijving = existingFactuur.getInschrijvingen().stream().filter(i -> i.cursusExists(cursus)).findFirst().orElse(null);
//
//            inschrijving.addCursisten(cursus, cursist);
//
//            return existingFactuur;
//        }
    }

    public Inschrijving mapToInschrijving(ResultSet rset, List<Inschrijving> inschrijvingen) throws SQLException {

        int id = rset.getInt("id");
        LocalDate date = rset.getDate("start_datum").toLocalDate();

        Cursus cursus = mapToCursus(rset);
        Cursist cursist = mapToCursist(rset);

        Inschrijving existingInschrijving = inschrijvingen.stream().filter(i -> i.cursusExists(cursus)).findFirst().orElse(null);

        if (existingInschrijving == null) {
            Inschrijving inschrijving = new Inschrijving(id);
            inschrijving.addCursus(cursus, date);
            inschrijving.addCursisten(cursus, cursist);

            return inschrijving;
        } else {
            existingInschrijving.addCursisten(cursus, cursist);

            return existingInschrijving;
        }
    }
}
