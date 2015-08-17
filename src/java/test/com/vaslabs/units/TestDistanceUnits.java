package com.vaslabs.units;

import junit.framework.TestCase;

/**
 * Created by nicolaouv on 8/17/15.
 */
public class TestDistanceUnits extends TestCase {

    public void setUp() {

    }

    public void test_from_meters_to_feet() {
        double metersValue = 68;
        double feetValue = DistanceUnit.FEET.convert(DistanceUnit.METERS, metersValue);

        assertEquals(223.097113, feetValue, 0.00001);
    }


}
