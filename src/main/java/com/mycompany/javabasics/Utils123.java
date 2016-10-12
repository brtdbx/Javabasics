package com.mycompany.javabasics;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils123 {

    //copies from apachy StringUtils, 
    //see http://commons.apache.org/proper/commons-lang/apidocs/src-html/org/apache/commons/lang3/StringUtils.html
    public static final String EMPTY = "";

    public static String trimToEmpty(final String str) {

        return str == null ? EMPTY : str.trim();

    }

    public static String joinSeparated(String s1, String s2, String delimiter) {

        return "".equals(trimToEmpty(s2)) ? trimToEmpty(s1) : ("".equals(trimToEmpty(s1)) ? trimToEmpty(s2) : trimToEmpty(s1) + delimiter + trimToEmpty(s2));

    }

    public static String calendarToDateString(Calendar cal) {

        String dateString = null;
        if (cal != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            dateString = simpleDateFormat.format(cal.getTime());
        }
        return dateString;
    }

}
