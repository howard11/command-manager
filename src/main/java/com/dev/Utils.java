package com.dev;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Utils, such as string empty check.
 */
public class Utils {

    private static String QUOTE = "\"";
    public static final String EMPTY_STRING = "";

    public static boolean isEmptyString(final String s) {
        return s == null || EMPTY_STRING.equals(s);
    }

    public static String getNowTime() {
        return new SimpleDateFormat("hh : mm : ss").format(new Date());
    }

    public static String packXmlNode(final String nodeTag, final String nodeText) {
        return "<" + nodeTag + ">\n" + nodeText + "\n</" + nodeTag + ">\n";
    }

    public static String[] splitCommand(String cmd) {
        cmd = cmd.trim();
        if (Utils.isEmptyString(cmd)) return null;

        List<String> l = new ArrayList<String>();
        int start = 0;
        boolean inQuote = false;
        for (int i = 0; i < cmd.length(); ++i) {
            if (cmd.charAt(i) == '"' || cmd.charAt(i) == '\'') inQuote = !inQuote;
            if (inQuote) continue;
            if (cmd.charAt(i) == ' ') {
                if (cmd.charAt(i - 1) != ' ') l.add(trimQuote(cmd.substring(start, i)));
                if (cmd.charAt(i + 1) != ' ') start = i + 1;
            }
        }
        l.add(trimQuote(cmd.substring(start)));

        return l.toArray(new String[l.size()]);
    }

    private static String trimQuote(final String s) {
        String q = "'";
        String Q = "\"";
        if (Utils.isEmptyString(s) || !((s.startsWith(q) && s.endsWith(q)) || (s.startsWith(Q) && s.endsWith(Q)))) return s;

        return s.substring(1, s.length() - 1);
    }
}
