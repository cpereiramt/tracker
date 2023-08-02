package com.claytonpereira.utils;

public class CalculateDistanceBetweenMSToBS {
    private static final double EARTH_RADIUS = 6371;

    // Calculate the distance in meters between two points using the Haversine formula
    public static double calculateDistance(double baseLat, double baseLon, double mobLat, double mobLon) {
        double dLat = Math.toRadians(mobLat - baseLat);
        double dLon = Math.toRadians(mobLon - baseLon);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(baseLat)) * Math.cos(Math.toRadians(mobLat))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }
}