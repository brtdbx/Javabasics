package brUtils;

import java.util.regex.Pattern;

public class StringHelper {

    private static void buildFindAnyPattern(String[] terms, StringBuffer sb) {
        if (terms.length == 0) {
            throw new IllegalArgumentException("There must be at least one term to find.");
        }
        sb.append("(?:");
        for (int i = 0; i < terms.length; i++) {
            if (i > 0) {
                sb.append("|");
            }
            sb.append("(?:");
            sb.append(escapeRegularExpressionLiteral(terms[i]));
            sb.append(")");
        }
        sb.append(")");
    }

    public static Pattern getContainsAnyPattern(String[] terms) {
        StringBuffer sb = new StringBuffer();
        sb.append("(?s).*");
        buildFindAnyPattern(terms, sb);
        sb.append(".*");
        return Pattern.compile(sb.toString());
    }

    public static Pattern getEqualsAnyPattern(String[] terms) {
        StringBuffer sb = new StringBuffer();
        sb.append("(?s)\\A");
        buildFindAnyPattern(terms, sb);
        sb.append("\\z");
        return Pattern.compile(sb.toString());
    }

    public static Pattern getStartsWithAnyPattern(String[] terms) {
        StringBuffer sb = new StringBuffer();
        sb.append("(?s)\\A");
        buildFindAnyPattern(terms, sb);
        sb.append(".*");
        return Pattern.compile(sb.toString());
    }

    public static Pattern getEndsWithAnyPattern(String[] terms) {
        StringBuffer sb = new StringBuffer();
        sb.append("(?s).*");
        buildFindAnyPattern(terms, sb);
        sb.append("\\z");
        return Pattern.compile(sb.toString());
    }

    public static Pattern getContainsAnyIgnoreCasePattern(String[] terms) {
        StringBuffer sb = new StringBuffer();
        sb.append("(?i)(?u)(?s).*");
        buildFindAnyPattern(terms, sb);
        sb.append(".*");
        return Pattern.compile(sb.toString());
    }

    public static Pattern getEqualsAnyIgnoreCasePattern(String[] terms) {
        StringBuffer sb = new StringBuffer();
        sb.append("(?i)(?u)(?s)\\A");
        buildFindAnyPattern(terms, sb);
        sb.append("\\z");
        return Pattern.compile(sb.toString());
    }

    public static Pattern getStartsWithAnyIgnoreCasePattern(String[] terms) {
        StringBuffer sb = new StringBuffer();
        sb.append("(?i)(?u)(?s)\\A");
        buildFindAnyPattern(terms, sb);
        sb.append(".*");
        return Pattern.compile(sb.toString());
    }

    public static Pattern getEndsWithAnyIgnoreCasePattern(String[] terms) {
        StringBuffer sb = new StringBuffer();
        sb.append("(?i)(?u)(?s).*");
        buildFindAnyPattern(terms, sb);
        sb.append("\\z");
        return Pattern.compile(sb.toString());
    }

    public static boolean containsAny(String s, String[] terms) {
        return getContainsAnyPattern(terms).matcher(s).matches();
    }

    public static boolean equalsAny(String s, String[] terms) {
        return getEqualsAnyPattern(terms).matcher(s).matches();
    }

    public static boolean startsWithAny(String s, String[] terms) {
        return getStartsWithAnyPattern(terms).matcher(s).matches();
    }

    public static boolean endsWithAny(String s, String[] terms) {
        return getEndsWithAnyPattern(terms).matcher(s).matches();
    }

    public static boolean containsAnyIgnoreCase(String s, String[] terms) {
        return getContainsAnyIgnoreCasePattern(terms).matcher(s).matches();
    }

    public static boolean equalsAnyIgnoreCase(String s, String[] terms) {
        return getEqualsAnyIgnoreCasePattern(terms).matcher(s).matches();
    }

    public static boolean startsWithAnyIgnoreCase(String s, String[] terms) {
        return getStartsWithAnyIgnoreCasePattern(terms).matcher(s).matches();
    }

    public static boolean endsWithAnyIgnoreCase(String s, String[] terms) {
        return getEndsWithAnyIgnoreCasePattern(terms).matcher(s).matches();
    }

    public static String escapeRegularExpressionLiteral(String s) {
        // According to the documentation in the Pattern class:
        //
        // The backslash character ('\') serves to introduce escaped constructs,
        // as defined in the table above, as well as to quote characters that
        // otherwise would be interpreted as unescaped constructs. Thus the
        // expression \\ matches a single backslash and \{ matches a left brace.
        //
        // It is an error to use a backslash prior to any alphabetic character
        // that does not denote an escaped construct; these are reserved for future
        // extensions to the regular-expression language. A backslash may be used
        // prior to a non-alphabetic character regardless of whether that character
        // is part of an unescaped construct.
        //
        // As a result, escape everything except [0-9a-zA-Z]

        int length = s.length();
        int newLength = length;
        // first check for characters that might
        // be dangerous and calculate a length
        // of the string that has escapes.
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (!((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                newLength += 1;
            }
        }
        if (length == newLength) {
            // nothing to escape in the string
            return s;
        }
        StringBuilder sb = new StringBuilder(newLength);
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (!((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                sb.append('\\');
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
