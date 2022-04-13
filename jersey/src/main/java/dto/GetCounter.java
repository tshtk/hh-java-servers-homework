package dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class GetCounter {
    private final String date;
    private final long value;

    public GetCounter(final long value) {
        this.date = ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(date);
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public long getValue() {
        return value;
    }
}
