package com.example.chaiy.popularmovies.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Chaiy on 7/10/2016.
 */
public final class SharedPreferenceUtil {


    private SharedPreferenceUtil() {

    }


    public static void saveSortPreference(Context context, int preferenceIndex) {

        SharedPreferences sharedpreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES.PREFERENCE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putInt(Constants.SHARED_PREFERENCES.PREFERENCE_SORT_ORDER, preferenceIndex);
        editor.apply();
    }

    public static int getSortPreference(Context context) {

        SharedPreferences sharedpreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES.PREFERENCE_NAME, Context.MODE_PRIVATE);
        int index = sharedpreferences.getInt(Constants.SHARED_PREFERENCES.PREFERENCE_SORT_ORDER, 1);

        return index;
    }
}
