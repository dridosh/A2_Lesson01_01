package android_2.lesson01.app01;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.widget.ListView;

import android_2.lesson01.app01.lib.DBTodo;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        App.Log("MainActivity onCreate");

		// Initialize UI components

		ListView lvList = (ListView) findViewById(R.id.lvTotoList);




		//Get a Cursor
		Cursor dep_cursor = App.getDB().getReadableCursor(DBTodo.TableTodo.TABLE_NAME);
		
		// Create arrays of columns and UI elements
		String[] dep_from = {DBTodo.TableTodo.C_CATEGORY,
				DBTodo.TableTodo.C_SUMMARY};
		int[] dep_to = {R.id.tv_category, R.id.tv_summary};

		// Create simple Cursor adapter
		SimpleCursorAdapter lvDepAdapter = new SimpleCursorAdapter(this,
				R.layout.list_item, dep_cursor, dep_from, dep_to, 1);

		// setting up adapter to list view
		lvList.setAdapter(lvDepAdapter);
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act_main, menu);
		return true;
	}


}
