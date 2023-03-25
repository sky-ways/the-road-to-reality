package com.github.cao.awa.trtr.util.string;

public class StringConcat {
    private static final StringBuilder BUILDER = new StringBuilder();

    public static String concat(String s1, Object... concat) {
        if (concat.length == 0) {
            return s1;
        }
        BUILDER.setLength(0);
        BUILDER.append(s1);
        for (Object o : concat) {
            BUILDER.append(o);
        }
        return BUILDER.toString();
    }
}
