package com.infosupport.domain;

import com.infosupport.exceptions.ImportException;
import com.infosupport.exceptions.IncorrectFormatException;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Raymond Phua on 11-10-2016.
 */
@NoArgsConstructor
public class ImportReader {

    public Map<Cursus, List<LocalDate>> importCursus(String file) throws ImportException {
        Map<Cursus, List<LocalDate>> cursusDates = new HashMap<>();

        String cleanFile = file.replace("\r", "");
        String[] splitForEachCursus = cleanFile.split("\n\n");

        for (String singleCursus : splitForEachCursus) {
            String[] splitByNewLines = singleCursus.split("\n");

            boolean formatCorrect = correctFormat(splitByNewLines);

            if (!formatCorrect) {
                throw new ImportException(new IncorrectFormatException("Incorrect format"));
            }

            String code = "";
            String titel = "";
            int duur = 0;
            LocalDate date = null;

            for (int i = 0; i < 4; i++) {
                int indexOfSplitColonSpace = splitByNewLines[i].indexOf(": ") + 2;
                String value = splitByNewLines[i].substring(indexOfSplitColonSpace);
                switch (i) {
                    case 0:
                        titel = value;
                        break;
                    case 1:
                        code = value;
                        break;
                    case 2:
                        duur = Character.getNumericValue(value.charAt(0));
                        break;
                    case 3:
                        date = parseToDate(value);
                        break;
                }
            }

            Cursus cursus = new Cursus(code, titel, duur);

            if (!cursusDates.containsKey(cursus)) {
                cursusDates.put(cursus, new ArrayList<>());
            }

            cursusDates.get(cursus).add(date);
        }

        return cursusDates;
    }

    private boolean correctFormat(String[] cursus) {
        if (cursus.length == 4) {
            if (!cursus[0].contains("Titel")) {
                return false;
            }
            if (!cursus[1].contains("Cursuscode")) {
                return false;
            }
            if  (!cursus[2].contains("Duur")) {
                return false;
            } else if (!cursus[2].contains("dagen")){
                return false;
            }
            if  (!cursus[3].contains("Startdatum")) {
                return false;
            } else {
                String[] split = cursus[3].split(": ");
                if (!split[1].matches("\\d{2}\\/\\d{2}\\/\\d{4}")) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    private LocalDate parseToDate(String s) {
        String[] split = s.split("/");

        int day = Integer.valueOf(split[0]);
        int month = Integer.valueOf(split[1]);
        int year = Integer.valueOf(split[2]);

        return LocalDate.of(year, month, day);
    }
}
