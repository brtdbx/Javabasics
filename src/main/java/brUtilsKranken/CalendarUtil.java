package brUtilsKranken;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class CalendarUtil {

    private static final int INT10 = 10;
    private static final int INT100 = 100;

    public static final int TTMMJJJJ = 1;
    public static final int JJJJMMTT = 2;
    public static final int TTMMJJ = 3;
    public static final int MMJJJJ = 4;

    private static final String[] MONATE = {"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November",
        "Dezember"};

    private CalendarUtil() {
    }

    public static int berechneAlterZumStichtag(LocalDate geburtstag, LocalDate stichtag) {

        int alter = Period.between(geburtstag, stichtag).getYears();

        if (alter <= 0 && geburtstag.isAfter(stichtag)) {
            // Stichtag liegt vor der Geburt - im Grunde ist das Alter noch nicht definiert ..
            // Die Alterszählung ist etwas komisch:
            // Da Alter 0 erst ab der Geburt startet, muss konsequenterweise das Periodeergebnis noch um 1 verringert werden
            // Alternative zum gewählten Vorgehen wäre nicht definiert ...
            alter--;
        }
        return alter;
    }

    public static int berechneAlterZumStichtag(Calendar geburtstag, Calendar stichtag) {
        return CalendarUtil.berechneAlterZumStichtag(calendarToLocalDate(geburtstag), calendarToLocalDate(stichtag));
    }

    public static int berechneAlterZumStichtag(Date geburtstag, Date stichtag) {
        return CalendarUtil.berechneAlterZumStichtag(dateToLocalDate(geburtstag), dateToLocalDate(stichtag));
    }

    public static String getGermanDateFormat(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

//    public static int berechneAlterZumStichtagWithJava7(Calendar geburtstag, Calendar stichtag) {
//        // Funktinioniert wohl - aber nur, wenn der Stichtag >= Geburtstag, was fachlich ausreichend ist
//        int gebJahr = geburtstag.get(Calendar.YEAR);
//        int stichtagJahr = stichtag.get(Calendar.YEAR);
//        int alter = stichtagJahr - gebJahr;
//
//        int stichttagMonat = stichtag.get(Calendar.MONTH);
//        int geburtstagMonat = geburtstag.get(Calendar.MONTH);
//        if (geburtstagMonat > stichttagMonat) { // this year can't be counted!
//            alter--;
//        } else if (geburtstagMonat == stichttagMonat) { // same month? check for day
//            int stichtagTag = stichtag.get(Calendar.DAY_OF_MONTH);
//            int geburtstagTag = geburtstag.get(Calendar.DAY_OF_MONTH);
//            if (geburtstagTag > stichtagTag) { // this year can't be counted!
//                alter--;
//            }
//        }
//
//        return alter;
//    }
    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate calendarToLocalDate(Calendar cal) {
        return cal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    // public static berechneAlterZumStichtag(Date )
    /**
     * Wandelt einen Calendar in einen GregorianCalendar
     *
     * @param cal ein Calender
     * @return ein GregorianCalendar
     */
    public static GregorianCalendar calendarToGregorianCalendar(Calendar cal) {
        GregorianCalendar gregCal = new GregorianCalendar();
        gregCal.setTime(cal.getTime());
        return gregCal;
    }

    /**
     * Gibt einen Calendar mit dem aktuellen Datum zurück. Die Zeit ist auf das
     * Minimum gesetzt.
     *
     * @return ein Calendar
     */
    public static Calendar getCurrentDateWithMinTime() {
        Calendar currentDate = Calendar.getInstance();
        setTimeToMinimum(currentDate);
        return currentDate;
    }

    /**
     * Gibt einen Calendar mit den übergebenen Werten jahr, monat und tag
     * zurück. Die Zeit ist auf das Minimum gesetzt.
     *
     * @param year das Jahr
     * @param month der Monat, beginnend bei 1
     * @param day der Tag beginnend bei 1
     *
     * @return ein Calendar
     */
    public static Calendar getDate(int year, int month, int day) {
        final Calendar cal = Calendar.getInstance();
        setTimeToMinimum(cal);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal;
    }

    /**
     * Erzeugt ein Calendar-Objekt mit der gewünschten Uhrzeit.
     *
     * @param hour Stunde (0-23)
     * @param minute Minute (0-59)
     * @param sec Sekunde (0-59)
     *
     * @return Calendar mit der gewünschten Uhrzeit, der Datumsanteil ist
     * undefiniert
     */
    public static Calendar getTime(int hour, int minute, int sec) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, sec);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    /**
     * Setzt die minimale Zeit im übergebenen Calendar.
     *
     * @param calendar in dem der Wert gesetzt wird
     * @return ein Calendar
     */
    public static Calendar setTimeToMaximum(Calendar calendar) {
        if (calendar != null) {
            setToMaximum(Calendar.HOUR_OF_DAY, calendar);
            setToMaximum(Calendar.MINUTE, calendar);
            setToMaximum(Calendar.SECOND, calendar);
            setToMaximum(Calendar.MILLISECOND, calendar);
        }
        return calendar;
    }

    /**
     * Setzt die minimale Zeit im übergebenen Calendar.
     *
     * @param calendar in dem der Wert gesetzt wird
     * @return ein Calendar
     */
    public static Calendar setTimeToMinimum(Calendar calendar) {
        if (calendar != null) {
            setToMinimum(Calendar.HOUR_OF_DAY, calendar);
            setToMinimum(Calendar.MINUTE, calendar);
            setToMinimum(Calendar.SECOND, calendar);
            setToMinimum(Calendar.MILLISECOND, calendar);
        }
        return calendar;
    }

    /**
     * Setzt den maximalen Wert eines Feldes im übergebenen Calendar.
     *
     * @param field die Nummer des Calendar Fields
     * @param calendar in dem der Wert gesetzt wird
     * @return ein Calendar
     */
    public static Calendar setToMaximum(int field, Calendar calendar) {
        calendar.set(field, calendar.getMaximum(field));
        return calendar;
    }

    /**
     * Setzt den minimalen Wert eines Feldes im übergebenen Calendar.
     *
     * @param field die Nummer des Calendar Fields
     * @param calendar in dem der Wert gesetzt wird
     * @return ein Calendar
     */
    public static Calendar setToMinimum(int field, Calendar calendar) {
        calendar.set(field, calendar.getMinimum(field));
        return calendar;
    }

    /**
     * Gibt true zurück wenn der field Wert den maximalen Wert hat.
     *
     * @param field das zu untersuchende Feld
     * @param calendar der zu prüfende Calendar
     * @return ein boolean
     */
    public static boolean isMaximum(int field, Calendar calendar) {
        return calendar.get(field) == calendar.getMaximum(field);
    }

    /**
     * Gibt true zurück wenn der field Wert den minimalen Wert hat.
     *
     * @param field das zu untersuchende Feld
     * @param calendar der zu prüfende Calendar
     * @return ein boolean
     */
    public static boolean isMinimum(int field, Calendar calendar) {
        return calendar.get(field) == calendar.getMinimum(field);
    }

    /**
     * gibt true zurück wenn die Zeit den maximalen Wert hat.
     *
     * @param calendar der zu prüfende Calendar
     * @return ein boolean
     */
    public static boolean isTimeMaximum(Calendar calendar) {
        return isMaximum(Calendar.HOUR_OF_DAY, calendar) && isMaximum(Calendar.MINUTE, calendar) && isMaximum(Calendar.SECOND, calendar)
                && isMaximum(Calendar.MILLISECOND, calendar);
    }

    /**
     * gibt true zurück wenn die Zeit den minimalen Wert hat.
     *
     * @param calendar der zu prüfende Calendar
     * @return ein boolean
     */
    public static boolean isTimeMinimum(Calendar calendar) {
        return isMinimum(Calendar.HOUR_OF_DAY, calendar) && isMinimum(Calendar.MINUTE, calendar) && isMinimum(Calendar.SECOND, calendar)
                && isMinimum(Calendar.MILLISECOND, calendar);
    }

    /**
     * Berechnet die Tage, die das übergebene Datum in der Vergangenheit liegt.
     *
     * @param cal das andere Datum
     * @return das Alter in Tagen
     */
    public static int errechneVergangeneTage(Calendar cal) {
        Calendar now = Calendar.getInstance();
        return (int) (Math.abs(now.getTimeInMillis() - cal.getTimeInMillis()) / 86400000L);
    }

    /**
     * Berechnet das Alter in Jahren. Ist <code>otherDate</code> vor
     * <code>birthdate</code> wird -1 zurückgegeben.
     *
     * @param birthdate das Geburtsdatum
     * @param otherDate das andere Datum
     * @return das Alter in Jahren
     */
    public static int calculateAge(Calendar birthdate, Calendar otherDate) {
        int age;

        if (birthdate == null) {
            return -1;
        }

        if (otherDate.before(birthdate)) {
            return -1;
        }

        age = otherDate.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);

        if (otherDate.get(Calendar.MONTH) < birthdate.get(Calendar.MONTH)) {
            --age;
        } else if ((otherDate.get(Calendar.MONTH) == birthdate.get(Calendar.MONTH))
                && (otherDate.get(Calendar.DAY_OF_MONTH) < birthdate.get(Calendar.DAY_OF_MONTH))) {
            --age;
        }

        return age;
    }

    /**
     * Formatiert ein Calendar-Object lesbar im Format "YYYY.MM.DD HH:MM:SS,MS"
     *
     * @param cal ein Calendar-Object
     * @return ein Calendar als String aufbereitet
     */
    public static String getFormattedTimeStamp(Calendar cal) {
        String sepD = ".";
        String sepT = ":";
        String sb = String.valueOf(cal.get(Calendar.YEAR)) + sepD
                + get2Digit((1 + cal.get(Calendar.MONTH))) + sepD
                + get2Digit((cal.get(Calendar.DAY_OF_MONTH))) + " "
                + get2Digit((cal.get(Calendar.HOUR_OF_DAY))) + sepT
                + get2Digit((cal.get(Calendar.MINUTE))) + sepT
                + get2Digit((cal.get(Calendar.SECOND))) + ","
                + cal.get(Calendar.MILLISECOND);

        return sb;
    }

    /**
     * Formatiert ein Calendar-Object lesbar im Format "DD.MM.YYYY HH:MM:SS"
     *
     * @param cal ein Calendar-Object
     * @return ein Calendar als String aufbereitet
     */
    public static String getTimestampWithoutMilliseconds(Calendar cal) {
        String sepD = ".";
        String sepT = ":";
        String sb = get2Digit(cal.get(Calendar.DAY_OF_MONTH)) + sepD
                + get2Digit((1 + cal.get(Calendar.MONTH))) + sepD
                + (cal.get(Calendar.YEAR)) + " "
                + get2Digit((cal.get(Calendar.HOUR_OF_DAY))) + sepT
                + get2Digit((cal.get(Calendar.MINUTE))) + sepT
                + get2Digit((cal.get(Calendar.SECOND)));

        return sb;
    }

    /**
     * Die Methode gibt einen numerischen Wert mit mindestens 2 Stellen
     * zur&uuml;ck. Speziell f&uuml; Datum und Uhrzeit.
     *
     * @return String wert
     */
    private static String get2Digit(int in) {
        if (in < INT10) {
            return "0" + in;
        }
        return in + "";
    }

    /**
     * Die Methode gibt einen numerischen Wert mit mindestens 3 Stellen zurück.
     * Speziell für die Millisekunden.
     *
     * @return String wert
     */
    private static String get3Digit(int in) {
        if (in < INT10) {
            return "00" + in;
        } else if (in < INT100) {
            return "0" + in;
        }
        return in + "";
    }

    /**
     * Diese Methode ermittelt die aktuelle Zeit (TimeStamp) im Format
     * <code>YYYYMMDDhhmmssxxx</code>.
     *
     * @return Timestamp als String.
     */
    public static synchronized String getTimeStamp() {
        Calendar cal = Calendar.getInstance();
        String sb = String.valueOf(cal.get(Calendar.YEAR))
                + get2Digit(1 + cal.get(Calendar.MONTH))
                + get2Digit(cal.get(Calendar.DAY_OF_MONTH))
                + get2Digit(cal.get(Calendar.HOUR_OF_DAY))
                + get2Digit(cal.get(Calendar.MINUTE))
                + get2Digit(cal.get(Calendar.SECOND))
                + get3Digit(cal.get(Calendar.MILLISECOND));

        return sb;
    }

    /**
     * Diese Methode ermittelt die aktuelle Zeit im Format <code>hh:mm</code>.
     *
     * @return Uhrzeit als String.
     */
    public static synchronized String getUhrzeit() {
        Calendar cal = Calendar.getInstance();
        return getUhrzeit(cal);
    }

    /**
     * Diese Methode ermittelt die aktuelle Zeit im Format <code>hh:mm</code>.
     *
     * @return Uhrzeit als String.
     */
    public static String getUhrzeit(Calendar cal) {
        if (cal == null) {
            return null;
        }
        String sb = get2Digit(cal.get(Calendar.HOUR_OF_DAY))
                + ":"
                + get2Digit(cal.get(Calendar.MINUTE));
        return sb;
    }

    public static String getDefaultFormattetString(Calendar date, int format) {
        if (date == null) {
            return "";
        }
        return getFormattetString(date, format);
    }

    public static String getFormattetString(Calendar date, int format) {
        String tag = Integer.toString(date.get(Calendar.DATE));
        if (tag.length() == 1) {
            tag = "0" + tag;
        }

        String monat = Integer.toString(date.get(Calendar.MONTH) + 1);
        if (monat.length() == 1) {
            monat = "0" + monat;
        }

        String jahr = Integer.toString(date.get(Calendar.YEAR));

        switch (format) {
            case TTMMJJJJ:
                return tag + "." + monat + "." + jahr;
            case JJJJMMTT:
                return jahr + "-" + monat + "-" + tag;
            case MMJJJJ:
                return monat + "." + jahr;
            default:
                jahr = jahr.substring(2);
                return tag + "." + monat + "." + jahr;
        }
    }

    public static String getFormattetStringMitMonatLang(Calendar date) {
        String tag = Integer.toString(date.get(Calendar.DATE));
        if (tag.length() == 1) {
            tag = "0" + tag;
        }

        int monat = date.get(Calendar.MONTH);
        String monatString = "";

        if (monat < 12) {
            monatString = MONATE[monat];
        }

        String jahr = Integer.toString(date.get(Calendar.YEAR));

        return tag + ". " + monatString + " " + jahr;

    }

    public static String getFormattetStringOhneTrennzeichen(Calendar date, int format) {
        String tag = Integer.toString(date.get(Calendar.DATE));
        if (tag.length() == 1) {
            tag = "0" + tag;
        }

        String monat = Integer.toString(date.get(Calendar.MONTH) + 1);
        if (monat.length() == 1) {
            monat = "0" + monat;
        }

        String jahr = Integer.toString(date.get(Calendar.YEAR));

        if (format == TTMMJJJJ) {
            return tag + monat + jahr;
        }
        return jahr + monat + tag;
    }

    public static boolean isGregorianLeapYear(Calendar cal) {
        Calendar gregCal = GregorianCalendar.getInstance();
        gregCal.setTime(cal.getTime());
        gregCal.set(Calendar.DAY_OF_MONTH, 1);
        gregCal.set(Calendar.MONTH, 2);
        gregCal.getTime();
        gregCal.add(Calendar.DAY_OF_YEAR, -1);
        return String.format("%1$td", gregCal).equals("29");
    }

    public static Integer getFormattedIntegerJJJJMMTT(Calendar cal) {
        if (cal != null) {
            String tag = Integer.toString(cal.get(Calendar.DATE));
            if (tag.length() == 1) {
                tag = "0" + tag;
            }
            String monat = Integer.toString(cal.get(Calendar.MONTH) + 1);
            if (monat.length() == 1) {
                monat = "0" + monat;
            }
            String jahr = Integer.toString(cal.get(Calendar.YEAR));
            String datum = jahr + monat + tag;
            return Integer.valueOf(datum);
        }
        return null;
    }

    /**
     * Überprüft, ob die zwischen dem Datum1 und dem Datum2 mindestens die
     * Anzahl der übergebenen Monate liegt
     *
     * @param aelteresDatum das erste Datum
     * @param juengeresDatum das zweite Datum
     * @param monate Mindestabstand der beiden Datum Objekete in Monaten
     * @return true, wenn mindestens monate zwischen aelteresDatum und
     * juengeresDatum liegt
     */
    public static boolean berechneMonatsDifferenz(Calendar aelteresDatum, Calendar juengeresDatum, int monate) {
        if (aelteresDatum == null) {
            throw new IllegalArgumentException("Datum1 ist null");
        }
        if (juengeresDatum == null) {
            throw new IllegalArgumentException("Datum2 ist null");
        }
        if (monate < 0) {
            throw new IllegalArgumentException("monate sind < 0");
        }

        Calendar datumAelterNeu = (Calendar) aelteresDatum.clone();
        datumAelterNeu.add(Calendar.MONTH, monate);

        return datumAelterNeu.compareTo(juengeresDatum) <= 0;
    }

    /**
     * Überprüft, ob zwischen dem Datum1 und dem Datum2 mindestens die Anzahl
     * der übergebenen Tage liegt
     *
     * @param aelteresDatum das erste Datum
     * @param juengeresDatum das zweite Datum
     * @return true, wenn mindestens tage zwischen aelteresDatum und
     * juengeresDatum liegt
     */
    public static boolean berechneTagesDifferenz(Calendar aelteresDatum, Calendar juengeresDatum, int tage) {
        if (aelteresDatum == null) {
            throw new IllegalArgumentException("Datum1 ist null");
        }
        if (juengeresDatum == null) {
            throw new IllegalArgumentException("Datum2 ist null");
        }
        if (tage < 0) {
            throw new IllegalArgumentException("monate sind < 0");
        }

        Calendar datumJuengerNeu = (Calendar) juengeresDatum.clone();
        datumJuengerNeu.add(Calendar.DAY_OF_YEAR, tage);

        return datumJuengerNeu.compareTo(aelteresDatum) <= 0;
    }

    /**
     * Gibt true zurück wenn das erste Datum identisch mit dem zweiten Datum
     * ist. Die Zeit wird nicht berücksichtigt.
     *
     * @param cal1 der erste Calendar
     * @param cal2 der zweite Calendar
     * @return ein boolean
     */
    public static boolean isDateEqual(Calendar cal1, Calendar cal2) {
        return isFieldEqual(cal1, cal2, Calendar.YEAR) && isFieldEqual(cal1, cal2, Calendar.MONTH) && isFieldEqual(cal1, cal2, Calendar.DATE);
    }

    /**
     * Gibt true zurück wenn für das gewünschte Field das erste Datum identisch
     * mit dem zweiten Datum ist.
     *
     * @param cal1 der erste Calendar
     * @param cal2 der zweite Calendar
     * @param field das zu untersuchende Feld
     * @return ein boolean
     */
    public static boolean isFieldEqual(Calendar cal1, Calendar cal2, int field) {
        return (cal1 != null) && (cal2 != null) && (cal1.get(field) == cal2.get(field));
    }

    /**
     * Gibt true zurück wenn das erste Datum nach dem zweiten Datum liegt. Die
     * Zeit wird nicht berücksichtigt.
     *
     * @param cal1 der erste Calendar
     * @param cal2 der zweite Calendar
     * @return ein boolean
     */
    public static boolean isDateAfter(Calendar cal1, Calendar cal2) {
        return isFieldAfter(cal1, cal2, Calendar.YEAR) || (isFieldEqual(cal1, cal2, Calendar.YEAR) && isFieldAfter(cal1, cal2, Calendar.MONTH))
                || (isFieldEqual(cal1, cal2, Calendar.YEAR) && isFieldEqual(cal1, cal2, Calendar.MONTH) && isFieldAfter(cal1, cal2, Calendar.DATE));
    }

    /**
     * Gibt true zurück wenn für das gewünschte Field das erste Datum nach dem
     * zweiten Datum liegt.
     *
     * @param cal1 der erste Calendar
     * @param cal2 der zweite Calendar
     * @param field das zu untersuchende Feld
     * @return ein boolean
     */
    public static boolean isFieldAfter(Calendar cal1, Calendar cal2, int field) {
        return (cal1 != null) && (cal2 != null) && (cal1.get(field) > cal2.get(field));
    }

}
