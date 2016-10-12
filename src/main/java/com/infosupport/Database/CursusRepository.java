package com.infosupport.Database;

import com.infosupport.domain.Cursus;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raymond Phua on 10-10-2016.
 */
@NoArgsConstructor
public class CursusRepository extends Database {

    public List<Cursus> getAllCursussen() {
        getConnection();

        List<Cursus> cursussen = new ArrayList<>();
        try {
            String query = "SELECT * FROM CURSUS";// c JOIN INSCHRIJVING sd ON c.CODE = sd.CURSUSID";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            System.out.println("\n Executing query: " + query);
            ResultSet rset = preparedStatement.executeQuery();

            while(rset.next()) {
                cursussen.add(mapper.mapToCursus(rset));
            }
            //cursussen = mapToCursusList(rset);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cursussen;
    }

    public void createCursus(Cursus cursus, List<LocalDate> startDatums) {
        getConnection();

        try {
            String query = "INSERT INTO CURSUS (CODE, TITEL, DUURTIJD) VALUES(?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, cursus.getCode());
            preparedStatement.setString(2, cursus.getTitel());
            preparedStatement.setInt(3, cursus.getDuurtijd());

            System.out.println("Executing query: " + query);
            preparedStatement.executeQuery();

            for (LocalDate localDate : startDatums) {
                Date date = Date.valueOf(localDate);

                String queryDatum = "INSERT INTO INSCHRIJVING (START_DATUM, CURSUSID) VALUES(?, ?)";

                PreparedStatement preparedStatement1 = conn.prepareStatement(queryDatum);
                preparedStatement1.setDate(1, date);
                preparedStatement1.setString(2, cursus.getCode());

                System.out.println("Executing query: " + queryDatum);
                preparedStatement1.executeQuery();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

//    private static List<Cursus> mapToCursusList(ResultSet rset) throws SQLException {
//        Map<String, Cursus> foundCursus = new HashMap<>();
//
//        while (rset.next()) {
//            Cursus existingCursus = foundCursus.get(rset.getString("code"));
//
//            Cursus cursus = mapToCursus(rset, existingCursus);
//
//            foundCursus.put(rset.getString("code"), cursus);
//        }
//
//        return foundCursus.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
//    }
}
