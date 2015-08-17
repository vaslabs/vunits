package com.vaslabs.units;

/**
 * Created by nicolaouv on 8/17/15.
 */
public interface IPressureUnit {
    double toPascal(double pValue);
    double toHectoPascal(double pValue);
    double toBar(double pValue);
    double toAt(double pValue);
    double toAtm(double pValue);
    double toTorr(double pValue);
    double toPsi(double pValue);
    double convert(PressureUnit pUnit, double pValue);
    String getSignature();
}
