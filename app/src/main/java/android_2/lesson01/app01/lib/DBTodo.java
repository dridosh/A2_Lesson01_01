package android_2.lesson01.app01.lib;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import android_2.lesson01.app01.R;

public class DBTodo extends DBSQLite {
	
	// Private field for store SQL WHERE for one element (by id)
	private static final String SQL_WHERE_BY_ID = BaseColumns._ID + "=?";
	
	// private (-Public) constant that store a name of data base
	private static final String DB_NAME = "Todo.db";
	
	// private (-Public) constant that store a version of data base
	private static final int DB_VERSION = 1;


	/** Constructor with one parameter that describe a link to the Context object */
	public DBTodo(Context context) {
		//null - CursorFactory factory
		super(context, DB_NAME, null, DB_VERSION);

	}		
	
	/** Called when the database is created for the first time. */
	@Override
	public void onCreate(SQLiteDatabase db) {
		/* Create tables */
        DBSQLite.execSQL(db, TableTodo.SQL_CREATE);

		/* Fill table tTodo */
		// load data from application resources
		String[] example_todo = getContext().getResources().getStringArray(	R.array.todo_start);

		// create object for store couples of names and values
		ContentValues contentValues = new ContentValues(example_todo.length);

		// Fill table TableTodo
		for (int i = 0; i < example_todo.length; i++) {

			// parse information about department (tuple (кортеж) = строка в таблице)
			String[] tuple = example_todo[i].split("-");

			// fill values
			contentValues.put(TableTodo.C_CATEGORY, tuple[0]);
			contentValues.put(TableTodo.C_SUMMARY, tuple[1]);
			contentValues.put(TableTodo.C_DESCRIPTION,tuple[2]);

			// add record to a data base
			db.insert(TableTodo.TABLE_NAME, null, contentValues);

		}

	}
	
	/**  Called when the database needs to be upgraded. */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		/* Drop tables */
		DBSQLite.dropTable(db, TableTodo.TABLE_NAME);
	}

////////////////////////////////////////////////////////////////////////////////////////////////////
//	Методы для добавления, изменения, удаления данных в  таблице TableTodo                        //
////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Add to TableTodo
	 *
	 * @param category - name of employee
	 * @param summary - information of employee
	 * @param description - id department
	 * @return long	the row ID of the newly inserted row, or -1 if an error occurred
	 */
	public long addEmpl(String category, String summary, String description) {

		/* Create a new map of values, where column names are the keys */
		ContentValues v = new ContentValues();

		/* Fill values */
		v.put(TableTodo.C_CATEGORY,category);
		v.put(TableTodo.C_SUMMARY, summary);
		v.put(TableTodo.C_DESCRIPTION, description);

		/* Add item to a data base */
		return this.getWritableDatabase().insert(TableTodo.TABLE_NAME, null, v);
	}


	/**
	 * update row from TableTodo
	 *
	 * @param category-
	 * @param summary - information of employee
	 * @param description- id department
	 * @param id- id of record
	 * @return boolean - update 1 row (OK)
	 */
	public boolean updateEmpl(String category, String summary, String description, long id) {

		/* Create a new map of values, where column names are the keys */
		ContentValues v = new ContentValues();

		/* Fill values */
		v.put(TableTodo.C_CATEGORY,category);
		v.put(TableTodo.C_SUMMARY, summary);
		v.put(TableTodo.C_DESCRIPTION, description);


		/* Update information */
		return 1 == this.getWritableDatabase().update(TableTodo.TABLE_NAME, v,
				SQL_WHERE_BY_ID, new String[] {String.valueOf(id)});
	}


	/**
	 * Public static class that contains information about table tTodo
	 * */
	public static class TableTodo implements BaseColumns {
		
		// Name of this table.
		public static final String TABLE_NAME = "tTodo";

	     //
		public static final String C_CATEGORY = "COLUMN_CATEGORY";

	     //
		public static final String C_SUMMARY = "COLUMN_SUMMARY";

	     //
		public static final String C_DESCRIPTION = "COLUMN_DESCRIPTION";
		
		// SQL query for a create this table.
		static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME +
				" (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				C_CATEGORY + " TEXT," +
				C_SUMMARY + " TEXT," +
				C_DESCRIPTION + " TEXT)";
	}



}
