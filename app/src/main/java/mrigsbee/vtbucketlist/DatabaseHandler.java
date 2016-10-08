package mrigsbee.vtbucketlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "database";
    private static final String TABLE = "list_entries";

    // Contacts Table Columns names
    static final String _ID = "_id";
    static final String KEY_CHECKBOX = "checkbox";
    static final String KEY_ENTRY = "entry";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String tbl = "CREATE TABLE " + TABLE + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_CHECKBOX + " TEXT, "
                + KEY_ENTRY + " TEXT" + ");";
        db.execSQL(tbl);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);

        onCreate(db);
    }

    public void add(TableEntry entry) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CHECKBOX, entry.getCheckbox());
        values.put(KEY_ENTRY, entry.getEntry());

        db.insert(TABLE, null, values);
        db.close();
    }

    public TableEntry get(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE, new String[] {_ID,
                        KEY_CHECKBOX, KEY_ENTRY }, _ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        TableEntry entry = new TableEntry(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        cursor.close();
        return entry;
    }

    public Cursor getAllRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE,
                new String[] {DatabaseHandler._ID, DatabaseHandler.KEY_CHECKBOX, DatabaseHandler.KEY_ENTRY},
                null, null, null, null, null
        );
        cursor.moveToFirst();
        return cursor;
    }

    public List<TableEntry> getAll() {
        List<TableEntry> list = new ArrayList<TableEntry>();

        String selectQuery = "SELECT  * FROM " + TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TableEntry entry = new TableEntry();
                entry.setId(Integer.parseInt(cursor.getString(0)));
                entry.setCheckbox(cursor.getString(1));
                entry.setEntry(cursor.getString(2));

                list.add(entry);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public int update(TableEntry entry) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CHECKBOX, entry.getCheckbox());
        values.put(KEY_ENTRY, entry.getEntry());

        return db.update(TABLE, values, _ID + " = ?",
                new String[] { String.valueOf(entry.getId()) });
    }

    public void delete(TableEntry entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, _ID + " = ?",
                new String[] { String.valueOf(entry.getId()) });
        db.close();
    }


    public int dbSize() {
        String countQuery = "SELECT  * FROM " + TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}