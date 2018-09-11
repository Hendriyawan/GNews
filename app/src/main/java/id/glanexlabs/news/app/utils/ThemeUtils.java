package id.glanexlabs.news.app.utils;
import android.content.*;
import android.preference.*;
import id.glanexlabs.news.app.*;

public class ThemeUtils
{
	private static SharedPreferences mSharedPref;
	private static SharedPreferences.Editor mPrefEditor;
	
	
	//create theme
	//call this method before setContentView
	public static void createTheme(Context context){
		mSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		String theme = mSharedPref.getString("app_theme", "light");
		switch(theme){
			case "light":
				context.setTheme(R.style.AppThemeLight);
				break;
				
			case "dark":
				context.setTheme(R.style.AppThemeDark);
				break;
		}
	}
	
	//check theme is light
	public static boolean isThemeLight(Context context){
		mSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		return mSharedPref.getBoolean("is_theme_light", true);
	}
	//set theme
	public static void setTheme(Context context, boolean isThemeLight, String theme){
		mSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		mPrefEditor = mSharedPref.edit();
		mPrefEditor.putBoolean("is_theme_light", isThemeLight);
		mPrefEditor.putString("app_theme", theme);
		mPrefEditor.commit();
	}
}
