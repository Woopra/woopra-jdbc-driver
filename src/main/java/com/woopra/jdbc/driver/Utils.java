package com.woopra.jdbc.driver;

public class Utils {

    /**
     *
     * @param s
     * @return
     */
    public static String logdecode(String s) {
        if (s == null) {
            return "";
        }
        int err = 0;
        //
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\0') {
                err = 1;
                break;
            }
        }

        if (err == 0) {
            return s;
        }

        StringBuilder str = new StringBuilder();

        int i = 0;
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\0') {
                i++;
                char c2 = s.charAt(i);

                switch (c2) {
                    case 'a':
                        str.append('\0');
                        break;
                    case 'b':
                        str.append('&');
                        break;
                    case 'c':
                        str.append('\n');
                        break;
                    case 'd':
                        str.append('\r');
                        break;
                    case 'e':
                        str.append('=');
                        break;
                }
            } else {
                str.append(c);
            }
        }

        return str.toString();
    }

    /**
     *
     * @param s
     * @return
     */
    public static String logencode(String s) {

        if (s == null) {
            return "";
        }

        int err = 0;
        //
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\0'
                    || c == '&'
                    || c == '\n'
                    || c == '\r'
                    || c == '=') {
                err = 1;
                break;
            }
        }

        if (err == 0) {
            return s;
        }

        //first replace with 0
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\0':
                    str.append('\0');
                    str.append('a');
                    break;
                case '&':
                    str.append('\0');
                    str.append('b');
                    break;
                case '\n':
                    str.append('\0');
                    str.append('c');
                    break;
                case '\r':
                    str.append('\0');
                    str.append('d');
                    break;
                case '=':
                    str.append('\0');
                    str.append('e');
                    break;
                default:
                    str.append(c);
                    break;
            }
        }

        return str.toString();
    }

    /**
     *
     * @param o
     * @return
     */
    public static long _long(Object o) {
        if (o == null) {
            return 0;
        }
        if (o instanceof Number) {
            return ((Number) o).longValue();
        }

        String s;
        if (o instanceof String) {
            s = (String) o;
        } else {
            s = o.toString();
        }

        try {
            return Long.parseLong(s);
        } catch (NumberFormatException ex) {
        }

        return 0;
    }

    public static double _double(Object o) {
        if (o == null) {
            return 0;
        }
        if (o instanceof Number) {
            return ((Number) o).doubleValue();
        }

        String s;
        if (o instanceof String) {
            s = (String) o;
        } else {
            s = o.toString();
        }

        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException ex) {
        }

        return 0;
    }

    public static boolean _boolean(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Boolean) {
            return ((Boolean) o);
        }

        String s;
        if (o instanceof String) {
            s = (String) o;
        } else {
            s = o.toString();
        }

        try {
            return Boolean.parseBoolean(s);
        } catch (Exception ex) {
        }

        return false;
    }

    /**
     *
     * @param o
     * @return
     */
    public static float _float(Object o) {
        if (o == null) {
            return 0;
        }
        if (o instanceof Number) {
            return ((Number) o).floatValue();
        }

        String s;
        if (o instanceof String) {
            s = (String) o;
        } else {
            s = o.toString();
        }

        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException ex) {
        }

        return 0;
    }

    /**
     *
     * @param o
     * @return
     */
    public static int _int(Object o) {
        if (o == null) {
            return 0;
        }
        if (o instanceof Number) {
            return ((Number) o).intValue();
        }

        String s;
        if (o instanceof String) {
            s = (String) o;
        } else {
            s = o.toString();
        }

        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException ex) {
        }

        return 0;
    }

    /**
     *
     * @param o
     * @return
     */
    public static short _short(Object o) {
        if (o == null) {
            return 0;
        }
        if (o instanceof Number) {
            return ((Number) o).shortValue();
        }

        String s;
        if (o instanceof String) {
            s = (String) o;
        } else {
            s = o.toString();
        }

        try {
            return Short.parseShort(s);
        } catch (NumberFormatException ex) {
        }

        return 0;
    }
}
