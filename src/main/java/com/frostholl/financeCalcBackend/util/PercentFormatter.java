package com.frostholl.financeCalcBackend.util;

import org.springframework.format.Formatter;
import org.springframework.format.number.PercentStyleFormatter;

import java.text.ParseException;
import java.util.Locale;

public class PercentFormatter extends PercentStyleFormatter {
    @Override
    public String print(Number number, Locale locale) {
        return String.format("%.2f %%", number.floatValue() * 100);
    }

    @Override
    public Number parse(String text, Locale locale) throws ParseException {
        if (text.contains("%")) {
            var s = text.replace("%", "").strip();
            return Float.parseFloat(s) / 100;
        }
        return Float.parseFloat(text) / 100;
    }
}
