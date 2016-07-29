package com.example.chaiy.popularmovies.helper;

import java.util.Collection;

/**
 * Created by Chaiy on 7/25/2016.
 */
public final class Utility {

    private Utility(){

    }

    public static <T> boolean IsNullOrEmpty(Collection<T> list) {
        return list == null || list.isEmpty();
    }

    public static String roundOffTo2DecPlaces(float val)
    {
        return String.format("%.2f", val);
    }
}
