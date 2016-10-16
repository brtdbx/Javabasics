package com.mycompany.javabasics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.regex.Pattern;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager; // do not import java.util.logging.Level at the same time!

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(App.class);

        App controller = new App();
        try {
            // -----------------------
            controller.arrayBeispiel();
            controller.hashMapBeispiel();
            controller.regExpressions();
//            controller.loggingBeispiel();
            //controller.holeUmgebung();
            //controller.leseAusDatei();
            //controller.javascriptEngineBenutzen();
//            controller.aufwaertsRundenAufVolle500();
//            controller.pruefZiffernBeispiel();
//            controller.joinStringsWithCommaIfNotEmpty();
//            controller.joinExamplesJDK7();
//            controller.joinExamples();
            controller.someMathSomelambda();
//            controller.printUtf8CharTable();
            //controller.showTextProps();
            // -----------------------
        } catch (Exception ex) {
            /*
              catching all exceptions like this is really dirt but I want to
              keep the methods code exception clean - for educational reasons -
              (your IDE will show you what exceptions are to be handled with)
             */
            logger.error("Nicht schön: Controller-Exception:", ex);
        }
    }

    public void showTextProps() {

        //String tmp = System.getProperties().toString();
        //System.out.println("" + tmp);
        System.out.println("" + Texter.getString("itemservice.item.added", "lksskd", "pokk", "hjh"));
        System.out.println("" + Texter.getString("anrede01", " Dr.", "Müller"));
        System.out.println("" + Texter.getString("anrede02", " Dr.", "Müller"));
        System.out.println("" + Texter.getString("anrede02", "", "Müller"));
    }

    public void printUtf8CharTable() throws IOException {

        // muss in file schreiben, console kann nicht utf8 anzeigen
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("outfilename"), "UTF-8"));
        out.write("привет");
        char c;
        for (int i = 33; i < 1024; i++) {
            String hex = Integer.toHexString(i);
            c = (char) i;
            out.write("\n" + hex + " \t " + c);
        }
//        PrintStream out = new PrintStream(System.out, true, "UTF-8");
//        char c;
//
//        new PrintStream(System.out, true, "UTF-8").println("привет");
//
//        for (int i = 2309; i < 2500; i++) {
//            String hex = Integer.toHexString(i);
//            c = (char) i;
//            System.out.println(hex + " = " + c);
//        }
        out.close();
    }

    /**
     * ########################## regExpressions ############################
     */
    public void splitTest() {

        String patternStr, line;
        // splitting a comma-separated multiline string but ignoring commas in quotes
        patternStr = "(?s),(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        line = "Schlafen,Trinken,usw.,Essen=\"Bananen,Äpfel,lange Liste,...,\nMüsli\",d;Sonstiges=\"work,travel\", abx";

        System.out.println("line = " + line);
        String[] tokens = line.split(patternStr);
        for (String t : tokens) {
            System.out.println("> " + t);
        }

        System.out.println("matches = " + line.matches(patternStr));
    }

    public void regExpressions() {

        /*
          Example: email-Validation with regular expressions emailPattern =
          [A-Za-z0-9_]+(?:[\.\-][ A-Za-z0-9_]+)*@[
          A-Za-z0-9_]{1}[[A-Za-z0-9_].\-]*\.[A-Za-z]{2,} [A-Za-z0-9_] are the
          "Word characters", i.e.letters, numbers and underscores, and can be
          abbreviated with \w so this emailPattern may be rewritten as
          emailPattern = [\w]+(?:[\.\-][\w]+)*@[\w]{1}[\w.\-]*\.[A-Za-z]{2,} As
          a Java string one must \ with another escape character \. So the
          final emailPattern is: emailPattern =
          "[\\w]+(?:[\\.\\-][\\w]+)*@[\\w]{1}[\\w.\\-]*\\.[A-Za-z]{2,}"
         */
        String emailPattern = "[\\w]+(?:[\\.\\-][\\w]+)*@[\\w]{1}[\\w.\\-]*\\.[A-Za-z]{2,}";
        String multilineCheckPattern1 = "[[ \\t\\r\\n\\v]\\p{Print}üÜäÄöÖß\\§&&[^&<>]]*";

        System.out.println(Pattern.matches(multilineCheckPattern1, "hallo 1     sdsf \\n \\t sfs"));

        System.out.println("Pattern to match: " + emailPattern);

        String[] emailStringsToTest = {
                "test@test.de",
                "test@server-dest.de",
                "vorname.nachname@domain.com",
                "vorname.nachname@sub.domain.com",
                "vorname.nachname@s123-sub.123-domain.com",
                "test@@test.de", //zuviele @
                "test@test@test.de", //zuviele @
                "test.de", //unvollständig
                "test@test", //unvollständig
                "te st@test.de", //leerzeichen
                "test@test test.de", //leerzeichen
                "est@testtest.d e", //leerzeichen
                "geüäöÜÄÖhale@test.ru" //umlaut
        };

        for (int i = 0; i < emailStringsToTest.length; i++) {
            String emailStringToTest = emailStringsToTest[i];
            System.out.println(String.format("%4d) ", i) + emailStringToTest
                    + " matches: " + Pattern.matches(emailPattern, emailStringToTest));
        }
    }

    public void someBigDecimal() {

        BigDecimal x1, x2, x1Rounded, x2Rounded, result, resultRounded;
        x1 = new BigDecimal(10.0015);
        x2 = new BigDecimal(20.0014);
        x1Rounded = x1.setScale(3, BigDecimal.ROUND_HALF_UP);
        x2Rounded = x2.setScale(3, BigDecimal.ROUND_HALF_UP);

        Double d = 1e-34;
        BigDecimal x3 = new BigDecimal(d);
        BigDecimal x4 = (new BigDecimal(BigInteger.ONE)).scaleByPowerOfTen(-34);

        System.out.println("x3=" + x3 + "\nx3.unscaledValue()=" + x3.unscaledValue() + "\nx3.scale()=" + x3.scale());
        System.out.println("x4=" + x4 + "\nx4.unscaledValue()=" + x4.unscaledValue() + "\nx4.scale()=" + x4.scale());

        BigDecimal x5 = x4.subtract(x3);
        System.out.println("x5=" + x5.toEngineeringString());

        System.out.println("x1=" + x1);
        System.out.println("x2=" + x2);
        System.out.println("x1Rounded=" + x1Rounded);
        System.out.println("x2Rounded=" + x2Rounded);
        result = x1.add(x2);
        System.out.println("x1+x2 = " + result);
        resultRounded = result.setScale(3, BigDecimal.ROUND_HALF_UP);
        System.out.println("resultRounded=" + resultRounded);

    }

    public void someMathSomelambda() {

        double startX = 0;
        double endX = 10;
        double steps = 100;
        double stepWidth = (endX - startX) / steps;

        DoubleUnaryOperator myFunction = Math::sin;
        DoubleFunction myFunction2 = (double x) -> Math.sin(x * x) * Math.cos(x * x);

        System.out.println("someRekursion " + someRekursion(100.00));
        System.out.println("recursiveFunction " + recursiveFunction(x -> x * x, 100.00));

        double x1 = startX;
        double x2, res1, res2;
        for (int i = 0; i < steps; i++) {
            x2 = x1 + stepWidth;
            res1 = Calculator.calcSomething(myFunction, x1, x2);
            res2 = Calculator.calcSomething2(myFunction2, x1, x2);
            System.out.println("x1, x2, res1, res2:" + x1 + ", " + x2 + "," + res1 + ", " + res2 + ", myFunction(x1) " + myFunction.applyAsDouble(x1));
            x1 = x2;

        }

    }

    // Type: (f, x) -> F(f, x)
    public static double recursiveFunction(DoubleUnaryOperator f, double x) {

        if (x < 1) {
            return 0;
        } else {
            return x * x + f.applyAsDouble(x - 1);
        }

    }

    // Type: x -> f(x) 
    public static double someRekursion(double x) {

        if (x < 1) {
            return 0;
        } else {
            return x + someRekursion(x - 1);
        }

    }


    static class Calculator {

        private static double calcSomething(DoubleUnaryOperator func, double x1, double x2) {

            double y1 = func.applyAsDouble(x1);
            double y2 = func.applyAsDouble(x2);

            return (y2 - y1) / (x2 - x1);
        }

        private static double calcSomething2(DoubleFunction func, double x1, double x2) {

            double y1, y2;
            y1 = (Double) func.apply(x1);
            y2 = (Double) func.apply(x2);

            return (y2 - y1) / (x2 - x1);
        }

    }

    public void joinExamplesJDK7() {

        String separator = "; ";
        String XML_EURO_SIGN = "&#x20AC;";
        String XML_NO_BREAK_SPACE_NARROW = "&#202F;";
        String XML_NO_BREAK_SPACE = "&#A0;";

        Boolean titelVorhanden = Boolean.TRUE;
        String titel = "Dr.";
        String[] agbTextBausteine = {"Sehr", "geehrter Herr", "geehrte Frau", titel, "Oberbontze"};
        Boolean[] abgTxtBedingungen = {Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, titelVorhanden, Boolean.TRUE};

        separator = " ";
        String joinedString1 = unconditionalJoinerJDK7(agbTextBausteine, separator);
        System.out.println("joinExamplesJDK7 - joinedString: " + joinedString1);

        String joinedString2 = conditionalJoinerJDK7(agbTextBausteine, abgTxtBedingungen, separator);
        System.out.println("joinExamplesJDK7- joinedString: " + joinedString2);

    }

    private String unconditionalJoinerJDK7(String[] txtBausteine, String separator) {

        return String.join(separator, txtBausteine);
    }

    private String conditionalJoinerJDK7(String[] txtBausteine, Boolean[] txtBedingungen, String separator) {

        org.apache.commons.lang.text.StrBuilder joined = new org.apache.commons.lang.text.StrBuilder();
        List<String> relevantList = new ArrayList<>();
        for (int i = 0; i < txtBausteine.length; i++) {
            if (txtBedingungen[i].equals(Boolean.TRUE)) {
                relevantList.add(txtBausteine[i]);
            }
        }

        joined = joined.appendWithSeparators(relevantList, separator);

        return joined.toString();
    }

    public void joinExamples() {

        String separator = "; ";
        // "AgbReise&#160;12" - non breaking space to fo?

        String[] agbTextBausteine = {"AgbReise 12", "AgbHP 23", "AgbHausrat 14", "AgbGlas 15", "AgbUnf 16"};
        Boolean[] abgTxtBedingungen = {Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE};

        String joinedString1 = unconditionalJoiner(agbTextBausteine, separator);
        System.out.println("joinExamples - joinedString: " + joinedString1);

        String joinedString2 = conditionalJoiner(agbTextBausteine, abgTxtBedingungen, separator);
        System.out.println("joinExamples - joinedString: " + "(" + joinedString2 + ")");

    }

    private String unconditionalJoiner(String[] txtBausteine, String separator) {

        StringJoiner joiner = new StringJoiner(separator);
        for (String txtBaustein : txtBausteine) {
            joiner.add(txtBaustein);
        }
        return joiner.toString();
    }

    private String conditionalJoiner(String[] txtBausteine, Boolean[] txtBedingungen, String separator) {

        StringJoiner joiner = new StringJoiner(separator);
        for (int i = 0; i < txtBausteine.length; i++) {
            if (txtBedingungen[i].equals(Boolean.TRUE)) {
                joiner.add(txtBausteine[i]);
            }
        }
        return joiner.toString();
    }

    public void joinStringsWithCommaIfNotEmpty() {

        String vorname = "Max";
        String nachname = "R.";
        Calendar gebDat = new GregorianCalendar(2000, 5, 27);

        String displayName = Utils123.joinSeparated(vorname, nachname, ", ");

        displayName = Utils123.joinSeparated(displayName, Utils123.calendarToDateString(gebDat), ", ");

        System.out.println("displayName: " + displayName);

    }

    /**
     * ########################## arrayBeispiel ################################
     */
    //public static void test(){};
    public void arrayBeispiel() {

        class EduDummy {

            public double skalarProdukt(double[] x, double[] y) {
                double result = 0.0;
                for (int i = 0; i < y.length; i++) {
                    result += x[i] * y[i];
                }
                return result;
            }

            public String vector2String(double[] x) {
                String vector2String = "(";

                for (int i = 0; i < x.length - 1; i++) {
                    vector2String = vector2String + x[i] + ",";
                }
                vector2String = vector2String + x[x.length - 1] + ")";
                return vector2String;
            }

            public String matrix2String(double[][] m) {
                String matrix2String = "";

                int numOfCols = m[0].length; // works as all rows have same number of col values
                int numOfRows = m.length; // works
                for (double[] aM : m) {
                    for (int j = 0; j < numOfCols; j++) {
                        String tmp = String.format("%1$,8.2f", aM[j]);
                        matrix2String = matrix2String + tmp;
                    }
                    matrix2String += System.getProperty("line.separator");
                }
                return matrix2String;
            }

        }

        double[] v1 = new double[3]; // length is 3, indices 0,1,2,3
        v1[0] = 1;
        v1[1] = 2;
        v1[2] = 4;

        double[] v2 = {2, 3, 4, 6}; // length is 4, indices 0,1,2,3
        double[] v3 = {1, 2, 3, 8};

        System.out.println("v1=" + (new EduDummy()).vector2String(v1));
        System.out.println("v2=" + (new EduDummy()).vector2String(v2));
        System.out.println("v3=" + (new EduDummy()).vector2String(v3));
        System.out.println("v1.length:" + v1.length + "v2.length:" + v2.length
                + " Arrays.equals(v1, v2):" + Arrays.equals(v1, v2));
        System.out.println("skalarProdukt v2*v3=" + (new EduDummy()).skalarProdukt(v2, v3));

        double[][] matrix = new double[3][4]; // defines a 3-row, 4-col matrix

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = (i + 1) * 10 + j + 1;
            }
        }

        double[] rowOne = matrix[0]; // getting a row is easy ...
        System.out.println("rowOne=" + (new EduDummy()).vector2String(rowOne));

        System.out.println((new EduDummy()).matrix2String(matrix));

    }

//        private boolean vergleiche(HashMap map, String param, Boolean neuerWert) {
//            Boolean alterWert=null;
//            boolean altNeuSindVerschieden = true;
//            if (map.containsKey(param)){
//                alterWert = (Boolean) map.get(param);
//
//                if (alterWert == neuerWert){
//                    altNeuSindVerschieden = false;
//                    map.pu
//                }
//
//            } else {
//                Object put = map.put(param, neuerWert);
//            }
//
//
//            Boolean alterParam = map.containsKey(param);
//
//
//
//    }

    /**
     * ########################## hashMapBeispiel #############################
     */
    public void hashMapBeispiel2() {


        HashMap map = new HashMap();

        map.put(2, "2");
        map.put(3, "3");
        Integer st1 = 0;


        for (Object key : map.keySet()) {
            System.out.println("key: " + key + " value: " + map.get(key).toString());
        }

        map.remove(3);
        System.out.println("Is HashMap is empty: " + map.isEmpty());
        System.out.println("Size of Map: " + map.size());
        System.out.println("Does HashMap contains 2 as key: " + map.containsKey(2));
        System.out.println("Does HashMap contains 3 as key: " + map.containsKey(3));
        System.out.println("Does HashMap contains st1:" + map.containsValue(st1));
        System.out.println("map.get(1):" + map.get(1));
    }

    /**
     * ########################## hashMapBeispiel #############################
     */
    public void hashMapBeispiel() {

        // trying builder pattern here as well ... fluent, perhaps a little overkill
        Student.Builder studentBuilder = new Student.Builder();

        Student st1 = studentBuilder
                .name("Max")
                .geburtsdatum(new GregorianCalendar(2000, 6, 27))
                .matrikelNummer("0001")
                .build();

        Student st2 = studentBuilder
                .name("Hanna")
                .geburtsdatum(new GregorianCalendar(2005, 9, 27))
                .matrikelNummer("0002")
                .build();

        HashMap map = new HashMap();

        map.put(1, st1);
        map.put(2, st2);
        map.put(3, st2);

        for (Integer key : (Iterable<Integer>) map.keySet()) {
            System.out.println("key: " + key + " value: " + map.get(key).toString());
        }

        map.remove(3);
        System.out.println("Is HashMap is empty: " + map.isEmpty());
        System.out.println("Size of Map: " + map.size());
        System.out.println("Does HashMap contains 2 as key: " + map.containsKey(2));
        System.out.println("Does HashMap contains 3 as key: " + map.containsKey(3));
        System.out.println("Does HashMap contains st1:" + map.containsValue(st1));
        System.out.println("map.get(1):" + map.get(1));
    }

    /**
     * ########################## leseAusDatei ###############################
     *
     * @throws java.lang.Exception
     */
    public void leseAusDatei() throws Exception {

        /*
          aus Datei lesen mit InputStreamReader auf FileInputStream
         */
        String pathAndFilename = "C:\\800_fix\\400_JavaDev\\devProjects\\Javabasics\\src\\main\\java\\com\\mycompany\\javabasics\\testrules.drl";
        FileInputStream fileInputStream = null;
        fileInputStream = new FileInputStream(pathAndFilename);
        Reader inputStreamReader = new InputStreamReader(fileInputStream);

        //aus Datei lesen
        byte zeichen;
        String text = "";

        do {
            zeichen = (byte) inputStreamReader.read();
            System.out.print(zeichen + " ");
            text += (char) zeichen;
        } while (zeichen != -1);
        inputStreamReader.close();

        System.out.println();
        System.out.println(text);

        /*
          aus Datei lesen mit BufferedReader auf FileReader
         */
        FileReader fileReader = null;
        String returnValue = null;
        fileReader = new FileReader(pathAndFilename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            returnValue += line + "\n";
        }
        fileReader.close();
        System.out.println(returnValue);
    }

    /**
     * ########################## holeUmgebung ##############################
     */
    public void holeUmgebung() {

        /*
          Umgebung mit System.getenv abfragen
         */
        System.out.println("System.getenv(\"PATH\") = " + System.getenv("PATH"));
    }

    /**
     * ########################## loggingBeispiel #############################
     */
    public void loggingBeispiel() {

        /*
          loggingBeispiel - Achtung App uses slf4j as logging API over jul
         */
        final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(App.class);

        logger.trace("Hello World!");
        logger.debug("How are you today?");
        logger.info("I am fine.");
        logger.warn("I love programming.");
        logger.error("I am programming.");
        //LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        //lc.setLoggingLevel(ch.qos.logback.classic.Level.DEBUG);
        logger.info("info msg");
        logger.debug("debug msg");

        String jft_id = "112";
        String jft_symbol = "square";
        logger.debug("Processing trade with id: {} and symbol : {} ", jft_id, jft_symbol);

    }

    /**
     * ########################## javascriptEngineBenutzen ####################
     */
    public void javascriptEngineBenutzen() throws Exception {

        /*
          javascriptEngineBenutzen
         */
        ScriptEngine e = new ScriptEngineManager().getEngineByName("js");
        e.put("x", "hanna");
        e.eval("print('Hello ' + x + '!')");
        e.put("x", 3);
        String myFunction = "Math.sin(Math.cos(x))";
        double dw2;
        dw2 = ((Number) e.eval(myFunction)).doubleValue();
        System.out.println("ScriptEngine sagt: dw2 = " + myFunction + " = " + dw2);

    }

    public void aufwaertsRundenAufVolle500() {

        int gerundet;

        for (int i = 50; i < 6000; i = i + 50) {
            gerundet = auf500Aufgerundet(i);
            System.out.println(i + " --> " + gerundet);
        }
    }

    private int auf500Aufgerundet(int nichtGerundet) {
        return 500 * (1 + (nichtGerundet - 1) / 500);
    }

    private Integer berechnePruefziffer(String wert) {
        /*
         berechnet die PrÃƒÆ’Ã‚Â¼fziffer modulo 11 fÃƒÆ’Ã‚Â¼r Zahlen und Zeichenketten
         beliebiger Länge
         */

        int MODULO_6 = 6;
        int MODULO_10 = 10;
        int MODULO_11 = 11;

        int n = 0;
        int len = wert.length();

        for (int i = 0;
             i < len;
             i++) {
            n += Character.getNumericValue(wert.charAt(len - 1 - i)) * (2 + (i % MODULO_6));
        }

        return (MODULO_11 - (n % MODULO_11)) % MODULO_10;
    }

    private void pruefZiffernBeispiel() {

        System.out.println("pruefziffer von '1234567': " + berechnePruefziffer("1234567"));
        System.out.println("pruefziffer von '123 4567': " + berechnePruefziffer("123 4567"));
        System.out.println("pruefziffer von '1111111': " + berechnePruefziffer("1111111"));
        System.out.println("pruefziffer von 'Johanna':" + berechnePruefziffer("Johanna"));
        System.out.println("pruefziffer von 'Johanna und Max':" + berechnePruefziffer("Johanna und Max"));

    }

}
