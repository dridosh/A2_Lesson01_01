package android_2.lesson01.app01;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import android_2.lesson01.app01.R;
import android_2.lesson01.app01.lib.DBEmpl;

public class ActMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
				
		/* Invoke a parent method */
		super.onCreate(savedInstanceState);
		Log.d("ActMain", "onCreate");
		/* Load user interface */
		setContentView(R.layout.act_main);
		
		/* Initialize UI components */
		TextView tvText = (TextView) findViewById(R.id.tvText);
		ListView lvList = (ListView) findViewById(R.id.lvList);

		/**
		 * Example 1
		 * */

		/* Create Cursor for read a data from data base */
		Cursor cr = MyApp.getDB().getReadableCursor(DBEmpl.TableDep.T_NAME);
		Cursor cr2 = MyApp.getDB().getReadableCursorWithSelectedData(DBEmpl.TableDep.T_NAME, new String[]{"NAME", "LOCATION"});

		/* Create a StringBuilder object */
		StringBuilder sBuilder = new StringBuilder();

		/* Read a data from data base */
		if (cr.moveToFirst()) {

			/* Calculate indexes of columns */
			int col_id = cr.getColumnIndex(DBEmpl.TableDep._ID);
			int col_name = cr.getColumnIndex(DBEmpl.TableDep.C_NAME);
			int col_info = cr.getColumnIndex(DBEmpl.TableDep.C_LOCA);

			/* Read data */
			do {

				sBuilder.append(cr.getString(col_id) + " " +
						cr.getString(col_name) + " - " + cr.getString(col_info) + "\n");

			} while (cr.moveToNext());

			/* Show data */
			tvText.setText(sBuilder.toString());

		}

		/* Close a Cursor */
		cr.close();

		/* Add new department *//*
		MyApp.getDB().addDep("Department 1", "Location 1");*/

		/* Delete department */
		//MyApp.getDB().deleteDep(5);
		/*
		*//**
		 * Example 2
		 * *//*

		*//* Get a Cursor *//*
		Cursor c = MyApp.getDB().getReadableCursor(DBEmpl.TableDep.T_NAME);
		
		*//* Create arrays of columns and UI elements *//*
		String[] from = {DBEmpl.TableDep.C_NAME, DBEmpl.TableDep.C_LOCA};
		int[] to = {R.id.tvName, R.id.tvLocation};

		*//* Create simple Cursor adapter *//*
		SimpleCursorAdapter lvAdapter = new SimpleCursorAdapter(this, 
				R.layout.list_item, c, from, to, 1);

		*//* setting up adapter to list view  *//*
		lvList.setAdapter(lvAdapter);*/

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act_main, menu);
		return true;
	}

}
