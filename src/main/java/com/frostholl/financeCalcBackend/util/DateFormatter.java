package com.frostholl.financeCalcBackend.util;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class DateFormatter implements Formatter<LocalDateTime> {
    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        var dateFormat = createDateFormat();
        return LocalDateTime.parse(text, dateFormat);
    }

    @Override
    public String print(LocalDateTime object, Locale locale) {
        var dateFormat = createDateFormat();
        return object.format(dateFormat);
    }

    private DateTimeFormatter createDateFormat() {
        var dateFormatBuilder = new DateTimeFormatterBuilder();
        dateFormatBuilder.appendPattern("dd/MM/yyyy HH:mm");
        return dateFormatBuilder.toFormatter();
    }
}
