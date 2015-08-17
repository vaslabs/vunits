package com.vaslabs.units;

/**
 * Created by vnicolaou on 07/08/15.
 */
public enum DistanceUnit {
    METERS("m") {
        @Override
        public double convert(DistanceUnit distanceUnit, double distance_value) {
            return distanceUnit.toMeters(distance_value);
        }

        @Override
        public double toKm(double m) {
            return m/CKM;
        }

        @Override
        public double toFeet(double m) {
            return m*CF;
        }

        @Override
        public double toMeters(double m) {
            return m;
        }

        @Override
        public double toMiles(double m) {
            return m/(CM*CKM);
        }

    }, FEET("ft") {
        @Override
        public double toKm(double ft) {
            return (ft/(CF*CKM));
        }

        @Override
        public double toFeet(double f) {
            return f;
        }

        @Override
        public double toMeters(double ft) {
            return ft/CF;
        }

        @Override
        public double toMiles(double feet) {
            return feet/(CF*CKM*CM);
        }

        @Override
        public double convert(DistanceUnit distanceUnit, double distance_value) {
            return distanceUnit.toFeet(distance_value);
        }
    }, KM("km") {
        @Override
        public double toKm(double km) {
            return km;
        }

        @Override
        public double toFeet(double km) {
            return km*CKM*CF;
        }

        @Override
        public double toMeters(double km) {
            return km*CKM;
        }

        @Override
        public double toMiles(double km) {
            return km/CM;
        }

        @Override
        public double convert(DistanceUnit distanceUnit, double distance_value) {
            return distanceUnit.toKm(distance_value);
        }
    }, MILES("miles") {
        @Override
        public double toKm(double miles) {
            return miles*CM;
        }

        @Override
        public double toFeet(double miles) {
            return miles*CM*1000*CF;
        }

        @Override
        public double toMeters(double miles) {
            return miles*CM*1000;
        }

        @Override
        public double toMiles(double miles) {
            return miles;
        }

        @Override
        public double convert(DistanceUnit distanceUnit, double distance_value) {
            return distanceUnit.toMiles(distance_value);
        }
    };

    public abstract double toKm(double km);

    public final double CF = 3.2808399;
    public final double CKM = 1000;
    public final double CM = 1.609344;

    public abstract double toFeet(double m);

    public abstract double toMeters(double distance_value);

    public abstract double toMiles(double distance_value);

    public final String signature;

    DistanceUnit(String signature) {
        this.signature = signature;
    }

    public abstract double convert(DistanceUnit distanceUnit, double distance_value);

    public String toString(double value) {
        return String.format("%.2f%s", value, signature);
    }

}
