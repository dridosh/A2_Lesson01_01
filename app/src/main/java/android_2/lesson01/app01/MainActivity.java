package android_2.lesson01.app01;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.widget.TextView;

import android_2.lesson01.app01.lib.DBEmployees;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        App.Log("MainActivity onCreate");

		/* Initialize UI components */
		TextView tvText = (TextView) findViewById(R.id.tvText);
//		ListView lvList = (ListView) findViewById(R.id.lvList);

		/*************
		 * Example 1 *
		 *************/

		/* Create Cursor for read a data from data base */
		Cursor cursor = App.getDB().getReadableCursor(DBEmployees.TableDep.TABLE_NAME);
//		Cursor cr2 = App.getDB().getReadableCursorWithSelectedData(DBEmployees.TableDep.TABLE_NAME, new String[]{"NAME", "LOCATION"});

		/* Create a StringBuilder object */
		StringBuilder sBuilder = new StringBuilder();

		/* Read a data from data base */
		if (cursor.moveToFirst()) {

			/* Calculate indexes of columns */
			int col_id = cursor.getColumnIndex(DBEmployees.TableDep._ID);
			int col_name = cursor.getColumnIndex(DBEmployees.TableDep.F_DEPARTMENT_NAME);
			int col_info = cursor.getColumnIndex(DBEmployees.TableDep.F_DEPARTMENT_LOCATION);

			/* Read data */
			do {

				sBuilder.append(cursor.getString(col_id) + " " +
						cursor.getString(col_name) + " - " + cursor.getString(col_info) + "\n");

			} while (cursor.moveToNext());

			/* Show data */
			tvText.setText(sBuilder.toString());

		}

		/* Close a Cursor */
		cursor.close();

		/* Add new department *//*
		App.getDB().addDep("Department 1", "Location 1");*/

		/* Delete department */
		//App.getDB().deleteDep(5);
		/*


		 */

		/*************
		 * Example 2 *
		 *************/


        /*

		*//* Get a Cursor *//*
		Cursor c = App.getDB().getReadableCursor(DBEmployees.TableDep.TABLE_NAME);
		
		*//* Create arrays of columns and UI elements *//*
		String[] from = {DBEmployees.TableDep.F_DEPARTMENT_NAME, DBEmployees.TableDep.F_DEPARTMENT_LOCATION};
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
