package io.branch.referral;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;


/**
 * Class for Branch utility methods
 */
class BranchUtil {

    static boolean isCustomDebugEnabled_ = false; /* For setting debug mode using Branch#setDebug api */

    /**
     * Get the value of "io.branch.sdk.TestMode" entry in application manifest or from String res.
     *
     * @return value of "io.branch.sdk.TestMode" entry in application manifest or String res.
     * false if "io.branch.sdk.TestMode" is not added in the manifest or String res.
     */
    public static boolean isTestModeEnabled(Context context) {
        if (isCustomDebugEnabled_) {
            return isCustomDebugEnabled_;
        }
        boolean isTestMode_ = false;
        String testModeKey = "io.branch.sdk.TestMode";
        try {
            final ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (ai.metaData != null && ai.metaData.containsKey(testModeKey)) {
                isTestMode_ = ai.metaData.getBoolean(testModeKey, false);
            } else {
                Resources resources = context.getResources();
                isTestMode_ = Boolean.parseBoolean(resources.getString(resources.getIdentifier(testModeKey, "string", context.getPackageName())));
            }

        } catch (Exception ignore) {
        }

        return isTestMode_;
    }

    /**
     * Converts a given link param as {@link JSONObject} to string after adding the source param and removes replaces any illegal characters.
     *
     * @param params Link param JSONObject.
     * @return A {@link String} representation of link params.
     */
    public static String formatAndStringifyLinkParam(JSONObject params) {
        return stringifyAndAddSource(filterOutBadCharacters(params));
    }

    /**
     * Convert the given JSONObject to string and adds source value as
     *
     * @param params JSONObject to convert to string
     * @return A {@link String} value representing the JSONObject
     */
    public static String stringifyAndAddSource(JSONObject params) {
        if (params == null) {
            params = new JSONObject();
        }
        try {
            params.put("source", "android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return params.toString();
    }

    /**
     * Replaces illegal characters in the given JSONObject.
     *
     * @param inputObj JSONObject to remove illegal characters.
     * @return A {@link JSONObject} with illegal characters replaced.
     */
    public static JSONObject filterOutBadCharacters(JSONObject inputObj) {
        JSONObject filteredObj = new JSONObject();
        if (inputObj != null) {
            Iterator<String> keys = inputObj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                try {
                    if (inputObj.has(key) && inputObj.get(key).getClass().equals(String.class)) {
                        filteredObj.put(key, inputObj.getString(key).replace("\n", "\\n").replace("\r", "\\r").replace("\"", "\\\""));
                    } else if (inputObj.has(key)) {
                        filteredObj.put(key, inputObj.get(key));
                    }
                } catch (JSONException ignore) {

                }
            }
        }
        return filteredObj;
    }


    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int drawableID) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(drawableID, context.getTheme());
        } else {
            //noinspection deprecation
            return context.getResources().getDrawable(drawableID);
        }
    }
}
