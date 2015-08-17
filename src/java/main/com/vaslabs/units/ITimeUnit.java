package com.vaslabs.units;

/**
 * Created by nicolaouv on 8/17/15.
 */
public interface ITimeUnit {
    public double convert(double sourceDuration, TimeUnit sourceUnit);


    public double toMillis(double duration);


    public double toSeconds(double duration);


    public double toMinutes(double duration);


    public double toHours(double duration);


    public double toDays(double duration);
}
