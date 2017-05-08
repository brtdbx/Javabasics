package com.mycompany.javabasics;

import brUtils.MathUtils;
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
import java.math.RoundingMode;
import java.util.*;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.regex.Pattern;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager; // do not import java.util.logging.Level at the same time!

// Math Utils from Apache
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import org.joda.time.DateTime;

// Logging
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.pmw.tinylog.Logger;


public class App {

    public static void main(String[] args) {
//        final Logger logger = LoggerFactory.getLogger(App.class);

        App controller = new App();
        try {
            // -----------------------
//            controller.arrayBeispiel();
//            controller.hashMapBeispiel();
//            controller.regExpressions();
            controller.loggingBeispiel();
            //controller.holeUmgebung();
            //controller.leseAusDatei();
            //controller.javascriptEngineBenutzen();
//            controller.aufwaertsRundenAufVolle500();
//            controller.pruefZiffernBeispiel();
//            controller.joinStringsWithCommaIfNotEmpty();
//            controller.joinExamplesJDK7();
//            controller.joinExamples();
//            controller.someMathSomelambda();
//            controller.printUtf8CharTable();
//            //controller.showTextProps();
//            controller.fragenTest3();
//            controller.someBigDecimal();
//            controller.someBigDecimal2();
            controller.apacheMathExample();
//            controller.runden();
//            controller.dioptrienTest();
//            controller.dateTimeTests();
            // -----------------------
        } catch (Exception ex) {
            /*
              catching all exceptions like this is really dirt but I want to
              keep the methods code exception clean - for educational reasons -
              (your IDE will show you what exceptions are to be handled with)
             */
            Logger.error("Nicht schön: Controller-Exception:", ex);
        }
    }

    public void apacheMathExample() {

// Create a real matrix with two rows and three columns, using a factory
// method that selects the implementation class for us.
        double[][] matrixData = {{1, 2, 3}, {4, 5, 6}};

        RealMatrix m = MatrixUtils.createRealMatrix(matrixData);

        System.out.println("" + Arrays.toString(m.getRow(0)));
        System.out.println("" + Arrays.toString(m.getColumn(0)));

        System.out.println("" + m.getRowVector(0));
        System.out.println("" + m.getColumnVector(0));

// One more with three rows, two columns, this time instantiating the
// RealMatrix implementation class directly.
        double[][] matrixData2 = {{1d, 2d}, {2d, 5d}, {1d, 7d}};
        RealMatrix n = new Array2DRowRealMatrix(matrixData2);

// Note: The constructor copies  the input double[][] array in both cases.
// Now multiply m by n
        RealMatrix p = m.multiply(n);
        System.out.println(p.getRowDimension());    // 2
        System.out.println(p.getColumnDimension()); // 2

// Invert p, using LU decomposition
        RealMatrix pInverse = new LUDecomposition(p).getSolver().getInverse();

        System.out.println("" + pInverse.toString());

    }

    public void dateTimeTests() {

        Calendar gebDat1 = new GregorianCalendar(2000, 6, 27);
        Calendar gebDat2 = new GregorianCalendar(2005, 9, 27);
        Calendar now = Calendar.getInstance(); // now
        // org.joda.time.DateTime
        // simply pass JDK Date or Calendar to a Joda-Time into the constructor:
        DateTime jodaDate1 = new DateTime(gebDat1);
        DateTime jodadate2 = new DateTime(gebDat2);
        DateTime jodaNow = new DateTime(now);

        System.out.println("dtNow: " + jodaNow);
        jodaDate1.isAfter(jodadate2);
        System.out.println(jodaDate1 + "isAfter " + jodadate2 + " :" + jodaDate1.isAfter(jodadate2));

        System.out.println("" + jodaDate1.toString());

        Calendar vb = new GregorianCalendar(2017, 1, 1);
        DateTime jodaVb = new DateTime(vb);
        DateTime dtTest = new DateTime(new GregorianCalendar(2017, 1, 16));

        boolean sameYearSameMonthNowLess16th = ((dtTest.getYear() == jodaVb.getYear())
                && (dtTest.getMonthOfYear() == jodaVb.getMonthOfYear())
                && (dtTest.getDayOfMonth() < 16));

        System.out.println("" + sameYearSameMonthNowLess16th);

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
        String emailRegex = "^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$"; // CPU, RAM Killer expression!

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
            "geüäöÜÄÖhale@test.ru", //umlaut
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa!",
            "aabcr@s"
        };
        long started = System.currentTimeMillis();
        for (int i = 0; i < emailStringsToTest.length; i++) {
            String emailStringToTest = emailStringsToTest[i];
            System.out.println(String.format("%4d) ", i) + emailStringToTest
                    + " matches: " + Pattern.matches(emailPattern, emailStringToTest));
        }
        long end = System.currentTimeMillis() - started;
        System.out.println("Anzahl Millisekunden: " + end);

    }

    public void dioptrienTest() {

        boolean ok;

        ok = dioptrienErlaubt(new BigDecimal("0.2551"));
        ok = dioptrienErlaubt(new BigDecimal("0.25"));
        ok = dioptrienErlaubt(new BigDecimal("0.2500000001"));
        ok = dioptrienErlaubt(new BigDecimal("0.25000000001"));
        ok = dioptrienErlaubt(new BigDecimal("99.2500000001"));
        ok = dioptrienErlaubt(new BigDecimal("99.25000000001"));
        ok = dioptrienErlaubt(new BigDecimal("99.2500000000"));
        ok = dioptrienErlaubt(new BigDecimal("7.75"));
        ok = dioptrienErlaubt(new BigDecimal("2.85"));
        ok = dioptrienErlaubt(new BigDecimal("12.25000000001"));
        ok = dioptrienErlaubt(new BigDecimal("13"));
        ok = dioptrienErlaubt(new BigDecimal("0.75"));
        ok = dioptrienErlaubt(new BigDecimal("1"));
        ok = dioptrienErlaubt(new BigDecimal("1.25"));

    }

//    private boolean dioptrienErlaubt(BigDecimal d) {
//
//        boolean erlaubt = false;
//
//        int myscale = 10;
//        BigDecimal eps = BigDecimal.valueOf(1, myscale);
//
//        System.out.println("-----------------------------------");
//        System.out.println("d=" + d);
//        System.out.println("eps=" + eps);
//
//        BigDecimal a1 = d.multiply(BigDecimal.valueOf(4).setScale(myscale + 1, BigDecimal.ROUND_DOWN));
//        System.out.println("a1=" + a1);
//        BigDecimal a2 = d.multiply(BigDecimal.valueOf(4).setScale(myscale + 1)).setScale(0, RoundingMode.DOWN);
//        System.out.println("a2=" + a2);
//        BigDecimal a3 = (a1.subtract(a2)).setScale(myscale, RoundingMode.DOWN);
//        System.out.println("a3= " + a3);
//        erlaubt = (a3.compareTo(eps) == -1);
//
//        System.out.println("erlaubt: " + erlaubt);
//
//        return erlaubt;
//    }
    private boolean runden() {

        BigDecimal zahl = new BigDecimal("-5.5");
        BigDecimal gerundeteZahl = zahl.divide(new BigDecimal(5), 0, RoundingMode.CEILING);
        gerundeteZahl = gerundeteZahl.multiply(new BigDecimal(5));

        System.out.println("" + gerundeteZahl);

        return true;
    }

    private boolean dioptrienErlaubt(BigDecimal d) {
        // Die Funktion prüft, ob die übergebene BigDecimal - Zahl d ein Vielfaches von 0.25 ist, ndem sie prüft, ob 4*d ganzzahlig ist.
        System.out.println("-----------------------------------");
        boolean erlaubt = ((d.multiply(BigDecimal.valueOf(4))).remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0);

        System.out.println("d=" + d + "    ---> erlaubt:" + erlaubt);

        return erlaubt;
    }

    public void someBigDecimal() {

        BigDecimal x1, x2, x1Rounded, x2Rounded, result, resultRounded;
        x1 = new BigDecimal("10.0015");
        x2 = new BigDecimal("20.0014");

        // java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result requires scale here!!
        BigDecimal x6 = new BigDecimal("1.0").divide(new BigDecimal("3.0"), 10, RoundingMode.HALF_EVEN);

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

    public void someBigDecimal2() {

        boolean erlaubt = false;

        int myscale = 10;
        BigDecimal eps = BigDecimal.valueOf(1, 10); // i.e 1^(-10)
//

        System.out.println("ugly Divisions");
        BigDecimal bd1 = new BigDecimal("20");
        BigDecimal bd2 = new BigDecimal("3");

        // this is ok, as 10*3 = 30     
        BigDecimal bd3 = bd1.multiply(bd2);

        //but this calclulation throws an arithmetic exception, since 10/3 = 0.333333..., has infinite scale!
        //BigDecimal bd4 = bd1.divide(bd2); 
        // performs the division, so that myscale digits are precisely (including the given roundind mode)
        BigDecimal bd5 = MathUtils.saveDivision(bd1, bd2, myscale, RoundingMode.DOWN);
//        BigDecimal bd5 = saveDivision(bd1, bd2, myscale);
        System.out.println("" + bd5);
        BigDecimal bd6 = MathUtils.saveDivision(bd1, bd2, myscale, RoundingMode.UP);
        System.out.println("" + bd6);
        BigDecimal bd9 = MathUtils.saveDivision(bd1, bd2, myscale, RoundingMode.HALF_UP);
//        BigDecimal bd9 = saveDivision(bd1, bd2);
        System.out.println("" + bd9);
        System.out.println("" + bd9.subtract(bd6));

        System.out.println("isZeroWithRespectToScale // kleiner als Test ...");
        BigDecimal delta1 = bd5.subtract(bd6);
        System.out.println("" + delta1 + " --> isZeroWithRespectToScale(scale:" + myscale + "): " + MathUtils.isZeroWithRespectToScale(delta1, myscale));
        System.out.println("" + delta1 + " --> isZeroWithRespectToScale(scale:" + (myscale - 1) + "): " + MathUtils.isZeroWithRespectToScale(delta1, myscale - 1));

        System.out.println("Test for whole number");
        BigDecimal bd7 = new BigDecimal("-12.000000000000000000");
        System.out.println("" + bd7 + " --> isWholeNumber: " + MathUtils.isWholeNumber(bd7));

        BigDecimal bd8 = new BigDecimal("-12.000000000000000000001");
        System.out.println("" + bd8 + " --> isWholeNumber: " + MathUtils.isWholeNumber(bd8));

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
//        final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(App.class
//        );

        Logger.trace("Hello World!");
        Logger.debug("How are you today?");
        Logger.info("I am fine.");
        Logger.warn("I love programming.");
        Logger.error("I am programming.");
        //LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        //lc.setLoggingLevel(ch.qos.logback.classic.Level.DEBUG);
        Logger.info("info msg");
        Logger.debug("debug msg");

        String jft_id = "112";
        String jft_symbol = "square";
        Logger.debug("Processing trade with id: {} and symbol : {} ", jft_id, jft_symbol);

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
