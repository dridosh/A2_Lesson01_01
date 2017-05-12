package android_2.lesson01.app01.lib;

import android_2.lesson01.app01.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class DBEmployees extends DBSQLite {
	
	// Private field for store SQL WHERE for one element (by id)
	private static final String SQL_WHERE_BY_ID = BaseColumns._ID + "=?";
	
	// private (-Public) constant that store a name of data base
	private static final String DB_NAME = "DBEmployees.db";
	
	// private (-Public) constant that store a version of data base
	private static final int DB_VERSION = 2;

	/** 
	 * Constructor with one parameter that describe a link 
	 * to the Context object.
	 * 	@param context	The context object.
	 * */
	public DBEmployees(Context context) {
		//null - CursorFactory factory
		super(context, DB_NAME, null, DB_VERSION);

	}		
	
	/**
	 * Called when the database is created for the first time. This is 
	 * where the creation of tables and the initial population of the 
	 * tables should happen.
	 * 	@param db	The database.
	 * */
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		/* Create tables */
		DBSQLite.execSQL(db, TableEmpl.SQL_CREATE);	
		DBSQLite.execSQL(db, TableDep.SQL_CREATE);
		
		/* Fill table tDep */
		
		// load data from application resources
		String[] departments = getContext().getResources().getStringArray(
				R.array.dep_items);
		
		// create object for store couples of names and values
		ContentValues contentValues = new ContentValues(departments.length);
				
		// Fill table tDep 
		for (int i = 0; i < departments.length; i++) {
		
			// parse information about department (tuple = кортеж = строка в таблице)
			String[] tuple = departments[i].split("-");
			
			// fill values
			contentValues.put(TableDep.F_DEPARTMENT_NAME, tuple[0]);
			contentValues.put(TableDep.F_DEPARTMENT_LOCATION, tuple[1]);
			
			// add record to a data base
			db.insert(TableDep.TABLE_NAME, null, contentValues);
			
		}
		
	}
	
	/** 
	 * Called when the database needs to be upgraded. The implementation 
	 * should use this method to drop tables, add tables, or do anything 
	 * else it needs to upgrade to the new schema version.
	 * @param db	The database.
	 * @param oldVersion	The old database version.
	 * @param newVersion	The new database version. 
	 * */		
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		/* Drop tables */
		DBSQLite.dropTable(db, TableEmpl.TABLE_NAME);
		DBSQLite.dropTable(db, TableDep.TABLE_NAME);
		
		/* Invoke onCreate method */
		this.onCreate(db);
			
	}
	
	/**
	 * Add information about department to a data base
	 * @param name	department name
	 * @param location	department location
	 * @return id of new element
	 * */
	public long addDep(String name, String location) {
		
		/* Create a new map of values, where column names are the keys */
		ContentValues v = new ContentValues();
		
		/* Fill values */
		v.put(TableDep.F_DEPARTMENT_NAME, name);
		v.put(TableDep.F_DEPARTMENT_LOCATION, location);
		
		/* Add item to a data base */
		return this.getWritableDatabase().insert(TableDep.TABLE_NAME, null, v);
		
	}
	
	/**
	 * Update information about department into a data base.
	 * @param name	new department name
	 * @param location	new department location
	 * @return True, if was been updated only one element
	 * */
	public boolean updateDep(String name, String location, long id) {
		
		/* Create a new map of values, where column names are the keys */
		ContentValues v = new ContentValues();
		
		/* Fill values */
		v.put(TableDep.F_DEPARTMENT_NAME, name);
		v.put(TableDep.F_DEPARTMENT_LOCATION, location);
		
		/* Update information */
		return 1 == this.getWritableDatabase().update(TableDep.TABLE_NAME, v,
				SQL_WHERE_BY_ID, new String[] {String.valueOf(id)});			
	}
	
	
	/**
	 * Delete department from a data base.
	 * @param id	of element that will be deleted
	 * @return True, if was been deleted only one element
	 * */
	public boolean deleteDep(long id) {		
		return 1 == this.getWritableDatabase().delete(
				TableDep.TABLE_NAME, SQL_WHERE_BY_ID,
				new String[] {String.valueOf(id)});			
	}
	
	/**
	 * Public static class that contains information about table tEmpl
	 * */	
	public static class TableEmpl implements BaseColumns {
		
		// Name of this table.
		public static final String TABLE_NAME = "tEmpl";
				

	     // field name - for employee name
		public static final String F_EMPLOYEES_NAME = "NAME";
		

	     // field name - Information about employee.
		public static final String F_INFO = "INFO";
		

	     //field name - Department (id) that related with this employee.
		public static final String F_DEP_ID = "DEP_ID";
		
		// SQL query for a create this table.
		public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME +
				" (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				F_EMPLOYEES_NAME + " TEXT," +
				F_INFO + " TEXT," +
				F_DEP_ID + " INTEGER)";
	}



	/**
	 * Public static class that contains information about table tDep
	 * */	
	public static class TableDep implements BaseColumns {
		
		// Name of table
		public static final String TABLE_NAME = "tDep";
		

	    // field name for department name
		public static final String F_DEPARTMENT_NAME = "NAME";
		

	    // field Department location
		public static final String F_DEPARTMENT_LOCATION = "LOCATION";
		
		// SQL query for a create this table.
		public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME +
				" (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				F_DEPARTMENT_NAME + " TEXT," +
				F_DEPARTMENT_LOCATION + " TEXT)";
	}		

}
