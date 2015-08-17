package com.vaslabs.units;

import junit.framework.TestCase;

/**
 * Created by nicolaouv on 8/17/15.
 */
public class TestDistanceUnits extends TestCase {

    static final double DIFF = 0.000001;

    public void setUp() {

    }

    public void test_from_meters_to_feet() {
        double metersValue = 68;
        double feetValue = DistanceUnit.FEET.convert(DistanceUnit.METERS, metersValue);

        assertEquals(223.097113, feetValue, DIFF);

        metersValue = 0.05;
        feetValue = DistanceUnit.FEET.convert(DistanceUnit.METERS, metersValue);

        assertEquals(0.164041995, feetValue, DIFF);

        metersValue = 0.0;
        feetValue = DistanceUnit.FEET.convert(DistanceUnit.METERS, metersValue);

        assertEquals(0, feetValue, DIFF);
    }

    public void test_from_meters_to_km() {
        double metersValue = 482390;
        double kmValue = DistanceUnit.KM.convert(DistanceUnit.METERS, metersValue);

        assertEquals(482.390, kmValue, DIFF);

        metersValue = 0.5;
        kmValue = DistanceUnit.KM.convert(DistanceUnit.METERS, metersValue);

        assertEquals(0.0005, kmValue, DIFF);

        metersValue = 0;
        kmValue = DistanceUnit.KM.convert(DistanceUnit.METERS, metersValue);

        assertEquals(0, kmValue, DIFF);
    }

    public void test_from_feet_to_meters() {
        double feetValue = 28.5;
        double metersValue = DistanceUnit.METERS.convert(DistanceUnit.FEET, feetValue);

        assertEquals(8.6868, metersValue, DIFF);

        feetValue = 0.6;
        metersValue = DistanceUnit.METERS.convert(DistanceUnit.FEET, feetValue);

        assertEquals(0.18288, metersValue, DIFF);

        feetValue = 0;
        metersValue = DistanceUnit.METERS.convert(DistanceUnit.FEET, feetValue);

        assertEquals(0, metersValue, DIFF);
    }

    public void test_from_feet_to_km() {
        double feetValue = 28.5;
        double kmValue = DistanceUnit.KM.convert(DistanceUnit.FEET, feetValue);

        assertEquals(0.0086868, kmValue, DIFF);

        feetValue = 0.6;
        kmValue = DistanceUnit.KM.convert(DistanceUnit.FEET, feetValue);

        assertEquals(0.00018288, kmValue, DIFF);

        feetValue = 0;
        kmValue = DistanceUnit.KM.convert(DistanceUnit.FEET, feetValue);

        assertEquals(0, kmValue, DIFF);
    }

    public void test_from_km_to_meters() {
        double kmValue = 0.06;
        double metersValue = DistanceUnit.METERS.convert(DistanceUnit.KM, kmValue);

        assertEquals(60, metersValue, DIFF);

        kmValue = 32.1;
        metersValue = DistanceUnit.METERS.convert(DistanceUnit.KM, kmValue);

        assertEquals(32100, metersValue, DIFF);

        kmValue = 0;
        metersValue = DistanceUnit.METERS.convert(DistanceUnit.KM, kmValue);

        assertEquals(0, metersValue, DIFF);

    }


    public void test_from_km_to_feet() {
        double kmValue = 0.06;
        double feetValue = DistanceUnit.FEET.convert(DistanceUnit.KM, kmValue);

        assertEquals(196.850394, feetValue, DIFF);

        kmValue = 32.1;
        feetValue = DistanceUnit.FEET.convert(DistanceUnit.KM, kmValue);

        assertEquals(105314.96079, feetValue, DIFF);

        kmValue = 0;
        feetValue = DistanceUnit.FEET.convert(DistanceUnit.KM, kmValue);

        assertEquals(0, feetValue, DIFF);
    }

}
