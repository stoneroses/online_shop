package com.wang.michael.online_shop.web.converter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

public class StringToJodaDateTimeConverter implements Converter<String, DateTime> {

    @Override
    public DateTime convert(String stringDateTime) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");
        return formatter.parseDateTime(stringDateTime);
    }
}
