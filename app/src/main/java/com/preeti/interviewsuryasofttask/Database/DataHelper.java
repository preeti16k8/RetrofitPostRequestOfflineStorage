package com.preeti.interviewsuryasofttask.Database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataHelper extends SQLiteOpenHelper {

    /*private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();*/

    static String DATABASE_NAME = "USERDB";
    static String USER_TABLE = "user";
    static String First_Name = "first_name";
    static String Last_Name = "last_name";
    static String Email = "email";
    static String Image_Url = "image_url";
    static String CREATE_ADMIN_TABLE = "";



    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        CREATE_ADMIN_TABLE = "CREATE TABLE " + USER_TABLE + "(" + Email + " TEXT PRIMARY KEY, " + First_Name + " VARCHAR, " + Last_Name + " VARCHAR, " + Image_Url + " VARCHAR " + ");";
        db.execSQL(CREATE_ADMIN_TABLE);

    }




    public void adduser(Context mContext, String userEmail, String firstName, String lastName, String userImage)

    {

        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(First_Name, firstName);
            values.put(Last_Name, lastName);
            values.put(Email, userEmail);
            values.put(Image_Url, userImage);


            db.insertWithOnConflict(USER_TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            db.close();

        } catch (Exception e) {
            Log.e("Insertion exception", "cannot insert", e);
        }
    }


    public void deleteUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USER_TABLE, null, null);
        db.close();
    }


    public int getUserCount() {
        String countQuery = "SELECT  * FROM " + USER_TABLE;
        int count = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null && !cursor.isClosed()) {
            count = cursor.getCount();
            cursor.close();
        }
        return count;
    }


    public Cursor getuser() {
        String selectuser = "SELECT * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectuser, null);


        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (newVersion > oldVersion) {
            // on upgrade drop older tables
            db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);

            // create new tables
            onCreate(db);

        }
    }
}