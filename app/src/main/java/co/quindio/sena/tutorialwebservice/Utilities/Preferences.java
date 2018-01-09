package co.quindio.sena.tutorialwebservice.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by manuel on 12/27/17.
 */

public class Preferences {

    public static final String IS_LOGIN = "IsloginON";
    public static final String KEY_USERNAME = "username";
    public static final String LEVEL = "level";
    public static final String TOKEN = "token";
    public static final String ARGUMENTS = "arguments";

    public static void setString(String key, String value, Context context) {
        //context.getSharedPreferences("BASE",0);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        //editor.commit();
        editor.apply();
    }

    public static void setBoolean(String key, boolean value, Context context) {
        //context.getSharedPreferences("BASE",0);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        //editor.commit();
        editor.apply();
    }

    public static void setInteger(String key, int value, Context context) {
        //context.getSharedPreferences("BASE",0);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        //editor.commit();
        editor.apply();
    }

    public static Boolean getBoolean(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, false);
    }

    public static String getString(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public static int getInteger(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key, 0);
    }

    public static void logOutpPreferences(Context mycontext){
        setBoolean(Preferences.IS_LOGIN,false,mycontext);///ESTA ES LA MIA
        setString(Preferences.TOKEN,null,mycontext);///ESTA ES LA MIA
        setString(Preferences.LEVEL,null,mycontext);///ESTA ES LA MIA
        setString(Preferences.KEY_USERNAME,null,mycontext);///ESTA ES LA MIA
    }

    public static void setLogin(Context mycontext, Boolean isLogin, String token, String level, String username){
        setBoolean(Preferences.IS_LOGIN,isLogin,mycontext);
        setString(Preferences.TOKEN,token,mycontext);///ESTA ES LA MIA
        setString(Preferences.LEVEL,level,mycontext);///ESTA ES LA MIA
        setString(Preferences.KEY_USERNAME,username,mycontext);///ESTA ES LA MIA
        Log.e(Preferences.TOKEN, token);
    }

    public static void setParams(Context mycontext, String arguments){
        setString(Preferences.ARGUMENTS,arguments,mycontext);///ESTA ES LA MIA
    }
    public static String getParams(Context mycontext, String arguments){
        return getString(Preferences.ARGUMENTS,mycontext);
    }

    public static String getUsername(Context mycontext){
        return getString(Preferences.KEY_USERNAME,mycontext);
    }

    public static String getToken(Context mycontext){
        return getString(Preferences.TOKEN,mycontext);
    }

    public static String getLevel(Context mycontext){
        return getString(Preferences.LEVEL,mycontext);
    }

    public static Boolean isLogin(Context mycontext){
        return getBoolean(Preferences.IS_LOGIN,mycontext);
    }

}
