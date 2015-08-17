package com.vaslabs.units;

/**
 * Created by nicolaouv on 8/17/15.
 */
public enum PressureUnit implements IPressureUnit {
    PASCAL("Pa") {
        @Override
        public double toPascal(double pascal) {
            return pascal;
        }

        @Override
        public double toHectoPascal(double pValue) {
            return pValue/HECTO_PASCAL;
        }

        @Override
        public double toBar(double pascal) {
            return pascal/BAR_TO_PASCAL;
        }

        @Override
        public double toAt(double pascal) {
            return pascal/AT_TO_PASCAL;
        }

        @Override
        public double toAtm(double pascal) {
            return pascal/ATM_TO_PASCAL;
        }

        @Override
        public double toTorr(double pascal) {
            return pascal/TORR_TO_PASCAL;
        }

        @Override
        public double toPsi(double pascal) {
            return pascal/PSI_TO_PASCAL;
        }

        @Override
        public double convert(PressureUnit pUnit, double pValue) {
            return pUnit.toPascal(pValue);
        }
    }, hPa("hPa") {
        @Override
        public double toPascal(double hPa) {
            return hPa*HECTO_PASCAL;
        }

        @Override
        public double toHectoPascal(double hPa) {
            return hPa;
        }

        @Override
        public double toBar(double hPa) {
            return hPa*HECTO_PASCAL/BAR_TO_PASCAL;
        }

        @Override
        public double toAt(double hPa) {
            return hPa*HECTO_PASCAL/AT_TO_PASCAL;
        }

        @Override
        public double toAtm(double hPa) {
            return hPa*HECTO_PASCAL/ATM_TO_PASCAL;
        }

        @Override
        public double toTorr(double hPa) {
            return hPa*HECTO_PASCAL/TORR_TO_PASCAL;
        }

        @Override
        public double toPsi(double hPa) {
            return hPa*HECTO_PASCAL/PSI_TO_PASCAL;
        }

        @Override
        public double convert(PressureUnit pUnit, double hPa) {
            return pUnit.toHectoPascal(hPa);
        }
    } ,
    BAR("bar") {
        @Override
        public double toPascal(double bar) {
            return bar*BAR_TO_PASCAL;
        }

        @Override
        public double toHectoPascal(double bar) {
            return bar*BAR_TO_PASCAL/HECTO_PASCAL;
        }

        @Override
        public double toBar(double bar) {
            return bar;
        }

        @Override
        public double toAt(double bar) {
            return bar*BAR_TO_AT;
        }

        @Override
        public double toAtm(double bar) {
            return bar*BAR_TO_ATM;
        }

        @Override
        public double toTorr(double bar) {
            return bar*BAR_TO_TORR;
        }

        @Override
        public double toPsi(double bar) {
            return bar*BAR_TO_PSI;
        }

        @Override
        public double convert(PressureUnit pUnit, double pValue) {
            return pUnit.toBar(pValue);
        }
    }, AT("at") {
        @Override
        public double toPascal(double at) {
            return at*AT_TO_PASCAL;
        }

        @Override
        public double toHectoPascal(double at) {
            return toPascal(at)/HECTO_PASCAL;
        }

        @Override
        public double toBar(double at) {
            return at/BAR_TO_AT;
        }

        @Override
        public double toAt(double at) {
            return at;
        }

        @Override
        public double toAtm(double at) {
            return at*AT_TO_ATM;
        }

        @Override
        public double toTorr(double at) {
            return at*AT_TO_TORR;
        }

        @Override
        public double toPsi(double at) {
            return at*AT_TO_PSI;
        }

        @Override
        public double convert(PressureUnit pUnit, double pValue) {
            return pUnit.toAt(pValue);
        }
    }, ATM("atm") {
        @Override
        public double toPascal(double atm) {
            return atm*ATM_TO_PASCAL;
        }

        @Override
        public double toHectoPascal(double atm) {
            return toPascal(atm)/HECTO_PASCAL;
        }

        @Override
        public double toBar(double atm) {
            return atm/BAR_TO_ATM;
        }

        @Override
        public double toAt(double atm) {
            return atm/AT_TO_ATM;
        }

        @Override
        public double toAtm(double atm) {
            return atm;
        }

        @Override
        public double toTorr(double atm) {
            return atm*ATM_TO_TORR;
        }

        @Override
        public double toPsi(double atm) {
            return atm*ATM_TO_PSI;
        }

        @Override
        public double convert(PressureUnit pUnit, double pValue) {
            return pUnit.toAtm(pValue);
        }
    },
    TORR("Torr") {
        @Override
        public double toPascal(double torr) {
            return torr*TORR_TO_PASCAL;
        }

        @Override
        public double toHectoPascal(double torr) {
            return toPascal(torr)/HECTO_PASCAL;
        }

        @Override
        public double toBar(double torr) {
            return torr/BAR_TO_TORR;
        }

        @Override
        public double toAt(double torr) {
            return torr/AT_TO_TORR;
        }

        @Override
        public double toAtm(double torr) {
            return torr/ATM_TO_TORR;
        }

        @Override
        public double toTorr(double torr) {
            return torr;
        }

        @Override
        public double toPsi(double torr) {
            return torr*TORR_TO_PSI;
        }

        @Override
        public double convert(PressureUnit pUnit, double pValue) {
            return pUnit.toTorr(pValue);
        }
    },
    PSI("psi") {
        @Override
        public double toPascal(double psi) {
            return psi*PSI_TO_PASCAL;
        }

        @Override
        public double toHectoPascal(double psi) {
            return toPascal(psi)/HECTO_PASCAL;
        }

        @Override
        public double toBar(double psi) {
            return psi/BAR_TO_PSI;
        }

        @Override
        public double toAt(double psi) {
            return psi/AT_TO_PSI;
        }

        @Override
        public double toAtm(double psi) {
            return psi/ATM_TO_PSI;
        }

        @Override
        public double toTorr(double psi) {
            return psi/TORR_TO_PSI;
        }

        @Override
        public double toPsi(double psi) {
            return psi;
        }

        @Override
        public double convert(PressureUnit pUnit, double pValue) {
            return pUnit.toPsi(pValue);
        }
    };


    private final String signature;
    PressureUnit(String signature) {
        this.signature = signature;
    }

    @Override
    public String getSignature() {
        return this.signature;
    }

    public static final double HECTO_PASCAL = 100;
    public static final double BAR_TO_PASCAL = 100000;
    public static final double AT_TO_PASCAL = 100000*0.98055;
    public static final double ATM_TO_PASCAL = 100000*1.01325;
    public static final double TORR_TO_PASCAL = 133.3224;
    public static final double PSI_TO_PASCAL = 6894.75729;

    public static final double BAR_TO_AT = 1.0197;
    public static final double BAR_TO_ATM = 0.98692;
    public static final double BAR_TO_TORR = 750.06;
    public static final double BAR_TO_PSI = 14.50377;

    public static final double AT_TO_ATM = 0.9678411;
    public static final double AT_TO_TORR = 735.5592;
    public static final double AT_TO_PSI = 13.22334;

    public static final double ATM_TO_TORR = 760;
    public static final double ATM_TO_PSI = 14.6959488;

    public static final double TORR_TO_PSI = 0.01933678;

}
