package com.example.android.sunshine.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

//  DONE (1) Create a class called SunshineSyncTask
public class SunshineSyncTask {
//  DONE (2) Within SunshineSyncTask, create a synchronized public static void method called syncWeather
synchronized public static void syncWeather(Context context) {
//      DONE (3) Within syncWeather, fetch new weather data
        try{
        ContentValues[] weatherContentValues = OpenWeatherJsonUtils
                .getWeatherContentValuesFromJson(context, NetworkUtils.getResponseFromHttpUrl(NetworkUtils.getUrl(context)));

            if ((weatherContentValues != null) && (weatherContentValues.length > 0)) {

                ContentResolver contentResolver = context.getContentResolver();

                //      DONE (4) If we have valid results, delete the old data and insert the new
                //delete old data
                contentResolver.delete(
                        WeatherContract.WeatherEntry.CONTENT_URI, null,null);
                // insert new data
                contentResolver.bulkInsert(WeatherContract.WeatherEntry.CONTENT_URI, weatherContentValues);
            }
        }

        catch (Exception error){
            error.printStackTrace();
        }
    }
}