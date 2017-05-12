package android_2.lesson01.app01.lib;

import android_2.lesson01.app01.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class DBEmpl extends DBSQLite {
	
	/* Private field for store SQL WHERE for one element (by id) */
	private static final String SQL_WHERE_BY_ID = BaseColumns._ID + "=?";
	
	/* Public constant that store a name of data base */
	public static final String DB_NAME = "DBEmpl.db";
	
	/* Public constant that store a version of data base */
	public static final int DB_VERSION = 2;	

	/** 
	 * Constructor with one parameter that describe a link 
	 * to the Context object.
	 * 	@param context	The context object.
	 * */
	public DBEmpl(Context context) {
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
		String[] deps = getContext().getResources().getStringArray(
				R.array.dep_items);
		
		// create object for store couples of names and values
		ContentValues values = new ContentValues(deps.length);
				
		// Fill table tDep 
		for (int i = 0; i < deps.length; i++) {			
		
			// parse information about department 
			String[] dep = deps[i].split("-");	
			
			// fill values
			values.put(TableDep.C_NAME, dep[0]);
			values.put(TableDep.C_LOCA, dep[1]);
			
			// add record to a data base
			db.insert(TableDep.T_NAME, null, values);
			
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
		DBSQLite.dropTable(db, TableEmpl.T_NAME);
		DBSQLite.dropTable(db, TableDep.T_NAME);
		
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
		v.put(TableDep.C_NAME, name);
		v.put(TableDep.C_LOCA, location);
		
		/* Add item to a data base */
		return this.getWritableDatabase().insert(TableDep.T_NAME, null, v);
		
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
		v.put(TableDep.C_NAME, name);
		v.put(TableDep.C_LOCA, location);
		
		/* Update information */
		return 1 == this.getWritableDatabase().update(TableDep.T_NAME, v, 
				SQL_WHERE_BY_ID, new String[] {String.valueOf(id)});			
	}
	
	
	/**
	 * Delete department from a data base.
	 * @param id	of element that will be deleted
	 * @return True, if was been deleted only one element
	 * */
	public boolean deleteDep(long id) {		
		return 1 == this.getWritableDatabase().delete(
				TableDep.T_NAME, SQL_WHERE_BY_ID, 
				new String[] {String.valueOf(id)});			
	}
	
	/**
	 * Public static class that contains information about table tEmpl
	 * */	
	public static class TableEmpl implements BaseColumns {
		
		/** Name of this table. */
		public static final String T_NAME = "tEmpl";
				
		/**
	     * The name of employee.
	     * <P>Type: TEXT</P>
	     */
		public static final String C_NAME = "NAME";
		
		/**
	     * Information about employee. 
	     * <P>Type: TEXT</P>
	     */
		public static final String C_INFO = "INFO";
		
		/**
	     * Department (id) that related with this employee.     
	     * <P>Type: INTEGER</P>
	     */
		public static final String C_DEP_ID  = "DEP_ID";
		
		/** SQL query for a create this table. */
		public static final String SQL_CREATE = "CREATE TABLE " + T_NAME + 
				" (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
				C_NAME + " TEXT," + 
				C_INFO + " TEXT," + 
				C_DEP_ID + " INTEGER)";	
	}
	
	/**
	 * Public static class that contains information about table tDep
	 * */	
	public static class TableDep implements BaseColumns {
		
		/** Name of this table. */
		public static final String T_NAME = "tDep";
		
		/**
	     * The name of department.
	     * <P>Type: TEXT</P>
	     */
		public static final String C_NAME = "NAME";
		
		/**
	     * Department location.
	     * <P>Type: TEXT</P>
	     */
		public static final String C_LOCA = "LOCATION";
		
		/** SQL query for a create this table. */
		public static final String SQL_CREATE = "CREATE TABLE " + T_NAME + 
				" (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
				C_NAME + " TEXT," + 
				C_LOCA + " TEXT)";	
	}		

}
