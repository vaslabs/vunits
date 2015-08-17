package com.vaslabs.units;

import junit.framework.TestCase;
import static com.vaslabs.units.TestConstants.DIFF;
/**
 * Created by nicolaouv on 8/17/15.
 */
public class TestPressureUnit extends TestCase {

    //The tests cover only the needs of SDC
    public void setUp() {
    }

    public void test_hPa_to_atm() {
        double pascalValue = 1032.43;
        double atmValue = PressureUnit.ATM.convert(PressureUnit.hPa, pascalValue);

        assertEquals(1.0189291882, atmValue, DIFF);


        pascalValue = 0;
        atmValue = PressureUnit.ATM.convert(PressureUnit.hPa, pascalValue);
        assertEquals(0, atmValue, DIFF);

    }

    public void test_hPa_to_psi() {
        double pascalValue = 1032.43;
        double psiValue = PressureUnit.PSI.convert(PressureUnit.hPa, pascalValue);

        assertEquals(14.9741312, psiValue, DIFF);


        pascalValue = 0;
        psiValue = PressureUnit.ATM.convert(PressureUnit.hPa, pascalValue);
        assertEquals(0, psiValue, DIFF);

    }

    public void test_atm_to_hPa() {
        double atmValue = 3.45;
        double hPaValue = PressureUnit.hPa.convert(PressureUnit.ATM, atmValue);

        assertEquals(3495.7125, hPaValue, DIFF);

        atmValue = 0.67;

        hPaValue = PressureUnit.hPa.convert(PressureUnit.ATM, atmValue);

        assertEquals(678.8775, hPaValue, DIFF);


        atmValue = 0;

        hPaValue = PressureUnit.hPa.convert(PressureUnit.ATM, atmValue);

        assertEquals(0, hPaValue, DIFF);
    }

    public void test_atm_to_psi() {
        double atmValue = 3.45;
        double psiValue = PressureUnit.PSI.convert(PressureUnit.ATM, atmValue);

        assertEquals(50.7010233, psiValue, DIFF);

        atmValue = 0.67;

        psiValue = PressureUnit.PSI.convert(PressureUnit.ATM, atmValue);

        assertEquals(9.84628568, psiValue, DIFF);


        atmValue = 0;

        psiValue = PressureUnit.PSI.convert(PressureUnit.ATM, atmValue);

        assertEquals(0, psiValue, DIFF);
    }

    public void test_psi_to_hPa() {
        double psiValue = 28;
        double hPaValue = PressureUnit.hPa.convert(PressureUnit.PSI, psiValue);

        assertEquals(1930.5320412, hPaValue, DIFF);


        psiValue = 0;
        hPaValue = PressureUnit.hPa.convert(PressureUnit.PSI, psiValue);
        assertEquals(0, hPaValue, DIFF);

        psiValue = 0.8;
        hPaValue = PressureUnit.hPa.convert(PressureUnit.PSI, psiValue);
        assertEquals(55.1580583, hPaValue, DIFF);
    }


    public void test_psi_to_atm() {
        double psiValue = 28;
        double atmValue = PressureUnit.ATM.convert(PressureUnit.PSI, psiValue);

        assertEquals(1.90528699, atmValue, DIFF);


        psiValue = 0;
        atmValue = PressureUnit.ATM.convert(PressureUnit.PSI, psiValue);
        assertEquals(0, atmValue, DIFF);

        psiValue = 0.8;
        atmValue = PressureUnit.ATM.convert(PressureUnit.PSI, psiValue);
        assertEquals(0.0544367711, atmValue, DIFF);
    }

}
