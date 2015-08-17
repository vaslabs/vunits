package com.vaslabs.units;


public enum TimeUnit implements ITimeUnit {

    MILLISECONDS("ms") {
        public double toMillis(double d)  { return d; }
        public double toSeconds(double d) { return d/(C3/C2); }
        public double toMinutes(double d) { return d/(C4/C2); }
        public double toHours(double d)   { return d/(C5/C2); }
        public double toDays(double d)    { return d/(C6/C2); }
        public double convert(double d, TimeUnit u) { return u.toMillis(d); }
    },
    SECONDS("s") {
        public double toMillis(double d)  { return x(d, C3/C2, MAX/(C3/C2)); }
        public double toSeconds(double d) { return d; }
        public double toMinutes(double d) { return d/(C4/C3); }
        public double toHours(double d)   { return d/(C5/C3); }
        public double toDays(double d)    { return d/(C6/C3); }
        public double convert(double d, TimeUnit u) { return u.toSeconds(d); }
    },
    MINUTES("m") {
        public double toMillis(double d)  { return x(d, C4/C2, MAX/(C4/C2)); }
        public double toSeconds(double d) { return x(d, C4/C3, MAX/(C4/C3)); }
        public double toMinutes(double d) { return d; }
        public double toHours(double d)   { return d/(C5/C4); }
        public double toDays(double d)    { return d/(C6/C4); }
        public double convert(double d, TimeUnit u) { return u.toMinutes(d); }
    },
    HOURS("h") {
        public double toMillis(double d)  { return x(d, C5/C2, MAX/(C5/C2)); }
        public double toSeconds(double d) { return x(d, C5/C3, MAX/(C5/C3)); }
        public double toMinutes(double d) { return x(d, C5/C4, MAX/(C5/C4)); }
        public double toHours(double d)   { return d; }
        public double toDays(double d)    { return d/(C6/C5); }
        public double convert(double d, TimeUnit u) { return u.toHours(d); }
    },
    DAYS("day") {
        public double toMillis(double d)  { return x(d, C6/C2, MAX/(C6/C2)); }
        public double toSeconds(double d) { return x(d, C6/C3, MAX/(C6/C3)); }
        public double toMinutes(double d) { return x(d, C6/C4, MAX/(C6/C4)); }
        public double toHours(double d)   { return x(d, C6/C5, MAX/(C6/C5)); }
        public double toDays(double d)    { return d; }
        public double convert(double d, TimeUnit u) { return u.toDays(d); }
    };

    // Handy constants for conversion methods
    static final double C2 = 1;
    static final double C3 = C2 * 1000;
    static final double C4 = C3 * 60;
    static final double C5 = C4 * 60;
    static final double C6 = C5 * 24;

    static final double MAX = Double.MAX_VALUE;
    public final String signature;

    TimeUnit(String signature) {
         this.signature = signature;
    }

    /**
     * Scale d by m, checking for overflow.
     * This has a short name to make above code more readable.
     */
    static double x(double d, double m, double over) {
        if (d >  over) return Double.MAX_VALUE;
        if (d < -over) return Double.MIN_VALUE;
        return d * m;
    }





}
