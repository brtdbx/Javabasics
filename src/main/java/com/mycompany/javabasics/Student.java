package com.mycompany.javabasics;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

class Student {

    private String name;
    private GregorianCalendar geburtsdatum;
    private String matrikelNummer;

    Student(String name, GregorianCalendar geburtsdatum, String matrikelNummer) {
        this.name = name;
        this.geburtsdatum = geburtsdatum;
        this.matrikelNummer = matrikelNummer;
    }

    Student() {
    }

    private Student(Student.Builder builder) {
        this.name = builder.name;
        this.geburtsdatum = builder.geburtsdatum;
        this.matrikelNummer = builder.matrikelNummer;
    }

    public static class Builder {

        private String name = "";
        private GregorianCalendar geburtsdatum = null;
        private String matrikelNummer = "";

        public Student.Builder name(String name) {
            this.name = name;
            return this;
        }

        public Student.Builder geburtsdatum(GregorianCalendar geburtsdatum) {
            this.geburtsdatum = geburtsdatum;
            return this;
        }

        public Student.Builder matrikelNummer(String matrikelNummer) {
            this.matrikelNummer = matrikelNummer;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    public String getName() {
        return name;
    }

    public GregorianCalendar getGeburtsdatum() {
        return geburtsdatum;
    }

    public String getMatrikelNummer() {
        return matrikelNummer;
    }

    @Override
    public String toString() {
        String gebDatumAlsString = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY");
        if (geburtsdatum != null) {
            gebDatumAlsString = sdf.format(geburtsdatum.getTime());
        }
        return "Student{" + "name=" + name + ", geburtsdatum=" + gebDatumAlsString
                + ", matrikelNummer=" + matrikelNummer + '}';
    }

}
