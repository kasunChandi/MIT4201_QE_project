package Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeUtils {

    private DateTimeUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getDateInYearMonthDay(int date) {
        Calendar cal1 = new GregorianCalendar();
        cal1.add(Calendar.DATE, date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(cal1.getTime());
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat("MM-dd-HH-mm-ss").format(new Date());
    }

    public static String getFullTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }

    public static String getTimeStamp2() {
        String timestamp = new SimpleDateFormat("MMddHHmmss").format(new Date());
        return timestamp;
    }
}
