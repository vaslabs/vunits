package com.vaslabs.units;

import junit.framework.TestCase;
import static com.vaslabs.units.TestConstants.DIFF;

/**
 * Created by nicolaouv on 8/17/15.
 */
public class TestTimeUnits extends TestCase {

    public void setUp() {

    }

    public void test_seconds_to_hours() {
        double secondsValue = 432.12;
        double hoursValue = TimeUnit.HOURS.convert(secondsValue, TimeUnit.SECONDS);

        assertEquals(0.120033, hoursValue, DIFF);

        secondsValue = 548723;
        hoursValue = TimeUnit.HOURS.convert(secondsValue, TimeUnit.SECONDS);

        assertEquals(152.423055556, hoursValue, DIFF);

        secondsValue = 0;
        hoursValue = TimeUnit.HOURS.convert(secondsValue, TimeUnit.SECONDS);

        assertEquals(0, hoursValue, DIFF);
    }

    public void test_seconds_to_minutes() {
        double secondsValue = 432.12;
        double minutesValue = TimeUnit.MINUTES.convert(secondsValue, TimeUnit.SECONDS);

        assertEquals(7.202, minutesValue, DIFF);

        secondsValue = 0.5;
        minutesValue = TimeUnit.MINUTES.convert(secondsValue, TimeUnit.SECONDS);

        assertEquals(0.008333333, minutesValue, DIFF);

        secondsValue = 0;
        minutesValue = TimeUnit.MINUTES.convert(secondsValue, TimeUnit.SECONDS);

        assertEquals(0, minutesValue, DIFF);
    }


    public void test_seconds_to_milliseconds() {
        double secondsValue = 432.12;
        double millisValue = TimeUnit.MILLISECONDS.convert(secondsValue, TimeUnit.SECONDS);

        assertEquals(432120, millisValue, DIFF);

        secondsValue = 0.5;
        millisValue = TimeUnit.MILLISECONDS.convert(secondsValue, TimeUnit.SECONDS);

        assertEquals(500, millisValue, DIFF);

        secondsValue = 0;
        millisValue = TimeUnit.MILLISECONDS.convert(secondsValue, TimeUnit.SECONDS);

        assertEquals(0, millisValue, DIFF);
    }

    public void test_seconds_to_days() {
        double secondsValue = 259200;
        double daysValue = TimeUnit.DAYS.convert(secondsValue, TimeUnit.SECONDS);

        assertEquals(3, daysValue, DIFF);


        secondsValue = 0;
        daysValue = TimeUnit.DAYS.convert(secondsValue, TimeUnit.SECONDS);

        assertEquals(0, daysValue, DIFF);
    }

    public void test_milliseconds_to_seconds() {
        double millisValue = 259200;
        double secondsValue = TimeUnit.SECONDS.convert(millisValue, TimeUnit.MILLISECONDS);

        assertEquals(259.2, secondsValue, DIFF);

        millisValue = 1;
        secondsValue = TimeUnit.SECONDS.convert(millisValue, TimeUnit.MILLISECONDS);
        assertEquals(0.001, secondsValue, DIFF);


        millisValue = 0;
        secondsValue = TimeUnit.SECONDS.convert(millisValue, TimeUnit.MILLISECONDS);

        assertEquals(0, secondsValue, DIFF);
    }

    public void test_milliseconds_to_minutes() {
        double millisValue = 259200;
        double minutesValue = TimeUnit.MINUTES.convert(millisValue, TimeUnit.MILLISECONDS);

        assertEquals(4.32, minutesValue, DIFF);

        millisValue = 0;
        minutesValue = TimeUnit.MINUTES.convert(millisValue, TimeUnit.MILLISECONDS);

        assertEquals(0, minutesValue, DIFF);
    }

    public void test_milliseconds_to_hours() {
        double millisValue = 259200;
        double hoursValue = TimeUnit.HOURS.convert(millisValue, TimeUnit.MILLISECONDS);

        assertEquals(0.072, hoursValue, DIFF);

        millisValue = 0;
        hoursValue = TimeUnit.HOURS.convert(millisValue, TimeUnit.MILLISECONDS);

        assertEquals(0, hoursValue, DIFF);
    }

    public void test_minutes_to_hours() {
        double minutesValue = 259;
        double hoursValue = TimeUnit.HOURS.convert(minutesValue, TimeUnit.MINUTES);

        assertEquals(4.31666666, hoursValue, DIFF);

        minutesValue = 0.8;
        hoursValue = TimeUnit.HOURS.convert(minutesValue, TimeUnit.MINUTES);

        assertEquals(0.0133333, hoursValue, DIFF);

        minutesValue = 0;
        hoursValue = TimeUnit.HOURS.convert(minutesValue, TimeUnit.MINUTES);

        assertEquals(0, hoursValue, DIFF);
    }

    public void test_minutes_to_seconds() {
        double minutesValue = 25.3289;
        double secondsValue = TimeUnit.SECONDS.convert(minutesValue, TimeUnit.MINUTES);

        assertEquals(1519.734, secondsValue, DIFF);

        minutesValue = 0.8;
        secondsValue = TimeUnit.SECONDS.convert(minutesValue, TimeUnit.MINUTES);

        assertEquals(48, secondsValue, DIFF);

        minutesValue = 0;
        secondsValue = TimeUnit.SECONDS.convert(minutesValue, TimeUnit.MINUTES);

        assertEquals(0, secondsValue, DIFF);
    }

}
