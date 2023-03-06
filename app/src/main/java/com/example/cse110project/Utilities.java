/*
package com.example.cse110project;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.AlertDialog;
<<<<<<< HEAD
=======
import android.content.Context;
>>>>>>> 391863b40ab4c02c608b115e39b04bbde4b4dd48
import android.content.SharedPreferences;

import java.util.Locale;
import java.util.UUID;

public class Utilities {
<<<<<<< HEAD
    public static final String USER_NAME = "user name";
    public static final String USER_UID = "user UID";
=======
    public final static String PREFERENCES_NAME = "my_preferences";

    public final static String PERSONAL_HOME_COORDINATES = "mine";
    public final static String FAMILY_HOME_COORDINATES = "family";
    public final static String FRIEND_HOME_COORDINATES = "friend";

    public final static String PERSONAL_HOME_LABEL = "myLabel";
    public final static String FAMILY_HOME_LABEL = "familyLabel";
    public final static String FRIEND_HOME_LABEL = "friendLabel";

    public final static String ORIENTATION_LABEL = "orientationLabel";


>>>>>>> 391863b40ab4c02c608b115e39b04bbde4b4dd48
    public static void showAlert(Activity activity, String message){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(activity);

        alertBuilder
                .setTitle("Alert!")
                .setMessage(message)
                .setPositiveButton("Ok", (dialog, id) -> {
                    dialog.cancel();
                })
                .setCancelable(true);

        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }

    public static String[] parseCoords(String str){
        String[] list = str.split(", ");
        return list;
    }

    public static boolean isValidOrientation(String str){
        try {
            double orienation = Double.parseDouble(str);
            if ((orienation < 0 || orienation > 360) && !str.equals("")) {
                return false;
            }
            return true;
        }

        catch (Exception e){
            return false;
        }
    }
    public static boolean isValidLatitude(String str){
        try {
            double lat = Double.parseDouble(str);
            if (lat < -90 || lat > 90){
                return false;
            }
            return true;
        }

        catch(Exception e) {
            return false;
        }
    }

    public static boolean isValidLongitude(String str){
        try {
            double longitude = Double.parseDouble(str);
            if (longitude < -180 || longitude > 180){
                return false;
            }
            return true;
        }

        catch(Exception e){
            return false;
        }
    }

//    public static double angleFromCoordinate(double lat1, double long1, double lat2,
//                                       double long2) {
//
//        double dLon = (long2 - long1);
//
//        double y = Math.sin(dLon) * Math.cos(lat2);
//        double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1)
//                * Math.cos(lat2) * Math.cos(dLon);
//
//        double brng = Math.atan2(y, x);
//
//        brng = Math.toDegrees(brng);
//        brng = (brng + 360) % 360;
//        brng = 360 - brng; // count degrees counter-clockwise - remove to make clockwise
//
//        return brng;
//    }
    
    public static double findAngle(double ourLat, double ourLong, double theirLat, double theirLong){
        double radians = Math.atan2((theirLong - ourLong), (theirLat - ourLat));
        double degrees = Math.toDegrees(radians);

        // If the longitude is south of us we need to add 180 degrees to place the label
        // on the bottom half of the circle. This is because arcTan is bound by [-90, 90] degrees.


        if(degrees < 0) {
            degrees += 360;
        }

        return degrees;
    }

    public static double stringToDouble(String str){
        return Double.parseDouble(str);
    }

    static String formatLocation(double latitude, double longitude) {
        return (latitude + ", " + longitude);
    }

<<<<<<< HEAD
    public static String createUID() {
        return UUID.randomUUID().toString();
    }
=======
//    public static void resetPrefs(){
//        Context context;
//        SharedPreferences preferences;
//        context = ApplicationProvider.getApplicationContext();
//        preferences = context.getSharedPreferences(Utilities.PREFERENCES_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString(Utilities.PERSONAL_HOME_COORDINATES, "");
//        editor.putString(Utilities.PERSONAL_HOME_LABEL, "My Home");
//        editor.apply();
//    }
>>>>>>> 391863b40ab4c02c608b115e39b04bbde4b4dd48
}

 */
