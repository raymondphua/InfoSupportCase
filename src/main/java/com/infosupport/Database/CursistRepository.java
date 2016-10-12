package com.infosupport.Database;

import com.infosupport.domain.Bedrijf;
import com.infosupport.domain.Cursist;
import com.infosupport.domain.Particulier;
import lombok.NoArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Raymond Phua on 10-10-2016.
 */
@NoArgsConstructor
public class CursistRepository extends Database {

    public List<Cursist> getAllCursisten() {
        getConnection();

        List<Cursist> cursists = new ArrayList<>();
        try {
            List<Cursist> allCursisten = new ArrayList<>();

            String query = "SELECT * FROM CURSISTS";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            System.out.println("\n Executing query: " + query);
            ResultSet rset = preparedStatement.executeQuery();

            while(rset.next()) {
                allCursisten.add(mapper.mapToCursist(rset));
            }

            cursists = fixRelations(allCursisten);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cursists;
    }

    public Cursist getCursist(Cursist cursist) {
        getConnection();

        Cursist returnCursist = null;
        try {
            List<Cursist> allCursisten = new ArrayList<>();

            String query = "SELECT * FROM CURSISTS WHERE PARENT = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, cursist.getParentId());
            System.out.println("\n Executing query: " + query);
            ResultSet rset = preparedStatement.executeQuery();

            while(rset.next()) {
                allCursisten.add(mapper.mapToCursist(rset));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return returnCursist;
    }

    public void createCursist(Cursist cursist) {
        getConnection();

        try {
            int offerte = parseBoolToInt(cursist.isOfferte());
            String query = "";
            PreparedStatement preparedStatement;
            int rowsUpdated = 0;

            if (cursist instanceof Bedrijf && !cursist.isOfferte()) {
                query = "INSERT INTO CURSISTS (NAAM, ADRES, WOONPLAATS, OFFERTE, ISBEDRIJF) " +
                        "VALUES (?, ?, ?, ?, ?)";

                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, cursist.getNaam());
                preparedStatement.setString(2, cursist.getAdres());
                preparedStatement.setString(3, cursist.getWoonplaats());
                preparedStatement.setInt(4, offerte);
                preparedStatement.setInt(5, 1);

                System.out.println("\n Executing query: " + query);
                rowsUpdated = preparedStatement.executeUpdate();
            } else {
                query = "INSERT INTO CURSISTS (NAAM, ADRES, WOONPLAATS, PARENT, OFFERTE, ISBEDRIJF) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, cursist.getNaam());
                preparedStatement.setString(2, cursist.getAdres());
                preparedStatement.setString(3, cursist.getWoonplaats());
                preparedStatement.setInt(4, cursist.getParentId());
                preparedStatement.setInt(5, offerte);
                preparedStatement.setInt(6, 0);
                System.out.println("\n Executing query: " + query);

                rowsUpdated = preparedStatement.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Cursist> fixRelations(List<Cursist> cursists) {

        List<Cursist> fixedCursists = new ArrayList<>();

        List<Particulier> particulieren = (List<Particulier>)(List<?>) cursists.stream().filter(c -> c instanceof Particulier).collect(Collectors.toList());

        List<Bedrijf> bedrijven = (List<Bedrijf>)(List<?>) cursists.stream().filter(c -> c instanceof Bedrijf && !c.isOfferte()).collect(Collectors.toList());

        List<Bedrijf> offertes = (List<Bedrijf>)(List<?>) cursists.stream().filter(b -> b.isOfferte()).collect(Collectors.toList());

        //List<Bedrijf> parentChildOfferteParticulier = offertes.stream().filter(o -> particulieren.stream().filter(p -> o.getId() == p.getParentId()).count() > 0).collect(Collectors.toList());

        List<Particulier> addedParticulieren = new ArrayList<>();

        //for each offerte, add the correct particulier to it
        offertes.stream().forEach(o -> particulieren.stream().forEach(p -> {
            if (o.getId() == p.getParentId()) {
                o.addCursist(p);
                addedParticulieren.add(p);
            }
        }));

        //for each bedrijf add the correct offerte
        bedrijven.stream().forEach(b -> offertes.stream().forEach(o -> {
            if (b.getId() == o.getParentId()) {
                b.addCursist(o);
            }
        }));

        fixedCursists = (List<Cursist>)(List<?>) bedrijven;

        //foreach addedParticulier (which means it belongs to an offerte/bedrijf
        //remove them --> the rest are alleenstaanden
        addedParticulieren.stream().forEach(p -> particulieren.remove(p));

        //particulieren.stream().forEach(alleendStaand -> fixedCursists.add(alleendStaand));

        //old fashioned way because above doesn't work with fixedCursists
        for (Cursist cursist : particulieren) {
            fixedCursists.add(cursist);
        }

        return fixedCursists;
    }

    private int parseBoolToInt(boolean bool) {
        if (bool) {
            return 1;
        }

        return 0;
    }
}
