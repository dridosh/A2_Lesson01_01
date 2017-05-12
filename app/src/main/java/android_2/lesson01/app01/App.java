package android_2.lesson01.app01;

import android.app.Application;
import android.util.Log;

import android_2.lesson01.app01.lib.DBEmployees;

public class App extends Application {

	/* Static file for store a link to the data base object */
	private static DBEmployees mDBEmployees = null;

	/* Private field for store a LOG tag */
	private static final String LOG_TAG = "A2L1";

	@Override
	public void onCreate() {
		super.onCreate();
		/* Create a data base object */
		mDBEmployees = new DBEmployees(getApplicationContext());
		/* Write a log */
		Log("Application onCreate");
	}


	 /* Get a link to the data base object*/
	public static DBEmployees getDB() {
		return mDBEmployees;
	}


	 /* Add any string to Log*/
	public static void Log(String log) {
		Log.d(LOG_TAG, log);
	}

}
