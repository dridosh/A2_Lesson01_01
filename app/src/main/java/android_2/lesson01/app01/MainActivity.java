package android_2.lesson01.app01;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListView;

import android_2.lesson01.app01.lib.DBEmployees;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        App.Log("MainActivity onCreate");

		// Initialize UI components

		ListView lvList = (ListView) findViewById(R.id.lvList);
        ListView emplList = (ListView) findViewById(R.id.empl_List);




		//Get a Cursor
		Cursor dep_cursor = App.getDB().getReadableCursor(DBEmployees.TableDep.TABLE_NAME);
		
		// Create arrays of columns and UI elements
		String[] dep_from = {DBEmployees.TableDep.F_DEPARTMENT_NAME,
				DBEmployees.TableDep.F_DEPARTMENT_LOCATION};
		int[] dep_to = {R.id.tvName, R.id.tvLocation};

		// Create simple Cursor adapter
		SimpleCursorAdapter lvDepAdapter = new SimpleCursorAdapter(this,
				R.layout.list_item, dep_cursor, dep_from, dep_to, 1);

		// setting up adapter to list view
		lvList.setAdapter(lvDepAdapter);


////////////////////////////////////////////////////////////////////////////////////////////////////
//	вывод дпнных из таблице TableEmpl                                                             //
////////////////////////////////////////////////////////////////////////////////////////////////////



    //Get a Cursor
    Cursor cursor = App.getDB().getReadableCursor(DBEmployees.TableEmpl.TABLE_NAME);

    // Create arrays of columns and UI elements
    String[] empl_from = {DBEmployees.TableEmpl.F_EMPLOYEES_NAME, DBEmployees.TableEmpl.F_INFO,
			DBEmployees.TableEmpl.F_DEP_ID};
    int[] empl_to = {R.id.empl_name, R.id.empl_info,R.id.empl_dep};

    // Create simple Cursor adapter
    SimpleCursorAdapter lvEmplAdapter = new SimpleCursorAdapter(this,
            R.layout.list_empl_item, cursor, empl_from, empl_to, 1);

    // setting up adapter to list view
		emplList.setAdapter(lvEmplAdapter);

}


}
