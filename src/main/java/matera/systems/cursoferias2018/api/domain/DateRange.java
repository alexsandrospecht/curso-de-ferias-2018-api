package matera.systems.cursoferias2018.api.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateRange {

    private static final String DEFAULT_DATE_PATTERN = "DD-MM-YYYY";
    private static final String BIG_BANG_DATE = "01-01-1970";

    private long start;
    private long end;

    private DateRange(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public static DateRange create(String start, String end) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);

        long startTs = sdf.parse(start).getTime();
        long endTs = sdf.parse(end).getTime();
        return new DateRange(startTs, endTs);
    }

    public static DateRange since(String start) throws ParseException {

        return create(start, new SimpleDateFormat(DEFAULT_DATE_PATTERN).format(new Date()));
    }

    public static DateRange until(String end) throws ParseException {

        return create(BIG_BANG_DATE, end);
    }

    public static DateRange wholePeriod() throws ParseException {
        return create(BIG_BANG_DATE, new SimpleDateFormat(DEFAULT_DATE_PATTERN).format(new Date()));
    }

}
