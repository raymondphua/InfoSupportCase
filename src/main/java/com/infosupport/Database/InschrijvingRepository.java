package com.infosupport.Database;

import com.infosupport.domain.Bedrijf;
import com.infosupport.domain.Cursist;
import com.infosupport.domain.Cursus;
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
 * Created by Raymond Phua on 12-10-2016.
 */
@NoArgsConstructor
public class InschrijvingRepository extends Database {

    public List<Inschrijving> getAllInschrijvingen() {
        getConnection();

        List<Inschrijving> inschrijvingen = new ArrayList<>();
        try {

            String query = "SELECT * FROM INSCHRIJVING i JOIN CURSUS c ON i.CURSUSID = c.CODE JOIN CURSISTS cs ON i.CURSISTID = cs.ID";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            System.out.println("\n Executing query: " + query);
            ResultSet rset = preparedStatement.executeQuery();

            while(rset.next()) {
                Inschrijving inschrijving = mapper.mapToInschrijving(rset, inschrijvingen);
                long count = inschrijvingen.stream().filter(i -> i.equals(inschrijving)).count();
                if (count == 0) {
                    inschrijvingen.add(inschrijving);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return inschrijvingen;
    }

    public void registerCursist(Cursus cursus, LocalDate startDatum, Cursist cursist) {
        getConnection();

        try {
            String query = "INSERT INTO INSCHRIJVING (START_DATUM, CURSUSID, CURSISTID) VALUES(?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setDate(1, Date.valueOf(startDatum));
            preparedStatement.setString(2, cursus.getCode());
            preparedStatement.setInt(3, cursist.getId());

            System.out.println("Executing query: " + query);
            preparedStatement.executeQuery();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
