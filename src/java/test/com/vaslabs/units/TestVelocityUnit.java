package com.vaslabs.units;
import junit.framework.TestCase;

import static com.vaslabs.units.TestConstants.DIFF;

/**
 * Created by nicolaouv on 8/17/15.
 */
public class TestVelocityUnit extends TestCase {

    public void setUp() {

    }

    public void test_minutes_per_second_to_km_per_hour() {
        double minutesPerSecondValue = 32.432;
        VelocityUnit minutesPerSecondVelocity = new VelocityUnit(
                                                        DistanceUnit.METERS, TimeUnit.SECONDS,
                                                        minutesPerSecondValue);
        VelocityUnit kmPerHour = minutesPerSecondVelocity.convert(DistanceUnit.KM, TimeUnit.HOURS);

        assertEquals(116.7552, kmPerHour.DISTANCE_VALUE, DIFF);

        minutesPerSecondValue = 0;
        minutesPerSecondVelocity = new VelocityUnit(
                DistanceUnit.METERS, TimeUnit.SECONDS,
                minutesPerSecondValue);
        kmPerHour = minutesPerSecondVelocity.convert(DistanceUnit.KM, TimeUnit.HOURS);

        assertEquals(0, kmPerHour.DISTANCE_VALUE, DIFF);

    }

    public void test_minutes_per_second_to_feet_per_second() {
        double minutesPerSecondValue = 32.432;
        VelocityUnit minutesPerSecondVelocity = new VelocityUnit(
                DistanceUnit.METERS, TimeUnit.SECONDS,
                minutesPerSecondValue);
        VelocityUnit kmPerHour = minutesPerSecondVelocity.convert(DistanceUnit.FEET, TimeUnit.SECONDS);

        assertEquals(106.404199, kmPerHour.DISTANCE_VALUE, DIFF);

        minutesPerSecondValue = 0;
        minutesPerSecondVelocity = new VelocityUnit(
                DistanceUnit.METERS, TimeUnit.SECONDS,
                minutesPerSecondValue);
        kmPerHour = minutesPerSecondVelocity.convert(DistanceUnit.FEET, TimeUnit.SECONDS);

        assertEquals(0, kmPerHour.DISTANCE_VALUE, DIFF);

        minutesPerSecondValue = 0.8;
        minutesPerSecondVelocity = new VelocityUnit(
                DistanceUnit.METERS, TimeUnit.SECONDS,
                minutesPerSecondValue);
        kmPerHour = minutesPerSecondVelocity.convert(DistanceUnit.FEET, TimeUnit.SECONDS);

        assertEquals(2.62467192, kmPerHour.DISTANCE_VALUE, DIFF);

    }

    public void test_km_per_hour_to_meters_per_second() {
        double kmPerHourValue = 203;
        VelocityUnit kmPerHour = new VelocityUnit(DistanceUnit.KM, TimeUnit.HOURS, kmPerHourValue);

        VelocityUnit metersPerSecond = kmPerHour.convert(DistanceUnit.METERS, TimeUnit.SECONDS);

        assertEquals(56.3888889, metersPerSecond.DISTANCE_VALUE, DIFF);


        kmPerHourValue = 0.3;
        kmPerHour = new VelocityUnit(DistanceUnit.KM, TimeUnit.HOURS, kmPerHourValue);

        metersPerSecond = kmPerHour.convert(DistanceUnit.METERS, TimeUnit.SECONDS);

        assertEquals(0.0833333333, metersPerSecond.DISTANCE_VALUE, DIFF);

        kmPerHourValue = 0;
        kmPerHour = new VelocityUnit(DistanceUnit.KM, TimeUnit.HOURS, kmPerHourValue);

        metersPerSecond = kmPerHour.convert(DistanceUnit.METERS, TimeUnit.SECONDS);

        assertEquals(0, metersPerSecond.DISTANCE_VALUE, DIFF);
    }

    public void test_km_per_hour_to_miles_per_hour() {
        double kmPerHourValue = 203;
        VelocityUnit kmPerHour = new VelocityUnit(DistanceUnit.KM, TimeUnit.HOURS, kmPerHourValue);

        VelocityUnit metersPerSecond = kmPerHour.convert(DistanceUnit.MILES, TimeUnit.HOURS);

        assertEquals(126.13835202417879, metersPerSecond.DISTANCE_VALUE, DIFF);


        kmPerHourValue = 0.3;
        kmPerHour = new VelocityUnit(DistanceUnit.KM, TimeUnit.HOURS, kmPerHourValue);

        metersPerSecond = kmPerHour.convert(DistanceUnit.MILES, TimeUnit.HOURS);

        assertEquals(0.186411, metersPerSecond.DISTANCE_VALUE, DIFF);

        kmPerHourValue = 0;
        kmPerHour = new VelocityUnit(DistanceUnit.KM, TimeUnit.HOURS, kmPerHourValue);

        metersPerSecond = kmPerHour.convert(DistanceUnit.MILES, TimeUnit.HOURS);

        assertEquals(0, metersPerSecond.DISTANCE_VALUE, DIFF);
    }
}
