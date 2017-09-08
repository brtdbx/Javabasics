package com.mycompany.javabasics;

import java.util.Calendar;
import java.util.Objects;

public class VergleichMichMalClass {

    private int geschlecht;
    private Boolean istAuftraggeber;
    private Calendar geburtsdatum;

    private String name;
    private String vorname;

    public VergleichMichMalClass(int geschlecht, Boolean istAuftraggeber, Calendar geburtsdatum, String name, String vorname) {
        this.geschlecht = geschlecht;
        this.istAuftraggeber = istAuftraggeber;
        this.geburtsdatum = geburtsdatum;
        this.name = name;
        this.vorname = vorname;
    }

    public void setGeschlecht(int geschlecht) {
        this.geschlecht = geschlecht;
    }

    public void setIstAuftraggeber(Boolean istAuftraggeber) {
        this.istAuftraggeber = istAuftraggeber;
    }

    public void setGeburtsdatum(Calendar geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public boolean istGleichAufNameVornameGeburtsdatum(VergleichMichMalClass otherObject) {
        if (otherObject == null) {
            return false;
        }

        // Alle Vergleichswerte sind null! Diesen Fall ausschliessen - kann man nicht sinnvoll vergleichen.
        if ((this.name == null || this.vorname == null || this.geburtsdatum == null) && (otherObject.name == null || otherObject.vorname == null || otherObject.geburtsdatum == null)) {
            return false;
        }
        int thisHash = this.ermittleVornameNameGeburtsdatumHash();
        int otherHash = otherObject.ermittleVornameNameGeburtsdatumHash();

        return thisHash == otherHash;
    }

    public int ermittleVornameNameGeburtsdatumHash() {

        return Objects.hash(name, vorname, geburtsdatum);

    }

}
