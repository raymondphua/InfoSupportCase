package com.infosupport.Database;

import com.infosupport.domain.Cursist;
import com.infosupport.domain.Cursus;
import com.infosupport.domain.Factuur;
import com.infosupport.domain.Inschrijving;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raymond Phua on 11-10-2016.
 */
@NoArgsConstructor
public class FactuurRepository extends Database{

    public List<Factuur> getAllFacturen() {
        getConnection();

        List<Factuur> facturen = new ArrayList<>();
        try {
            String query = "SELECT * FROM INSCHRIJVING i JOIN CURSUS c ON i.CURSUSID = c.CODE " +
                    "JOIN CURSISTS cs ON i.CURSISTID = cs.ID";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            System.out.println("\n Executing query: " + query);
            ResultSet rset = preparedStatement.executeQuery();

            setFacturen(rset, facturen);
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return facturen;
    }

    public List<Factuur> getFacturenFromWeek(int week) {
        getConnection();

        List<Factuur> facturen = new ArrayList<>();
        try {
            String query = "SELECT * FROM INSCHRIJVING i JOIN CURSUS c ON i.CURSUSID = c.CODE " +
                    "JOIN CURSISTS cs ON i.CURSISTID = cs.ID WHERE to_char(START_DATUM, 'WW') = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, week);
            System.out.println("\n Executing query: " + query);
            ResultSet rset = preparedStatement.executeQuery();

            setFacturen(rset, facturen);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return facturen;
    }

    public List<Factuur> getFacturenFromCursist(Cursist cursist) {
        getConnection();

        List<Factuur> facturen = new ArrayList<>();
        try {
            String query = "SELECT * FROM FACTUUR f JOIN INSCHRIJVING i ON f.INSCHRIJVINGID = i.id " +
                    "JOIN CURSUS c ON i.CURSUSID = c.CODE JOIN CURSISTS cs ON i.CURSISTID = cs.ID " +
                    "WHERE CURSISTID = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, cursist.getId());
            System.out.println("\n Executing query: " + query);
            ResultSet rset = preparedStatement.executeQuery();

            setFacturen(rset, facturen);
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return facturen;
    }

    public void createFactuur(Factuur factuur) {
        getConnection();

        try {
            for (Inschrijving inschrijving : factuur.getInschrijvingen()) {

                String query = "INSERT INTO FACTUUR (INSCHRIJVINGID) VALUES(?)";

                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement.setInt(2, inschrijving.getId());

                System.out.println("Executing query: " + query);
                preparedStatement.executeQuery();
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void setFacturen(ResultSet rset, List<Factuur> facturen) throws SQLException{
        while(rset.next()) {
            Factuur factuur = mapper.mapToFactuur(rset, facturen);
            long count = facturen.stream().filter(f -> f.equals(factuur)).count();
            if (count == 0) {
                facturen.add(factuur);
            }
        }
    }
}
