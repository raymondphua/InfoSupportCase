package com.infosupport;

import com.infosupport.Database.CursistRepository;
import com.infosupport.Database.CursusRepository;
import com.infosupport.Database.FactuurRepository;
import com.infosupport.Database.InschrijvingRepository;
import com.infosupport.domain.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raymond Phua on 10-10-2016.
 */
public class CaseOneTester {

    public static void main(String[] args) {
        CursistRepository cursistRepository = new CursistRepository();
        CursusRepository cursusRepository = new CursusRepository();
        FactuurRepository factuurRepository = new FactuurRepository();
        InschrijvingRepository inschrijvingRepository = new InschrijvingRepository();

        cursistRepository.getAllCursisten();
        //CursusRepository.getAllCursussen();
        //cursistRepository.createCursist(new Particulier(0, "Test Particulier", "Teststraat x", "Testwoonplaats x", 27));

        cursusRepository.getAllCursussen();

        //Cursus cursusTest = new Cursus("test", "test", 3);
        LocalDate date = LocalDate.of(2016, 10, 10);
        LocalDate date2 = LocalDate.of(2016, 10, 21);
        List<LocalDate> dates = new ArrayList<>();
        dates.add(date);
        dates.add(date2);
        //cursusRepository.createCursus(cursusTest, dates);

        Factuur testFactuur = new Factuur(2);
        testFactuur.addInschrijving(new Inschrijving(3));
        testFactuur.addInschrijving(new Inschrijving(4));
        factuurRepository.getAllFacturen();
        //factuurRepository.createFactuur(testFactuur);

        //Cursist cursist = new Particulier(9, "test", "test", "test", 7);
        inschrijvingRepository.getAllInschrijvingen();
        //inschrijvingRepository.registerCursist(cursusTest, date, cursist);

        Cursist bedrijf = new Bedrijf(1, "Info Support", "Teststraat", "Testwoonplaats");

        Cursist offerte = new Bedrijf(2, 1);

        Cursist particulier1 = new Particulier(3, "Raymond", "Straat Raymond", "Woonplaats Raymond", 2);

        Cursist particulier2 = new Particulier(4, "Factuur x", "Straat x", "Woonplaats x", 2);

        Cursist particulier3 = new Particulier(5, "Factuur y", "Straat y", "Woonplaats y", 2);

        ((Bedrijf)offerte).addCursist(particulier1);
        ((Bedrijf)offerte).addCursist(particulier2);
        ((Bedrijf)offerte).addCursist(particulier3);

        ((Bedrijf)bedrijf).addCursist(offerte);

        Cursist alleenStaandeParticulier = new Particulier(6, "Alleen staand", "alleen staand", "alleen staand", 0);

        Cursus cursus = new Cursus("JAVA", "Java Programmeren", 4);
//        cursus.addStartDatumToCursus(LocalDate.of(2016, 10, 02));

        Inschrijving inschrijving = new Inschrijving();
        inschrijving.addCursus(cursus, LocalDate.of(2016, 10, 10));
        inschrijving.addCursisten(cursus, bedrijf);
        inschrijving.addCursisten(cursus, alleenStaandeParticulier);

        Factuur factuur = new Factuur();
        factuur.addInschrijving(inschrijving);
        factuur.printInfoOfWeek(41);

        factuur.printInfoOfCursist(alleenStaandeParticulier);
    }
}
